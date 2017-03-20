/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nagarciah.pocs.statemachine.service;

import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author fardul
 */
@Service
public class ManagerFlowBuilder extends RouteBuilder {

    final static Logger logger = Logger.getLogger(ManagerFlowBuilder.class.getName());
    
    @Autowired
    MyProcessor myProcessor;
    

    @Override
    public void configure() throws Exception {
        from("direct:myFlowManager").process(new Processor() {
            public void process(Exchange exchange) {
                Message in = exchange.getIn();
                in.setBody(in.getBody(String.class) + " World!");
                System.out.println("mensaje de camel: " + in.getBody(String.class));
            }
        })
        .process(myProcessor)
        .to("mock:result");
    }

}
