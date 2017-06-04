package com.nagarciah.pocs.ldap.dao;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.ldap.query.LdapQueryBuilder.query;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Before;
import org.junit.Test;

import com.nagarciah.pocs.ldap.TestUtils;
import com.nagarciah.pocs.ldap.entry.InetOrgPerson;
import com.nagarciah.pocs.ldap.service.AppConfigurationManager;
import com.nagarciah.pocs.ldap.service.LdapTemplateManager;

public class PersonCompositeRepositoryTest {
	
	private String[] expectedServerKeys = TestUtils.expectedServerKeys;
	private PersonCompositeRepository personCompositeRepository;
	private Log log = LogFactory.getLog(PersonCompositeRepositoryTest.class);
	
	@Before
	public void setup(){
		AppConfigurationManager appConfigManager = TestUtils.createAppConfigManagerMock();
		LdapTemplateManager ldapTemplateManager = new LdapTemplateManager(appConfigManager);
		ldapTemplateManager.initialize();
		personCompositeRepository = new PersonCompositeRepository(ldapTemplateManager);
	}

	@Test
	public void testCompositeSearchWithMultipleResults() {
		List<InetOrgPerson> persons = personCompositeRepository.search(
				query().where("uid").is("pparker"));
		
		persons.forEach(s -> log.info(s));
		
		assertThat(persons)
			.hasSize(3)
			.haveExactly(3, TestUtils.isPeterParker)
			.extracting("ldapSourceKey")
				.hasSize(expectedServerKeys.length)
				.containsExactlyInAnyOrder(
						expectedServerKeys[0],
						expectedServerKeys[1], 
						expectedServerKeys[2]); // Esta en 3 nodos/servidores distintos
	}

}
