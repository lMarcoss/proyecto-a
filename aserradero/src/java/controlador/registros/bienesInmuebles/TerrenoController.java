package controlador.registros.bienesInmuebles;

import dao.registros.LocalidadCRUD;
import dao.registros.bienesInmuebles.TerrenoCRUD;
import entidades.registros.Localidad;
import entidades.registros.bienesInmuebles.Terreno;
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
public class TerrenoController extends HttpServlet {

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
                    registrarTerreno(request, response, sesion, action);
                    break;
                case "actualizar":
                    actualizarTerreno(request, response, sesion, action);
                    break;
                case "buscar":
                    buscar(request, response, sesion, action);
                    break;
                /**
                 * *************** Respuestas a métodos GET
                 * *********************
                 */
                case "nuevo":
                    prepararNuevoTerreno(request, response, sesion);
                    break;
                case "listar":
                    listarTerrenos(request, response, sesion, action);
                    break;
                case "modificar":
                    modificarTerreno(request, response, sesion, action);
                    break;
                case "eliminar":
                    eliminarTerreno(request, response, sesion, action);
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

    private void registrarTerreno(HttpServletRequest request, HttpServletResponse response, HttpSession sesion, String action) {
        Terreno terreno = extraerTerrenoForm(request, sesion, action);
        TerrenoCRUD terrenocrud = new TerrenoCRUD();
        try {
            terrenocrud.registrar(terreno);
            response.sendRedirect("/aserradero/TerrenoController?action=listar");
        } catch (Exception ex) {
            listarTerrenos(request, response, sesion, "error_registrar");
            Logger.getLogger(TerrenoController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private Terreno extraerTerrenoForm(HttpServletRequest request, HttpSession sesion, String action) {
        Terreno terreno = new Terreno();
        if (action.equals("actualizar")) {
            terreno.setId_terreno(Integer.valueOf(request.getParameter("id_terreno")));
        }
        terreno.setNombre(request.getParameter("nombre"));
        terreno.setDimension(request.getParameter("dimension"));
        terreno.setDireccion(request.getParameter("direccion"));
        terreno.setNombre_localidad(request.getParameter("nombre_localidad"));
        terreno.setNombre_municipio(request.getParameter("nombre_municipio"));
        terreno.setEstado(request.getParameter("estado"));
        terreno.setValor_estimado(BigDecimal.valueOf((Double.valueOf(request.getParameter("valor_estimado")))));
        terreno.setId_empleado((String) sesion.getAttribute("id_empleado"));
        return terreno;
    }

    private void actualizarTerreno(HttpServletRequest request, HttpServletResponse response, HttpSession sesion, String action) {
        Terreno terreno = extraerTerrenoForm(request, sesion, action);
        TerrenoCRUD terrenocrud = new TerrenoCRUD();
        try {
            terrenocrud.actualizar(terreno);
            response.sendRedirect("/aserradero/TerrenoController?action=listar");
        } catch (Exception ex) {
            listarTerrenos(request, response, sesion, "error_actualizar");
            Logger.getLogger(TerrenoController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void buscar(HttpServletRequest request, HttpServletResponse response, HttpSession sesion, String action) {
        List<Terreno> terrenos;
        String nombre_campo = request.getParameter("nombre_campo");
        String dato = request.getParameter("dato");
        TerrenoCRUD terrenocrud = new TerrenoCRUD();
        try {
            terrenos = (List<Terreno>) terrenocrud.buscar(nombre_campo, dato, (String) sesion.getAttribute("id_jefe"));
            mostrarTerrenos(request, response, terrenos, action);
        } catch (Exception ex) {
            listarTerrenos(request, response, sesion, "error_buscar");
            Logger.getLogger(TerrenoController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void mostrarTerrenos(HttpServletRequest request, HttpServletResponse response, List<Terreno> listaTerrenos, String action) {
        request.setAttribute("mensaje", action);
        request.setAttribute("listaTerrenos", listaTerrenos);
        RequestDispatcher view = request.getRequestDispatcher("moduloRegistros/bienesInmuebles/terreno/listarTerrenos.jsp");
        try {
            view.forward(request, response);
        } catch (ServletException | IOException ex) {
            System.err.println("No se pudo mostrar la lista de terrenos");
            Logger.getLogger(TerrenoController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void prepararNuevoTerreno(HttpServletRequest request, HttpServletResponse response, HttpSession sesion) {
        try {
            LocalidadCRUD localidadCRUD = new LocalidadCRUD();

            List<Localidad> localidades;
            localidades = (List<Localidad>) localidadCRUD.listar((String) sesion.getAttribute("id_jefe"));
            request.setAttribute("localidades", localidades);
            RequestDispatcher view = request.getRequestDispatcher("moduloRegistros/bienesInmuebles/terreno/nuevoTerreno.jsp");
            view.forward(request, response);
        } catch (Exception ex) {
            listarTerrenos(request, response, sesion, "error_nuevo");
            System.out.println(ex);
            Logger.getLogger(TerrenoController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void listarTerrenos(HttpServletRequest request, HttpServletResponse response, HttpSession sesion, String action) {
        List<Terreno> listaTerrenos;
        TerrenoCRUD terrenoCRUD = new TerrenoCRUD();
        try {
            listaTerrenos = (List<Terreno>) terrenoCRUD.listar((String) sesion.getAttribute("id_jefe"));
            mostrarTerrenos(request, response, listaTerrenos, action);
        } catch (Exception ex) {
            System.out.println(ex);
            Logger.getLogger(TerrenoController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void modificarTerreno(HttpServletRequest request, HttpServletResponse response, HttpSession sesion, String action) {
        Terreno terrenoEC = new Terreno();
        terrenoEC.setId_terreno(Integer.valueOf(request.getParameter("id_terreno")));
        TerrenoCRUD terrenocrud = new TerrenoCRUD();
        try {
            Terreno terreno = (Terreno) terrenocrud.modificar(terrenoEC);
            request.setAttribute("terreno", terreno);
            
            LocalidadCRUD localidadCRUD = new LocalidadCRUD();
            List<Localidad> localidades = localidadCRUD.listar((String) sesion.getAttribute("id_jefe"));
            request.setAttribute("localidades", localidades);
            
            RequestDispatcher view = request.getRequestDispatcher("moduloRegistros/bienesInmuebles/terreno/actualizarTerreno.jsp");
            view.forward(request, response);
        } catch (Exception ex) {
            listarTerrenos(request, response, sesion, action);
            Logger.getLogger(TerrenoController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void eliminarTerreno(HttpServletRequest request, HttpServletResponse response, HttpSession sesion, String action) {
        Terreno terrenoEC = new Terreno();
        terrenoEC.setId_terreno(Integer.valueOf(request.getParameter("id_terreno")));
        TerrenoCRUD terrenocrud = new TerrenoCRUD();
        try {
            terrenocrud.eliminar(terrenoEC);
            response.sendRedirect("/aserradero/TerrenoController?action=listar");
        } catch (Exception ex) {
            listarTerrenos(request, response, sesion, "error_eliminar");
            Logger.getLogger(TerrenoController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
