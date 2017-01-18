package controlador.empleado;

import dao.empleado.EmpleadoCRUD;
import dao.registros.PersonaCRUD;
import entidades.empleado.Empleado;
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
public class EmpleadoController extends HttpServlet {

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
                    registrarEmpleado(request, response, sesion, action);
                    break;
                case "actualizar":
                    actualizarEmpleado(request, response, sesion, action);
                    break;
                /**
                 * *************** Respuestas a métodos GET
                 * *********************
                 */
                case "nuevo":
                    prepararNuevoEmpleado(request, response, sesion);
                    break;
                case "listar":
                    listarEmpleados(request, response, sesion, action);
                    break;
                case "modificar":
                    modificarEmpleado(request, response, sesion, action);
                    break;
                case "eliminar":
                    eliminarEmpleado(request, response, sesion, action);
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

    private void registrarEmpleado(HttpServletRequest request, HttpServletResponse response, HttpSession sesion, String action) {
        Empleado empleado = extraerEmpleadoForm(request, sesion, action);
        EmpleadoCRUD empleadoCRUD = new EmpleadoCRUD();
        try {
            empleadoCRUD.registrar(empleado);
            //enviar mensaje -> registrado
            response.sendRedirect("/aserradero/EmpleadoController?action=listar");
        } catch (Exception ex) {
            listarEmpleados(request, response, sesion, "error_registrar");
            System.out.println(ex);
            Logger.getLogger(EmpleadoController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private Empleado extraerEmpleadoForm(HttpServletRequest request, HttpSession sesion, String action) {
        Empleado empleado = new Empleado();
        empleado.setId_empleado(request.getParameter("id_empleado"));
        empleado.setId_persona(request.getParameter("id_persona"));
        empleado.setId_jefe((String) sesion.getAttribute("id_jefe"));//Para empleado Administrador no aplica al insertar: el trigger de la BD lo modifica al insertar
        empleado.setRol(request.getParameter("rol"));
        empleado.setEstatus(request.getParameter("estatus"));
        return empleado;
    }

    private void actualizarEmpleado(HttpServletRequest request, HttpServletResponse response, HttpSession sesion, String action) {
        Empleado empleado = extraerEmpleadoForm(request, sesion, action);
        EmpleadoCRUD empleadoCRUD = new EmpleadoCRUD();
        try {
            empleadoCRUD.actualizar(empleado);
            //enviar mensaje -> actualizado
            response.sendRedirect("/aserradero/EmpleadoController?action=listar");
        } catch (Exception ex) {
            listarEmpleados(request, response, sesion, "error_actualizar");
            System.out.println(ex);
            Logger.getLogger(EmpleadoController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void mostrarEmpleados(HttpServletRequest request, HttpServletResponse response, List<Empleado> listaEmpleados, String action) {
        request.setAttribute("mensaje", action);
        request.setAttribute("listaEmpleados", listaEmpleados);
        RequestDispatcher view = request.getRequestDispatcher("moduloEmpleado/empleado/listarEmpleados.jsp");
        try {
            view.forward(request, response);
        } catch (ServletException | IOException ex) {
            System.err.println("No se pudo mostrar la lista de empleados");
            Logger.getLogger(EmpleadoController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void prepararNuevoEmpleado(HttpServletRequest request, HttpServletResponse response, HttpSession sesion) {
        try {
            //Enviamos las personas a seleccionar para asignarlos como empleado
            PersonaCRUD personaCRUD = new PersonaCRUD();
            List<Persona> personas;
            personas = (List<Persona>) personaCRUD.listar((String) sesion.getAttribute("id_jefe"), (String) sesion.getAttribute("rol"));
            request.setAttribute("personas", personas);

            RequestDispatcher view = request.getRequestDispatcher("moduloEmpleado/empleado/nuevoEmpleado.jsp");
            view.forward(request, response);
        } catch (Exception ex) {
            listarEmpleados(request, response, sesion, "error_nuevo");
            System.out.println(ex);
            Logger.getLogger(EmpleadoController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void listarEmpleados(HttpServletRequest request, HttpServletResponse response, HttpSession sesion, String action) {
        List<Empleado> listaEmpleados;
        EmpleadoCRUD empleadoCRUD = new EmpleadoCRUD();
        try {
            listaEmpleados = (List<Empleado>) empleadoCRUD.listar((String) sesion.getAttribute("id_jefe"), (String) sesion.getAttribute("rol"));
            mostrarEmpleados(request, response, listaEmpleados, action);
        } catch (Exception ex) {
            System.out.println(ex);
            Logger.getLogger(EmpleadoController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void modificarEmpleado(HttpServletRequest request, HttpServletResponse response, HttpSession sesion, String action) {
        Empleado empleadoEC = extraerClavesEmpleado(request, sesion);
        EmpleadoCRUD empleadoCRUD = new EmpleadoCRUD();
        try {
            //Enviamos empleado a modificar
            Empleado empleado = (Empleado) empleadoCRUD.modificar(empleadoEC);
            request.setAttribute("empleado", empleado);
            RequestDispatcher view = request.getRequestDispatcher("moduloEmpleado/empleado/actualizarEmpleado.jsp");
            view.forward(request, response);
        } catch (Exception ex) {
            listarEmpleados(request, response, sesion, "error_modificar");
            System.out.println(ex);
            Logger.getLogger(EmpleadoController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private Empleado extraerClavesEmpleado(HttpServletRequest request, HttpSession sesion) {
        Empleado empleado = new Empleado();
        empleado.setId_empleado(request.getParameter("id_empleado"));
        empleado.setId_jefe((String) sesion.getAttribute("id_jefe"));
        empleado.setRol(request.getParameter("rol"));
        return empleado;
    }

    private void eliminarEmpleado(HttpServletRequest request, HttpServletResponse response, HttpSession sesion, String action) {
        Empleado empleadoEC = extraerClavesEmpleado(request, sesion);
        EmpleadoCRUD empleadoCRUD = new EmpleadoCRUD();
        try {
            empleadoCRUD.eliminar(empleadoEC);
            //enviar mensaje -> eliminado
            response.sendRedirect("/aserradero/EmpleadoController?action=listar");
        } catch (Exception ex) {
            listarEmpleados(request, response, sesion, "error_eliminar");
            System.out.println(ex);
            Logger.getLogger(EmpleadoController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
