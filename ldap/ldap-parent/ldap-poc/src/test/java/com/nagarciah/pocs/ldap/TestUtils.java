package com.nagarciah.pocs.ldap;

import java.util.ArrayList;
import java.util.List;

import org.assertj.core.api.Condition;
import org.mockito.Mockito;

import com.nagarciah.pocs.ldap.dto.LdapSourceConfig;
import com.nagarciah.pocs.ldap.entry.InetOrgPerson;
import com.nagarciah.pocs.ldap.service.AppConfigurationManager;

public class TestUtils {

	public static final String[] expectedServerKeys = new String[]{"ldap-server-1", "ldap-server-2", "ldap-server-3"};
	public static Condition<InetOrgPerson> isPeterParker = new Condition<InetOrgPerson>() {
		@Override
		public boolean matches(InetOrgPerson value) {
			return "Peter Benjamin Parker".equals(value.getDisplayName());
		}
	};
	public static AppConfigurationManager createAppConfigManagerMock(){
		AppConfigurationManager appConfigManager = Mockito.mock(AppConfigurationManager.class);
		List<LdapSourceConfig> ldapServerConfig = new ArrayList<>();
		ldapServerConfig.add(new LdapSourceConfig("ldap-server-1", "Primer servidor LDAP", "ldap://localhost:10389", "ou=users,dc=example,dc=com", "uid=admin,ou=system", "secret"));		
		ldapServerConfig.add(new LdapSourceConfig("ldap-server-2", "Segundo servidor LDAP", "ldap://localhost:10389", "ou=users,dc=example,dc=com", "uid=admin,ou=system", "secret"));
		ldapServerConfig.add(new LdapSourceConfig("ldap-server-3", "Tercer servidor LDAP", "ldap://localhost:10389", "ou=users,dc=example,dc=com", "uid=admin,ou=system", "secret"));
		
		Mockito.when(appConfigManager.loadLdapSourcesConfig()).thenReturn(ldapServerConfig);
	
		return appConfigManager;
	}
	
}