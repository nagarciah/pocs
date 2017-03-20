package com.aldeamo.poc.mailing.testutil;

import java.io.IOException;
import java.util.Properties;

public class AppPropertiesReader {
	
	private final static AppPropertiesReader _instance = new AppPropertiesReader();
	private Properties props = null;
	
	private AppPropertiesReader(){
		if(props==null){
			props = new Properties();
			
			try {
				props.load(this.getClass().getResourceAsStream("/application.properties"));
			} catch (IOException e) {
				throw new RuntimeException("Error cargando archivo de propiedades", e);
			}
		}
	}
	
	public static String get(String propertyName){
		return (String) _instance.props.get(propertyName);
	}
}
