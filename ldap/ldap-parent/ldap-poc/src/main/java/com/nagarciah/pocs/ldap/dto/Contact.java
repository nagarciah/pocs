package com.nagarciah.pocs.ldap.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL) // Para solo incluir propiedades no nulas en el json
public class Contact {
	String id;
	
	@NotNull
	@Size(min=1)
	String name;
	
	String role;
	String phone;
	String address;
	String phoneExt;
	String image;
	String email;
	String countryCode;
	String headquarter;
	String status;
	boolean vip;  //type
	String skype;
	String password;
	
	public Contact(){}
	
	public Contact(String id, String name, String role, String phone, String address, String phoneExt, String image,
			String email, String countryCode, String headquarter, String status, boolean vip, String skype) {
		super();
		this.id = id;
		this.name = name;
		this.role = role;
		this.phone = phone;
		this.address = address;
		this.phoneExt = phoneExt;
		this.image = image;
		this.email = email;
		this.countryCode = countryCode;
		this.headquarter = headquarter;
		this.status = status;
		this.vip = vip;
		this.skype = skype;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPhoneExt() {
		return phoneExt;
	}
	public void setPhoneExt(String phoneExt) {
		this.phoneExt = phoneExt;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getCountryCode() {
		return countryCode;
	}
	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}
	public String getHeadquarter() {
		return headquarter;
	}
	public void setHeadquarter(String headquarter) {
		this.headquarter = headquarter;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public boolean isVip() {
		return vip;
	}
	public void setVip(boolean vip) {
		this.vip = vip;
	}
	public String getSkype() {
		return skype;
	}
	public void setSkype(String skype) {
		this.skype = skype;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Contact [id=");
		builder.append(id);
		builder.append(", name=");
		builder.append(name);
		builder.append(", role=");
		builder.append(role);
		builder.append(", phone=");
		builder.append(phone);
		builder.append(", address=");
		builder.append(address);
		builder.append(", phoneExt=");
		builder.append(phoneExt);
		builder.append(", image=");
		builder.append(image);
		builder.append(", email=");
		builder.append(email);
		builder.append(", countryCode=");
		builder.append(countryCode);
		builder.append(", headquarter=");
		builder.append(headquarter);
		builder.append(", status=");
		builder.append(status);
		builder.append(", vip=");
		builder.append(vip);
		builder.append(", skype=");
		builder.append(skype);
		builder.append("]");
		return builder.toString();
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPassword() {
		return password;
	}
}
