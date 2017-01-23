package controlador.maderaAserrada;

import dao.maderaAserrada.EntradaMaderaAserradaCRUD;
import dao.maderaAserrada.MaderaAserradaClasifCRUD;
import entidades.maderaAserrada.EntradaMaderaAserrada;
import entidades.maderaAserrada.MaderaAserradaClasif;
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
public class EntradaMaderaAserradaController extends HttpServlet {

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
                    registrarEntradaMaderaAserrada(request, response, sesion, action);
                    break;
                case "actualizar":
                    actualizarEntradaMaderaAserrada(request, response, sesion, action);
                    break;
                case "buscar":
                    buscarEntradaMaderaAserrada(request, response, sesion, action);
                    break;
                /**
                 * *************** Respuestas a métodos GET
                 * *********************
                 */
                case "nuevo":
                    prepararNuevoEntradaMaderaAserrada(request, response, sesion, action);
                    break;
                case "listar":
                    listarEntradaMaderaAserrada(request, response, sesion, action);
                    break;
                case "modificar":
                    modificarEntradaMaderaAserrada(request, response, sesion, action);
                    break;
                case "eliminar":
                    eliminarEntradaMaderaAserrada(request, response, sesion, action);
                    break;
                case "resumen_hoy":
                    listarResumenHoy(request, response, sesion, action);
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

    private void registrarEntradaMaderaAserrada(HttpServletRequest request, HttpServletResponse response, HttpSession sesion, String action) {
        EntradaMaderaAserrada entradaMaderaAserrada = extraerEntradaMaderaAserradaForm(request, sesion);
        EntradaMaderaAserradaCRUD entradaMaderaAserradaCRUD = new EntradaMaderaAserradaCRUD();
        try {
            entradaMaderaAserradaCRUD.registrar(entradaMaderaAserrada);
            //enviar mensaje -> registrado
            response.sendRedirect("/aserradero/EntradaMaderaAserradaController?action=listar");
        } catch (Exception ex) {
            listarEntradaMaderaAserrada(request, response, sesion, "error_registrar");
            Logger.getLogger(EntradaMaderaAserradaController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private EntradaMaderaAserrada extraerEntradaMaderaAserradaForm(HttpServletRequest request, HttpSession sesion) {
        EntradaMaderaAserrada entradaMaderaAserrada = new EntradaMaderaAserrada();
        entradaMaderaAserrada.setId_entrada(Integer.valueOf(request.getParameter("id_entrada")));
        entradaMaderaAserrada.setFecha(Date.valueOf(request.getParameter("fecha")));
        entradaMaderaAserrada.setId_madera(request.getParameter("id_madera"));
        entradaMaderaAserrada.setNum_piezas(Integer.valueOf(request.getParameter("num_piezas")));
        entradaMaderaAserrada.setId_empleado((String) sesion.getAttribute("id_empleado"));
        entradaMaderaAserrada.setId_administrador((String) sesion.getAttribute("id_jefe"));
        return entradaMaderaAserrada;
    }

    private void listarEntradaMaderaAserrada(HttpServletRequest request, HttpServletResponse response, HttpSession sesion, String action) {
        List<EntradaMaderaAserrada> listaMEAserrada;
        EntradaMaderaAserradaCRUD entradaMaderaAserradaCrud = new EntradaMaderaAserradaCRUD();
        try {
            listaMEAserrada = (List<EntradaMaderaAserrada>) entradaMaderaAserradaCrud.listar((String) sesion.getAttribute("id_jefe"), (String) sesion.getAttribute("rol"));
            mostrarEntradaMaderaAserrada(request, response, listaMEAserrada, action);
        } catch (Exception ex) {
            System.out.println(ex);
            Logger.getLogger(EntradaMaderaAserradaController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void mostrarEntradaMaderaAserrada(HttpServletRequest request, HttpServletResponse response, List<EntradaMaderaAserrada> listaEntradaMaderaAserrada, String action) {
        request.setAttribute("mensaje", action);
        request.setAttribute("listaEntradaMaderaAserrada", listaEntradaMaderaAserrada);
        RequestDispatcher view = request.getRequestDispatcher("moduloMaderaAserrada/entradaMaderaAserrada/listarEntradaMaderaAserrada.jsp");
        try {
            view.forward(request, response);
        } catch (ServletException | IOException ex) {
            System.err.println("No se pudo mostrar la lista de entrada madera aserrada");
            Logger.getLogger(MaderaAserradaClasifController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void actualizarEntradaMaderaAserrada(HttpServletRequest request, HttpServletResponse response, HttpSession sesion, String action) {
        EntradaMaderaAserrada entradaMaderaAserrada = extraerEntradaMaderaAserradaForm(request, sesion);
        EntradaMaderaAserradaCRUD entradaMaderaAserradaCRUD = new EntradaMaderaAserradaCRUD();
        try {
            entradaMaderaAserradaCRUD.actualizar(entradaMaderaAserrada);
            //enviar mensaje -> actualizado
            response.sendRedirect("/aserradero/EntradaMaderaAserradaController?action=listar");
        } catch (Exception ex) {
            listarEntradaMaderaAserrada(request, response, sesion, "error_actualizar");
            Logger.getLogger(EntradaMaderaAserradaController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void buscarEntradaMaderaAserrada(HttpServletRequest request, HttpServletResponse response, HttpSession sesion, String action) {
        List<EntradaMaderaAserrada> listaMEAserrada;
        String nombre_campo = request.getParameter("nombre_campo");
        String dato = request.getParameter("dato");
        EntradaMaderaAserradaCRUD entradaMaderaAserradaCRUD = new EntradaMaderaAserradaCRUD();
        try {
            listaMEAserrada = (List<EntradaMaderaAserrada>) entradaMaderaAserradaCRUD.buscar(nombre_campo, dato, (String) sesion.getAttribute("id_jefe"), (String) sesion.getAttribute("rol"));
            mostrarEntradaMaderaAserrada(request, response, listaMEAserrada, action);
        } catch (Exception ex) {
            listarEntradaMaderaAserrada(request, response, sesion, "error_buscar_campo");
            Logger.getLogger(EntradaMaderaAserradaController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void prepararNuevoEntradaMaderaAserrada(HttpServletRequest request, HttpServletResponse response, HttpSession sesion, String action) {
        try {
            //enviamos lista de maderaClasificación al jsp
            MaderaAserradaClasifCRUD maderaClasificacionCRUD = new MaderaAserradaClasifCRUD();
            List<MaderaAserradaClasif> clasificaciones = (List<MaderaAserradaClasif>) maderaClasificacionCRUD.listar((String) sesion.getAttribute("id_jefe"), (String) sesion.getAttribute("rol"));
            request.setAttribute("clasificaciones", clasificaciones);

            RequestDispatcher view = request.getRequestDispatcher("moduloMaderaAserrada/entradaMaderaAserrada/nuevoEntradaMaderaAserrada.jsp");
            view.forward(request, response);
        } catch (Exception ex) {
            listarEntradaMaderaAserrada(request, response, sesion, action);
            System.out.println(ex);
            Logger.getLogger(EntradaMaderaAserradaController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void modificarEntradaMaderaAserrada(HttpServletRequest request, HttpServletResponse response, HttpSession sesion, String action) {
        EntradaMaderaAserrada entradaMaderaAserradaEC = new EntradaMaderaAserrada();
        entradaMaderaAserradaEC.setId_entrada(Integer.valueOf(request.getParameter("id_entrada")));
        EntradaMaderaAserradaCRUD entradaMaderaAserradaCRUD = new EntradaMaderaAserradaCRUD();
        try {
            //enviamos la entradaMaderaAserrada a modificar
            EntradaMaderaAserrada entrada = (EntradaMaderaAserrada) entradaMaderaAserradaCRUD.modificar(entradaMaderaAserradaEC);
            request.setAttribute("entrada", entrada);
            RequestDispatcher view = request.getRequestDispatcher("moduloMaderaAserrada/entradaMaderaAserrada/actualizarEntradaMaderaAserrada.jsp");
            view.forward(request, response);
        } catch (Exception ex) {
            System.err.println(ex);
            listarEntradaMaderaAserrada(request, response, sesion, "error_modificar");
            Logger.getLogger(EntradaMaderaAserradaController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void eliminarEntradaMaderaAserrada(HttpServletRequest request, HttpServletResponse response, HttpSession sesion, String action) {
        EntradaMaderaAserrada entradaMaderaAserradaEC = new EntradaMaderaAserrada();
        entradaMaderaAserradaEC.setId_entrada(Integer.valueOf(request.getParameter("id_entrada")));
        EntradaMaderaAserradaCRUD entradaMaderaAserradaCRUD = new EntradaMaderaAserradaCRUD();
        try {
            entradaMaderaAserradaCRUD.eliminar(entradaMaderaAserradaEC);
            //enviar mensaje -> eliminado
            response.sendRedirect("/aserradero/EntradaMaderaAserradaController?action=listar");
        } catch (Exception ex) {
            listarEntradaMaderaAserrada(request, response, sesion, "error_eliminar");
            Logger.getLogger(EntradaMaderaAserradaController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void listarResumenHoy(HttpServletRequest request, HttpServletResponse response, HttpSession sesion, String action) {
        try {
            EntradaMaderaAserradaCRUD entradaCRUD = new EntradaMaderaAserradaCRUD();
            List<EntradaMaderaAserrada> listaMEAserrada = (List<EntradaMaderaAserrada>) entradaCRUD.listarEntradaHoy((String) sesion.getAttribute("id_jefe"));
            EntradaMaderaAserrada entradaTotalHoy = entradaCRUD.entradaTotalHoy((String) sesion.getAttribute("id_jefe"));
            request.setAttribute("listaEntradaMaderaAserrada", listaMEAserrada);
            request.setAttribute("entradaTotal", entradaTotalHoy);
            RequestDispatcher view = request.getRequestDispatcher("moduloMaderaAserrada/entradaMaderaAserrada/resumenHoy.jsp");
            view.forward(request, response);
        } catch (Exception ex) {
            Logger.getLogger(EntradaMaderaAserradaController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
