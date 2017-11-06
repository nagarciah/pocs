package com.aldeamo.docs.colsubsidio.cupo.it;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

import java.io.File;
import java.util.concurrent.TimeUnit;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringRunner;

import com.aldeamo.docs.colsubsidio.cupo.ColsubsidioCupoApplicationTests;
import com.aldeamo.docs.colsubsidio.cupo.rabbit.TestReceiver;
import com.aldeamo.docs.colsubsidio.cupo.service.TransformerService;

@RunWith(SpringRunner.class)
@SpringBootTest
@ComponentScan(basePackageClasses=ColsubsidioCupoApplicationTests.class)
public class LeerYEnviarTests {

	@Autowired
	TransformerService transformerService;
	
	@Autowired
	TestReceiver testReceiver;
	
	@Value("${inputFile}")
	File inputFile;

	@Value("${charsetName}")
	String charsetName;
	
	@Value("${cantidadEsperadaRegistros}") 
	int cantidadEsperadaRegistros;
	int enviados;

	@Test
	public void readAllAndSentThemToQueue() throws InterruptedException {
		leerArchivoYEnviarACola();
		verificarCola();
	}

	private void leerArchivoYEnviarACola() {
		this.enviados = transformerService.transform(inputFile, charsetName);
	}


	private void verificarCola() throws InterruptedException {
		testReceiver.getLatch().await(10, TimeUnit.SECONDS);
		assertThat(enviados).isEqualTo(cantidadEsperadaRegistros);
	}
}
