package com.aldeamo.poc.mailing.api.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class EmailTemplateRequest {

	private Long id;
	
	/**
	 * El Identificador dado por el cliente a esta plantilla
	 */
	@NotNull
	@Size(min=1)
	private String customerTemplateId;
	
	@NotNull
	@Size(min=1)
	private String friendlyName;
	private String description;
	private String category;
	
	@NotNull
	@Size(min=1)
	private String customerId;
	
	@NotNull
	@Size(min=1)
	private String htmlFilename;
	private String txtFilename;
	private String imagesSubpath;
	private String cssSubpath;
	
	// TODO Cambiar por autenticación Básica HTTP
	@NotNull
	@Size(min=1)
	private String senderToken;
	
	
	public EmailTemplateRequest(){
		super();
	}
	
	public EmailTemplateRequest(String friendlyName, String description, String category, String customerId,
			String mainFilename, String basePath, String imagesPath, String cssPath) {
		super();
		this.friendlyName = friendlyName;
		this.description = description;
		this.category = category;
		this.customerId = customerId;
		this.htmlFilename = mainFilename;
		this.imagesSubpath = imagesPath;
		this.cssSubpath = cssPath;
	}
	
	public EmailTemplateRequest(String category, String customerId, String mainFilename, String basePath, String imagesPath, String cssPath) {
		this(null, null, category, customerId, mainFilename, basePath, imagesPath, cssPath);
	}
	
	public EmailTemplateRequest(String category, String customerId, String mainFilename, String basePath, String imagesPath) {
		this(category, customerId, mainFilename, basePath, imagesPath, null);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFriendlyName() {
		return friendlyName;
	}
	public void setFriendlyName(String friendlyName) {
		this.friendlyName = friendlyName;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getCustomerId() {
		return customerId;
	}
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	public String getHtmlFilename() {
		return htmlFilename;
	}
	public void setHtmlFilename(String htmlFilename) {
		this.htmlFilename = htmlFilename;
	}
	public String getImagesSubpath() {
		return imagesSubpath;
	}
	public void setImagesSubpath(String imagesPath) {
		this.imagesSubpath = imagesPath;
	}
	public String getCssSubpath() {
		return cssSubpath;
	}
	public void setCssSubpath(String cssPath) {
		this.cssSubpath = cssPath;
	}

	public String getTxtFilename() {
		return txtFilename;
	}

	public void setTxtFilename(String txtFilename) {
		this.txtFilename = txtFilename;
	}

	public String getCustomerTemplateId() {
		return customerTemplateId;
	}

	public void setCustomerTemplateId(String customerTemplateId) {
		this.customerTemplateId = customerTemplateId;
	}

	public String getSenderToken() {
		return senderToken;
	}

	public void setSenderToken(String senderToken) {
		this.senderToken = senderToken;
	}
}
