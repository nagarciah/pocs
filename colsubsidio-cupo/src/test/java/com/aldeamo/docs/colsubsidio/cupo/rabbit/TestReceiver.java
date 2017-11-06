package com.aldeamo.docs.colsubsidio.cupo.rabbit;

import java.util.concurrent.CountDownLatch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.aldeamo.docs.colsubsidio.cupo.dto.Cupo;

@Component
public class TestReceiver {

	private static final Logger log = LoggerFactory.getLogger(TestReceiver.class);
	private CountDownLatch latch;
	private int cantidadEsperadaRegistros;

	public TestReceiver(
			@Value("${cantidadEsperadaRegistros}") int cantidadEsperadaRegistros) 
	{
		this.latch = new CountDownLatch(cantidadEsperadaRegistros);
	}

	public void receiveMessage(Cupo cupo) {
		log.info("Recibido {}", cupo);
		latch.countDown();
	}

	public CountDownLatch getLatch() {
		return latch;
	}

	public int getCantidadEsperadaRegistros() {
		return cantidadEsperadaRegistros;
	}
}