package controlador.registros;

import dao.registros.MunicipioCRUD;
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
public class MunicipioController extends HttpServlet {

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
                    registrarMunicipio(request, response, sesion, action);
                    break;
                case "actualizar":
                    actualizarMunicipio(request, response, sesion, action);
                    break;
                /**
                 * *************** Respuestas a métodos GET
                 * *********************
                 */
                case "nuevo":
                    prepararNuevoMunicipio(request, response, sesion);
                    break;
                case "listar":
                    listarMunicipios(request, response, sesion, action);
                    break;
                case "modificar":
                    modificarMunicipio(request, response, sesion, action);
                    break;
                case "eliminar":
                    eliminarMunicipio(request, response, sesion, action);
                    break;
                case "buscar_municipio":
                    buscarMunicipio(request, response, sesion, action);
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

    private void registrarMunicipio(HttpServletRequest request, HttpServletResponse response, HttpSession sesion, String action) {
        Municipio municipio = extraerMunicipioForm(request, sesion, action);
        MunicipioCRUD municipioCRUD = new MunicipioCRUD();
        try {
            municipioCRUD.registrar(municipio);
            listarMunicipios(request, response, sesion, action);
        } catch (Exception ex) {
            listarMunicipios(request, response, sesion, "error_registrar");
            Logger.getLogger(MunicipioController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //Extraer datos de municipio en el formulario HTML
    private Municipio extraerMunicipioForm(HttpServletRequest request, HttpSession sesion, String action) {
        Municipio municipio = new Municipio();
        municipio.setNombre_municipio(request.getParameter("nombre_municipio"));
        municipio.setEstado(request.getParameter("estado"));
        municipio.setTelefono(request.getParameter("telefono"));
        return municipio;
    }

    private void actualizarMunicipio(HttpServletRequest request, HttpServletResponse response, HttpSession sesion, String action) {
        Municipio municipio = extraerMunicipioForm(request, sesion, action);
        MunicipioCRUD municipioCRUD = new MunicipioCRUD();
        try {
            municipioCRUD.actualizar(municipio);
            listarMunicipios(request, response, sesion, action);
        } catch (Exception ex) {
            listarMunicipios(request, response, sesion, action);
            Logger.getLogger(MunicipioController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void mostrarMunicipios(HttpServletRequest request, HttpServletResponse response, List<Municipio> listaMunicipios, String action) {
        request.setAttribute("mensaje", action);
        request.setAttribute("listaMunicipios", listaMunicipios);
        RequestDispatcher view = request.getRequestDispatcher("moduloRegistros/municipio/listarMunicipios.jsp");
        try {
            view.forward(request, response);
        } catch (ServletException | IOException ex) {
            System.err.println("No se pudo mostrar la lista de municipios");
            Logger.getLogger(MunicipioController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void prepararNuevoMunicipio(HttpServletRequest request, HttpServletResponse response, HttpSession sesion) {
        try {
            response.sendRedirect("/aserradero/moduloRegistros/municipio/nuevoMunicipio.jsp");
        } catch (IOException ex) {
            Logger.getLogger(MunicipioController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void listarMunicipios(HttpServletRequest request, HttpServletResponse response, HttpSession sesion, String action) {
        List<Municipio> listaMunicipios;
        MunicipioCRUD municipioCRUD = new MunicipioCRUD();
        try {
            listaMunicipios = (List<Municipio>) municipioCRUD.listar();
            mostrarMunicipios(request, response, listaMunicipios, action);
        } catch (Exception ex) {
            System.out.println(ex);
            Logger.getLogger(MunicipioController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void modificarMunicipio(HttpServletRequest request, HttpServletResponse response, HttpSession sesion, String action) {
        Municipio municipioEC = new Municipio();
        municipioEC.setNombre_municipio(request.getParameter("nombre_municipio"));
        MunicipioCRUD municipioCRUD = new MunicipioCRUD();
        try {
            Municipio municipio = municipioCRUD.modificar(municipioEC);
            request.setAttribute("municipio", municipio);
            RequestDispatcher view = request.getRequestDispatcher("moduloRegistros/municipio/actualizarMunicipio.jsp");
            view.forward(request, response);
        } catch (Exception ex) {
            listarMunicipios(request, response, sesion, "error_modificar");
            Logger.getLogger(MunicipioController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void eliminarMunicipio(HttpServletRequest request, HttpServletResponse response, HttpSession sesion, String action) {
        Municipio municipioEC = new Municipio();
        municipioEC.setNombre_municipio(request.getParameter("nombre_municipio"));
        MunicipioCRUD municipioCRUD = new MunicipioCRUD();
        try {
            municipioCRUD.eliminar(municipioEC);
            listarMunicipios(request, response, sesion, action);
        } catch (Exception ex) {
            listarMunicipios(request, response, sesion, "error_eliminar");
            Logger.getLogger(MunicipioController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //Busca un municipio individual
    private void buscarMunicipio(HttpServletRequest request, HttpServletResponse response, HttpSession sesion, String action) {
        try {
            List<Municipio> municipios;
            MunicipioCRUD municipioCRUDCrud = new MunicipioCRUD();
            String nombre_municipio = request.getParameter("nombre_municipio");
            String estado = request.getParameter("estado");
            municipios = (List<Municipio>) municipioCRUDCrud.buscarMunicipio(nombre_municipio, estado);
            mostrarMunicipios(request, response, municipios, action);
        } catch (Exception ex) {
            System.out.println(ex);
            listarMunicipios(request, response, sesion, action);
            Logger.getLogger(MunicipioController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
