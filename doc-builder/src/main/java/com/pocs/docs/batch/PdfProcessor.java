package com.pocs.docs.batch;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.pocs.docs.service.PdfGenerator;

@Component
public class PdfProcessor implements ItemProcessor<Map<String, Object>, Map<String, Object>> {
	
	private final static Log log = LogFactory.getLog(PdfProcessor.class);

	@Autowired
	PdfGenerator pdfService;
	
	@Value("${batch.output.resultDir}")
	protected File outputDir;
	
	@Override
	public Map<String, Object> process(Map<String, Object> item) throws Exception {
		checkDir(); // TODO Mover esta linea a un inicializador o constructor que se ejecute una sola vez
		
		pdfService.createReport("simple.jrxml", null, item, (String)item.get("outFileName"));
		
		return item;
	}
	
	private void checkDir() throws IOException{
		if(!outputDir.exists()){
    		log.warn("El directorio de archivos de resultados no existe. Se intentara crearlo..." + outputDir.getAbsolutePath());
    		FileUtils.forceMkdir(outputDir);
    		log.info("Directorio de archivos de resultados creado: " + outputDir.getAbsolutePath());
    	}
	}
}
