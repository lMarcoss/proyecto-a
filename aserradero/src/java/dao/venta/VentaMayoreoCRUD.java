package dao.venta;

import dao.Conexion;
import entidades.venta.Venta;
import entidades.venta.VentaMayoreo;
import interfaces.OperacionesCRUD;
import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import ticketVenta.Madera;

/**
 *
 * @author lmarcoss
 */
public class VentaMayoreoCRUD extends Conexion implements OperacionesCRUD {

    @Override
    public void registrar(Object objeto) throws Exception {
        VentaMayoreo ventaMayoreo = (VentaMayoreo) objeto;
        try {
            this.abrirConexion();
            PreparedStatement st = this.conexion.prepareStatement(
                    "INSERT INTO VENTA_MAYOREO (id_administrador,id_venta,id_madera,num_piezas,volumen,monto,tipo_madera) VALUES (?,?,?,?,?,?,?)");
            st = cargarObject(st, ventaMayoreo);
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
        VentaMayoreo ventaMayoreo = (VentaMayoreo) objeto;
        st.setString(1, ventaMayoreo.getId_administrador());
        st.setString(2, ventaMayoreo.getId_venta());
        st.setString(3, ventaMayoreo.getId_madera());
        st.setFloat(4, ventaMayoreo.getNum_piezas());
        st.setBigDecimal(5, ventaMayoreo.getVolumen());
        st.setBigDecimal(6, ventaMayoreo.getMonto());
        st.setString(7, ventaMayoreo.getTipo_madera());
        return st;
    }

    @Override
    public <T> List listar(String id_jefe, String rol) throws Exception {
        List<Venta> listaVentas;
        String consulta;
        if (rol.equals("Administrador")) {
            consulta = "SELECT * FROM VISTA_VENTA_MAYOREO WHERE id_jefe = ? ORDER BY fecha DESC";
        } else {
            consulta = "SELECT * FROM VISTA_VENTA_MAYOREO WHERE id_jefe = ? AND fecha = CURDATE() ORDER BY fecha DESC";
        }
        try {
            this.abrirConexion();
            try (PreparedStatement st = this.conexion.prepareStatement(consulta)) {
                st.setString(1, id_jefe);
                listaVentas = new ArrayList();
                try (ResultSet rs = st.executeQuery()) {
                    while (rs.next()) {
                        Venta venta = (Venta) extraerVenta(rs);
                        listaVentas.add(venta);
                    }
                }
            } catch (Exception e) {
                listaVentas = null;
                System.out.println(e);
            }
        } catch (Exception e) {
            System.out.println(e);
            throw e;
        } finally {
            this.cerrarConexion();
        }
        return listaVentas;
    }

    private Venta extraerVenta(ResultSet rs) throws SQLException {
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

    public <T> List listarDetalleVentaMayoreo(String id_venta) throws Exception {
        // Se consulta el detalle de una venta mayoreo
        List<VentaMayoreo> detalles;
        try {
            this.abrirConexion();
            try (PreparedStatement st = this.conexion.prepareStatement("SELECT * FROM VENTA_MAYOREO WHERE id_venta = ?")) {
                st.setString(1, id_venta);
                detalles = new ArrayList();
                try (ResultSet rs = st.executeQuery()) {
                    while (rs.next()) {
                        VentaMayoreo venta = (VentaMayoreo) extraerObject(rs);
                        detalles.add(venta);
                    }
                }
            } catch (Exception e) {
                detalles = null;
            }
        } catch (Exception e) {
            System.out.println(e);
            throw e;
        } finally {
            this.cerrarConexion();
        }
        return detalles;
    }

    @Override
    public Object extraerObject(ResultSet rs) throws SQLException {
        VentaMayoreo ventaMayoreo = new VentaMayoreo();
        ventaMayoreo.setId_administrador(rs.getString("id_administrador"));
        ventaMayoreo.setId_venta(rs.getString("id_venta"));
        ventaMayoreo.setId_madera(rs.getString("id_madera"));
        ventaMayoreo.setNum_piezas(rs.getInt("num_piezas"));
        ventaMayoreo.setVolumen(rs.getBigDecimal("volumen"));
        ventaMayoreo.setMonto(rs.getBigDecimal("monto"));
        ventaMayoreo.setTipo_madera(rs.getString("tipo_madera"));
        return ventaMayoreo;
    }

    @Override
    public Object modificar(Object objeto) throws Exception {
        VentaMayoreo ventaMayoreoM = (VentaMayoreo) objeto;
        VentaMayoreo ventaMayoreo = null;
        this.abrirConexion();
        try (PreparedStatement st = this.conexion.prepareStatement(
                "SELECT * FROM VENTA_MAYOREO WHERE id_venta = ? AND id_madera = ? AND tipo_madera = ?")) {
            st.setString(1, ventaMayoreoM.getId_venta());
            st.setString(2, ventaMayoreoM.getId_madera());
            st.setString(3, ventaMayoreoM.getTipo_madera());
            try (ResultSet rs = st.executeQuery()) {
                while (rs.next()) {
                    ventaMayoreo = (VentaMayoreo) extraerObject(rs);
                }
            }
        }
        return ventaMayoreo;
    }

    @Override
    public void actualizar(Object objeto) throws Exception {
        VentaMayoreo ventaMayoreo = (VentaMayoreo) objeto;
        try {
            this.abrirConexion();
            PreparedStatement st = this.conexion.prepareStatement("UPDATE VENTA_MAYOREO SET num_piezas = ?, volumen = ?, monto = ? WHERE id_venta = ? AND id_madera = ? AND tipo_madera = ?");
            st.setInt(1, ventaMayoreo.getNum_piezas());
            st.setBigDecimal(2, ventaMayoreo.getVolumen());
            st.setBigDecimal(3, ventaMayoreo.getMonto());
            st.setString(4, ventaMayoreo.getId_venta());
            st.setString(5, ventaMayoreo.getId_madera());
            st.setString(6, ventaMayoreo.getTipo_madera());
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
        VentaMayoreo ventaMayoreo = (VentaMayoreo) objeto;
        if (!ventaPagado(ventaMayoreo.getId_venta())) {
            try {
                this.abrirConexion();
                PreparedStatement st = this.conexion.prepareStatement("DELETE FROM VENTA_MAYOREO WHERE id_venta = ? AND id_madera = ? AND tipo_madera=?");
                st.setString(1, ventaMayoreo.getId_venta());
                st.setString(2, ventaMayoreo.getId_madera());
                st.setString(3, ventaMayoreo.getTipo_madera());
                st.executeUpdate();
            } catch (Exception e) {
                System.out.println(e);
                throw e;
            } finally {
                this.cerrarConexion();
            }
        }

    }

    public <T> List buscar(String nombre_campo, String dato, String id_jefe, String rol) throws Exception {
        List<VentaMayoreo> ventaMayoreos;
        String consulta;
        if (rol.equals("Administrador")) {
            consulta = "SELECT * FROM VENTA_MAYOREO WHERE " + nombre_campo + " like ? AND id_administrador = ? ORDER BY fecha DESC";
        } else {
            consulta = "SELECT * FROM VENTA_MAYOREO WHERE " + nombre_campo + " like ? AND id_administrador = ? AND fecha = CURDATE() ORDER BY fecha DESC";
        }
        try {
            this.abrirConexion();
            try (PreparedStatement st = this.conexion.prepareStatement(consulta)) {
                st.setString(1, "%" + dato + "%");
                st.setString(2, id_jefe);
                ventaMayoreos = new ArrayList();
                try (ResultSet rs = st.executeQuery()) {
                    while (rs.next()) {
                        VentaMayoreo ventaMayoreo = (VentaMayoreo) extraerObject(rs);
                        ventaMayoreos.add(ventaMayoreo);
                    }
                }
            }
        } catch (Exception e) {
            System.out.println(e);
            throw e;
        } finally {
            this.cerrarConexion();
        }
        return ventaMayoreos;
    }

    private boolean ventaPagado(String id_venta) throws Exception {
        boolean pagado = false;
        this.abrirConexion();
        try (PreparedStatement st = this.conexion.prepareStatement("SELECT id_venta FROM VENTA WHERE estatus = ? AND id_venta = ?")) {
            st.setString(1, "Pagado");
            st.setString(2, id_venta);
            try (ResultSet rs = st.executeQuery()) {
                while (rs.next()) {
                    pagado = true;
                }
            }
        }
        return pagado;
    }

    public List<Madera> consultaMaderaVendida(Venta venta, String tipo_madera) {
        List<Madera> listaMVendida = new ArrayList<>();
        try {
            this.abrirConexion();
            PreparedStatement st = this.conexion.prepareStatement(
                    "SELECT "
                    + "C.id_madera, "
                    + "C.grueso_f, "
                    + "C.ancho_f, "
                    + "C.largo_f, "
                    + "C.volumen AS volumen_unitario, "
                    + "V.num_piezas, "
                    + "C.costo_por_volumen AS costo_volumen,"
                    + "tipo_madera, "
                    + "ROUND((V.num_piezas * C.volumen),3) AS volumen_total, "
                    + "ROUND((V.num_piezas * C.volumen * C.costo_por_volumen),2) AS costo_total "
                    + "FROM VENTA_MAYOREO AS V, MADERA_ASERRADA_CLASIF AS C "
                    + "WHERE V.id_madera = C.id_madera "
                    + "AND V.id_administrador = C.id_administrador "
                    + "AND id_venta = ? "
                    + "AND tipo_madera = ?");
            st.setString(1, venta.getId_venta());
            st.setString(2, tipo_madera);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Madera maderaV = extraerDatosMadera(rs);
                listaMVendida.add(maderaV);
            }
            this.cerrarConexion();
        } catch (Exception ex) {
            Logger.getLogger(VentaMayoreoCRUD.class.getName()).log(Level.SEVERE, null, ex);
        }

        // regresamos la lista de paquetes y cada paquete tiene una lista de madera
        return listaMVendida;
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

    public List<VentaMayoreo> listarMadera(String id_venta) throws Exception {
        List<VentaMayoreo> ventas;
        try {
            this.abrirConexion();
            try (PreparedStatement st = this.conexion.prepareStatement(
                    "SELECT "
                    + "V.id_madera, "
                    + "SUM(V.num_piezas) AS num_piezas, "
                    + "SUM(V.volumen) AS volumen, "
                    + "SUM(V.monto) AS monto "
                    + "FROM VENTA_MAYOREO AS V "
                    + "WHERE V.id_venta = ? "
                    + "GROUP BY V.id_madera")) {
                st.setString(1, id_venta);
                ventas = new ArrayList();
                try (ResultSet rs = st.executeQuery()) {
                    while (rs.next()) {
                        VentaMayoreo ventaMayoreo = (VentaMayoreo) extraerMadera(rs);
                        ventas.add(ventaMayoreo);
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

    private VentaMayoreo extraerMadera(ResultSet rs) throws SQLException {
        VentaMayoreo venta = new VentaMayoreo();
        venta.setId_madera(rs.getString("id_madera"));
        venta.setNum_piezas(rs.getInt("num_piezas"));
        venta.setVolumen(rs.getBigDecimal("volumen"));
        venta.setMonto(rs.getBigDecimal("monto"));
        return venta;
    }

    public BigDecimal consultarCostoTotalMadera(Venta venta) throws Exception {
        BigDecimal monto = null;
        try {
            this.abrirConexion();
            try (PreparedStatement st = this.conexion.prepareStatement(
                    "SELECT "
                    + "SUM(monto) AS monto "
                    + "FROM VENTA_MAYOREO "
                    + "WHERE id_venta = ? AND tipo_madera =  'Madera' "
                    + "GROUP BY id_venta")) {
                st.setString(1, venta.getId_venta());
                try (ResultSet rs = st.executeQuery()) {
                    while (rs.next()) {
                        monto = rs.getBigDecimal("monto");
                    }
                }
            } catch (Exception e) {
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

    public BigDecimal consultarVolumenTotalMadera(Venta venta) throws Exception {
        BigDecimal volumen = null;
        try {
            this.abrirConexion();
            try (PreparedStatement st = this.conexion.prepareStatement(
                    "SELECT "
                    + "SUM(volumen) AS volumen "
                    + "FROM VENTA_MAYOREO "
                    + "WHERE id_venta = ? AND tipo_madera =  'Madera' "
                    + "GROUP BY id_venta")) {
                st.setString(1, venta.getId_venta());
                try (ResultSet rs = st.executeQuery()) {
                    while (rs.next()) {
                        volumen = rs.getBigDecimal("volumen");
                    }
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        } catch (Exception e) {
            System.out.println(e);
            throw e;
        } finally {
            this.cerrarConexion();
        }
        return volumen;
    }

    public BigDecimal consultarCostoTotalAmarre(Venta venta) throws Exception {
        BigDecimal monto = null;
        try {
            this.abrirConexion();
            try (PreparedStatement st = this.conexion.prepareStatement(
                    "SELECT "
                    + "SUM(monto) AS monto "
                    + "FROM VENTA_MAYOREO "
                    + "WHERE id_venta = ? AND tipo_madera =  'Amarre' "
                    + "GROUP BY id_venta")) {
                st.setString(1, venta.getId_venta());
                try (ResultSet rs = st.executeQuery()) {
                    while (rs.next()) {
                        monto = rs.getBigDecimal("monto");
                    }
                }
            } catch (Exception e) {
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

    public BigDecimal consultarVolumenTotalAmarre(Venta venta) throws Exception {
        BigDecimal volumen = null;
        try {
            this.abrirConexion();
            try (PreparedStatement st = this.conexion.prepareStatement(
                    "SELECT "
                    + "SUM(volumen) AS volumen "
                    + "FROM VENTA_MAYOREO "
                    + "WHERE id_venta = ? AND tipo_madera =  'Amarre' "
                    + "GROUP BY id_venta")) {
                st.setString(1, venta.getId_venta());
                try (ResultSet rs = st.executeQuery()) {
                    while (rs.next()) {
                        volumen = rs.getBigDecimal("volumen");
                    }
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        } catch (Exception e) {
            System.out.println(e);
            throw e;
        } finally {
            this.cerrarConexion();
        }
        return volumen;
    }

    public BigDecimal consultarCostoTotalVentaMayoreo(Venta venta) throws Exception {
        BigDecimal monto = null;
        try {
            this.abrirConexion();
            try (PreparedStatement st = this.conexion.prepareStatement(
                    "SELECT sum(monto) AS monto FROM VENTA_MAYOREO WHERE id_venta = ? GROUP BY id_venta")) {
                st.setString(1, venta.getId_venta());
                try (ResultSet rs = st.executeQuery()) {
                    while (rs.next()) {
                        monto = rs.getBigDecimal("monto");
                        System.out.println("montoTotal:" + monto);
                    }
                }
            } catch (Exception e) {
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

    public BigDecimal consultarVolumenTotalVentaMayoreo(Venta venta) throws Exception {
        BigDecimal volumen = null;
        try {
            this.abrirConexion();
            try (PreparedStatement st = this.conexion.prepareStatement(
                    "SELECT sum(volumen) AS volumen FROM VENTA_MAYOREO WHERE id_venta = ? GROUP BY id_venta")) {
                st.setString(1, venta.getId_venta());
                try (ResultSet rs = st.executeQuery()) {
                    while (rs.next()) {
                        volumen = rs.getBigDecimal("volumen");
                        System.out.println("volumenTotal:" + volumen);
                    }
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        } catch (Exception e) {
            System.out.println(e);
            throw e;
        } finally {
            this.cerrarConexion();
        }
        return volumen;
    }
}
