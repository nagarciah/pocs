package com.aldeamo.poc.mailing.api;

import javax.validation.Valid;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.aldeamo.poc.mailing.api.dto.EmailSendingResponse;
import com.aldeamo.poc.mailing.api.dto.EmailSendingResponse.EmailSendingStatus;
import com.aldeamo.poc.mailing.model.EmailSendingRequest;
import com.aldeamo.poc.mailing.model.SendingProcessingService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(ApiConstants.API_MAPPING + "/email")
// @Api(value="EmailAPI")
public class EmailAPI {

	public final static Log log = LogFactory.getLog(EmailAPI.class);

	@Autowired
	SendingProcessingService sendingProcessingService;

	// FIXME Por ahora el token que se recibe es el mismo ID de sparkpost de la
	// subcuenta. Debería cambiarse a un token real, administardo por Aldeamo,
	// que mapee al ID de la subcuenta, para reducir dependencias en el servicio
	// externo
	@RequestMapping(
			value = { "/send-single", "/send-single/" }, 
			method = RequestMethod.POST, 
			produces=MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Enviar email con reemplazo de variables", 
		notes = "El sendingId que se retorna, debe ser almacenado por la aplicación cliente si está "
			+ "interesado en realizar consultas posteriores sobre el estado del envío. El valor 'RECEIVED' "
			+ "indica que el sistema ha recibido la petición de email y será procesada durante los próximos "
			+ "segundos o minutos. Esto no indica que el email ha sido recibido por el destinatario. Para "
			+ "consultar el número de correos entregados a los destinatarios, debe utilizar el sendingId "
			+ "en el API de reportes. Este método soporta el reemplazo de variables en la plantilla.")
	public EmailSendingResponse sendSingleEmail(
			@Valid @RequestBody(required = true) EmailSendingRequest sending) throws Exception 
	{

		try {
			// FIXME Añadir validación del campo TO. Ese campo debe ser opcional
			// para evníos masivos.
			Long sendingId = sendingProcessingService.process(sending);
			return new EmailSendingResponse(sendingId, EmailSendingStatus.RECEIVED);
		} catch (Exception e) {
			// TODO Agregar más información para poder rastrear el error y
			// agilizar el diagnóstico cuando sea reportado por el cliente
			log.error(e);
			// return new EmailSendingResponse(null,
			// EmailSendingStatus.PROCESSING_ERROR);
			throw e;
		}
	}


	@ApiOperation(value = "Enviar email con reemplazo de variables leídas desde un archivo", 
			notes = "El sendingId que se retorna, debe ser almacenado por la aplicación cliente si está "
				+ "interesado en realizar consultas posteriores sobre el estado del envío. El valor 'RECEIVED' "
				+ "indica que el sistema ha recibido la petición de email y será procesada durante los próximos "
				+ "segundos o minutos. Esto no indica que el email ha sido recibido por el destinatario. Para "
				+ "consultar el número de correos entregados a los destinatarios, debe utilizar el sendingId "
				+ "en el API de reportes. Este método soporta el reemplazo de variables en la plantilla.")
	@RequestMapping(
			value = { "/send-massive", "/send-massive/" }, 
			method = RequestMethod.POST, 
			consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public EmailSendingResponse sendMultipleEmail(
			@RequestParam(value = "emailDataFile", required = true) MultipartFile emailDataFile,
			@Valid EmailSendingRequest sending,
			BindingResult bindingResult) throws Exception 
	{
		try {			
			Long sendingId = sendingProcessingService.processMassive(sending, emailDataFile);
			return new EmailSendingResponse(sendingId, EmailSendingStatus.RECEIVED);
		} catch (Exception e) {
			// TODO Agregar más información para poder rastrear el error y
			// agilizar el diagnóstico cuando sea reportado por el cliente
			log.error(e);
			// return new EmailSendingResponse(null,
			// EmailSendingStatus.PROCESSING_ERROR);
			throw e;
		}
	}
}
