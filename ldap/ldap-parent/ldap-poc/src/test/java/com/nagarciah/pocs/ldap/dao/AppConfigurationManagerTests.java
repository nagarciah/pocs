package com.nagarciah.pocs.ldap.dao;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringRunner;

import com.nagarciah.pocs.ldap.TestUtils;
import com.nagarciah.pocs.ldap.dto.LdapSourceConfig;
import com.nagarciah.pocs.ldap.service.AppConfigurationManager;

@RunWith(SpringRunner.class)
@SpringBootTest
@ComponentScan(basePackages="com.nagarciah.pocs.ldap")
public class AppConfigurationManagerTests {

	private String[] expectedServerKeys = TestUtils.expectedServerKeys;

	@Autowired
	AppConfigurationManager configManager;
	
	@Test
	public void testLoadLdapSourcesConfig() {
		List<LdapSourceConfig> config = configManager.loadLdapSourcesConfig();
		
		//config.forEach(s -> log.info(s));
		
		assertThat(config)
			.hasSize(3)
			.extracting("key")
				.hasSize(expectedServerKeys.length)
				.containsExactlyInAnyOrder(
						expectedServerKeys[0],
						expectedServerKeys[1], 
						expectedServerKeys[2]);
	}
}
