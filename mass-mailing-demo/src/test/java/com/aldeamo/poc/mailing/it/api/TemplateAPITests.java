package com.aldeamo.poc.mailing.it.api;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.isA;
import static org.hamcrest.Matchers.greaterThan;

import java.io.File;
import java.text.MessageFormat;

import org.junit.Test;
import org.junit.experimental.categories.Category;

import com.aldeamo.poc.mailing.api.dto.EmailTemplateRequest;
import com.aldeamo.poc.mailing.testutil.IntegrationTestCategory;

import io.restassured.http.ContentType;

@Category(IntegrationTestCategory.class)
public class TemplateAPITests {

	private String apiBaseUrl = "http://localhost:8080/api/v0.1/template";
	private String testTemplateFilename = "test-template.zip";
	
    
    private EmailTemplateRequest createDefaultRequest(){
		String category = "Mis Plantillas";
		String senderToken = "1";
		String customerId = "1";
		String customerTemplateId = "plantilla-pruebas";
		String description = "Una plantilla para pruebas de la aplicación";
		String friendlyName = "Plantilla de pruebas";
		String htmlFilename = "product-announcement.html";

    	EmailTemplateRequest templateRequest = new EmailTemplateRequest();
		templateRequest.setCategory(category);
		templateRequest.setSenderToken(senderToken); // ID de SparkPost de la subcuenta
		templateRequest.setCustomerId(customerId );  // FIXME Cual es la diferencia con el token? Podría extraerse a partir del token 
		templateRequest.setCustomerTemplateId(customerTemplateId);
		templateRequest.setDescription(description);
		templateRequest.setFriendlyName(friendlyName);
		templateRequest.setHtmlFilename(htmlFilename);
		
		return templateRequest;
    }
    
    
	
	@Test
	public void uploadOrReplaceTemplateWithRequiredFields() throws Exception {
		EmailTemplateRequest templateRequest = createDefaultRequest();
	
		File testFile = new File(TemplateAPITests.class.getClassLoader().getResource(testTemplateFilename).getFile());
		
		
		// assert: resultado del API
		given()
			//.contentType(ContentType.)
			.formParam("category", templateRequest.getCategory())
			.formParam("customerId", templateRequest.getCustomerId())
			.formParam("customerTemplateId", templateRequest.getCustomerTemplateId())
			.formParam("description", templateRequest.getDescription())
			.formParam("friendlyName", templateRequest.getFriendlyName())
			.formParam("htmlFilename", templateRequest.getHtmlFilename())
			.multiPart("templateZipFile", testFile)
			.log().all()
		.expect()
			.statusCode(200)
			//.contentType(ContentType.JSON)
			.body("id", isA(Integer.class))
			.body("id", greaterThan(0))
		.when()
			.post(apiBaseUrl + "/")
		.then()
			.log().all();
	}

	
	@Test
	public void listTemplatesForCustomer() {
		String customerTemplateId = "factura-1";
		String customerId = "1";
		String url = MessageFormat.format("/template/{0}", customerId, customerTemplateId);

		// assert: resultado del API
		given()
			.contentType(ContentType.JSON)
		.when()
			.get(apiBaseUrl + url)
		.then()
			.log().all()
		.and()
			.statusCode(200)
			.contentType(ContentType.JSON)
			// TODO Agregar validaciones mas precisas del cuerpo de la respuesta
			/*.body("sendingId", isA(Integer.class))
			.and()
			.body("sendingId", greaterThan(0))*/;		
	}
}
