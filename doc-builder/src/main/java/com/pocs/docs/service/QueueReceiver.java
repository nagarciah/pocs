package com.pocs.docs.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.pocs.docs.batch.PdfProcessor;
import com.pocs.docs.dto.InputRecord;

@Component
public class QueueReceiver {
	
	@Autowired
	PdfProcessor pdfProcessor;

	public void receiveMessage(InputRecord message) throws Exception {
		System.out.println("Received <" + message + ">");
		pdfProcessor.process(message);
	}
}
