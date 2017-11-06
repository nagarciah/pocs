package com.sinbugs.docgenerator.pocs;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;

import org.junit.Test;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

/**
 * Opciones:
 * <ol>
 * <li>Usar expresiones JsonPointer como nombres de rangos en properties para
 * luego asignar el rango al nodo</li>
 * <li>Usar una plantilla JSON con placeholders para los nombres de rangos,
 * leerla y llenarla con cada registro: la plantilla parece ser de solo lectura.
 * Para modificcarle se debe reconstruir el arbol</li>
 * </ol>
 * <b>Como saber cuando termina un registro en el input?</b>
 * 
 * @author nelson
 * @see http://websystique.com/java/json/jackson-tree-model-example/
 */
public class JsonDynamicTests {

	@Test
	public void createObjectTreeModelSample() throws IOException {
		ObjectMapper mapper = new ObjectMapper();

		JsonGenerator generator = mapper.getFactory().createGenerator(System.out);

		JsonNode rootNode = mapper.createObjectNode();
		JsonNode node;

		// root.car
		// root.colors[]

		/*
		 * if(rootNode.has("/car")) { node = rootNode.findPath("/car"); }else{
		 * node = ((ObjectNode) rootNode).put("car", "Hyundai"); }
		 */

		node = ((ObjectNode) rootNode).put("car", "Hyundai");

		ArrayNode arrayNode = ((ObjectNode) node).putArray("colors");
		arrayNode.add("GRAY");
		arrayNode.add("WHITE");

		JsonNode arrayNode2 = ((ObjectNode) node).findPath("colors");
		((ArrayNode) arrayNode2).add("GRAY2");
		((ArrayNode) arrayNode2).add("WHITE2");

		mapper.writeTree(generator, rootNode);
	}

	@Test
	public void readObjectTreeModelSample() throws JsonProcessingException, IOException {
		System.out.println("");

		ObjectMapper mapper = new ObjectMapper();

		JsonNode rootNode = mapper.readTree(new File("input.json"));
		// ((ObjectNode)rootNode.at("/")).set("car", "Hyundai2");

		JsonNode carNode = rootNode.path("car");
		System.out.println(carNode.asText());

		JsonNode priceNode = rootNode.path("price");
		System.out.println(priceNode.asText());

		JsonNode modelNode = rootNode.path("model");
		System.out.println(modelNode.asText());

		JsonNode colorsNode = rootNode.path("colors");
		Iterator<JsonNode> colors = colorsNode.elements();

		while (colors.hasNext()) {
			System.out.println(colors.next().asText());
		}
	}
}
