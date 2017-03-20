package com.nagarciah.trainning.statemachine.service;

public class NodeFactoryException extends RuntimeException {

	private static final long serialVersionUID = 8189318100459161397L;

	public NodeFactoryException() {
		super();
	}

	public NodeFactoryException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public NodeFactoryException(Throwable cause) {
		super(cause);
	}

	public NodeFactoryException(String message, Throwable cause) {
		super(message, cause);
	}

	public NodeFactoryException(String message) {
		super(message);
	}
}