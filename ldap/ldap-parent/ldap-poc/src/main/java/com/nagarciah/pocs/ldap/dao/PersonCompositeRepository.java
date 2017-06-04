package com.nagarciah.pocs.ldap.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.naming.Name;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ldap.core.ContextMapper;
import org.springframework.ldap.core.DirContextAdapter;
import org.springframework.ldap.core.DirContextOperations;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.core.support.AbstractContextMapper;
import org.springframework.ldap.query.LdapQuery;
import org.springframework.ldap.support.LdapNameBuilder;
import org.springframework.stereotype.Service;

import com.nagarciah.pocs.ldap.entry.InetOrgPerson;
import com.nagarciah.pocs.ldap.service.LdapTemplateManager;

// FIXME Convertir en composite
/**
 * 
 * @author nelson
 * @see http://docs.spring.io/spring-ldap/docs/current/reference/#a-complete-personrepository-class
 */
@Service
public class PersonCompositeRepository {

	private LdapTemplateManager ldapTemplateManager;

	@Autowired
	public PersonCompositeRepository(LdapTemplateManager ldapTemplateManager) {
		super();
		this.ldapTemplateManager = ldapTemplateManager;
	}

	public List<InetOrgPerson> search(LdapQuery query) {
		List<InetOrgPerson> persons = new ArrayList<>();
		Map<String, LdapTemplate> templates = ldapTemplateManager.getAllTemplates();

		// FIXME Hacer esta busqueda en paralelo, cache, cola o todos para
		// mejorar rendimiento y evitar problemas de concurrencia
		templates.forEach((key, template) -> {
			List<InetOrgPerson> results = template.search(query, getDefaultMapper());
			results.forEach(r -> r.setLdapSourceKey(key));
			persons.addAll(results);
		});

		return persons;
	}

	public void create(InetOrgPerson person) {
		LdapTemplate ldapTemplate = getLdapTemplate(person.getLdapSourceKey());
		
		DirContextAdapter context = new DirContextAdapter(buildDn(person));
		mapToContext(person, context);
		ldapTemplate.bind(context);
	}
	
	public void update(InetOrgPerson person) {
		LdapTemplate ldapTemplate = getLdapTemplate(person.getLdapSourceKey());
		
		Name dn = buildDn(person);
		DirContextOperations context = ldapTemplate.lookupContext(dn);
		mapToContext(person, context);
		ldapTemplate.modifyAttributes(context);
	}

	public void delete(InetOrgPerson person) {
		LdapTemplate ldapTemplate = getLdapTemplate(person.getLdapSourceKey());
		ldapTemplate.unbind(buildDn(person));
	}

	
	private LdapTemplate getLdapTemplate(String ldapSourceKey) {
		return ldapTemplateManager.getTemplateByKey(ldapSourceKey);
	}

	protected ContextMapper<InetOrgPerson> getDefaultMapper() {
		return new PersonContextMapper();
	}

	protected void mapToContext(InetOrgPerson person, DirContextOperations context) {
		// FIXME Dejar externalizable
		context.setAttributeValues("objectclass", new String[] { "top", "person", "organizationalPerson", "inetOrgPerson" });
		context.setAttributeValue("cn", person.getCn());
		context.setAttributeValue("sn", person.getSn());
		context.setAttributeValue("description", person.getDisplayName());
	}

	protected Name buildDn(InetOrgPerson p) {
		// FIXME Dejar esto externalizable
		// uid=nromanov,ou=users,dc=example,dc=com
		return LdapNameBuilder.newInstance()
				.add("uid", p.getUid())
				.add("ou", "users")
				.add("dc", "example")
				.add("dc", "com").build();
	}
	
	/**
	 * 
	 * @author nelson
	 * @see http://docs.spring.io/spring-ldap/docs/current/reference/#search-and-lookup-using-contextmapper
	 */
	private static class PersonContextMapper extends AbstractContextMapper<InetOrgPerson> {

		// TODO Como utilizar Dozer aqui u otra forma para externalizar el
		// mapping de atributtos
		@Override
		protected InetOrgPerson doMapFromContext(DirContextOperations ctx) {
			InetOrgPerson person = new InetOrgPerson();
			person.setDisplayName(ctx.getStringAttribute("displayName"));
			// person.setDn(attr.get("dn").get().toString()); TODO Como?
			person.setUid(ctx.getStringAttribute("uid"));

			return person;
		}
	}
}
