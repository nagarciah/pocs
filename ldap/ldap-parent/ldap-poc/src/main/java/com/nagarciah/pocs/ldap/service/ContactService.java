package com.nagarciah.pocs.ldap.service;

//import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.nagarciah.pocs.ldap.dao.PersonRepository;
import com.nagarciah.pocs.ldap.dto.Contact;
import com.nagarciah.pocs.ldap.entry.InetOrgPerson;

@Service
public class ContactService {
	
	private final static Log log = LogFactory.getLog(ContactService.class); 

	@Autowired
	PersonRepository personRepository;
	
	@Autowired
	Mapper mapper;
	
	@Value("${ldap.defaultCharset : UTF-8")
	String defaultCharset = "UTF-8";

	// TODO Agregar cache a esta lista
	List<Contact> contacts = null;
	
	public List<Contact> getContact() {
		contacts = getLdapContact();
		return contacts;
	}

	public Contact save(Contact contact) {
		InetOrgPerson person = mapper.map(contact, InetOrgPerson.class);
		
		personRepository.save(person);
		mapper.map(person, contact);
		getContact(); // FIXME Actualizar cache
		
		return contact;
	}

	public void delete(String contactId) {
		Contact contact = new Contact();
		contact.setId(contactId);
		
		InetOrgPerson person = mapper.map(contact, InetOrgPerson.class);
		
		personRepository.delete(person.getDn());
		getContact(); // FIXME Actualizar cache
	}

	private List<Contact> getLdapContact() {
		Iterable<InetOrgPerson> ldapEntries = personRepository.findAll();
		List<Contact> contacts = new ArrayList<>();
		
		ldapEntries.forEach(e -> contacts.add(mapper.map(e, Contact.class)));
		
		return contacts;
	}
}
