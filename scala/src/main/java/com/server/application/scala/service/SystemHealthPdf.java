package com.server.application.scala.service;

import java.io.FileNotFoundException;

import org.springframework.stereotype.Service;

import com.itextpdf.kernel.color.Color;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.Style;
import com.itextpdf.layout.border.RoundDotsBorder;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.List;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.TextAlignment;

@Service
public class SystemHealthPdf {

    public void createPDF() throws FileNotFoundException {

        String dest = "/home/coral/Documents/GitHub/ScalaClient/scala/src/main/resources/static/SystemReport.pdf";
        PdfWriter writer = new PdfWriter(dest);
        PdfDocument pdfDoc = new PdfDocument(writer);
        pdfDoc.addNewPage();

        Document document = new Document(pdfDoc);

        String para = "SERVER SYSTEM HEALTH REPORT";
        Paragraph paragraph = new Paragraph(para);
        document.add(paragraph);

        List lst = new List().setBackgroundColor(Color.LIGHT_GRAY).setMargin(10L);
        lst.add("CPU Load");
        lst.add("Total RAM");
        lst.add("Used Ram");
        lst.add("Free Ram");
        document.add(lst);
        

        float[] pointColumnWidths = { 150F, 150F, 150F, 150F };
        Table table = new Table(pointColumnWidths);
        Cell cellCpuLoad = new Cell().setBackgroundColor(Color.GREEN).setBorder(new RoundDotsBorder(Color.BLACK, 3))
                .setTextAlignment(TextAlignment.CENTER);
        Cell cellTotalRam = new Cell().setBackgroundColor(Color.LIGHT_GRAY)
                .setBorder(new RoundDotsBorder(Color.BLACK, 3))
                .setTextAlignment(TextAlignment.CENTER);
        Cell cellUsedRam = new Cell().setBackgroundColor(Color.PINK).setBorder(new RoundDotsBorder(Color.BLACK, 3))
                .setTextAlignment(TextAlignment.CENTER);
        Cell cellFreeRam = new Cell().setBackgroundColor(Color.YELLOW).setBorder(new RoundDotsBorder(Color.BLACK, 3))
                .setTextAlignment(TextAlignment.CENTER);


        table.addCell(cellCpuLoad.add("CPU Load"));
        table.addCell(cellTotalRam.add("Total Ram"));
        table.addCell(cellUsedRam.add("Used Ram"));
        table.addCell(cellFreeRam.add("Free Ram"));
        document.add(table);

        document.close();
        System.out.println("Successfully");    

    }

}
