package com.aldeamo.poc.mailing.api;

import javax.validation.Valid;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.aldeamo.poc.mailing.api.dto.ContactUnsubscribeRequest;
import com.aldeamo.poc.mailing.api.dto.GenericAPIResponse;
import com.aldeamo.poc.mailing.api.dto.GenericAPIResponse.GenericStatus;
import com.aldeamo.poc.mailing.model.ContactsService;
import com.aldeamo.poc.mailing.model.ExclusionListEntry;

@RestController
@RequestMapping(ApiConstants.API_MAPPING + "/exclusion-list")
public class ContactsAPI {
	public static final Log log = LogFactory.getLog(ContactsAPI.class);
	
	private ContactsService contactsService;
	
	@Autowired
	public ContactsAPI(ContactsService contactsService) {
		super();
		this.contactsService = contactsService;
	}
	
	@RequestMapping(value = "/", method = RequestMethod.PUT, consumes=MediaType.APPLICATION_JSON_VALUE)
	public GenericAPIResponse addContactToExclusionList(@Valid @RequestBody ContactUnsubscribeRequest unsubscribeRequest)
	{
		try{
			ExclusionListEntry listEntry = contactsService.addToExclusionList(unsubscribeRequest);
			return new GenericAPIResponse(listEntry.getId(), GenericStatus.SUCCESS); 
		} catch (Exception e) {
			// TODO Agregar más información para poder rastrear el error y
			// agilizar el diagnóstico cuando sea reportado por el cliente
			log.error(e);
			// return new EmailSendingResponse(null,
			// EmailSendingStatus.PROCESSING_ERROR);
			throw e;
		}
	}
}
