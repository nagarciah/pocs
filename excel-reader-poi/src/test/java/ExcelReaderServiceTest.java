import static com.codeborne.selenide.Selenide.*;
import static org.junit.Assert.*;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;

import org.junit.Test;
import org.openqa.selenium.By;

public class ExcelReaderServiceTest {

	//@Test
	public void readFile() throws IOException {
		List<MyRow> rows = new ExcelReaderService().readFile("/tmp/test.xls");
		
		for(MyRow r : rows){
			System.out.println(r);
		}
	}
	
	@Test
	public void generateFiles() throws IOException {
		ExcelReaderService excel = new ExcelReaderService();
		String filename = "/tmp/hito.xls";
		
		List<MyRow> rows = excel.readFile(filename);
		
		String url = "http://192.95.23.219/hitodiciembre2015/crear_imagen.php?imagen={IMAGEN}&texto={TEXTO}";
		String file = "http://192.95.23.219/FilesPng/{ARCHIVO}";
		
		for(MyRow r : rows){
			String tmpURL = url.replace("{IMAGEN}", r.getImage()).replace("{TEXTO}", URLEncoder.encode(r.getText(), "UTF-8"));

			open(tmpURL);
			r.setFile( $(By.id("fileName")).text() );
			$(By.id("ButtonClickImgNext")).click();
			
			// Guardar en archivo
			System.out.println(r);
			
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		excel.createResultsFile(rows, filename + ".result.xls");
	}
}
