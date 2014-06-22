/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IPDGES;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Gustavo Salvo Lara
 */
public class ConcentForm extends HttpServlet {
    
    private String institution = "Hospital Barros Luco";
    private String address = "Calle tanto numero tanto";
    private String city = "Santiago";
    private String personName = "Nombre de la persona que notifica";
    private Integer rut = 17409487;
    
    private String patientName = "Bernarda";
    private Integer patientRut = 6972769;
    private String patientFonasa = "Fonasa del paciente";
    private String patientIsapre = "Isapre del paciente";
    private String home = "Casa tanto bla bla";
    private String commune = "La florida";
    private String region = "Metropolitana";
    private String phoneNumber = "2972493";
    private String celularNumber = "86652272";
    private String mail = "proyectista@hotmail.com";
    
    private String ges = "true";
    

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/pdf");

        try {
            //style
            Font typeBold = new Font(Font.FontFamily.TIMES_ROMAN, 12, 1);
            Font type = new Font(Font.FontFamily.TIMES_ROMAN, 10);
            Font title = new Font(Font.FontFamily.TIMES_ROMAN, 15, 1);
            Font subTitle = new Font(Font.FontFamily.TIMES_ROMAN, 13, 1);
            Font subTitle2 = new Font(Font.FontFamily.TIMES_ROMAN, 10, 1);
            float space = (float) 20;

            Document document = new Document();
            document.setPageSize(PageSize.LETTER);
            PdfWriter.getInstance(document, response.getOutputStream());
            document.open();

            //General
            PdfPTable table;
            Paragraph p1;
            Paragraph p2;
            PdfPCell cellRow1;
            PdfPCell cellRow2;

            //row: 1
            table = new PdfPTable(2);
            table.setWidthPercentage(100);
            p1 = new Paragraph(space, "FORMULARIO DE CONSTANCIA DE INFORMACION AL PACIENTE GES", title);
            p1.setAlignment(Element.ALIGN_CENTER);
            cellRow1 = new PdfPCell(p1);
            cellRow1.setColspan(2);
            formatCellBorder(cellRow1, 20);
            table.addCell(cellRow1);
            p1 = new Paragraph(space, "(Artículo 24°, Ley 19.966)", subTitle2);
            p1.setAlignment(Element.ALIGN_CENTER);
            cellRow1 = new PdfPCell(p1);
            cellRow1.setColspan(2);
            formatCellBorder(cellRow1, 20);
            table.addCell(cellRow1);
            document.add(table);
            document.add(new Paragraph("\n"));
            
            //row: 2
            table = new PdfPTable(2);
            table.setWidthPercentage(100);
            p1 = new Paragraph(space, "DATOS DEL PRESTADOR", subTitle);
            cellRow1 = new PdfPCell(p1);
            cellRow1.setColspan(2);
            formatCellBorder(cellRow1, 20);
            cellRow1.setBorderWidthTop(1);
            cellRow1.setBorderWidthLeft(1);
            cellRow1.setBorderWidthRight(1);
            cellRow1.setBorderWidthBottom(1);
            table.addCell(cellRow1);
            p1 = new Paragraph(space, "INSTITUCIÓN (Hospital, Clínica, Consultorio,etc): " + institution, type);
            cellRow1 = new PdfPCell(p1);
            cellRow1.setColspan(2);
            formatCellBorder(cellRow1, 20);
            cellRow1.setBorderWidthLeft(1);
            cellRow1.setBorderWidthRight(1);
            table.addCell(cellRow1);
            p1 = new Paragraph(space, "DIRECCION: " + address, type);
            p2 = new Paragraph(space, "CIUDAD: " + city, type);
            cellRow1 = new PdfPCell(p1);
            cellRow2 = new PdfPCell(p2);
            formatCellBorder(cellRow1, 20);
            formatCellBorder(cellRow2, 20);
            cellRow1.setBorderWidthLeft(1);
            cellRow2.setBorderWidthRight(1);
            table.addCell(cellRow1);
            table.addCell(cellRow2);
            p1 = new Paragraph(space, "NOMBRE PERSONA QUE NOTIFICA: " + personName, type);
            p2 = new Paragraph(space, "RUT: " + rut, type);
            cellRow1 = new PdfPCell(p1);
            cellRow2 = new PdfPCell(p2);
            formatCellBorder(cellRow1, 20);
            formatCellBorder(cellRow2, 20);
            cellRow1.setBorderWidthLeft(1);
            cellRow2.setBorderWidthRight(1);
            cellRow1.setBorderWidthBottom(1);
            cellRow2.setBorderWidthBottom(1);
            table.addCell(cellRow1);
            table.addCell(cellRow2);
            document.add(table);
            document.add(new Paragraph("\n"));

            //row 3
            table = new PdfPTable(2);
            table.setWidthPercentage(100);
            p1 = new Paragraph(space, "ANTECEDENTES DEL PACIENTE", subTitle);
            cellRow1 = new PdfPCell(p1);
            cellRow1.setColspan(2);
            formatCellBorder(cellRow1, 20);
            cellRow1.setBorderWidthTop(1);
            cellRow1.setBorderWidthLeft(1);
            cellRow1.setBorderWidthRight(1);
            cellRow1.setBorderWidthBottom(1);
            table.addCell(cellRow1);
            p1 = new Paragraph(space, "NOMBRE: " + patientName, type);
            cellRow1 = new PdfPCell(p1);
            cellRow1.setColspan(2);
            formatCellBorder(cellRow1, 20);
            cellRow1.setBorderWidthLeft(1);
            cellRow1.setBorderWidthRight(1);
            table.addCell(cellRow1);
            p1 = new Paragraph(space, "RUT: " + patientRut, type);
            p2 = new Paragraph(space, "FONASA: " + patientFonasa, type);
            cellRow1 = new PdfPCell(p1);
            cellRow2 = new PdfPCell(p2);
            formatCellBorder(cellRow1, 20);
            formatCellBorder(cellRow2, 20);
            cellRow1.setBorderWidthLeft(1);
            cellRow2.setBorderWidthRight(1);
            table.addCell(cellRow1);
            table.addCell(cellRow2);
            p1 = new Paragraph(space, "ISAPRE: " + patientIsapre, type);
            p2 = new Paragraph(space, "DOMICILIO: " + home, type);
            cellRow1 = new PdfPCell(p1);
            cellRow2 = new PdfPCell(p2);
            formatCellBorder(cellRow1, 20);
            formatCellBorder(cellRow2, 20);
            cellRow1.setBorderWidthLeft(1);
            cellRow2.setBorderWidthRight(1);            
            table.addCell(cellRow1);
            table.addCell(cellRow2);
            p1 = new Paragraph(space, "COMUNA: " + commune, type);
            p2 = new Paragraph(space, "REGION: " + region, type);
            cellRow1 = new PdfPCell(p1);
            cellRow2 = new PdfPCell(p2);
            formatCellBorder(cellRow1, 20);
            formatCellBorder(cellRow2, 20);
            cellRow1.setBorderWidthLeft(1);
            cellRow2.setBorderWidthRight(1);            
            table.addCell(cellRow1);
            table.addCell(cellRow2);
            p1 = new Paragraph(space, "N° TELÉFONO FIJO: " + phoneNumber, type);
            p2 = new Paragraph(space, "N° TELÉFONO CELULAR: " + celularNumber, type);
            cellRow1 = new PdfPCell(p1);
            cellRow2 = new PdfPCell(p2);
            formatCellBorder(cellRow1, 20);
            formatCellBorder(cellRow2, 20);
            cellRow1.setBorderWidthLeft(1);
            cellRow2.setBorderWidthRight(1);            
            table.addCell(cellRow1);
            table.addCell(cellRow2);
            p1 = new Paragraph(space, "DIRECCIÓN CORREO ELECTRÓNICO (E-MAIL): " + mail, type);
            cellRow1 = new PdfPCell(p1);
            cellRow1.setColspan(2);
            formatCellBorder(cellRow1, 20);
            cellRow1.setBorderWidthLeft(1);
            cellRow1.setBorderWidthRight(1);
            cellRow1.setBorderWidthBottom(1);
            
            table.addCell(cellRow1);
            document.add(table);
            document.add(new Paragraph("\n"));
            
            //row 4
            table = new PdfPTable(2);
            table.setWidthPercentage(100);
            p1 = new Paragraph(space, "INFORMACIÓN MÉDICA", subTitle);
            cellRow1 = new PdfPCell(p1);
            cellRow1.setColspan(2);
            formatCellBorder(cellRow1, 20);
            cellRow1.setBorderWidthTop(1);
            cellRow1.setBorderWidthLeft(1);
            cellRow1.setBorderWidthRight(1);
            cellRow1.setBorderWidthBottom(1);
            table.addCell(cellRow1);
            p1 = new Paragraph(space, "CONFIRMACIÓN DIAGNÓSTICA GES:", type);
            cellRow1 = new PdfPCell(p1);
            cellRow1.setColspan(2);
            formatCellBorder(cellRow1, 20);
            cellRow1.setBorderWidthLeft(1);
            cellRow1.setBorderWidthRight(1);
            table.addCell(cellRow1);
            p1 = new Paragraph(space, ges, type);
            cellRow1 = new PdfPCell(p1);
            cellRow1.setColspan(2);
            formatCellBorder(cellRow1, 20);
            cellRow1.setBorderWidthLeft(1);
            cellRow1.setBorderWidthRight(1);
            table.addCell(cellRow1);            
            p1 = new Paragraph(space, "(  ) Confirmación Diagnóstica", type);
            p2 = new Paragraph(space, "(  ) Paciente en Tratamiento", type);
            cellRow1 = new PdfPCell(p1);
            cellRow2 = new PdfPCell(p2);
            formatCellBorder(cellRow1, 20);
            formatCellBorder(cellRow2, 20);
            cellRow1.setBorderWidthLeft(1);
            cellRow2.setBorderWidthRight(1);
            cellRow1.setBorderWidthBottom(1);
            cellRow2.setBorderWidthBottom(1);
            table.addCell(cellRow1);
            table.addCell(cellRow2);            
            document.add(table);
            //row 5
            document.add(new Paragraph(space, "CONSTANCIA", subTitle));
            Paragraph text1 = new Paragraph(space, "Declaro que, con esta fecha y hora, he tomado conocimiento que tengo derecho a acceder a las Garantías Explícitas en Salud, siempre que la atención sea otorgada en la red de Prestadores que me corresponde según Fonasa o Isapre, a la que me encuentro adscrito", type);
            text1.setAlignment(Element.ALIGN_JUSTIFIED);
            document.add(text1);
            document.add(new Paragraph(space, "IMPORTANTE", subTitle));
            Paragraph text2 = new Paragraph(space,"Tenga presente que sí no se cumplen las garantías usted puede reclamar ante Fonasa o la Isapre, según corresponda. Si la respuesta no es satisfactoria, usted puede recurrir en segunda instancia a la Superintendencia de Salud.", type);
            text2.setAlignment(Element.ALIGN_JUSTIFIED);
            document.add(text2);            
            document.add(new Paragraph(space, "FECHA Y HORA DE NOTIFICACIÓN: " +"-" , subTitle));
            document.add(new Paragraph(space, "\n", type));
            document.add(new Paragraph(space, "\n", type));
            document.add(new Paragraph(space, "\n", type));
            //row 6
            table = new PdfPTable(2);
            table.setWidthPercentage(100);
            p1 = new Paragraph(space, "INFORMÉ DIAGNÓSTICO GES", subTitle);
            p2 = new Paragraph(space, "TOMÉ CONOCIMIENTO", subTitle);
            cellRow1 = new PdfPCell(p1);
            cellRow2 = new PdfPCell(p2);
            formatCellBorder(cellRow1, 20);
            formatCellBorder(cellRow2, 20);
            table.addCell(cellRow1);
            table.addCell(cellRow2);            
            p1 = new Paragraph(space, "(Firma de persona que notifica)", subTitle2);
            p2 = new Paragraph(space, "(Firma o huella digital ó representante)", subTitle2);
            cellRow1 = new PdfPCell(p1);
            cellRow2 = new PdfPCell(p2);
            formatCellBorder(cellRow1, 20);
            formatCellBorder(cellRow2, 20);
            table.addCell(cellRow1);
            table.addCell(cellRow2); 
            document.add(table);            
            //row 7
            document.add(new Paragraph(space, "En caso que la persona que tomó conocimiento no sea el paciente, identificar.", type));
            
            //row 8
            table = new PdfPTable(2);
            table.setWidthPercentage(100);
            p1 = new Paragraph(space, "Nombre:", type);
            p2 = new Paragraph(space, "R.U.T:", type);
            cellRow1 = new PdfPCell(p1);
            cellRow2 = new PdfPCell(p2);
            formatCellBorder(cellRow1, 20);
            formatCellBorder(cellRow2, 20);
            table.addCell(cellRow1);
            table.addCell(cellRow2);            
            p1 = new Paragraph(space, "N° Teléfono Celular:", type);
            p2 = new Paragraph(space, "Dirección correo electrónico (e-mail):", type);
            cellRow1 = new PdfPCell(p1);
            cellRow2 = new PdfPCell(p2);
            formatCellBorder(cellRow1, 20);
            formatCellBorder(cellRow2, 20);
            table.addCell(cellRow1);
            table.addCell(cellRow2); 
            document.add(table);
            
            
            document.close();
        } catch (DocumentException de) {
            throw new IOException(de.getMessage());
        }

    }

    private void formatCellBorder(PdfPCell cell, int h) {
        cell.setFixedHeight(h);
        cell.setBorder(0);
    }

}
