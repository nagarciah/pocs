package tech.nagarciah.poc.blobmigration;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


/**
 * Adaptado de http://java.dzone.com/articles/drools-integration-spring-vs
 * 
 * @author n.garcia
 */
public class App {
	private static Log log = LogFactory.getLog(App.class);
	
	public static void main(String[] args) {
		ApplicationContext context;
		try {
			log.info("Cargando configuraciónn...");
	
			String contextFile = "classpath:main-context.xml";
			context = new ClassPathXmlApplicationContext(contextFile);
	
			log.info("Configuración cargada. Iniciando aplicación...");
			
			Migrator migrator = context.getBean(Migrator.class);
			migrator.migrate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			//context.close();
		}
	}
}