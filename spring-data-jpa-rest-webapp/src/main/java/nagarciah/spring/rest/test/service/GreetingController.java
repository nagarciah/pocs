package nagarciah.spring.rest.test.service;

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

@RestController()
public class GreetingController {

	private static final String template = "Hello, %s!";
	private final AtomicLong counter = new AtomicLong();
	
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
	}
}
