package controlador.venta;

import ticketVenta.DatosClienteTicket;
import dao.registros.ClienteCRUD;
import dao.venta.VentaCRUD;
import dao.venta.VentaExtraCRUD;
import dao.venta.VentaMayoreoCRUD;
import dao.venta.VentaPaqueteCRUD;
import entidades.registros.Cliente;
import entidades.venta.Venta;
import entidades.venta.VentaExtra;
import entidades.venta.VentaMayoreo;
import entidades.venta.VentaPaquete;
import ticketVenta.Madera;
import ticketVenta.Paquete;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;
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
public class VentaController extends HttpServlet {

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
            throws ServletException, IOException, Exception {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");// Forzar a usar codificación UTF-8 iso-8859-1
        try {
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
                        registrarVenta(request, response, sesion, action);
                        break;
                    case "actualizar":
                        actualizarVenta(request, response, sesion, action);
                        break;
                    case "buscar":
                        buscar(request, response, sesion, action);
                        break;
                    /**
                     * *************** Respuestas a métodos GET
                     * *********************
                     */
                    case "nuevo":
                        prepararNuevoVenta(request, response, sesion, action);
                        break;
                    case "listar":
                        listarVenta(request, response, sesion, action);
                        break;
                    case "modificar":
                        modificarVenta(request, sesion, response);
                        break;
                    case "ticket_costo":
                        ticketCosto(request, sesion, response, action);
                        break;
                    case "ticket_sin_costo":
                        ticketSinCosto(request, sesion, response);
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
        } catch (Exception e) {
            System.out.println(e);
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
        try {
            processRequest(request, response);
//        response.setContentType("text/html;charset=UTF-8");
//        request.setCharacterEncoding("UTF-8");// Forzar a usar codificación UTF-8 iso-8859-1
//        //Llegan url
//        String action = request.getParameter("action");
//        Venta ventaEC; //Enviar al CRUD
//        Venta venta; //Respuesta del CRUD
//        RequestDispatcher view;
//        VentaCRUD ventaCRUD;
//        String id_venta;
//        switch (action) {
//            case "nuevo":
//
//                break;
//            case "listar":
//                listarVentas(request, response, "Lista de ventas");
//                break;
//            case "cobrar_venta":
//                ventaEC = new Venta();
//                ventaEC.setId_venta(request.getParameter("id_venta"));
//                ventaCRUD = new VentaCRUD();
//                try {
//                    venta = (Venta) ventaCRUD.modificar(ventaEC);
//                    request.setAttribute("venta", venta);
//                    view = request.getRequestDispatcher("venta/actualizarVenta.jsp");
//                    view.forward(request, response);
//                } catch (Exception ex) {
//                    listarVentas(request, response, "error_modificar");
//                    System.out.println(ex);
//                    Logger.getLogger(VentaController.class.getName()).log(Level.SEVERE, null, ex);
//                }
//                break;
//            case "eliminar":
//
//                break;
//            case "buscar_venta":
//                id_venta = request.getParameter("id_venta");
//                buscarVentaPorId(request, response, id_venta);
//                break;
//            case "ver_ticket":

//                break;
//        }
        } catch (Exception ex) {
            Logger.getLogger(VentaController.class.getName()).log(Level.SEVERE, null, ex);
        }

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
        try {
            processRequest(request, response);
//        response.setContentType("text/html;charset=UTF-8");
//        request.setCharacterEncoding("UTF-8");// Forzar a usar codificación UTF-8 iso-8859-1
//        String action = request.getParameter("action");
//        String tipo_venta = request.getParameter("tipo_venta");
//        Venta venta;
//        VentaCRUD ventaCRUD;
//        switch (action) {
//            case "nuevo":
//
//                break;
//            case "buscar":
//
//                break;
//            case "cobrar":
//                try {
//                    cobrarVenta(request, response);
//                } catch (Exception ex) {
//                    Logger.getLogger(VentaController.class.getName()).log(Level.SEVERE, null, ex);
//                }
//                break;
//        }
        } catch (Exception ex) {
            Logger.getLogger(VentaController.class.getName()).log(Level.SEVERE, null, ex);
        }
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

    private void registrarVenta(
            HttpServletRequest request, HttpServletResponse response, HttpSession sesion, String action) {
        Venta venta = extraerVentaForm(request, response, sesion, action);

        VentaCRUD ventaCRUD = new VentaCRUD();
        HttpSession sesion_ajax;
        try {
            ventaCRUD.registrar(venta);
            switch (venta.getTipo_venta()) {//Este tipo de venta es mayoreo y proviene de ajax
                case "Mayoreo":
                    sesion_ajax = request.getSession(true);
                    ArrayList<VentaMayoreo> VentaMay = (ArrayList<VentaMayoreo>) sesion_ajax.getAttribute("detalle_venta_mayoreo");
                    VentaMayoreoCRUD ventaMayoreoCRUD;
                    ventaMayoreoCRUD = new VentaMayoreoCRUD();
                    for (VentaMayoreo a : VentaMay) {
                        a.setId_administrador((String) sesion.getAttribute("id_jefe"));
                        ventaMayoreoCRUD.registrar(a);
                    }
                    break;
                case "Extra":
                    sesion_ajax = request.getSession(true);
                    ArrayList<VentaExtra> VentaExt = (ArrayList<VentaExtra>) sesion_ajax.getAttribute("detalle_venta_extra");
                    VentaExtraCRUD VentaExtraCrud;
                    VentaExtraCrud = new VentaExtraCRUD();
                    for (VentaExtra a : VentaExt) {
                        VentaExtraCrud.registrar(a);
                    }
                    break;
                case "Paquete":
                    sesion_ajax = request.getSession(true);
                    ArrayList<VentaPaquete> VentaPaq = (ArrayList<VentaPaquete>) sesion_ajax.getAttribute("detalle_venta_paquete");
                    VentaPaqueteCRUD VentaPaqueteCrud;
                    VentaPaqueteCrud = new VentaPaqueteCRUD();
                    for (VentaPaquete a : VentaPaq) {
                        a.setId_administrador((String) sesion.getAttribute("id_jefe"));
                        VentaPaqueteCrud.registrar(a);
                    }
                    break;
            }
            mostrarTicket(request, response, venta, venta.getTicket());
        } catch (Exception ex) {
            System.out.println(ex);
            try {
                response.sendRedirect("/aserradero/");
            } catch (IOException ex1) {
                System.out.println(ex1);
                Logger.getLogger(VentaController.class.getName()).log(Level.SEVERE, null, ex1);
            }
        }
    }

    // Extraer datos del formulario
    private Venta extraerVentaForm(HttpServletRequest request, HttpServletResponse response, HttpSession sesion, String action) {
        Venta venta = new Venta();
        venta.setFecha(Date.valueOf(request.getParameter("fecha")));
        venta.setId_venta(request.getParameter("id_venta"));
        venta.setId_cliente(request.getParameter("id_cliente"));
        venta.setId_empleado((String) sesion.getAttribute("id_empleado"));
        venta.setEstatus(request.getParameter("estatus"));
        venta.setTipo_venta(request.getParameter("tipo_venta"));
        venta.setPago(BigDecimal.valueOf(Double.valueOf(request.getParameter("pago"))));
        venta.setTicket(request.getParameter("ticket"));
        return venta;
    }

    private void actualizarVenta(HttpServletRequest request, HttpServletResponse response, HttpSession sesion, String action)
            throws IOException {
        Venta venta = extraerVentaForm(request, response, sesion, action);
        VentaCRUD ventaCRUD = new VentaCRUD();
        try {
            ventaCRUD.actualizar(venta);
            switch (venta.getTipo_venta()) {
                case "Paquete":
                    response.sendRedirect("/aserradero/VentaPaqueteController?action=listar");
                    break;
                case "Mayoreo":
                    response.sendRedirect("/aserradero/VentaMayoreoController?action=listar");
                    break;
                case "Extra":
                    response.sendRedirect("/aserradero/VentaExtraController?action=listar");
                    break;
            }
        } catch (Exception ex) {
            switch (venta.getTipo_venta()) {
                case "Paquete":
                    response.sendRedirect("/aserradero/VentaPaqueteController?action=listar");
                    break;
                case "Mayoreo":
                    response.sendRedirect("/aserradero/VentaMayoreoController?action=listar");
                    break;
                case "Extra":
                    response.sendRedirect("/aserradero/VentaExtraController?action=listar");
                    break;
            }
            Logger.getLogger(VentaController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void buscar(HttpServletRequest request, HttpServletResponse response, HttpSession sesion, String action) {
        List<Venta> ventas;
        String nombre_campo = request.getParameter("nombre_campo");
        String dato = request.getParameter("dato");
        VentaCRUD ventaCRUD = new VentaCRUD();
        try {
            ventas = (List<Venta>) ventaCRUD.buscar(nombre_campo, dato, (String) sesion.getAttribute("id_jefe"), (String) sesion.getAttribute("rol"));
            request.setAttribute("ventas", ventas);
            RequestDispatcher view = request.getRequestDispatcher("venta/ventas.jsp");
            view.forward(request, response);
        } catch (Exception ex) {
            System.out.println(ex);
            listarVenta(request, response, sesion, "error_buscar_campo");
            Logger.getLogger(VentaController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void prepararNuevoVenta(HttpServletRequest request, HttpServletResponse response, HttpSession sesion, String action) {
        try {
            //Enviamos la lista de clientes
            ClienteCRUD clienteCRUD = new ClienteCRUD();
            List<Cliente> clientes = (List<Cliente>) clienteCRUD.listar((String) sesion.getAttribute("id_jefe"), (String) sesion.getAttribute("rol"));
            request.setAttribute("clientes", clientes);

            RequestDispatcher view = request.getRequestDispatcher("venta/nuevoVenta.jsp");
            view.forward(request, response);
        } catch (Exception ex) {
            System.out.println(ex);
            listarVenta(request, response, sesion, "error_nuevo");
            Logger.getLogger(VentaController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void listarVenta(HttpServletRequest request, HttpServletResponse response, HttpSession sesion, String action) {
        List<Venta> ventas;
        VentaCRUD ventaCrud = new VentaCRUD();
        try {
            ventas = (List<Venta>) ventaCrud.listar((String) sesion.getAttribute("id_jefe"), (String) sesion.getAttribute("rol"));
            //Enviamos las listas al jsp
            request.setAttribute("ventas", ventas);
            //enviamos mensaje al jsp
            request.setAttribute("mensaje", action);
            RequestDispatcher view = request.getRequestDispatcher("venta/ventas.jsp");
            view.forward(request, response);
        } catch (Exception ex) {
            System.out.println(ex);
            Logger.getLogger(VentaController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void modificarVenta(HttpServletRequest request, HttpSession sesion, HttpServletResponse response)
            throws IOException {
        Venta ventaEC = new Venta();
        VentaCRUD ventaCRUD = new VentaCRUD();

        ventaEC.setId_venta(request.getParameter("id_venta"));
        ventaEC.setTipo_venta(request.getParameter("tipo_venta"));
        try {
            // Enviamos el registro a modificar
            Venta ventaM = (Venta) ventaCRUD.modificar(ventaEC);
            request.setAttribute("venta", ventaM);
            RequestDispatcher view = request.getRequestDispatcher("moduloVenta/venta/actualizarVenta.jsp");
            view.forward(request, response);
        } catch (Exception ex) {
            System.out.println(ex);
            switch (ventaEC.getTipo_venta()) {
                case "Paquete":
                    response.sendRedirect("/aserradero/VentaPaqueteController?action=listar");
                    break;
                case "Mayoreo":
                    response.sendRedirect("/aserradero/VentaMayoreoController?action=listar");
                    break;
                case "Extra":
                    response.sendRedirect("/aserradero/VentaExtraController?action=listar");
                    break;
            }
            Logger.getLogger(VentaPaqueteController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void mostrarTicket(HttpServletRequest request, HttpServletResponse response, Venta venta, String tipo_ticket)
            throws ServletException, IOException, Exception {
        switch (venta.getTipo_venta()) {
            case "Paquete":
                ticketVentaPaquete(request, response, venta, tipo_ticket);
                break;
            case "Mayoreo":
                ticketVentaMayoreo(request, response, venta, tipo_ticket);
                break;
            case "Extra":
                ticketVentaExtra(request, response, venta, tipo_ticket);
                break;
        }
    }

    private void ticketVentaPaquete(HttpServletRequest request, HttpServletResponse response, Venta venta, String tipo_ticket)
            throws Exception {
        VentaCRUD ventaCRUD = new VentaCRUD();
        VentaPaqueteCRUD vPaqueteCRUD = new VentaPaqueteCRUD();

        BigDecimal costo_venta;     // costo total de la venta
        BigDecimal volumen_venta;   // volumen total de la venta
        BigDecimal costo_madera;    // Costo total de madera en una venta (Paquete o mayoreo) 
        BigDecimal costo_amarre;    // Costo total de amarre en una venta (Paquete o mayoreo) 
        BigDecimal volumen_madera;  // Volumen total de madera en una venta (Paquete o mayoreo) 
        BigDecimal volumen_amarre;  // Volumen total de amarre en una venta (Paquete o mayoreo) 

        DatosClienteTicket datosCliente = (DatosClienteTicket) ventaCRUD.consultaDatosCliente(venta);
        List<Paquete> listaPaquetesMadera = vPaqueteCRUD.consultaPaquetesMaderaVendida(venta, "Madera");
        List<Paquete> listaPaquetesAmarre = vPaqueteCRUD.consultaPaquetesMaderaVendida(venta, "Amarre");
        List<VentaPaquete> listaMadera = vPaqueteCRUD.listarMadera(venta.getId_venta());

        //Madera
        if (!listaPaquetesMadera.isEmpty()) {
            costo_madera = vPaqueteCRUD.consultarCostoTotalMadera(venta);
            volumen_madera = vPaqueteCRUD.consultarVolumenTotalMadera(venta);
        } else {
            costo_madera = BigDecimal.valueOf(Double.valueOf("0"));
            volumen_madera = BigDecimal.valueOf(Double.valueOf("0"));
        }
        //Amarre
        if (!listaPaquetesAmarre.isEmpty()) {
            costo_amarre = vPaqueteCRUD.consultarCostoTotalAmarre(venta);
            volumen_amarre = vPaqueteCRUD.consultarVolumenTotalAmarre(venta);
        } else {
            costo_amarre = BigDecimal.valueOf(Double.valueOf("0"));
            volumen_amarre = BigDecimal.valueOf(Double.valueOf("0"));
        }

        // Se consulta el costo total si alguna de las dos listas no está vacía
        if (!listaPaquetesMadera.isEmpty() || !listaPaquetesAmarre.isEmpty()) {
            costo_venta = vPaqueteCRUD.consultarCostoTotalVentaPaquete(venta);
            volumen_venta = vPaqueteCRUD.consultarVolumenTotalVentaPaquete(venta);
        } else {
            costo_venta = BigDecimal.valueOf(Double.valueOf("0"));
            volumen_venta = BigDecimal.valueOf(Double.valueOf("0"));
        }
        //enviamos información al jsp
        request.setAttribute("datosCliente", datosCliente);
        request.setAttribute("listaPaquetesMadera", listaPaquetesMadera);
        request.setAttribute("listaPaquetesAmarre", listaPaquetesAmarre);
        request.setAttribute("listaMadera", listaMadera);
        //Costos
        request.setAttribute("costo_madera", costo_madera);
        request.setAttribute("costo_amarre", costo_amarre);
        request.setAttribute("costo_venta", costo_venta);
        //Volumen
        request.setAttribute("volumen_madera", volumen_madera);
        request.setAttribute("volumen_amarre", volumen_amarre);
        request.setAttribute("volumen_venta", volumen_venta);

        request.setAttribute("id_venta", venta.getId_venta());
        request.setAttribute("tipo_ticket", tipo_ticket);

        RequestDispatcher view = request.getRequestDispatcher("moduloVenta/venta/ticketVentaPaquete.jsp");
        view.forward(request, response);
    }

    private void ticketVentaMayoreo(HttpServletRequest request, HttpServletResponse response, Venta venta, String tipo_ticket)
            throws Exception {
        VentaCRUD ventaCRUD = new VentaCRUD();
        VentaMayoreoCRUD vMayoreoCRUD = new VentaMayoreoCRUD();

        BigDecimal costo_venta;     // costo total de la venta
        BigDecimal volumen_venta;   // volumen total de la venta
        BigDecimal costo_madera;    // Costo total de madera en una venta (Paquete o mayoreo) 
        BigDecimal costo_amarre;    // Costo total de amarre en una venta (Paquete o mayoreo) 
        BigDecimal volumen_madera;  // Volumen total de madera en una venta (Paquete o mayoreo) 
        BigDecimal volumen_amarre;  // Volumen total de amarre en una venta (Paquete o mayoreo) 

        DatosClienteTicket datosCliente = (DatosClienteTicket) ventaCRUD.consultaDatosCliente(venta);
        List<Madera> listaMadera = vMayoreoCRUD.consultaMaderaVendida(venta, "Madera");// tipo_madera = Madera
        List<Madera> listaAmarre = vMayoreoCRUD.consultaMaderaVendida(venta, "Amarre");// tipo_madera = Amarre
        List<VentaMayoreo> listaMaderaMayoreo = vMayoreoCRUD.listarMadera(venta.getId_venta());// tipo_madera = Madera y Amarre

        //Madera
        if (!listaMadera.isEmpty()) {
            costo_madera = vMayoreoCRUD.consultarCostoTotalMadera(venta);
            volumen_madera = vMayoreoCRUD.consultarVolumenTotalMadera(venta);
        } else {
            costo_madera = BigDecimal.valueOf(Double.valueOf("0"));
            volumen_madera = BigDecimal.valueOf(Double.valueOf("0"));
        }
        //Amarre
        if (!listaAmarre.isEmpty()) {
            costo_amarre = vMayoreoCRUD.consultarCostoTotalAmarre(venta);
            volumen_amarre = vMayoreoCRUD.consultarVolumenTotalAmarre(venta);
        } else {
            costo_amarre = BigDecimal.valueOf(Double.valueOf("0"));
            volumen_amarre = BigDecimal.valueOf(Double.valueOf("0"));
        }

        // Se consulta el costo total si alguna de las dos listas no está vacía
        if (!listaMadera.isEmpty() || !listaAmarre.isEmpty()) {
            costo_venta = vMayoreoCRUD.consultarCostoTotalVentaMayoreo(venta);
            volumen_venta = vMayoreoCRUD.consultarVolumenTotalVentaMayoreo(venta);
        } else {
            costo_venta = BigDecimal.valueOf(Double.valueOf("0"));
            volumen_venta = BigDecimal.valueOf(Double.valueOf("0"));
        }
        //enviamos información al jsp
        request.setAttribute("datosCliente", datosCliente);
        request.setAttribute("listaMadera", listaMadera);
        request.setAttribute("listaAmarre", listaAmarre);
        request.setAttribute("listaMaderaMayoreo", listaMaderaMayoreo);
        //Costos
        request.setAttribute("costo_madera", costo_madera);
        request.setAttribute("costo_amarre", costo_amarre);
        request.setAttribute("costo_venta", costo_venta);
        //Volumen
        request.setAttribute("volumen_madera", volumen_madera);
        request.setAttribute("volumen_amarre", volumen_amarre);
        request.setAttribute("volumen_venta", volumen_venta);

        request.setAttribute("id_venta", venta.getId_venta());
        request.setAttribute("tipo_ticket", tipo_ticket);

        RequestDispatcher view = request.getRequestDispatcher("moduloVenta/venta/ticketVentaMayoreo.jsp");
        view.forward(request, response);
    }

    private void ticketCosto(HttpServletRequest request, HttpSession sesion, HttpServletResponse response, String action)
            throws Exception {
        String id_venta = request.getParameter("id_venta");
        VentaCRUD ventaCRUD = new VentaCRUD();
        Venta venta = ventaCRUD.consultarVenta(id_venta);
        switch (venta.getTipo_venta()) {
            case "Paquete":
                ticketVentaPaquete(request, response, venta, "costo");
                break;
            case "Mayoreo":
                ticketVentaMayoreo(request, response, venta, "costo");
                break;
            case "Extra":
                ticketVentaExtra(request, response, venta, "costo");
                break;
        }
    }

    private void ticketSinCosto(HttpServletRequest request, HttpSession sesion, HttpServletResponse response)
            throws Exception {
        String id_venta = request.getParameter("id_venta");
        VentaCRUD ventaCRUD = new VentaCRUD();
        Venta venta = ventaCRUD.consultarVenta(id_venta);
        switch (venta.getTipo_venta()) {
            case "Paquete":
                ticketVentaPaquete(request, response, venta, "sin_costo");
                break;
            case "Mayoreo":
                ticketVentaMayoreo(request, response, venta, "sin_costo");
                break;
            case "Extra":
                ticketVentaExtra(request, response, venta, "sin_costo");
                break;
        }
    }

    private void ticketVentaExtra(HttpServletRequest request, HttpServletResponse response, Venta venta, String tipo_ticket)
            throws Exception {
        VentaCRUD ventaCRUD = new VentaCRUD();
        VentaExtraCRUD vExtraCRUD = new VentaExtraCRUD();

        BigDecimal costo_venta;     // costo total de la venta

        DatosClienteTicket datosCliente = (DatosClienteTicket) ventaCRUD.consultaDatosCliente(venta);
        List<VentaExtra> listaMaderaExtra = vExtraCRUD.listarMaderaV(venta.getId_venta());
        // Se consulta el costo total si alguna de las dos listas no está vacía
        if (!listaMaderaExtra.isEmpty()) {
            costo_venta = vExtraCRUD.consultarCostoTotalVentaExtra(venta);
        } else {
            costo_venta = BigDecimal.valueOf(Double.valueOf("0"));
        }
        //enviamos información al jsp
        request.setAttribute("datosCliente", datosCliente);

        request.setAttribute("listaMaderaExtra", listaMaderaExtra);
        //Costos
        request.setAttribute("costo_venta", costo_venta);
        request.setAttribute("tipo_ticket", tipo_ticket);
        request.setAttribute("id_venta", venta.getId_venta());
        RequestDispatcher view = request.getRequestDispatcher("moduloVenta/venta/ticketVentaExtra.jsp");
        view.forward(request, response);
    }
}
