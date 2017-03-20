package com.aldeamo.poc.mailing.web.service;

public class UserInfoNotFoundException extends RuntimeException {
	private static final long serialVersionUID = 7116345632473495637L;

	public UserInfoNotFoundException() {
		super();
	}

	public UserInfoNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public UserInfoNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	public UserInfoNotFoundException(String message) {
		super(message);
	}

	public UserInfoNotFoundException(Throwable message) {
		super(message);
	}
}
