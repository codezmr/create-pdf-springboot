package app.pdf.service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import org.xhtmlrenderer.pdf.ITextRenderer;

import com.itextpdf.text.DocumentException;

public class HtmlToPdfService {
	
	public static ByteArrayOutputStream createPdfItext(String html) {
		
		ITextRenderer renderer = new ITextRenderer();
		renderer.setDocumentFromString(html);
		renderer.layout();
		
		ByteArrayOutputStream fs = new ByteArrayOutputStream();
		try {
			renderer.createPDF(fs);
		} catch (DocumentException e) {
			
			e.printStackTrace();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
		return fs;
	}
}
