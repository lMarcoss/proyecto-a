package controlador.registros;

import dao.registros.LocalidadCRUD;
import dao.registros.PersonaCRUD;
import entidades.registros.Localidad;
import entidades.registros.Persona;
import java.io.IOException;
import java.sql.Date;
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
public class PersonaController extends HttpServlet {

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
                case "insertar":
                    registrarPersona(request, response, sesion, action);
                    break;
                case "actualizar":
                    actualizarPersona(request, response, sesion, action);
                    break;
                case "buscar":
                    buscar(request, response, sesion, action);
                    break;
                /**
                 * *************** Respuestas a métodos GET
                 * *********************
                 */
                case "nuevo":
                    prepararNuevoPersona(request, response, sesion);
                    break;
                case "listar":
                    listarPersonas(request, response, sesion, action);
                    break;
                case "modificar":
                    modificarPersona(request, response, sesion, action);
                    break;
                case "eliminar":
                    eliminarPersona(request, response, sesion, action);
                    break;
                case "buscar_persona":
                    buscarPersona(request, response, sesion, action);
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

    private void registrarPersona(HttpServletRequest request, HttpServletResponse response, HttpSession sesion, String action) {
        Persona persona = extraerPersonaForm(request, sesion, action);
        PersonaCRUD personaCRUD = new PersonaCRUD();
        try {
            personaCRUD.registrar(persona);
            //enviar mensaje -> registrado
            response.sendRedirect("/aserradero/PersonaController?action=listar");
        } catch (Exception ex) {
            listarPersonas(request, response, sesion, "error_registrar");
            System.out.println(ex);
            Logger.getLogger(PersonaController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private Persona extraerPersonaForm(HttpServletRequest request, HttpSession sesion, String action) {
        Persona persona = new Persona();
        persona.setId_persona(request.getParameter("id_persona"));
        persona.setNombre(request.getParameter("nombre"));
        persona.setApellido_paterno(request.getParameter("apellido_paterno"));
        persona.setApellido_materno(request.getParameter("apellido_materno"));
        persona.setNombre_localidad(request.getParameter("nombre_localidad"));
        persona.setNombre_municipio(request.getParameter("nombre_municipio"));
        persona.setEstado(request.getParameter("estado"));
        persona.setDireccion(request.getParameter("direccion"));
        persona.setSexo(request.getParameter("sexo"));
        persona.setFecha_nacimiento(Date.valueOf(request.getParameter(("fecha_nacimiento"))));
        persona.setTelefono(request.getParameter("telefono"));
        return persona;
    }

    private void actualizarPersona(HttpServletRequest request, HttpServletResponse response, HttpSession sesion, String action) {
        Persona persona = extraerPersonaForm(request, sesion, action);
        PersonaCRUD personaCRUD = new PersonaCRUD();
        try {
            personaCRUD.actualizar(persona);
            listarPersonas(request, response, sesion, action);
        } catch (Exception ex) {
            listarPersonas(request, response, sesion, "error_actualizar");
            Logger.getLogger(PersonaController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void buscar(HttpServletRequest request, HttpServletResponse response, HttpSession sesion, String action) {
        List<Persona> listaPersonas;
        String nombre_campo = request.getParameter("nombre_campo");
        String dato = request.getParameter("dato");
        PersonaCRUD personaCRUD = new PersonaCRUD();
        try {
            listaPersonas = (List<Persona>) personaCRUD.buscar(nombre_campo, dato, (String) sesion.getAttribute("id_jefe"), (String) sesion.getAttribute("rol"));
            mostrarPersonas(request, response, listaPersonas, action);
        } catch (Exception ex) {
            listarPersonas(request, response, sesion, "error_buscar_campo");
            System.out.println(ex);
            Logger.getLogger(PersonaController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void mostrarPersonas(HttpServletRequest request, HttpServletResponse response, List<Persona> listaPersonas, String action) {
        request.setAttribute("mensaje", action);
        request.setAttribute("listaPersonas", listaPersonas);
        RequestDispatcher view = request.getRequestDispatcher("moduloRegistros/persona/listarPersonas.jsp");
        try {
            view.forward(request, response);
        } catch (ServletException | IOException ex) {
            System.err.println("No se pudo mostrar la lista de personas");
            Logger.getLogger(PersonaController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void prepararNuevoPersona(HttpServletRequest request, HttpServletResponse response, HttpSession sesion) {
        try {
            LocalidadCRUD localidadCRUD = new LocalidadCRUD();
            List<Localidad> localidades;
            localidades = (List<Localidad>) localidadCRUD.listar((String) sesion.getAttribute("id_jefe"), (String) sesion.getAttribute("rol"));
            request.setAttribute("localidades", localidades);
            RequestDispatcher view = request.getRequestDispatcher("moduloRegistros/persona/nuevoPersona.jsp");
            view.forward(request, response);
        } catch (Exception ex) {
            listarPersonas(request, response, sesion, "error_nuevo");
            System.out.println(ex);
            Logger.getLogger(PersonaController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void listarPersonas(HttpServletRequest request, HttpServletResponse response, HttpSession sesion, String action) {
        List<Persona> listaPersonas;
        PersonaCRUD personaCRUD = new PersonaCRUD();
        try {
            listaPersonas = (List<Persona>) personaCRUD.listar((String) sesion.getAttribute("id_jefe"), (String) sesion.getAttribute("rol"));
            mostrarPersonas(request, response, listaPersonas, action);
        } catch (Exception ex) {
            System.out.println(ex);
            Logger.getLogger(PersonaController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void modificarPersona(HttpServletRequest request, HttpServletResponse response, HttpSession sesion, String action) {
        Persona personaEC = new Persona();
        personaEC.setId_persona(request.getParameter("id_persona"));
        PersonaCRUD personaCRUD = new PersonaCRUD();
        try {
            Persona persona = (Persona) personaCRUD.modificar(personaEC);
            request.setAttribute("persona", persona);
            RequestDispatcher view = request.getRequestDispatcher("moduloRegistros/persona/actualizarPersona.jsp");
            view.forward(request, response);
        } catch (Exception ex) {
            listarPersonas(request, response, sesion, "error_modificar");
            Logger.getLogger(PersonaController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void eliminarPersona(HttpServletRequest request, HttpServletResponse response, HttpSession sesion, String action) {
        Persona personaEC = new Persona();
        personaEC.setId_persona(request.getParameter("id_persona"));
        PersonaCRUD personaCRUD = new PersonaCRUD();
        try {
            personaCRUD.eliminar(personaEC);
            listarPersonas(request, response, sesion, action);
        } catch (Exception ex) {
            listarPersonas(request, response, sesion, "error_eliminar");
            Logger.getLogger(PersonaController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void buscarPersona(HttpServletRequest request, HttpServletResponse response, HttpSession sesion, String action) {
        List<Persona> listaPersonas;
        String id_persona = request.getParameter("id_persona");
        id_persona = id_persona.substring(0, 18);
        PersonaCRUD personaCRUD = new PersonaCRUD();
        try {
            listaPersonas = (List<Persona>) personaCRUD.buscarPorId(id_persona);
            mostrarPersonas(request, response, listaPersonas, action);
        } catch (Exception ex) {
            listarPersonas(request, response, sesion, "error_buscar_id");
            System.out.println(ex);
            Logger.getLogger(PersonaController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
