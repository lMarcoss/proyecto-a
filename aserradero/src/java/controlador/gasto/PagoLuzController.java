package controlador.gasto;

import dao.gasto.PagoLuzCRUD;
import entidades.gasto.PagoLuz;
import java.io.IOException;
import java.math.BigDecimal;
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
 * @author rcortes
 */
public class PagoLuzController extends HttpServlet {

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
        } else if (rol.equals("Administrador") || rol.equals("Empleado") || rol.equals("Vendedor")) {
            //Acción a realizar
            String action = request.getParameter("action");
            switch (action) {
                /**
                 * *************** Respuestas a métodos POST
                 * *********************
                 */
                case "insertar":
                    registrarPagoLuz(request, response, sesion, action);
                    break;
                case "actualizar":
                    actualizarPagoLuz(request, response, sesion, action);
                    break;
                case "buscar":
                    buscarPagoLuz(request, response, sesion, action);
                    break;
                /**
                 * *************** Respuestas a métodos GET
                 * *********************
                 */
                case "nuevo":
                    prepararNuevoPagoLuz(request, response, sesion, action);
                    break;
                case "listar":
                    listarPagoLuz(request, response, sesion, action);
                    break;
                case "modificar":
                    modificarPagoLuz(request, response, sesion, action);
                    break;
                case "eliminar":
                    eliminarPagoLuz(request, response, sesion, action);
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

    private void registrarPagoLuz(HttpServletRequest request, HttpServletResponse response, HttpSession sesion, String action) {
        PagoLuz pagoLuz = extraerPagoLuzForm(request, response, sesion, action);
        PagoLuzCRUD pagoLuzCRUD = new PagoLuzCRUD();
        try {
            pagoLuzCRUD.registrar(pagoLuz);
            //enviar mensaje -> registrado
            response.sendRedirect("/aserradero/PagoLuzController?action=listar");
        } catch (Exception ex) {
            System.out.println(ex);
            listarPagoLuz(request, response, sesion, action);
            Logger.getLogger(PagoLuzController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private PagoLuz extraerPagoLuzForm(HttpServletRequest request, HttpServletResponse response, HttpSession sesion, String action) {
        PagoLuz pagoLuz = new PagoLuz();
        if (action.equals("actualizar")) {
            pagoLuz.setId_pago_luz(request.getParameter("id_pago_luz"));
        }
        pagoLuz.setFecha(request.getParameter("fecha"));
        pagoLuz.setId_empleado((String) sesion.getAttribute("id_jefe"));
        pagoLuz.setMonto(BigDecimal.valueOf((Double.valueOf(request.getParameter("monto")))));
        pagoLuz.setObservacion(request.getParameter("observacion"));
        return pagoLuz;
    }

    private void listarPagoLuz(HttpServletRequest request, HttpServletResponse response, HttpSession sesion, String action) {
        List<PagoLuz> pagosluz;
        PagoLuzCRUD pagoLuzcrud = new PagoLuzCRUD();
        try {
            pagosluz = (List<PagoLuz>) pagoLuzcrud.listar((String) sesion.getAttribute("id_jefe"), (String) sesion.getAttribute("rol"));
            mostrarPagosRenta(request, response, pagosluz, sesion, action);
        } catch (Exception ex) {
            System.out.println(ex);
            Logger.getLogger(PagoLuzController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void mostrarPagosRenta(HttpServletRequest request, HttpServletResponse response, List<PagoLuz> listaPagosRenta, HttpSession sesion, String action) {
        request.setAttribute("listaPagosRenta", listaPagosRenta);
        request.setAttribute("mensaje", action);
        RequestDispatcher view = request.getRequestDispatcher("moduloGasto/pagoLuz/listarPagosRenta.jsp");
        try {
            view.forward(request, response);
        } catch (ServletException | IOException ex) {
            System.out.println(ex);
            Logger.getLogger(PagoLuzController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void actualizarPagoLuz(HttpServletRequest request, HttpServletResponse response, HttpSession sesion, String action) {
        PagoLuz pagoLuz = extraerPagoLuzForm(request, response, sesion, action);
        PagoLuzCRUD pagoLuzCRUD = new PagoLuzCRUD();
        try {
            pagoLuzCRUD.actualizar(pagoLuz);
            //enviar mensaje -> actualizado
            response.sendRedirect("/aserradero/PagoLuzController?action=listar");
        } catch (Exception ex) {
            System.out.println(ex);
            listarPagoLuz(request, response, sesion, "error_actualizar");
            Logger.getLogger(PagoLuzController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void buscarPagoLuz(HttpServletRequest request, HttpServletResponse response, HttpSession sesion, String action) {
        List<PagoLuz> pagosluz;
        String nombre_campo = request.getParameter("nombre_campo");
        String dato = request.getParameter("dato");
        PagoLuzCRUD pagoLuzCRUD = new PagoLuzCRUD();
        try {
            pagosluz = (List<PagoLuz>) pagoLuzCRUD.buscar(nombre_campo, dato, (String) sesion.getAttribute("id_jefe"), (String) sesion.getAttribute("rol"));
            mostrarPagosRenta(request, response, pagosluz, sesion, action);
        } catch (Exception ex) {
            System.out.println(ex);
            listarPagoLuz(request, response, sesion, "error_buscar_campo");
            Logger.getLogger(PagoLuzController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void prepararNuevoPagoLuz(HttpServletRequest request, HttpServletResponse response, HttpSession sesion, String action) {
        try {
            RequestDispatcher view = request.getRequestDispatcher("moduloGasto/pagoLuz/nuevoPagoLuz.jsp");
            view.forward(request, response);
        } catch (IOException | ServletException ex) {
            System.out.println(ex);
            listarPagoLuz(request, response, sesion, "error_nuevo");
            Logger.getLogger(PagoLuzController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void modificarPagoLuz(HttpServletRequest request, HttpServletResponse response, HttpSession sesion, String action) {
        PagoLuz pagoLuzEC = new PagoLuz();
        pagoLuzEC.setId_pago_luz(request.getParameter("id_pago_luz"));
        PagoLuzCRUD pagoLuzCRUD = new PagoLuzCRUD();
        try {
            PagoLuz pagoLuz = (PagoLuz) pagoLuzCRUD.modificar(pagoLuzEC);
            request.setAttribute("pagoLuz", pagoLuz);
            RequestDispatcher view = request.getRequestDispatcher("moduloGasto/pagoLuz/actualizarPagoLuz.jsp");
            view.forward(request, response);
        } catch (Exception ex) {
            System.out.println(ex);
            listarPagoLuz(request, response, sesion, "error_modificar");
            Logger.getLogger(PagoLuzController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void eliminarPagoLuz(HttpServletRequest request, HttpServletResponse response, HttpSession sesion, String action) {
        PagoLuz pagoLuzEC = new PagoLuz();
        pagoLuzEC.setId_pago_luz(request.getParameter("id_pago_luz"));
        PagoLuzCRUD pagoLuzCRUD = new PagoLuzCRUD();
        try {
            pagoLuzCRUD.eliminar(pagoLuzEC);
            //enviar mensaje -> eliminado
            response.sendRedirect("/aserradero/PagoLuzController?action=listar");
        } catch (Exception ex) {
            System.out.println(ex);
            listarPagoLuz(request, response, sesion, "error_eliminar");
            Logger.getLogger(PagoLuzController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
