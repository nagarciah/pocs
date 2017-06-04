package com.nagarciah.pocs.ldap.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nagarciah.pocs.ldap.dto.LdapSourceConfig;

public interface LdapSourcesRepository extends JpaRepository<LdapSourceConfig, String> {

}
