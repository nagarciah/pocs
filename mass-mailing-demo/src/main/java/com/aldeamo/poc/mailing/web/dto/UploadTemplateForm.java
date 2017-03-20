package com.aldeamo.poc.mailing.web.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class UploadTemplateForm {
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
	private String htmlFilename;
	private String txtFilename;
	private String imagesSubpath;
	private String cssSubpath;
	
	private String senderToken;
	private String customerId;

	public String getCustomerTemplateId() {
		return customerTemplateId;
	}

	public void setCustomerTemplateId(String customerTemplateId) {
		this.customerTemplateId = customerTemplateId;
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

	public String getHtmlFilename() {
		return htmlFilename;
	}

	public void setHtmlFilename(String htmlFilename) {
		this.htmlFilename = htmlFilename;
	}

	public String getTxtFilename() {
		return txtFilename;
	}

	public void setTxtFilename(String txtFilename) {
		this.txtFilename = txtFilename;
	}

	public String getImagesSubpath() {
		return imagesSubpath;
	}

	public void setImagesSubpath(String imagesSubpath) {
		this.imagesSubpath = imagesSubpath;
	}

	public String getCssSubpath() {
		return cssSubpath;
	}

	public void setCssSubpath(String cssSubpath) {
		this.cssSubpath = cssSubpath;
	}

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
}
