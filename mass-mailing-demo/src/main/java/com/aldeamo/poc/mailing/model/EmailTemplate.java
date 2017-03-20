package com.aldeamo.poc.mailing.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class EmailTemplate {

	@Id
	@GeneratedValue
	private Long id;
	
	/**
	 * El Identificador dado por el cliente a esta plantilla
	 */
	private String customerTemplateId;
	private String friendlyName;
	private String description;
	private String category;
	private String customerId;
	private String htmlFilename;
	private String txtFilename;
	private String templateSubdir;
	private String imagesSubpath;
	private String cssSubpath;
	private String uploadedZipFilename;
	
	public EmailTemplate(){
		super();
	}
	
	public EmailTemplate(String friendlyName, String description, String category, String customerId,
			String mainFilename, String basePath, String imagesPath, String cssPath) {
		super();
		this.friendlyName = friendlyName;
		this.description = description;
		this.category = category;
		this.customerId = customerId;
		this.htmlFilename = mainFilename;
		this.templateSubdir = basePath;
		this.imagesSubpath = imagesPath;
		this.cssSubpath = cssPath;
	}
	
	public EmailTemplate(String category, String customerId, String mainFilename, String basePath, String imagesPath, String cssPath) {
		this(null, null, category, customerId, mainFilename, basePath, imagesPath, cssPath);
	}
	
	public EmailTemplate(String category, String customerId, String mainFilename, String basePath, String imagesPath) {
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
	public String getTemplateSubdir() {
		return templateSubdir;
	}
	public void setTemplateSubdir(String subdirPath) {
		this.templateSubdir = subdirPath;
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

	public String getUploadedZipFilename() {
		return uploadedZipFilename;
	}

	public void setUploadedZipFilename(String uploadedZipFilename) {
		this.uploadedZipFilename = uploadedZipFilename;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("EmailTemplate [id=").append(id).append(", customerTemplateId=").append(customerTemplateId)
				.append(", friendlyName=").append(friendlyName).append(", description=").append(description)
				.append(", category=").append(category).append(", customerId=").append(customerId)
				.append(", htmlFilename=").append(htmlFilename).append(", txtFilename=").append(txtFilename)
				.append(", templateSubdir=").append(templateSubdir).append(", imagesSubpath=").append(imagesSubpath)
				.append(", cssSubpath=").append(cssSubpath).append(", uploadedZipFilename=").append(uploadedZipFilename)
				.append("]");
		return builder.toString();
	}
}
