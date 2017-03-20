package com.aldeamo.poc.mailing.web.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.aldeamo.poc.mailing.util.Constants;

public class UnsubscribeForm {
	@NotNull
	@Pattern(regexp=Constants.EMAIL_REGEX, message="No tiene el formato de una dirección de email válida")
	private String confirmedEmail;
	private String userReason;
		
	public String getConfirmedEmail() {
		return confirmedEmail;
	}
	public void setConfirmedEmail(String confirmedEmail) {
		this.confirmedEmail = confirmedEmail;
	}
	public String getUserReason() {
		return userReason;
	}
	public void setUserReason(String userReason) {
		this.userReason = userReason;
	}
}
