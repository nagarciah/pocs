package com.aldeamo.poc.mailing.model;

import javax.mail.MessagingException;

public interface EmailService {

	/**
	 * Permite cambiar las credenciales STMP a utilizar en la conexión con el servicio SMTP. Las nuevas credenciales se utilizarán
	 * para los envíos subsecuentes hasta que se invoque nuevamente a este método con información de autenticación nueva
	 * @param authInfo Por ahora sólo soporta autenticación con token de usuario ({@link AuthType.CUSTOM_TOKEN})
	 */
	public void updateAuthInfo(AuthenticationInfo authInfo);
	
	void send(EmailMessage message);

	void sendMime(EmailMessage message) throws MessagingException;
}
