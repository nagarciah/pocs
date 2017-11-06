package com.sinbugs.docgenerator.it;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

import com.pocs.docs.dto.InputRecord;
import com.pocs.docs.dto.InputRecord.InputSource;
import com.pocs.docs.dto.InputRecord.OutputTarget;

public class MessageSender {
	static String inboundQueueName = "inQ";
	
    private static RabbitTemplate inboundAmqpTemplate;

	public static void main(String[] args) {
		createTemplate();
		
		for(int i=0;i<1;i++){
	        System.out.println("Sending message...");
	        Map<String, Object> fields = new HashMap<>();
	        fields.put("Nombre", "Rosa Evelia");
	        
	        InputRecord msg = new InputRecord(
	        		InputSource.WS, 
	        		OutputTarget.CSV, 
	        		"simple.jrxml", 
	        		UUID.randomUUID().toString(), 
	        		fields);
	        
	        inboundAmqpTemplate.convertAndSend(inboundQueueName, msg);
		}
    }
	
	private static void createTemplate(){
		CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
		connectionFactory.setHost("localhost");
		connectionFactory.setPort(5672);
		
		inboundAmqpTemplate = new RabbitTemplate(connectionFactory);
	}
}
