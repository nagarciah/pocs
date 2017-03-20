package com.aldeamo.poc.mailing.model;

public class TemplateProcessingException extends RuntimeException {

	private static final long serialVersionUID = 2371763962533910404L;

	public TemplateProcessingException(String message) {
		super(message);
	}

	public TemplateProcessingException() {
		super();
	}

	public TemplateProcessingException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public TemplateProcessingException(String message, Throwable cause) {
		super(message, cause);
	}

	public TemplateProcessingException(Throwable cause) {
		super(cause);
	}
}
