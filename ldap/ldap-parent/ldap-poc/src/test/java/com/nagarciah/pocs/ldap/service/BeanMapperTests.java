package com.nagarciah.pocs.ldap.service;

import static org.assertj.core.api.Assertions.assertThat;

import javax.naming.InvalidNameException;
import javax.naming.ldap.LdapName;

import org.dozer.Mapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.nagarciah.pocs.ldap.dto.Contact;
import com.nagarciah.pocs.ldap.entry.InetOrgPerson;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class BeanMapperTests {

	@Autowired
	Mapper mapper;
	
	@Test
	public void contactToInetOrgPerson() throws InvalidNameException {
		//Mapper mapper = new DozerBeanMapper();

		//TODO incluir los demas campos
		String cn = "uid=nagarciah,ou=users,dc=example,dc=com";
		String name = "Nelson Garcia";

		Contact contact = new Contact();
		contact.setId(cn);
		contact.setName(name);
		
		InetOrgPerson person = mapper.map(contact, InetOrgPerson.class);
		
		assertThat(person)
			.hasFieldOrPropertyWithValue("displayName", name)
			.hasFieldOrPropertyWithValue("dn", new LdapName(cn));
	}

}
