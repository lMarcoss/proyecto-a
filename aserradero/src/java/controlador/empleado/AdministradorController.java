package controlador.empleado;

import dao.empleado.AdministradorCRUD;
import dao.registros.PersonaCRUD;
import entidades.empleado.Administrador;
import entidades.registros.Persona;
import java.io.IOException;
import java.math.BigDecimal;
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
public class AdministradorController extends HttpServlet {

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
                case "insertar":
                    registrarAdministrador(request, response, sesion, action);
                    break;
                case "actualizar":
                    actualizarAdministrador(request, response, sesion, action);
                    break;
                case "buscar":
                    buscarAdministrador(request, response, sesion, action);
                    break;
                /**
                 * *************** Respuestas a métodos GET
                 * *********************
                 */
                case "nuevo":
                    prepararNuevoAdministrador(request, response, sesion, action);
                    break;
                case "listar":
                    listarAdministradores(request, response, sesion, action);
                    break;
                case "modificar":
                    modificarAdministrador(request, response, sesion, action);
                    break;
                case "eliminar":
                    eliminarAdministrador(request, response, sesion, action);
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

    // Extraer datos del formulario
    private Administrador extraerAdministradorForm(HttpServletRequest request) {
        Administrador administrador = new Administrador();
        administrador.setId_administrador(request.getParameter("id_administrador"));
        administrador.setCuenta_inicial(BigDecimal.valueOf((Double.valueOf(request.getParameter("cuenta_inicial")))));
        return administrador;
    }

    private void registrarAdministrador(HttpServletRequest request, HttpServletResponse response, HttpSession sesion, String action) {
        Administrador administrador = extraerAdministradorForm(request);
        AdministradorCRUD administradorCRUD = new AdministradorCRUD();
        try {
            administradorCRUD.registrar(administrador);
            //enviar mensaje -> registrado
            response.sendRedirect("/aserradero/AdministradorController?action=listar");
        } catch (Exception ex) {
            listarAdministradores(request, response, sesion, "error_registrar");
            System.out.println(ex);
            Logger.getLogger(AdministradorController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void actualizarAdministrador(HttpServletRequest request, HttpServletResponse response, HttpSession sesion, String action) {
        Administrador administrador = extraerAdministradorForm(request);
        AdministradorCRUD administradorCRUD = new AdministradorCRUD();
        try {
            administradorCRUD.actualizar(administrador);
            //enviar mensaje -> actualizado
            response.sendRedirect("/aserradero/AdministradorController?action=listar");
        } catch (Exception ex) {
            listarAdministradores(request, response, sesion, "error_actualizar");
            System.out.println(ex);
            Logger.getLogger(AdministradorController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void buscarAdministrador(HttpServletRequest request, HttpServletResponse response, HttpSession sesion, String action) {
        try {
            List<Administrador> administradores;
            String nombre_campo = request.getParameter("nombre_campo");
            String dato = request.getParameter("dato");
            AdministradorCRUD administradorCRUD = new AdministradorCRUD();
            administradores = (List<Administrador>) administradorCRUD.buscar(nombre_campo, dato, (String) sesion.getAttribute("id_jefe"));
            mostrarListaAdministradores(request, response, administradores, action);
        } catch (Exception ex) {
            System.out.println(ex);
            Logger.getLogger(AdministradorController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void mostrarListaAdministradores(HttpServletRequest request, HttpServletResponse response, List<Administrador> listaAdministradores, String action) {
        request.setAttribute("mensaje", action);
        request.setAttribute("listaAdministradores", listaAdministradores);
        RequestDispatcher view = request.getRequestDispatcher("moduloEmpleado/administrador/listarAdministradores.jsp");
        try {
            view.forward(request, response);
        } catch (ServletException | IOException ex) {
            System.err.println("No se pudo mostrar la listaAdministrador");
            Logger.getLogger(AdministradorController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void prepararNuevoAdministrador(HttpServletRequest request, HttpServletResponse response, HttpSession sesion, String action) {
        try {
            PersonaCRUD personaCRUD = new PersonaCRUD();
            List<Persona> personas;
            personas = (List<Persona>) personaCRUD.listarPersonasPara("administrador", (String) sesion.getAttribute("id_jefe"));
            request.setAttribute("personas", personas);

            RequestDispatcher view = request.getRequestDispatcher("moduloEmpleado/administrador/nuevoAdministrador.jsp");
            view.forward(request, response);
        } catch (Exception ex) {
            listarAdministradores(request, response, sesion, "error_nuevo");
            System.out.println(ex);
            Logger.getLogger(AdministradorController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void listarAdministradores(HttpServletRequest request, HttpServletResponse response, HttpSession sesion, String action) {
        List<Administrador> administradores;
        AdministradorCRUD administradorCrud = new AdministradorCRUD();
        try {
            administradores = (List<Administrador>) administradorCrud.listar((String) sesion.getAttribute("id_jefe"));
            mostrarListaAdministradores(request, response, administradores, action);
        } catch (Exception ex) {
            System.out.println(ex);
            Logger.getLogger(AdministradorController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void modificarAdministrador(HttpServletRequest request, HttpServletResponse response, HttpSession sesion, String action) {
        Administrador administradorEC = new Administrador();
        administradorEC.setId_administrador(request.getParameter("id_administrador"));
        AdministradorCRUD administradorCRUD = new AdministradorCRUD();
        try {
            Administrador administrador = (Administrador) administradorCRUD.modificar(administradorEC);
            request.setAttribute("administrador", administrador);
            RequestDispatcher view = request.getRequestDispatcher("moduloEmpleado/administrador/actualizarAdministrador.jsp");
            view.forward(request, response);
        } catch (Exception ex) {
            listarAdministradores(request, response, sesion, "error_modificar");
            System.out.println(ex);
            Logger.getLogger(AdministradorController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void eliminarAdministrador(HttpServletRequest request, HttpServletResponse response, HttpSession sesion, String action) {
        Administrador administradorEliminar = new Administrador();
        administradorEliminar.setId_administrador(request.getParameter("id_administrador"));

        AdministradorCRUD administradorCRUD = new AdministradorCRUD();
        try {
            administradorCRUD.eliminar(administradorEliminar);
            //enviar mensaje -> eliminado
            response.sendRedirect("/aserradero/AdministradorController?action=listar");
        } catch (Exception ex) {
            listarAdministradores(request, response, sesion, "error_eliminar");
            System.out.println(ex);
            Logger.getLogger(AdministradorController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
