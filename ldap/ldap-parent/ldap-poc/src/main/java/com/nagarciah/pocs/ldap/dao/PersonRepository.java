package com.nagarciah.pocs.ldap.dao;

import org.springframework.data.ldap.repository.LdapRepository;

import com.nagarciah.pocs.ldap.entry.InetOrgPerson;

public interface PersonRepository extends LdapRepository<InetOrgPerson> {

	public InetOrgPerson findOneByUid(String uid);
}
