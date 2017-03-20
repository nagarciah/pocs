package tech.nagarciah.poc.blobmigration;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Migrator {
	private static Logger log = Logger.getLogger(Migrator.class);

	@Autowired
	DAO sourceDAO;
	
	@Autowired
	DAO targetDAO;
	
	boolean stopOnException;

	public void migrate() {
		log.info("Buscando registros...");

		List<Integer> ids = sourceDAO.getIDs();

		log.info("Registros encontrados: " + ids.size());

		long start = System.currentTimeMillis(); 
		double count = 0;
		StringBuilder logMsg = new StringBuilder();
		
		for (int id : ids) {
			try 
			{
				logMsg.setLength(0);
				logMsg.append("Procesando registro con ID ").append(id).append(" - ").append( (count++/((double)ids.size()))*100 ).append("%");
				log.info( logMsg );
				
				targetDAO.write(id, sourceDAO.read(id));
			} 
			catch (Exception e) 
			{
				logMsg.setLength(0);
				logMsg.append("Error procesando registro con ID ").append(id);
				log.error(logMsg, e);
				
				if (stopOnException) {
					log.warn("Se detendrá el procesamiento porque stopOnException es true.");
					break;
				}
			}
		}
		
		log.info("Proceso finalizado en " + ((System.currentTimeMillis()-start)/1000) + " segundos");
	}
	

	public DAO getSourceDAO() {
		return sourceDAO;
	}

	public void setSourceDAO(DAO sourceDAO) {
		this.sourceDAO = sourceDAO;
	}

	public DAO getTargetDAO() {
		return targetDAO;
	}

	public void setTargetDAO(DAO targetDAO) {
		this.targetDAO = targetDAO;
	}

	public boolean isStopOnException() {
		return stopOnException;
	}

	public void setStopOnException(boolean stopOnException) {
		this.stopOnException = stopOnException;
	}
}
