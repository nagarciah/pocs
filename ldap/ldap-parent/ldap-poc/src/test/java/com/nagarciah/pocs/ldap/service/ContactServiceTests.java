package com.nagarciah.pocs.ldap.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.assertj.core.api.Condition;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.nagarciah.pocs.ldap.dto.Contact;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ContactServiceTests {

	@Autowired
	private ContactService contactService;

	@Test
	public void getAllContacts() {
		List<Contact> allContacts = contactService.getContact();

		assertThat(allContacts)
			.isNotNull()
			.isNotEmpty()
			.hasSize(12)
			.hasOnlyElementsOfType(Contact.class)
			.haveExactly(1, new Condition<Contact>(){
				@Override
				public boolean matches(Contact value) {
					return "Peter Benjamin Parker".equals(value.getName());
				}
			});
	}
}
