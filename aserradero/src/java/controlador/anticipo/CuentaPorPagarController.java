package controlador.anticipo;

import dao.anticipo.CuentaPorPagarCRUD;
import entidades.anticipo.CuentaPorPagar;
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
public class CuentaPorPagarController extends HttpServlet {

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

                /**
                 * *************** Respuestas a métodos GET
                 * *********************
                 */
                case "listar_proveedores":
                    listarCuentaPorPagaresProveedor(request, response, sesion);
                    break;
                case "listar_clientes":
                    listarCuentaPorPagaresCliente(request, response, sesion);
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

    private void mostrarCPPagarProveedores(HttpServletRequest request, HttpServletResponse response, List<CuentaPorPagar> cuentaPorPagares) throws IOException, ServletException {
        request.setAttribute("cuentaPorPagares", cuentaPorPagares);
        RequestDispatcher view = request.getRequestDispatcher("moduloAnticipo/cuentaPorPagar/cuentaPorPagarProveedores.jsp");
        view.forward(request, response);
    }

    private void mostrarCPPagarClientes(HttpServletRequest request, HttpServletResponse response, List<CuentaPorPagar> cuentaPorPagares) throws IOException, ServletException {
        request.setAttribute("cuentaPorPagares", cuentaPorPagares);
        RequestDispatcher view = request.getRequestDispatcher("moduloAnticipo/cuentaPorPagar/cuentaPorPagarClientes.jsp");
        view.forward(request, response);
    }

    private void listarCuentaPorPagaresProveedor(HttpServletRequest request, HttpServletResponse response, HttpSession sesion) {
        List<CuentaPorPagar> cuentaPorPagares;
        CuentaPorPagarCRUD cuentaPorPagarCrud = new CuentaPorPagarCRUD();
        try {
            cuentaPorPagares = (List<CuentaPorPagar>) cuentaPorPagarCrud.listarCPProveedore((String) sesion.getAttribute("id_jefe"));
            mostrarCPPagarProveedores(request, response, cuentaPorPagares);
        } catch (Exception ex) {
            System.out.println(ex);
            Logger.getLogger(CuentaPorPagarController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void listarCuentaPorPagaresCliente(HttpServletRequest request, HttpServletResponse response, HttpSession sesion) {
        List<CuentaPorPagar> cuentaPorPagares;
        CuentaPorPagarCRUD cuentaPorPagarCrud = new CuentaPorPagarCRUD();
        try {
            cuentaPorPagares = (List<CuentaPorPagar>) cuentaPorPagarCrud.listarCPCliente((String) sesion.getAttribute("id_jefe"));
            mostrarCPPagarClientes(request, response, cuentaPorPagares);
        } catch (Exception ex) {
            System.out.println(ex);
            Logger.getLogger(CuentaPorPagarController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
