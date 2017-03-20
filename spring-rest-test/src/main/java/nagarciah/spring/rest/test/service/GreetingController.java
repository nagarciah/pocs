package nagarciah.spring.rest.test.service;

import java.io.IOException;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.atomic.AtomicLong;

import nagarciah.spring.rest.test.model.Greeting;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController()
public class GreetingController {

	private static final String template = "Hello, %s!";
	private final AtomicLong counter = new AtomicLong();
	private static final ObjectMapper mapper = new ObjectMapper(); // can reuse, share globally
	
	
	@RequestMapping(value="/greeting", method=RequestMethod.GET)
	public Greeting greeting(
			@RequestParam(value="name", defaultValue="World") String name) {
		return new Greeting(
				counter.incrementAndGet(), 
				String.format(template, name));
	}
	
	@RequestMapping(value="/greeting", method=RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE) // consumes es opcional
	@ResponseStatus( HttpStatus.CREATED ) //Opcional
	public void create(@RequestBody Greeting g){
		System.out.println("Recibido: " + g.getContent());
		
		// Ejemplo para interpretar JSON genérico recibido en una variable del objeto parámetro:
		try {
			Map<String,Object> userData = mapper.readValue(g.getContent() /*"{\"tags\":[{\"id\":1, \"tag\":\"linux\"}, {\"id\":2, \"tag\":\"java\"}]}"*/, Map.class);
			
			for(Entry<String,Object> e : userData.entrySet()) {
				System.out.println(e.getKey() + "=" + e.getValue());
			}
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
