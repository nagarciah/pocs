package com.aldeamo.poc.mailing.api.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.aldeamo.poc.mailing.model.ContactUnsubscribeReason;
import com.aldeamo.poc.mailing.util.Constants;

public class ContactUnsubscribeRequest {
	private String senderToken;
	@NotNull
	@Size(min=1)
	private String customerId;
	private String exclusionListId;
	
	@NotNull
	@Pattern(regexp=Constants.EMAIL_REGEX, message="No tiene el formato de una dirección de email válida")
	private String recipientEmail;
	
	@NotNull
	private ContactUnsubscribeReason reason; 
	private String description;
	
	public String getSenderToken() {
		return senderToken;
	}
	public void setSenderToken(String senderToken) {
		this.senderToken = senderToken;
	}
	public String getCustomerId() {
		return customerId;
	}
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	public String getExclusionListId() {
		return exclusionListId;
	}
	public void setExclusionListId(String exclusionListId) {
		this.exclusionListId = exclusionListId;
	}
	public ContactUnsubscribeReason getReason() {
		return reason;
	}
	public void setReason(ContactUnsubscribeReason reason) {
		this.reason = reason;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getRecipientEmail() {
		return recipientEmail;
	}
	public void setRecipientEmail(String recipientEmail) {
		this.recipientEmail = recipientEmail;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ContactUnsubscribeRequest [senderToken=");
		builder.append(senderToken);
		builder.append(", customerId=");
		builder.append(customerId);
		builder.append(", exclusionListId=");
		builder.append(exclusionListId);
		builder.append(", recipientEmail=");
		builder.append(recipientEmail);
		builder.append(", reason=");
		builder.append(reason);
		builder.append(", description=");
		builder.append(description);
		builder.append("]");
		return builder.toString();
	}
}
