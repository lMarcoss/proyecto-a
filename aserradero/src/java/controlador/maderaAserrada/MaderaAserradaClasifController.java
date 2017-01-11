package controlador.maderaAserrada;

import dao.maderaAserrada.MaderaAserradaClasifCRUD;
import entidades.maderaAserrada.MaderaAserradaClasif;
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
public class MaderaAserradaClasifController extends HttpServlet {

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
                    registrarMaderaAserradaClasif(request, response, sesion, action);
                    break;
                case "actualizar":
                    actualizarMaderaAserradaClasif(request, response, sesion, action);
                    break;
                case "buscar":
                    buscarMaderaAserradaClasif(request, response, sesion, action);
                    break;
                /**
                 * *************** Respuestas a métodos GET
                 * *********************
                 */
                case "nuevo":
                    prepararNuevoMaderaAserradaClasif(request, response, sesion, action);
                    break;
                case "listar":
                    listarMaderaAserradaClasif(request, response, sesion, action);
                    break;
                case "modificar":
                    modificarMaderaAserradaClasif(request, response, sesion, action);
                    break;
                case "eliminar":
                    eliminarMaderaAserradaClasif(request, response, sesion, action);
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

    private void registrarMaderaAserradaClasif(HttpServletRequest request, HttpServletResponse response, HttpSession sesion, String action) {
        MaderaAserradaClasif mAClasif = extraerMaderaAserradaClasifForm(request, sesion, action);
        MaderaAserradaClasifCRUD mAClasifCRUD = new MaderaAserradaClasifCRUD();
        try {
            mAClasifCRUD.registrar(mAClasif);
            response.sendRedirect("/aserradero/MaderaAserradaClasifController?action=listar"); // para evitar acciones repetidas al actualizar página
            //listarMaderaAserradaClasif(request, response, "registrado");
        } catch (Exception ex) {
            System.out.println(ex);
            listarMaderaAserradaClasif(request, response, sesion, "error_registrar");
            Logger.getLogger(MaderaAserradaClasifController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private MaderaAserradaClasif extraerMaderaAserradaClasifForm(HttpServletRequest request, HttpSession sesion, String action) {
        MaderaAserradaClasif mAClasif = new MaderaAserradaClasif();
        mAClasif.setId_administrador((String) sesion.getAttribute("id_jefe"));
        mAClasif.setId_empleado((String) sesion.getAttribute("id_empleado"));
        mAClasif.setId_madera(request.getParameter("id_madera"));
        mAClasif.setGrueso(BigDecimal.valueOf((Double.valueOf(request.getParameter("grueso")))));
        mAClasif.setGrueso_f(request.getParameter("grueso_f"));
        mAClasif.setAncho(BigDecimal.valueOf((Double.valueOf(request.getParameter("ancho")))));
        mAClasif.setAncho_f(request.getParameter("ancho_f"));
        mAClasif.setLargo(BigDecimal.valueOf((Double.valueOf(request.getParameter("largo")))));
        mAClasif.setLargo_f(request.getParameter("largo_f"));
        mAClasif.setCosto_por_volumen(BigDecimal.valueOf((Double.valueOf(request.getParameter("costo_por_volumen")))));
        return mAClasif;
    }

    private void actualizarMaderaAserradaClasif(HttpServletRequest request, HttpServletResponse response, HttpSession sesion, String action) {
        MaderaAserradaClasif mAClasif = extraerMaderaAserradaClasifForm(request, sesion, action);
        MaderaAserradaClasifCRUD mAClasifCRUD = new MaderaAserradaClasifCRUD();
        try {
            mAClasifCRUD.actualizar(mAClasif);
            response.sendRedirect("/aserradero/MaderaAserradaClasifController?action=listar"); // para evitar acciones repetidas al actualizar página
        } catch (Exception ex) {
            System.out.println(ex);
            listarMaderaAserradaClasif(request, response, sesion, "error_actualizar");
            Logger.getLogger(MaderaAserradaClasifController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void buscarMaderaAserradaClasif(HttpServletRequest request, HttpServletResponse response, HttpSession sesion, String action) {
        List<MaderaAserradaClasif> listaMaderaAserradaClasif;
        String nombre_campo = request.getParameter("nombre_campo"); //Nombre del campo asociado a la búsqueda
        String dato = request.getParameter("dato");                 // Valor a buscar en el campo
        MaderaAserradaClasifCRUD mAClasifCRUD = new MaderaAserradaClasifCRUD();
        try {
            listaMaderaAserradaClasif = (List<MaderaAserradaClasif>) mAClasifCRUD.buscar(nombre_campo, dato, (String) sesion.getAttribute("id_jefe"), (String) sesion.getAttribute("rol"));
            mostrarMaderaAserradaClasif(request, response, listaMaderaAserradaClasif, action);
        } catch (Exception ex) {
            System.out.println(ex);
            listarMaderaAserradaClasif(request, response, sesion, "error_buscar_campo");
            Logger.getLogger(MaderaAserradaClasifController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void mostrarMaderaAserradaClasif(HttpServletRequest request, HttpServletResponse response, List<MaderaAserradaClasif> listaMaderaAserradaClasif, String action) {
        request.setAttribute("mensaje", action);
        request.setAttribute("listaMaderaAserradaClasif", listaMaderaAserradaClasif);
        RequestDispatcher view = request.getRequestDispatcher("moduloMaderaAserrada/maderaAserradaClasif/listarMaderaAserradaClasif.jsp");
        try {
            view.forward(request, response);
        } catch (ServletException | IOException ex) {
            System.err.println("No se pudo mostrar la lista de clasificacion de madera aserrada");
            Logger.getLogger(MaderaAserradaClasifController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void prepararNuevoMaderaAserradaClasif(HttpServletRequest request, HttpServletResponse response, HttpSession sesion, String action) {
        RequestDispatcher view = request.getRequestDispatcher("moduloMaderaAserrada/maderaAserradaClasif/nuevoMaderaAserradaClasif.jsp");
        try {
            view.forward(request, response);
        } catch (ServletException | IOException ex) {
            System.out.println(ex);
            listarMaderaAserradaClasif(request, response, sesion, action);
            Logger.getLogger(MaderaAserradaClasifController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void listarMaderaAserradaClasif(HttpServletRequest request, HttpServletResponse response, HttpSession sesion, String action) {
        List<MaderaAserradaClasif> listaMaderaAserradaClasif;
        MaderaAserradaClasifCRUD mAClasifCRUD = new MaderaAserradaClasifCRUD();
        try {
            listaMaderaAserradaClasif = (List<MaderaAserradaClasif>) mAClasifCRUD.listar((String) sesion.getAttribute("id_jefe"), (String) sesion.getAttribute("rol"));
            mostrarMaderaAserradaClasif(request, response, listaMaderaAserradaClasif, action);
        } catch (Exception ex) {
            System.out.println(ex);
            Logger.getLogger(MaderaAserradaClasifController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void modificarMaderaAserradaClasif(HttpServletRequest request, HttpServletResponse response, HttpSession sesion, String action) {
        MaderaAserradaClasifCRUD mAClasifCRUD = new MaderaAserradaClasifCRUD();
        MaderaAserradaClasif mAClasif = new MaderaAserradaClasif();
        //Obtenemos el id_madera a modificar
        mAClasif.setId_administrador((String) sesion.getAttribute("id_jefe"));
        mAClasif.setId_madera(request.getParameter("id_madera"));
        try {
            mAClasif = (MaderaAserradaClasif) mAClasifCRUD.modificar(mAClasif);
            request.setAttribute("mAClasif", mAClasif);
            RequestDispatcher view = request.getRequestDispatcher("moduloMaderaAserrada/maderaAserradaClasif/actualizarMaderaAserradaClasif.jsp");
            view.forward(request, response);
        } catch (Exception ex) {
            listarMaderaAserradaClasif(request, response, sesion, "error_modificar");
            Logger.getLogger(MaderaAserradaClasifController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void eliminarMaderaAserradaClasif(HttpServletRequest request, HttpServletResponse response, HttpSession sesion, String action) {
        MaderaAserradaClasifCRUD mAClasifCRUD = new MaderaAserradaClasifCRUD();
        MaderaAserradaClasif mAClasif = new MaderaAserradaClasif();
        mAClasif.setId_administrador((String) sesion.getAttribute("id_jefe"));
        mAClasif.setId_madera(request.getParameter("id_madera"));
        try {
            mAClasifCRUD.eliminar(mAClasif);
            response.sendRedirect("/aserradero/MaderaAserradaClasifController?action=listar"); // para evitar acciones repetidas al actualizar página
        } catch (Exception ex) {
            listarMaderaAserradaClasif(request, response, sesion, "error_eliminar");
            Logger.getLogger(MaderaAserradaClasifController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
