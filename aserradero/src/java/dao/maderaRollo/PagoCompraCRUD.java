package dao.maderaRollo;

import dao.Conexion;
import entidades.maderaRollo.CuentaPago;
import entidades.maderaRollo.PagoCompra;
import entidadesVirtuales.VistaMontoPagoCompra;
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
public class PagoCompraCRUD extends Conexion implements OperacionesCRUD {

    @Override
    public void registrar(Object objeto) throws Exception {
        PagoCompra pagoCompra = (PagoCompra) objeto;
        try {
            this.abrirConexion();
            PreparedStatement st = this.conexion.prepareStatement(
                    "INSERT INTO PAGO_COMPRA (fecha, id_proveedor, monto_pago, monto_por_pagar, monto_por_cobrar) values ( ?, ?, ?, ?, ?)");
            st = cargarObject(st, pagoCompra);
            st.executeUpdate();
        } catch (Exception e) {
            System.err.println(e);
            throw e;
        } finally {
            this.cerrarConexion();
        }
    }

    @Override
    public PreparedStatement cargarObject(PreparedStatement st, Object objeto) throws SQLException {
        PagoCompra pagoCompra = (PagoCompra) objeto;
        st.setDate(1, pagoCompra.getFecha());
        st.setString(2, pagoCompra.getId_proveedor());
        st.setBigDecimal(3, pagoCompra.getMonto_pago());
        st.setBigDecimal(4, pagoCompra.getMonto_por_pagar());
        st.setBigDecimal(5, pagoCompra.getMonto_por_cobrar());
        return st;
    }

    @Override
    public <T> List listar(String id_jefe) throws Exception {
        List<PagoCompra> pagoCompras;
        try {
            this.abrirConexion();
            try (PreparedStatement st = this.conexion.prepareStatement("SELECT * FROM VISTA_PAGO_COMPRA WHERE id_administrador = ?")) {
                st.setString(1, id_jefe);
                pagoCompras = new ArrayList<>();
                try (ResultSet rs = st.executeQuery()) {
                    while (rs.next()) {
                        PagoCompra pagoCompra = (PagoCompra) extraerObject(rs);
                        pagoCompras.add(pagoCompra);
                    }
                } catch (Exception e) {
                    System.out.println(e);
                }
            } catch (Exception e) {
                pagoCompras = null;
                System.out.println(e);
            }
        } catch (Exception e) {
            System.out.println(e);
            throw e;
        } finally {
            cerrarConexion();
        }
        return pagoCompras;
    }

    @Override
    public Object extraerObject(ResultSet rs) throws SQLException {
        PagoCompra pagoCompra = new PagoCompra();
        pagoCompra.setId_pago(rs.getInt("id_pago"));
        pagoCompra.setFecha(rs.getDate("fecha"));
        pagoCompra.setId_proveedor(rs.getString("id_proveedor"));
        pagoCompra.setProveedor(rs.getString("proveedor"));
        pagoCompra.setId_administrador(rs.getString("id_administrador"));
        pagoCompra.setMonto_pago(rs.getBigDecimal("monto_pago"));
        pagoCompra.setMonto_por_pagar(rs.getBigDecimal("monto_por_pagar"));
        pagoCompra.setMonto_por_cobrar(rs.getBigDecimal("monto_por_cobrar"));
        return pagoCompra;
    }

    @Override
    public Object modificar(Object objeto) throws Exception {
        PagoCompra pagoCompraM = (PagoCompra) objeto;
        PagoCompra pagoCompra = null;
        this.abrirConexion();
        try (PreparedStatement st = this.conexion.prepareStatement("SELECT * FROM VISTA_PAGO_COMPRA WHERE id_pago = ?")) {
            st.setInt(1, pagoCompraM.getId_pago());
            try (ResultSet rs = st.executeQuery()) {
                while (rs.next()) {
                    pagoCompra = (PagoCompra) extraerObject(rs);
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return pagoCompra;
    }

    @Override
    public void actualizar(Object objeto) throws Exception {
        PagoCompra pagoCompra = (PagoCompra) objeto;
        try {
            this.abrirConexion();
            PreparedStatement st = this.conexion.prepareStatement("UPDATE PAGO_COMPRA SET fecha = ?, id_proveedor= ?, monto_pago = ?, monto_por_pagar = ?, monto_por_cobrar = ? where id_pago = ?");
            st.setDate(1, pagoCompra.getFecha());
            st.setString(2, pagoCompra.getId_proveedor());
            st.setBigDecimal(3, pagoCompra.getMonto_pago());
            st.setBigDecimal(4, pagoCompra.getMonto_por_pagar());
            st.setBigDecimal(5, pagoCompra.getMonto_por_cobrar());
            st.setInt(6, pagoCompra.getId_pago());
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
        PagoCompra pagoCompra = (PagoCompra) objeto;
        try {
            this.abrirConexion();
            PreparedStatement st = this.conexion.prepareStatement("DELETE FROM PAGO_COMPRA WHERE id_pago = ?");
            st.setInt(1, pagoCompra.getId_pago());
            st.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
            throw e;
        } finally {
            this.cerrarConexion();
        }
    }

    @Override
    public <T> List buscar(String nombre_campo, String dato, String id_jefe) throws Exception {
        List<PagoCompra> pagoCompras;
        try {
            this.abrirConexion();
            try (PreparedStatement st = this.conexion.prepareStatement("SELECT * FROM VISTA_PAGO_COMPRA WHERE " + nombre_campo + " like ?")) {
                st.setString(1, "%" + dato + "%");
                pagoCompras = new ArrayList<>();
                try (ResultSet rs = st.executeQuery()) {
                    while (rs.next()) {
                        PagoCompra pagoCompra = (PagoCompra) extraerObject(rs);
                        pagoCompras.add(pagoCompra);
                    }
                }
            }
        } catch (Exception e) {
            System.out.println(e);
            throw e;
        } finally {
            this.cerrarConexion();
        }
        return pagoCompras;
    }

//    public <T> List listarMontoPagoCompra(String administrador) throws Exception {
//        List<VistaMontoPagoCompra> listaMontoPagoCompra;
//        try {
//            this.abrirConexion();
//            try (PreparedStatement st = this.conexion.prepareStatement("SELECT * FROM VISTA_MONTO_PAGO_COMPRA WHERE id_administrador = ? AND existe_entrada > 0")) {
//                st.setString(1, administrador);
//                listaMontoPagoCompra = new ArrayList();
//                try (ResultSet rs = st.executeQuery()) {
//                    while (rs.next()) {
//                        VistaMontoPagoCompra montoPagoCompra = (VistaMontoPagoCompra) extraerMontoPagoCompra(rs);
//                        listaMontoPagoCompra.add(montoPagoCompra);
//                    }
//                }
//            } catch (Exception e) {
//                listaMontoPagoCompra = null;
//                System.out.println(e);
//            }
//        } catch (Exception e) {
//            System.out.println(e);
//            throw e;
//        } finally {
//            this.cerrarConexion();
//        }
//        return listaMontoPagoCompra;
//    }
//
//    private VistaMontoPagoCompra extraerMontoPagoCompra(ResultSet rs) throws SQLException {
//        VistaMontoPagoCompra monto = new VistaMontoPagoCompra();
//        monto.setId_proveedor(rs.getString("id_proveedor"));
//        monto.setProveedor(rs.getString("proveedor"));
//        monto.setMonto_por_pagar(rs.getBigDecimal("monto_por_pagar"));
//        monto.setCuenta_por_cobrar(rs.getBigDecimal("cuenta_por_cobrar"));
//        monto.setId_administrador(rs.getString("id_administrador"));
//        return monto;
//    }

    public <T> List listarCuentaPago(String id_jefe) throws Exception {
        List<CuentaPago> listaCuentas;
        try {
            this.abrirConexion();
            try (PreparedStatement st = this.conexion.prepareStatement("SELECT * FROM CUENTA_PAGO WHERE id_jefe = ? AND monto_total_madera > 0")) {
                st.setString(1, id_jefe);
                listaCuentas = new ArrayList<>();
                try (ResultSet rs = st.executeQuery()) {
                    while (rs.next()) {
                        CuentaPago cuenta = (CuentaPago) extraerCuentaPago(rs);
                        listaCuentas.add(cuenta);
                    }
                } catch (Exception e) {
                    System.out.println(e);
                }
            } catch (Exception e) {
                listaCuentas = null;
                System.out.println(e);
            }
        } catch (Exception e) {
            System.out.println(e);
            throw e;
        } finally {
            cerrarConexion();
        }
        return listaCuentas;
    }

    public CuentaPago extraerCuentaPago(ResultSet rs) throws SQLException {
        CuentaPago cuenta = new CuentaPago();
        cuenta.setId_proveedor(rs.getString("id_proveedor"));
        cuenta.setProveedor(rs.getString("proveedor"));
        cuenta.setMonto_total_madera(rs.getBigDecimal("monto_total_madera"));
        cuenta.setCuenta_por_pagar(rs.getBigDecimal("cuenta_por_pagar"));
        cuenta.setCuenta_por_cobrar(rs.getBigDecimal("cuenta_por_cobrar"));
        return cuenta;
    }

    public PagoCompra consultarPagoCompraPorID(int id_pago) throws Exception {
        PagoCompra pagoCompra = null;
        try {
            this.abrirConexion();
            try (PreparedStatement st = this.conexion.prepareStatement("SELECT * FROM VISTA_PAGO_COMPRA WHERE id_pago = ?")) {
                st.setInt(1, id_pago);
                try (ResultSet rs = st.executeQuery()) {
                    while (rs.next()) {
                        pagoCompra = (PagoCompra) extraerObject(rs);
                    }
                } catch (Exception e) {
                    System.out.println(e);
                }
            } catch (Exception e) {
                pagoCompra = null;
                System.out.println(e);
            }
        } catch (Exception e) {
            System.out.println(e);
            throw e;
        } finally {
            cerrarConexion();
        }
        return pagoCompra;
    }
}
