package nagarciah.pocs.statemachine.service;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.stereotype.Component;

@Component
public class MyProcessor implements Processor {

	public String doSomething(Exchange exchange) {

		System.out.println("Bean1 Received Exchange: " + exchange.getIn().getBody(String.class) + ", MIP: "
				+ exchange.getPattern());

		return exchange.getIn().getBody(String.class);

	}

	@Override
	public void process(Exchange exchange) throws Exception {
		System.out.println("MyProcessor Received Exchange: " 
				+ exchange.getIn().getBody(String.class) 
				+ ", MIP: "
				+ exchange.getPattern());
	}
}
