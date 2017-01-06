package controlador.anticipo;

import dao.anticipo.CuentaPorCobrarCRUD;
import entidades.anticipo.CuentaPorCobrar;
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
 * @author Marcos
 */
public class CuentaPorCobrarController extends HttpServlet {

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
            throws ServletException, IOException, Exception {
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
                case "buscar_cliente":
                    buscarCCCliente(request, response, sesion); //Buscar cuenta por cobrar de clientes
                    break;
                case "buscar_proveedor":
                    buscarCCProveedor(request, response, sesion); //Buscar cuenta por cobrar de proveedores
                    break;
                /**
                 * *************** Respuestas a métodos GET
                 * *********************
                 */
                case "listar_clientes":
                    listarCCCliente(request, response, sesion); // listar cuenta por cobrar de clientes
                    break;
                case "listar_proveedores":
                    listarCCProveedor(request, response, sesion); // listar cuenta por cobrar de proveedores
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
        try {
            processRequest(request, response);
        } catch (Exception ex) {
            Logger.getLogger(CuentaPorCobrarController.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        try {
            processRequest(request, response);
        } catch (Exception ex) {
            Logger.getLogger(CuentaPorCobrarController.class.getName()).log(Level.SEVERE, null, ex);
        }
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

    private void buscarCCCliente(HttpServletRequest request, HttpServletResponse response, HttpSession sesion) throws Exception {
        List<CuentaPorCobrar> cuentaPorCobrares;
        String nombre_campo = request.getParameter("nombre_campo");
        String dato = request.getParameter("dato");
        CuentaPorCobrarCRUD cuentaPorCobrarCRUD = new CuentaPorCobrarCRUD();
        try {
            cuentaPorCobrares = (List<CuentaPorCobrar>) cuentaPorCobrarCRUD.buscarCCCliente(nombre_campo, dato, (String) sesion.getAttribute("id_jefe"));
            mostrarCPCobrarCiente(request, response, cuentaPorCobrares);
        } catch (IOException | ServletException ex) {
            System.out.println(ex);
            listarCCCliente(request, response, sesion);
            Logger.getLogger(CuentaPorCobrarController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void mostrarCPCobrarCiente(HttpServletRequest request, HttpServletResponse response, List<CuentaPorCobrar> cuentaPorCobrares) throws ServletException, IOException {
        request.setAttribute("cuentaPorCobrares", cuentaPorCobrares);
        RequestDispatcher view = request.getRequestDispatcher("moduloAnticipo/cuentaPorCobrar/cuentaPorCobrarClientes.jsp");
        view.forward(request, response);
    }

    private void buscarCCProveedor(HttpServletRequest request, HttpServletResponse response, HttpSession sesion) throws Exception {
        List<CuentaPorCobrar> cuentaPorCobrares;
        String nombre_campo = request.getParameter("nombre_campo");
        String dato = request.getParameter("dato");

        CuentaPorCobrarCRUD cuentaPorCobrarCRUD = new CuentaPorCobrarCRUD();

        try {
            cuentaPorCobrares = (List<CuentaPorCobrar>) cuentaPorCobrarCRUD.buscarCCProveedor(nombre_campo, dato, (String) sesion.getAttribute("id_jefe"));
            mostrarCPCobrarProveedores(request, response, cuentaPorCobrares);
        } catch (IOException | ServletException ex) {
            listarCCProveedor(request, response, sesion);
            System.out.println(ex);
            Logger.getLogger(CuentaPorCobrarController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void mostrarCPCobrarProveedores(HttpServletRequest request, HttpServletResponse response, List<CuentaPorCobrar> cuentaPorCobrares) throws ServletException, IOException {
        request.setAttribute("cuentaPorCobrares", cuentaPorCobrares);
        RequestDispatcher view = request.getRequestDispatcher("moduloAnticipo/cuentaPorCobrar/cuentaPorCobrarProveedores.jsp");
        view.forward(request, response);
    }

    private void listarCCProveedor(HttpServletRequest request, HttpServletResponse response, HttpSession sesion) throws Exception {
        try {
            CuentaPorCobrarCRUD cuentaPorCobrarCrud = new CuentaPorCobrarCRUD();
            List<CuentaPorCobrar> cuentaPorCobrares = (List<CuentaPorCobrar>) cuentaPorCobrarCrud.listarCuentasPorCobrarProveedor((String) sesion.getAttribute("id_jefe"));
            mostrarCPCobrarProveedores(request, response, cuentaPorCobrares);
        } catch (ServletException | IOException ex) {
            Logger.getLogger(CuentaPorCobrarController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void listarCCCliente(HttpServletRequest request, HttpServletResponse response, HttpSession sesion) throws Exception {
        CuentaPorCobrarCRUD cuentaPorCobrarCrud = new CuentaPorCobrarCRUD();
        try {
            List<CuentaPorCobrar> cuentaPorCobrares = (List<CuentaPorCobrar>) cuentaPorCobrarCrud.listarCuentasPorCobrarCliente((String) sesion.getAttribute("id_jefe"));
            mostrarCPCobrarCiente(request, response, cuentaPorCobrares);
        } catch (ServletException | IOException ex) {
            Logger.getLogger(CuentaPorCobrarController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
