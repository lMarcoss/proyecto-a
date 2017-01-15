package controlador.cuenta;

import dao.cuenta.BalanceCuentaCRUD;
import entidades.cuenta.BalanceCuenta;
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
public class BalanceCuentaController extends HttpServlet {

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
                case "listar":
                    listarBalanceCuenta(request, response, sesion, action);
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

    private void listarBalanceCuenta(HttpServletRequest request, HttpServletResponse response, HttpSession sesion, String action) {
        List<BalanceCuenta> listaBalance;
        BalanceCuentaCRUD balanceCRUD = new BalanceCuentaCRUD();
        String cuenta_total;
        try {
            listaBalance = (List<BalanceCuenta>) balanceCRUD.listar((String) sesion.getAttribute("id_jefe"), (String) sesion.getAttribute("rol"));
            cuenta_total = (String) balanceCRUD.consultar_cuenta_total((String) sesion.getAttribute("id_jefe"), (String) sesion.getAttribute("rol"));
            mostrarBalanceCuentas(request, response, listaBalance, cuenta_total, action);
        } catch (Exception ex) {
            System.out.println(ex);
            Logger.getLogger(BalanceCuentaController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void mostrarBalanceCuentas(HttpServletRequest request, HttpServletResponse response, List<BalanceCuenta> listaBalance, String cuenta_total, String action) {
        request.setAttribute("listaBalance", listaBalance);
        request.setAttribute("cuenta_total", cuenta_total);
        RequestDispatcher view = request.getRequestDispatcher("moduloCuenta/balanceCuenta/listarBalanceCuenta.jsp");
        try {
            view.forward(request, response);
        } catch (ServletException | IOException ex) {
            System.err.println("No se pudo mostrar el balance de cuentas");
            Logger.getLogger(BalanceCuentaController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
