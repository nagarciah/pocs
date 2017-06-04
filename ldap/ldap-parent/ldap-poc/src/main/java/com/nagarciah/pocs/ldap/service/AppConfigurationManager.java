package com.nagarciah.pocs.ldap.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nagarciah.pocs.ldap.dao.LdapSourcesRepository;
import com.nagarciah.pocs.ldap.dto.LdapSourceConfig;

@Service
public class AppConfigurationManager {

	
	LdapSourcesRepository ldapSourcesRepository;
	
	@Autowired
	public AppConfigurationManager(LdapSourcesRepository ldapSourcesRepository) {
		super();
		this.ldapSourcesRepository = ldapSourcesRepository;
	}

	public List<LdapSourceConfig> loadLdapSourcesConfig() {
		return ldapSourcesRepository.findAll();
	}
}
