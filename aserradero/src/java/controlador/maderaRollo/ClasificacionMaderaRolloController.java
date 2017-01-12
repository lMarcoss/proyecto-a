package controlador.maderaRollo;

import dao.maderaRollo.ClasificacionMaderaRolloCRUD;
import dao.registros.ProveedorCRUD;
import entidades.maderaRollo.ClasificacionMaderaRollo;
import entidades.registros.Proveedor;
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
public class ClasificacionMaderaRolloController extends HttpServlet {

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
                    registrarClasificacion(request, response, sesion, action);
                    break;
                case "actualizar":
                    actualizarClasificacion(request, response, sesion, action);
                    break;
                case "buscar":
                    buscar(request, response, sesion, action);
                    break;
                /**
                 * *************** Respuestas a métodos GET
                 * *********************
                 */
                case "nuevo":
                    prepararNuevoClasificacion(request, response, sesion, action);
                    break;
                case "listar":
                    listarClasificacion(request, response, sesion, action);
                    break;
                case "modificar":
                    modificarClasificacion(request, sesion, response);
                    break;
                case "eliminar":
                    eliminarClasificacion(request, sesion, response);
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

    private void registrarClasificacion(HttpServletRequest request, HttpServletResponse response, HttpSession sesion, String action) {
        ClasificacionMaderaRollo clasificacion = extraerClasificacionForm(request, sesion, action);
        ClasificacionMaderaRolloCRUD clasificacionCRUD = new ClasificacionMaderaRolloCRUD();
        try {
            clasificacionCRUD.registrar(clasificacion);
            //enviar mensaje -> registrado
            response.sendRedirect("/aserradero/ClasificacionMaderaRolloController?action=listar");
        } catch (Exception ex) {
            System.out.println(ex);
            listarClasificacion(request, response, sesion, "error_registrar");
            Logger.getLogger(ClasificacionMaderaRolloController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private ClasificacionMaderaRollo extraerClasificacionForm(HttpServletRequest request, HttpSession sesion, String action) {
        ClasificacionMaderaRollo clasificacionMaderaEntrada = new ClasificacionMaderaRollo();
        clasificacionMaderaEntrada.setId_proveedor(request.getParameter("id_proveedor"));
        clasificacionMaderaEntrada.setClasificacion(request.getParameter("clasificacion"));
        clasificacionMaderaEntrada.setCosto(BigDecimal.valueOf((Double.valueOf(request.getParameter("costo")))));
        return clasificacionMaderaEntrada;
    }

    private void actualizarClasificacion(HttpServletRequest request, HttpServletResponse response, HttpSession sesion, String action) {
        ClasificacionMaderaRollo clasificacion = extraerClasificacionForm(request, sesion, action);
        ClasificacionMaderaRolloCRUD clasificacionCRUD = new ClasificacionMaderaRolloCRUD();
        try {
            clasificacionCRUD.actualizar(clasificacion);
            //enviar mensaje -> actualizado
            response.sendRedirect("/aserradero/ClasificacionMaderaRolloController?action=listar");
        } catch (Exception ex) {
            System.out.println(ex);
            listarClasificacion(request, response, sesion, action);
            Logger.getLogger(ClasificacionMaderaRolloController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void buscar(HttpServletRequest request, HttpServletResponse response, HttpSession sesion, String action) {
        List<ClasificacionMaderaRollo> listaClasificacion;
        String nombre_campo = request.getParameter("nombre_campo"); //Nombre del campo asociado a la búsqueda
        String dato = request.getParameter("dato");                 // Valor a buscar en el campo
        ClasificacionMaderaRolloCRUD clasificacionCRUD = new ClasificacionMaderaRolloCRUD();
        try {
            listaClasificacion = (List<ClasificacionMaderaRollo>) clasificacionCRUD.buscar(nombre_campo, dato, (String) sesion.getAttribute("id_jefe"), (String) sesion.getAttribute("rol"));
            mostrarClasificaciones(request, response, listaClasificacion, action);
        } catch (Exception ex) {
            System.out.println(ex);
            listarClasificacion(request, response, sesion, "error_buscar_campo");
            Logger.getLogger(ClasificacionMaderaRolloController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void mostrarClasificaciones(HttpServletRequest request, HttpServletResponse response, List<ClasificacionMaderaRollo> listaClasificacion, String action) {
        request.setAttribute("mensaje", action);
        request.setAttribute("listaClasificacion", listaClasificacion);
        RequestDispatcher view = request.getRequestDispatcher("moduloMaderaRollo/clasificacionMaderaRollo/listarClasificacion.jsp");
        try {
            view.forward(request, response);
        } catch (ServletException | IOException ex) {
            System.out.println(ex);
            System.err.println("No se pudo mostrar la lista de clasificacion");
            Logger.getLogger(ClasificacionMaderaRolloController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void prepararNuevoClasificacion(HttpServletRequest request, HttpServletResponse response, HttpSession sesion, String action) {
        try {
            // enviamos la lista de personas que pueden ser prestadores
            ProveedorCRUD proveedorCRUD = new ProveedorCRUD();
            List<Proveedor> proveedores = (List<Proveedor>) proveedorCRUD.listar((String) sesion.getAttribute("id_jefe"), (String) sesion.getAttribute("rol"));
            request.setAttribute("proveedores", proveedores);

            RequestDispatcher view = request.getRequestDispatcher("moduloMaderaRollo/clasificacionMaderaRollo/nuevoClasificacion.jsp");
            view.forward(request, response);
        } catch (Exception ex) {
            System.out.println(ex);
            listarClasificacion(request, response, sesion, "error_nuevo");
            Logger.getLogger(ClasificacionMaderaRolloController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void listarClasificacion(HttpServletRequest request, HttpServletResponse response, HttpSession sesion, String action) {
        List<ClasificacionMaderaRollo> listaClasificacion;
        ClasificacionMaderaRolloCRUD clasificacionCRUD = new ClasificacionMaderaRolloCRUD();
        try {
            listaClasificacion = (List<ClasificacionMaderaRollo>) clasificacionCRUD.listar((String) sesion.getAttribute("id_jefe"), (String) sesion.getAttribute("rol"));
            mostrarClasificaciones(request, response, listaClasificacion, action);
        } catch (Exception ex) {
            System.out.println(ex);
            Logger.getLogger(ClasificacionMaderaRolloController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void modificarClasificacion(HttpServletRequest request, HttpSession sesion, HttpServletResponse response) {
        ClasificacionMaderaRollo clasificacionEC = new ClasificacionMaderaRollo();
        clasificacionEC.setId_proveedor(request.getParameter("id_proveedor"));
        clasificacionEC.setClasificacion(request.getParameter("clasificacion"));

        ClasificacionMaderaRolloCRUD clasificacionCRUD = new ClasificacionMaderaRolloCRUD();
        try {
            ClasificacionMaderaRollo clasificacion = (ClasificacionMaderaRollo) clasificacionCRUD.modificar(clasificacionEC);
            request.setAttribute("clasificacion", clasificacion);
            RequestDispatcher view = request.getRequestDispatcher("moduloMaderaRollo/clasificacionMaderaRollo/actualizarClasificacion.jsp");
            view.forward(request, response);
        } catch (Exception ex) {
            System.out.println(ex);
            listarClasificacion(request, response, sesion, "error_modificar");
            Logger.getLogger(ClasificacionMaderaRolloController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void eliminarClasificacion(HttpServletRequest request, HttpSession sesion, HttpServletResponse response) {
        ClasificacionMaderaRollo clasificacionEC = new ClasificacionMaderaRollo();
        clasificacionEC.setId_proveedor(request.getParameter("id_proveedor"));
        clasificacionEC.setClasificacion(request.getParameter("clasificacion"));

        ClasificacionMaderaRolloCRUD clasificacionCRUD = new ClasificacionMaderaRolloCRUD();
        try {
            clasificacionCRUD.eliminar(clasificacionEC);
            response.sendRedirect("/aserradero/ClasificacionMaderaRolloController?action=listar");
        } catch (Exception ex) {
            System.out.println(ex);
            listarClasificacion(request, response, sesion, "error_eliminar");
            Logger.getLogger(ClasificacionMaderaRolloController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
