package com.aldeamo.poc.mailing.model;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aldeamo.poc.mailing.api.dto.ContactUnsubscribeRequest;
import com.aldeamo.poc.mailing.dao.ExclusionListRepository;

@Service
public class ContactsService {
	public static final Log log = LogFactory.getLog(ContactsService.class);
	
	ExclusionListRepository exListRepository;

	@Autowired
	public ContactsService(ExclusionListRepository exListRepository) {
		super();
		this.exListRepository = exListRepository;
	}
	
	
	// TODO Verificar si también se añade a las listas de exlucisón de SparkPost. Podría ser útil en el caso en 
	//		que el cliente se conecte directamente al Endpoint SMTP o haga relay, en lugar de usar este API
	public ExclusionListEntry addToExclusionList(ContactUnsubscribeRequest unsubscribeRequest) {
		if(log.isDebugEnabled()){
			log.debug("Agregando contacto a lista de exclusión: " + unsubscribeRequest);
		}
		
		ExclusionListEntry entry = new ExclusionListEntry();
		entry.setCustomerId( unsubscribeRequest.getCustomerId() );
		entry.setCustomerListId( unsubscribeRequest.getExclusionListId() );
		entry.setDescription( unsubscribeRequest.getDescription() );
		entry.setReason( unsubscribeRequest.getReason() );
		entry.setRecipientEmail( unsubscribeRequest.getRecipientEmail() );
		
		exListRepository.saveAndFlush(entry);
		
		if(log.isDebugEnabled()){
			log.debug("Contacto agregado a lista de exclusión: " + entry);
		}
		
		return entry;
	}
	

	
	/**
	 * Si no se indica una lista de exclusión, se busca en todas las listas de exclusión del cliente. Esto para evitar
	 * Que el receptor siga recibiendo mensajes aún cuando ha solicitado su baja en alguna lista
	 * @param customerId TODO
	 * @param sending
	 * @return
	 */
	public boolean isInExclusionList(String customerId, String customerExclusionListId, String recipientEmail) {
		// FIXME Hacer esta búsqueda más eficiente, en caso de listas largas de To
		ExclusionListEntry entry = null;
		if(customerExclusionListId !=null){
			entry = exListRepository.findOneByCustomerIdAndCustomerListIdAndRecipientEmail(
					customerId, 
					customerExclusionListId, 
					recipientEmail);
		}else{
			entry = exListRepository.findOneByCustomerIdAndRecipientEmail(
					customerId, 
					recipientEmail);
		}
		
		if(entry!=null){
			return true;
		}

		return false;
	}
}
