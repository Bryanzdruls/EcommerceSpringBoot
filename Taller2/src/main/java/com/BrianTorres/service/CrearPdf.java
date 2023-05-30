package com.BrianTorres.service;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import org.springframework.stereotype.Component;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

@Component
public class CrearPdf {
    public void generatePdf(String text, String filePath) throws DocumentException, FileNotFoundException {
        try {
            Document document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream(filePath));
            document.open();
            document.add(new Paragraph(text));
            document.close();
        } catch (Exception e) {
            System.out.println("error en pdf: "+e);
        }
        
    }
}
