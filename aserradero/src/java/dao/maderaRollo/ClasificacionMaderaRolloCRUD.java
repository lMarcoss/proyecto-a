package dao.maderaRollo;

import dao.Conexion;
import entidades.maderaRollo.ClasificacionMaderaRollo;
import interfaces.OperacionesCRUD;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author rcortes
 */
public class ClasificacionMaderaRolloCRUD extends Conexion implements OperacionesCRUD {

    @Override
    public void registrar(Object objeto) throws Exception {
        ClasificacionMaderaRollo costoMaderaEntrada = (ClasificacionMaderaRollo) objeto;
        try {
            this.abrirConexion();
            PreparedStatement st = this.conexion.prepareStatement(
                    "INSERT INTO CLASIFICACION_M_ROLLO (id_proveedor,clasificacion,costo) values(?,?,?)");
            st = cargarObject(st, costoMaderaEntrada);
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
        ClasificacionMaderaRollo costoMaderaEntrada = (ClasificacionMaderaRollo) objeto;
        st.setString(1, costoMaderaEntrada.getId_proveedor());
        st.setString(2, costoMaderaEntrada.getClasificacion());
        st.setString(3, String.valueOf(costoMaderaEntrada.getCosto()));
        return st;
    }

    @Override
    public <T> List listar(String id_jefe) throws Exception {
        List<ClasificacionMaderaRollo> costoMaderaEntradas;
        try {
            this.abrirConexion();
            try (PreparedStatement st = this.conexion.prepareStatement("SELECT * FROM V_CLASIFICACION_M_ROLLO WHERE id_jefe = ? ORDER BY proveedor, clasificacion")) {
                st.setString(1, id_jefe);
                costoMaderaEntradas = new ArrayList<>();
                try (ResultSet rs = st.executeQuery()) {
                    while (rs.next()) {
                        ClasificacionMaderaRollo costoMaderaEntrada = (ClasificacionMaderaRollo) extraerObject(rs);
                        costoMaderaEntradas.add(costoMaderaEntrada);
                    }
                } catch (Exception e) {
                }
            } catch (Exception e) {
                costoMaderaEntradas = null;
                System.out.println(e);
            }
        } catch (Exception e) {
            System.out.println(e);
            throw e;
        } finally {
            cerrarConexion();
        }
        return costoMaderaEntradas;
    }

    @Override
    public Object extraerObject(ResultSet rs) throws SQLException {
        ClasificacionMaderaRollo costoMaderaEntrada = new ClasificacionMaderaRollo();
        costoMaderaEntrada.setId_proveedor(rs.getString("id_proveedor"));
        costoMaderaEntrada.setProveedor(rs.getString("proveedor"));
        costoMaderaEntrada.setId_jefe(rs.getString("id_jefe"));
        costoMaderaEntrada.setClasificacion(rs.getString("clasificacion"));
        costoMaderaEntrada.setCosto(rs.getBigDecimal("costo"));
        return costoMaderaEntrada;
    }

    @Override
    public Object modificar(Object objeto) throws Exception {
        ClasificacionMaderaRollo costoMaderaEntradaM = (ClasificacionMaderaRollo) objeto;
        ClasificacionMaderaRollo costoMaderaEntrada = null;
        this.abrirConexion();
        try (PreparedStatement st = this.conexion.prepareStatement("SELECT * FROM V_CLASIFICACION_M_ROLLO WHERE id_proveedor = ? AND clasificacion = ?")) {
            st.setString(1, costoMaderaEntradaM.getId_proveedor());
            st.setString(2, costoMaderaEntradaM.getClasificacion());
            try (ResultSet rs = st.executeQuery()) {
                while (rs.next()) {
                    costoMaderaEntrada = (ClasificacionMaderaRollo) extraerObject(rs);
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return costoMaderaEntrada;
    }

    @Override
    public void actualizar(Object objeto) throws Exception {
        ClasificacionMaderaRollo costoMaderaEntrada = (ClasificacionMaderaRollo) objeto;
        try {
            this.abrirConexion();
            PreparedStatement st = this.conexion.prepareStatement("UPDATE CLASIFICACION_M_ROLLO SET costo = ? where id_proveedor = ? AND clasificacion=?");
            st.setBigDecimal(1, costoMaderaEntrada.getCosto());
            st.setString(2, costoMaderaEntrada.getId_proveedor());
            st.setString(3, costoMaderaEntrada.getClasificacion());
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
        ClasificacionMaderaRollo costoMaderaEntrada = (ClasificacionMaderaRollo) objeto;
        try {
            this.abrirConexion();
            PreparedStatement st = this.conexion.prepareStatement("DELETE FROM CLASIFICACION_M_ROLLO WHERE id_proveedor = ?  AND clasificacion = ?");
            st.setString(1, costoMaderaEntrada.getId_proveedor());
            st.setString(2, costoMaderaEntrada.getClasificacion());
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
        List<ClasificacionMaderaRollo> costoMaderaEntradas;
        try {
            this.abrirConexion();
            try (PreparedStatement st = this.conexion.prepareStatement("SELECT * FROM V_CLASIFICACION_M_ROLLO WHERE " + nombre_campo + " like ? AND id_jefe = ? ORDER BY proveedor, clasificacion")) {
                st.setString(1, "%" + dato + "%");
                st.setString(2, id_jefe);
                costoMaderaEntradas = new ArrayList<>();
                try (ResultSet rs = st.executeQuery()) {
                    while (rs.next()) {
                        ClasificacionMaderaRollo costoMaderaEntrada = (ClasificacionMaderaRollo) extraerObject(rs);
                        costoMaderaEntradas.add(costoMaderaEntrada);
                    }
                }
            }
        } catch (Exception e) {
            System.out.println(e);
            throw e;
        } finally {
            this.cerrarConexion();
        }
        return costoMaderaEntradas;
    }

}
