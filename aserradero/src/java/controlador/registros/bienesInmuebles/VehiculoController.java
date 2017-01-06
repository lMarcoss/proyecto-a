package controlador.registros.bienesInmuebles;

import dao.registros.bienesInmuebles.VehiculoCRUD;
import entidades.registros.bienesInmuebles.Vehiculo;
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
 * @author rcortes
 */
public class VehiculoController extends HttpServlet {

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
                    registrarVehiculo(request, response, sesion, action);
                    break;
                case "actualizar":
                    actualizarVehiculo(request, response, sesion, action);
                    break;
                case "buscar":
                    buscar(request, response, sesion, action);
                    break;
                /**
                 * *************** Respuestas a métodos GET
                 * *********************
                 */
                case "nuevo":
                    prepararNuevoVehiculo(request, response, sesion);
                    break;
                case "listar":
                    listarVehiculos(request, response, sesion, action);
                    break;
                case "modificar":
                    modificarVehiculo(request, response, sesion, action);
                    break;
                case "eliminar":
                    eliminarVehiculo(request, response, sesion, action);
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

    private void registrarVehiculo(HttpServletRequest request, HttpServletResponse response, HttpSession sesion, String action) {
        Vehiculo vehiculo = extraerVehiculoForm(request, sesion, action);
        VehiculoCRUD vehiculocrud = new VehiculoCRUD();
        try {
            vehiculocrud.registrar(vehiculo);
            response.sendRedirect("/aserradero/VehiculoController?action=listar");
        } catch (Exception ex) {
            listarVehiculos(request, response, sesion, "error_registrar");
            Logger.getLogger(VehiculoController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private Vehiculo extraerVehiculoForm(HttpServletRequest request, HttpSession sesion, String action) {
        Vehiculo vehiculo = new Vehiculo();
        if(action.equals("actualizar")){
            vehiculo.setId_vehiculo(Integer.valueOf(request.getParameter("id_vehiculo")));
        }
        vehiculo.setMatricula(request.getParameter("matricula"));
        vehiculo.setTipo(request.getParameter("tipo"));
        vehiculo.setColor(request.getParameter("color"));
        vehiculo.setCarga_admitida(request.getParameter("carga_admitida"));
        vehiculo.setMotor(request.getParameter("motor"));
        vehiculo.setModelo(request.getParameter("modelo"));
        vehiculo.setCosto(BigDecimal.valueOf((Double.valueOf(request.getParameter("costo")))));
        vehiculo.setId_empleado((String) sesion.getAttribute("id_empleado"));
        return vehiculo;
    }

    private void actualizarVehiculo(HttpServletRequest request, HttpServletResponse response, HttpSession sesion, String action) {
        Vehiculo vehiculo = extraerVehiculoForm(request, sesion, action);
        VehiculoCRUD vehiculocrud = new VehiculoCRUD();
        try {
            vehiculocrud.actualizar(vehiculo);
            response.sendRedirect("/aserradero/VehiculoController?action=listar");
        } catch (Exception ex) {
            listarVehiculos(request, response, sesion, "error_actualizar");
            Logger.getLogger(VehiculoController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void buscar(HttpServletRequest request, HttpServletResponse response, HttpSession sesion, String action) {
        List<Vehiculo> vehiculos;
        String nombre_campo = request.getParameter("nombre_campo");
        String dato = request.getParameter("dato");
        VehiculoCRUD vehiculocrud = new VehiculoCRUD();
        try {
            vehiculos = (List<Vehiculo>) vehiculocrud.buscar(nombre_campo, dato, (String) sesion.getAttribute("id_jefe"));
            mostrarVehiculos(request, response, vehiculos, action);
        } catch (Exception ex) {
            listarVehiculos(request, response, sesion, "error_buscar");
            Logger.getLogger(VehiculoController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void mostrarVehiculos(HttpServletRequest request, HttpServletResponse response, List<Vehiculo> listaVehiculos, String action) {
        request.setAttribute("mensaje", action);
        request.setAttribute("listaVehiculos", listaVehiculos);
        RequestDispatcher view = request.getRequestDispatcher("moduloRegistros/bienesInmuebles/vehiculo/listarVehiculos.jsp");
        try {
            view.forward(request, response);
        } catch (ServletException | IOException ex) {
            System.err.println("No se pudo mostrar la lista de vehiculos");
            Logger.getLogger(VehiculoController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void prepararNuevoVehiculo(HttpServletRequest request, HttpServletResponse response, HttpSession sesion) {
        try {
            response.sendRedirect("moduloRegistros/bienesInmuebles/vehiculo/nuevoVehiculo.jsp");
        } catch (IOException ex) {
            System.out.println(ex);
            listarVehiculos(request, response, sesion, "error_nuevo");
            Logger.getLogger(VehiculoController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void listarVehiculos(HttpServletRequest request, HttpServletResponse response, HttpSession sesion, String action) {
        List<Vehiculo> listaVehiculos;
        VehiculoCRUD vehiculoCRUD = new VehiculoCRUD();
        try {
            listaVehiculos = (List<Vehiculo>) vehiculoCRUD.listar((String) sesion.getAttribute("id_jefe"));
            mostrarVehiculos(request, response, listaVehiculos, action);
        } catch (Exception ex) {
            System.out.println(ex);
            Logger.getLogger(VehiculoController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void modificarVehiculo(HttpServletRequest request, HttpServletResponse response, HttpSession sesion, String action) {
        Vehiculo vehiculoEC = new Vehiculo();
        vehiculoEC.setId_vehiculo(Integer.valueOf(request.getParameter("id_vehiculo")));
        VehiculoCRUD vehiculocrud = new VehiculoCRUD();
        try {
            Vehiculo vehiculo = (Vehiculo) vehiculocrud.modificar(vehiculoEC);
            request.setAttribute("vehiculo", vehiculo);
            RequestDispatcher view = request.getRequestDispatcher("moduloRegistros/bienesInmuebles/vehiculo/actualizarVehiculo.jsp");
            view.forward(request, response);
        } catch (Exception ex) {
            listarVehiculos(request, response, sesion, action);
            Logger.getLogger(VehiculoController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void eliminarVehiculo(HttpServletRequest request, HttpServletResponse response, HttpSession sesion, String action) {
        Vehiculo vehiculoEC = new Vehiculo();
        vehiculoEC.setId_vehiculo(Integer.valueOf(request.getParameter("id_vehiculo")));
        VehiculoCRUD vehiculocrud = new VehiculoCRUD();
        try {
            vehiculocrud.eliminar(vehiculoEC);
            response.sendRedirect("/aserradero/VehiculoController?action=listar");
        } catch (Exception ex) {
            listarVehiculos(request, response, sesion, "error_eliminar");
            Logger.getLogger(VehiculoController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
