package app.pdf.service;

import java.io.ByteArrayOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

@Service
public class PdfGeneratorService {
	
	@Autowired
	SpringTemplateEngine springTemplateEngine;
	
	public ByteArrayOutputStream createPdf(String title) {
		
		return HtmlToPdfService.createPdfItext(renderHtmlForPdf(title), true);
	}
	
	
	private String renderHtmlForPdf(String title) {
		
		Context context = new Context();
		context.setVariable("title", title);
		context.setVariable("image1", ImageUtils.convertImageToBase64("/pdf/images/image1.png"));
		//context.setVariable("barcode1", BarCodeService.getBarCodeAsBase64("Invoice-12354287"));
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.YY HH:mm:ss");
		context.setVariable("today", dateFormat.format(new Date()));
		
		return springTemplateEngine.process("pdf/confirmation.html", context);
	}
}


























