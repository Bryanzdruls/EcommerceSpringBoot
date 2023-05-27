package com.BrianTorres.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.view.document.AbstractPdfView;

import com.BrianTorres.model.Carrito;
import com.lowagie.text.Document;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component("/pedido")
public class GeneradorPdf extends AbstractPdfView{

    @Override
    @RequestMapping(value = "/pdfFile", method = RequestMethod.POST, produces = {"application/pdf"})
    public void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter writer,
            HttpServletRequest request, HttpServletResponse response) throws Exception {
        @SuppressWarnings("unchecked")
        List<Carrito> carrito = (List<Carrito>) model.get("carrito");

        PdfPTable tablaFactura= new PdfPTable(4);
        carrito.forEach(detalle ->{
            tablaFactura.addCell(detalle.getNombreProducto());
            tablaFactura.addCell(detalle.getCantidad().toString());
            tablaFactura.addCell(detalle.getValorPedido().toString());
            tablaFactura.addCell(detalle.getTotalPorPedido().toString());
        });
        document.add(tablaFactura);
    }
    
}
