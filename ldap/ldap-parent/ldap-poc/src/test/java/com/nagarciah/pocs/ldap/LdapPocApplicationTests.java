package com.nagarciah.pocs.ldap;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.ldap.query.LdapQueryBuilder.query;

import java.util.List;

import javax.naming.NamingException;
import javax.naming.directory.Attributes;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.assertj.core.api.Condition;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.ldap.core.AttributesMapper;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import com.nagarciah.pocs.ldap.dao.PersonRepository;
import com.nagarciah.pocs.ldap.entry.InetOrgPerson;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LdapPocApplicationTests {
	
	private Log log = LogFactory.getLog(LdapPocApplicationTests.class);
	
	public static final String DOMAIN_DSN = "dc=example,dc=com";

	@Autowired
	LdapTemplate ldapTemplate;
	
	@Autowired
	PersonRepository personRepository;
	
	Condition<InetOrgPerson> isPeterParker = new Condition<InetOrgPerson>() {
		@Override
		public boolean matches(InetOrgPerson value) {
			return "Peter Benjamin Parker".equals(value.getDisplayName());
		}
	};

	@Test
	public void contextLoads() {
	}

	@Test
	public void testSimpleQuery(){
		List<InetOrgPerson> persons = ldapTemplate.search(
				query().where("objectClass").is("person"), 
				new AttributesMapper<InetOrgPerson>(){
					@Override
					public InetOrgPerson mapFromAttributes(Attributes attr) throws NamingException {
						InetOrgPerson person = new InetOrgPerson();
						person.setDisplayName(attr.get("displayName").get().toString());
//						person.setDn(attr.get("dn").get().toString()); TODO Como?
						person.setUid(attr.get("uid").get().toString());
						
						return person;
					}
				});
		
		persons.forEach(s -> log.info(s));
		
		assertThat(persons)
			.hasSize(12)
			.haveExactly(1, isPeterParker);
	}
	
	@Test
	public void testOdmQuery(){
		InetOrgPerson person = ldapTemplate.findOne(query().where("uid").is("pparker"), InetOrgPerson.class);
		
		assertThat(person)
			.isNotNull()
			.hasFieldOrPropertyWithValue("displayName", "Peter Benjamin Parker");
		
		log.info(person);
	}
	
	@Test
	public void testLdapRepositoryQuery(){
		InetOrgPerson person = personRepository.findOneByUid("pparker");
		
		assertThat(person)
			.isNotNull()
			.hasFieldOrPropertyWithValue("displayName", "Peter Benjamin Parker");
		
		log.info(person);
	}
}
