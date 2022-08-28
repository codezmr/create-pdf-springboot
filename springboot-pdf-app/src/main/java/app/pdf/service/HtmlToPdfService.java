package app.pdf.service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import org.xhtmlrenderer.pdf.ITextRenderer;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;
import com.itextpdf.text.pdf.PdfWriter;

public class HtmlToPdfService {

	public static ByteArrayOutputStream createPdfItext(String html, boolean secure) {

		ITextRenderer renderer = new ITextRenderer();
		renderer.setDocumentFromString(html);
		renderer.layout();

		ByteArrayOutputStream fs = new ByteArrayOutputStream();
		try {
			renderer.createPDF(fs);

			PdfReader reader = new PdfReader(fs.toByteArray());
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			PdfStamper stamper = new PdfStamper(reader, out);

			if (secure == true) {

				stamper.setEncryption("password".getBytes(), null, PdfWriter.ALLOW_PRINTING, PdfWriter.STANDARD_ENCRYPTION_128);

			} else {
				stamper.setEncryption(null, null, PdfWriter.ALLOW_PRINTING | PdfWriter.ALLOW_MODIFY_CONTENTS,
						PdfWriter.STANDARD_ENCRYPTION_128);
			}
			stamper.close();

			return out;

		} catch (DocumentException e) {

			e.printStackTrace();
		} catch (IOException e) {

			e.printStackTrace();
		}

		return fs;
	}
}
