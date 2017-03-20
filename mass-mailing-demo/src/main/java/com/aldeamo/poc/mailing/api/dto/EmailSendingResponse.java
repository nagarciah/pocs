package com.aldeamo.poc.mailing.api.dto;

public class EmailSendingResponse {
	
	public enum EmailSendingStatus {
		RECEIVED,
		PROCESSING_ERROR,
	}

	private Long sendingId;
	private EmailSendingStatus status;

	public EmailSendingResponse(Long sendingId, EmailSendingStatus status) {
		super();
		this.sendingId = sendingId;
		this.status = status;
	}

	public EmailSendingStatus getStatus() {
		return status;
	}

	public void setStatus(EmailSendingStatus status) {
		this.status = status;
	}

	public Long getSendingId() {
		return sendingId;
	}

	public void setSendingId(Long sendingId) {
		this.sendingId = sendingId;
	}
}
