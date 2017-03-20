package com.aldeamo.poc.mailing.model;

import java.io.File;
import java.net.URI;
import java.text.MessageFormat;
import java.util.Map.Entry;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.aldeamo.poc.mailing.model.AuthenticationInfo.AuthType;

@Service
public class SMTPEmailService implements EmailService {
	
	private final Log log = LogFactory.getLog(SMTPEmailService.class); 
	
	private JavaMailSender javaMailSender;
	
	// FIXME Estas variables y la lógica asociada a ellas, genera acoplamiento con Sparkpost. 
	// Debe reemplazarse por un factory de proveedores de relay de email o dedicar este relay sólo a SparkPost
	@Value("${spring.mail.username}")
	private String defaultUsername;
	
	@Value("${sparkpost.api.usernameTemplate}")
	private String sparkpostApiUsernameTemplate;
	
	@Value("${sparkpost.api.masterKey}")
	private String sparkpostApiMasterKey;
	
	@Autowired
	public SMTPEmailService(JavaMailSender javaMailSender){
		this.javaMailSender = javaMailSender;
	}
	
	
	//@Async
	@Override
	public void send(EmailMessage message){
        SimpleMailMessage mail = new SimpleMailMessage();
		mail.setTo(message.getTo().toArray(new String[message.getTo().size()]));
		mail.setFrom( message.getFrom() );
		mail.setSubject( message.getSubject() );
		mail.setText( message.getHtmlContent() );
		
		javaMailSender.send(mail);
	}
	

	@Override
	public void sendMime(EmailMessage message) throws MessagingException{
		Boolean isMultipart = Boolean.TRUE;
		MimeMessage mail = this.javaMailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(mail, isMultipart);

		helper.setTo(message.getTo().toArray(new String[message.getTo().size()]));
		helper.setFrom( message.getFrom() );
		helper.setSubject( message.getSubject() );
		helper.setText( message.getHtmlContent() );
		helper.setReplyTo( message.getReplyTo()==null ? message.getFrom() : message.getReplyTo() );
		helper.setText(message.getPlainTextContent(), message.getHtmlContent());
		
		for(Entry<String, URI> attachment : message.getAttachments().entrySet()){
			helper.addAttachment(attachment.getKey(), new File(attachment.getValue()));
		}
		
		for(Entry<String, File> inline : message.getInlineFiles().entrySet()){
			helper.addInline(inline.getKey(), inline.getValue());
		}
		
		javaMailSender.send(mail);
	}
	
	/**
	 * Permite cambiar las credenciales STMP a utilizar en la conexión con el servicio SMTP. Las nuevas credenciales se utilizarán
	 * para los envíos subsecuentes hasta que se invoque nuevamente a este método con información de autenticación nueva
	 * @param authInfo 
	 */
	@Override
	public void updateAuthInfo(AuthenticationInfo authInfo) {
		if(authInfo == null){
			log.warn("Se ha intentado cambiar la autenticación SMTP pero el objeto con la información de autenticación es nulo. Continuará usando la configuración por defecto.");
		}else if(authInfo.getAuthType() == AuthType.CUSTOM_TOKEN){
			String newUsername = MessageFormat.format(sparkpostApiUsernameTemplate, authInfo.getToken());
			
			if(log.isDebugEnabled()){
				String logMsg = MessageFormat.format(
						"Se ha recibido información nueva de autenticación SMTP para enviar el mensaje: [token={0}]", 
						newUsername);
				log.debug(logMsg);
			}
			
			if(javaMailSender instanceof JavaMailSenderImpl){
				JavaMailSenderImpl sender = (JavaMailSenderImpl)javaMailSender;
				sender.setUsername(newUsername);
				sender.setPassword(sparkpostApiMasterKey);
			}else{
				log.warn("No se ha podido establecer la información de autenticación porque la variable 'javaMailSender' no es de tipo 'JavaMailSenderImpl'. Se intentará enviar el mensaje con la información de autenticación predefinida");
			}
		
		}else{
			String newUsername = authInfo.getUsername();
			if(newUsername == null){
				if(log.isWarnEnabled()){
					log.warn("Se intentó cambiar la autenticación SMTP con usr/pwd, pero el username tiene valor nulo. Se usará el username configurado por defecto: " + defaultUsername);
				}
				newUsername = defaultUsername;
			}
			
			String newPassword = authInfo.getPassword();
			if(newPassword == null){
				String errMsg = "Se intentó cambiar la autenticación SMTP con usr/pwd, pero el password tiene valor nulo."; 
				throw new EmailProcessingException(errMsg);
			}
			
			if(log.isDebugEnabled()){
				String logMsg = MessageFormat.format(
						"Se ha recibido información nueva de autenticación SMTP para enviar el mensaje: [username={0}], [password length={1}]", 
						newUsername,
						newPassword.length());
				log.debug(logMsg);
			}
			
			if(javaMailSender instanceof JavaMailSenderImpl){
				JavaMailSenderImpl sender = (JavaMailSenderImpl)javaMailSender;
				sender.setUsername(newUsername);
				sender.setPassword(newPassword);
			}else{
				log.warn("No se ha podido establecer la información de autenticación porque la variable 'javaMailSender' no es de tipo 'JavaMailSenderImpl'. Se intentará enviar el mensaje con la información de autenticación predefinida");
			}
		}
	}
}
