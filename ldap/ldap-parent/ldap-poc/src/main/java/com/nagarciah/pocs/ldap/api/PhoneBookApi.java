package com.nagarciah.pocs.ldap.api;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.nagarciah.pocs.ldap.dto.Contact;
import com.nagarciah.pocs.ldap.service.ContactService;

@RestController
public class PhoneBookApi {
	
	public final static String BASE_URL = "/contact";

	@Autowired
	private ContactService contactService;

	@RequestMapping(path=BASE_URL, method=RequestMethod.GET)
	public List<Contact> getContacts(){
		tryError();
		return contactService.getContact();
	}
	
	@RequestMapping(path=BASE_URL, method=RequestMethod.POST)
	public Contact saveContact(@RequestBody @Valid Contact contact){
		tryError();
		return contactService.save(contact);
	}
	
	@RequestMapping(path=BASE_URL + "/{contactId}", method=RequestMethod.DELETE)
	public void deleteContact(@PathVariable String contactId){
		tryError();
		contactService.delete(contactId);
	}

	private void tryError(){
		double errorRate = 0.1;
		if(Math.random()<errorRate){
			//throw new RuntimeException("Error aleatorio generado");
		}
	}
}
