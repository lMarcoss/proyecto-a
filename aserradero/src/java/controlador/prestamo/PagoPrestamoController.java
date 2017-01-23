package controlador.prestamo;

import dao.prestamo.PagoPrestamoCRUD;
import dao.prestamo.PrestamoCRUD;
import entidades.prestamo.PagoPrestamo;
import entidades.prestamo.Prestamo;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author lmarcoss
 */
public class PagoPrestamoController extends HttpServlet {

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

        // Sesiones
        HttpSession sesion = request.getSession(false);
        String nombre_usuario = (String) sesion.getAttribute("nombre_usuario");
        String rol = (String) sesion.getAttribute("rol");
        if (nombre_usuario.equals("")) {
            response.sendRedirect("/aserradero/");
        } else if (rol.equals("Administrador")) {
            //Acción a realizar
            String action = request.getParameter("action");
            switch (action) {
                /**
                 * *************** Respuestas a métodos POST
                 * *********************
                 */
                case "insertar":
                    registrarPagoPrestamo(request, response, sesion, action);
                    break;
                case "actualizar":
                    actualizarPagoPrestamo(request, response, sesion, action);
                    break;
                /**
                 * *************** Respuestas a métodos GET
                 * *********************
                 */
                case "nuevo":
                    prepararNuevoPagoPrestamo(request, response, sesion, action);
                    break;
                case "listar":
                    listarPagoPrestamo(request, response, sesion, action);
                    break;
                case "modificar":
                    modificarPagoPrestamo(request, response, sesion, action);
                    break;
                case "eliminar":
                    eliminarPagoPrestamo(request, response, sesion, action);
                    break;
            }
        } else {
            try {
                sesion.invalidate();
            } catch (Exception e) {
                System.out.println(e);
                response.sendRedirect("/aserradero/");
            }
            response.sendRedirect("/aserradero/");
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

    private void registrarPagoPrestamo(HttpServletRequest request, HttpServletResponse response, HttpSession sesion, String action) {
        PagoPrestamo pagoPrestamo = extraerPagoPrestamoForm(request, sesion, action);
        PagoPrestamoCRUD pagoPrestamoCRUD = new PagoPrestamoCRUD();
        try {
            pagoPrestamoCRUD.registrar(pagoPrestamo);
            response.sendRedirect("/aserradero/PagoPrestamoController?action=listar"); // para evitar acciones repetidas al actualizar página
        } catch (Exception ex) {
            listarPagoPrestamo(request, response, sesion, "error_registrar");
            Logger.getLogger(PagoPrestamoController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private PagoPrestamo extraerPagoPrestamoForm(HttpServletRequest request, HttpSession sesion, String action) {
        PagoPrestamo pagoPrestamo = new PagoPrestamo();
        if (action.equals("actualizar")) {
            // Se ejecuta sólo en el caso de actualizar
            pagoPrestamo.setId_pago(Integer.valueOf(request.getParameter("id_pago")));
        }
        pagoPrestamo.setId_prestamo(Integer.valueOf(request.getParameter("id_prestamo")));
        pagoPrestamo.setFecha(Date.valueOf(request.getParameter("fecha")));
        pagoPrestamo.setId_empleado((String) sesion.getAttribute("id_jefe"));
        pagoPrestamo.setMonto_pago(BigDecimal.valueOf((Double.valueOf(request.getParameter("monto_pago")))));
        return pagoPrestamo;
    }

    private void actualizarPagoPrestamo(HttpServletRequest request, HttpServletResponse response, HttpSession sesion, String action) {
        PagoPrestamo pagoPrestamo = extraerPagoPrestamoForm(request, sesion, action);
        PagoPrestamoCRUD pagoPrestamoCRUD = new PagoPrestamoCRUD();
        try {
            pagoPrestamoCRUD.actualizar(pagoPrestamo);
            response.sendRedirect("/aserradero/PagoPrestamoController?action=listar"); // para evitar acciones repetidas al actualizar página
        } catch (Exception ex) {
            listarPagoPrestamo(request, response, sesion, "error_actualizar");
            Logger.getLogger(PagoPrestamoController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void mostrarPagoPrestamos(HttpServletRequest request, HttpServletResponse response, List<PagoPrestamo> listaPagoPrestamo, String action) {
        request.setAttribute("mensaje", action);
        request.setAttribute("listaPagoPrestamo", listaPagoPrestamo);
        RequestDispatcher view = request.getRequestDispatcher("moduloPrestamo/pagoPrestamo/listarPagoPrestamo.jsp");
        try {
            view.forward(request, response);
        } catch (ServletException | IOException ex) {
            System.err.println("No se pudo mostrar la lista de pago prestamo");
            Logger.getLogger(PagoPrestamoController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void prepararNuevoPagoPrestamo(HttpServletRequest request, HttpServletResponse response, HttpSession sesion, String action) {
        PrestamoCRUD prestamoCRUD = new PrestamoCRUD();
        List<Prestamo> listaPrestamo;
        try {
            listaPrestamo = (List<Prestamo>) prestamoCRUD.listarPrestamoPorPagar();
            request.setAttribute("listaPrestamo", listaPrestamo);
            RequestDispatcher view = request.getRequestDispatcher("moduloPrestamo/pagoPrestamo/nuevoPagoPrestamo.jsp");
            view.forward(request, response);
        } catch (Exception ex) {
            listarPagoPrestamo(request, response, sesion, "error_nuevo");
            Logger.getLogger(PagoPrestamoController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void listarPagoPrestamo(HttpServletRequest request, HttpServletResponse response, HttpSession sesion, String action) {
        List<PagoPrestamo> listaPagoPrestamos;
        PagoPrestamoCRUD pagoPrestamoCRUD = new PagoPrestamoCRUD();
        try {
            listaPagoPrestamos = (List<PagoPrestamo>) pagoPrestamoCRUD.listar((String) sesion.getAttribute("id_jefe"), (String) sesion.getAttribute("rol"));
            mostrarPagoPrestamos(request, response, listaPagoPrestamos, action);
        } catch (Exception ex) {
            System.out.println(ex);
            Logger.getLogger(PagoPrestamoController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void modificarPagoPrestamo(HttpServletRequest request, HttpServletResponse response, HttpSession sesion, String action) {
        PagoPrestamoCRUD pagoPrestamoCRUD = new PagoPrestamoCRUD();
        PagoPrestamo pagoPrestamo = new PagoPrestamo();
        //Obtenemos el pago a modificar
        pagoPrestamo.setId_pago(Integer.valueOf(request.getParameter("id_pago")));
        pagoPrestamo.setId_prestamo(Integer.valueOf(request.getParameter("id_prestamo")));
        try {
            pagoPrestamo = (PagoPrestamo) pagoPrestamoCRUD.modificar(pagoPrestamo);
            request.setAttribute("pagoPrestamo", pagoPrestamo);
            RequestDispatcher view = request.getRequestDispatcher("moduloPrestamo/pagoPrestamo/actualizarPagoPrestamo.jsp");
            view.forward(request, response);
        } catch (Exception ex) {
            listarPagoPrestamo(request, response, sesion, "error_modificar");
            Logger.getLogger(PagoPrestamoController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void eliminarPagoPrestamo(HttpServletRequest request, HttpServletResponse response, HttpSession sesion, String action) {
        PagoPrestamoCRUD pagoPrestamoCRUD = new PagoPrestamoCRUD();
        PagoPrestamo pagoPrestamo = new PagoPrestamo();
        pagoPrestamo.setId_pago(Integer.valueOf(request.getParameter("id_pago")));
        try {
            pagoPrestamoCRUD.eliminar(pagoPrestamo);
            response.sendRedirect("/aserradero/PagoPrestamoController?action=listar"); // para evitar acciones repetidas al actualizar página
        } catch (Exception ex) {
            listarPagoPrestamo(request, response, sesion, "error_eliminar");
            Logger.getLogger(PagoPrestamoController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
