package controlador.venta;

import dao.registros.ClienteCRUD;
import dao.maderaAserrada.InventarioMaderaAserradaCRUD;
import dao.venta.VentaPaqueteCRUD;
import entidades.registros.Cliente;
import entidades.maderaAserrada.InventarioMaderaAserrada;
import entidades.venta.Venta;
import entidades.venta.VentaPaquete;
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
public class VentaPaqueteController extends HttpServlet {

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
                    registrarVentaPaquete(request, response, sesion, action);
                    break;
                case "actualizar":
                    actualizarVentaPaquete(request, response, sesion, action);
                    break;
                case "buscar":
                    buscar(request, response, sesion, action);
                    break;
                /**
                 * *************** Respuestas a métodos GET
                 * *********************
                 */
                case "nuevo":
                    prepararNuevoVentaPaquete(request, response, sesion, action);
                    break;
                case "listar":
                    listarVentaPaquetes(request, response, sesion, action);
                    break;
                case "modificar":
                    modificarVentaPaquete(request, sesion, response);
                    break;
                case "eliminar":
                    eliminarVentaPaquete(request, sesion, response);
                    break;
                case "detalle":
                    listarDetalleVentaPaquete(request, response, sesion, action);
                    break;
            }
        } else {
            try {
                sesion.invalidate();
                response.sendRedirect("/aserradero/");
            } catch (IOException e) {
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

    private void registrarVentaPaquete(
            HttpServletRequest request, HttpServletResponse response, HttpSession sesion, String action) {
        VentaPaquete ventaPaquete = extraerVentaPaqueteForm(request, sesion, action);
        VentaPaqueteCRUD ventaPaqueteCRUD = new VentaPaqueteCRUD();
        try {
            ventaPaqueteCRUD.registrar(ventaPaquete);
            response.sendRedirect("/aserradero/VentaPaqueteController?action=listar");
        } catch (Exception ex) {
            System.out.println(ex);
            listarVentaPaquetes(request, response, sesion, "error_registrar");
            Logger.getLogger(VentaPaqueteController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // Extraer datos del formulario
    private VentaPaquete extraerVentaPaqueteForm(HttpServletRequest request, HttpSession sesion, String action) {
        VentaPaquete ventaPaquete = new VentaPaquete();
        ventaPaquete.setId_administrador((String) sesion.getAttribute("id_jefe"));
        ventaPaquete.setId_venta(request.getParameter("id_venta"));
        ventaPaquete.setNumero_paquete(Integer.valueOf(request.getParameter("numero_paquete")));
        ventaPaquete.setId_madera(request.getParameter("id_madera"));
        ventaPaquete.setNum_piezas(Integer.valueOf(request.getParameter("num_piezas")));
        ventaPaquete.setVolumen(BigDecimal.valueOf(Double.valueOf(request.getParameter("volumen"))));
        ventaPaquete.setMonto(BigDecimal.valueOf(Double.valueOf(request.getParameter("monto"))));
        ventaPaquete.setTipo_madera(request.getParameter("tipo_madera"));
        return ventaPaquete;
    }

    private void listarVentaPaquetes(
            HttpServletRequest request, HttpServletResponse response, HttpSession sesion, String action) {
        List<Venta> listaVentas;
        VentaPaqueteCRUD ventaPaqueteCrud = new VentaPaqueteCRUD();
        try {
            listaVentas = (List<Venta>) ventaPaqueteCrud.listar((String) sesion.getAttribute("id_jefe"));
            mostrarVentas(request, response, listaVentas, sesion, action);
        } catch (Exception ex) {
            System.out.println(ex);
            Logger.getLogger(VentaPaqueteController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void mostrarVentas(
            HttpServletRequest request, HttpServletResponse response,
            List<Venta> listaVentas, HttpSession sesion, String action) {
        //Enviamos las listas al jsp
        request.setAttribute("listaVentas", listaVentas);
        //enviamos mensaje al jsp
        request.setAttribute("mensaje", action);
        RequestDispatcher view = request.getRequestDispatcher("moduloVenta/ventaPaquete/ventaPaquetes.jsp");
        try {
            view.forward(request, response);
        } catch (ServletException | IOException ex) {
            Logger.getLogger(VentaPaqueteController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void actualizarVentaPaquete(
            HttpServletRequest request, HttpServletResponse response, HttpSession sesion, String action) {
        VentaPaquete ventaPaquete = extraerVentaPaqueteForm(request, sesion, action);
        VentaPaqueteCRUD ventaPaqueteCRUD = new VentaPaqueteCRUD();
        try {
            ventaPaqueteCRUD.actualizar(ventaPaquete);
            listarDetalleVentaPaquete(ventaPaquete.getId_venta(), request, response);
        } catch (Exception ex) {
            System.out.println(ex);
            listarVentaPaquetes(request, response, sesion, "error_actualizar");
            Logger.getLogger(VentaPaqueteController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void listarDetalleVentaPaquete(String id_venta, HttpServletRequest request, HttpServletResponse response) {
        List<VentaPaquete> detalles;
        VentaPaqueteCRUD ventaPaqueteCrud = new VentaPaqueteCRUD();
        try {
            detalles = (List<VentaPaquete>) ventaPaqueteCrud.listarDetalleVentaPaquete(id_venta);
            //Enviamos los detalles de la venta
            request.setAttribute("detalles", detalles);
            //enviamos mensaje al jsp
            request.setAttribute("mensaje", "detalles");
            RequestDispatcher view = request.getRequestDispatcher("moduloVenta/ventaPaquete/mostrarDetalleVP.jsp");
            view.forward(request, response);
        } catch (Exception ex) {
            System.out.println(ex);
            Logger.getLogger(VentaPaqueteController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void buscar(HttpServletRequest request, HttpServletResponse response, HttpSession sesion, String action) {
        List<Venta> ventaPaquetes;
        String nombre_campo = request.getParameter("nombre_campo");
        String dato = request.getParameter("dato");
        VentaPaqueteCRUD ventaPaqueteCRUD = new VentaPaqueteCRUD();
        try {
            ventaPaquetes = (List<Venta>) ventaPaqueteCRUD.buscar(nombre_campo, dato, (String) sesion.getAttribute("id_jefe"));
            mostrarVentas(request, response, ventaPaquetes, sesion, action);
        } catch (Exception ex) {
            System.out.println(ex);
            listarVentaPaquetes(request, response, sesion, "error_buscar_campo");
            Logger.getLogger(VentaPaqueteController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void prepararNuevoVentaPaquete(
            HttpServletRequest request, HttpServletResponse response, HttpSession sesion, String action) {
        try {
            //Generamos el Id de venta con los milisegundos del sistema
            String id_venta = String.valueOf(System.currentTimeMillis());
            request.setAttribute("siguienteventa", id_venta);

            //Enviamos id_administrador
            request.setAttribute("id_administrador", (String) sesion.getAttribute("id_jefe"));

            //Enviamos la lista de inventario
            List<InventarioMaderaAserrada> listaInventario;
            InventarioMaderaAserradaCRUD inventarioMA = new InventarioMaderaAserradaCRUD();
            listaInventario = (List<InventarioMaderaAserrada>) inventarioMA.listar((String) sesion.getAttribute("id_jefe"));
            request.setAttribute("listaInventario", listaInventario);

            //Enviamos la lista de clientes
            ClienteCRUD clienteCRUD = new ClienteCRUD();
            List<Cliente> clientes = (List<Cliente>) clienteCRUD.listar((String) sesion.getAttribute("id_jefe"));
            request.setAttribute("clientes", clientes);

            RequestDispatcher view = request.getRequestDispatcher("moduloVenta/ventaPaquete/nuevoVentaPaquete.jsp");
            view.forward(request, response);
        } catch (Exception ex) {
            listarVentaPaquetes(request, response, sesion, "error_nuevo");
            Logger.getLogger(VentaController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void modificarVentaPaquete(HttpServletRequest request, HttpSession sesion, HttpServletResponse response) {
        VentaPaquete ventaPaqueteEC = new VentaPaquete();
        ventaPaqueteEC.setId_venta(request.getParameter("id_venta"));
        ventaPaqueteEC.setId_madera(request.getParameter("id_madera"));
        ventaPaqueteEC.setNumero_paquete(Integer.valueOf(request.getParameter("numero_paquete")));
        ventaPaqueteEC.setTipo_madera(request.getParameter("tipo_madera"));
        VentaPaqueteCRUD ventaPaqueteCRUD = new VentaPaqueteCRUD();
        try {
            // Enviamos el registro a modificar
            VentaPaquete ventaPaquete = (VentaPaquete) ventaPaqueteCRUD.modificar(ventaPaqueteEC);
            request.setAttribute("ventaPaquete", ventaPaquete);

            //Enviamos la lista de inventario
            List<InventarioMaderaAserrada> listaInventario;
            InventarioMaderaAserradaCRUD inventarioMA = new InventarioMaderaAserradaCRUD();
            listaInventario = (List<InventarioMaderaAserrada>) inventarioMA.listar((String) sesion.getAttribute("id_jefe"));
            request.setAttribute("listaInventario", listaInventario);

            RequestDispatcher view = request.getRequestDispatcher("moduloVenta/ventaPaquete/actualizarVentaPaquete.jsp");
            view.forward(request, response);
        } catch (Exception ex) {
            System.out.println(ex);
            listarVentaPaquetes(request, response, sesion, "error_modificar");
            Logger.getLogger(VentaPaqueteController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void eliminarVentaPaquete(HttpServletRequest request, HttpSession sesion, HttpServletResponse response) {
        VentaPaquete ventaPaqueteEC = new VentaPaquete();
        ventaPaqueteEC.setId_venta(request.getParameter("id_venta"));
        ventaPaqueteEC.setId_madera(request.getParameter("id_madera"));
        ventaPaqueteEC.setNumero_paquete(Integer.valueOf(request.getParameter("numero_paquete")));
        ventaPaqueteEC.setTipo_madera(request.getParameter("tipo_madera"));
        VentaPaqueteCRUD ventaPaqueteCRUD = new VentaPaqueteCRUD();
        try {
            ventaPaqueteCRUD.eliminar(ventaPaqueteEC);
            listarDetalleVentaPaquete(ventaPaqueteEC.getId_venta(), request, response);
        } catch (Exception ex) {
            System.out.println(ex);
            listarVentaPaquetes(request, response, sesion, "error_eliminar");
            Logger.getLogger(VentaPaqueteController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void listarDetalleVentaPaquete(HttpServletRequest request, HttpServletResponse response, HttpSession sesion, String action) {
        String id_venta = request.getParameter("id_venta");
        listarDetalleVentaPaquete(id_venta, request, response);
    }
}
