package com.pocs.docs.service;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.pocs.docs.dto.ReportInfo;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRMapCollectionDataSource;

@Service
public class PdfGenerator {
	
	private final static Log log = LogFactory.getLog(PdfGenerator.class);
	
	@Value("${parameter.temp.dir}")
	String tempDir;
	
	@Value("${parameter.reports.dir}")
	String reportDir;

	@Value("${parameter.reports.mediaType}")
	String reportMediaType;
	
	
	public ReportInfo createReport(String jrxmlFilename, Map<String, Object> parameters, Map<String, Object> record, String outFileName) throws JRException {
		
		File jrxmlFile = new File(reportDir, jrxmlFilename);

		
		// Asegura que exista el directorio de salida
		File outDir = new File(tempDir);
		outDir.mkdirs();

		String outputFilename = appendFileSeparator(outDir.getAbsolutePath()) + outFileName; //jrxmlFilename.replace(".jrxml", ".pdf");

		
		// FIXME Compila s[olo si no existe ya el compilado jasper
		//File jasperFile = new File(reportDir, jrxmlFilename.replace(".jrxml", ".jasper"));
		//if(!jasperFile.exists()){
			JasperReport jasperReport = JasperCompileManager
					.compileReport( jrxmlFile.getAbsolutePath() );
		//}
		
		// Llena los datos del reporte
		
		Collection<Map<String, ?>> wrappedRecord = new ArrayList<>();
		wrappedRecord.add(record);
		
		JRDataSource mapDataSource = new JRMapCollectionDataSource(wrappedRecord);
		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, mapDataSource);
		
		// Exporta a PDF
		JasperExportManager.exportReportToPdfFile(jasperPrint, outputFilename);
		log.info("Reporte generado en '" + outputFilename + "'");
		
		return new ReportInfo(new File(outputFilename), reportMediaType);

	}
	
	
	private String appendFileSeparator(String path){
		if(!path.endsWith(File.separator)){
			path += File.separator;
		}
		
		return path;
	}
}
