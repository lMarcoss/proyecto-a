package dao.anticipo;

import dao.Conexion;
import entidades.anticipo.AnticipoProveedor;
import interfaces.OperacionesCRUD;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Marcos
 */
public class AnticipoProveedorCRUD extends Conexion implements OperacionesCRUD {

    @Override
    public void registrar(Object objeto) throws Exception {
        AnticipoProveedor anticipoProveedor = (AnticipoProveedor) objeto;
        try {
            this.abrirConexion();
            PreparedStatement st = this.conexion.prepareStatement(
                    "INSERT INTO ANTICIPO_PROVEEDOR (fecha,id_proveedor,id_empleado,monto_anticipo) VALUES (?,?,?,?)");
            st = cargarObject(st, anticipoProveedor);
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
        List<AnticipoProveedor> anticipoProveedores;
        String consulta;
        if (rol.equals("Administrador")) {
            consulta = "SELECT * FROM VISTA_ANTICIPO_PROVEEDOR WHERE id_jefe = ? ORDER BY fecha DESC";
        } else {
            consulta = "SELECT * FROM VISTA_ANTICIPO_PROVEEDOR WHERE id_jefe = ? AND fecha = CURDATE() ORDER BY fecha DESC";
        }
        try {
            this.abrirConexion();
            try (PreparedStatement st = this.conexion.prepareStatement(consulta)) {
                st.setString(1, id_jefe);
                anticipoProveedores = new ArrayList();
                try (ResultSet rs = st.executeQuery()) {
                    while (rs.next()) {
                        AnticipoProveedor anticipoProveedor = (AnticipoProveedor) extraerObject(rs);
                        anticipoProveedores.add(anticipoProveedor);
                    }
                }
            } catch (Exception e) {
                anticipoProveedores = null;
                System.out.println(e);
            }
        } catch (Exception e) {
            System.out.println(e);
            throw e;
        } finally {
            this.cerrarConexion();
        }
        return anticipoProveedores;
    }

    @Override
    public Object modificar(Object objeto) throws Exception {
        AnticipoProveedor anticipoProveedorM = (AnticipoProveedor) objeto;
        AnticipoProveedor anticipoProveedor = null;
        this.abrirConexion();
        try (PreparedStatement st = this.conexion.prepareStatement("SELECT * FROM VISTA_ANTICIPO_PROVEEDOR WHERE id_anticipo_p=?")) {
            st.setInt(1, anticipoProveedorM.getId_anticipo_p());

            try (ResultSet rs = st.executeQuery()) {
                while (rs.next()) {
                    anticipoProveedor = (AnticipoProveedor) extraerObject(rs);
                }
            }
        }
        return anticipoProveedor;
    }

    @Override
    public void actualizar(Object objeto) throws Exception {
        AnticipoProveedor anticipoProveedor = (AnticipoProveedor) objeto;
        try {
            this.abrirConexion();
            PreparedStatement st = this.conexion.prepareStatement("UPDATE ANTICIPO_PROVEEDOR SET monto_anticipo = ? WHERE id_anticipo_p=?");
            st.setBigDecimal(1, anticipoProveedor.getMonto_anticipo());
            st.setInt(2, anticipoProveedor.getId_anticipo_p());
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
        AnticipoProveedor anticipoProveedor = (AnticipoProveedor) objeto;
        try {
            this.abrirConexion();
            PreparedStatement st = this.conexion.prepareStatement("DELETE FROM ANTICIPO_PROVEEDOR WHERE id_anticipo_p = ?");
            st.setInt(1, anticipoProveedor.getId_anticipo_p());
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
        List<AnticipoProveedor> anticipoProveedores;
        String consulta;
        if (rol.equals("Administrador")) {
            consulta = "SELECT * FROM VISTA_ANTICIPO_PROVEEDOR WHERE " + nombre_campo + " like ? AND id_jefe = ? ORDER BY fecha DESC";
        } else {
            consulta = "SELECT * FROM VISTA_ANTICIPO_PROVEEDOR WHERE " + nombre_campo + " like ? AND id_jefe = ? AND fecha = CURDATE() ORDER BY fecha DESC";
        }
        try {
            this.abrirConexion();
            try (PreparedStatement st = this.conexion.prepareStatement(consulta)) {
                st.setString(1, "%" + dato + "%");
                st.setString(2, id_jefe);
                anticipoProveedores = new ArrayList();
                try (ResultSet rs = st.executeQuery()) {
                    while (rs.next()) {
                        AnticipoProveedor anticipoProveedor = (AnticipoProveedor) extraerObject(rs);
                        anticipoProveedores.add(anticipoProveedor);
                    }
                }
            }
        } catch (Exception e) {
            System.out.println(e);
            throw e;
        } finally {
            this.cerrarConexion();
        }
        return anticipoProveedores;
    }

    @Override
    public Object extraerObject(ResultSet rs) throws SQLException {
        AnticipoProveedor anticipoProveedor = new AnticipoProveedor();
        anticipoProveedor.setId_anticipo_p(rs.getInt("id_anticipo_p"));
        anticipoProveedor.setFecha(rs.getDate("fecha"));
        anticipoProveedor.setId_proveedor(rs.getString("id_proveedor"));
        anticipoProveedor.setProveedor(rs.getString("proveedor"));
        anticipoProveedor.setId_empleado(rs.getString("id_empleado"));
        anticipoProveedor.setEmpleado(rs.getString("empleado"));
        anticipoProveedor.setMonto_anticipo(rs.getBigDecimal("monto_anticipo"));

        return anticipoProveedor;
    }

    @Override
    public PreparedStatement cargarObject(PreparedStatement st, Object objeto) throws SQLException {
        AnticipoProveedor anticipoProveedor = (AnticipoProveedor) objeto;
        st.setDate(1, anticipoProveedor.getFecha());
        st.setString(2, anticipoProveedor.getId_proveedor());
        st.setString(3, anticipoProveedor.getId_empleado());
        st.setBigDecimal(4, anticipoProveedor.getMonto_anticipo());
        return st;
    }

}
