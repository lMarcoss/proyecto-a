package dao.anticipo;

import dao.Conexion;
import entidades.anticipo.CuentaPorPagar;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author lmarcoss
 */
public class CuentaPorPagarCRUD extends Conexion {

    public <T> List buscarCPProveedor(String nombre_campo, String dato, String id_jefe) throws Exception {
        List<CuentaPorPagar> cuentaPorPagares;
        try {
            this.abrirConexion();
            try (PreparedStatement st = this.conexion.prepareStatement("SELECT * FROM C_POR_PAGAR_PROVEEDOR WHERE " + nombre_campo + " like ? AND id_jefe = ?")) {
                st.setString(1, "%" + dato + "%");
                st.setString(2, id_jefe);
                cuentaPorPagares = new ArrayList();
                try (ResultSet rs = st.executeQuery()) {
                    while (rs.next()) {
                        CuentaPorPagar cuentaPorPagar = (CuentaPorPagar) extraerObject(rs);
                        cuentaPorPagares.add(cuentaPorPagar);
                    }
                }
            }
        } catch (Exception e) {
            System.out.println(e);
            throw e;
        } finally {
            this.cerrarConexion();
        }
        return cuentaPorPagares;
    }

    public <T> List buscarCPCliente(String nombre_campo, String dato, String id_jefe) throws Exception {
        List<CuentaPorPagar> cuentaPorPagares;
        try {
            this.abrirConexion();
            try (PreparedStatement st = this.conexion.prepareStatement("SELECT * FROM C_POR_PAGAR_CLIENTE WHERE " + nombre_campo + " like ? AND id_jefe = ?")) {
                st.setString(1, "%" + dato + "%");
                st.setString(2, id_jefe);
                cuentaPorPagares = new ArrayList();
                try (ResultSet rs = st.executeQuery()) {
                    while (rs.next()) {
                        CuentaPorPagar cuentaPorPagar = (CuentaPorPagar) extraerObject(rs);
                        cuentaPorPagares.add(cuentaPorPagar);
                    }
                }
            }
        } catch (Exception e) {
            System.out.println(e);
            throw e;
        } finally {
            this.cerrarConexion();
        }
        return cuentaPorPagares;
    }

    public Object extraerObject(ResultSet rs) throws SQLException {
        CuentaPorPagar cuentaPorPagar = new CuentaPorPagar();
        cuentaPorPagar.setId_persona(rs.getString("id_persona"));
        cuentaPorPagar.setPersona(rs.getString("persona"));
        cuentaPorPagar.setId_jefe(rs.getString("id_jefe"));
        cuentaPorPagar.setMonto(rs.getBigDecimal("monto"));
        return cuentaPorPagar;
    }

    // listar cuenta por pagar de personaes
    public <T> List listarCPProveedore(String id_jefe) throws Exception {
        List<CuentaPorPagar> cuentaPorPagares;
        try {
            this.abrirConexion();
            try (PreparedStatement st = this.conexion.prepareStatement("SELECT * FROM C_POR_PAGAR_PROVEEDOR WHERE id_jefe = ?")) {
                st.setString(1, id_jefe);
                cuentaPorPagares = new ArrayList();
                try (ResultSet rs = st.executeQuery()) {
                    while (rs.next()) {
                        CuentaPorPagar cuentaPorPagar = (CuentaPorPagar) extraerObject(rs);
                        cuentaPorPagares.add(cuentaPorPagar);
                    }
                }
            } catch (Exception e) {
                cuentaPorPagares = null;
                System.out.println(e);
            }
        } catch (Exception e) {
            System.out.println(e);
            throw e;
        } finally {
            this.cerrarConexion();
        }
        return cuentaPorPagares;
    }

    // listar cuenta por pagar de clientes
    public <T> List listarCPCliente(String id_jefe) throws Exception {
        List<CuentaPorPagar> cuentaPorPagares;
        try {
            this.abrirConexion();
            try (PreparedStatement st = this.conexion.prepareStatement("SELECT * FROM C_POR_PAGAR_CLIENTE WHERE id_jefe = ?")) {
                st.setString(1, id_jefe);
                cuentaPorPagares = new ArrayList();
                try (ResultSet rs = st.executeQuery()) {
                    while (rs.next()) {
                        CuentaPorPagar cuentaPorPagar = (CuentaPorPagar) extraerObject(rs);
                        cuentaPorPagares.add(cuentaPorPagar);
                    }
                }
            } catch (Exception e) {
                cuentaPorPagares = null;
                System.out.println(e);
            }
        } catch (Exception e) {
            System.out.println(e);
            throw e;
        } finally {
            this.cerrarConexion();
        }
        return cuentaPorPagares;
    }
}
