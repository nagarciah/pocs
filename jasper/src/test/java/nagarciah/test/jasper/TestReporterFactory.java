package nagarciah.test.jasper;

import java.io.File;
import java.util.List;

import nagarciah.test.jasper.model.DataBean;
import nagarciah.test.jasper.service.DummyDataProducer;
import nagarciah.test.jasper.service.reporting.Reporter;
import nagarciah.test.jasper.service.reporting.Reporter.ExportFormat;
import nagarciah.test.jasper.service.reporting.ReporterFactory;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


/** 
 * Prueba unitaria del generador de reportes por defecto
 * @author Nelson García
 */
public class TestReporterFactory {
	
	private List<DataBean> data;
	private String baseDir;
	private String templateFileName;
	

	@Before
	public void setUp() throws Exception {
		this.data = new DummyDataProducer().getDataBeanList();
		
		File file = new File(getClass().getClassLoader().getResource("reports/report1.jasper").toURI());
		this.templateFileName = file.getAbsolutePath();
		this.baseDir = file.getParentFile().getAbsolutePath() + File.separator;
		System.out.println(templateFileName);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testCreateDefaultReporter() {
		Reporter reporter = ReporterFactory.createDefaultReporter()
			.setData(data)
			.setTemplateFileName(templateFileName);
		
		Assert.assertNotNull(reporter);
	}
	
	@Test
	public void testExportToPDF() {
		String outputFileName = this.baseDir + "export_test.pdf";
		exportToFile(ExportFormat.PDF, outputFileName);
		Assert.assertTrue(new File(outputFileName).exists());
	}
	
	
	@Test
	public void testExportToExcel() {
		String outputFileName = this.baseDir + "export_test.xls";
		exportToFile(ExportFormat.XLS, outputFileName);
		Assert.assertTrue(new File(outputFileName).exists());
	}
	
	@Test
	public void testExportToHtml() {
		String outputFileName = this.baseDir + "export_test.html";
		exportToFile(ExportFormat.HTML, outputFileName);
		Assert.assertTrue(new File(outputFileName).exists());
	}
	
	private void exportToFile(ExportFormat format, String filename) {
		deleteSilently(filename);
		
		ReporterFactory.createDefaultReporter()
			.setData(data)
			.setTemplateFileName(templateFileName)
			.setExportFormat(format)
			.setOutputFileName(filename)
			.export();
		System.out.println(filename);		
	}
	
	
	private void deleteSilently(String fileName) {
		try {
			File file = new File(fileName);
			file.delete();
		}catch(Exception e) {
			System.out.println("Error eliminando archivo (se silenciará el error)");
			e.printStackTrace();
		}
	}

}
