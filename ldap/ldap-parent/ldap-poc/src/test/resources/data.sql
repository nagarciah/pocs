insert into ldap_source_config(
	ldap_key,
	description,
	urls,
	base,
	ldap_username,
	ldap_password
) 
values(
	'ldap-server-1', 
	'Primer servidor LDAP', 
	'ldap://localhost:10389', 
	'ou=users,dc=example,dc=com', 
	'uid=admin,ou=system', 
	'secret'
);

insert into ldap_source_config(
	ldap_key,
	description,
	urls,
	base,
	ldap_username,
	ldap_password
) 
values(
	'ldap-server-2', 
	'Segundo servidor LDAP', 
	'ldap://localhost:10389', 
	'ou=users,dc=example,dc=com', 
	'uid=admin,ou=system', 
	'secret'
);

insert into ldap_source_config(
	ldap_key,
	description,
	urls,
	base,
	ldap_username,
	ldap_password
) 
values(
	'ldap-server-3', 
	'Tercer servidor LDAP', 
	'ldap://localhost:10389', 
	'ou=users,dc=example,dc=com', 
	'uid=admin,ou=system', 
	'secret'
);
