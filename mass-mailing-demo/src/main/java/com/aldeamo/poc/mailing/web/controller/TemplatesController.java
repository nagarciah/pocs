package com.aldeamo.poc.mailing.web.controller;

import java.io.File;
import java.util.List;

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

import com.aldeamo.poc.mailing.api.dto.EmailTemplateRequest;
import com.aldeamo.poc.mailing.model.EmailTemplate;
import com.aldeamo.poc.mailing.model.EmailTemplateService;
import com.aldeamo.poc.mailing.web.dto.UploadTemplateForm;
import com.aldeamo.poc.mailing.web.dto.UserAuthenticationInfo;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;


@Controller
@RequestMapping("/templates")
public class TemplatesController {
	
	private static final String TEMPLATES_HOME_TPL = "template-upload";
	private static final String TEMPLATES_SUCCESS_TPL = "success";
	private final Log log = LogFactory.getLog(TemplatesController.class); 

	@Autowired
	protected EmailTemplateService templateService;
	
	@Value("${web.apiClient.endpoint.template}")
	protected String apiEndpoint;
	
	
	@RequestMapping(value = { "/" }, method = RequestMethod.GET)
	public String home(
			Model model,
			@AuthenticationPrincipal UserAuthenticationInfo authInfo) 
	{
		// FIXME Cambiar por consulta al servicio web mediante servicio local de plantillas (SendWizardController./)
		String customerId = authInfo.getUserInfo().getCustomerId();
		List<EmailTemplate> emailTemplates = templateService.getRepository().findAllByCustomerId(customerId);
		model.addAttribute("emailTemplates", emailTemplates);

		return TEMPLATES_HOME_TPL;
	}
	
	@RequestMapping(value = { "/" }, method = RequestMethod.POST)
	public String upload(
			@RequestParam("templateZipFile") MultipartFile templateZipFile, 
			@Valid UploadTemplateForm uploadTemplateForm,
			BindingResult bindingResult, 
			Model model,
			@AuthenticationPrincipal UserAuthenticationInfo authInfo) throws Exception {

        if (!bindingResult.hasErrors()) {
	 		try{
	 			uploadTemplateForm.setCustomerId( authInfo.getUserInfo().getCustomerId() );
	 			uploadTemplateForm.setSenderToken( authInfo.getUserInfo().getSenderToken() );
	 			
	 			saveWithFile(uploadTemplateForm, templateZipFile);
				
	 			model.addAttribute("message", "Hemos recibido su plantilla");
				model.addAttribute("detail", "Ya puede usarla para sus envíos");
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
	

	
	private void saveWithFile(UploadTemplateForm uploadTemplateForm, MultipartFile file) throws Exception {
		EmailTemplateRequest templateRequest = new EmailTemplateRequest();
		templateRequest.setCategory( uploadTemplateForm.getCategory() );
		templateRequest.setCssSubpath( uploadTemplateForm.getCssSubpath() );
		templateRequest.setCustomerTemplateId( uploadTemplateForm.getCustomerTemplateId() );
		templateRequest.setDescription( uploadTemplateForm.getDescription() );
		templateRequest.setFriendlyName( uploadTemplateForm.getFriendlyName() );
		templateRequest.setHtmlFilename( uploadTemplateForm.getHtmlFilename() );
		templateRequest.setImagesSubpath( uploadTemplateForm.getImagesSubpath() );
		templateRequest.setTxtFilename( uploadTemplateForm.getTxtFilename() );
		templateRequest.setCustomerId( uploadTemplateForm.getCustomerId() );
		templateRequest.setSenderToken( uploadTemplateForm.getSenderToken() );

		File upload = File.createTempFile("mail-template-", ".zip");
		upload.deleteOnExit();
		file.transferTo(upload);

		// Permitir campos nulos
		HttpResponse<JsonNode> jsonResponse = Unirest.post(apiEndpoint)
				  .header("accept", "application/json")
				  .field("category", templateRequest.getCategory())
				  //.field("cssSubpath", templateRequest.getCssSubpath())
				  .field("customerId", templateRequest.getCustomerId())
				  .field("customerTemplateId", templateRequest.getCustomerTemplateId())
				  .field("description", templateRequest.getDescription())
				  .field("friendlyName", templateRequest.getFriendlyName())
				  .field("htmlFilename", templateRequest.getHtmlFilename())
				  //.field("imagesSubpath", templateRequest.getImagesSubpath())
				  //.field("txtFilename", templateRequest.getTxtFilename())
				  .field("senderToken", templateRequest.getSenderToken())
				  .field("templateZipFile", upload)
				  .asJson();
		
		if(jsonResponse.getStatus()!=200){
			String errorMsg = "Error contactando servicio de plantillas: " + jsonResponse.getBody().toString();
			log.error(errorMsg);
			throw new Exception(errorMsg);
		}
	}
}
