package com.nagarciah.pocs.ldap.it.api;

import static io.restassured.RestAssured.*;
import static io.restassured.module.jsv.JsonSchemaValidator.*;
import static org.hamcrest.Matchers.*;

import org.junit.Test;

import com.nagarciah.pocs.ldap.dto.Contact;

import io.restassured.http.ContentType;

public class PhoneBookApiTests {
	
	String apiBaseUrl = "/contact";
	
	@Test
	public void testGetAllContacts(){
		given()
			.contentType(ContentType.JSON)
			.auth().basic("user", "pwd") // FIXME El api esta sin seguridad
			.log().all()
		.when()
			.get(apiBaseUrl)
		.then()
			.log().all()
			.statusCode(200)
			.body(matchesJsonSchemaInClasspath("get-contacts-api.schema.json"))
			.body("name", hasItems("Natasha Romanov", "Steve Rogers", "Tony Stark"));
	}
	
	@Test
	public void testSaveContact(){
		saveContactAndAssertSaved(null);
	}
	
	private void saveContactAndAssertSaved(Contact contact){
		// FIXME Falla si la entrada no existe en ldap
		if(contact==null){
			contact = new Contact("dpool", "Wade Wilson", "Deadpool", "123456", "Canada", "123", "nueva imagen", "dpool@shield.com", "ca", "Ontario", "activo", false, "dpool_skype");
		}

		given()
			.contentType(ContentType.JSON)
			.auth().basic("user", "pwd")
			.body(contact)
			.log().all()
		.when()
			.post(apiBaseUrl)
		.then()
			.log().all()
			.statusCode(200)
			//.body(matchesJsonSchemaInClasspath("get-contacts-api.schema.json"))
			.body("id", is(contact.getId()));
	}
	
	@Test
	public void testDeleteContacts(){
		String contactId = "cable";
		
		given()
			.contentType(ContentType.JSON)
			.auth().basic("user", "pwd") // FIXME El api esta sin seguridad
			.log().all()
		.when()
			.get(apiBaseUrl)
		.then()
			.log().all()
			.statusCode(200)
			.body(matchesJsonSchemaInClasspath("get-contacts-api.schema.json"))
			.body("id", not(hasItems(contactId)));
		
		Contact contact = new Contact(contactId, "Nuevo nombre", "Nuevo rol", "Nuevo telefono", "Nueva direccion", "Nueva extension", "nueva imagen", "nuevo email", "co", "nueva sede", "nuevo estado", false, "nuevo skype");
		saveContactAndAssertSaved(contact);
		
		given()
			.contentType(ContentType.JSON)
			.auth().basic("user", "pwd") // FIXME El api esta sin seguridad
			.pathParam("contactId", contactId)
			.log().all()
		.when()
			.delete(apiBaseUrl + "/{contactId}")
		.then()
			.log().all()
			.statusCode(200);
		
		given()
			.contentType(ContentType.JSON)
			.auth().basic("user", "pwd") // FIXME El api esta sin seguridad
			.log().all()
		.when()
			.get(apiBaseUrl)
		.then()
			.log().all()
			.statusCode(200)
			.body(matchesJsonSchemaInClasspath("get-contacts-api.schema.json"))
			.body("id", not(hasItems(contactId)));
	}
}
