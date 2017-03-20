import java.io.FileOutputStream;
import java.io.IOException;

import org.junit.Test;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;
import com.itextpdf.text.pdf.PdfWriter;

public class ProtectPdf {

	/** User password. */
	public static byte[] USER = "Hello".getBytes();
	/** Owner password. */
	public static byte[] OWNER = "World".getBytes();

	@Test
	public void test() throws IOException, DocumentException {
		String src = "/home/nelson2/Documentos/Aldeamo/Email-Aida/sistemcobro-2/reporte.pdf";
		
		String dest = "/home/nelson2/Documentos/Aldeamo/Email-Aida/sistemcobro-2/reporte-protected.pdf";
		
		encryptPdf(src, dest);
	}

	public void encryptPdf(String src, String dest) throws IOException, DocumentException {
		PdfReader reader = new PdfReader(src);
		PdfStamper stamper = new PdfStamper(reader, new FileOutputStream(dest));
		stamper.setEncryption(USER, OWNER, PdfWriter.ALLOW_PRINTING,
				PdfWriter.ENCRYPTION_AES_128 | PdfWriter.DO_NOT_ENCRYPT_METADATA);
		stamper.close();
		reader.close();
	}

}
