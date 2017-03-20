package com.aldeamo.poc.mailing.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
public class EmailSendingRequest implements Serializable {

	private static final long serialVersionUID = -7075268529794420730L;

	@Id
	@GeneratedValue
	private Long id;
	
	@NotNull
	@Size(min=1)
	private String customerId;
	
	@NotNull
	@Size(min=1)
	private String campaignId;
	
	@NotNull
	@Size(min=1)
	private String subject;
	
	@NotNull
	@Size(min=1)
	@Column(name="from_email")
	private String from;
	
	private String replyTo;

	// FIXME Guardar los campos Transient
	@Transient
	@JsonIgnore
	private List<String> to;
	
	@Transient
	@JsonIgnore
	private List<String> cc;
	
	@Transient
	@JsonIgnore
	private List<String> bcc;
	
	@NotNull
	@Size(min=1)
	private String customerTemplateId;
	
	private String customerExclusionListId;


	@NotNull
	@Size(min=1)
	private String senderToken;
	
	@Transient
	private Map<String, Object> variables;
	
	//TODO Implementar la capacidad de recibir adjuntos
	@Transient
	private Map<String, String> attachments;
	
	// TODO implementar como mapa Options?
	private int batchSize;
	private long batchDelay;
	private LocalDateTime schedule;
	private String emailDataFile;

	
	
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
	public String getFrom() {
		return from;
	}
	public void setFrom(String from) {
		this.from = from;
	}
	public String getReplyTo() {
		return replyTo;
	}
	public void setReplyTo(String replyTo) {
		this.replyTo = replyTo;
	}
	public List<String> getTo() {
		return to;
	}
	public void setTo(List<String> to) {
		this.to = to;
	}
	public List<String> getCc() {
		return cc;
	}
	public void setCc(List<String> cc) {
		this.cc = cc;
	}
	public List<String> getBcc() {
		return bcc;
	}
	public void setBcc(List<String> bcc) {
		this.bcc = bcc;
	}
	public String getCustomerTemplateId() {
		return customerTemplateId;
	}
	public void setCustomerTemplateId(String customerTemplateId) {
		this.customerTemplateId = customerTemplateId;
	}
	public Map<String, Object> getVariables() {
		return variables;
	}
	public void setVariables(Map<String, Object> variables) {
		this.variables = variables;
	}
	public Map<String, String> getAttachments() {
		return attachments;
	}
	public void setAttachments(Map<String, String> attachments) {
		this.attachments = attachments;
	}
	public int getBatchSize() {
		return batchSize;
	}
	public void setBatchSize(int batchSize) {
		this.batchSize = batchSize;
	}
	public long getBatchDelay() {
		return batchDelay;
	}
	public void setBatchDelay(long batchDelay) {
		this.batchDelay = batchDelay;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public LocalDateTime getSchedule() {
		return schedule;
	}
	public void setSchedule(LocalDateTime schedule) {
		this.schedule = schedule;
	}
	public String getSenderToken() {
		return senderToken;
	}
	public void setSenderToken(String senderToken) {
		this.senderToken = senderToken;
	}
	public String getEmailDataFile() {
		return emailDataFile;
	}
	public void setEmailDataFile(String emailDataFile) {
		this.emailDataFile = emailDataFile;
	}
	public String getCustomerExclusionListId() {
		return customerExclusionListId;
	}
	public void setCustomerExclusionListId(String customerExclusionListId) {
		this.customerExclusionListId = customerExclusionListId;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("EmailSendingRequest [id=");
		builder.append(id);
		builder.append(", customerId=");
		builder.append(customerId);
		builder.append(", campaignId=");
		builder.append(campaignId);
		builder.append(", subject=");
		builder.append(subject);
		builder.append(", from=");
		builder.append(from);
		builder.append(", replyTo=");
		builder.append(replyTo);
		builder.append(", to=");
		builder.append(to);
		builder.append(", cc=");
		builder.append(cc);
		builder.append(", bcc=");
		builder.append(bcc);
		builder.append(", customerTemplateId=");
		builder.append(customerTemplateId);
		builder.append(", customerExclusionListId=");
		builder.append(customerExclusionListId);
		builder.append(", senderToken=");
		builder.append(senderToken);
		builder.append(", variables=");
		builder.append(variables);
		builder.append(", attachments=");
		builder.append(attachments);
		builder.append(", batchSize=");
		builder.append(batchSize);
		builder.append(", batchDelay=");
		builder.append(batchDelay);
		builder.append(", schedule=");
		builder.append(schedule);
		builder.append(", emailDataFile=");
		builder.append(emailDataFile);
		builder.append("]");
		return builder.toString();
	}
}
