package dao.registros.bienesInmuebles;

import dao.Conexion;
import entidades.registros.bienesInmuebles.Terreno;
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
public class TerrenoCRUD extends Conexion implements OperacionesCRUD {

    @Override
    public void registrar(Object objeto) throws Exception {
        Terreno terreno = (Terreno) objeto;
        try {
            this.abrirConexion();
            PreparedStatement st = this.conexion.prepareStatement(
                    "INSERT INTO TERRENO (id_terreno,nombre,dimension,direccion,nombre_localidad,nombre_municipio,estado,valor_estimado,id_empleado) values (?,?,?,?,?,?,?,?,?)");
            st = cargarObject(st, terreno);
            st.executeUpdate();
        } catch (Exception e) {
            System.err.println(e);
            throw e;
        } finally {
            this.cerrarConexion();
        }
    }

    @Override
    public <T> List listar(String id_jefe) throws Exception {
        List<Terreno> terrenos;
        try {
            this.abrirConexion();
            try (PreparedStatement st = this.conexion.prepareStatement("SELECT * FROM VISTA_TERRENO WHERE id_jefe = ?")) {
                st.setString(1, id_jefe);
                terrenos = new ArrayList<>();
                try (ResultSet rs = st.executeQuery()) {
                    while (rs.next()) {
                        Terreno terreno = (Terreno) extraerObject(rs);
                        terrenos.add(terreno);
                    }
                } catch (Exception e) {
                    System.out.println(e);
                }
            } catch (Exception e) {
                terrenos = null;
                System.out.println(e);
            }
        } catch (Exception e) {
            System.out.println(e);
            throw e;
        } finally {
            cerrarConexion();
        }
        return terrenos;
    }

    @Override
    public Object modificar(Object objeto) throws Exception {
        Terreno terrenoM = (Terreno) objeto;
        Terreno terreno = null;
        this.abrirConexion();
        try (PreparedStatement st = this.conexion.prepareStatement("SELECT * FROM VISTA_TERRENO WHERE id_terreno = ?")) {
            st.setInt(1, terrenoM.getId_terreno());
            try (ResultSet rs = st.executeQuery()) {
                while (rs.next()) {
                    terreno = (Terreno) extraerObject(rs);
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return terreno;
    }

    @Override
    public void actualizar(Object objeto) throws Exception {
        Terreno terreno = (Terreno) objeto;
        try {
            this.abrirConexion();
            PreparedStatement st = this.conexion.prepareStatement("UPDATE TERRENO SET nombre = ?, dimension = ?, direccion = ?, nombre_localidad = ?, nombre_municipio = ?, estado = ?, valor_estimado = ? WHERE id_terreno = ?");
            st.setString(1, terreno.getNombre());
            st.setString(2, terreno.getDimension());
            st.setString(3, terreno.getDireccion());
            st.setString(4, terreno.getNombre_localidad());
            st.setString(5, terreno.getNombre_municipio());
            st.setString(6, terreno.getEstado());
            st.setBigDecimal(7, terreno.getValor_estimado());
            st.setInt(8, terreno.getId_terreno());
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
        Terreno terreno = (Terreno) objeto;
        try {
            this.abrirConexion();
            PreparedStatement st = this.conexion.prepareStatement("DELETE FROM TERRENO WHERE id_terreno = ?");
            st.setInt(1, terreno.getId_terreno());
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
        List<Terreno> terrenos;
        try {
            this.abrirConexion();
            try (PreparedStatement st = this.conexion.prepareStatement("SELECT * FROM VISTA_TERRENO WHERE " + nombre_campo + " like ? AND id_jefe = ?")) {
                st.setString(1, "%" + dato + "%");
                st.setString(2, id_jefe);
                terrenos = new ArrayList<>();
                try (ResultSet rs = st.executeQuery()) {
                    while (rs.next()) {
                        Terreno terreno = (Terreno) extraerObject(rs);
                        terrenos.add(terreno);
                    }
                }
            }
        } catch (Exception e) {
            System.out.println(e);
            throw e;
        } finally {
            this.cerrarConexion();
        }
        return terrenos;
    }

    @Override
    public Object extraerObject(ResultSet rs) throws SQLException {
        Terreno terreno = new Terreno();
        terreno.setId_terreno(rs.getInt("id_terreno"));
        terreno.setNombre(rs.getString("nombre"));
        terreno.setDimension(rs.getString("dimension"));
        terreno.setDireccion(rs.getString("direccion"));
        terreno.setNombre_localidad(rs.getString("nombre_localidad"));
        terreno.setNombre_municipio(rs.getString("nombre_municipio"));
        terreno.setEstado(rs.getString("estado"));
        terreno.setValor_estimado(rs.getBigDecimal("valor_estimado"));
        terreno.setId_empleado(rs.getString("id_empleado"));
        terreno.setEmpleado(rs.getString("empleado"));
        terreno.setId_jefe(rs.getString("id_jefe"));
        return terreno;
    }
        
    @Override
    public PreparedStatement cargarObject(PreparedStatement st, Object objeto) throws SQLException {
        Terreno terreno = (Terreno) objeto;
        st.setInt(1, terreno.getId_terreno());
        st.setString(2, terreno.getNombre());
        st.setString(3, terreno.getDimension());
        st.setString(4, terreno.getDireccion());
        st.setString(5, terreno.getNombre_localidad());
        st.setString(6, terreno.getNombre_municipio());
        st.setString(7, terreno.getEstado());
        st.setBigDecimal(8, terreno.getValor_estimado());
        st.setString(9, terreno.getId_empleado());
        return st;
    }
}
