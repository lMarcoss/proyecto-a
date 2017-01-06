package controlador.registros;

import dao.registros.PersonaCRUD;
import dao.registros.ProveedorCRUD;
import entidades.registros.Persona;
import entidades.registros.Proveedor;
import java.io.IOException;
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
public class ProveedorController extends HttpServlet {

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
                    registrarProveedor(request, response, sesion, action);
                    break;
                case "buscar":
                    buscarProveedor(request, response, sesion, action);
                    break;
                /**
                 * *************** Respuestas a métodos GET
                 * *********************
                 */
                case "nuevo":
                    prepararNuevoProveedor(request, response, sesion);
                    break;
                case "listar":
                    listarProveedores(request, response, sesion, action);
                    break;
                case "eliminar":
                    eliminarProveedor(request, response, sesion, action);
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

    private void registrarProveedor(HttpServletRequest request, HttpServletResponse response, HttpSession sesion, String action) {
        Proveedor proveedor = extraerProveedorForm(request, sesion, action);
        ProveedorCRUD proveedorCRUD = new ProveedorCRUD();
        try {
            proveedorCRUD.registrar(proveedor);
            //enviar mensaje -> registrado
            response.sendRedirect("/aserradero/ProveedorController?action=listar");
        } catch (Exception ex) {
            listarProveedores(request, response, sesion, "error_registrar");
            System.out.println(ex);
            Logger.getLogger(ProveedorController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private Proveedor extraerProveedorForm(HttpServletRequest request, HttpSession sesion, String action) {
        Proveedor proveedor = new Proveedor();
        proveedor.setId_persona(request.getParameter("id_persona"));
        proveedor.setId_jefe((String) sesion.getAttribute("id_jefe"));
        return proveedor;
    }

    private void buscarProveedor(HttpServletRequest request, HttpServletResponse response, HttpSession sesion, String action) {
        List<Proveedor> listaProveedores;
        String nombre_campo = request.getParameter("nombre_campo");
        String dato = request.getParameter("dato");
        ProveedorCRUD proveedorCRUD = new ProveedorCRUD();
        try {
            listaProveedores = (List<Proveedor>) proveedorCRUD.buscar(nombre_campo, dato, (String) sesion.getAttribute("id_jefe"));
            mostrarProveedores(request, response, listaProveedores, action);
        } catch (Exception ex) {
            listarProveedores(request, response, sesion, "error_buscar_campo");
            System.out.println(ex);
            Logger.getLogger(ProveedorController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void mostrarProveedores(HttpServletRequest request, HttpServletResponse response, List<Proveedor> listaProveedores, String action) {
        request.setAttribute("mensaje", action);
        request.setAttribute("listaProveedores", listaProveedores);
        RequestDispatcher view = request.getRequestDispatcher("moduloRegistros/proveedor/listarProveedores.jsp");
        try {
            view.forward(request, response);
        } catch (ServletException | IOException ex) {
            System.err.println("No se pudo mostrar la lista de proveedores");
            Logger.getLogger(ProveedorController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void prepararNuevoProveedor(HttpServletRequest request, HttpServletResponse response, HttpSession sesion) {
        PersonaCRUD personaCRUD = new PersonaCRUD();
        try {
            //consultamos la lista de personas para registrarlos como proveedor
            List<Persona> personas;
            personas = personaCRUD.listarPersonasPara("proveedor", (String) sesion.getAttribute("id_jefe"));
            request.setAttribute("personas", personas);

            RequestDispatcher view = request.getRequestDispatcher("moduloRegistros/proveedor/nuevoProveedor.jsp");
            view.forward(request, response);
        } catch (ServletException | IOException ex) {
            System.out.println(ex);
            listarProveedores(request, response, sesion, "error_nuevo");
            Logger.getLogger(ProveedorController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            listarProveedores(request, response, sesion, "error_nuevo");
            Logger.getLogger(ProveedorController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void listarProveedores(HttpServletRequest request, HttpServletResponse response, HttpSession sesion, String action) {
        List<Proveedor> listaProveedores;
        ProveedorCRUD proveedorCRUD = new ProveedorCRUD();
        try {
            listaProveedores = (List<Proveedor>) proveedorCRUD.listar((String) sesion.getAttribute("id_jefe"));
            mostrarProveedores(request, response, listaProveedores, action);
        } catch (Exception ex) {
            System.out.println(ex);
            Logger.getLogger(ProveedorController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void eliminarProveedor(HttpServletRequest request, HttpServletResponse response, HttpSession sesion, String action) {
        Proveedor proveedorEC = new Proveedor();
        proveedorEC.setId_proveedor(request.getParameter("id_proveedor"));
        proveedorEC.setId_jefe((String) sesion.getAttribute("id_jefe"));

        ProveedorCRUD proveedorCRUD = new ProveedorCRUD();
        try {
            proveedorCRUD.eliminar(proveedorEC);
            //enviar mensaje -> eliminado
            response.sendRedirect("/aserradero/ProveedorController?action=listar");
        } catch (Exception ex) {
            listarProveedores(request, response, sesion, "error_eliminar");
            System.out.println(ex);
            Logger.getLogger(ProveedorController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
