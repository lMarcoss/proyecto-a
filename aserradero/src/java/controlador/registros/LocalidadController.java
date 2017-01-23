package controlador.registros;

import dao.registros.LocalidadCRUD;
import dao.registros.MunicipioCRUD;
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
public class LocalidadController extends HttpServlet {

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
                    registrarLocalidad(request, response, sesion, action);
                    break;
                case "actualizar":
                    actualizarLocalidad(request, response, sesion, action);
                    break;
                /**
                 * *************** Respuestas a métodos GET
                 * *********************
                 */
                case "nuevo":
                    prepararNuevoLocalidad(request, response, sesion);
                    break;
                case "listar":
                    listarLocalidades(request, response, sesion, action);
                    break;
                case "modificar":
                    modificarLocalidad(request, response, sesion, action);
                    break;
                case "eliminar":
                    eliminarLocalidad(request, response, sesion, action);
                    break;
                case "buscar_localidad":
                    buscarLocalidad(request, response, sesion, action);
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

    private void registrarLocalidad(HttpServletRequest request, HttpServletResponse response, HttpSession sesion, String action) {
        Localidad localidad = extraerLocalidadForm(request, sesion, action);
        LocalidadCRUD localidadCRUD = new LocalidadCRUD();
        try {
            localidadCRUD.registrar(localidad);
            listarLocalidades(request, response, sesion, action);
        } catch (Exception ex) {
            listarLocalidades(request, response, sesion, "error_registrar");
            System.out.println(ex);
            Logger.getLogger(LocalidadController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //Extraer datos de localidad en el formulario HTML
    private Localidad extraerLocalidadForm(HttpServletRequest request, HttpSession sesion, String action) {
        Localidad localidad = new Localidad();
        localidad.setNombre_localidad(request.getParameter("nombre_localidad"));
        localidad.setNombre_municipio(request.getParameter("nombre_municipio"));
        localidad.setEstado(request.getParameter("estado"));
        localidad.setTelefono_localidad(request.getParameter("telefono"));
        return localidad;
    }

    private void actualizarLocalidad(HttpServletRequest request, HttpServletResponse response, HttpSession sesion, String action) {
        Localidad localidad = extraerLocalidadForm(request, sesion, action);
        LocalidadCRUD localidadCRUD = new LocalidadCRUD();
        try {
            localidadCRUD.actualizar(localidad);
            listarLocalidades(request, response, sesion, action);
        } catch (Exception ex) {
            listarLocalidades(request, response, sesion, "error_actualizar");
            Logger.getLogger(LocalidadController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void mostrarLocalidades(HttpServletRequest request, HttpServletResponse response, List<Localidad> listaLocalidades, String action) {
        request.setAttribute("mensaje", action);
        request.setAttribute("listaLocalidades", listaLocalidades);
        RequestDispatcher view = request.getRequestDispatcher("moduloRegistros/localidad/listarLocalidades.jsp");
        try {
            view.forward(request, response);
        } catch (ServletException | IOException ex) {
            System.err.println("No se pudo mostrar la lista de localidades");
            Logger.getLogger(LocalidadController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void prepararNuevoLocalidad(HttpServletRequest request, HttpServletResponse response, HttpSession sesion) {
        MunicipioCRUD municipioCRUD = new MunicipioCRUD();
        List<Municipio> municipios;
        try {
            municipios = (List<Municipio>) municipioCRUD.listar();
            request.setAttribute("municipios", municipios);
            RequestDispatcher view = request.getRequestDispatcher("moduloRegistros/localidad/nuevoLocalidad.jsp");
            view.forward(request, response);
        } catch (Exception ex) {
            listarLocalidades(request, response, sesion, "error_nuevo");
            Logger.getLogger(LocalidadController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void listarLocalidades(HttpServletRequest request, HttpServletResponse response, HttpSession sesion, String action) {
        List<Localidad> listaLocalidades;
        LocalidadCRUD localidadCRUD = new LocalidadCRUD();
        try {
            listaLocalidades = (List<Localidad>) localidadCRUD.listar((String) sesion.getAttribute("id_jefe"), (String) sesion.getAttribute("rol"));
            mostrarLocalidades(request, response, listaLocalidades, action);
        } catch (Exception ex) {
            System.out.println(ex);
            Logger.getLogger(LocalidadController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void modificarLocalidad(HttpServletRequest request, HttpServletResponse response, HttpSession sesion, String action) {
        Localidad localidadEC = new Localidad();
        localidadEC.setNombre_localidad(request.getParameter("nombre_localidad"));
        LocalidadCRUD localidadCRUD = new LocalidadCRUD();
        try {
            Localidad localidad = (Localidad) localidadCRUD.modificar(localidadEC);
            request.setAttribute("localidad", localidad);
            RequestDispatcher view = request.getRequestDispatcher("moduloRegistros/localidad/actualizarLocalidad.jsp");
            view.forward(request, response);
        } catch (Exception ex) {
            System.out.println(ex);
            listarLocalidades(request, response, sesion, "error_modificar");
            Logger.getLogger(LocalidadController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void eliminarLocalidad(HttpServletRequest request, HttpServletResponse response, HttpSession sesion, String action) {
        Localidad localidadEC = new Localidad();
        localidadEC.setNombre_localidad(request.getParameter("nombre_localidad"));
        LocalidadCRUD localidadCRUD = new LocalidadCRUD();
        try {
            localidadCRUD.eliminar(localidadEC);
            listarLocalidades(request, response, sesion, action);
        } catch (Exception ex) {
            System.out.println(ex);
            listarLocalidades(request, response, sesion, "error_eliminar");
            Logger.getLogger(LocalidadController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //Busca una localidad individual
    private void buscarLocalidad(HttpServletRequest request, HttpServletResponse response, HttpSession sesion, String action) {
        try {
            List<Localidad> localidad;
            LocalidadCRUD localidadCRUD = new LocalidadCRUD();
            String nombre_localidad = request.getParameter("nombre_localidad");
            String nombre_municipio = request.getParameter("nombre_municipio");
            String estado = request.getParameter("estado");
            localidad = (List<Localidad>) localidadCRUD.buscarLocalidad(nombre_localidad, nombre_municipio, estado);
            mostrarLocalidades(request, response, localidad, action);
        } catch (Exception ex) {
            System.out.println(ex);
            listarLocalidades(request, response, sesion, action);
            Logger.getLogger(MunicipioController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
