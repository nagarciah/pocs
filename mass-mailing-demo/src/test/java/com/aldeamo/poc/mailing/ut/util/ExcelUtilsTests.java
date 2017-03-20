package com.aldeamo.poc.mailing.ut.util;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.util.Iterator;
import java.util.Map;

import org.junit.Test;
import org.junit.experimental.categories.Category;

import com.aldeamo.poc.mailing.model.EmailProcessingException;
import com.aldeamo.poc.mailing.model.ExcelFileModel;
import com.aldeamo.poc.mailing.testutil.UnitTestCategory;
import com.aldeamo.poc.mailing.util.ExcelUtils;

@Category(UnitTestCategory.class)
public class ExcelUtilsTests {

	final static String COL_NOMBRE = "nombre";
	final static String COL_APELLIDO = "apellido";
	final static String COL_SALDO = "total";
	final static String COL_EMAIL = "email";
	
	@Test
	public void testReadUploadedFile() throws EmailProcessingException {
		String excelFilePath = "testData.xlsx";
		ClassLoader classLoader = ExcelUtilsTests.class.getClassLoader();
		File file = new File(classLoader.getResource(excelFilePath).getFile()); 
		
		ExcelFileModel fileContent = new ExcelUtils().readUploadedFile(file);
		
		Iterator<Map<String, Object>> rows = fileContent.getContent().iterator();
		while(rows.hasNext()){
			Map<String, Object> row = rows.next();
			System.out.println("-------------- Fila --------------");
			for(String key : row.keySet()){
				System.out.println(key + "=" + row.get(key));
			}
		}
		
		assertEquals(4, fileContent.getHeaders().size());
		assertEquals(COL_NOMBRE, fileContent.getHeaders().get(0));
		assertEquals(COL_APELLIDO, fileContent.getHeaders().get(1));
		assertEquals(COL_SALDO, fileContent.getHeaders().get(2));
		assertEquals(COL_EMAIL, fileContent.getHeaders().get(3));
		
		assertEquals(2, fileContent.getContent().size());
		assertEquals("Nelson", fileContent.getContent().get(0).get(COL_NOMBRE));
		assertEquals("García", fileContent.getContent().get(0).get(COL_APELLIDO));
		assertEquals(150000.0, fileContent.getContent().get(0).get(COL_SALDO));
		assertEquals("nelson.garcia@aldeamo.com", fileContent.getContent().get(0).get(COL_EMAIL));
		
		assertEquals("John", fileContent.getContent().get(1).get(COL_NOMBRE));
		assertEquals("Doe", fileContent.getContent().get(1).get(COL_APELLIDO));
		assertEquals(300000.0, fileContent.getContent().get(1).get(COL_SALDO));
		assertEquals("nagarciah@yahoo.com", fileContent.getContent().get(1).get(COL_EMAIL));
		
		// Prueba getCell
		assertEquals("Nelson", fileContent.getCell(0, COL_NOMBRE));
		assertEquals("García", fileContent.getCell(0, COL_APELLIDO));
		assertEquals(150000.0, fileContent.getCell(0, COL_SALDO));
		assertEquals("nelson.garcia@aldeamo.com", fileContent.getCell(0, COL_EMAIL));
		
		assertEquals("John", fileContent.getCell(1, COL_NOMBRE));
		assertEquals("Doe", fileContent.getCell(1, COL_APELLIDO));
		assertEquals(300000.0, fileContent.getCell(1, COL_SALDO));
		assertEquals("nagarciah@yahoo.com", fileContent.getCell(1, COL_EMAIL));
	}

}
