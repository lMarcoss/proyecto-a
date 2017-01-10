package controlador.maderaRollo;

import dao.maderaRollo.EntradaMaderaRolloCRUD;
import dao.empleado.EmpleadoCRUD;
import dao.registros.ProveedorCRUD;
import entidades.maderaRollo.EntradaMaderaRollo;
import entidades.empleado.Empleado;
import entidades.registros.Proveedor;
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
 * @author rcortes
 */
public class EntradaMaderaRolloController extends HttpServlet {

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
                    registrarEntradaMaderaRollo(request, response, sesion, action);
                    break;
                case "actualizar":
                    actualizarEntradaMaderaRollo(request, response, sesion, action);
                    break;
                case "buscar":
                    buscar(request, response, sesion, action);
                    break;
                /**
                 * *************** Respuestas a métodos GET
                 * *********************
                 */
                case "nuevo":
                    prepararNuevoEntradaMaderaRollo(request, response, sesion, action);
                    break;
                case "listar":
                    listarEntradaMaderaRollo(request, response, sesion, action);
                    break;
                case "modificar":
                    modificarEntradaMaderaRollo(request, sesion, response);
                    break;
                case "eliminar":
                    eliminarEntradaMaderaRollo(request, sesion, response);
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

    private void registrarEntradaMaderaRollo(HttpServletRequest request, HttpServletResponse response, HttpSession sesion, String action) {
        EntradaMaderaRollo entradaMaderaRollo = extraerEntradaMaderaRolloForm(request, sesion, action);
        EntradaMaderaRolloCRUD entradaMaderaRolloCRUD = new EntradaMaderaRolloCRUD();
        try {
            entradaMaderaRolloCRUD.registrar(entradaMaderaRollo);
            //enviar mensaje -> registrado
            response.sendRedirect("/aserradero/EntradaMaderaRolloController?action=listar");
        } catch (Exception ex) {
            System.out.println(ex);
            listarEntradaMaderaRollo(request, response, sesion, "error_registrar");
            Logger.getLogger(EntradaMaderaRolloController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private EntradaMaderaRollo extraerEntradaMaderaRolloForm(HttpServletRequest request, HttpSession sesion, String action) {
        EntradaMaderaRollo entrada = new EntradaMaderaRollo();
        if (action.equals("actualizar")) {
            entrada.setId_entrada(Integer.valueOf(request.getParameter("id_entrada")));
        }
        entrada.setFecha(Date.valueOf(request.getParameter("fecha")));
        entrada.setId_proveedor(request.getParameter("id_proveedor"));
        entrada.setId_empleado((String)sesion.getAttribute("id_empleado"));
        System.out.println((String)sesion.getAttribute("id_empleado"));
        entrada.setId_chofer(request.getParameter("id_chofer"));        
        entrada.setNum_pieza_primario(Integer.valueOf(request.getParameter("num_pieza_primario")));
        entrada.setVolumen_primario(BigDecimal.valueOf((Double.valueOf(request.getParameter("volumen_primario")))));
        entrada.setNum_pieza_secundario(Integer.valueOf(request.getParameter("num_pieza_secundario")));
        entrada.setVolumen_secundario(BigDecimal.valueOf((Double.valueOf(request.getParameter("volumen_secundario")))));
        entrada.setNum_pieza_terciario(Integer.valueOf(request.getParameter("num_pieza_terciario")));
        entrada.setVolumen_terciario(BigDecimal.valueOf((Double.valueOf(request.getParameter("volumen_terciario")))));
        return entrada;
    }

    private void actualizarEntradaMaderaRollo(HttpServletRequest request, HttpServletResponse response, HttpSession sesion, String action) {
        EntradaMaderaRollo entradaMaderaRollo = extraerEntradaMaderaRolloForm(request, sesion, action);
        EntradaMaderaRolloCRUD entradaMaderaRolloCRUD = new EntradaMaderaRolloCRUD();
        try {
            entradaMaderaRolloCRUD.actualizar(entradaMaderaRollo);
            //enviar mensaje -> actualizado
            response.sendRedirect("/aserradero/EntradaMaderaRolloController?action=listar");
        } catch (Exception ex) {
            System.out.println(ex);
            listarEntradaMaderaRollo(request, response, sesion, "error_actualizar");
            Logger.getLogger(EntradaMaderaRolloController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void buscar(HttpServletRequest request, HttpServletResponse response, HttpSession sesion, String action) {
        List<EntradaMaderaRollo> entradas;
        String nombre_campo = request.getParameter("nombre_campo");
        String dato = request.getParameter("dato");
        EntradaMaderaRolloCRUD entradaMaderaRolloCRUD = new EntradaMaderaRolloCRUD();
        try {
            entradas = (List<EntradaMaderaRollo>) entradaMaderaRolloCRUD.buscar(nombre_campo, dato, (String) sesion.getAttribute("id_jefe"));
            mostrarEntradaMaderaRollo(request, response, entradas, action);
        } catch (Exception ex) {
            System.out.println(ex);
            listarEntradaMaderaRollo(request, response, sesion, "error_buscar_campo");
            Logger.getLogger(EntradaMaderaRolloController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void mostrarEntradaMaderaRollo(HttpServletRequest request, HttpServletResponse response, List<EntradaMaderaRollo> listaEntradaMaderaRollo, String action) {
        request.setAttribute("mensaje", action);
        request.setAttribute("listaEntradaMaderaRollo", listaEntradaMaderaRollo);
        RequestDispatcher view = request.getRequestDispatcher("moduloMaderaRollo/entradaMaderaRollo/listarEntradaMaderaRollo.jsp");
        try {
            view.forward(request, response);
        } catch (ServletException | IOException ex) {
            System.out.println(ex);
            System.err.println("No se pudo mostrar la lista de entradaMaderaRollo");
            Logger.getLogger(EntradaMaderaRolloController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void prepararNuevoEntradaMaderaRollo(HttpServletRequest request, HttpServletResponse response, HttpSession sesion, String action) {
        try {
            //enviamos la lista de proveedores
            ProveedorCRUD proveedorCRUD = new ProveedorCRUD();
            List<Proveedor> proveedores = (List<Proveedor>) proveedorCRUD.listar((String) sesion.getAttribute("id_jefe"));
            request.setAttribute("proveedores", proveedores);

            //Enviamos la lista de choferes
            EmpleadoCRUD empleadoCRUD = new EmpleadoCRUD();
            List<Empleado> choferes = (List<Empleado>) empleadoCRUD.listarEmpleadoPorRol("Chofer");
            request.setAttribute("choferes", choferes);

            //Enviamos la fecha actual
            Date fechaActual = Date.valueOf(LocalDate.now());
            request.setAttribute("fechaActual", fechaActual);
            RequestDispatcher view = request.getRequestDispatcher("moduloMaderaRollo/entradaMaderaRollo/nuevoEntradaMaderaRollo.jsp");
            view.forward(request, response);
        } catch (Exception ex) {
            System.out.println(ex);
            listarEntradaMaderaRollo(request, response, sesion, "error_nuevo");
            Logger.getLogger(EntradaMaderaRolloController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void listarEntradaMaderaRollo(HttpServletRequest request, HttpServletResponse response, HttpSession sesion, String action) {
        List<EntradaMaderaRollo> listaEntradaMaderaRollo;
        EntradaMaderaRolloCRUD entradaMaderaRolloCRUD = new EntradaMaderaRolloCRUD();
        try {
            listaEntradaMaderaRollo = (List<EntradaMaderaRollo>) entradaMaderaRolloCRUD.listar((String) sesion.getAttribute("id_jefe"));
            mostrarEntradaMaderaRollo(request, response, listaEntradaMaderaRollo, action);
        } catch (Exception ex) {
            System.out.println(ex);
            Logger.getLogger(EntradaMaderaRolloController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void modificarEntradaMaderaRollo(HttpServletRequest request, HttpSession sesion, HttpServletResponse response) {
        EntradaMaderaRollo entradaMaderaRolloEC = new EntradaMaderaRollo();
        entradaMaderaRolloEC.setId_entrada(Integer.valueOf(request.getParameter("id_entrada")));
        EntradaMaderaRolloCRUD entradaMaderaRolloCRUD = new EntradaMaderaRolloCRUD();
        try {
            //enviamos la entradaMaderaRollo a modificar
            EntradaMaderaRollo entrada = (EntradaMaderaRollo) entradaMaderaRolloCRUD.modificar(entradaMaderaRolloEC);
            request.setAttribute("entrada", entrada);

            //enviamos la lista de proveedores
            ProveedorCRUD proveedorCRUD = new ProveedorCRUD();
            List<Proveedor> proveedores = (List<Proveedor>) proveedorCRUD.listar((String) sesion.getAttribute("id_jefe"));
            request.setAttribute("proveedores", proveedores);

            //Enviamos la lista de choferes
            EmpleadoCRUD empleadoCRUD = new EmpleadoCRUD();
            List<Empleado> choferes = (List<Empleado>) empleadoCRUD.listarEmpleadoPorRol("Chofer");
            request.setAttribute("choferes", choferes);

            RequestDispatcher view = request.getRequestDispatcher("moduloMaderaRollo/entradaMaderaRollo/actualizarEntradaMaderaRollo.jsp");
            view.forward(request, response);
        } catch (Exception ex) {
            System.out.println(ex);
            listarEntradaMaderaRollo(request, response, sesion, "error_modificar");
            Logger.getLogger(EntradaMaderaRolloController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void eliminarEntradaMaderaRollo(HttpServletRequest request, HttpSession sesion, HttpServletResponse response) {
        EntradaMaderaRollo entradaMaderaRolloEC = new EntradaMaderaRollo();
        entradaMaderaRolloEC.setId_entrada(Integer.valueOf(request.getParameter("id_entrada")));
        EntradaMaderaRolloCRUD entradaMaderaRolloCRUD = new EntradaMaderaRolloCRUD();
        try {
            entradaMaderaRolloCRUD.eliminar(entradaMaderaRolloEC);
            //enviar mensaje -> eliminado
            response.sendRedirect("/aserradero/EntradaMaderaRolloController?action=listar");
        } catch (Exception e) {
            System.out.println(e);
            listarEntradaMaderaRollo(request, response, sesion, "error_eliminar");
            Logger.getLogger(EntradaMaderaRolloController.class.getName()).log(Level.SEVERE, null, e);
        }
    }
}
