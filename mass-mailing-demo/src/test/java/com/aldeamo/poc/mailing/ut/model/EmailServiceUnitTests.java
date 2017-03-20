package com.aldeamo.poc.mailing.ut.model;

import static com.aldeamo.poc.mailing.testutil.WiserAssertions.assertReceivedMessage;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import javax.mail.MessagingException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.MailSendException;
import org.springframework.test.context.junit4.SpringRunner;
import org.subethamail.wiser.Wiser;

import com.aldeamo.poc.mailing.model.EmailMessage;
import com.aldeamo.poc.mailing.model.EmailProcessingException;
import com.aldeamo.poc.mailing.model.EmailService;
import com.aldeamo.poc.mailing.model.EmailTemplate;
import com.aldeamo.poc.mailing.model.EmailTemplateService;
import com.aldeamo.poc.mailing.testutil.UnitTestCategory;

@RunWith(SpringRunner.class)
@SpringBootTest
@Category(UnitTestCategory.class)
public class EmailServiceUnitTests {

	@Autowired
	//@InjectMocks TODO Posiblemente se necesiten mocks para que sea verdaderamente UT
	EmailService emailService;
	
	@Autowired
	EmailTemplateService templateService;
	
	@Value("${templates.processor.thymeleaf.baseFolder}")
	String templatesFolder;
	
	String templateDir;
	String templateName;
	String projectDir;

	private Wiser wiser;

	@Before
    public void setUp() throws Exception {
        wiser = new Wiser();
        wiser.setPort(2525);
        wiser.start();

        projectDir = new File(".").getAbsolutePath();		
		templateDir = templatesFolder + "slate/Receipt/";
		templateName = "slate/Receipt/receipt";
		
		//templateDir = templatesFolder + "/slate/Newsletter/";
		//templateDir = templatesFolder + "newsletter";
	}

    @After
    public void tearDown() throws Exception {
        wiser.stop();
    }

	
	@Test
	public void contextLoads() {
	}

	@Test
	public void sendMimeTest() throws MessagingException{
		String nombre = "García";
		String apellido = "García";
		Long total = 1000000L;
		String to = "nelson.garcia@aldeamo.com";
		String from = "sandbox@sparkpostbox.com";
		String subject = "Mensaje de prueba - Nelson";
		
		EmailMessage message = new EmailMessage();		
		message.addTo(to);
		message.addCc("jorge.torrico@aldeamo.com");
		message.addBcc("alfredo.angel@aldeamo.com");
		message.setFrom(from);
		message.setReplyTo(to);
		message.setSubject(subject);
		
		Map<String, Object> variables = new HashMap<>();
		variables.put("nombre", nombre);
		variables.put("apellido", apellido);
		variables.put("total", total);
		message.addVariables(variables);

		message.setPlainTextContent("Su cliente de correo no soporta HTML");

		//EmailTemplate plainTextTemplate = new EmailTemplate("category", "customerId", "mainFilename", "basePath", "imagesPath", "cssPath");
		EmailTemplate  htmlTemplate = new EmailTemplate("category", "customerId-1", "slate/Receipt/receipt", projectDir + "/mail-templates/slate/Receipt/", projectDir + "/mail-templates/slate/Receipt/");

		templateService.getProcesor().setHtmlContent(message, htmlTemplate, message.getVariables());

		emailService.sendMime(message);
		
		assertTrue(message.getHtmlContent().contains(nombre));
		
		// assert: envio realmente el correo
        assertReceivedMessage(wiser)
                .from(from)
                .to(to)
                .withSubject(subject)
                /*.withContent(varValue)*/;
	}
	
	@Test(expected=MailSendException.class)
	public void missingInlineFileWhileSendingEmail() throws MessagingException{
		String nombre = "García";
		String apellido = "García";
		Long total = 1000000L;
		
		EmailMessage message = new EmailMessage();
		
		message.addTo("nelson.garcia@aldeamo.com");
		message.addCc("jorge.torrico@aldeamo.com");
		message.addBcc("alfredo.angel@aldeamo.com");
		message.setFrom("sandbox@sparkpostbox.com");
		message.setReplyTo("nelson.garcia@aldeamo.com");
		message.setSubject("Mensaje de prueba - Nelson");
		
		Map<String, Object> variables = new HashMap<>();
		variables.put("nombre", nombre);
		variables.put("apellido", apellido);
		variables.put("total", total);
		message.addVariables(variables);

		//EmailTemplate plainTextTemplate = new EmailTemplate("category", "customerId", "mainFilename", "basePath", "imagesPath", "cssPath");
		EmailTemplate  htmlTemplate = new EmailTemplate("category", "customerId-1", "slate/Receipt/receipt_missing_file", projectDir + "/mail-templates/slate/Receipt/", projectDir + "/mail-templates/slate/Receipt/");

		message.setPlainTextContent("Su cliente de correo no soporta HTML");
		templateService.getProcesor().setHtmlContent(message, htmlTemplate, message.getVariables());

		emailService.sendMime(message);
	}
	
	// TODO Cambiar por excepción genérica que involucre a cualquier otro proveedor (Velocity, Freemaker, etc) porque esta es exclusiva de Thymeleaf
	// TODO Esta prueba queda mejor en el set de pruebas del TemplateService
	@Test(expected=EmailProcessingException.class)
	public void missingTemplateWhileSendingEmail() throws MessagingException{
		String nombreUsuario = "Nelson García";
		
		EmailMessage message = new EmailMessage();
		
		message.addTo("nelson.garcia@aldeamo.com");
		message.addCc("jorge.torrico@aldeamo.com");
		message.addBcc("alfredo.angel@aldeamo.com");
		message.setFrom("sandbox@sparkpostbox.com");
		message.setReplyTo("nelson.garcia@aldeamo.com");
		message.setSubject("Mensaje de prueba - Nelson");
		
		Map<String, Object> variables = new HashMap<>();
		variables.put("nombreUsuario", nombreUsuario);
		message.addVariables(variables);

		//EmailTemplate plainTextTemplate = new EmailTemplate("category", "customerId", "mainFilename", "basePath", "imagesPath", "cssPath");
		EmailTemplate  missing_template = new EmailTemplate("category", "customerId-1", "slate/Receipt/missing_template", projectDir + "/mail-templates/slate/Receipt/", projectDir + "/mail-templates/slate/Receipt/");

		message.setPlainTextContent("Su cliente de correo no soporta HTML");
		templateService.getProcesor().setHtmlContent(message, missing_template, message.getVariables());

		emailService.sendMime(message);
	}
}
