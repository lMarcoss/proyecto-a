package controlador;

import dao.empleado.UsuarioCRUD;
import entidades.empleado.Usuario;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author lmarcoss
 */
public class IniciarSesion extends HttpServlet {

    @Override
    public void init(ServletConfig conf) throws ServletException {
        super.init(conf);

    }

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
        try (PrintWriter out = response.getWriter()) {
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
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");// Forzar a usar codificación UTF-8 iso-8859-1

        // cerrar sesión
        String action = request.getParameter("action");
        if (action.equals("cerrar_sesion")) {
            HttpSession sesion = request.getSession(true);
            sesion.invalidate();
            response.sendRedirect("/aserradero/");
        } else {
            response.sendRedirect("/aserradero/");
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
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");// Forzar a usar codificación UTF-8 iso-8859-1
     
        // Se reciben formularios del médoto post
        // Form de inicio de sesión
        String nombre_usuario = request.getParameter("nombre_usuario");
        String contrasenia = request.getParameter("contrasenia");
        UsuarioCRUD usuarioCRUD = new UsuarioCRUD();
        try {
            Usuario usuario = usuarioCRUD.validarUsuario(nombre_usuario, contrasenia);
            if (usuario != null) {
                //Creamos la sesión
                HttpSession sesion = request.getSession(true);
                sesion.setAttribute("nombre_usuario", usuario.getNombre_usuario());
                sesion.setAttribute("id_empleado", usuario.getId_empleado());
                sesion.setAttribute("empleado", usuario.getEmpleado());
                sesion.setAttribute("id_jefe", usuario.getId_jefe());
                sesion.setAttribute("rol", usuario.getRol());
                sesion.setAttribute("estatus", usuario.getEstatus());
                //Mostramos la página de inicio
                response.sendRedirect("/aserradero/inicio/inicio.jsp");
            } else {
                response.sendRedirect("/aserradero/");
            }
        } catch (Exception ex) {
            System.out.println(ex);
            response.sendRedirect("/aserradero/");
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

}
