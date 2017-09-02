package com.pocs.docs.batch;

import java.util.Map;

//import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.batch.core.SkipListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class ItemProcessorListener implements SkipListener<Map<String, Object>, Map<String, Object>>/*, StepExecutionListener*/ {
	
	private Log log = LogFactory.getLog(ItemProcessorListener.class);
	
	/*@Autowired
	private DbService dbService;*/
	

	@Override
	public void onSkipInRead(Throwable exception) {
		log.error("Error leyendo un registro del archivo de entrada", exception);
	}
	
	@Override
	public void onSkipInProcess(Map<String, Object> item, Throwable exception) {
/*		StringBuilder root = new StringBuilder();
		for(String line : ExceptionUtils.getRootCauseStackTrace(exception)){
			root.append(line).append("\n");
		}
		
		item.setErrorProcesamiento(root.toString());
		log.error("Error procesando item: " + item, exception);
		
		try{
			dbService.guardarTransaccionSms(item);
		}catch(Exception ex){
			StringBuilder fatal = new StringBuilder();
			fatal.append("Ocurrio un error procesando este registro del archivo ")
				.append("de entrada y tampoco pudo registrarse el error en la base de datos: ")
				.append(item)
				.append("\nEl error de procesamiento del item fue: ")
				.append(ExceptionUtils.getStackTrace(exception))
				.append("\nLa causa de no registrarlo en la base de datos fue:")
				.append(ExceptionUtils.getStackTrace(ex));
			log.error(fatal);
		}*/
	}

	@Override
	public void onSkipInWrite(Map<String, Object> item, Throwable exception) {
		log.error("Item fue procesado correctamente pero ocurrio un error escribiendo el resultado en el archivo de salida. Item: " + item, exception);
	}
}
