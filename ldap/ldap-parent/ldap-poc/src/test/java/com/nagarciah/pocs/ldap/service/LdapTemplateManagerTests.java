package com.nagarciah.pocs.ldap.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.ldap.query.LdapQueryBuilder.query;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.ldap.core.LdapTemplate;

import com.nagarciah.pocs.ldap.TestUtils;
import com.nagarciah.pocs.ldap.entry.InetOrgPerson;

public class LdapTemplateManagerTests {

	private Log log = LogFactory.getLog(LdapTemplateManagerTests.class);

	private String[] expectedServerKeys = TestUtils.expectedServerKeys;
	
	LdapTemplateManager ldapTemplateManager;
	AppConfigurationManager appConfigManager;

	
	@Before
	public void setup(){
		appConfigManager = TestUtils.createAppConfigManagerMock();
		ldapTemplateManager = new LdapTemplateManager(appConfigManager);
		ldapTemplateManager.initialize(); // Solo requerido por fuera de contexto Spring
	}
	
	@Test
	public void loadLdapTemplatesAtStartup(){
		Map<String, LdapTemplate> ldapTemplates = ldapTemplateManager.getAllTemplates();
		
		assertThat(ldapTemplates)
			.isNotNull()
			.isNotEmpty()
			.hasSize(3)
			.doesNotContainValue(null);
	}
	
	@Test
	public void getLdapTemplateForKey(){
		LdapTemplate ldapTemplate = ldapTemplateManager.getTemplateByKey(expectedServerKeys[0]);
		
		//LdapTestUtils.findAndAssertPeterParker(ldapTemplate)
		InetOrgPerson person = ldapTemplate.findOne(query().where("uid").is("pparker"), InetOrgPerson.class);
		
		assertThat(person)
			.isNotNull()
			.hasFieldOrPropertyWithValue("displayName", "Peter Benjamin Parker");
	}
	
	@Test(expected=LdapTemplateNotFoundException.class)
	public void missingTemplateForKey(){
		ldapTemplateManager.getTemplateByKey("this-key-is-missing");		
	}
	
	@Test(expected=LdapTemplateManagerException.class)
	public void managerNotInititializedBeforeGetTemplateByKey(){
		new LdapTemplateManager(appConfigManager).getTemplateByKey(expectedServerKeys[0]);		
	}

	@Test(expected=LdapTemplateManagerException.class)
	public void managerNotInititializedBeforeGetAllTemplates(){
		new LdapTemplateManager(appConfigManager).getAllTemplates();		
	}
	
	@Test
	@Ignore("Corregir para que cuando el metodo tenga parametros, se cree un arreglo o lista con instancias de los tipos apropiados")
	public void managerNotInititializedBeforeAnyPublicMethodInvocation() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException{
		LdapTemplateManager ldapTemplateManager = new LdapTemplateManager(appConfigManager);
			Method[] publicMethods = ldapTemplateManager.getClass().getMethods();
			for(Method m : publicMethods){
				if(!m.isAnnotationPresent(PostConstruct.class)){
					try{
						if(m.getParameterCount()>0){
							m.invoke(ldapTemplateManager, (Object) new Object[]{});
						}else{
							m.invoke(ldapTemplateManager);
						}
						
						throw new RuntimeException("NO se lanzo "+ LdapTemplateManagerException.class.getName() +" para el metodo" + m.getName());
					}catch(InvocationTargetException ite){
						if(ite.getCause() instanceof LdapTemplateManagerException){
							log .debug(m.getName() + ": OK - Lanzo excepcion por falta de inicializacion");
						}else{
							throw ite;
						}
					}
				}
			}
			
	}
}
