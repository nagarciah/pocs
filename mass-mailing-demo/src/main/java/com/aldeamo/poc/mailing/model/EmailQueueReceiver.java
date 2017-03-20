package com.aldeamo.poc.mailing.model;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class EmailQueueReceiver {
	
	private final Log log = LogFactory.getLog(EmailQueueReceiver.class);
	
	@Autowired
	SendingProcessingService sendingService;
	
	public void receiveMessage(EmailSendingRequest sending){
		try {
			sendingService.process(sending);
		} catch (Exception e) {
			// FIXME Implementar algún registro o mecanismo de reintento o Dead-Letter Channel, en combinación con el middleware de colas (RabbitMQ)
			log.error("Error inetntando enviar email recibido de la cola de mensajes: " + sending.toString(), e);
		}
	}
}
