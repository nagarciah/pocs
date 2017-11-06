package com.aldeamo.docs.colsubsidio.cupo;

import java.io.File;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.aldeamo.docs.colsubsidio.cupo.service.TransformerService;

@SpringBootApplication
public class ColsubsidioCupoApplication {

	public static void main(String[] args) {
		SpringApplication.run(ColsubsidioCupoApplication.class, args);
	}

	// FIXME Cambiar esta inicialización por una ruta de camel que lea de un directorio e inicie el procesamiento
//	@Bean
//	CommandLineRunner init(final TransformerService transformerService) {
//	    return new CommandLineRunner() {
//			@Value("${inputFile}")
//			File inputFile;
//	
//			@Value("${charsetName}")
//			String charsetName;
//			
//	        public void run(String... strings) throws Exception {
//	            transformerService.transform(inputFile, charsetName);
//	        }
//	    };
//	}
}
