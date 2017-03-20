package com.aldeamo.poc.mailing.it.api;

import static com.aldeamo.poc.mailing.testutil.WiserAssertions.assertMessagesNotReceived;
import static com.aldeamo.poc.mailing.testutil.WiserAssertions.assertReceivedMessage;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.isA;
import static org.hamcrest.Matchers.greaterThan;

import java.io.File;
import java.text.MessageFormat;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.subethamail.wiser.Wiser;

import com.aldeamo.poc.mailing.model.EmailSendingRequest;
import com.aldeamo.poc.mailing.testutil.IntegrationTestCategory;

import io.restassured.http.ContentType;

@Category(IntegrationTestCategory.class)
public class MetricsAPITests {

	private String apiBaseUrl = "http://localhost:8080/api/v0.1/metrics";

	private EmailSendingRequest createDefaultRequest(){
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
    
/*
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
*/
    
	@Test
	public void successfulMetricsForSubaccount() {
		EmailSendingRequest emailSending = createDefaultRequest();

		String customerToken = "1";
		
		// assert: resultado del API
		given()
			.contentType(ContentType.JSON)
			//.body(emailSending)
		.when()
			.get(apiBaseUrl + "/time-series/" + customerToken)
		.then()
			.log().all()
		.and()
			.statusCode(200)
			.contentType(ContentType.JSON)
			/*.body("sendingId", isA(Integer.class))
			.and()
			.body("sendingId", greaterThan(0))*/;
	}
}
