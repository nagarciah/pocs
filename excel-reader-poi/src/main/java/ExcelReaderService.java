import static com.codeborne.selenide.Selenide.$;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.openqa.selenium.By;

/**
 * HSSF is the POI Project’s pure Java implementation of the Excel ’97(-2007)
 * file format. e.g. HSSFWorkbook, HSSFSheet. XSSF is the POI Project’s pure
 * Java implementation of the Excel 2007 OOXML (.xlsx) file format. e.g.
 * XSSFWorkbook, XSSFSheet.
 * 
 * @author nelson
 *
 */
public class ExcelReaderService {
	public void generateFiles(String filename) {
		try {
			List<MyRow> rows = new ExcelReaderService().readFile(filename);
			
			String url = "http://192.95.23.219/hitodiciembre2015/crear_imagen.php?imagen={IMAGEN}&texto={TEXTO}";
			String file = "http://192.95.23.219/FilesPng/{ARCHIVO}";
			
			for(MyRow r : rows){
				String open = url.replace("{IMAGEN}", r.getImage()).replace("{TEXTO}", URLEncoder.encode(r.getText(), "UTF-8")); 
				$( open );
				r.setFile( $(By.id("fileName")).text() );
				$(By.id("ButtonClickImgNext")).click();
				
				System.out.println(r);
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public List<MyRow> readFile(String filename) throws IOException {
		List<MyRow> fileContent = new ArrayList<MyRow>();

		FileInputStream file = new FileInputStream(new File(filename));

		// Create Workbook instance holding reference to .xlsx file
		Workbook workbook = new HSSFWorkbook(file);

		// Get first/desired sheet from the workbook
		Sheet sheet = workbook.getSheetAt(0);

		Iterator<Row> rowIterator = sheet.iterator();
		while (rowIterator.hasNext()) {
			Row row = rowIterator.next();
			
			fileContent.add(new MyRow(
					getValueAsString(row.getCell(0)), //image 
					getValueAsString(row.getCell(1))  //text
				));
		}
		
		file.close();
		
		return fileContent;
	}
	
	private String getValueAsString(Cell cell){
		switch (cell.getCellType()) {
		case Cell.CELL_TYPE_NUMERIC:
			return String.valueOf( cell.getNumericCellValue() );
		case Cell.CELL_TYPE_STRING:
			return cell.getStringCellValue();
		default:
			return String.valueOf( cell.getNumericCellValue() );
		}
	}
	
	public void createResultsFile(List<MyRow> data, String filename) throws IOException{
		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFSheet sheet = workbook.createSheet("Resultados");

		for(MyRow r : data){
			Row row = sheet.createRow(0);
			
			row.createCell(0).setCellValue(r.getImage());
			row.createCell(1).setCellValue(r.getText());
			row.createCell(2).setCellValue(r.getFile());
		}
		
	    FileOutputStream out = new FileOutputStream(new File(filename));
	    workbook.write(out);
	    out.close();
	}
}
