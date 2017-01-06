package controlador.gasto;

import dao.gasto.OtroGastoCRUD;
import entidades.gasto.OtroGasto;
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
 * @author rcortes
 */
public class OtroGastoController extends HttpServlet {

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
                    registrarOtroGasto(request, response, sesion, action);
                    break;
                case "actualizar":
                    actualizarOtroGasto(request, response, sesion, action);
                    break;
                case "buscar":
                    buscarOtroGasto(request, response, sesion, action);
                    break;
                /**
                 * *************** Respuestas a métodos GET
                 * *********************
                 */
                case "nuevo":
                    prepararNuevoOtroGasto(request, response, sesion, action);
                    break;
                case "listar":
                    listarOtroGasto(request, response, sesion, action);
                    break;
                case "modificar":
                    modificarOtroGasto(request, response, sesion, action);
                    break;
                case "eliminar":
                    eliminarOtroGasto(request, response, sesion, action);
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

    private void registrarOtroGasto(HttpServletRequest request, HttpServletResponse response, HttpSession sesion, String action) {
        OtroGasto otroGasto = extraerOtroGastoForm(request, response, sesion, action);
        OtroGastoCRUD otroGastocrud = new OtroGastoCRUD();
        try {
            otroGastocrud.registrar(otroGasto);
            //enviar mensaje -> registrado
            response.sendRedirect("/aserradero/OtroGastoController?action=listar");
        } catch (Exception ex) {
            System.out.println(ex);
            listarOtroGasto(request, response, sesion, "error_registrar");
            Logger.getLogger(OtroGastoController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private OtroGasto extraerOtroGastoForm(HttpServletRequest request, HttpServletResponse response, HttpSession sesion, String action) {
        OtroGasto otrogasto = new OtroGasto();
        if (action.equals("actualizar")) {
            otrogasto.setId_gasto(Integer.valueOf(request.getParameter("id_gasto")));
        }
        otrogasto.setFecha(Date.valueOf(request.getParameter("fecha")));
        otrogasto.setId_empleado((String) sesion.getAttribute("id_empleado"));
        otrogasto.setNombre_gasto(request.getParameter("nombre_gasto"));
        otrogasto.setMonto(BigDecimal.valueOf((Double.valueOf(request.getParameter("monto")))));
        otrogasto.setObservacion(request.getParameter("observacion"));
        return otrogasto;
    }

    private void listarOtroGasto(HttpServletRequest request, HttpServletResponse response, HttpSession sesion, String action) {
        List<OtroGasto> otrosGasto;
        OtroGastoCRUD otroGastocrud = new OtroGastoCRUD();
        try {
            otrosGasto = (List<OtroGasto>) otroGastocrud.listar((String) sesion.getAttribute("id_jefe"));
            mostrarOtrosGasto(request, response, otrosGasto, sesion, action);
        } catch (Exception ex) {
            System.out.println(ex);
            Logger.getLogger(OtroGastoController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void mostrarOtrosGasto(HttpServletRequest request, HttpServletResponse response, List<OtroGasto> listaOtrosGasto, HttpSession sesion, String action) {
        request.setAttribute("listaOtrosGasto", listaOtrosGasto);
        request.setAttribute("mensaje", action);
        RequestDispatcher view = request.getRequestDispatcher("moduloGasto/otroGasto/listarOtrosGasto.jsp");
        try {
            view.forward(request, response);
        } catch (ServletException | IOException ex) {
            System.out.println(ex);
            Logger.getLogger(OtroGastoController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void actualizarOtroGasto(HttpServletRequest request, HttpServletResponse response, HttpSession sesion, String action) {
        OtroGasto otrogasto = extraerOtroGastoForm(request, response, sesion, action);
        OtroGastoCRUD otrogastoCRUD = new OtroGastoCRUD();
        try {
            otrogastoCRUD.actualizar(otrogasto);
            response.sendRedirect("/aserradero/OtroGastoController?action=listar");
        } catch (Exception ex) {
            System.out.println(ex);
            listarOtroGasto(request, response, sesion, "error_actualizar");
            Logger.getLogger(OtroGastoController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void buscarOtroGasto(HttpServletRequest request, HttpServletResponse response, HttpSession sesion, String action) {
        List<OtroGasto> otrosgastos;
        String nombre_campo = request.getParameter("nombre_campo");
        String dato = request.getParameter("dato");
        OtroGastoCRUD otrogastoCRUD = new OtroGastoCRUD();
        try {
            otrosgastos = (List<OtroGasto>) otrogastoCRUD.buscar(nombre_campo, dato, (String) sesion.getAttribute("id_jefe"));
            mostrarOtrosGasto(request, response, otrosgastos, sesion, action);
        } catch (Exception ex) {
            System.out.println(ex);
            listarOtroGasto(request, response, sesion, "error_buscar_campo");
            Logger.getLogger(OtroGastoController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void prepararNuevoOtroGasto(HttpServletRequest request, HttpServletResponse response, HttpSession sesion, String action) {
        try {
            RequestDispatcher view = request.getRequestDispatcher("moduloGasto/otroGasto/nuevoOtroGasto.jsp");
            view.forward(request, response);
        } catch (IOException | ServletException ex) {
            System.out.println(ex);
            listarOtroGasto(request, response, sesion, "error_nuevo");
            Logger.getLogger(OtroGastoController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void modificarOtroGasto(HttpServletRequest request, HttpServletResponse response, HttpSession sesion, String action) {
        OtroGasto otrogastoEC = new OtroGasto();
        otrogastoEC.setId_gasto(Integer.valueOf(request.getParameter("id_gasto")));
        OtroGastoCRUD otrogastoCRUD = new OtroGastoCRUD();
        try {
            OtroGasto otrogasto = (OtroGasto) otrogastoCRUD.modificar(otrogastoEC);
            request.setAttribute("otrogasto", otrogasto);
            RequestDispatcher view = request.getRequestDispatcher("moduloGasto/otroGasto/actualizarOtroGasto.jsp");
            view.forward(request, response);
        } catch (Exception ex) {
            System.out.println(ex);
            listarOtroGasto(request, response, sesion, "error_modificar");
            Logger.getLogger(OtroGastoController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void eliminarOtroGasto(HttpServletRequest request, HttpServletResponse response, HttpSession sesion, String action) {
        OtroGasto otrogastoEC = new OtroGasto();
        otrogastoEC.setId_gasto(Integer.valueOf(request.getParameter("id_gasto")));
        OtroGastoCRUD otrogastoCRUD = new OtroGastoCRUD();
        try {
            otrogastoCRUD.eliminar(otrogastoEC);
            //enviar mensaje -> eliminado
            response.sendRedirect("/aserradero/OtroGastoController?action=listar");
        } catch (Exception ex) {
            System.out.println(ex);
            listarOtroGasto(request, response, sesion, "error_eliminar");
            Logger.getLogger(OtroGastoController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
