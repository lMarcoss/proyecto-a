package controlador.venta;

import dao.maderaAserrada.InventarioMaderaAserradaCRUD;
import dao.registros.ClienteCRUD;
import dao.venta.VentaMayoreoCRUD;
import entidades.maderaAserrada.InventarioMaderaAserrada;
import entidades.registros.Cliente;
import entidades.venta.Venta;
import entidades.venta.VentaMayoreo;
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
public class VentaMayoreoController extends HttpServlet {

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
                    registrarVentaMayoreo(request, response, sesion, action);
                    break;
                case "actualizar":
                    actualizarVentaMayoreo(request, response, sesion, action);
                    break;
                case "buscar":
                    buscar(request, response, sesion, action);
                    break;
                /**
                 * *************** Respuestas a métodos GET
                 * *********************
                 */
                case "nuevo":
                    prepararNuevoVentaMayoreo(request, response, sesion, action);
                    break;
                case "listar":
                    listarVentaMayoreos(request, response, sesion, action);
                    break;
                case "modificar":
                    modificarVentaMayoreo(request, sesion, response);
                    break;
                case "eliminar":
                    eliminarVentaMayoreo(request, sesion, response);
                    break;
                case "detalle":
                    listarDetalleVentaMayoreo(request, response, sesion, action);
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

    private void registrarVentaMayoreo(HttpServletRequest request, HttpServletResponse response, HttpSession sesion, String action) {
        VentaMayoreo ventaMayoreo = extraerVentaMayoreoForm(request, sesion, action);
        VentaMayoreoCRUD ventaMayoreoCRUD = new VentaMayoreoCRUD();
        try {
            ventaMayoreoCRUD.registrar(ventaMayoreo);
            response.sendRedirect("/aserradero/VentaMayoreoController?action=listar");
        } catch (Exception ex) {
            System.out.println(ex);
            listarVentaMayoreos(request, response, sesion, "error_registrar");
            Logger.getLogger(VentaMayoreoController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // Extraer datos del formulario
    private VentaMayoreo extraerVentaMayoreoForm(HttpServletRequest request, HttpSession sesion, String action) {
        VentaMayoreo ventaMayoreo = new VentaMayoreo();
        ventaMayoreo.setId_administrador((String) sesion.getAttribute("id_jefe"));
        ventaMayoreo.setId_venta(request.getParameter("id_venta"));
        ventaMayoreo.setId_madera(request.getParameter("id_madera"));
        ventaMayoreo.setNum_piezas(Integer.valueOf(request.getParameter("num_piezas")));
        ventaMayoreo.setVolumen(BigDecimal.valueOf(Double.valueOf(request.getParameter("volumen"))));
        ventaMayoreo.setMonto(BigDecimal.valueOf(Double.valueOf(request.getParameter("monto"))));
        ventaMayoreo.setTipo_madera(request.getParameter("tipo_madera"));
        return ventaMayoreo;
    }

    private void listarVentaMayoreos(HttpServletRequest request, HttpServletResponse response, HttpSession sesion, String action) {
        List<Venta> listaVentas;
        VentaMayoreoCRUD ventaMayoreoCrud = new VentaMayoreoCRUD();
        try {
            listaVentas = (List<Venta>) ventaMayoreoCrud.listar((String) sesion.getAttribute("id_jefe"),(String) sesion.getAttribute("rol"));
            mostrarVentas(request, response, listaVentas, sesion, action);
        } catch (Exception ex) {
            System.out.println(ex);
            Logger.getLogger(VentaMayoreoController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void mostrarVentas(HttpServletRequest request, HttpServletResponse response, List<Venta> listaVentas, HttpSession sesion, String action) {
        //Enviamos las listas al jsp
        request.setAttribute("listaVentas", listaVentas);
        //enviamos mensaje al jsp
        request.setAttribute("mensaje", action);
        RequestDispatcher view = request.getRequestDispatcher("moduloVenta/ventaMayoreo/ventaMayoreos.jsp");
        try {
            view.forward(request, response);
        } catch (ServletException | IOException ex) {
            Logger.getLogger(VentaMayoreoController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void actualizarVentaMayoreo(HttpServletRequest request, HttpServletResponse response, HttpSession sesion, String action) {
        VentaMayoreo ventaMayoreo = extraerVentaMayoreoForm(request, sesion, action);
        VentaMayoreoCRUD ventaMayoreoCRUD = new VentaMayoreoCRUD();
        try {
            ventaMayoreoCRUD.actualizar(ventaMayoreo);
            listarDetalleVentaMayoreo(ventaMayoreo.getId_venta(), request, response);
        } catch (Exception ex) {
            System.out.println(ex);
            listarVentaMayoreos(request, response, sesion, "error_actualizar");
            Logger.getLogger(VentaMayoreoController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void listarDetalleVentaMayoreo(String id_venta, HttpServletRequest request, HttpServletResponse response) {
        List<VentaMayoreo> detalles;
        VentaMayoreoCRUD ventaMayoreoCrud = new VentaMayoreoCRUD();
        try {
            detalles = (List<VentaMayoreo>) ventaMayoreoCrud.listarDetalleVentaMayoreo(id_venta);
            //Enviamos los detalles de la venta
            request.setAttribute("detalles", detalles);
            //enviamos mensaje al jsp
            request.setAttribute("mensaje", "detalles");
            RequestDispatcher view = request.getRequestDispatcher("moduloVenta/ventaMayoreo/mostrarDetalleVM.jsp");
            view.forward(request, response);
        } catch (Exception ex) {
            System.out.println(ex);
            Logger.getLogger(VentaMayoreoController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void buscar(HttpServletRequest request, HttpServletResponse response, HttpSession sesion, String action) {
        List<VentaMayoreo> ventaMayoreos;
        String nombre_campo = request.getParameter("nombre_campo");
        String dato = request.getParameter("dato");
        VentaMayoreoCRUD ventaMayoreoCRUD = new VentaMayoreoCRUD();
        try {
            ventaMayoreos = (List<VentaMayoreo>) ventaMayoreoCRUD.buscar(nombre_campo, dato, (String) sesion.getAttribute("id_jefe"), (String) sesion.getAttribute("rol"));
            request.setAttribute("ventaMayoreos", ventaMayoreos);
            RequestDispatcher view = request.getRequestDispatcher("moduloVenta/ventaMayoreo/ventaMayoreos.jsp");
            view.forward(request, response);
        } catch (Exception ex) {
            System.out.println(ex);
            listarVentaMayoreos(request, response, sesion, "error_buscar_campo");
            Logger.getLogger(VentaMayoreoController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void prepararNuevoVentaMayoreo(HttpServletRequest request, HttpServletResponse response, HttpSession sesion, String action) {
        try {
            //Generamos el Id de venta con los milisegundos del sistema
            String id_venta = String.valueOf(System.currentTimeMillis());
            request.setAttribute("siguienteventa", id_venta);

            //Enviamos id_administrador
            request.setAttribute("id_administrador", (String) sesion.getAttribute("id_jefe"));

            //Enviamos la lista de inventario
            List<InventarioMaderaAserrada> listaInventario;
            InventarioMaderaAserradaCRUD inventarioMA = new InventarioMaderaAserradaCRUD();
            listaInventario = (List<InventarioMaderaAserrada>) inventarioMA.listar((String) sesion.getAttribute("id_jefe"), (String) sesion.getAttribute("rol"));
            request.setAttribute("listaInventario", listaInventario);

            //Enviamos la lista de clientes
            ClienteCRUD clienteCRUD = new ClienteCRUD();
            List<Cliente> clientes = (List<Cliente>) clienteCRUD.listar((String) sesion.getAttribute("id_jefe"), (String) sesion.getAttribute("rol"));
            request.setAttribute("clientes", clientes);

            RequestDispatcher view = request.getRequestDispatcher("moduloVenta/ventaMayoreo/nuevoVentaMayoreo.jsp");
            view.forward(request, response);
        } catch (Exception ex) {
            listarVentaMayoreos(request, response, sesion, "error_nuevo");
            Logger.getLogger(VentaController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void modificarVentaMayoreo(HttpServletRequest request, HttpSession sesion, HttpServletResponse response) {
        VentaMayoreo ventaMayoreoEC = new VentaMayoreo();
        ventaMayoreoEC.setId_venta(request.getParameter("id_venta"));
        ventaMayoreoEC.setId_madera(request.getParameter("id_madera"));
        ventaMayoreoEC.setTipo_madera(request.getParameter("tipo_madera"));
        VentaMayoreoCRUD ventaMayoreoCRUD = new VentaMayoreoCRUD();
        try {
            // Enviamos el registro a modificar
            VentaMayoreo ventaMayoreo = (VentaMayoreo) ventaMayoreoCRUD.modificar(ventaMayoreoEC);
            request.setAttribute("ventaMayoreo", ventaMayoreo);

            //Enviamos la lista de inventario
            List<InventarioMaderaAserrada> listaInventario;
            InventarioMaderaAserradaCRUD inventarioMA = new InventarioMaderaAserradaCRUD();
            listaInventario = (List<InventarioMaderaAserrada>) inventarioMA.listar((String) sesion.getAttribute("id_jefe"), (String) sesion.getAttribute("rol"));
            request.setAttribute("listaInventario", listaInventario);

            RequestDispatcher view = request.getRequestDispatcher("moduloVenta/ventaMayoreo/actualizarVentaMayoreo.jsp");
            view.forward(request, response);
        } catch (Exception ex) {
            System.out.println(ex);
            listarVentaMayoreos(request, response, sesion, "error_modificar");
            Logger.getLogger(VentaMayoreoController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void eliminarVentaMayoreo(HttpServletRequest request, HttpSession sesion, HttpServletResponse response) {
        VentaMayoreo ventaMayoreoEC = new VentaMayoreo();
        ventaMayoreoEC.setId_venta(request.getParameter("id_venta"));
        ventaMayoreoEC.setId_madera(request.getParameter("id_madera"));
        ventaMayoreoEC.setTipo_madera(request.getParameter("tipo_madera"));
        VentaMayoreoCRUD ventaMayoreoCRUD = new VentaMayoreoCRUD();
        try {
            ventaMayoreoCRUD.eliminar(ventaMayoreoEC);
            listarDetalleVentaMayoreo(ventaMayoreoEC.getId_venta(), request, response);
        } catch (Exception ex) {
            System.out.println(ex);
            listarVentaMayoreos(request, response, sesion, "error_eliminar");
            Logger.getLogger(VentaMayoreoController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void listarDetalleVentaMayoreo(HttpServletRequest request, HttpServletResponse response, HttpSession sesion, String action) {
        String id_venta = request.getParameter("id_venta");
        listarDetalleVentaMayoreo(id_venta, request, response);
    }

}
