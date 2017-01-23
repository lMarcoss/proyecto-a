package dao.registros;

import dao.Conexion;
import entidades.registros.Localidad;
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
public class LocalidadCRUD extends Conexion implements OperacionesCRUD {

    @Override
    public void registrar(Object objeto) throws Exception {
        Localidad localidad = (Localidad) objeto;
        try {
            this.abrirConexion();
            PreparedStatement st = this.conexion.prepareStatement("INSERT INTO LOCALIDAD (nombre_localidad,nombre_municipio,estado,telefono) VALUES (?,?,?,?)");
            st = cargarObject(st, localidad);
            st.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
            throw e;
        } finally {
            this.cerrarConexion();
        }
    }

    @Override
    public PreparedStatement cargarObject(PreparedStatement st, Object objecto) throws SQLException {
        Localidad localidad = (Localidad) objecto;
        st.setString(1, localidad.getNombre_localidad());
        st.setString(2, localidad.getNombre_municipio());
        st.setString(3, localidad.getEstado());
        st.setString(4, localidad.getTelefono_localidad());
        return st;
    }

    @Override
    public <T> List listar(String id_jefe, String rol) throws Exception {
        List<Localidad> localidades;
        try {
            this.abrirConexion();
            try (PreparedStatement st = this.conexion.prepareStatement("SELECT * FROM VISTA_LOCALIDAD")) {
                localidades = new ArrayList();
                try (ResultSet rs = st.executeQuery()) {
                    while (rs.next()) {
                        Localidad localidad = (Localidad) extraerObject(rs);
                        localidades.add(localidad);
                    }
                }
            } catch (Exception e) {
                localidades = null;
                System.out.println(e);
            }
        } catch (Exception e) {
            System.out.println(e);
            throw e;
        } finally {
            this.cerrarConexion();
        }
        return localidades;
    }

    @Override
    public Object extraerObject(ResultSet rs) throws SQLException {
        Localidad localidad = new Localidad();
        localidad.setNombre_localidad(rs.getString("nombre_localidad"));
        localidad.setNombre_municipio(rs.getString("nombre_municipio"));
        localidad.setTelefono_localidad(rs.getString("telefono_localidad"));
        localidad.setEstado(rs.getString("estado"));
        return localidad;
    }

    @Override
    public Object modificar(Object objeto) throws Exception {
        Localidad localidadM = (Localidad) objeto;
        Localidad localidad = null;
        this.abrirConexion();
        try (PreparedStatement st = this.conexion.prepareStatement("SELECT * FROM VISTA_LOCALIDAD WHERE nombre_localidad = ?")) {
            st.setString(1, localidadM.getNombre_localidad());
            try (ResultSet rs = st.executeQuery()) {
                while (rs.next()) {
                    localidad = (Localidad) extraerObject(rs);
                }
            }
        }
        return localidad;
    }

    @Override
    public void actualizar(Object objeto) throws Exception {
        Localidad localidad = (Localidad) objeto;
        try {
            this.abrirConexion();
            PreparedStatement st = this.conexion.prepareStatement("UPDATE LOCALIDAD SET nombre_municipio = ?, telefono = ? WHERE nombre_localidad = ?");
            st.setString(1, localidad.getNombre_municipio());
            st.setString(2, localidad.getTelefono_localidad());
            st.setString(3, localidad.getNombre_localidad());
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
        Localidad localidad = (Localidad) objeto;
        try {
            this.abrirConexion();
            PreparedStatement st = this.conexion.prepareStatement("DELETE FROM LOCALIDAD WHERE nombre_localidad = ?");
            st.setString(1, localidad.getNombre_localidad());
            st.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
            throw e;
        } finally {
            this.cerrarConexion();
        }
    }

    public <T> List buscar(String nombre_campo, String dato, String id_jefe, String rol) throws Exception {
        List<Localidad> localidades;
        try {
            this.abrirConexion();
            try (PreparedStatement st = this.conexion.prepareStatement("SELECT * FROM VISTA_LOCALIDAD WHERE " + nombre_campo + " like ?")) {
                st.setString(1, "%" + dato + "%");
                localidades = new ArrayList();
                try (ResultSet rs = st.executeQuery()) {
                    while (rs.next()) {
                        Localidad localidad = (Localidad) extraerObject(rs);
                        localidades.add(localidad);
                    }
                }
            }
        } catch (Exception e) {
            System.out.println(e);
            throw e;
        } finally {
            this.cerrarConexion();
        }
        return localidades;
    }

    public List<Localidad> buscarLocalidad(String nombre_localidad, String nombre_municipio, String estado) throws Exception {
        List<Localidad> localidades = null;
        try {
            this.abrirConexion();
            try (PreparedStatement st = this.conexion.prepareStatement("SELECT * FROM VISTA_LOCALIDAD WHERE nombre_localidad = ? AND nombre_municipio = ? AND estado = ?")) {
                st.setString(1, nombre_localidad);
                st.setString(2, nombre_municipio);
                st.setString(3, estado);
                localidades = new ArrayList();
                try (ResultSet rs = st.executeQuery()) {
                    while (rs.next()) {
                        Localidad localidad = (Localidad) extraerObject(rs);
                        localidades.add(localidad);
                    }
                }
            }
        } catch (Exception e) {
            System.out.println(e);
            throw e;
        } finally {
            this.cerrarConexion();
        }
        return localidades;
    }

    public boolean existeLocalidad(String nombre_localidad, String nombre_municipio, String estado) throws Exception {
        boolean existe = false;
        try {
            this.abrirConexion();
            try (PreparedStatement st = this.conexion.prepareStatement("SELECT * FROM LOCALIDAD WHERE nombre_localidad = ? AND nombre_municipio = ? AND estado = ?")) {
                st.setString(1, nombre_localidad);
                st.setString(2, nombre_municipio);
                st.setString(3, estado);
                try (ResultSet rs = st.executeQuery()) {
                    while (rs.next()) {
                        existe = true;
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
}
