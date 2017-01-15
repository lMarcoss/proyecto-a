package controlador.anticipo;

import dao.anticipo.AnticipoProveedorCRUD;
import dao.registros.ProveedorCRUD;
import entidades.anticipo.AnticipoProveedor;
import entidades.registros.Proveedor;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Marcos
 */
@WebServlet(name = "AnticipoProveedorController", urlPatterns = {"/AnticipoProveedorController"})
public class AnticipoProveedorController extends HttpServlet {

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
                    registrarAnticipoProveedor(request, response, sesion, action);
                    break;
                case "actualizar":
                    actualizarAnticipoProveedor(request, response, sesion, action);
                    break;
                case "buscar":
                    buscar(request, response, sesion, action);
                    break;
                /**
                 * *************** Respuestas a métodos GET
                 * *********************
                 */
                case "nuevo":
                    prepararNuevoAnticipoProveedor(request, response, sesion, action);
                    break;
                case "listar":
                    listarAnticipoProveedores(request, response, sesion, action);
                    break;
                case "modificar":
                    modificarAnticipoProveedor(request, sesion, response);
                    break;
                case "eliminar":
                    eliminarAnticipoProveedor(request, sesion, response);
                    break;
            }
        } else {
            try {
                sesion.invalidate();
                response.sendRedirect("/aserradero/");
            } catch (IOException e) {
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
//        response.setContentType("text/html;charset=UTF-8");
//        request.setCharacterEncoding("UTF-8");// Forzar a usar codificación UTF-8 iso-8859-1
//        //Llegan url
//        String action = request.getParameter("action");
//        AnticipoProveedor anticipoProveedorEC; //Enviar al CRUD
//        AnticipoProveedor anticipoProveedor; //Respuesta del CRUD
//        AnticipoProveedorCRUD anticipoProveedorCRUD;
//        switch (action) {
//            case "nuevo":
//                try {
//
//                    //Enviamos la lista de empleados
//                    EmpleadoCRUD empleadoCRUD = new EmpleadoCRUD();
//                    List<Empleado> empleados = (List<Empleado>) empleadoCRUD.listarEmpleadoPorRol("Empleado");
//                    request.setAttribute("empleados", empleados);
//
//                    RequestDispatcher view = request.getRequestDispatcher("anticipoProveedor/nuevoAnticipoProveedor.jsp");
//                    view.forward(request, response);
//                } catch (Exception ex) {
//                    listarAnticipoProveedores(request, response, "error_nuevo");
//                    System.out.println(ex);
//                    Logger.getLogger(EmpleadoController.class.getName()).log(Level.SEVERE, null, ex);
//                }
//                break;
//            case "listar":
//                listarAnticipoProveedores(request, response, "Lista anticipo proveedores");
//                break;
//            case "modificar":
//                anticipoProveedorEC = new AnticipoProveedor();
//                anticipoProveedorEC.setId_anticipo_p(Integer.valueOf(request.getParameter("id_anticipo_p")));
//                anticipoProveedorCRUD = new AnticipoProveedorCRUD();
//                try {
//                    anticipoProveedor = (AnticipoProveedor) anticipoProveedorCRUD.modificar(anticipoProveedorEC);
//                    request.setAttribute("anticipoProveedor", anticipoProveedor);
//                    RequestDispatcher view = request.getRequestDispatcher("anticipoProveedor/actualizarAnticipoProveedor.jsp");
//                    view.forward(request, response);
//                } catch (Exception ex) {
//                    listarAnticipoProveedores(request, response, "error_modificar");
//                    System.out.println(ex);
//                    Logger.getLogger(AnticipoProveedorController.class.getName()).log(Level.SEVERE, null, ex);
//                }
//                break;
//            case "eliminar":
//                anticipoProveedorEC = new AnticipoProveedor();
//                anticipoProveedorEC.setId_anticipo_p(Integer.valueOf(request.getParameter("id_anticipo_p")));
//                anticipoProveedorCRUD = new AnticipoProveedorCRUD();
//                try {
//                    anticipoProveedorCRUD.eliminar(anticipoProveedorEC);
//                    listarAnticipoProveedores(request, response, "eliminado");
//                } catch (Exception ex) {
//                    listarAnticipoProveedores(request, response, "error_eliminar");
//                    System.out.println(ex);
//                    Logger.getLogger(AnticipoProveedorController.class.getName()).log(Level.SEVERE, null, ex);
//                }
//                break;
//        }
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
//        response.setContentType("text/html;charset=UTF-8");
//        request.setCharacterEncoding("UTF-8");// Forzar a usar codificación UTF-8 iso-8859-1
//        String action = request.getParameter("action");
//        AnticipoProveedor anticipoProveedor;
//        AnticipoProveedorCRUD anticipoProveedorCRUD;
//        switch (action) {
//            case "nuevo":
//                anticipoProveedor = extraerAnticipoProveedorForm(request);
//                anticipoProveedorCRUD = new AnticipoProveedorCRUD();
//                try {
//                    anticipoProveedorCRUD.registrar(anticipoProveedor);
//                    listarAnticipoProveedores(request, response, "registrado");
//                } catch (Exception ex) {
//                    listarAnticipoProveedores(request, response, "error_registrar");
//                    System.out.println(ex);
//                    Logger.getLogger(AnticipoProveedorController.class.getName()).log(Level.SEVERE, null, ex);
//                }
//                break;
//            case "actualizar":
//                anticipoProveedor = extraerAnticipoProveedorForm(request);
//                anticipoProveedorCRUD = new AnticipoProveedorCRUD();
//                try {
//                    anticipoProveedorCRUD.actualizar(anticipoProveedor);
//                    listarAnticipoProveedores(request, response, "actualizado");
//                } catch (Exception ex) {
//                    listarAnticipoProveedores(request, response, "error_actualizar");
//                    System.out.println(ex);
//                    Logger.getLogger(AnticipoProveedorController.class.getName()).log(Level.SEVERE, null, ex);
//                }
//                break;
//            case "buscar":
//                List<AnticipoProveedor> anticipoProveedores;
//                String nombre_campo = request.getParameter("nombre_campo");
//                String dato = request.getParameter("dato");
//                anticipoProveedorCRUD = new AnticipoProveedorCRUD();
//                try {
//                    anticipoProveedores = (List<AnticipoProveedor>) anticipoProveedorCRUD.buscar(nombre_campo, dato);
//                    request.setAttribute("anticipoProveedores", anticipoProveedores);
//                    RequestDispatcher view = request.getRequestDispatcher("anticipoProveedor/anticipoProveedores.jsp");
//                    view.forward(request, response);
//                } catch (Exception ex) {
//                    listarAnticipoProveedores(request, response, "error_buscar_campo");
//                    System.out.println(ex);
//                    Logger.getLogger(AnticipoProveedorController.class.getName()).log(Level.SEVERE, null, ex);
//                }
//                break;
//        }
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

//    private void listarAnticipoProveedores(HttpServletRequest request, HttpServletResponse response, String mensaje) {
//        List<AnticipoProveedor> anticipoProveedores;
//        AnticipoProveedorCRUD anticipoProveedorCrud = new AnticipoProveedorCRUD();
//        try {
//            anticipoProveedores = (List<AnticipoProveedor>) anticipoProveedorCrud.listar("");
//            //Enviamos las listas al jsp
//            request.setAttribute("anticipoProveedores", anticipoProveedores);
//            //Enviamos mensaje
//            request.setAttribute("mensaje", mensaje);
//            RequestDispatcher view = request.getRequestDispatcher("anticipoProveedor/anticipoProveedores.jsp");
//            view.forward(request, response);
//        } catch (Exception ex) {
//            System.out.println(ex);
//            Logger.getLogger(AnticipoProveedorController.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
//
//    // Extraer datos del formulario
//    private AnticipoProveedor extraerAnticipoProveedorForm(HttpServletRequest request) {
//        AnticipoProveedor anticipoProveedor = new AnticipoProveedor();
//        anticipoProveedor.setId_anticipo_p(Integer.valueOf(request.getParameter("id_anticipo_p")));
//        anticipoProveedor.setFecha(Date.valueOf(request.getParameter("fecha")));
//        anticipoProveedor.setId_proveedor(request.getParameter("id_proveedor"));
//        anticipoProveedor.setId_empleado(request.getParameter("id_empleado"));
//        anticipoProveedor.setMonto_anticipo(BigDecimal.valueOf((Double.valueOf(request.getParameter("monto_anticipo")))));
//
//        return anticipoProveedor;
//    }
//
//}
    private void registrarAnticipoProveedor(
            HttpServletRequest request, HttpServletResponse response, HttpSession sesion, String action) {
        AnticipoProveedor anticipoProveedor = extraerAnticipoProveedorForm(request, sesion, action);
        AnticipoProveedorCRUD anticipoProveedorCRUD = new AnticipoProveedorCRUD();
        try {
            anticipoProveedorCRUD.registrar(anticipoProveedor);
            response.sendRedirect("/aserradero/AnticipoProveedorController?action=listar");
        } catch (Exception ex) {
            listarAnticipoProveedores(request, response, sesion, "error_registrar");
            System.out.println(ex);
            Logger.getLogger(AnticipoProveedorController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // Extraer datos del formulario
    private AnticipoProveedor extraerAnticipoProveedorForm(HttpServletRequest request, HttpSession sesion, String action) {
        AnticipoProveedor anticipoProveedor = new AnticipoProveedor();
        if (action.equals("actualizar")) {
            anticipoProveedor.setId_anticipo_p(Integer.valueOf(request.getParameter("id_anticipo_p")));
        }
        anticipoProveedor.setFecha(Date.valueOf(request.getParameter("fecha")));
        anticipoProveedor.setId_proveedor(request.getParameter("id_proveedor"));
        anticipoProveedor.setId_empleado((String) sesion.getAttribute("id_empleado"));
        anticipoProveedor.setMonto_anticipo(BigDecimal.valueOf((Double.valueOf(request.getParameter("monto_anticipo")))));
        return anticipoProveedor;
    }

    private void listarAnticipoProveedores(
            HttpServletRequest request, HttpServletResponse response, HttpSession sesion, String action) {
        List<AnticipoProveedor> anticipoProveedores;
        AnticipoProveedorCRUD anticipoProveedorCrud = new AnticipoProveedorCRUD();
        try {
            anticipoProveedores = (List<AnticipoProveedor>) anticipoProveedorCrud.listar(
                    (String) sesion.getAttribute("id_jefe"),(String) sesion.getAttribute("rol"));
            mostrarAnticipos(request, response, anticipoProveedores, action);
        } catch (Exception ex) {
            System.out.println(ex);
            Logger.getLogger(AnticipoProveedorController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void mostrarAnticipos(
            HttpServletRequest request, HttpServletResponse response, List<AnticipoProveedor> listaAnticipos, String action) {
        //Enviamos la lista
        request.setAttribute("listaAnticipos", listaAnticipos);

        //enviamos mensaje al jsp
        request.setAttribute("mensaje", action);
        RequestDispatcher view = request.getRequestDispatcher("moduloAnticipo/anticipoProveedor/anticipoProveedores.jsp");
        try {
            view.forward(request, response);
        } catch (ServletException | IOException ex) {
            System.out.println("No se puede mostrar la página moduloAnticipo/anticipoProveedor/anticipoProveedores.jsp");
            Logger.getLogger(AnticipoProveedorController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void actualizarAnticipoProveedor(
            HttpServletRequest request, HttpServletResponse response, HttpSession sesion, String action) {
        AnticipoProveedor anticipoProveedor = extraerAnticipoProveedorForm(request, sesion, action);
        AnticipoProveedorCRUD anticipoProveedorCRUD = new AnticipoProveedorCRUD();
        try {
            anticipoProveedorCRUD.actualizar(anticipoProveedor);
            response.sendRedirect("/aserradero/AnticipoProveedorController?action=listar");
        } catch (Exception ex) {
            System.out.println(ex);
            listarAnticipoProveedores(request, response, sesion, "error_actualizar");
            Logger.getLogger(AnticipoProveedorController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void buscar(HttpServletRequest request, HttpServletResponse response, HttpSession sesion, String action) {
        List<AnticipoProveedor> anticipoProveedores;
        String nombre_campo = request.getParameter("nombre_campo");
        String dato = request.getParameter("dato");
        AnticipoProveedorCRUD anticipoProveedorCRUD = new AnticipoProveedorCRUD();
        try {
            anticipoProveedores = (List<AnticipoProveedor>) anticipoProveedorCRUD.buscar(
                    nombre_campo, dato, (String) sesion.getAttribute("id_jefe"),(String) sesion.getAttribute("rol"));
            mostrarAnticipos(request, response, anticipoProveedores, action);
        } catch (Exception ex) {
            System.out.println(ex);
            listarAnticipoProveedores(request, response, sesion, "error_buscar_campo");
            Logger.getLogger(AnticipoProveedorController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void prepararNuevoAnticipoProveedor(
            HttpServletRequest request, HttpServletResponse response, HttpSession sesion, String action) {
        try {
            //Enviamos la lista de proveedores
            ProveedorCRUD proveedorCRUD = new ProveedorCRUD();
            List<Proveedor> proveedores = (List<Proveedor>) proveedorCRUD.listar((String)sesion.getAttribute("id_jefe"),(String)sesion.getAttribute("rol"));
            request.setAttribute("proveedores", proveedores);

            RequestDispatcher view = request.getRequestDispatcher("moduloAnticipo/anticipoProveedor/nuevoAnticipoProveedor.jsp");
            view.forward(request, response);
        } catch (Exception ex) {
            System.out.println(ex);
            listarAnticipoProveedores(request, response, sesion, "error_nuevo");
            Logger.getLogger(AnticipoProveedorController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void modificarAnticipoProveedor(HttpServletRequest request, HttpSession sesion, HttpServletResponse response) {
        AnticipoProveedor anticipoProveedorEC = new AnticipoProveedor();
        anticipoProveedorEC.setId_anticipo_p(Integer.valueOf(request.getParameter("id_anticipo_p")));
        AnticipoProveedorCRUD anticipoProveedorCRUD = new AnticipoProveedorCRUD();
        try {
            AnticipoProveedor anticipoProveedor = (AnticipoProveedor) anticipoProveedorCRUD.modificar(anticipoProveedorEC);
            request.setAttribute("anticipoProveedor", anticipoProveedor);
            RequestDispatcher view = request.getRequestDispatcher("moduloAnticipo/anticipoProveedor/actualizarAnticipoProveedor.jsp");
            view.forward(request, response);
        } catch (Exception ex) {
            System.out.println(ex);
            listarAnticipoProveedores(request, response, sesion, "error_modificar");
            Logger.getLogger(AnticipoProveedorController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void eliminarAnticipoProveedor(HttpServletRequest request, HttpSession sesion, HttpServletResponse response) {
        AnticipoProveedor anticipoProveedorEC = new AnticipoProveedor();
        anticipoProveedorEC.setId_anticipo_p(Integer.valueOf(request.getParameter("id_anticipo_c")));
        AnticipoProveedorCRUD anticipoProveedorCRUD = new AnticipoProveedorCRUD();
        try {
            anticipoProveedorCRUD.eliminar(anticipoProveedorEC);
            response.sendRedirect("/aserradero/AnticipoProveedorController?action=listar");
        } catch (Exception ex) {
            System.out.println(ex);
            listarAnticipoProveedores(request, response, sesion, "error_eliminar");
            Logger.getLogger(AnticipoProveedorController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
