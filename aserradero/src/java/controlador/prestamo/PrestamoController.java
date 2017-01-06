package controlador.prestamo;

import dao.registros.PersonaCRUD;
import dao.prestamo.PrestamoCRUD;
import entidades.registros.Persona;
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
public class PrestamoController extends HttpServlet {

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
                    registrarPrestamo(request, response, sesion, action);
                    break;
                case "actualizar":
                    actualizarPrestamo(request, response, sesion, action);
                    break;
                case "buscar":
                    buscarPrestamo(request, response, sesion, action);
                    break;
                /**
                 * *************** Respuestas a métodos GET
                 * *********************
                 */
                case "nuevo":
                    prepararNuevoPrestamo(request, response, sesion, action);
                    break;
                case "listar":
                    listarPrestamo(request, response, sesion, action);
                    break;
                case "modificar":
                    modificarPrestamo(request, sesion, response);
                    break;
                case "eliminar":
                    eliminarPrestamo(request, sesion, response);
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

    private void registrarPrestamo(HttpServletRequest request, HttpServletResponse response, HttpSession sesion, String action) {
        Prestamo prestamo = extraerPrestamoForm(request, sesion, action);
        PrestamoCRUD prestamoCRUD = new PrestamoCRUD();
        try {
            prestamoCRUD.registrar(prestamo);
            response.sendRedirect("/aserradero/PrestamoController?action=listar");
        } catch (Exception ex) {
            listarPrestamo(request, response, sesion, "error_registrar");
            Logger.getLogger(PrestamoController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private Prestamo extraerPrestamoForm(HttpServletRequest request, HttpSession sesion, String action) {
        Prestamo prestamo = new Prestamo();
        if (action.equals("actualizar")) {
            // Se ejecuta sólo en el caso de actualizar
            prestamo.setId_prestamo(Integer.valueOf(request.getParameter("id_prestamo")));
        }
        prestamo.setFecha(Date.valueOf(request.getParameter("fecha")));
        prestamo.setId_prestador(request.getParameter("id_prestador"));
        prestamo.setId_empleado((String) sesion.getAttribute("id_empleado"));
        prestamo.setMonto_prestamo(BigDecimal.valueOf((Double.valueOf(request.getParameter("monto_prestamo")))));
        prestamo.setInteres(Integer.valueOf(request.getParameter("interes")));
        return prestamo;
    }

    private void actualizarPrestamo(HttpServletRequest request, HttpServletResponse response, HttpSession sesion, String action) {
        Prestamo prestamo = extraerPrestamoForm(request, sesion, action);
        PrestamoCRUD prestamoCRUD = new PrestamoCRUD();
        try {
            prestamoCRUD.actualizar(prestamo);
            response.sendRedirect("/aserradero/PrestamoController?action=listar");
        } catch (Exception ex) {
            listarPrestamo(request, response, sesion, "error_actualizar");
            Logger.getLogger(PrestamoController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void buscarPrestamo(HttpServletRequest request, HttpServletResponse response, HttpSession sesion, String action) {
        List<Prestamo> listaPrestamo;
        String nombre_campo = request.getParameter("nombre_campo"); //Nombre del campo asociado a la búsqueda
        String dato = request.getParameter("dato");                 // Valor a buscar en el campo
        PrestamoCRUD prestamoCRUD = new PrestamoCRUD();
        try {
            listaPrestamo = (List<Prestamo>) prestamoCRUD.buscar(nombre_campo, dato, (String) sesion.getAttribute("id_jefe"));
            mostrarPrestamos(request, response, listaPrestamo, action);
        } catch (Exception ex) {
            listarPrestamo(request, response, sesion, "error_buscar_campo");
            Logger.getLogger(PrestamoController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void mostrarPrestamos(HttpServletRequest request, HttpServletResponse response, List<Prestamo> listaPrestamo, String action) {
        request.setAttribute("mensaje", action);
        request.setAttribute("listaPrestamo", listaPrestamo);
        RequestDispatcher view = request.getRequestDispatcher("moduloPrestamo/prestamo/listarPrestamo.jsp");
        try {
            view.forward(request, response);
        } catch (ServletException | IOException ex) {
            System.err.println("No se pudo mostrar la lista de prestamo");
            Logger.getLogger(PrestamoController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void prepararNuevoPrestamo(HttpServletRequest request, HttpServletResponse response, HttpSession sesion, String action) {
        try {
            // enviamos la lista de personas que pueden ser prestadores
            PersonaCRUD personaCRUD = new PersonaCRUD();
            List<Persona> personas = (List<Persona>) personaCRUD.listar((String) sesion.getAttribute("id_jefe"));
            request.setAttribute("personas", personas);

            RequestDispatcher view = request.getRequestDispatcher("moduloPrestamo/prestamo/nuevoPrestamo.jsp");
            view.forward(request, response);
        } catch (Exception ex) {
            listarPrestamo(request, response, sesion, "error_nuevo");
            System.out.println(ex);
            Logger.getLogger(PrestamoController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void listarPrestamo(HttpServletRequest request, HttpServletResponse response, HttpSession sesion, String action) {
        List<Prestamo> listaPrestamos;
        PrestamoCRUD prestamoCRUD = new PrestamoCRUD();
        try {
            listaPrestamos = (List<Prestamo>) prestamoCRUD.listar((String) sesion.getAttribute("id_jefe"));
            mostrarPrestamos(request, response, listaPrestamos, action);
        } catch (Exception ex) {
            System.out.println(ex);
            Logger.getLogger(PrestamoController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void modificarPrestamo(HttpServletRequest request, HttpSession sesion, HttpServletResponse response) {
        Prestamo prestamoEC = new Prestamo();
        prestamoEC.setId_prestamo(Integer.valueOf(request.getParameter("id_prestamo")));
        PrestamoCRUD prestamoCRUD = new PrestamoCRUD();
        try {
            // enviamos la lista de personas que pueden ser prestadores
            PersonaCRUD personaCRUD = new PersonaCRUD();
            List<Persona> personas = (List<Persona>) personaCRUD.listar((String) sesion.getAttribute("id_jefe"));
            request.setAttribute("personas", personas);

            Prestamo prestamo = (Prestamo) prestamoCRUD.modificar(prestamoEC);
            request.setAttribute("prestamo", prestamo);
            RequestDispatcher view = request.getRequestDispatcher("moduloPrestamo/prestamo/actualizarPrestamo.jsp");
            view.forward(request, response);
        } catch (Exception ex) {
            listarPrestamo(request, response, sesion, "error_modificar");
            Logger.getLogger(PrestamoController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void eliminarPrestamo(HttpServletRequest request, HttpSession sesion, HttpServletResponse response) {
        Prestamo prestamoEC = new Prestamo();
        prestamoEC.setId_prestamo(Integer.valueOf(request.getParameter("id_prestamo")));
        PrestamoCRUD prestamoCRUD = new PrestamoCRUD();
        try {
            prestamoCRUD.eliminar(prestamoEC);
            response.sendRedirect("/aserradero/PrestamoController?action=listar");
        } catch (Exception ex) {
            listarPrestamo(request, response, sesion, "error_eliminar");
            Logger.getLogger(PrestamoController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
