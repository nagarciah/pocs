package com.nagarciah.pocs.ldap.conf;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ldap.core.support.LdapContextSource;
import org.springframework.ldap.test.unboundid.LdapTestUtils;
import org.springframework.ldap.test.unboundid.TestContextSourceFactoryBean;

import com.unboundid.ldap.listener.InMemoryDirectoryServerConfig;
import com.unboundid.ldap.listener.InMemoryDirectoryServerTool;

@Configuration
@Profile("test")
public class MultiLdapTestConfig {

	@Bean
	public String /*TestContextSourceFactoryBean*/ createServer(){
		/*LdapContextSource contextSource = new LdapContextSource();
	    contextSource.setUrl("ldap://127.0.0.1:" + 10888);
	    contextSource.setUserDn("");
	    contextSource.setPassword("");
	    contextSource.setPooled(false);
	    contextSource.afterPropertiesSet();
	    
		LdapTestUtils.startEmbeddedServer(10888, "com=test", new ClassPathResource("users.ldif"));*/
		/*TestContextSourceFactoryBean ctxFactory = new TestContextSourceFactoryBean();
		ctxFactory.setDefaultPartitionSuffix ("dc=jayway,dc=se");
	    ctxFactory.setDefaultPartitionName ("jayway");
	    ctxFactory.setPrincipal ("uid=admin,ou=system");
	    ctxFactory.setPassword ("secret");
	    ctxFactory.setLdifFile (new ClassPathResource("/setup_data.ldif"));
	    ctxFactory.setPort (1888);*/
	  
	    LdapTestUtils.startEmbeddedServer(1889, "o=osi,ou=users", "");
	    new InMemoryDirectoryServerConfig(/*baseDNs*/)
	    //return ctxFactory;
	    return "";
	}
}
