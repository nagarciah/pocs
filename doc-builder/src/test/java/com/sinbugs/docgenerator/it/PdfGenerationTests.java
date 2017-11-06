package com.sinbugs.docgenerator.it;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.pocs.docs.DocGeneratorApplication;
import com.pocs.docs.dto.InputRecord;
import com.pocs.docs.dto.InputRecord.InputSource;
import com.pocs.docs.dto.InputRecord.OutputTarget;

@RunWith(SpringRunner.class)
@SpringBootTest(classes=DocGeneratorApplication.class)
public class PdfGenerationTests {

	@Value("${rabbitmq.inbound.queue}")
	String inboundQueueName;
	
	@Autowired
    private RabbitTemplate inboundAmqpTemplate;

	@Test
	public void successCreatingPdf() {
        System.out.println("Sending message...");
        Map<String, Object> fields = new HashMap<>();
        fields.put("Nombre", "Nelson");
        
        InputRecord msg = new InputRecord(
        		InputSource.WS, 
        		OutputTarget.CSV, 
        		"simple.jrxml", 
        		UUID.randomUUID().toString(), 
        		fields);
        
        inboundAmqpTemplate.convertAndSend(inboundQueueName, msg);
    }
}

