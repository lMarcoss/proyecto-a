package dao.venta;

import dao.Conexion;
import entidades.venta.Venta;
import entidades.venta.VentaPaquete;
import interfaces.OperacionesCRUD;
import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import ticketVenta.Madera;
import ticketVenta.Paquete;

/**
 *
 * @author lmarcoss
 */
public class VentaPaqueteCRUD extends Conexion implements OperacionesCRUD {

    @Override
    public void registrar(Object objeto) throws Exception {
        VentaPaquete ventaPaquete = (VentaPaquete) objeto;
        try {
            this.abrirConexion();
            PreparedStatement st = this.conexion.prepareStatement(
                    "INSERT INTO VENTA_PAQUETE (id_administrador,id_venta,numero_paquete,id_madera,num_piezas,volumen,monto,tipo_madera) VALUES (?,?,?,?,?,?,?,?)");
            st = cargarObject(st, ventaPaquete);
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
        VentaPaquete ventaPaquete = (VentaPaquete) objeto;
        st.setString(1, ventaPaquete.getId_administrador());
        st.setString(2, ventaPaquete.getId_venta());
        st.setInt(3, ventaPaquete.getNumero_paquete());
        st.setString(4, ventaPaquete.getId_madera());
        st.setFloat(5, ventaPaquete.getNum_piezas());
        st.setBigDecimal(6, ventaPaquete.getVolumen());
        st.setBigDecimal(7, ventaPaquete.getMonto());
        st.setString(8, ventaPaquete.getTipo_madera());
        return st;
    }

    @Override
    public <T> List listar(String id_jefe, String rol) throws Exception {
        List<Venta> ventas;
        String consulta;
        if (rol.equals("Administrador")) {
            consulta = "SELECT * FROM VISTA_VENTA_PAQUETE WHERE id_jefe = ? ORDER BY fecha DESC";
        } else {
            consulta = "SELECT * FROM VISTA_VENTA_PAQUETE WHERE id_jefe = ? AND fecha = CURDATE() ORDER BY fecha DESC";
        }
        try {
            this.abrirConexion();
            try (PreparedStatement st = this.conexion.prepareStatement(consulta)) {
                st.setString(1, id_jefe);
                ventas = new ArrayList();
                try (ResultSet rs = st.executeQuery()) {
                    while (rs.next()) {
                        Venta venta = (Venta) extraerVenta(rs);
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

    public <VentaPaquete> List listarDetalleVentaPaquete(String id_venta) throws Exception {
        // Se consulta el detalle de una venta paquete
        List<VentaPaquete> detalles;
        try {
            this.abrirConexion();
            try (PreparedStatement st = this.conexion.prepareStatement("SELECT * FROM VENTA_PAQUETE WHERE id_venta = ?")) {
                st.setString(1, id_venta);
                detalles = new ArrayList();
                try (ResultSet rs = st.executeQuery()) {
                    while (rs.next()) {
                        VentaPaquete venta = (VentaPaquete) extraerObject(rs);
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
        VentaPaquete ventaPaquete = new VentaPaquete();
        ventaPaquete.setId_administrador(rs.getString("id_administrador"));
        ventaPaquete.setId_venta(rs.getString("id_venta"));
        ventaPaquete.setNumero_paquete(rs.getInt("numero_paquete"));
        ventaPaquete.setId_madera(rs.getString("id_madera"));
        ventaPaquete.setNum_piezas(rs.getInt("num_piezas"));
        ventaPaquete.setVolumen(rs.getBigDecimal("volumen"));
        ventaPaquete.setMonto(rs.getBigDecimal("monto"));
        ventaPaquete.setTipo_madera(rs.getString("tipo_madera"));
        return ventaPaquete;
    }

    @Override
    public Object modificar(Object objeto) throws Exception {
        VentaPaquete ventaPaqueteM = (VentaPaquete) objeto;
        VentaPaquete ventaPaquete = null;
        this.abrirConexion();
        try (PreparedStatement st = this.conexion.prepareStatement(
                "SELECT * FROM VENTA_PAQUETE WHERE id_venta = ? AND numero_paquete = ? AND id_madera = ? AND tipo_madera = ?")) {
            st.setString(1, ventaPaqueteM.getId_venta());
            st.setInt(2, ventaPaqueteM.getNumero_paquete());
            st.setString(3, ventaPaqueteM.getId_madera());
            st.setString(4, ventaPaqueteM.getTipo_madera());
            try (ResultSet rs = st.executeQuery()) {
                while (rs.next()) {
                    ventaPaquete = (VentaPaquete) extraerObject(rs);
                }
            }
        }
        return ventaPaquete;
    }

    @Override
    public void actualizar(Object objeto) throws Exception {
        VentaPaquete ventaPaquete = (VentaPaquete) objeto;
        try {
            this.abrirConexion();
            PreparedStatement st = this.conexion.prepareStatement(
                    "UPDATE VENTA_PAQUETE SET num_piezas = ?, volumen = ?, monto = ? WHERE id_venta = ? AND numero_paquete = ? AND id_madera = ? AND tipo_madera = ?");
            st.setInt(1, ventaPaquete.getNum_piezas());
            st.setBigDecimal(2, ventaPaquete.getVolumen());
            st.setBigDecimal(3, ventaPaquete.getMonto());
            st.setString(4, ventaPaquete.getId_venta());
            st.setInt(5, ventaPaquete.getNumero_paquete());
            st.setString(6, ventaPaquete.getId_madera());
            st.setString(7, ventaPaquete.getTipo_madera());
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
        VentaPaquete ventaPaquete = (VentaPaquete) objeto;
        if (!ventaPagado(ventaPaquete.getId_venta())) {
            try {
                this.abrirConexion();
                PreparedStatement st = this.conexion.prepareStatement(
                        "DELETE FROM VENTA_PAQUETE WHERE id_venta = ? AND numero_paquete = ? AND id_madera = ? AND tipo_madera = ?");
                st.setString(1, ventaPaquete.getId_venta());
                st.setInt(2, ventaPaquete.getNumero_paquete());
                st.setString(3, ventaPaquete.getId_madera());
                st.setString(4, ventaPaquete.getTipo_madera());
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
        List<Venta> ventaPaquetes;
        String consulta;
        if (rol.equals("Administrador")) {
            consulta = "SELECT * FROM VISTA_VENTA_PAQUETE WHERE " + nombre_campo + " like ? AND id_jefe = ? ORDER BY fecha DESC";
        } else {
            consulta = "SELECT * FROM VISTA_VENTA_PAQUETE WHERE " + nombre_campo + " like ? AND id_jefe = ? AND fecha = CURDATE() ORDER BY fecha DESC";
        }
        try {
            this.abrirConexion();
            try (PreparedStatement st = this.conexion.prepareStatement(consulta)) {
                st.setString(1, "%" + dato + "%");
                st.setString(2, id_jefe);
                ventaPaquetes = new ArrayList();
                try (ResultSet rs = st.executeQuery()) {
                    while (rs.next()) {
                        Venta ventaPaquete = (Venta) extraerVenta(rs);
                        ventaPaquetes.add(ventaPaquete);
                    }
                }
            }
        } catch (Exception e) {
            System.out.println(e);
            throw e;
        } finally {
            this.cerrarConexion();
        }
        return ventaPaquetes;
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

    public List<Paquete> consultaPaquetesMaderaVendida(Venta venta, String tipo_madera) throws Exception {
        List<Paquete> listaPaqueteMaderaVendida;

        //Lista de paquetes en una venta (paquete,volumen_paquete,costo_paquete)
        listaPaqueteMaderaVendida = consultarPaquetesYMontoPaquete(venta, tipo_madera);

        //Por cada paquete  consultamos la lista de madera
        for (Paquete paqueteVendida : listaPaqueteMaderaVendida) {
            int numero_paquete = paqueteVendida.getNumero_paquete();
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
                    + "FROM VENTA_PAQUETE AS V,MADERA_ASERRADA_CLASIF AS C "
                    + "WHERE V.id_madera = C.id_madera "
                    + "AND V.id_administrador = C.id_administrador "
                    + "AND id_venta = ? "
                    + "AND numero_paquete = ? "
                    + "AND tipo_madera = ?");
            st.setString(1, venta.getId_venta());
            st.setInt(2, numero_paquete);
            st.setString(3, tipo_madera);
            List<Madera> listaMaderaVendida = new ArrayList();
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Madera maderaV = extraerDatosMadera(rs);
                listaMaderaVendida.add(maderaV);
            }
            paqueteVendida.setListaMadera(listaMaderaVendida);
            this.cerrarConexion();
        }
        // regresamos la lista de paquetes y cada paquete tiene una lista de madera
        return listaPaqueteMaderaVendida;
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

    private List<Paquete> consultarPaquetesYMontoPaquete(Venta venta, String tipo_madera) throws Exception {
        List<Paquete> listaPaqueteMaderaVendida;
        try {
            this.abrirConexion();
            try (PreparedStatement st = this.conexion.prepareStatement(
                    "SELECT "
                    + "numero_paquete, "
                    + "SUM(monto) AS monto_total_paquete, "
                    + "SUM(volumen) AS volumen_total_paquete "
                    + "FROM VENTA_PAQUETE "
                    + "WHERE id_venta = ?  AND tipo_madera = ? "
                    + "GROUP BY numero_paquete "
                    + "ORDER BY numero_paquete ASC")) {
                st.setString(1, venta.getId_venta());
                st.setString(2, tipo_madera);
                listaPaqueteMaderaVendida = new ArrayList();
                try (ResultSet rs = st.executeQuery()) {
                    while (rs.next()) {
                        Paquete paquete = new Paquete();
                        paquete.setNumero_paquete(rs.getInt("numero_paquete"));
                        paquete.setListaMadera(null);
                        paquete.setMonto_total_paquete(rs.getBigDecimal("monto_total_paquete"));
                        paquete.setVolumen_total_paquete(rs.getBigDecimal("volumen_total_paquete"));
                        System.out.println(paquete.getMonto_total_paquete());
                        listaPaqueteMaderaVendida.add(paquete);
                    }
                }
            } catch (Exception e) {
                listaPaqueteMaderaVendida = null;
                System.out.println(e);
            }
        } catch (Exception e) {
            System.out.println(e);
            throw e;
        } finally {
            this.cerrarConexion();
        }
        return listaPaqueteMaderaVendida;
    }

    public BigDecimal consultarCostoTotalVentaPaquete(Venta venta) throws Exception {
        BigDecimal monto = null;
        try {
            this.abrirConexion();
            try (PreparedStatement st = this.conexion.prepareStatement("SELECT sum(monto) AS monto FROM VENTA_PAQUETE WHERE id_venta = ? GROUP BY id_venta")) {
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

    public BigDecimal consultarVolumenTotalVentaPaquete(Venta venta) throws Exception {
        BigDecimal volumen = null;
        try {
            this.abrirConexion();
            try (PreparedStatement st = this.conexion.prepareStatement("SELECT sum(volumen) AS volumen FROM VENTA_PAQUETE WHERE id_venta = ? GROUP BY id_venta")) {
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

    public BigDecimal consultarCostoTotalMadera(Venta venta) throws Exception {
        BigDecimal monto = null;
        try {
            this.abrirConexion();
            try (PreparedStatement st = this.conexion.prepareStatement(
                    "SELECT "
                    + "SUM(monto) AS monto "
                    + "FROM VENTA_PAQUETE "
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

    public BigDecimal consultarCostoTotalAmarre(Venta venta) throws Exception {
        BigDecimal monto = null;
        try {
            this.abrirConexion();
            try (PreparedStatement st = this.conexion.prepareStatement(
                    "SELECT "
                    + "SUM(monto) AS monto "
                    + "FROM VENTA_PAQUETE "
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

    public BigDecimal consultarVolumenTotalMadera(Venta venta) throws Exception {
        BigDecimal volumen = null;
        try {
            this.abrirConexion();
            try (PreparedStatement st = this.conexion.prepareStatement(
                    "SELECT "
                    + "SUM(volumen) AS volumen "
                    + "FROM VENTA_PAQUETE "
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

    public BigDecimal consultarVolumenTotalAmarre(Venta venta) throws Exception {
        BigDecimal volumen = null;
        try {
            this.abrirConexion();
            try (PreparedStatement st = this.conexion.prepareStatement(
                    "SELECT "
                    + "SUM(volumen) AS volumen "
                    + "FROM VENTA_PAQUETE "
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

    // Lista de maderas por clasificación en una venta por paquete (num_piezas,volumen,monto)
    public List<VentaPaquete> listarMadera(String id_venta) throws Exception {
        List<VentaPaquete> ventas;
        try {
            this.abrirConexion();
            try (PreparedStatement st = this.conexion.prepareStatement(
                    "SELECT "
                    + "V.id_madera, "
                    + "SUM(V.num_piezas) AS num_piezas, "
                    + "SUM(V.volumen) AS volumen, "
                    + "SUM(V.monto) AS monto "
                    + "FROM VENTA_PAQUETE AS V "
                    + "WHERE V.id_venta = ? "
                    + "GROUP BY V.id_madera")) {
                st.setString(1, id_venta);
                ventas = new ArrayList();
                try (ResultSet rs = st.executeQuery()) {
                    while (rs.next()) {
                        VentaPaquete ventaPaquete = (VentaPaquete) extraerMadera(rs);
                        ventas.add(ventaPaquete);
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

    private VentaPaquete extraerMadera(ResultSet rs) throws SQLException {
        VentaPaquete venta = new VentaPaquete();
        venta.setId_madera(rs.getString("id_madera"));
        venta.setNum_piezas(rs.getInt("num_piezas"));
        venta.setVolumen(rs.getBigDecimal("volumen"));
        venta.setMonto(rs.getBigDecimal("monto"));
        return venta;
    }
}
