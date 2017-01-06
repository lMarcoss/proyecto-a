package controlador.registros;

import dao.registros.ClienteCRUD;
import dao.registros.PersonaCRUD;
import entidades.registros.Cliente;
import entidades.registros.Persona;
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
public class ClienteController extends HttpServlet {

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
        } else if (rol.equals("Administrador") || rol.equals("Empleado")|| rol.equals("Vendedor")) {
            //Acción a realizar
            String action = request.getParameter("action");
            switch (action) {
                /**
                 * *************** Respuestas a métodos POST
                 * *********************
                 */
                case "insertar":
                    registrarCliente(request, response, sesion, action);
                    break;
                case "buscar":
                    buscarCliente(request, response, sesion, action);
                    break;
                /**
                 * *************** Respuestas a métodos GET
                 * *********************
                 */
                case "nuevo":
                    prepararNuevoCliente(request, response, sesion);
                    break;
                case "listar":
                    listarClientes(request, response, sesion, action);
                    break;
                case "eliminar":
                    eliminarCliente(request, response, sesion, action);
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

    private void registrarCliente(HttpServletRequest request, HttpServletResponse response, HttpSession sesion, String action) {
        Cliente cliente = extraerClienteForm(request, sesion, action);
        ClienteCRUD clienteCRUD = new ClienteCRUD();
        try {
            clienteCRUD.registrar(cliente);
            //enviar mensaje -> registrado
            response.sendRedirect("/aserradero/ClienteController?action=listar");
        } catch (Exception ex) {
            listarClientes(request, response, sesion, "error_registrar");
            System.out.println(ex);
            Logger.getLogger(ClienteController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private Cliente extraerClienteForm(HttpServletRequest request, HttpSession sesion, String action) {
        Cliente cliente = new Cliente();
        cliente.setId_persona(request.getParameter("id_persona"));
        cliente.setId_jefe((String) sesion.getAttribute("id_jefe"));
        return cliente;
    }

    private void buscarCliente(HttpServletRequest request, HttpServletResponse response, HttpSession sesion, String action) {
        List<Cliente> listaClientes;
        String nombre_campo = request.getParameter("nombre_campo");
        String dato = request.getParameter("dato");
        ClienteCRUD clienteCRUD = new ClienteCRUD();
        try {
            listaClientes = (List<Cliente>) clienteCRUD.buscar(nombre_campo, dato, (String) sesion.getAttribute("id_jefe"));
            mostrarClientes(request, response, listaClientes, action);
        } catch (Exception ex) {
            listarClientes(request, response, sesion, "error_buscar_campo");
            System.out.println(ex);
            Logger.getLogger(ClienteController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void mostrarClientes(HttpServletRequest request, HttpServletResponse response, List<Cliente> listaClientes, String action) {
        request.setAttribute("mensaje", action);
        request.setAttribute("listaClientes", listaClientes);
        RequestDispatcher view = request.getRequestDispatcher("moduloRegistros/cliente/listarClientes.jsp");
        try {
            view.forward(request, response);
        } catch (ServletException | IOException ex) {
            System.err.println("No se pudo mostrar la lista de clientes");
            Logger.getLogger(ClienteController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void prepararNuevoCliente(HttpServletRequest request, HttpServletResponse response, HttpSession sesion) {
        PersonaCRUD personaCRUD = new PersonaCRUD();
        try {
            //consultamos la lista de personas para registrarlos como cliente
            List<Persona> personas;
            personas = personaCRUD.listarPersonasPara("cliente",(String) sesion.getAttribute("id_jefe"));
            request.setAttribute("personas", personas);

            RequestDispatcher view = request.getRequestDispatcher("moduloRegistros/cliente/nuevoCliente.jsp");
            view.forward(request, response);
        } catch (ServletException | IOException ex) {
            System.out.println(ex);
            listarClientes(request, response, sesion, "error_nuevo");
            Logger.getLogger(ClienteController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            listarClientes(request, response, sesion, "error_nuevo");
            Logger.getLogger(ClienteController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void listarClientes(HttpServletRequest request, HttpServletResponse response, HttpSession sesion, String action) {
        List<Cliente> listaClientes;
        ClienteCRUD clienteCRUD = new ClienteCRUD();
        try {
            listaClientes = (List<Cliente>) clienteCRUD.listar((String) sesion.getAttribute("id_jefe"));
            mostrarClientes(request, response, listaClientes, action);
        } catch (Exception ex) {
            System.out.println(ex);
            Logger.getLogger(ClienteController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void eliminarCliente(HttpServletRequest request, HttpServletResponse response, HttpSession sesion, String action) {
        Cliente clienteEC = new Cliente();
        clienteEC.setId_cliente(request.getParameter("id_cliente"));
        clienteEC.setId_jefe((String) sesion.getAttribute("id_jefe"));

        ClienteCRUD clienteCRUD = new ClienteCRUD();
        try {
            clienteCRUD.eliminar(clienteEC);
            //enviar mensaje -> eliminado
            response.sendRedirect("/aserradero/ClienteController?action=listar");
        } catch (Exception ex) {
            listarClientes(request, response, sesion, "error_eliminar");
            System.out.println(ex);
            Logger.getLogger(ClienteController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
