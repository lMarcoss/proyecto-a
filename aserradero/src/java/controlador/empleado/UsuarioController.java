package controlador.empleado;

import dao.empleado.UsuarioCRUD;
import dao.empleado.EmpleadoCRUD;
import dao.registros.LocalidadCRUD;
import dao.registros.MunicipioCRUD;
import entidades.empleado.Empleado;
import entidades.empleado.Usuario;
import entidades.registros.Localidad;
import entidades.registros.Municipio;
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
            throws ServletException, IOException, Exception {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");// Forzar a usar codificación UTF-8 iso-8859-1

        String action = request.getParameter("action");//Acción a realizar

        if ((action.equals("insertar_primer_registro") || action.equals("nuevo_primer_registro")) && !existeUsuario()) {
            //Registro inicial al sistema
            switch (action) {
                /**
                 * *************** Respuestas a métodos POST
                 * *********************
                 */
                case "insertar_primer_registro":
                    registrarUsuario(request, response, null, action);
                    break;
                /**
                 * *************** Respuestas a métodos GET
                 * *********************
                 */
                case "nuevo_primer_registro":
                    prepararRegistroInicial(request, response);
                    break;
            }

        } else {
            try {
                HttpSession sesion = request.getSession(false);
                String nombre_usuario = (String) sesion.getAttribute("nombre_usuario");
                String rol = (String) sesion.getAttribute("rol");
                if (nombre_usuario.equals("")) {
                    response.sendRedirect("/aserradero/");
                } else if (rol.equals("Administrador")) {
                    switch (action) {
                        /**
                         * *************** Respuestas a métodos POST
                         * *********************
                         */
                        case "insertar":
                            registrarUsuario(request, response, sesion, action);
                            break;
                        case "actualizar":
                            actualizarUsuario(request, response, sesion, action);
                            break;
                        /**
                         * *************** Respuestas a métodos GET
                         * *********************
                         */
                        case "nuevo":
                            prepararNuevoUsuario(request, response, sesion, true);
                            break;
                        case "listar":
                            listarUsuarios(request, response, sesion, action);
                            break;
                        case "modificar":
                            modificarUsuario(request, response, sesion, action);
                            break;
                        case "eliminar":
                            eliminarUsuario(request, response, sesion, action);
                            break;
                    }
                } else {
                    response.sendRedirect("/aserradero/");
                }
            } catch (Exception e) {
                System.out.println(e);
                response.sendRedirect("/aserradero/");
            }
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
            Logger.getLogger(UsuarioController.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        try {
            processRequest(request, response);
        } catch (Exception ex) {
            Logger.getLogger(UsuarioController.class.getName()).log(Level.SEVERE, null, ex);
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

    private void registrarUsuario(HttpServletRequest request, HttpServletResponse response, HttpSession sesion, String action) {
        Usuario usuario = extraerUsuarioForm(request);
        UsuarioCRUD usuarioCRUD = new UsuarioCRUD();
        try {
            usuarioCRUD.registrar(usuario);
            response.sendRedirect("/aserradero/UsuarioController?action=listar");
        } catch (Exception ex) {
            listarUsuarios(request, response, sesion, "error_registrar");
            Logger.getLogger(UsuarioController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // Extraer datos del formulario
    private Usuario extraerUsuarioForm(HttpServletRequest request) {
        Usuario usuario = new Usuario();
        usuario.setId_empleado(request.getParameter("id_empleado"));
        usuario.setNombre_usuario(request.getParameter("nombre_usuario"));
        usuario.setContrasenia(request.getParameter("contrasenia"));
        return usuario;
    }

    private void actualizarUsuario(HttpServletRequest request, HttpServletResponse response, HttpSession sesion, String action) {
        Usuario usuario = extraerUsuarioForm(request);
        UsuarioCRUD usuarioCRUD = new UsuarioCRUD();
        try {
            usuarioCRUD.actualizar(usuario);
            response.sendRedirect("/aserradero/UsuarioController?action=listar");
        } catch (Exception ex) {
            listarUsuarios(request, response, sesion, "error_actualizar");
            Logger.getLogger(UsuarioController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void prepararNuevoUsuario(HttpServletRequest request, HttpServletResponse response, HttpSession sesion, boolean existeUsuario) throws Exception {

        EmpleadoCRUD empleadoCRUD = new EmpleadoCRUD();
        try {
            List<Empleado> empleados = (List<Empleado>) empleadoCRUD.empleadosParaUsuario((String) sesion.getAttribute("id_jefe"));
            request.setAttribute("empleados", empleados);
            RequestDispatcher view = request.getRequestDispatcher("moduloEmpleado/usuario/nuevoUsuario.jsp");
            view.forward(request, response);
        } catch (Exception ex) {
            listarUsuarios(request, response, sesion, "error_nuevo");
            Logger.getLogger(UsuarioController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    //Mostrar lista de usuarios
    private void listarUsuarios(HttpServletRequest request, HttpServletResponse response, HttpSession sesion, String action) {
        List<Usuario> usuarios;
        UsuarioCRUD usuariocrud = new UsuarioCRUD();
        try {
            usuarios = (List<Usuario>) usuariocrud.listar((String) sesion.getAttribute("id_jefe"));
            mostrarUsuarios(request, response, usuarios, action);
        } catch (Exception ex) {
            System.out.println(ex);
            Logger.getLogger(UsuarioController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void mostrarUsuarios(HttpServletRequest request, HttpServletResponse response, List<Usuario> usuarios, String action) {
        //Enviamos las listas al jsp
        try {
            request.setAttribute("usuarios", usuarios);
            request.setAttribute("mensaje", action);
            RequestDispatcher view;
            view = request.getRequestDispatcher("moduloEmpleado/usuario/listarUsuarios.jsp");
            view.forward(request, response);
        } catch (ServletException | IOException ex) {
            Logger.getLogger(UsuarioController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void modificarUsuario(HttpServletRequest request, HttpServletResponse response, HttpSession sesion, String action) {
        Usuario usuarioEC = new Usuario();
        usuarioEC.setNombre_usuario(request.getParameter("nombre_usuario"));
        UsuarioCRUD usuarioCRUD = new UsuarioCRUD();
        try {
            Usuario usuario = usuarioCRUD.modificar(usuarioEC);
            request.setAttribute("usuario", usuario);
            RequestDispatcher view = request.getRequestDispatcher("moduloEmpleado/usuario/modificarUsuario.jsp");
            view.forward(request, response);
        } catch (Exception ex) {
            listarUsuarios(request, response, sesion, "error_modificar");
            Logger.getLogger(UsuarioController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void eliminarUsuario(HttpServletRequest request, HttpServletResponse response, HttpSession sesion, String action) {
        Usuario usuarioEC = new Usuario();
        usuarioEC.setNombre_usuario(request.getParameter("nombre_usuario"));
        UsuarioCRUD usuarioCRUD = new UsuarioCRUD();
        try {
            usuarioCRUD.eliminar(usuarioEC);
            response.sendRedirect("/aserradero/UsuarioController?action=listar");
        } catch (Exception ex) {
            listarUsuarios(request, response, sesion, "error_eliminar");
            Logger.getLogger(UsuarioController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private boolean existeUsuario() throws Exception {
        UsuarioCRUD usuarioCRUD = new UsuarioCRUD();
        return usuarioCRUD.existeUsuario();
    }

    private void prepararRegistroInicial(HttpServletRequest request, HttpServletResponse response) throws ServletException, Exception {
        /*
        * Si no existe usuario: Registramos un municipio, localidad, datos personales, y el usuario a registrar va a ser un administrador
         */
        try {
            //Municipios existentes
            MunicipioCRUD municipioCRUD = new MunicipioCRUD();
            List<Municipio> listaMunicipios = (List<Municipio>) municipioCRUD.listar();
            //Localidades existentes
            LocalidadCRUD localidadCRUD = new LocalidadCRUD();
            List<Localidad> listaLocalidades = localidadCRUD.listar(null, null);

            //Enviamos las listas
            request.setAttribute("listaMunicipios", listaMunicipios);
            request.setAttribute("listaLocalidades", listaLocalidades);
            RequestDispatcher view = request.getRequestDispatcher("moduloEmpleado/usuario/primer_usuario.jsp");
            view.forward(request, response);
        } catch (IOException ex) {
            System.out.println("no se puede registrar nuevo registro inicial");
            Logger.getLogger(UsuarioController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
