package com.aldeamo.poc.mailing.api.dto;

public class GenericAPIResponse {
	
	public enum GenericStatus {
		SUCCESS,
		PROCESSING_ERROR,
	}

	private Long entityId;
	private GenericStatus status;
	private String message;
	
	
	public GenericAPIResponse(Long entityId, GenericStatus status) {
		super();
		this.entityId = entityId;
		this.status = status;
	}
	
	public GenericAPIResponse(Long entityId, GenericStatus status, String message) {
		this(entityId, status);
		this.message = message;
	}

	public Long getEntityId() {
		return entityId;
	}
	public void setEntityId(Long entityId) {
		this.entityId = entityId;
	}
	public GenericStatus getStatus() {
		return status;
	}
	public void setStatus(GenericStatus status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}	
}
