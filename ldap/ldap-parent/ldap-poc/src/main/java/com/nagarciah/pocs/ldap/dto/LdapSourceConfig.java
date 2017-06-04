package com.nagarciah.pocs.ldap.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Representa la información de configuración de un servidor LDAP que será usado
 * como fuente de datos
 * 
 * @author nelson
 *
 */
@Entity
public class LdapSourceConfig {

	@Id
	@Column(name = "ldap_key")
	private String key;
	private String description;
	private String urls; // =ldap://localhost:10389
	@Column(name = "ldap_password")
	private String password;// =secret
	@Column(name = "ldap_username")
	private String username;// =uid=admin,ou=system
	private String base;// =ou=users,dc=example,dc=com

	public LdapSourceConfig() {
		super();
	}

	/**
	 * 
	 * @param key
	 *            Identificador único de esta fuente de datos
	 * @param description
	 *            Descripción o nombre "amigable" para mostrar al usuario
	 * @param urls
	 *            una o mas URLs separadas con comas, a las cuales se puede
	 *            conectar el cliente, con el formato
	 *            <code>ldap://host:puerto</code>
	 * @param password
	 * @param username
	 *            Distinguished Name (DN) del usuario LDAP para conectarse al
	 *            servidor, por ejemplo, <code>uid=admin,ou=system</code>
	 * @param base
	 *            Nodo dentro del cual se ejecutarán las búsquedas y demás
	 *            operaciones, ejemplo: <code>ou=users,dc=example,dc=com</code>
	 */
	public LdapSourceConfig(String key, String description, String urls, String base,
			String username, String password) {
		super();
		this.key = key;
		this.description = description;
		this.urls = urls;
		this.password = password;
		this.username = username;
		this.base = base;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getUrls() {
		return urls;
	}

	public void setUrls(String urls) {
		this.urls = urls;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getBase() {
		return base;
	}

	public void setBase(String base) {
		this.base = base;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("LdapSourceConfig [key=");
		builder.append(key);
		builder.append(", description=");
		builder.append(description);
		builder.append(", urls=");
		builder.append(urls);
		builder.append(", password=");
		builder.append(password);
		builder.append(", username=");
		builder.append(username);
		builder.append(", base=");
		builder.append(base);
		builder.append("]");
		return builder.toString();
	}
}
