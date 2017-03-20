package com.aldeamo.poc.mailing.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;


@Entity
public class UserInfo implements Serializable{
	
	private static final long serialVersionUID = 3440582298859253229L;
	
	@Id
	@GeneratedValue
	private Long id;
	private String username;
	private String firstName;
	private String lastName;
	private String password;
	private String customerId;
	private String senderToken;
	// TODO Buscar la forma de abstraer estos parámetros para ocultar la existencia de Sparkpost y poder cambiar por otro proveedor (Metadata Map?)
	private String providerToken;
	private String providerSubaccountId;
	
	public UserInfo(){
		super();
	}
	
	public UserInfo(Long id, String username, String firstName, String lastName, String password) {
		super();
		this.id = id;
		this.username = username;
		this.firstName = firstName;
		this.lastName = lastName;
		this.password = password;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getSenderToken() {
		return senderToken;
	}

	public void setSenderToken(String senderToken) {
		this.senderToken = senderToken;
	}

	public String getProviderToken() {
		return providerToken;
	}

	public void setProviderToken(String providerToken) {
		this.providerToken = providerToken;
	}

	public String getProviderSubaccountId() {
		return providerSubaccountId;
	}

	public void setProviderSubaccountId(String providerSubaccountId) {
		this.providerSubaccountId = providerSubaccountId;
	}
}
