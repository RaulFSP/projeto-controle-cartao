package io.github.app.service;


import java.io.OutputStream;

import org.openpdf.text.Document;
import org.openpdf.text.PageSize;
import org.openpdf.text.Paragraph;
import org.openpdf.text.pdf.PdfWriter;
import org.springframework.stereotype.Service;

@Service
public class PdfGenerator {

	public void export(OutputStream output) {
		
		Document document = new Document(PageSize.A4);
		PdfWriter.getInstance(document, output);
		document.open();
		Paragraph p = new Paragraph("Batatinha");
		document.add(p);
		document.close();
	}
}
