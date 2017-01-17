package dao.registros;

import dao.Conexion;
import entidades.registros.Proveedor;
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
public class ProveedorCRUD extends Conexion implements OperacionesCRUD {

    @Override
    public void registrar(Object objeto) throws Exception {
        Proveedor proveedor = (Proveedor) objeto;
        try {
            this.abrirConexion();
            PreparedStatement st = this.conexion.prepareStatement(
                    "INSERT INTO PROVEEDOR (id_proveedor,id_persona,id_jefe) VALUES (?,?,?)");
            st = cargarObject(st, proveedor);
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
        Proveedor proveedor = (Proveedor) objeto;
        st.setString(1, proveedor.getId_persona()); //calculamos el id con la clase CalcularId
        st.setString(2, proveedor.getId_persona());
        st.setString(3, proveedor.getId_jefe());
        return st;
    }

    @Override
    public <T> List listar(String id_jefe, String rol) throws Exception {
        List<Proveedor> proveedores;
        try {
            this.abrirConexion();
            try (PreparedStatement st = this.conexion.prepareStatement("SELECT * FROM PERSONAL_PROVEEDOR WHERE id_jefe = ?")) {
                st.setString(1, id_jefe);
                proveedores = new ArrayList();
                try (ResultSet rs = st.executeQuery()) {
                    while (rs.next()) {
                        Proveedor proveedor = (Proveedor) extraerObject(rs);
                        proveedores.add(proveedor);
                    }
                }
            } catch (Exception e) {
                proveedores = null;
                System.out.println(e);
            }
        } catch (Exception e) {
            System.out.println(e);
            throw e;
        } finally {
            this.cerrarConexion();
        }
        return proveedores;
    }

    @Override
    public Object extraerObject(ResultSet rs) throws SQLException {
        Proveedor proveedor = new Proveedor();
        proveedor.setId_proveedor(rs.getString("id_proveedor"));
        proveedor.setId_persona(rs.getString("id_persona"));
        proveedor.setProveedor(rs.getString("proveedor"));
        proveedor.setId_jefe(rs.getString("id_jefe"));
        proveedor.setJefe(rs.getString("jefe"));
        return proveedor;
    }

    @Override
    public Object modificar(Object objeto) throws Exception {
        return null;
    }

    @Override
    public void actualizar(Object objeto) throws Exception {
    }

    @Override
    public void eliminar(Object objeto) throws Exception {
        Proveedor proveedor = (Proveedor) objeto;
        try {
            this.abrirConexion();
            PreparedStatement st = this.conexion.prepareStatement("DELETE FROM PROVEEDOR WHERE id_proveedor = ? AND id_jefe = ?");
            st.setString(1, proveedor.getId_proveedor());
            st.setString(2, proveedor.getId_jefe());
            st.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
            throw e;
        } finally {
            this.cerrarConexion();
        }
    }

    public <T> List buscar(String nombre_campo, String dato, String id_jefe, String rol) throws Exception {
        List<Proveedor> proveedores;
        try {
            this.abrirConexion();
            try (PreparedStatement st = this.conexion.prepareStatement("SELECT * FROM PERSONAL_PROVEEDOR WHERE " + nombre_campo + " like ? AND id_jefe = ?")) {
                st.setString(1, "%" + dato + "%");
                st.setString(2, id_jefe);
                proveedores = new ArrayList();
                try (ResultSet rs = st.executeQuery()) {
                    while (rs.next()) {
                        Proveedor proveedor = (Proveedor) extraerObject(rs);
                        proveedores.add(proveedor);
                    }
                }
            }
        } catch (Exception e) {
            System.out.println(e);
            throw e;
        } finally {
            this.cerrarConexion();
        }
        return proveedores;
    }
    
}
