package dao.venta;

import dao.Conexion;
import ticketVenta.DatosClienteTicket;
import entidades.venta.Venta;
import ticketVenta.Madera;
import ticketVenta.DatosVentaExtra;
import interfaces.OperacionesCRUD;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author lmarcoss
 */
public class VentaCRUD extends Conexion implements OperacionesCRUD {

    @Override
    public void registrar(Object objeto) throws Exception {
        Venta venta = (Venta) objeto;
        try {
            this.abrirConexion();
            PreparedStatement st = this.conexion.prepareStatement(
                    "INSERT INTO VENTA (id_venta,fecha,id_cliente,id_empleado,estatus,tipo_venta,pago) VALUES (?,?,?,?,?,?,?)");
            st = cargarObject(st, venta);
            st.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
            throw e;
        } finally {
            this.cerrarConexion();
        }
    }

    @Override
    public PreparedStatement cargarObject(PreparedStatement st, Object objeto) throws SQLException {
        Venta venta = (Venta) objeto;
        st.setString(1, venta.getId_venta());
        st.setDate(2, venta.getFecha());
        st.setString(3, venta.getId_cliente());
        st.setString(4, venta.getId_empleado());
        st.setString(5, venta.getEstatus());
        st.setString(6, venta.getTipo_venta());
        st.setBigDecimal(7, venta.getPago());
        return st;
    }

    @Override
    public <T> List listar(String id_jefe, String rol) throws Exception {
        List<Venta> ventas;
        String consulta;
        if (rol.equals("Administrador")) {
            consulta = "SELECT * FROM VISTA_VENTA WHERE id_jefe = ? ORDER BY fecha DESC,tipo_venta ASC";
        } else {
            consulta = "SELECT * FROM VISTA_VENTA WHERE id_jefe = ? AND fecha = CURDATE() ORDER BY fecha DESC,tipo_venta ASC";
        }
        try {
            this.abrirConexion();
            try (PreparedStatement st = this.conexion.prepareStatement(consulta)) {
                st.setString(1, id_jefe);
                ventas = new ArrayList();
                try (ResultSet rs = st.executeQuery()) {
                    while (rs.next()) {
                        Venta venta = (Venta) extraerObject(rs);
                        ventas.add(venta);
                    }
                }
            } catch (Exception e) {
                ventas = null;
                System.out.println(e);
            }
        } catch (Exception e) {
            System.out.println(e);
            throw e;
        } finally {
            this.cerrarConexion();
        }
        return ventas;
    }

    @Override
    public Object extraerObject(ResultSet rs) throws SQLException {
        Venta venta = new Venta();
        venta.setId_venta(rs.getString("id_venta"));
        venta.setFecha(rs.getDate("fecha"));
        venta.setId_cliente(rs.getString("id_cliente"));
        venta.setCliente(rs.getString("cliente"));
        venta.setId_empleado(rs.getString("id_empleado"));
        venta.setEstatus(rs.getString("estatus"));
        venta.setTipo_venta(rs.getString("tipo_venta"));
        venta.setPago(rs.getBigDecimal("pago"));
        return venta;
    }

    @Override
    public Object modificar(Object objeto) throws Exception {
        Venta ventaM = (Venta) objeto;
        System.out.println("id_venta: " + ventaM.getId_venta());
        Venta venta = null;
        String consulta = "";
        switch (ventaM.getTipo_venta()) {
            case "Paquete":
                consulta = "SELECT * FROM VISTA_VENTA_PAQUETE WHERE id_venta = ?";
                break;
            case "Mayoreo":
                consulta = "SELECT * FROM VISTA_VENTA_MAYOREO WHERE id_venta = ?";
                break;
            case "Extra":
                consulta = "SELECT * FROM VISTA_VENTA_EXTRA WHERE id_venta = ?";
                break;
        }
        this.abrirConexion();
        try (PreparedStatement st = this.conexion.prepareStatement(consulta)) {
            st.setString(1, ventaM.getId_venta());
            try (ResultSet rs = st.executeQuery()) {
                while (rs.next()) {
                    venta = (Venta) extraerVentaModificar(rs);
                }
            }
        }
        return venta;
    }

    public Venta extraerVentaModificar(ResultSet rs) throws SQLException {
        Venta venta = new Venta();
        venta.setId_venta(rs.getString("id_venta"));
        venta.setFecha(rs.getDate("fecha"));
        venta.setId_cliente(rs.getString("id_cliente"));
        venta.setCliente(rs.getString("cliente"));
        venta.setId_empleado(rs.getString("id_empleado"));
        venta.setEmpleado(rs.getString("empleado"));
        venta.setId_jefe(rs.getString("id_jefe"));
        venta.setEstatus(rs.getString("estatus"));
        venta.setTipo_venta(rs.getString("tipo_venta"));
        venta.setPago(rs.getBigDecimal("pago"));
        venta.setMonto(rs.getBigDecimal("monto"));
        return venta;
    }

    @Override
    public void actualizar(Object objeto) throws Exception {
        Venta venta = (Venta) objeto;
        try {
            this.abrirConexion();
            PreparedStatement st = this.conexion.prepareStatement("UPDATE VENTA SET fecha = ?, pago = ? WHERE id_venta = ?");
            st.setDate(1, venta.getFecha());
            st.setBigDecimal(2, venta.getPago());
            st.setString(3, venta.getId_venta());
            st.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
            throw e;
        } finally {
            this.cerrarConexion();
        }
    }

    @Override
    public void eliminar(Object objeto) throws Exception {
        Venta venta = (Venta) objeto;
        try {
            this.abrirConexion();
            PreparedStatement st = this.conexion.prepareStatement("DELETE FROM VENTA WHERE id_venta = ?");
            st.setString(1, venta.getId_venta());
            st.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
            throw e;
        } finally {
            this.cerrarConexion();
        }
    }

    @Override
    public <T> List buscar(String nombre_campo, String dato, String id_jefe, String rol) throws Exception {
        List<Venta> ventas;
        String consulta;
        if (rol.equals("Administrador")) {
            consulta = "SELECT * FROM VISTA_VENTA WHERE " + nombre_campo + " like ? AND id_jefe = ? ORDER BY fecha DESC,tipo_venta ASC";
        } else {
            consulta = "SELECT * FROM VISTA_VENTA WHERE " + nombre_campo + " like ? AND id_jefe = ? AND fecha = CURDATE() ORDER BY fecha DESC,tipo_venta ASC";
        }
        try {
            this.abrirConexion();
            try (PreparedStatement st = this.conexion.prepareStatement(consulta)) {
                st.setString(1, "%" + dato + "%");
                st.setString(2, id_jefe);
                ventas = new ArrayList();
                try (ResultSet rs = st.executeQuery()) {
                    while (rs.next()) {
                        Venta venta = (Venta) extraerObject(rs);
                        ventas.add(venta);
                    }
                }
            }
        } catch (Exception e) {
            System.out.println(e);
            throw e;
        } finally {
            this.cerrarConexion();
        }
        return ventas;
    }

    public List<Venta> buscarPorId(String id_venta) throws Exception {
        List<Venta> ventas;
        try {
            this.abrirConexion();
            try (PreparedStatement st = this.conexion.prepareStatement("SELECT * FROM VENTA WHERE id_venta = ?")) {
                st.setString(1, id_venta);
                ventas = new ArrayList();
                try (ResultSet rs = st.executeQuery()) {
                    while (rs.next()) {
                        Venta venta = (Venta) extraerObject(rs);
                        ventas.add(venta);
                    }
                }
            }
        } catch (Exception e) {
            System.out.println(e);
            throw e;
        } finally {
            this.cerrarConexion();
        }
        return ventas;
    }

    public <T> List listarVentasExtras(String id_jefe) throws Exception {
        List<Venta> ventas;
        try {
            this.abrirConexion();
            try (PreparedStatement st = this.conexion.prepareStatement("SELECT * FROM VISTA_VENTA WHERE tipo_venta = ? AND estatus = ? AND id_jefe = ? ORDER BY fecha")) {
                st.setString(1, "Extra");
                st.setString(2, "Sin pagar");
                st.setString(3, id_jefe);
                ventas = new ArrayList();
                try (ResultSet rs = st.executeQuery()) {
                    while (rs.next()) {
                        Venta venta = (Venta) extraerObject(rs);
                        ventas.add(venta);
                    }
                }
            } catch (Exception e) {
                ventas = null;
                System.out.println(e);
            }
        } catch (Exception e) {
            System.out.println(e);
            throw e;
        } finally {
            this.cerrarConexion();
        }
        return ventas;
    }

    public <T> List listarVentasPaquete(String id_jefe) throws Exception {
        List<Venta> ventas;
        try {
            this.abrirConexion();
            try (PreparedStatement st = this.conexion.prepareStatement("SELECT * FROM VISTA_VENTA WHERE tipo_venta = ? AND estatus = ? AND id_jefe = ? ORDER BY fecha DESC")) {
                st.setString(1, "Paquete");
                st.setString(2, "Sin pagar");
                st.setString(3, id_jefe);
                ventas = new ArrayList();
                try (ResultSet rs = st.executeQuery()) {
                    while (rs.next()) {
                        Venta venta = (Venta) extraerObject(rs);
                        ventas.add(venta);
                    }
                }
            } catch (Exception e) {
                ventas = null;
                System.out.println(e);
            }
        } catch (Exception e) {
            System.out.println(e);
            throw e;
        } finally {
            this.cerrarConexion();
        }
        return ventas;
    }

    public <T> List listarVentasMayoreo(String id_jefe) throws Exception {
        List<Venta> ventas;
        try {
            this.abrirConexion();
            try (PreparedStatement st = this.conexion.prepareStatement("SELECT * FROM VISTA_VENTA WHERE tipo_venta = ? AND estatus = ? AND id_jefe = ? ORDER BY fecha DESC")) {
                st.setString(1, "Mayoreo");
                st.setString(2, "Sin pagar");
                st.setString(3, id_jefe);
                ventas = new ArrayList();
                try (ResultSet rs = st.executeQuery()) {
                    while (rs.next()) {
                        Venta venta = (Venta) extraerObject(rs);
                        ventas.add(venta);
                    }
                }
            } catch (Exception e) {
                ventas = null;
                System.out.println(e);
            }
        } catch (Exception e) {
            System.out.println(e);
            throw e;
        } finally {
            this.cerrarConexion();
        }
        return ventas;
    }

    public boolean consultaClienteCuentaPorPagar(Venta venta) throws Exception {
        boolean existe = false;
        try {
            this.abrirConexion();
            try (PreparedStatement st = this.conexion.prepareStatement("SELECT CONSULTA_CLIENTE (?)")) {
                st.setString(1, venta.getId_cliente());
                try (ResultSet rs = st.executeQuery()) {
                    while (rs.next()) {
                        if (rs.getInt(1) == 1) {
                            existe = true;
                            System.out.println("Cuenta existe");
                        }
                    }
                }
            }
        } catch (Exception e) {
            System.out.println(e);
            throw e;
        } finally {
            this.cerrarConexion();
        }
        return existe;
    }

    public void pagarVentaPaqueteAnticipado(Venta venta) throws Exception {
        try {
            this.abrirConexion();
            try (PreparedStatement st = this.conexion.prepareStatement("SELECT PAGAR_VENTA_PAQUETE_ANTICIPADO (?,?)")) {
                st.setString(1, venta.getId_venta());
                st.setString(2, venta.getId_cliente());
                try (ResultSet rs = st.executeQuery()) {
                    while (rs.next()) {
                        System.out.println("Venta paquete Pagado");
                    }
                }
            }
        } catch (Exception e) {
            System.out.println(e);
            throw e;
        } finally {
            this.cerrarConexion();
        }
    }

    public void pagarVentaMayoreoAnticipado(Venta venta) throws Exception {
        try {
            this.abrirConexion();
            try (PreparedStatement st = this.conexion.prepareStatement("SELECT PAGAR_VENTA_MAYOREO_ANTICIPADO (?,?)")) {
                st.setString(1, venta.getId_venta());
                st.setString(2, venta.getId_cliente());
            }
        } catch (Exception e) {
            System.out.println(e);
            throw e;
        } finally {
            this.cerrarConexion();
        }
    }

    public boolean pagarVentaExtra(Venta venta) throws Exception {
        boolean var_venta = false;
        try {
            this.abrirConexion();
            try (PreparedStatement st = this.conexion.prepareStatement("SELECT PAGAR_VENTA_EXTRA (?,?)")) {
                st.setString(1, venta.getId_venta());
                st.setString(2, venta.getId_cliente());
                try (ResultSet rs = st.executeQuery()) {
                    while (rs.next()) {
                        var_venta = rs.getInt(1) == 1;
                    }
                }
            }
        } catch (Exception e) {
            System.out.println(e);
            throw e;
        } finally {
            this.cerrarConexion();
        }
        return var_venta;
    }

    public List<Madera> consultaMaderasVendidasMayoreo(Venta venta) throws Exception {
        List<Madera> listaMaderaVendidas;
        try {
            this.abrirConexion();
            try (PreparedStatement st = this.conexion.prepareStatement("select * from VISTA_VENTAS_POR_MAYOREO WHERE id_venta = ?")) {
                st.setString(1, venta.getId_venta());
                listaMaderaVendidas = new ArrayList();
                try (ResultSet rs = st.executeQuery()) {
                    while (rs.next()) {
                        Madera maderaV = extraerDatosMadera(rs);
                        listaMaderaVendidas.add(maderaV);
                    }
                }
            } catch (Exception e) {
                listaMaderaVendidas = null;
                System.out.println(e);
            }
        } catch (Exception e) {
            System.out.println(e);
            throw e;
        } finally {
            this.cerrarConexion();
        }
        return listaMaderaVendidas;
    }

    private Madera extraerDatosMadera(ResultSet rs) throws SQLException {
        Madera madera = new Madera();
        madera.setId_madera(rs.getString("id_madera"));
        madera.setGrueso_f(rs.getString("grueso_f"));
        madera.setAncho_f(rs.getString("ancho_f"));
        madera.setLargo_f(rs.getString("largo_f"));
        madera.setVolumen_unitario(rs.getBigDecimal("volumen_unitario"));
        madera.setNum_piezas(rs.getInt("num_piezas"));
        madera.setCosto_volumen(rs.getBigDecimal("costo_volumen"));
        madera.setVolumen_total(rs.getBigDecimal("volumen_total"));
        madera.setCosto_total(rs.getBigDecimal("costo_total"));
        return madera;
    }

    public DatosClienteTicket consultaDatosCliente(Venta venta) throws Exception {
        DatosClienteTicket datosCliente = null;
        try {
            this.abrirConexion();
            try (PreparedStatement st = this.conexion.prepareStatement("SELECT * FROM VISTA_CLIENTE_TICKET WHERE id_venta = ? AND id_cliente = ? AND tipo_venta = ?")) {
                st.setString(1, venta.getId_venta());
                st.setString(2, venta.getId_cliente());
                st.setString(3, venta.getTipo_venta());
                try (ResultSet rs = st.executeQuery()) {
                    while (rs.next()) {
                        datosCliente = extraerDatosCliente(rs);
                        System.out.println("Cliente encontrado");
                    }
                }
            } catch (Exception e) {
                datosCliente = null;
                System.out.println(e);
            }
        } catch (Exception e) {
            System.out.println(e);
            throw e;
        } finally {
            this.cerrarConexion();
        }
        return datosCliente;
    }

    private DatosClienteTicket extraerDatosCliente(ResultSet rs) throws SQLException {
        DatosClienteTicket datosCliente = new DatosClienteTicket();
        datosCliente.setId_venta(rs.getString("id_venta"));
        datosCliente.setFecha(rs.getDate("fecha"));
        datosCliente.setTipo_venta(rs.getString("tipo_venta"));
        datosCliente.setId_jefe(rs.getString("id_jefe"));
        datosCliente.setId_cliente(rs.getString("id_cliente"));
        datosCliente.setId_persona(rs.getString("id_persona"));
        datosCliente.setCliente(rs.getString("cliente"));
        datosCliente.setDireccion(rs.getString("direccion"));
        datosCliente.setLocalidad(rs.getString("localidad"));
        datosCliente.setMunicipio(rs.getString("municipio"));
        datosCliente.setEstado(rs.getString("estado"));
        return datosCliente;
    }

    public float consultarMontoTotalVentaMayoreo(Venta venta) throws Exception {
        float monto = 0;
        try {
            this.abrirConexion();
            try (PreparedStatement st = this.conexion.prepareStatement("select SUM(costo_total) as monto from VISTA_VENTAS_POR_MAYOREO WHERE id_venta = ? GROUP BY id_venta")) {
                st.setString(1, venta.getId_venta());
                try (ResultSet rs = st.executeQuery()) {
                    while (rs.next()) {
                        monto = rs.getFloat("monto");
                    }
                }
            } catch (Exception e) {
                monto = 0;
                System.out.println(e);
            }
        } catch (Exception e) {
            System.out.println(e);
            throw e;
        } finally {
            this.cerrarConexion();
        }
        return monto;
    }

    public List<DatosVentaExtra> consultaDatosVentaExtra(Venta venta) throws Exception {
        List<DatosVentaExtra> listaDatosVentaE;
        try {
            this.abrirConexion();
            try (PreparedStatement st = this.conexion.prepareStatement("SELECT tipo,observacion,monto FROM VISTA_VENTAS_EXTRA WHERE id_venta= ?")) {
                st.setString(1, venta.getId_venta());
                listaDatosVentaE = new ArrayList();
                try (ResultSet rs = st.executeQuery()) {
                    while (rs.next()) {
                        DatosVentaExtra datosVE = extraerDatosVentaExtra(rs);
                        listaDatosVentaE.add(datosVE);
                    }
                }
            } catch (Exception e) {
                listaDatosVentaE = null;
                System.out.println(e);
            }
        } catch (Exception e) {
            System.out.println(e);
            throw e;
        } finally {
            this.cerrarConexion();
        }
        return listaDatosVentaE;
    }

    private DatosVentaExtra extraerDatosVentaExtra(ResultSet rs) throws SQLException {
        DatosVentaExtra datosVe = new DatosVentaExtra();
        datosVe.setTipo(rs.getString("tipo"));
        datosVe.setObservacion(rs.getString("observacion"));
        datosVe.setMonto(rs.getFloat("monto"));
        return datosVe;
    }

    public float consultarMontoTotalVentaExtra(Venta venta) throws Exception {
        float monto = 0;
        try {
            this.abrirConexion();
            try (PreparedStatement st = this.conexion.prepareStatement("SELECT SUM(monto) AS monto_total FROM VISTA_VENTAS_EXTRA WHERE id_venta= ? GROUP BY id_venta;")) {
                st.setString(1, venta.getId_venta());
                try (ResultSet rs = st.executeQuery()) {
                    while (rs.next()) {
                        monto = rs.getFloat("monto_total");
                    }
                }
            } catch (Exception e) {
                monto = 0;
                System.out.println(e);
            }
        } catch (Exception e) {
            System.out.println(e);
            throw e;
        } finally {
            this.cerrarConexion();
        }
        return monto;
    }

    public Venta consultarVenta(String id_venta) throws Exception {
        Venta venta = new Venta();
        try {
            this.abrirConexion();
            try (PreparedStatement st = this.conexion.prepareStatement("SELECT * FROM VISTA_VENTA WHERE id_venta = ?")) {
                st.setString(1, id_venta);
                try (ResultSet rs = st.executeQuery()) {
                    while (rs.next()) {
                        venta = (Venta) extraerObject(rs);
                    }
                }
            } catch (Exception e) {
                venta = null;
                System.out.println(e);
            }
        } catch (Exception e) {
            System.out.println(e);
            throw e;
        } finally {
            this.cerrarConexion();
        }
        return venta;
    }
}
