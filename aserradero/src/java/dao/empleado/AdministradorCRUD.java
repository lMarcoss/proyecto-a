package dao.empleado;

import dao.Conexion;
import entidades.empleado.Administrador;
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
public class AdministradorCRUD extends Conexion implements OperacionesCRUD {

    @Override
    public void registrar(Object objeto) throws Exception {
        Administrador administrador = (Administrador) objeto;
        try {
            this.abrirConexion();
            PreparedStatement st = this.conexion.prepareStatement(
                    "INSERT INTO ADMINISTRADOR (id_administrador, cuenta_inicial) VALUES (?, ?)");
            st = cargarObject(st, administrador);
            st.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
            throw e;
        } finally {
            this.cerrarConexion();
        }
    }

    @Override
    public <T> List listar(String id_jefe, String rol) throws Exception {
        List<Administrador> administradores;
        try {
            this.abrirConexion();
            try (PreparedStatement st = this.conexion.prepareStatement("SELECT * FROM PERSONAL_ADMINISTRADOR WHERE id_administrador = ?")) {
                st.setString(1, id_jefe);
                administradores = new ArrayList();
                try (ResultSet rs = st.executeQuery()) {
                    while (rs.next()) {
                        Administrador administrador = (Administrador) extraerObject(rs);
                        administradores.add(administrador);
                    }
                }
            } catch (Exception e) {
                administradores = null;
                System.out.println(e);
            }
        } catch (Exception e) {
            System.out.println(e);
            throw e;
        } finally {
            this.cerrarConexion();
        }
        return administradores;
    }

    @Override
    public Object modificar(Object objeto) throws Exception {
        Administrador administradorM = (Administrador) objeto;
        Administrador administrador = new Administrador();
        try {
            this.abrirConexion();
            try (PreparedStatement st = this.conexion.prepareStatement("SELECT * FROM PERSONAL_ADMINISTRADOR WHERE id_administrador = ?")) {
                st.setString(1, administradorM.getId_administrador());
                try (ResultSet rs = st.executeQuery()) {
                    while (rs.next()) {
                        administrador = (Administrador) extraerObject(rs);
                    }
                } catch (Exception e) {
                    System.out.println(e);
                }
            } catch (Exception e) {
                administrador = null;
                System.out.println(e);
            }
        } catch (Exception e) {
            System.out.println(e);
            throw e;
        } finally {
            this.cerrarConexion();
        }
        return administrador;
    }

    @Override
    public void actualizar(Object objeto) throws Exception {
        Administrador administrador = (Administrador) objeto;
        try {
            this.abrirConexion();
            PreparedStatement st = this.conexion.prepareStatement("UPDATE ADMINISTRADOR SET cuenta_inicial = ? WHERE id_administrador = ?");
            st.setBigDecimal(1, administrador.getCuenta_inicial());
            st.setString(2, administrador.getId_administrador());
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
        Administrador administrador = (Administrador) objeto;
        try {
            this.abrirConexion();
            PreparedStatement st = this.conexion.prepareStatement("DELETE FROM ADMINISTRADOR WHERE id_administrador = ?");
            st.setString(1, administrador.getId_administrador());
            st.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
            throw e;
        } finally {
            this.cerrarConexion();
        }
    }

    public <T> List buscar(String nombre_campo, String dato, String id_jefe, String rol) throws Exception {
        List<Administrador> administradores;
        try {
            this.abrirConexion();
            try (PreparedStatement st = this.conexion.prepareStatement("SELECT * FROM PERSONAL_ADMINISTRADOR WHERE " + nombre_campo + " like ? AND id_administrador = ?")) {
                st.setString(1, "%" + dato + "%");
                st.setString(2, id_jefe);
                administradores = new ArrayList();
                try (ResultSet rs = st.executeQuery()) {
                    while (rs.next()) {
                        Administrador pAdministrador = (Administrador) extraerObject(rs);
                        administradores.add(pAdministrador);
                    }
                }
            }
        } catch (Exception e) {
            System.out.println(e);
            throw e;
        } finally {
            this.cerrarConexion();
        }
        return administradores;
    }

    @Override
    public Object extraerObject(ResultSet rs) throws SQLException {
        Administrador administrador = new Administrador();
        administrador.setId_administrador(rs.getString("id_administrador"));
        administrador.setNombre(rs.getString("nombre"));
        administrador.setCuenta_inicial(rs.getBigDecimal("cuenta_inicial"));
        return administrador;
    }

    @Override
    public PreparedStatement cargarObject(PreparedStatement st, Object objeto) throws SQLException {
        Administrador administrador = (Administrador) objeto;
        st.setString(1, administrador.getId_administrador());
        st.setBigDecimal(2, administrador.getCuenta_inicial());
        return st;
    }

}
