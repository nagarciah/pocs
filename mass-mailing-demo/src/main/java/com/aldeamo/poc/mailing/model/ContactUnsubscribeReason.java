package com.aldeamo.poc.mailing.model;

/**
 * Indica la razón de la adición de un contacto a una lista de exclusión.
 * @author nelson
 */
public enum ContactUnsubscribeReason {
	/**
	 * La exclusión se hace porque se recibió un reporte de Spam
	 */
	SPAM_COMPLAINT, 

	/**
	 * El usuario seleccionó darse de baja de una lista
	 */
	LIST_UNSUBSCRIBE,
	
	/**
	 * Por ejemplo, cuando un email rebota porque la cuenta no existe
	 */
	BOUNCE_RULE,
	
	/**
	 * El usuario siguió un link de baja
	 */
	UNSUBSCRIBE_LINK, 
	
	/**
	 * Manualmente agregado por el administrador o por el cliente del API
	 */
	MANUALLY_ADDED, 
	
	/**
	 * Por cumplir alguna norma o ley
	 */
	COMPLIANCE
}
