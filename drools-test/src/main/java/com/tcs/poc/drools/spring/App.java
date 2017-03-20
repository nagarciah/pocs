package com.tcs.poc.drools.spring;

import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.drools.time.Job;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.tcs.poc.drools.spring.model.DecisionType;


/**
 * Adaptado de http://java.dzone.com/articles/drools-integration-spring-vs
 * 
 * @author n.garcia
 */
public class App {
	private static Log log = LogFactory.getLog(App.class);
	
	public static void main(String[] args) {
		log.info("Cargando configuraci�n...");

		//String contextFile = System.getProperty("springContextFile"); //"file:C:/_workspaces/workspace-spring/spring-batch-test/spring-contexts/main-context.xml"
		String contextFile = "classpath:config/spring/main-context.xml";
		ApplicationContext context = new ClassPathXmlApplicationContext(contextFile);

		log.info("Configuración cargada. Iniciando aplicación...");
/*
		JobLauncher jobLauncher = context.getBean(JobLauncher.class);
		Job job = context.getBean("importProducts", Job.class);
		try {
			jobLauncher.run(
					job,
					new JobParametersBuilder()
							.addString("inputResource", inputResource)
							.addString("targetDirectory", targetDirectory)
							.addString("targetFile", targetFile)
							.addDate("date", new Date()).toJobParameters());
		}catch (Exception e) {
			log.error("Error lanzando job", e);
		}finally{
			log.info("Aplicación finalizada", e);
		}*/
		
	}
}