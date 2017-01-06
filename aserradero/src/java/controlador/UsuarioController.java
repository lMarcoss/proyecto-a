package controlador;

import dao.empleado.EmpleadoCRUD;
import dao.UsuarioCRUD;
import entidades.empleado.Empleado;
import entidades.Usuario;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author lmarcoss
 */
public class UsuarioController extends HttpServlet {

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
        //Llegan url
        String action = request.getParameter("action");
        Usuario usuarioEC; //Enviar al CRUD
        Usuario usuario; //Respuesta del CRUD
        UsuarioCRUD usuarioCRUD;
        switch(action){
            case "nuevo"://Enviar la lista de empleados al jsp nuevo usuario para seleccionar el empleado a crear usuario
                EmpleadoCRUD empleadoCRUD = new EmpleadoCRUD();
                List<Empleado> empleados;
                try {
                    empleados = (List<Empleado>)empleadoCRUD.listar("");
                    request.setAttribute("empleados",empleados);
                    RequestDispatcher view = request.getRequestDispatcher("usuario/nuevoUsuario.jsp");
                    view.forward(request,response);
                } catch (Exception ex) {
                    listarUsuarios(request, response,"error_nuevo");
                    Logger.getLogger(UsuarioController.class.getName()).log(Level.SEVERE, null, ex);
                }
                break;
            case "listar":
                listarUsuarios(request, response,"");
                break;
            case "modificar":
                usuarioEC = new Usuario();
                usuarioEC.setNombre_usuario(request.getParameter("nombre_usuario"));
                usuarioCRUD = new UsuarioCRUD();
                try {
                    usuario = usuarioCRUD.modificar(usuarioEC);
                    request.setAttribute("usuario",usuario);
                    RequestDispatcher view = request.getRequestDispatcher("usuario/actualizarUsuario.jsp");
                    view.forward(request,response);
                } catch (Exception ex) {
                    listarUsuarios(request, response, "error_modificar");
                    Logger.getLogger(UsuarioController.class.getName()).log(Level.SEVERE, null, ex);
                }
                break;
            case "eliminar":
                usuarioEC = new Usuario();
                usuarioEC.setNombre_usuario(request.getParameter("nombre_usuario"));
                usuarioCRUD = new UsuarioCRUD();
                try {
                    usuarioCRUD.eliminar(usuarioEC);
                    listarUsuarios(request, response,"eliminado");
                } catch (Exception ex) {
                    listarUsuarios(request, response, "error_eliminar");
                    Logger.getLogger(UsuarioController.class.getName()).log(Level.SEVERE, null, ex);
                }
                break;                        
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
        //Llegan formularios de tipo post
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");// Forzar a usar codificación UTF-8 iso-8859-1
        
        Usuario usuario;
        UsuarioCRUD usuarioCRUD;
        
        String action = request.getParameter("action");
        switch(action){
            case "nuevo":
                usuario = extraerUsuarioForm(request);
                usuarioCRUD = new UsuarioCRUD();
                try {
                    usuarioCRUD.registrar(usuario);
                    listarUsuarios(request, response,"registrado");
                } catch (Exception ex) {
                    listarUsuarios(request, response, "error_registrar");
                    Logger.getLogger(UsuarioController.class.getName()).log(Level.SEVERE, null, ex);
                }
                break;
            case "actualizar":
                usuario = extraerUsuarioForm(request);
                usuarioCRUD = new UsuarioCRUD();
                try {
                    usuarioCRUD.actualizar(usuario);
                    listarUsuarios(request, response,"actualizado");
                } catch (Exception ex) {
                    listarUsuarios(request, response, "error_actualizar");
                    Logger.getLogger(UsuarioController.class.getName()).log(Level.SEVERE, null, ex);
                }
                break;
            case "buscar":
                List <Usuario> usuarios;
                String nombre_campo = request.getParameter("nombre_campo");
                String dato = request.getParameter("dato");
                usuarioCRUD = new UsuarioCRUD();
                try {
                    usuarios = (List<Usuario>)usuarioCRUD.buscar(nombre_campo, dato);
                    request.setAttribute("usuarios",usuarios);
                    RequestDispatcher view = request.getRequestDispatcher("usuario/usuarios.jsp");
                    view.forward(request,response);
                } catch (Exception ex) {
                    listarUsuarios(request, response, "error_buscar_campo");
                    Logger.getLogger(UsuarioController.class.getName()).log(Level.SEVERE, null, ex);
                }
                break;
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

    //Mostrar lista de usuarios
    private void listarUsuarios(HttpServletRequest request, HttpServletResponse response, String mensaje) {
        List<Usuario> usuarios;
        UsuarioCRUD usuariocrud = new UsuarioCRUD();
        try {
            usuarios = (List<Usuario>)usuariocrud.listar();
            System.out.println("Hola Usuario listar");
            //Enviamos las listas al jsp
            request.setAttribute("usuarios",usuarios);
            request.setAttribute("mensaje",mensaje);
            RequestDispatcher view;
            view = request.getRequestDispatcher("usuario/usuarios.jsp");
            view.forward(request,response);
        } catch (Exception ex) {
            System.out.println(ex);
            Logger.getLogger(UsuarioController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    // Extraer datos del formulario
    private Usuario extraerUsuarioForm(HttpServletRequest request) {
        Usuario usuario = new Usuario();
        usuario.setId_empleado(request.getParameter("id_empleado"));
        usuario.setNombre_usuario(request.getParameter("nombre_usuario"));
        usuario.setContrasenia(request.getParameter("contrasenia"));
        usuario.setEmail(request.getParameter("email"));
        return usuario;
    }

}
