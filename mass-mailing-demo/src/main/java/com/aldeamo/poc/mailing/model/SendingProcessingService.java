package com.aldeamo.poc.mailing.model;

import java.io.File;
import java.text.MessageFormat;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.transaction.Transactional;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.aldeamo.poc.mailing.conf.RabbitMQConfig;
import com.aldeamo.poc.mailing.dao.SendingRepository;
import com.aldeamo.poc.mailing.util.ExcelUtils;

// TODO Mejorar la experiencia de usuario, desacoplando los envíos de la respuesta, mediante una cola
@Service
public class SendingProcessingService {
	
	private final Log log = LogFactory.getLog(SendingProcessingService.class);

	@Autowired
	private EmailService emailService;

	@Autowired
	private SendingRepository sendingRepository;

	@Autowired
	private EmailTemplateService templateService;
	
	@Autowired
	private ContactsService contactsService;
	
	@Autowired
	private RabbitTemplate rabbitTemplate;
	

	@Value("${api.uploads.dir}")
	File uploadsDir;


	public Long process(EmailSendingRequest sending) throws Exception {

		// Guarda la solicitud para auditoria
		sendingRepository.save(sending);

		// Cambia la autenticación para usar la cuenta del remitente
		if(sending.getSenderToken()!=null){
			// FIXME Desacoplar esta clase de Sparkpost y buscar usr/pwd con base en token (hazelcast?)
			//emailService.updateAuthInfo(new AuthenticationInfo(sending.getSenderToken()));
			emailService.updateAuthInfo(new AuthenticationInfo(null, sending.getSenderToken()));
		}
		
		// TODO Agregar metadata como el ID de la campaña y de la subcuenta para tracking y reportes. 
		// Como está, aunque salga por un sending domain diferente, se le cuenta a la cuenta maestra

		// Prepara el mensaje con base en los parametros del envio
		EmailMessage message = new EmailMessage();
		message.setTo(sending.getTo());
		message.setCc(sending.getCc());
		message.setBcc(sending.getBcc());
		message.setFrom(sending.getFrom());
		message.setSubject(sending.getSubject());
		message.setVariables(sending.getVariables());
		
		if(sending.getReplyTo()==null 
				|| sending.getReplyTo().trim().length()>0)
		{
			message.setReplyTo(sending.getFrom()); 
		}

		// Preparar la plantilla del mensaje
		List<EmailTemplate> templateList = templateService.getTemplates(sending.getCustomerId(),
				sending.getCustomerTemplateId());
		if (templateList.isEmpty()) {
			throw new EmailProcessingException(
					MessageFormat.format("No existe un plantilla con Id [{0}] para el cliente con customerId [{1}]",
							sending.getCustomerTemplateId(), sending.getCustomerId()));
		}else if (templateList.size()>1){
			if(log.isWarnEnabled()){
				log.warn(MessageFormat.format("Se encontraron múltiples plantillas ({2}) con Id [{0}] para el cliente con customerId [{1}]",
								sending.getCustomerTemplateId(), 
								sending.getCustomerId(),
								templateList.size()));
			}
		}
		EmailTemplate emailTemplate = templateList.get(0);

		// Prepara el contenido del mensaje
		message.setHtmlContent(templateService.processTemplate(message, emailTemplate, sending.getVariables()));
		// TODO agregar procesamiento de plantillas de texto plano
		message.setPlainTextContent("TODO Procesar plantillas de texto plano");

		emailService.sendMime(message);

		return sending.getId();
	}

	
	@Transactional
	public Long processMassive(EmailSendingRequest sending, MultipartFile emailDataFile) throws Exception 
	{
		sendingRepository.save(sending); // FIXME Que hacer con el registro, si el archivo no se procesa? @Transactional? Implementar Test
		File savedFile = saveUploadedFileAndUpdateSending(sending, emailDataFile);
		ExcelFileModel fileContent = new ExcelUtils().readUploadedFile(savedFile);
		
		Iterator<Map<String, Object>> rows = fileContent.getContent().iterator();
		while(rows.hasNext()){
			Map<String, Object> row = rows.next();
			sending.setVariables(row);
			
			String to = row.get("email").toString(); // FIXME Controlar el error cuando el email venga nulo o el header no exista 
			sending.setTo(Arrays.asList(new String[]{to}));
			
			if(!isInExclusionList(sending)){
				//process(sending);
				enqueueEmail(sending);
			}else{
				// TODO Como registrar este evento en base de datos? Sólo un total?
				log.info("Receptor en lista de exclusión. No se enviará el correo: " + sending);
			}
		}
				
		return sending.getId();

	}
	
	private void enqueueEmail(EmailSendingRequest sending){
		rabbitTemplate.convertAndSend(RabbitMQConfig.EMAIL_QUEUE_NAME, sending);
	} 


	private boolean isInExclusionList(EmailSendingRequest sending) {
		for(String to :  sending.getTo()){
			boolean found = contactsService.isInExclusionList(
								sending.getCustomerId(), 
								sending.getCustomerExclusionListId(),
								to);
			if(found){
				return true;
			}
		}
		
		return false;
	}


	private File saveUploadedFileAndUpdateSending(EmailSendingRequest sending, MultipartFile emailDataFile) throws Exception
	{
		if(emailDataFile==null || emailDataFile.getBytes()==null || emailDataFile.getBytes().length==0)
		{
			throw new EmailProcessingException("El archivo recibido está vacío o corrupto");
		}
		
		// Calcula el destino del archivo recibido y lo mueve allí
		String filename = emailDataFile.getOriginalFilename();
		if(filename==null || filename.trim().length()==0){
			filename = UUID.randomUUID().toString();
		} 

		String targetDirName = new StringBuilder()
				.append("customer")
				.append(sending.getCustomerId())
				.append(File.separator)
				.append("mass-sending")
				.append(sending.getId())
				.toString();
		
		File targetDir = new File(uploadsDir, targetDirName);
		targetDir.mkdirs();
		
		File targetFile = new File(targetDir, filename);
		
		emailDataFile.transferTo(targetFile);

		// Actualiza y guarda la información del archivo en el envío, para auditoría
		sending.setEmailDataFile(targetDirName + File.separator + filename);
		sendingRepository.save(sending);
		
		return targetFile;
	}
}