package com.aldeamo.poc.mailing.web.dto;

public class SendWizardForm {
	private String customerId;
	private String campaignId;
	private String subject;
	private String fromEmail;
	private String fromName;
	private String replyToEmail;
	private String templateId;
	private String varNamesInFirstRow;
	private String senderToken;

	public String getCustomerId() {
		return customerId;
	}
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	public String getCampaignId() {
		return campaignId;
	}
	public void setCampaignId(String campaignId) {
		this.campaignId = campaignId;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getFromEmail() {
		return fromEmail;
	}
	public void setFromEmail(String fromEmail) {
		this.fromEmail = fromEmail;
	}
	public String getFromName() {
		return fromName;
	}
	public void setFromName(String fromName) {
		this.fromName = fromName;
	}
	public String getReplyToEmail() {
		return replyToEmail;
	}
	public void setReplyToEmail(String replyToEmail) {
		this.replyToEmail = replyToEmail;
	}
	public String getTemplateId() {
		return templateId;
	}
	public void setTemplateId(String templateId) {
		this.templateId = templateId;
	}
	public String getVarNamesInFirstRow() {
		return varNamesInFirstRow;
	}
	public void setVarNamesInFirstRow(String varNamesInFirstRow) {
		this.varNamesInFirstRow = varNamesInFirstRow;
	}
	public String getSenderToken() {
		return senderToken;
	}
	public void setSenderToken(String senderToken) {
		this.senderToken = senderToken;
	}
}
