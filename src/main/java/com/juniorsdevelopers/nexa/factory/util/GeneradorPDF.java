package com.juniorsdevelopers.nexa.factory.util;

import com.juniorsdevelopers.nexa.factory.model.Reporte;

import com.itextpdf.text.BaseColor;
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

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class GeneradorPDF {

    public static boolean generarPdf(File archivoDestino, String tipoReporte, List<Reporte> listaReportes) {
        Document documento = new Document();

        try {
            PdfWriter.getInstance(documento, new FileOutputStream(archivoDestino));
            documento.open();

            Font fuenteTitulo = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 18, new BaseColor(13, 44, 84));
            Font fuenteSubtitulo = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 12, BaseColor.GRAY);
            Font fuenteHeaderTabla = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 10, BaseColor.WHITE);
            Font fuenteContenido = FontFactory.getFont(FontFactory.HELVETICA, 9, BaseColor.BLACK);

            Paragraph titulo = new Paragraph("NEXA FACTORY", fuenteTitulo);
            titulo.setAlignment(Element.ALIGN_CENTER);
            documento.add(titulo);

            Paragraph subtitulo = new Paragraph("Reporte de " + tipoReporte, fuenteSubtitulo);
            subtitulo.setAlignment(Element.ALIGN_CENTER);
            subtitulo.setSpacingAfter(10);
            documento.add(subtitulo);

            String fechaActual = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));
            Paragraph fechaGen = new Paragraph("Fecha de emisión: " + fechaActual, fuenteContenido);
            fechaGen.setAlignment(Element.ALIGN_RIGHT);
            fechaGen.setSpacingAfter(15);
            documento.add(fechaGen);

            PdfPTable tabla = new PdfPTable(4);
            tabla.setWidthPercentage(100);
            tabla.setWidths(new float[]{20f, 45f, 15f, 20f});

            String[] encabezados = {"Fecha", "Descripción", "Cantidad", "Estado"};
            for (String enc : encabezados) {
                PdfPCell celda = new PdfPCell(new Phrase(enc, fuenteHeaderTabla));
                celda.setBackgroundColor(new BaseColor(13, 44, 84));
                celda.setHorizontalAlignment(Element.ALIGN_CENTER);
                celda.setPadding(6);
                tabla.addCell(celda);
            }

            if (listaReportes != null && !listaReportes.isEmpty()) {
                for (Reporte rep : listaReportes) {
                    PdfPCell cellFecha = new PdfPCell(new Phrase(rep.getFecha(), fuenteContenido));
                    cellFecha.setHorizontalAlignment(Element.ALIGN_CENTER);
                    cellFecha.setPadding(5);
                    tabla.addCell(cellFecha);

                    PdfPCell cellDesc = new PdfPCell(new Phrase(rep.getDescripcion(), fuenteContenido));
                    cellDesc.setPadding(5);
                    tabla.addCell(cellDesc);

                    PdfPCell cellCant = new PdfPCell(new Phrase(String.valueOf(rep.getCantidad()), fuenteContenido));
                    cellCant.setHorizontalAlignment(Element.ALIGN_CENTER);
                    cellCant.setPadding(5);
                    tabla.addCell(cellCant);

                    PdfPCell cellEstado = new PdfPCell(new Phrase(rep.getEstado(), fuenteContenido));
                    cellEstado.setHorizontalAlignment(Element.ALIGN_CENTER);
                    cellEstado.setPadding(5);
                    tabla.addCell(cellEstado);
                }
            } else {
                PdfPCell celdaVacia = new PdfPCell(new Phrase("No hay registros para mostrar en este reporte.", fuenteContenido));
                celdaVacia.setColspan(4);
                celdaVacia.setHorizontalAlignment(Element.ALIGN_CENTER);
                celdaVacia.setPadding(10);
                tabla.addCell(celdaVacia);
            }

            documento.add(tabla);
            documento.close();
            return true;

        } catch (DocumentException | IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}