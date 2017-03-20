package com.aldeamo.poc.mailing.api;

import java.util.List;

import javax.validation.Valid;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.aldeamo.poc.mailing.api.dto.EmailTemplateRequest;
import com.aldeamo.poc.mailing.model.EmailTemplate;
import com.aldeamo.poc.mailing.model.EmailTemplateService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(ApiConstants.API_MAPPING + "/template")
public class TemplateAPI {
	
	private final Log log = LogFactory.getLog(TemplateAPI.class);
	
	@Autowired
	EmailTemplateService templateService;

	
	@RequestMapping(value = "/template/{customerId}", method = RequestMethod.GET)
	public List<EmailTemplate> getTemplates(@Valid @PathVariable(required = true) String customerId) throws Exception {

		try {
			return templateService.getRepository().findAllByCustomerId(customerId);
		} catch (Exception e) {
			// TODO Agregar más información para poder rastrear el error y
			// agilizar el diagnóstico cuando sea reportado por el cliente
			log.error(e);
			throw e;
		}
	}
	
	@ApiOperation(value = "Cargar una plantilla nueva o reemplazar una existente", 
			notes = "El templateId que se retorna, debe ser almacenado por la aplicación cliente si está "
				+ "interesado en realizar operaciones posteriormente sobre la plantilla.")
	@RequestMapping(
			value = { "/" }, 
			method = RequestMethod.POST, 
			consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public EmailTemplateRequest sendMultipleEmail(
			@RequestParam(value = "templateZipFile", required = true) MultipartFile templateZipFile,
			@Valid EmailTemplateRequest templateRequest,
			BindingResult bindingResult) throws Exception 
	{
		try {
			EmailTemplate template = new EmailTemplate();
			template.setCategory(templateRequest.getCategory());
			template.setCssSubpath(templateRequest.getCssSubpath());
			template.setCustomerId(templateRequest.getCustomerId());
			template.setCustomerTemplateId(templateRequest.getCustomerTemplateId());
			template.setDescription(templateRequest.getDescription());
			template.setFriendlyName(templateRequest.getFriendlyName());
			template.setHtmlFilename(templateRequest.getHtmlFilename());
			template.setId(templateRequest.getId());
			template.setImagesSubpath(templateRequest.getImagesSubpath());
			template.setTxtFilename(templateRequest.getTxtFilename());
			
			Long sendingId = templateService.save(template, templateZipFile);
			templateRequest.setId(sendingId);
			
			return templateRequest;
		} catch (Exception e) {
			// TODO Agregar más información para poder rastrear el error y
			// agilizar el diagnóstico cuando sea reportado por el cliente
			log.error(e);
			throw e;
		}
	}
}
