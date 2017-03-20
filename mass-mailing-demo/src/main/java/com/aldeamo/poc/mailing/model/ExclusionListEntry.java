package com.aldeamo.poc.mailing.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.aldeamo.poc.mailing.util.Constants;

// TODO Revisar si este modelo si es eficiente, teniendo en cuenta 
//		que pueden crecer muchísimo. Podría requerir NoSQL. Por ahora no se normaliza para evitar Join
@Entity
public class ExclusionListEntry {
	@Id
	@GeneratedValue
	private Long id;
	
	@NotNull
	@Size(min=1)
	private String customerId; //FIXME Verificar que exista un índice para esta columna, customerListId y para recipientEmail 
	
	@NotNull
	@Size(min=1)
	private String customerListId;
	
	@NotNull
	@Pattern(regexp=Constants.EMAIL_REGEX, message="No tiene el formato de una dirección de email válida")
	private String recipientEmail;
	
	@NotNull
	private ContactUnsubscribeReason reason;
	
	private String description;
	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getRecipientEmail() {
		return recipientEmail;
	}

	public void setRecipientEmail(String recipientEmail) {
		this.recipientEmail = recipientEmail;
	}

	public ContactUnsubscribeReason getReason() {
		return reason;
	}

	public void setReason(ContactUnsubscribeReason reason) {
		this.reason = reason;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getCustomerListId() {
		return customerListId;
	}
	
	public void setCustomerListId(String customerListId) {
		this.customerListId = customerListId;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ExclusionListEntry [id=");
		builder.append(id);
		builder.append(", customerId=");
		builder.append(customerId);
		builder.append(", customerListId=");
		builder.append(customerListId);
		builder.append(", recipientEmail=");
		builder.append(recipientEmail);
		builder.append(", reason=");
		builder.append(reason);
		builder.append(", description=");
		builder.append(description);
		builder.append("]");
		return builder.toString();
	}
}
