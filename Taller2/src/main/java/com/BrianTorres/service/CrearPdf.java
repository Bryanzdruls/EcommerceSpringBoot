package com.BrianTorres.service;

import java.io.FileNotFoundException;
import java.util.List;

import org.springframework.stereotype.Component;

import com.BrianTorres.model.Carrito;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import jakarta.servlet.http.HttpServletResponse;

@Component
public class CrearPdf {
    public void generatePdf(String text, List<Carrito> detalle, HttpServletResponse response) throws DocumentException, FileNotFoundException {
         
 
        try {
            Document document = new Document();
            PdfWriter.getInstance(document, response.getOutputStream());
            document.open();
            //titulo
            Font titleFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 18, Font.UNDERLINE,BaseColor.RED);
            Paragraph title = new Paragraph("FACTURA", titleFont);
            title.setAlignment(Element.ALIGN_CENTER);
            document.add(title);

            // Añadir texto con color
            Font textFont = FontFactory.getFont(FontFactory.HELVETICA, 12, BaseColor.RED);
            document.add(Chunk.NEWLINE);
            //Celdas
            // Añadir tabla
            PdfPTable table = new PdfPTable(3); // Número de columnas
            table.setWidthPercentage(100); // Ancho de la tabla (% del ancho disponible)

            // Encabezados de columna
            PdfPCell cell1 = new PdfPCell(new Phrase("Nombre", textFont));
            PdfPCell cell2 = new PdfPCell(new Phrase("Descripcion", textFont));
            PdfPCell cell3 = new PdfPCell(new Phrase("Cantidad", textFont));
            table.addCell(cell1);
            table.addCell(cell2);
            table.addCell(cell3);
            

            for (Carrito carrito : detalle) {
                table.addCell(carrito.getNombreProducto());
                table.addCell(carrito.getProducto().getDescripcion());
                table.addCell(carrito.getCantidad().toString());
            }
            document.add(table);
            //document.add(new Paragraph(text));
            document.close();
        } catch (Exception e) {
            System.out.println("error en pdf: "+e);
        }
        
    }
}
