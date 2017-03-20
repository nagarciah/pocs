package com.aldeamo.poc.mailing.model;

import java.io.File;
import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EmailMessage {

	private String from;
	private List<String> to;
	private List<String> cc;
	private List<String> bcc;
	private Map<String, Object> variables;
	private Map<String, URI> attachments;
	private Map<String, File> inlineFiles;
	private String replyTo;
	private String templateHtml;
	private String templateText;
	private String subject;
	private String htmlContent;
	private String plainTextContent;
	private AuthenticationInfo authInfo;

	
	public String getFrom() {
		return from;
	}
	public void setFrom(String from) {
		this.from = from;
	}
	public List<String> getTo() {
		if(this.to == null){
			this.to = new ArrayList<>();
		}
		
		return to;
	}
	public void setTo(List<String> to) {
		this.to = to;
	}
	public List<String> getCc() {
		if(this.cc == null){
			this.cc = new ArrayList<>();
		}
		
		return cc;
	}
	public void setCc(List<String> cc) {
		this.cc = cc;
	}
	public List<String> getBcc() {
		if(this.bcc == null){
			this.bcc = new ArrayList<>();
		}
		
		return bcc;
	}
	public void setBcc(List<String> bcc) {
		this.bcc = bcc;
	}
	public Map<String, Object> getVariables() {
		if(this.variables == null){
			this.variables = new HashMap<>();
		}
		return variables;
	}
	public void setVariables(Map<String, Object> varibles) {
		this.variables = varibles;
	}
	public String getReplyTo() {
		return replyTo;
	}
	public void setReplyTo(String replyTo) {
		this.replyTo = replyTo;
	}
	public String getTemplateHtml() {
		return templateHtml;
	}
	public void setTemplateHtml(String templateHtml) {
		this.templateHtml = templateHtml;
	}
	public String getTemplateText() {
		return templateText;
	}
	public void setTemplateText(String templateText) {
		this.templateText = templateText;
	}
	
	
	public void addTo(String emailAddress) {
		this.getTo().add(emailAddress);
	}
	public void addCc(String emailAddress) {
		this.getCc().add(emailAddress);
	}
	public void addBcc(String emailAddress) {
		this.getBcc().add(emailAddress);
	}
	public void addVariables(Map<String, Object> variablesKeyValuePairs) {
		getVariables().putAll(variablesKeyValuePairs);
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getHtmlContent() {
		return htmlContent;
	}
	public void setHtmlContent(String htmlContent) {
		this.htmlContent = htmlContent;
	}
	public String getPlainTextContent() {
		return plainTextContent;
	}
	public void setPlainTextContent(String contentText) {
		this.plainTextContent = contentText;
	}
	public Map<String, URI> getAttachments() {
		if(this.attachments == null){
			this.attachments = new HashMap<>();
		}
		return attachments;
	}
	public void setAttachments(Map<String, URI> attachments) {
		this.attachments = attachments;
	}
	public void addAttachment(String name, URI url) {
		getAttachments().put(name, url);
	}
	public Map<String, File> getInlineFiles() {
		if(this.inlineFiles == null){
			this.inlineFiles = new HashMap<>();
		}
		return inlineFiles;
	}
	public void setInlineFiles(Map<String, File> inlineFiles) {
		this.inlineFiles = inlineFiles;
	}
	public void addInlineFile(String id, File file) {
		getInlineFiles().put(id, file);
	}
	public AuthenticationInfo getAuthInfo() {
		return authInfo;
	}
	public void setAuthInfo(AuthenticationInfo authInfo) {
		this.authInfo = authInfo;
	}
}
