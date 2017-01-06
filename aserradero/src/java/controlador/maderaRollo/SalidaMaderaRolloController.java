package controlador.maderaRollo;

import dao.maderaRollo.InventarioMaderaRolloCRUD;
import dao.maderaRollo.SalidaMaderaRolloCRUD;
import entidades.maderaRollo.InventarioMaderaRollo;
import entidades.maderaRollo.SalidaMaderaRollo;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;
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
public class SalidaMaderaRolloController extends HttpServlet {

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
                    registrarSalidaMaderaRollo(request, response, sesion, action);
                    break;
                case "actualizar":
                    actualizarSalidaMaderaRollo(request, response, sesion, action);
                    break;
                case "buscar":
                    buscar(request, response, sesion, action);
                    break;
                /**
                 * *************** Respuestas a métodos GET
                 * *********************
                 */
                case "nuevo":
                    prepararNuevoSalidaMaderaRollo(request, response, sesion, action);
                    break;
                case "listar":
                    listarSalidaMaderaRollo(request, response, sesion, action);
                    break;
                case "modificar":
                    modificarSalidaMaderaRollo(request, sesion, response);
                    break;
                case "eliminar":
                    eliminarSalidaMaderaRollo(request, sesion, response);
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

    private void registrarSalidaMaderaRollo(HttpServletRequest request, HttpServletResponse response, HttpSession sesion, String action) {
        SalidaMaderaRollo salidaMaderaRollo = extraerSalidaMaderaRolloForm(request, sesion, action);
        SalidaMaderaRolloCRUD salidaMaderaRolloCRUD = new SalidaMaderaRolloCRUD();
        try {
            salidaMaderaRolloCRUD.registrar(salidaMaderaRollo);
            //enviar mensaje -> registrado
            response.sendRedirect("/aserradero/SalidaMaderaRolloController?action=listar");
        } catch (Exception ex) {
            System.out.println(ex);
            listarSalidaMaderaRollo(request, response, sesion, "error_registrar");
            Logger.getLogger(SalidaMaderaRolloController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private SalidaMaderaRollo extraerSalidaMaderaRolloForm(HttpServletRequest request, HttpSession sesion, String action) {
        SalidaMaderaRollo salida = new SalidaMaderaRollo();
        if (action.equals("actualizar")) {
            salida.setId_salida(Integer.valueOf(request.getParameter("id_salida")));
        }
        salida.setFecha(Date.valueOf(request.getParameter("fecha")));
        salida.setId_empleado((String) sesion.getAttribute("id_empleado"));
        salida.setNum_pieza_primario(Integer.valueOf(request.getParameter("num_pieza_primario")));
        salida.setVolumen_primario(BigDecimal.valueOf((Double.valueOf(request.getParameter("volumen_primario")))));
        salida.setNum_pieza_secundario(Integer.valueOf(request.getParameter("num_pieza_secundario")));
        salida.setVolumen_secundario(BigDecimal.valueOf((Double.valueOf(request.getParameter("volumen_secundario")))));
        salida.setNum_pieza_terciario(Integer.valueOf(request.getParameter("num_pieza_terciario")));
        salida.setVolumen_terciario(BigDecimal.valueOf((Double.valueOf(request.getParameter("volumen_terciario")))));
        return salida;
    }

    private void actualizarSalidaMaderaRollo(HttpServletRequest request, HttpServletResponse response, HttpSession sesion, String action) {
        SalidaMaderaRollo salidaMaderaRollo = extraerSalidaMaderaRolloForm(request, sesion, action);
        SalidaMaderaRolloCRUD salidaMaderaRolloCRUD = new SalidaMaderaRolloCRUD();
        try {
            salidaMaderaRolloCRUD.actualizar(salidaMaderaRollo);
            //enviar mensaje -> actualizado
            response.sendRedirect("/aserradero/SalidaMaderaRolloController?action=listar");
        } catch (Exception ex) {
            System.out.println(ex);
            listarSalidaMaderaRollo(request, response, sesion, "error_actualizar");
            Logger.getLogger(SalidaMaderaRolloController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void buscar(HttpServletRequest request, HttpServletResponse response, HttpSession sesion, String action) {
        List<SalidaMaderaRollo> salidas;
        String nombre_campo = request.getParameter("nombre_campo");
        String dato = request.getParameter("dato");
        SalidaMaderaRolloCRUD salidaMaderaRolloCRUD = new SalidaMaderaRolloCRUD();
        try {
            salidas = (List<SalidaMaderaRollo>) salidaMaderaRolloCRUD.buscar(nombre_campo, dato, (String) sesion.getAttribute("id_jefe"));
            mostrarSalidaMaderaRollo(request, response, salidas, action);
        } catch (Exception ex) {
            System.out.println(ex);
            listarSalidaMaderaRollo(request, response, sesion, "error_buscar_campo");
            Logger.getLogger(SalidaMaderaRolloController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void mostrarSalidaMaderaRollo(HttpServletRequest request, HttpServletResponse response, List<SalidaMaderaRollo> listaSalidaMaderaRollo, String action) {
        request.setAttribute("mensaje", action);
        request.setAttribute("listaSalidaMaderaRollo", listaSalidaMaderaRollo);
        RequestDispatcher view = request.getRequestDispatcher("moduloMaderaRollo/salidaMaderaRollo/listarSalidaMaderaRollo.jsp");
        try {
            view.forward(request, response);
        } catch (ServletException | IOException ex) {
            System.out.println(ex);
            System.err.println("No se pudo mostrar la lista de salidaMaderaRollo");
            Logger.getLogger(SalidaMaderaRolloController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void prepararNuevoSalidaMaderaRollo(HttpServletRequest request, HttpServletResponse response, HttpSession sesion, String action) {
        try {
            //Enviamos el inventario de madera rollo
            InventarioMaderaRolloCRUD inventarioMRCRUD = new InventarioMaderaRolloCRUD();
            List<InventarioMaderaRollo> inventario = (List<InventarioMaderaRollo>) inventarioMRCRUD.listar((String) sesion.getAttribute("id_jefe"));
            request.setAttribute("inventario", inventario);

            //Enviamos la fecha actual 
            Date fechaActual = Date.valueOf(LocalDate.now());
            request.setAttribute("fechaActual", fechaActual);
            RequestDispatcher view = request.getRequestDispatcher("moduloMaderaRollo/salidaMaderaRollo/nuevoSalidaMadera.jsp");
            view.forward(request, response);
        } catch (Exception ex) {
            System.out.println(ex);
            listarSalidaMaderaRollo(request, response, sesion, "error_nuevo");
            Logger.getLogger(SalidaMaderaRolloController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void listarSalidaMaderaRollo(HttpServletRequest request, HttpServletResponse response, HttpSession sesion, String action) {
        List<SalidaMaderaRollo> listaSalidaMaderaRollo;
        SalidaMaderaRolloCRUD salidaMaderaRolloCRUD = new SalidaMaderaRolloCRUD();
        try {
            listaSalidaMaderaRollo = (List<SalidaMaderaRollo>) salidaMaderaRolloCRUD.listar((String) sesion.getAttribute("id_jefe"));
            mostrarSalidaMaderaRollo(request, response, listaSalidaMaderaRollo, action);
        } catch (Exception ex) {
            System.out.println(ex);
            Logger.getLogger(SalidaMaderaRolloController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void modificarSalidaMaderaRollo(HttpServletRequest request, HttpSession sesion, HttpServletResponse response) {
        SalidaMaderaRollo salidaMaderaRolloEC = new SalidaMaderaRollo();
        salidaMaderaRolloEC.setId_salida(Integer.valueOf(request.getParameter("id_salida")));

        SalidaMaderaRolloCRUD salidaMaderaRolloCRUD = new SalidaMaderaRolloCRUD();
        try {
            //enviamos la salidaMaderaRollo a modificar
            SalidaMaderaRollo salidaMaderaRollo = (SalidaMaderaRollo) salidaMaderaRolloCRUD.modificar(salidaMaderaRolloEC);
            request.setAttribute("salidaMaderaRollo", salidaMaderaRollo);

            //enviamos el inventario de madera rollo
            InventarioMaderaRolloCRUD inventarioMRCRUD = new InventarioMaderaRolloCRUD();
            List<InventarioMaderaRollo> inventarioMR = (List<InventarioMaderaRollo>) inventarioMRCRUD.listar((String) sesion.getAttribute("id_jefe"));
            if(inventarioMR.isEmpty()){
                InventarioMaderaRollo inventario = new InventarioMaderaRollo(
                        "", 0, BigDecimal.valueOf(0), BigDecimal.valueOf(0), BigDecimal.valueOf(0), 0, BigDecimal.valueOf(0), BigDecimal.valueOf(0), BigDecimal.valueOf(0), 0, BigDecimal.valueOf(0), BigDecimal.valueOf(0), BigDecimal.valueOf(0), 0, BigDecimal.valueOf(0), BigDecimal.valueOf(0));
                inventarioMR.add(inventario);
            }
            request.setAttribute("inventarioMR", inventarioMR);

            RequestDispatcher view = request.getRequestDispatcher("moduloMaderaRollo/salidaMaderaRollo/actualizarSalidaMaderaRollo.jsp");
            view.forward(request, response);
        } catch (Exception ex) {
            System.out.println(ex);
            listarSalidaMaderaRollo(request, response, sesion, "error_modificar");
            Logger.getLogger(SalidaMaderaRolloController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void eliminarSalidaMaderaRollo(HttpServletRequest request, HttpSession sesion, HttpServletResponse response) {
        SalidaMaderaRollo salidaMaderaRolloEC = new SalidaMaderaRollo();
        salidaMaderaRolloEC.setId_salida(Integer.valueOf(request.getParameter("id_salida")));

        SalidaMaderaRolloCRUD salidaMaderaRolloCRUD = new SalidaMaderaRolloCRUD();
        try {
            salidaMaderaRolloCRUD.eliminar(salidaMaderaRolloEC);
            //enviar mensaje -> eliminado
            response.sendRedirect("/aserradero/SalidaMaderaRolloController?action=listar");
        } catch (Exception e) {
            System.out.println(e);
            listarSalidaMaderaRollo(request, response, sesion, "error_eliminar");
            Logger.getLogger(SalidaMaderaRolloController.class.getName()).log(Level.SEVERE, null, e);
        }
    }
}
