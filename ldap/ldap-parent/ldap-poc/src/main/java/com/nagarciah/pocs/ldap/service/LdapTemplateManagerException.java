package com.nagarciah.pocs.ldap.service;

public class LdapTemplateManagerException extends RuntimeException {

	private static final long serialVersionUID = -3136939568824260488L;

	public LdapTemplateManagerException() {
		super();
	}

	public LdapTemplateManagerException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public LdapTemplateManagerException(String message, Throwable cause) {
		super(message, cause);
	}

	public LdapTemplateManagerException(String message) {
		super(message);
	}

	public LdapTemplateManagerException(Throwable cause) {
		super(cause);
	}

}
