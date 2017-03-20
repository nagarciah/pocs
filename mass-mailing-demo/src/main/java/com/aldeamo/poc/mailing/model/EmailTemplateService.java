package com.aldeamo.poc.mailing.model;

import java.io.File;
import java.text.MessageFormat;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.transaction.Transactional;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.aldeamo.poc.mailing.dao.EmailTemplateRepository;

import net.lingala.zip4j.core.ZipFile;
import net.lingala.zip4j.exception.ZipException;

@Service
public class EmailTemplateService {
	
	private final Log log = LogFactory.getLog(EmailTemplateService.class);

	private EmailTemplateProcessor processor;
	private EmailTemplateRepository repository;
	
	@Value("${api.uploads.dir}")
	private File uploadsDir;
	
	@Value("${templates.processor.thymeleaf.baseFolder}")
	private File templatesBaseFolder;

	
	@Autowired
	public EmailTemplateService(EmailTemplateProcessor procesor, EmailTemplateRepository repository) {
		super();
		this.processor = procesor;
		this.repository = repository;
	}

	public List<EmailTemplate> getTemplates(String customerId, String customerTemplateId) {
		return this.repository.findAllByCustomerIdAndCustomerTemplateId(customerId, customerTemplateId);
	}
	
	public String processTemplate(EmailMessage message, EmailTemplate emailTemplate, Map<String, Object> variables) {		
		return this.processor.setHtmlContent(message, emailTemplate, variables);
	}

	public EmailTemplateProcessor getProcesor() {
		return processor;
	}
	public void setProcesor(EmailTemplateProcessor procesor) {
		this.processor = procesor;
	}
	public EmailTemplateRepository getRepository() {
		return repository;
	}
	public void setRepository(EmailTemplateRepository repository) {
		this.repository = repository;
	}

	@Transactional
	public Long save(EmailTemplate template, MultipartFile templateZipFile) {
		if(log.isInfoEnabled()){
			log.info("Guardando plantilla: " + template.toString());
		}
		
		if(template.getHtmlFilename() != null && template.getHtmlFilename().endsWith(".html")){
			template.setHtmlFilename( template.getHtmlFilename().replaceAll(".html", "") );
			
			if(log.isDebugEnabled()){
				log.debug("Reemplazada la extensión en el nombre de la plantilla: " + template.toString());
			}
		}
		
		getRepository().save(template); // FIXME Que hacer con el registro, si el archivo no se procesa? @Transactional? Implementar Test
		File uploadedFile = saveUploadedFileAndUpdateTemplate(template, templateZipFile);
		installUploadedTemplate(template, uploadedFile);
				
		return template.getId();
	}
	
	
	private File saveUploadedFileAndUpdateTemplate(EmailTemplate template, MultipartFile templateZipFile)
	{
		try{
			if(templateZipFile==null || templateZipFile.getBytes()==null || templateZipFile.getBytes().length==0)
			{
				throw new TemplateProcessingException("El archivo recibido está vacío o corrupto");
			}
		}catch(Exception e){
			throw new TemplateProcessingException("El archivo recibido está vacío o corrupto", e);
		}
		
		// Calcula el destino del archivo recibido y lo mueve allí
		String filename = templateZipFile.getOriginalFilename();
		if(filename==null || filename.trim().length()==0){
			filename = UUID.randomUUID().toString();
		} 

		// TODO Unificar este código con el método de gestion de archivos del SendingProcessingService
		template.setTemplateSubdir("template" + template.getId()); 
		
		String targetDirName = new StringBuilder()
				.append("customer")
				.append(template.getCustomerId())
				.append(File.separator)
				.append(template.getTemplateSubdir())
				.toString();
		
		File targetDir = new File(uploadsDir, targetDirName);
		targetDir.mkdirs();
		
		File targetFile = new File(targetDir, filename);
		
		try {
			templateZipFile.transferTo(targetFile);
		} catch (Exception e) {
			throw new TemplateProcessingException("Error guardando el archivo recibido", e);
		}

		// Actualiza y guarda la información del archivo en el envío, para auditoría
		template.setUploadedZipFilename(targetDirName + File.separator + filename);
		getRepository().save(template);
		
		return targetFile;
	}	
	
	
	private void installUploadedTemplate(EmailTemplate template, File uploadedFile) {
		if(log.isInfoEnabled()){
			log.info("Instalando plantilla... " + template);
		}
		
		if(template.getTemplateSubdir() == null){
			template.setTemplateSubdir("tpl_" + template.getId() + File.separator);
		}
		
		String compressedFile = uploadedFile.getAbsolutePath();
		String destination = templatesBaseFolder.getAbsolutePath() + File.separator + template.getTemplateSubdir();
		
		if(log.isDebugEnabled()){
			log.debug(MessageFormat.format("Zip origen={0}, Destino={1}", compressedFile, destination));
		}
		
		try {
			ZipFile zipFile = new ZipFile(compressedFile);
		    if (zipFile.isEncrypted()) {
		    	log.error("El archivo está encriptado=" + compressedFile);
		        //zipFile.setPassword(password); // Por ahora no están soportados los archivos con password
		    	throw new TemplateProcessingException("Los archivos Zip encriptados no están soportados.");
		    }
		    zipFile.extractAll(destination);
		} catch (ZipException e) {
		    throw new TemplateProcessingException("Error descomprimiendo archivo zip en " + destination, e);
		}
		
		if(log.isInfoEnabled()){
			log.info("Plantilla instalada en:" + destination);
		}
	}
}