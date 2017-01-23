package controlador.gasto;

import dao.empleado.EmpleadoCRUD;
import dao.gasto.PagoRentaCRUD;
import entidades.empleado.Empleado;
import entidades.gasto.PagoRenta;
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
public class PagoRentaController extends HttpServlet {

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
                    registrarPagoRenta(request, response, sesion, action);
                    break;
                case "actualizar":
                    actualizarPagoRenta(request, response, sesion, action);
                    break;
                case "buscar":
                    buscarPagoRenta(request, response, sesion, action);
                    break;
                /**
                 * *************** Respuestas a métodos GET
                 * *********************
                 */
                case "nuevo":
                    prepararNuevoPagoRenta(request, response, sesion, action);
                    break;
                case "listar":
                    listarPagoRenta(request, response, sesion, action);
                    break;
                case "modificar":
                    modificarPagoRenta(request, response, sesion, action);
                    break;
                case "eliminar":
                    eliminarPagoRenta(request, response, sesion, action);
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

    private void registrarPagoRenta(HttpServletRequest request, HttpServletResponse response, HttpSession sesion, String action) {
        PagoRenta pagorenta = extraerPagoRentaForm(request, response, sesion, action);
        PagoRentaCRUD pagorentacrud = new PagoRentaCRUD();
        try {
            pagorentacrud.registrar(pagorenta);
            //enviar mensaje -> registrado
            response.sendRedirect("/aserradero/PagoRentaController?action=listar");
        } catch (Exception ex) {
            System.out.println(ex);
            listarPagoRenta(request, response, sesion, "error_registrar");
            Logger.getLogger(PagoRentaController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private PagoRenta extraerPagoRentaForm(HttpServletRequest request, HttpServletResponse response, HttpSession sesion, String action) {
        PagoRenta pagorenta = new PagoRenta();
        if (action.equals("actualizar")) {
            pagorenta.setId_pago_renta(request.getParameter("id_pago_renta"));
        }
        pagorenta.setFecha(request.getParameter("fecha"));
        pagorenta.setNombre_persona(request.getParameter("nombre_persona"));
        pagorenta.setId_empleado((String) sesion.getAttribute("id_empleado"));
        pagorenta.setMonto(BigDecimal.valueOf((Double.valueOf(request.getParameter("monto")))));
        pagorenta.setObservacion(request.getParameter("observacion"));

        return pagorenta;
    }

    private void listarPagoRenta(HttpServletRequest request, HttpServletResponse response, HttpSession sesion, String action) {
        List<PagoRenta> pagosrenta;
        PagoRentaCRUD pagorentacrud = new PagoRentaCRUD();
        try {
            pagosrenta = (List<PagoRenta>) pagorentacrud.listar((String) sesion.getAttribute("id_jefe"), (String) sesion.getAttribute("rol"));
            mostrarPagosRenta(request, response, pagosrenta, sesion, action);
        } catch (Exception ex) {
            System.out.println(ex);
            Logger.getLogger(PagoRentaController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void mostrarPagosRenta(HttpServletRequest request, HttpServletResponse response, List<PagoRenta> listaPagosRenta, HttpSession sesion, String action) {
        request.setAttribute("listaPagosRenta", listaPagosRenta);
        request.setAttribute("mensaje", action);
        RequestDispatcher view = request.getRequestDispatcher("moduloGasto/pagoRenta/listarPagosRenta.jsp");
        try {
            view.forward(request, response);
        } catch (ServletException | IOException ex) {
            System.out.println(ex);
            Logger.getLogger(PagoRentaController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void actualizarPagoRenta(HttpServletRequest request, HttpServletResponse response, HttpSession sesion, String action) {
        PagoRenta pagorenta = extraerPagoRentaForm(request, response, sesion, action);
        PagoRentaCRUD pagorentacrud = new PagoRentaCRUD();
        try {
            pagorentacrud.actualizar(pagorenta);
            //enviar mensaje -> actualizado
            response.sendRedirect("/aserradero/PagoRentaController?action=listar");
        } catch (Exception ex) {
            System.out.println(ex);
            listarPagoRenta(request, response, sesion, action);
            Logger.getLogger(PagoRentaController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void buscarPagoRenta(HttpServletRequest request, HttpServletResponse response, HttpSession sesion, String action) {
        List<PagoRenta> pagosrenta;
        String nombre_campo = request.getParameter("nombre_campo");
        String dato = request.getParameter("dato");
        PagoRentaCRUD pagorentacrud = new PagoRentaCRUD();
        try {
            pagosrenta = (List<PagoRenta>) pagorentacrud.buscar(nombre_campo, dato, (String) sesion.getAttribute("id_jefe"), (String) sesion.getAttribute("rol"));
            mostrarPagosRenta(request, response, pagosrenta, sesion, action);
        } catch (Exception ex) {
            System.out.println(ex);
            listarPagoRenta(request, response, sesion, "error_buscar_campo");
            Logger.getLogger(PagoRentaController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void prepararNuevoPagoRenta(HttpServletRequest request, HttpServletResponse response, HttpSession sesion, String action) {
        try {
            EmpleadoCRUD empleadoCRUD = new EmpleadoCRUD();
            List<Empleado> empleados;
            empleados = (List<Empleado>) empleadoCRUD.listar((String) sesion.getAttribute("id_jefe"), (String) sesion.getAttribute("rol"));
            request.setAttribute("empleados", empleados);
            RequestDispatcher view = request.getRequestDispatcher("moduloGasto/pagoRenta/nuevoPagoRenta.jsp");
            view.forward(request, response);
        } catch (Exception ex) {
            System.out.println(ex);
            listarPagoRenta(request, response, sesion, "error_nuevo");
            Logger.getLogger(PagoRentaController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void modificarPagoRenta(HttpServletRequest request, HttpServletResponse response, HttpSession sesion, String action) {
        PagoRenta pagorentaEC = new PagoRenta();
        pagorentaEC.setId_pago_renta(request.getParameter("id_pago_renta"));
        PagoRentaCRUD pagorentaCRUD = new PagoRentaCRUD();
        try {
            PagoRenta pagorenta = (PagoRenta) pagorentaCRUD.modificar(pagorentaEC);
            request.setAttribute("pagorenta", pagorenta);
            RequestDispatcher view = request.getRequestDispatcher("moduloGasto/pagoRenta/actualizarPagoRenta.jsp");
            view.forward(request, response);
        } catch (Exception ex) {
            System.out.println(ex);
            listarPagoRenta(request, response, sesion, "error_modificar");
            Logger.getLogger(PagoRentaController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void eliminarPagoRenta(HttpServletRequest request, HttpServletResponse response, HttpSession sesion, String action) {
        PagoRenta pagorentaEC = new PagoRenta();
        pagorentaEC.setId_pago_renta(request.getParameter("id_pago_renta"));
        PagoRentaCRUD pagorentaCRUD = new PagoRentaCRUD();
        try {
            pagorentaCRUD.eliminar(pagorentaEC);
            response.sendRedirect("/aserradero/PagoRentaController?action=listar");
        } catch (Exception e) {
            System.out.println(e);
            listarPagoRenta(request, response, sesion, "error_eliminar");
            Logger.getLogger(PagoRentaController.class.getName()).log(Level.SEVERE, null, e);
        }
    }
}
