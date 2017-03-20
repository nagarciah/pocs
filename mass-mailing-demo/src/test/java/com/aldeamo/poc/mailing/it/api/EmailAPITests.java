package com.aldeamo.poc.mailing.it.api;

import static com.aldeamo.poc.mailing.testutil.WiserAssertions.assertMessagesNotReceived;
import static com.aldeamo.poc.mailing.testutil.WiserAssertions.assertReceivedMessage;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.isA;
import static org.hamcrest.Matchers.greaterThan;

import java.io.File;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.subethamail.wiser.Wiser;

import com.aldeamo.poc.mailing.model.EmailSendingRequest;
import com.aldeamo.poc.mailing.testutil.AppPropertiesReader;
import com.aldeamo.poc.mailing.testutil.IntegrationTestCategory;

import io.restassured.http.ContentType;

@Category(IntegrationTestCategory.class)
public class EmailAPITests {

	private String apiMasterKey = AppPropertiesReader.get("sparkpost.api.masterKey");
	private String apiBaseUrl = "http://localhost:8080/api/v0.1/email";
	private Wiser wiser;
	private String testExcelFilename = "testData.xlsx";
	
	//TODO Hacer un test para probar Excepción cuando se envía con una subcuenta, utilizando un dominio no configurado para esa subcuenta (org.springframework.mail.MailSendException: Failed messages: com.sun.mail.smtp.SMTPSendFailedException: 550 5.7.1 Unconfigured Sending Domain <aldeamo.com>)
	
    @Before
    public void setUp() throws Exception {
        wiser = new Wiser();
        wiser.setPort(2525);
        wiser.start();
    }

    @After
    public void tearDown() throws Exception {
        wiser.stop();
    }
    
    private EmailSendingRequest createDefaultSending(){
    	String from = "nelson.garcia@aldeamo.com"; //"sandbox@sparkpostbox.com"; // TODO Agregar nombre del remitente
		String to = "nelson.garcia@aldeamo.com"; 
		String subject = "API Integration Test";
		String customerTemplateId = "factura-1";
		String customerId = "1";
		
		EmailSendingRequest emailSending = new EmailSendingRequest();
		emailSending.setSenderToken("0"); // ID de SparkPost de la subcuenta
		emailSending.setFrom(from);
		emailSending.setCampaignId("testCampaign1");
		emailSending.setSubject(subject);
		emailSending.setCustomerTemplateId(customerTemplateId);
		emailSending.setCustomerId(customerId);
		emailSending.setTo(Arrays.asList(new String[]{to}));

		Map<String, Object> data = new HashMap<>();
		emailSending.setVariables(data);

		return emailSending;
    }
    
    
    @Test
	public void invalidTemplateId() {
		EmailSendingRequest emailSending = createDefaultSending();
		
		String customerId = "1";
		String customerTemplateId = "missing-template-id";
		
		emailSending.setCustomerId(customerId);
		emailSending.setCustomerTemplateId(customerTemplateId);
		
		// assert: resultado del API
		given()
			.contentType(ContentType.JSON)
			.body(emailSending)
		.when()
			.post(apiBaseUrl + "/send-single")
		.then()
			.log().all()
		.and()
			.statusCode(500)
			.contentType(ContentType.JSON)
			.body("message", containsString("No existe un plantilla con Id ["+customerTemplateId+"] para el cliente con customerId ["+customerId+"]"));
		
		// assert: envio realmente el correo
        assertMessagesNotReceived(wiser);
	}

    
	@Test
	public void missingTemplateVariables() {
		EmailSendingRequest emailSending = createDefaultSending();
		emailSending.setVariables(new HashMap<>());

		// assert: resultado del API
		given()
			.contentType(ContentType.JSON)
			.body(emailSending)
		.when()
			.post(apiBaseUrl + "/send-single")
		.then()
			.log().all()
		.and()
			.statusCode(500)
			.contentType(ContentType.JSON)
			.body("message", containsString("Error procesando plantilla de correo"));
		
		// assert: envio realmente el correo
        assertMessagesNotReceived(wiser);
	}

    
	@Test
	public void successfulSendingOnBehalfOfMasterAccount() {
		EmailSendingRequest emailSending = createDefaultSending();

		String nombre = "Maynard";
		String apellido = "James Keenan";
		
		Map<String, Object> data = new HashMap<>();
		data.put("nombre", nombre);
		data.put("apellido", apellido);
		emailSending.setVariables(data);
	

		// assert: resultado del API
		given()
			.contentType(ContentType.JSON)
			.body(emailSending)
		.when()
			.post(apiBaseUrl + "/send-single")
		.then()
			.log().all()
		.and()
			.statusCode(200)
			.contentType(ContentType.JSON)
			.body("sendingId", isA(Integer.class))
			.and()
			.body("sendingId", greaterThan(0));
		
		// assert: envio realmente el correo
        assertReceivedMessage(wiser)
                .from(emailSending.getFrom())
                .to(emailSending.getTo().get(0))
                .withSubject(emailSending.getSubject())
                /*.withContent(varValue)*/;
	}
	
	
	@Test
	public void successfulMassiveSendingOnBehalfOfMasterAccount() throws Exception {
		EmailSendingRequest emailSending = createDefaultSending();
		emailSending.setTo(null);
		emailSending.setVariables(null);
		emailSending.setSenderToken(apiMasterKey);
	
		File testFile = new File(EmailAPITests.class.getClassLoader().getResource(testExcelFilename).getFile());
		/*InputStream testFileIS = EmailAPITests.class.getClassLoader().getResourceAsStream(testExcelFilename);
		MockMultipartFile testEmailDataFile = new MockMultipartFile(
			    "emailDataFile",
			    "testData.xlsx",
			    "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet",
			    testFileIS);*/
		
		
		// assert: resultado del API
		given()
			//.contentType(ContentType.)
			.formParam("customerId", emailSending.getCustomerId())
			.formParam("campaignId", emailSending.getCampaignId())
			.formParam("subject", emailSending.getSubject())
			.formParam("from", emailSending.getFrom())
			.formParam("replyTo", emailSending.getReplyTo())
			.formParam("customerTemplateId", emailSending.getCustomerTemplateId())
			.formParam("senderToken", emailSending.getSenderToken())
			.multiPart("emailDataFile", testFile)
			.log().all()
		.expect()
			.statusCode(200)
			//.contentType(ContentType.JSON)
			.body("sendingId", isA(Integer.class))
			.and()
			.body("sendingId", greaterThan(0))
		.when()
			.post(apiBaseUrl + "/send-massive")
		.then()
			.log().all();

		
		// assert: envio realmente el correo
        assertReceivedMessage(wiser)
        		.totalMessagesReceived(2)
                .from(emailSending.getFrom())
                .to("nelson.garcia@aldeamo.com")
                .to("nagarciah@yahoo.com")
                .withSubject(emailSending.getSubject())
                /*.withContent(varValue)*/;
	}
	
	@Test
	public void successfulMassiveSendingOnBehalfOfSubAccount() throws Exception {
		EmailSendingRequest emailSending = createDefaultSending();
		emailSending.setTo(null);
		emailSending.setVariables(null);
		emailSending.setFrom("postmaster@aldeamail.com");
		// Para enviar por subcuenta y que aparezca en nombre de tal, se debe usar el api token de la subcuenta y el mismo usuario para todos.
		emailSending.setSenderToken("56f23b45a4faf26959dd6847a45fe4817bcc382a"); // Sistemcobro ID en SparkPost == 2
		emailSending.setCustomerId("8");
	
		File testFile = new File(EmailAPITests.class.getClassLoader().getResource(testExcelFilename).getFile());
		
		
		// assert: resultado del API
		given()
			//.contentType(ContentType.)
			.formParam("customerId", emailSending.getCustomerId())
			.formParam("campaignId", emailSending.getCampaignId())
			.formParam("subject", emailSending.getSubject())
			.formParam("from", emailSending.getFrom())
			.formParam("replyTo", emailSending.getReplyTo())
			.formParam("customerTemplateId", emailSending.getCustomerTemplateId())
			.formParam("senderToken", emailSending.getSenderToken())
			.multiPart("emailDataFile", testFile)
			.log().all()
		.expect()
			.statusCode(200)
			//.contentType(ContentType.JSON)
			.body("sendingId", isA(Integer.class))
			.and()
			.body("sendingId", greaterThan(0))
		.when()
			.post(apiBaseUrl + "/send-massive")
		.then()
			.log().all();

		
		// assert: envio realmente el correo
        assertReceivedMessage(wiser)
        		.totalMessagesReceived(2)
                .from(emailSending.getFrom())
                .to("nelson.garcia@aldeamo.com")
                .to("nagarciah@yahoo.com")
                .withSubject(emailSending.getSubject())
                /*.withContent(varValue)*/;
	}
}
