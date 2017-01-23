package controlador.empleado;

import dao.empleado.EmpleadoCRUD;
import dao.empleado.PagoEmpleadoCRUD;
import entidades.empleado.Empleado;
import entidades.empleado.PagoEmpleado;
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
 * @author Marcos
 */
public class PagoEmpleadoController extends HttpServlet {

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
                    registrarPagoEmpleado(request, response, sesion, action);
                    break;
                case "actualizar":
                    actualizarPagoEmpleado(request, response, sesion, action);
                    break;
                /**
                 * *************** Respuestas a métodos GET
                 * *********************
                 */
                case "nuevo":
                    prepararNuevoPagoEmpleado(request, response, sesion);
                    break;
                case "listar":
                    listarPagoEmpleados(request, response, sesion, action);
                    break;
                case "modificar":
                    modificarPagoEmpleado(request, response, sesion, action);
                    break;
                case "eliminar":
                    eliminarPagoEmpleado(request, response, sesion, action);
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

    private void registrarPagoEmpleado(HttpServletRequest request, HttpServletResponse response, HttpSession sesion, String action) {
        PagoEmpleado empleado = extraerPagoEmpleadoForm(request, action);
        PagoEmpleadoCRUD empleadoCRUD = new PagoEmpleadoCRUD();
        try {
            empleadoCRUD.registrar(empleado);
            //enviar mensaje -> registrado
            response.sendRedirect("/aserradero/PagoEmpleadoController?action=listar");
        } catch (Exception ex) {
            listarPagoEmpleados(request, response, sesion, "error_registrar");
            System.out.println(ex);
            Logger.getLogger(PagoEmpleadoController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private PagoEmpleado extraerPagoEmpleadoForm(HttpServletRequest request, String action) {
        PagoEmpleado pagoPagoEmpleado = new PagoEmpleado();
        if (action.equals("actualizar")) {
            pagoPagoEmpleado.setId_pago_empleado(Integer.valueOf(request.getParameter("id_pago_empleado")));
        }
        pagoPagoEmpleado.setFecha(Date.valueOf(request.getParameter("fecha")));
        pagoPagoEmpleado.setId_empleado(request.getParameter("id_empleado"));
        pagoPagoEmpleado.setMonto(BigDecimal.valueOf((Double.valueOf(request.getParameter("monto")))));
        pagoPagoEmpleado.setObservacion(request.getParameter("observacion"));
        return pagoPagoEmpleado;
    }

    private void actualizarPagoEmpleado(HttpServletRequest request, HttpServletResponse response, HttpSession sesion, String action) {
        PagoEmpleado pagoEmpleado = extraerPagoEmpleadoForm(request, action);
        PagoEmpleadoCRUD pagoEmpleadoCRUD = new PagoEmpleadoCRUD();
        try {
            pagoEmpleadoCRUD.actualizar(pagoEmpleado);
            //enviar mensaje -> actualizado
            response.sendRedirect("/aserradero/PagoEmpleadoController?action=listar");
        } catch (Exception ex) {
            listarPagoEmpleados(request, response, sesion, action);
            System.out.println(ex);
            Logger.getLogger(PagoEmpleadoController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void mostrarPagoEmpleados(HttpServletRequest request, HttpServletResponse response, List<PagoEmpleado> listaPagoEmpleados, String action) {
        request.setAttribute("mensaje", action);
        request.setAttribute("listaPagoEmpleados", listaPagoEmpleados);
        RequestDispatcher view = request.getRequestDispatcher("moduloEmpleado/pagoEmpleado/listarPagoEmpleados.jsp");
        try {
            view.forward(request, response);
        } catch (ServletException | IOException ex) {
            System.err.println("No se pudo mostrar la lista de pago empleados");
            Logger.getLogger(PagoEmpleadoController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void prepararNuevoPagoEmpleado(HttpServletRequest request, HttpServletResponse response, HttpSession sesion) {
        try {
            // Enviamos la lista de empleados
            EmpleadoCRUD empleadoCRUD = new EmpleadoCRUD();
            List<Empleado> empleados = (List<Empleado>) empleadoCRUD.listar((String) sesion.getAttribute("id_jefe"), (String) sesion.getAttribute("rol"));
            request.setAttribute("empleados", empleados);

            RequestDispatcher view = request.getRequestDispatcher("moduloEmpleado/pagoEmpleado/nuevoPagoEmpleado.jsp");
            view.forward(request, response);
        } catch (Exception ex) {
            listarPagoEmpleados(request, response, sesion, "error_nuevo");
            System.out.println(ex);
            Logger.getLogger(PagoEmpleadoController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void listarPagoEmpleados(HttpServletRequest request, HttpServletResponse response, HttpSession sesion, String action) {
        List<PagoEmpleado> listaPagoEmpleados;
        PagoEmpleadoCRUD empleadoCRUD = new PagoEmpleadoCRUD();
        try {
            listaPagoEmpleados = (List<PagoEmpleado>) empleadoCRUD.listar((String) sesion.getAttribute("id_jefe"), (String) sesion.getAttribute("rol"));
            mostrarPagoEmpleados(request, response, listaPagoEmpleados, action);
        } catch (Exception ex) {
            System.out.println(ex);
            Logger.getLogger(PagoEmpleadoController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void modificarPagoEmpleado(HttpServletRequest request, HttpServletResponse response, HttpSession sesion, String action) {
        PagoEmpleado padoEmpleadoEC = new PagoEmpleado();
        padoEmpleadoEC.setId_pago_empleado(Integer.parseInt(request.getParameter("id_pago_empleado")));
        PagoEmpleadoCRUD empleadoCRUD = new PagoEmpleadoCRUD();
        try {
            //Enviamos empleado a modificar
            PagoEmpleado pagoEmpleado = (PagoEmpleado) empleadoCRUD.modificar(padoEmpleadoEC);
            request.setAttribute("pagoEmpleado", pagoEmpleado);
            RequestDispatcher view = request.getRequestDispatcher("moduloEmpleado/pagoEmpleado/actualizarPagoEmpleado.jsp");
            view.forward(request, response);
        } catch (Exception ex) {
            listarPagoEmpleados(request, response, sesion, "error_modificar");
            System.out.println(ex);
            Logger.getLogger(PagoEmpleadoController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void eliminarPagoEmpleado(HttpServletRequest request, HttpServletResponse response, HttpSession sesion, String action) {
        PagoEmpleado empleadoEC = new PagoEmpleado();
        empleadoEC.setId_pago_empleado(Integer.parseInt(request.getParameter("id_pago_empleado")));
        PagoEmpleadoCRUD empleadoCRUD = new PagoEmpleadoCRUD();
        try {
            empleadoCRUD.eliminar(empleadoEC);
            //enviar mensaje -> eliminado
            response.sendRedirect("/aserradero/PagoEmpleadoController?action=listar");
        } catch (Exception ex) {
            listarPagoEmpleados(request, response, sesion, "error_eliminar");
            System.out.println(ex);
            Logger.getLogger(PagoEmpleadoController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
