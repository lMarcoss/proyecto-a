package controlador.venta;

import dao.registros.ClienteCRUD;
import dao.venta.VentaCRUD;
import dao.venta.VentaExtraCRUD;
import entidades.registros.Cliente;
import entidades.venta.Venta;
import entidades.venta.VentaExtra;
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
public class VentaExtraController extends HttpServlet {

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
                    registrarVentaExtra(request, response, sesion, action);
                    break;
                case "actualizar":
                    actualizarVentaExtra(request, response, sesion, action);
                    break;
                /**
                 * *************** Respuestas a métodos GET
                 * *********************
                 */
                case "nuevo":
                    prepararNuevoVentaExtra(request, response, sesion, action);
                    break;
                case "listar":
                    listarVentaExtras(request, response, sesion, action);
                    break;
                case "modificar":
                    modificarVentaExtra(request, sesion, response);
                    break;
                case "eliminar":
                    eliminarVentaExtra(request, sesion, response);
                    break;
                case "detalle":
                    listarDetalleVentaExtra(request, response, sesion, action);
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

    private void registrarVentaExtra(HttpServletRequest request, HttpServletResponse response, HttpSession sesion, String action) {
        VentaExtra ventaExtra = extraerVentaExtraForm(request);
        VentaExtraCRUD ventaExtraCRUD = new VentaExtraCRUD();
        try {
            ventaExtraCRUD.registrar(ventaExtra);
            response.sendRedirect("/aserradero/VentaExtraController?action=listar");
        } catch (Exception ex) {
            System.out.println(ex);
            listarVentaExtras(request, response, sesion, "error_registrar");
            Logger.getLogger(VentaExtraController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // Extraer datos del formulario
    private VentaExtra extraerVentaExtraForm(HttpServletRequest request) {
        VentaExtra ventaExtra = new VentaExtra();
        ventaExtra.setId_venta(request.getParameter("id_venta"));
        ventaExtra.setTipo(request.getParameter("tipo"));
        ventaExtra.setMonto(BigDecimal.valueOf((Double.valueOf(request.getParameter("monto")))));
        ventaExtra.setObservacion(request.getParameter("observacion"));
        return ventaExtra;
    }

    private void listarVentaExtras(HttpServletRequest request, HttpServletResponse response, HttpSession sesion, String mensaje) {
        List<Venta> ventaExtras;
        VentaExtraCRUD ventaExtraCrud = new VentaExtraCRUD();
        try {
            ventaExtras = (List<Venta>) ventaExtraCrud.listar((String) sesion.getAttribute("id_jefe"), (String) sesion.getAttribute("rol"));
            //Enviamos las listas al jsp
            request.setAttribute("ventaExtras", ventaExtras);
            //enviamos mensaje al jsp
            request.setAttribute("mensaje", mensaje);
            RequestDispatcher view = request.getRequestDispatcher("moduloVenta/ventaExtra/ventaExtras.jsp");
            view.forward(request, response);
        } catch (Exception ex) {
            System.out.println(ex);
            Logger.getLogger(VentaExtraController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void actualizarVentaExtra(HttpServletRequest request, HttpServletResponse response, HttpSession sesion, String action) {
        VentaExtra ventaExtra = extraerVentaExtraForm(request);
        VentaExtraCRUD ventaExtraCRUD = new VentaExtraCRUD();
        try {
            ventaExtraCRUD.actualizar(ventaExtra);
            listarDetalleVenta(ventaExtra.getId_venta(), request, response);
        } catch (Exception ex) {
            listarVentaExtras(request, response, sesion, "error_actualizar");
            System.out.println(ex);
            Logger.getLogger(VentaExtraController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void listarDetalleVenta(String id_venta, HttpServletRequest request, HttpServletResponse response) {
        List<VentaExtra> detalles;
        VentaExtraCRUD ventaExtraCrud = new VentaExtraCRUD();
        try {
            detalles = (List<VentaExtra>) ventaExtraCrud.listarDetalleVE(id_venta);
            //Enviamos los detalles de la venta
            request.setAttribute("detalles", detalles);
            //enviamos mensaje al jsp
            request.setAttribute("mensaje", "detalles");
            RequestDispatcher view = request.getRequestDispatcher("moduloVenta/ventaExtra/mostrarDetalleVE.jsp");
            view.forward(request, response);
        } catch (Exception ex) {
            System.out.println(ex);
            Logger.getLogger(VentaExtraController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void prepararNuevoVentaExtra(HttpServletRequest request, HttpServletResponse response, HttpSession sesion, String action) {
        try {
            VentaCRUD ventaCRUD = new VentaCRUD();
            List<Venta> ventas;
            ventas = (List<Venta>) ventaCRUD.listarVentasExtras((String) sesion.getAttribute("id_jefe"));
            request.setAttribute("ventas", ventas);

            //Generamos el Id de venta con los milisegundos del sistema
            String id_venta = String.valueOf(System.currentTimeMillis());
            request.setAttribute("siguienteventa", id_venta);

            //Enviamos la lista de clientes
            ClienteCRUD clienteCRUD = new ClienteCRUD();
            List<Cliente> clientes = (List<Cliente>) clienteCRUD.listar((String) sesion.getAttribute("id_jefe"), (String) sesion.getAttribute("rol"));
            request.setAttribute("clientes", clientes);

            RequestDispatcher view = request.getRequestDispatcher("moduloVenta/ventaExtra/nuevoVentaExtra.jsp");
            view.forward(request, response);
        } catch (Exception ex) {
            listarVentaExtras(request, response, sesion, "error_nuevo");
            Logger.getLogger(VentaController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void modificarVentaExtra(HttpServletRequest request, HttpSession sesion, HttpServletResponse response) {
        VentaExtra ventaExtraEC = new VentaExtra();
        ventaExtraEC.setId_venta(request.getParameter("id_venta"));
        ventaExtraEC.setTipo(request.getParameter("tipo"));
        VentaExtraCRUD ventaExtraCRUD = new VentaExtraCRUD();
        try {
            VentaExtra ventaExtra = (VentaExtra) ventaExtraCRUD.modificar(ventaExtraEC);
            request.setAttribute("ventaExtra", ventaExtra);
            RequestDispatcher view = request.getRequestDispatcher("moduloVenta/ventaExtra/actualizarVentaExtra.jsp");
            view.forward(request, response);
        } catch (Exception ex) {
            System.out.println(ex);
            listarVentaExtras(request, response, sesion, "error_modificar");
            Logger.getLogger(VentaExtraController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void eliminarVentaExtra(HttpServletRequest request, HttpSession sesion, HttpServletResponse response) {
        VentaExtra ventaExtraEC = new VentaExtra();
        ventaExtraEC.setId_venta(request.getParameter("id_venta"));
        ventaExtraEC.setTipo(request.getParameter("tipo"));
        VentaExtraCRUD ventaExtraCRUD = new VentaExtraCRUD();
        try {
            ventaExtraCRUD.eliminar(ventaExtraEC);
            listarDetalleVenta(ventaExtraEC.getId_venta(), request, response);
        } catch (Exception ex) {
            System.out.println(ex);
            listarVentaExtras(request, response, sesion, "error_eliminar");
            Logger.getLogger(VentaExtraController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void listarDetalleVentaExtra(HttpServletRequest request, HttpServletResponse response, HttpSession sesion, String action) {
        String id_venta = request.getParameter("id_venta");
        listarDetalleVenta(id_venta, request, response);
    }
}
