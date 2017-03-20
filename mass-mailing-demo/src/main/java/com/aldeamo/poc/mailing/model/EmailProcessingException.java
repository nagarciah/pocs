package com.aldeamo.poc.mailing.model;

public class EmailProcessingException extends RuntimeException {

	private static final long serialVersionUID = -647516300469906907L;

	public EmailProcessingException(String message) {
		super(message);
	}

	public EmailProcessingException() {
		super();
	}

	public EmailProcessingException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public EmailProcessingException(String message, Throwable cause) {
		super(message, cause);
	}

	public EmailProcessingException(Throwable cause) {
		super(cause);
	}
}
