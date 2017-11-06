package com.aldeamo.docs.colsubsidio.cupo.service;

import java.io.File;
import java.util.Scanner;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.aldeamo.docs.colsubsidio.cupo.dto.Colilla;
import com.aldeamo.docs.colsubsidio.cupo.dto.Cupo;
import com.aldeamo.docs.colsubsidio.cupo.dto.CupoFragmento;
import com.aldeamo.docs.colsubsidio.cupo.dto.Detalle;
import com.aldeamo.docs.colsubsidio.cupo.dto.Encabezado;
import com.aldeamo.docs.colsubsidio.cupo.dto.Mensajes;
import com.aldeamo.docs.colsubsidio.cupo.dto.Metadata;
import com.aldeamo.docs.colsubsidio.cupo.dto.Resumen;
import com.aldeamo.docs.colsubsidio.cupo.dto.SaldosMagipuntos;

@Service
public class TransformerService {

	private static final String COD_ENCABEZADO = "1";
	private static final String COD_SALDOS_MAGIPUNTOS = "6";

	private final static Logger log = LoggerFactory.getLogger(TransformerService.class);

	@Autowired
	@Qualifier("cupoLineMapper")
	LineMapper<CupoFragmento> cupoLineMapper;

	@Value("${metadata.mantener.lineaOriginal:false}")
	boolean mantenerLineaOriginal;

	@Value("${metadata.mantener.nombreArchivo:false}")
	boolean mantenerNombreArchivo;

	@Value("${client.rabbit.exchange}")
	String rabbitExchange;

	@Value("${client.rabbit.routingKey}")
	String rabbitRoutingKey;

	@Autowired
	RabbitTemplate rabbitTemplate;

	/**
	 * Recorre línea por línea el archivo de entrada, creando objectos de tipo
	 * {@link Cupo} y enviándolos a la cola cuando finlaiza la creación de cada
	 * uno
	 * 
	 * @param inputFile
	 *            Archivo de entrada según la especificación de Colsubsidio para
	 *            el spool de Cupo
	 * @param charsetName
	 *            Nombre de la codificación en la cual están los caracteres del
	 *            archivo. Normalmente para un archivo creado en Windows es
	 *            "ISO-8859-1". Prara otros sistemas suele ser "UTF-8"
	 */
	public int transform(File inputFile, String charsetName) {
		int numeroLinea = 0;
		int numeroRegistro = 0;

		String nombreArchivo = inputFile.getAbsolutePath();
		String idEjecucion = UUID.randomUUID().toString();

		log.info("Iniciando procesamiento del archivo {} con id de ejecución {}", nombreArchivo, idEjecucion);

		rabbitTemplate.setExchange(rabbitExchange);
		rabbitTemplate.setRoutingKey(rabbitRoutingKey);

		try (Scanner scanner = new Scanner(inputFile, charsetName)) {
			Cupo cupo = null;

			while (scanner.hasNext()) {
				numeroLinea++;
				String linea = scanner.nextLine();
				String codRegistro = linea.substring(0, 1);

				if (COD_ENCABEZADO.equals(codRegistro)) {
					numeroRegistro++;
					cupo = new Cupo();
				}

				log.debug("Línea {} ==> {}", numeroLinea, linea);

				CupoFragmento fragmento = cupoLineMapper.mapLine(linea, numeroLinea);

				Metadata metadata = crearMetadata(nombreArchivo, idEjecucion, numeroLinea, numeroRegistro, linea);
				fragmento.setMetadata(metadata);

				agregarFragmento(cupo, fragmento);

				log.debug("Mapeo    ==> {}", fragmento);

				if (cupo != null && COD_SALDOS_MAGIPUNTOS.equals(codRegistro)) {
					enviarACola(cupo);
				}
			}

		} catch (Exception e) {
			throw new RuntimeException("Error transformando el archivo de entrada", e);
		}

		log.info(
				"Finalizado procesamiento del archivo {} con id de ejecución {}: {} registros fueron enviados a la cola",
				nombreArchivo, idEjecucion, numeroRegistro);

		return numeroRegistro;
	}

	private void agregarFragmento(Cupo cupo, CupoFragmento fragmento) {
		if (cupo != null) {
			if (fragmento instanceof Encabezado) {
				cupo.setEncabezado((Encabezado) fragmento);
			} else if (fragmento instanceof Detalle) {
				cupo.getDetalle().add((Detalle) fragmento);
			} else if (fragmento instanceof Mensajes) {
				cupo.getMensajes().add((Mensajes) fragmento);
			} else if (fragmento instanceof Resumen) {
				cupo.setResumen((Resumen) fragmento);
			} else if (fragmento instanceof Colilla) {
				cupo.setColilla((Colilla) fragmento);
			} else if (fragmento instanceof SaldosMagipuntos) {
				cupo.setSaldosMagipuntos((SaldosMagipuntos) fragmento);
			}
		}
	}

	private void enviarACola(Cupo cupo) throws Exception {
		try {
			rabbitTemplate.convertAndSend(cupo);
			log.debug("Enviado a la cola: {}", cupo);
		} catch (Exception e) {
			throw new Exception("Error intentando enviar un objeto a la cola", e);
		}
	}

	private Metadata crearMetadata(String nombreArchivo, String idEjecucion, int numeroLinea, int numeroRegistro,
			String linea) {
		Metadata metadata = new Metadata();
		metadata.setIdEjecucion(idEjecucion);
		metadata.setNumeroLinea(numeroLinea);
		metadata.setNumeroRegistro(numeroRegistro);
		if (mantenerNombreArchivo) {
			metadata.setNombreArchivo(nombreArchivo);
		}
		if (mantenerLineaOriginal) {
			metadata.setLineaOriginal(linea);
		}
		return metadata;
	}
}
