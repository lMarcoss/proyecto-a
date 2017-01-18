/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador.generadorticket;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author rcortes
 */
public class generadorpdfticket extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");// Forzar a usar codificación UTF-8 iso-8859-1        
        String Encabezado=(request.getParameter("encabezado"));
        String Encabezado1=(request.getParameter("encabezado1"));
        String Encabezado2=(request.getParameter("encabezado2"));
        String Tipo[]=request.getParameterValues("tipo");
        
        String Fecha=(request.getParameter("Fecha"));
        String Municipio=(request.getParameter("Municipio"));
        String Cliente=(request.getParameter("Cliente"));
        String Estado=(request.getParameter("Estado"));
        String Direccion=(request.getParameter("Direccion"));
        String Localidad=(request.getParameter("Localidad"));
        String Venta=(request.getParameter("Venta"));
        
        Font fuente=new Font(Font.FontFamily.COURIER, 10);
        Paragraph P=new Paragraph();
        Chunk c=new Chunk();
        P.setAlignment(Element.ALIGN_CENTER);        
        
        try {
            FileOutputStream Archivo=new FileOutputStream("/home/rcortes/Ejemplo.pdf");
            Document doc=new Document();
            PdfWriter.getInstance(doc, Archivo);
            doc.open();
            doc.add(new Paragraph(Encabezado));
            doc.add(new Paragraph(Encabezado1));
            doc.add(new Paragraph(Encabezado2));
            PdfPTable tabla1=new PdfPTable(3);
            doc.add(new Paragraph(" "));
            doc.add(new Paragraph("Venta: "+Venta));
            doc.add(new Paragraph("Cliente: "+Cliente));
            doc.add(new Paragraph("Estado: "+Estado));
            doc.add(new Paragraph("Municipio: "+Municipio));
            doc.add(new Paragraph("Localidad: "+Localidad));            
            doc.add(new Paragraph("Dirección: "+Direccion));
            doc.add(new Paragraph(" "));
            tabla1.addCell("Tipo");
            tabla1.addCell("Monto");
            tabla1.addCell("Observación");
            System.out.println(Tipo[0]);
            doc.add(tabla1);
            doc.close();
            System.out.println("Se creo pdf\n");
        } catch (DocumentException e) {
            System.out.println(e);
        }
    }

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
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");// Forzar a usar codificación UTF-8 iso-8859-1        
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
