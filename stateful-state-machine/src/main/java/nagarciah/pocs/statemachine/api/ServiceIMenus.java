/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nagarciah.pocs.statemachine.api;

import org.apache.camel.CamelContext;
import org.apache.camel.ExchangePattern;
import org.apache.camel.ProducerTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import nagarciah.pocs.statemachine.service.SessionManager;

/**
 * REST Web Service
 *
 * @author nagarciah
 */


@RestController
@RequestMapping("/statemachine/")
public class ServiceIMenus {
	
	@Autowired
	SessionManager sessionManager;
	
	@Autowired
	CamelContext camelContext;
	
	@Autowired
	private ProducerTemplate producerTemplate;
	
	

    @RequestMapping(value="/generic", method=RequestMethod.GET, produces="text/html")
    public String getHtml(
    		@RequestParam("idStep") Integer idStep, 
    		@RequestParam("idFlow") Integer idFlow,
    		@RequestParam("idSession") String idSession, 
    		@RequestParam("shortCode") String shortCode,
    		@RequestParam("provider") String provider) throws Exception {

    	/**
    	 * Docs: https://examples.javacodegeeks.com/enterprise-java/apache-camel/apache-camel-exchange-example/
    	 */
    	Object resp = producerTemplate.sendBody("direct:myFlowManager", ExchangePattern.InOut, idSession);
    	
    	return "<h1>Respuesta: " + resp.toString() + "</h1>";
    }
}
