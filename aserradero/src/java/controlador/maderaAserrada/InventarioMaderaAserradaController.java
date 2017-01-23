package controlador.maderaAserrada;

import dao.maderaAserrada.InventarioMaderaAserradaCRUD;
import entidades.maderaAserrada.InventarioMaderaAserrada;
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
public class InventarioMaderaAserradaController extends HttpServlet {

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
                case "buscar":
                    buscarInventarioMaderaAserrada(request, response, sesion, action);
                    break;
                /**
                 * *************** Respuestas a métodos GET
                 * *********************
                 */
                case "listar":
                    listarInventarioMaderaAserrada(request, response, sesion, action);
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

    private void buscarInventarioMaderaAserrada(HttpServletRequest request, HttpServletResponse response, HttpSession sesion, String action) {
        List<InventarioMaderaAserrada> inventarioMaderaAserrada;
        String nombre_campo = request.getParameter("nombre_campo");
        String dato = request.getParameter("dato");
        if (dato.equals("")) {
            listarInventarioMaderaAserrada(request, response, sesion, action);
        } else {
            InventarioMaderaAserradaCRUD inventarioMaderaAserradaCRUD = new InventarioMaderaAserradaCRUD();
            try {
                inventarioMaderaAserrada = (List<InventarioMaderaAserrada>) inventarioMaderaAserradaCRUD.buscar(nombre_campo, dato, (String) sesion.getAttribute("id_jefe"), (String) sesion.getAttribute("rol"));
                mostrarInventario(request, response, inventarioMaderaAserrada, null, sesion, action);

            } catch (Exception ex) {
                System.out.println(ex);
                listarInventarioMaderaAserrada(request, response, sesion, "error_buscar_campo");
                Logger.getLogger(InventarioMaderaAserradaController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    private void mostrarInventario(HttpServletRequest request, HttpServletResponse response, List<InventarioMaderaAserrada> listaInventario, InventarioMaderaAserrada inventarioTotal, HttpSession sesion, String action) {
        request.setAttribute("listaInventario", listaInventario);
        request.setAttribute("inventarioTotal", inventarioTotal);
        request.setAttribute("mensaje", action);
        RequestDispatcher view = request.getRequestDispatcher("moduloMaderaAserrada/inventarioMaderaAserrada/listarInventario.jsp");
        try {
            view.forward(request, response);
        } catch (ServletException | IOException ex) {
            System.out.println(ex);
            Logger.getLogger(InventarioMaderaAserradaController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void listarInventarioMaderaAserrada(HttpServletRequest request, HttpServletResponse response, HttpSession sesion, String action) {
        List<InventarioMaderaAserrada> inventarioMaderaAserrada;
        InventarioMaderaAserrada inventarioTotal;
        InventarioMaderaAserradaCRUD inventarioMaderaAserradaCrud = new InventarioMaderaAserradaCRUD();
        try {
            inventarioMaderaAserrada = (List<InventarioMaderaAserrada>) inventarioMaderaAserradaCrud.listar((String) sesion.getAttribute("id_jefe"), (String) sesion.getAttribute("rol"));
            if (!inventarioMaderaAserrada.isEmpty()) {
                inventarioTotal = (InventarioMaderaAserrada) inventarioMaderaAserradaCrud.consultaTotalInventario((String) sesion.getAttribute("id_jefe"));
            } else {
                inventarioTotal = null;
            }
            mostrarInventario(request, response, inventarioMaderaAserrada, inventarioTotal, sesion, action);
        } catch (Exception ex) {
            System.out.println(ex);
            Logger.getLogger(InventarioMaderaAserradaController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
