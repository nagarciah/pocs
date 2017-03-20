package com.aldeamo.poc.mailing.model;

public class AuthenticationInfo {
	
	/**
	 * Indica el tipo de autenticación
	 * @author nelson
	 */
	public enum AuthType {
		SMTP_AUTH_LOGIN,
		CUSTOM_TOKEN
	}
	
	AuthType authType;
	String username;
	String password;
	String token;
	
	public AuthenticationInfo(AuthType authType, String username, String password) {
		super();
		this.authType = authType;
		this.username = username;
		this.password = password;
	}
	
	/**
	 * Usuario y password para usar en la conexion con el proveedor del servicio de email.
	 * Por defecto asume el tipo de autenticación {@link AuthType.SMTP_AUTH_LOGIN}
	 * @param username Nombre de usuario
	 * @param password Contraseña
	 */
	public AuthenticationInfo(String username, String password) {
		this(AuthType.SMTP_AUTH_LOGIN, username, password);
	}
	
	/**
	 * Token del usuario para usar en la conexion con el proveedor del servicio de email.
	 * Por defecto asume el tipo de autenticación {@link AuthType.CUSTOM_TOKEN}
	 * @param username Nombre de usuario
	 * @param password Contraseña
	 */
	public AuthenticationInfo(String userToken) {
		super();
		this.authType = AuthType.CUSTOM_TOKEN;
		this.setToken(userToken);
	}

	public AuthType getAuthType() {
		return authType;
	}
	public void setAuthType(AuthType authType) {
		this.authType = authType;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
}
