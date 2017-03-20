package nagarciah.test.jasper.service.reporting.impl;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nagarciah.test.jasper.service.reporting.Reporter;
import nagarciah.test.jasper.service.reporting.ReporterException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRXlsExporter;

/**
 * Proveedor de reportes que utiliza Jasper Reports como librería de generación de reportes. 
 * 
 * @author Nelson García
 */
public class JasperReporter implements Reporter {
	protected List<? extends Object> data;
	protected ExportFormat format;
	protected String outputFileName;
	protected String templateFileName;
	protected HashMap<String, Object> reporterParams = new HashMap<String, Object>();

	public Reporter addParam(String key, Object value) {
		reporterParams.put(key, value);
		return this;
	}
	
	public Map<String, Object> getParamsMap() {
		return Collections.synchronizedMap( this.reporterParams );
	}

	public Reporter setData(List<? extends Object> data) {
		this.data = data;
		return this;
	}

	/**
	 * La plantilla es una reporte de JasperReports compilado (*.jasper).
	 * TODO Implementar la compilación para recibir archivos *.jrxml
	 */
	public Reporter setTemplateFileName(String templateFileName) {
		this.templateFileName = templateFileName;
		return this;
	}
	
	/** TODO Recibir también OutputStream */
	public Reporter setOutputFileName(String outputFileName) {
		this.outputFileName = outputFileName;
		return this;
	}

	public Reporter setExportFormat(ExportFormat format) {
		this.format = format;
		return this;
	}

	public void export() {
		this.export(this.data, this.format, this.outputFileName, this.templateFileName);
	}

	public void export(ExportFormat format, String outputFileName) {
		this.export(this.data, format, outputFileName, this.templateFileName);
	}

	public void export(List<? extends Object> data, ExportFormat format, String outputFileName) {
		this.export(data, format, outputFileName, this.templateFileName);
	}

	public void export(List<? extends Object> data, ExportFormat format, String outputFileName, String templateFileName) {
		String printFileName = null;

		JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(data);

		try {
			printFileName = JasperFillManager.fillReportToFile(templateFileName, this.getParamsMap(), beanColDataSource);
			if (printFileName != null) {
				
				switch(format) {
				case PDF:
					JasperExportManager.exportReportToPdfFile(printFileName, outputFileName);
					break;
				
				case HTML:
					JasperExportManager.exportReportToHtmlFile(printFileName, outputFileName);
					break;
					
				case XLS:
					JRXlsExporter exporter = new JRXlsExporter();
	
					exporter.setParameter(JRExporterParameter.INPUT_FILE_NAME, printFileName);
					exporter.setParameter(JRExporterParameter.OUTPUT_FILE_NAME, outputFileName);
	
					exporter.exportReport();
					break;
					
				default:
					throw new ReporterException("Formato de salida no oportado: " + format);
				}
			}else {
				throw new ReporterException("No existe el archivo intermedio (print file) requerido para exportar el reporte");
			}
		} catch (Exception e) {
			throw new ReporterException("Error exportando reporte", e);
		}		
	}
}
