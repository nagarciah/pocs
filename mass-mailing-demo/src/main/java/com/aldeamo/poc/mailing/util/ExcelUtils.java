package com.aldeamo.poc.mailing.util;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.aldeamo.poc.mailing.model.EmailProcessingException;
import com.aldeamo.poc.mailing.model.ExcelFileModel;

public class ExcelUtils {
	
	private Log log = LogFactory.getLog(ExcelUtils.class);

	// Se suprime nel warning de obsolescencia porque en la versión 4.0 de Apache POI, desaparecerá
	// el warning (http://stackoverflow.com/questions/39993683/alternative-to-deprecated-getcelltype)
	@SuppressWarnings("deprecation")
	public ExcelFileModel readUploadedFile(File savedFile) throws EmailProcessingException {

		if(!savedFile.exists()){
			throw new EmailProcessingException("El archivo a procesar no existe: " + savedFile.getAbsolutePath());
		}
		
		ExcelFileModel excelContent = new ExcelFileModel();
		excelContent.setHeaders(new ArrayList<String>());
		excelContent.setContent(new ArrayList<>());
		excelContent.setFile(savedFile);

		try(
			FileInputStream inputStream = new FileInputStream(savedFile);
			Workbook workbook = new XSSFWorkbook(inputStream);
		){
			Sheet sheet = workbook.getSheetAt(0);
			Iterator<Row> rowIterator = sheet.iterator();
			boolean isTitleRow = true;

			while (rowIterator.hasNext()) 
			{
				Map<String, Object> rowContent = new HashMap<>();
				Row row = rowIterator.next();
				Iterator<Cell> cellIterator = row.cellIterator();
				while (cellIterator.hasNext()) 
				{
					Object cellContent;
					Cell cell = cellIterator.next();
					switch (cell.getCellType()) {
					case Cell.CELL_TYPE_STRING:
						cellContent = cell.getStringCellValue();
						break;
					case Cell.CELL_TYPE_NUMERIC:
						cellContent = cell.getNumericCellValue();
						break;
					case Cell.CELL_TYPE_BOOLEAN:
						cellContent = cell.getBooleanCellValue();
						break;
					default:
						cellContent = cell.getStringCellValue();
					}
					
					if(isTitleRow){
						excelContent.getHeaders().add(cell.getColumnIndex(), cellContent.toString());
					}else{
						String key = excelContent.getHeaders().get(cell.getColumnIndex()); 
						rowContent.put(key, cellContent);
					}
				}
				
				if(!isTitleRow){
					excelContent.getContent().add(rowContent);
				}else{
					isTitleRow = Boolean.FALSE;
				}
			}
			
			return excelContent;
		}
		catch(Exception e)
		{
			log .error("Error procesando el archivo Excel", e);
			throw new EmailProcessingException("Error procesando el archivo Excel", e);
		}
	}
}
