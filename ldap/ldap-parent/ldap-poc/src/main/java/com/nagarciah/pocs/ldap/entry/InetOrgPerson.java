package com.nagarciah.pocs.ldap.entry;

import java.util.Arrays;

import javax.naming.Name;

import org.springframework.ldap.odm.annotations.Attribute;
import org.springframework.ldap.odm.annotations.Attribute.Type;
import org.springframework.ldap.odm.annotations.Entry;
import org.springframework.ldap.odm.annotations.Id;


/**
 * 	// Atributos de top:
	objectClass
	
	// Atributos de person:
	MUST ( sn $ cn )
	MAY ( userPassword $ telephoneNumber $ seeAlso $ description
			  
	// organizationalPerson
	MAY ( title $ x121Address $ registeredAddress $ destinationIndicator $
			preferredDeliveryMethod $ telexNumber $ teletexTerminalIdentifier $
			telephoneNumber $ internationaliSDNNumber $
			facsimileTelephoneNumber $ street $ postOfficeBox $ postalCode $
			postalAddress $ physicalDeliveryOfficeName $ ou $ st $ l
					   
	// inetOrgPerson
	MAY ( audio $ businessCategory $ carLicense $ departmentNumber $
			displayName $ employeeNumber $ employeeType $ givenName $
			homePhone $ homePostalAddress $ initials $ jpegPhoto $
			labeledURI $ mail $ manager $ mobile $ o $ pager $ photo $
			roomNumber $ secretary $ uid $ userCertificate $
			x500uniqueIdentifier $ preferredLanguage $
			userSMIMECertificate $ userPKCS12 )
 * @author nelson
 *
 */
@Entry(objectClasses = { "top", "person", "organizationalPerson", "inetOrgPerson" })
public final class InetOrgPerson {

	@Id
	Name dn;
		
	@Attribute
	String sn;
	
	@Attribute
	String cn;
	
	@Attribute
	String displayName;

	@Attribute
	String uid;
	
	@Attribute
	String mail;
	
	@Attribute
	String postalAddress;
	
	@Attribute
	String photo;
	
	@Attribute
	String telephoneNumber;
	
	@Attribute
	String title;
	
	@Attribute
	String labeledUri;
	
	@Attribute(type=Type.BINARY)
	byte[] userPassword;
	

	public InetOrgPerson(String displayName, String uid) {
		super();
		this.displayName = displayName;
		this.uid = uid;
	}
	
	public InetOrgPerson() {
		
	}

	public Name getDn() {
		return dn;
	}

	public void setDn(Name dn) {
		this.dn = dn;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("InetOrgPerson [dn=");
		builder.append(dn);
		builder.append(", sn=");
		builder.append(sn);
		builder.append(", cn=");
		builder.append(cn);
		builder.append(", displayName=");
		builder.append(displayName);
		builder.append(", uid=");
		builder.append(uid);
		builder.append(", mail=");
		builder.append(mail);
		builder.append(", postalAddress=");
		builder.append(postalAddress);
		builder.append(", photo=");
		builder.append(photo);
		builder.append(", telephoneNumber=");
		builder.append(telephoneNumber);
		builder.append(", title=");
		builder.append(title);
		builder.append(", labeledUri=");
		builder.append(labeledUri);
		builder.append(", userPassword=");
		builder.append(Arrays.toString(userPassword));
		builder.append("]");
		return builder.toString();
	}

	public String getSn() {
		return sn;
	}

	public void setSn(String sn) {
		this.sn = sn;
	}

	public String getCn() {
		return cn;
	}

	public void setCn(String cn) {
		this.cn = cn;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getPostalAddress() {
		return postalAddress;
	}

	public void setPostalAddress(String postalAddress) {
		this.postalAddress = postalAddress;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public String getTelephoneNumber() {
		return telephoneNumber;
	}

	public void setTelephoneNumber(String telephoneNumber) {
		this.telephoneNumber = telephoneNumber;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getLabeledUri() {
		return labeledUri;
	}

	public void setLabeledUri(String labeledUri) {
		this.labeledUri = labeledUri;
	}

	public byte[] getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(byte[] userPassword) {
		this.userPassword = userPassword;
	}
}
