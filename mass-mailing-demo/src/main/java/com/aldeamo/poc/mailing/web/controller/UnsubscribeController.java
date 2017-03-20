package com.aldeamo.poc.mailing.web.controller;

import java.io.IOException;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import javax.validation.ValidationException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.aldeamo.poc.mailing.api.dto.ContactUnsubscribeRequest;
import com.aldeamo.poc.mailing.model.ContactUnsubscribeReason;
import com.aldeamo.poc.mailing.web.dto.UnsubscribeForm;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.ObjectMapper;
import com.mashape.unirest.http.Unirest;


@Controller
@RequestMapping("/unsubscribe")
public class UnsubscribeController {
	
	private static final String TEMPLATES_HOME_TPL = "unsubscribe";
	private static final String TEMPLATES_SUCCESS_TPL = "unsubscribe-success";
	private static final String SESSION_ATTRIBUTE_NAME = "unsubscribeRequest";
	private final Log log = LogFactory.getLog(UnsubscribeController.class); 

	
	@RequestMapping(value = { "/{encryptedParameters}" }, method = RequestMethod.GET)
	public String home(
			Model model, 
			@PathVariable String encryptedParameters, 
			HttpSession session,
			UnsubscribeForm unsubscribeForm
			/*BindingResult bindingResult*/) {
		
		// FIXME Desencriptar parámetros y guardarlos en la sesión para luego validarlos contra el formulario en el POST
		ContactUnsubscribeRequest unsubscribeRequest = new ContactUnsubscribeRequest();
		unsubscribeRequest.setRecipientEmail(encryptedParameters);
		unsubscribeRequest.setCustomerId("8");
		unsubscribeRequest.setExclusionListId("fixme"); // FIXME null excluiría de todas las listas de este cliente
		// TODO y otros parametros ...
		session.setAttribute(SESSION_ATTRIBUTE_NAME, unsubscribeRequest);
		
		return TEMPLATES_HOME_TPL;
	}
	
	@RequestMapping(value = { "/" }, method = RequestMethod.POST)
	public String confirmUnsubscription(
			@Valid UnsubscribeForm unsubscribeForm,
			BindingResult bindingResult, 
			Model model,
			HttpSession session) throws Exception {

        if (!bindingResult.hasErrors()) {
	 		try{
	 			if(emailIsValid(unsubscribeForm, session)){
		 			unsubscribe(unsubscribeForm, session);
					model.addAttribute("success", true);
	 			}else{
	 				// FIXME Agregar errores a bindingResult o model
	 				bindingResult.addError(new ObjectError("confirmedEmail", "La dirección de correo electrónico que ha indicado, no coincide con aquel que recibió el mensaje original"));
	 				return TEMPLATES_HOME_TPL;
	 			}
			}catch(ValidationException e){
				log.info("Datos de formulario invalidos", e);
				model.addAttribute("error", e.getMessage());
			}catch(Exception e){
				log.error("Error procesando peticion", e);
				model.addAttribute("error", e);
				throw e;
			}
        }else{
        	StringBuilder errors = new StringBuilder();
        	for(ObjectError e : bindingResult.getAllErrors()){
        		errors.append("Campo: ")
        		.append(e.getObjectName())
        		.append(", Error: ")
        		.append(e.getDefaultMessage());
        	}
        	throw new Exception("Errores en el formulario: " + bindingResult.toString());
        }

        return TEMPLATES_SUCCESS_TPL;
	}
	
	
	private boolean emailIsValid(UnsubscribeForm unsubscribeForm, HttpSession session) {
		String requestedEmail = ((ContactUnsubscribeRequest) session.getAttribute(SESSION_ATTRIBUTE_NAME)).getRecipientEmail();
		String confirmedEmail = unsubscribeForm.getConfirmedEmail();  // FIXME Validar nulos
		
		if(requestedEmail.equalsIgnoreCase(confirmedEmail.trim())){
			return true;
		}
		return false;
	}

	
	private void unsubscribe(UnsubscribeForm unsubscribeForm, HttpSession session) throws Exception {
		ContactUnsubscribeRequest unsubscribeRequest = (ContactUnsubscribeRequest) session.getAttribute(SESSION_ATTRIBUTE_NAME);
		String senderToken = unsubscribeRequest.getSenderToken();
		String customerId = unsubscribeRequest.getCustomerId();
		String exclusionListId = unsubscribeRequest.getExclusionListId();
		String description = unsubscribeRequest.getDescription();
		String recipientEmail = unsubscribeRequest.getRecipientEmail();
		ContactUnsubscribeReason reason = ContactUnsubscribeReason.MANUALLY_ADDED;

		unsubscribeRequest.setSenderToken(senderToken); // ID de SparkPost de la subcuenta
		unsubscribeRequest.setCustomerId(customerId);   // FIXME Debería extraerse de la bd de tokens
		unsubscribeRequest.setRecipientEmail( recipientEmail );	// TODO Aceptar lotes
		unsubscribeRequest.setExclusionListId( exclusionListId );
		unsubscribeRequest.setReason(reason);
		unsubscribeRequest.setDescription(description);


		// Permitir campos nulos
		HttpResponse<JsonNode> jsonResponse = Unirest.put("http://localhost:8080/api/v0.1/exclusion-list/")
				  .header("accept", "application/json")
				  .header("Content-Type", MediaType.APPLICATION_JSON_VALUE)
				  .body(unsubscribeRequest)
				  .asJson();
		
		if(jsonResponse.getStatus()!=200){
			String errorMsg = "Error invocando al servicio de contactos: " + jsonResponse.getBody().toString();
			log.error(errorMsg);
			throw new Exception(errorMsg);
		}
	}
	
	// FIXME Hacer esto sólo una vez, en una clase de configuración y agregar esto en un contextListener al bajar la aplicaición: Unirest.shutdown();
	static{
		Unirest.setObjectMapper(new ObjectMapper() {
		    private com.fasterxml.jackson.databind.ObjectMapper jacksonObjectMapper
		                = new com.fasterxml.jackson.databind.ObjectMapper();

		    public <T> T readValue(String value, Class<T> valueType) {
		        try {
		            return jacksonObjectMapper.readValue(value, valueType);
		        } catch (IOException e) {
		            throw new RuntimeException(e);
		        }
		    }

		    public String writeValue(Object value) {
		        try {
		            return jacksonObjectMapper.writeValueAsString(value);
		        } catch (JsonProcessingException e) {
		            throw new RuntimeException(e);
		        }
		    }
		});
	}
}
