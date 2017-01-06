package controlador.anticipo;

import dao.anticipo.AnticipoClienteCRUD;
import dao.registros.ClienteCRUD;
import entidades.anticipo.AnticipoCliente;
import entidades.registros.Cliente;
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
public class AnticipoClienteController extends HttpServlet {

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
                    registrarAnticipoCliente(request, response, sesion, action);
                    break;
                case "actualizar":
                    actualizarAnticipoCliente(request, response, sesion, action);
                    break;
                case "buscar":
                    buscar(request, response, sesion, action);
                    break;
                /**
                 * *************** Respuestas a métodos GET
                 * *********************
                 */
                case "nuevo":
                    prepararNuevoAnticipoCliente(request, response, sesion, action);
                    break;
                case "listar":
                    listarAnticipoClientes(request, response, sesion, action);
                    break;
                case "modificar":
                    modificarAnticipoCliente(request, sesion, response);
                    break;
                case "eliminar":
                    eliminarAnticipoCliente(request, sesion, response);
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

    private void registrarAnticipoCliente(
            HttpServletRequest request, HttpServletResponse response, HttpSession sesion, String action) {
        AnticipoCliente anticipoCliente = extraerAnticipoClienteForm(request, sesion, action);
        AnticipoClienteCRUD anticipoClienteCRUD = new AnticipoClienteCRUD();
        try {
            anticipoClienteCRUD.registrar(anticipoCliente);
            response.sendRedirect("/aserradero/AnticipoClienteController?action=listar");
        } catch (Exception ex) {
            listarAnticipoClientes(request, response, sesion, "error_registrar");
            System.out.println(ex);
            Logger.getLogger(AnticipoClienteController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // Extraer datos del formulario
    private AnticipoCliente extraerAnticipoClienteForm(HttpServletRequest request, HttpSession sesion, String action) {
        AnticipoCliente anticipoCliente = new AnticipoCliente();
        if (action.equals("actualizar")) {
            anticipoCliente.setId_anticipo_c(Integer.valueOf(request.getParameter("id_anticipo_c")));
        }
        anticipoCliente.setFecha(Date.valueOf(request.getParameter("fecha")));
        anticipoCliente.setId_cliente(request.getParameter("id_cliente"));
        anticipoCliente.setId_empleado((String) sesion.getAttribute("id_empleado"));
        anticipoCliente.setMonto_anticipo(BigDecimal.valueOf((Double.valueOf(request.getParameter("monto_anticipo")))));
        return anticipoCliente;
    }

    private void listarAnticipoClientes(
            HttpServletRequest request, HttpServletResponse response, HttpSession sesion, String action) {
        List<AnticipoCliente> anticipoClientes;
        AnticipoClienteCRUD anticipoClienteCrud = new AnticipoClienteCRUD();
        try {
            anticipoClientes = (List<AnticipoCliente>) anticipoClienteCrud.listar((String) sesion.getAttribute("id_jefe"));
            mostrarAnticipos(request, response, anticipoClientes, action);
        } catch (Exception ex) {
            System.out.println(ex);
            Logger.getLogger(AnticipoClienteController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void mostrarAnticipos(
            HttpServletRequest request, HttpServletResponse response, List<AnticipoCliente> listaAnticipos, String action) {
        //Enviamos la lista
        request.setAttribute("listaAnticipos", listaAnticipos);

        //enviamos mensaje al jsp
        request.setAttribute("mensaje", action);
        RequestDispatcher view = request.getRequestDispatcher("moduloAnticipo/anticipoCliente/anticipoClientes.jsp");
        try {
            view.forward(request, response);
        } catch (ServletException | IOException ex) {
            System.out.println("No se puede mostrar la página moduloAnticipo/anticipoCliente/anticipoClientes.jsp");
            Logger.getLogger(AnticipoClienteController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void actualizarAnticipoCliente(
            HttpServletRequest request, HttpServletResponse response, HttpSession sesion, String action) {
        AnticipoCliente anticipoCliente = extraerAnticipoClienteForm(request, sesion, action);
        AnticipoClienteCRUD anticipoClienteCRUD = new AnticipoClienteCRUD();
        try {
            anticipoClienteCRUD.actualizar(anticipoCliente);
            response.sendRedirect("/aserradero/AnticipoClienteController?action=listar");
        } catch (Exception ex) {
            System.out.println(ex);
            listarAnticipoClientes(request, response, sesion, "error_actualizar");
            Logger.getLogger(AnticipoClienteController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void buscar(HttpServletRequest request, HttpServletResponse response, HttpSession sesion, String action) {
        List<AnticipoCliente> anticipoClientes;
        String nombre_campo = request.getParameter("nombre_campo");
        String dato = request.getParameter("dato");
        AnticipoClienteCRUD anticipoClienteCRUD = new AnticipoClienteCRUD();
        try {
            anticipoClientes = (List<AnticipoCliente>) anticipoClienteCRUD.buscar(nombre_campo, dato, (String) sesion.getAttribute("id_jefe"));
            mostrarAnticipos(request, response, anticipoClientes, action);
        } catch (Exception ex) {
            System.out.println(ex);
            listarAnticipoClientes(request, response, sesion, "error_buscar_campo");
            Logger.getLogger(AnticipoClienteController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void prepararNuevoAnticipoCliente(
            HttpServletRequest request, HttpServletResponse response, HttpSession sesion, String action) {
        try {
            ClienteCRUD clienteCRUD = new ClienteCRUD();
            List<Cliente> clientes;
            clientes = (List<Cliente>) clienteCRUD.listar((String) sesion.getAttribute("id_jefe"));
            request.setAttribute("clientes", clientes);

            RequestDispatcher view = request.getRequestDispatcher("moduloAnticipo/anticipoCliente/nuevoAnticipoCliente.jsp");
            view.forward(request, response);
        } catch (Exception ex) {
            System.out.println(ex);
            listarAnticipoClientes(request, response, sesion, "error_nuevo");
            Logger.getLogger(AnticipoClienteController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void modificarAnticipoCliente(HttpServletRequest request, HttpSession sesion, HttpServletResponse response) {
        AnticipoCliente anticipoClienteEC = new AnticipoCliente();
        anticipoClienteEC.setId_anticipo_c(Integer.valueOf(request.getParameter("id_anticipo_c")));
        AnticipoClienteCRUD anticipoClienteCRUD = new AnticipoClienteCRUD();
        try {
            AnticipoCliente anticipoCliente = (AnticipoCliente) anticipoClienteCRUD.modificar(anticipoClienteEC);
            request.setAttribute("anticipoCliente", anticipoCliente);
            RequestDispatcher view = request.getRequestDispatcher("moduloAnticipo/anticipoCliente/actualizarAnticipoCliente.jsp");
            view.forward(request, response);
        } catch (Exception ex) {
            System.out.println(ex);
            listarAnticipoClientes(request, response, sesion, "error_modificar");
            Logger.getLogger(AnticipoClienteController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void eliminarAnticipoCliente(HttpServletRequest request, HttpSession sesion, HttpServletResponse response) {
        AnticipoCliente anticipoClienteEC = new AnticipoCliente();
        anticipoClienteEC.setId_anticipo_c(Integer.valueOf(request.getParameter("id_anticipo_c")));
        AnticipoClienteCRUD anticipoClienteCRUD = new AnticipoClienteCRUD();
        try {
            anticipoClienteCRUD.eliminar(anticipoClienteEC);
            response.sendRedirect("/aserradero/AnticipoClienteController?action=listar");
        } catch (Exception ex) {
            System.out.println(ex);
            listarAnticipoClientes(request, response, sesion, "error_eliminar");
            Logger.getLogger(AnticipoClienteController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
