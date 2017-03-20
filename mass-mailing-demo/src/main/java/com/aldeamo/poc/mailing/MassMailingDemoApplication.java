package com.aldeamo.poc.mailing;

//import org.h2.server.web.WebServlet;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.subethamail.wiser.Wiser;

@SpringBootApplication
public class MassMailingDemoApplication {
	
	@Value("${test.mail.port}")
	int testMailServerPort;
	
	public static void main(String[] args) {
		SpringApplication.run(MassMailingDemoApplication.class, args);
	}
	
	// TODO Mover a configuración del perfil de pruebas
//	@Bean
//	public ServletRegistrationBean h2servletRegistration() {
//	    ServletRegistrationBean registration = new ServletRegistrationBean(new WebServlet());
//	    registration.addUrlMappings("/console/*");
//	    return registration;
//	}
	
	//@Bean
	public Wiser smtpMockServer(){
        Wiser wiser = new Wiser(testMailServerPort);
        wiser.start();
        
        return wiser;
	}
}
