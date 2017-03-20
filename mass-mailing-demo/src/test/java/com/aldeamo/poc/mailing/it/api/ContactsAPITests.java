package com.aldeamo.poc.mailing.it.api;

import static com.aldeamo.poc.mailing.testutil.WiserAssertions.assertReceivedMessage;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.isA;
//import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThan;
import static org.junit.Assert.fail;

import java.io.File;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.subethamail.wiser.Wiser;

import com.aldeamo.poc.mailing.api.dto.ContactUnsubscribeRequest;
import com.aldeamo.poc.mailing.model.ContactUnsubscribeReason;
import com.aldeamo.poc.mailing.testutil.IntegrationTestCategory;

import io.restassured.http.ContentType;

@Category(IntegrationTestCategory.class)
public class ContactsAPITests {

	private String apiBaseUrl = "http://localhost:8080/api/v0.1/exclusion-list";
	private String emailApiBaseUrl = "http://localhost:8080/api/v0.1/email";
	
	private Wiser wiser;
	private String testExcelFilename = "testData.xlsx";
	private String testExclusionList = "promociones-banco";
	
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

	private ContactUnsubscribeRequest createDefaultRequest(){
		String senderToken = "FIXME";
		String customerId = "8";
		String exclusionListId = testExclusionList;
		String description = "Llamada al Centro de Servicio al cliente";
		String recipientEmail = "nelson.garcia@aldeamo.com";
		ContactUnsubscribeReason reason = ContactUnsubscribeReason.MANUALLY_ADDED;
		
		ContactUnsubscribeRequest unsubscribeRequest = new ContactUnsubscribeRequest();
		unsubscribeRequest.setSenderToken(senderToken); // ID de SparkPost de la subcuenta
		unsubscribeRequest.setCustomerId(customerId);   // FIXME Debería extraerse de la bd de tokens
		unsubscribeRequest.setRecipientEmail(recipientEmail);	// TODO Aceptar lotes
		unsubscribeRequest.setExclusionListId(exclusionListId);
		unsubscribeRequest.setReason(reason);
		unsubscribeRequest.setDescription(description);
		
		return unsubscribeRequest;
    }
    
    
	@Test
	public void successfulUnsubscribeFromSpecificCustomerList() throws Exception {
		// Envía correos
		sendMassiveEmail();
		assertReceivedMessage(wiser).totalMessagesReceived(2);
        wiser.getMessages().clear();
        
        
		// Agrega email a la lista de exlusión
		ContactUnsubscribeRequest unsubscribeRequest = createDefaultRequest();
		
		given()
			.contentType(ContentType.JSON)
			.body(unsubscribeRequest)
			.log().all()
		.when()
			.put(apiBaseUrl + "/")
		.then()
			.log().all()
		.and()
			.statusCode(200)
			.contentType(ContentType.JSON)
			.body("entityId", isA(Integer.class))
			.body("entityId", greaterThan(0))
			.body("status", equalTo("SUCCESS"));
		

		// Verifica que no hayan llegado correos al email en lista de exclusion
		sendMassiveEmail();
		assertReceivedMessage(wiser).totalMessagesReceived(1);
	}
	
	@Ignore("Sin implementar")
	@Test
	public void successfulUnsubscribeFromAllCustomerLists() throws Exception {
		fail("Sin implementar");
	}
	
	
	private void sendMassiveEmail(){
    	String from = "postmaster@aldeamail.com";
    	String replyTo = null;
    	String subject = "API Integration Test";
		String customerTemplateId = "factura-1";
		String customerId = "8";
		String customerExclusionListId = "promociones-banco";
		String campaignId = "testCampaign1";
		String senderToken = "56f23b45a4faf26959dd6847a45fe4817bcc382a";

		File testFile = new File(EmailAPITests.class.getClassLoader().getResource(testExcelFilename).getFile());
		
		
		// assert: resultado del API
		given()
			//.contentType(ContentType.)
			.formParam("customerId", customerId)
			.formParam("campaignId", campaignId)
			.formParam("subject", subject)
			.formParam("from", from)
			.formParam("replyTo", replyTo)
			.formParam("customerTemplateId", customerTemplateId)
			.formParam("senderToken", senderToken)
			.formParam("customerExclusionListId", customerExclusionListId)
			.multiPart("emailDataFile", testFile)
			.log().all()
		.expect()
			.statusCode(200)
			.contentType(ContentType.JSON)
			.body("sendingId", isA(Integer.class))
			.and()
			.body("sendingId", greaterThan(0))
		.when()
			.post(emailApiBaseUrl + "/send-massive")
		.then()
			.log().all();
	}
}
