package com.aldeamo.poc.mailing.web.controller;

import static io.restassured.RestAssured.given;

import java.io.File;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;
import javax.validation.ValidationException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.aldeamo.poc.mailing.model.EmailSendingRequest;
import com.aldeamo.poc.mailing.model.EmailTemplate;
import com.aldeamo.poc.mailing.model.EmailTemplateService;
import com.aldeamo.poc.mailing.model.UserInfo;
import com.aldeamo.poc.mailing.web.dto.SendWizardForm;
import com.aldeamo.poc.mailing.web.dto.UserAuthenticationInfo;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;

import io.restassured.http.ContentType;

@Controller
@RequestMapping("/campaign-wizard")
public class SendWizardController {

	private static final String SEND_WIZARD_HOME_TPL = "send-wizard";
	private static final String SEND_WIZARD_SUCCESS_TPL = "success";
	private final Log log = LogFactory.getLog(SendWizardController.class);

	@Autowired
	protected EmailTemplateService templateService;
	
	@Value("${web.apiClient.endpoint.sendMassive}")
	protected String apiEndpoint;
	

	@RequestMapping(value = { "/" }, method = RequestMethod.GET)
	public String sendWizard(Model model, @AuthenticationPrincipal UserAuthenticationInfo userInfo) {
		// FIXME Cambiar por consulta al servicio web mediante servicio local de plantillas (TemplatesController./)
		String customerId = userInfo.getUserInfo().getCustomerId();

		List<EmailTemplate> emailTemplates = templateService.getRepository().findAllByCustomerId(customerId);
		model.addAttribute("emailTemplates", emailTemplates);

		return SEND_WIZARD_HOME_TPL;
	}

	@RequestMapping(value = { "/send" }, method = RequestMethod.POST)
	public String send(
			@RequestParam("emailDataFile") MultipartFile emailDataFile, 
			@Valid SendWizardForm sendWizardForm,
			BindingResult bindingResult, 
			Model model,
			@AuthenticationPrincipal UserAuthenticationInfo authInfo) throws Exception 
	{

        if (!bindingResult.hasErrors()) {
	 		try{
	 			UserInfo userInfo = authInfo.getUserInfo();
				sendWizardForm.setCustomerId( userInfo.getCustomerId() );
				sendWizardForm.setSenderToken( userInfo.getSenderToken() );
				
	 			saveWithFile(sendWizardForm, emailDataFile);

	 			model.addAttribute("message", "Hemos recibido su solicitud");
				model.addAttribute("detail", "Los envíos se procesarán durante los próximos segundos");
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
        	throw new Exception("Errores en el formulario: " + errors.toString());
        }

		return SEND_WIZARD_SUCCESS_TPL;
	}

	private void save(SendWizardForm sendWizardForm) {
		EmailSendingRequest emailSending = new EmailSendingRequest();
		emailSending.setCustomerId(sendWizardForm.getCustomerId());
		emailSending.setCampaignId(sendWizardForm.getCampaignId());
		emailSending.setSubject(sendWizardForm.getSubject());
		emailSending.setFrom(sendWizardForm.getFromEmail()); // TODO Agregar nombre del remitente
		emailSending.setCustomerTemplateId(sendWizardForm.getTemplateId());
		emailSending.setCustomerId(sendWizardForm.getCustomerId());
		emailSending.setSenderToken(sendWizardForm.getSenderToken());
		
		// FIXME Sacar los desrtinatarios y variables de archivo Excel
		String to = "nelson.garcia@aldeamo.com";
		emailSending.setTo(Arrays.asList(new String[] { to }));

		Map<String, Object> data = new HashMap<>();
		data.put("nombreUsuario", "Maynard James Keenan");
		emailSending.setVariables(data);

		given().contentType(ContentType.JSON).body(emailSending).when()
				.post("http://localhost:8080/api/v0.1/email/send-single").then().log().all().and().statusCode(200)
				.contentType(ContentType.JSON)
				/*.body("sendingId", greaterThan(0)) */;
	}

	private void saveWithFile(SendWizardForm sendWizardForm, MultipartFile file) throws Exception {
		EmailSendingRequest emailSending = new EmailSendingRequest();
		emailSending.setCustomerId(sendWizardForm.getCustomerId());
		emailSending.setCampaignId(sendWizardForm.getCampaignId());
		emailSending.setSubject(sendWizardForm.getSubject());
		emailSending.setFrom(sendWizardForm.getFromEmail()); // TODO Agregar nombre del remitente
		emailSending.setCustomerTemplateId(sendWizardForm.getTemplateId());
		emailSending.setCustomerId(sendWizardForm.getCustomerId());
		emailSending.setSenderToken(sendWizardForm.getSenderToken());
		// FIXME Sacar los destinatarios y variables de archivo Excel
//		String to = "nelson.garcia@aldeamo.com";
//		emailSending.setTo(Arrays.asList(new String[] { to }));
//
//		Map<String, Object> data = new HashMap<>();
//		data.put("nombreUsuario", "Maynard James Keenan");
//		emailSending.setVariables(data);

		File upload = File.createTempFile("mail-sending-data-", ".xlsx");
		upload.deleteOnExit();
		file.transferTo(upload);

		HttpResponse<JsonNode> jsonResponse = Unirest
				.post(apiEndpoint)
				.header("accept", "application/json")
				.field("customerId", emailSending.getCustomerId())
				.field("campaignId", emailSending.getCampaignId())
				.field("subject", emailSending.getSubject())
				.field("from", emailSending.getFrom())
				.field("customerTemplateId", emailSending.getCustomerTemplateId())
				.field("senderToken", emailSending.getSenderToken())
				.field("emailDataFile", upload)
				.asJson();

		if (jsonResponse.getStatus() != 200) {
			String errorMsg = "Error contactando servicio de email: " + jsonResponse.getBody().toString();
			log.error(errorMsg);
			throw new Exception(errorMsg);
		}
	}
}
