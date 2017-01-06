package dao.maderaAserrada;

import dao.Conexion;
import entidades.maderaAserrada.MaderaAserradaClasif;
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
public class MaderaAserradaClasifCRUD extends Conexion implements OperacionesCRUD {

    @Override
    public void registrar(Object objeto) throws Exception {
        MaderaAserradaClasif maderaClasificacion = (MaderaAserradaClasif) objeto;
        try {
            this.abrirConexion();
            PreparedStatement st = this.conexion.prepareStatement(
                    "INSERT INTO MADERA_ASERRADA_CLASIF (id_administrador,id_empleado,id_madera,grueso,grueso_f,ancho,ancho_f,largo,largo_f,volumen,costo_por_volumen) VALUES (?,?,?,?,?,?,?,?,?,?,?)");
            st = cargarObject(st, maderaClasificacion);
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
        MaderaAserradaClasif maderaClasificacion = (MaderaAserradaClasif) objeto;
        st.setString(1, maderaClasificacion.getId_administrador());
        st.setString(2, maderaClasificacion.getId_empleado());
        st.setString(3, maderaClasificacion.getId_madera());
        st.setBigDecimal(4, maderaClasificacion.getGrueso());
        st.setString(5, maderaClasificacion.getGrueso_f());
        st.setBigDecimal(6, maderaClasificacion.getAncho());
        st.setString(7, maderaClasificacion.getAncho_f());
        st.setBigDecimal(8, maderaClasificacion.getLargo());
        st.setString(9, maderaClasificacion.getLargo_f());
        st.setBigDecimal(10, maderaClasificacion.getVolumen());
        st.setBigDecimal(11, maderaClasificacion.getCosto_por_volumen());
        return st;
    }

    @Override
    public <T> List listar(String id_jefe) throws Exception {
        List<MaderaAserradaClasif> maderaClasificaciones;
        try {
            this.abrirConexion();
            try (PreparedStatement st = this.conexion.prepareStatement("SELECT * FROM V_MADERA_ASERRADA_CLASIF WHERE id_administrador = ? ORDER BY id_madera")) {
                st.setString(1, id_jefe);
                maderaClasificaciones = new ArrayList();
                try (ResultSet rs = st.executeQuery()) {
                    while (rs.next()) {
                        MaderaAserradaClasif maderaClasificacion = (MaderaAserradaClasif) extraerObject(rs);
                        maderaClasificaciones.add(maderaClasificacion);
                    }
                } catch (Exception e) {
                    System.out.println(e);
                }
            } catch (Exception e) {
                maderaClasificaciones = null;
                System.out.println(e);
            }
        } catch (Exception e) {
            System.out.println(e);
            throw e;
        } finally {
            this.cerrarConexion();
        }
        return maderaClasificaciones;
    }

    @Override
    public Object extraerObject(ResultSet rs) throws SQLException {
        MaderaAserradaClasif maderaClasificacion = new MaderaAserradaClasif();
        maderaClasificacion.setId_empleado(rs.getString("id_empleado"));
        maderaClasificacion.setEmpleado(rs.getString("empleado"));
        maderaClasificacion.setId_madera(rs.getString("id_madera"));
        maderaClasificacion.setGrueso(rs.getBigDecimal("grueso"));
        maderaClasificacion.setGrueso_f(rs.getString("grueso_f"));
        maderaClasificacion.setAncho(rs.getBigDecimal("ancho"));
        maderaClasificacion.setAncho_f(rs.getString("ancho_f"));
        maderaClasificacion.setLargo(rs.getBigDecimal("largo"));
        maderaClasificacion.setLargo_f(rs.getString("largo_f"));
        maderaClasificacion.setVolumen(rs.getBigDecimal("volumen"));
        maderaClasificacion.setCosto_por_volumen(rs.getBigDecimal("costo_por_volumen"));
        return maderaClasificacion;
    }

    @Override
    public Object modificar(Object objeto) throws Exception {
        MaderaAserradaClasif maderaClasificacionM = (MaderaAserradaClasif) objeto;
        MaderaAserradaClasif maderaClasificacion = null;
        this.abrirConexion();
        try (PreparedStatement st = this.conexion.prepareStatement("SELECT * FROM V_MADERA_ASERRADA_CLASIF WHERE id_administrador = ? AND id_madera = ?")) {
            st.setString(1, maderaClasificacionM.getId_administrador());
            st.setString(2, maderaClasificacionM.getId_madera());
            try (ResultSet rs = st.executeQuery()) {
                while (rs.next()) {
                    maderaClasificacion = (MaderaAserradaClasif) extraerObject(rs);
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return maderaClasificacion;
    }

    @Override
    public void actualizar(Object objeto) throws Exception {
        MaderaAserradaClasif maderaClasificacion = (MaderaAserradaClasif) objeto;
        try {
            this.abrirConexion();
            PreparedStatement st = this.conexion.prepareStatement("UPDATE MADERA_ASERRADA_CLASIF SET grueso = ?, grueso_f = ?, ancho = ?, ancho_f = ?, largo = ?, largo_f = ?, volumen = ?, costo_por_volumen = ? WHERE id_administrador = ? AND id_madera= ?");
            st.setBigDecimal(1, maderaClasificacion.getGrueso());
            st.setString(2, maderaClasificacion.getGrueso_f());
            st.setBigDecimal(3, maderaClasificacion.getAncho());
            st.setString(4, maderaClasificacion.getAncho_f());
            st.setBigDecimal(5, maderaClasificacion.getLargo());
            st.setString(6, maderaClasificacion.getLargo_f());
            st.setBigDecimal(7, maderaClasificacion.getVolumen());
            st.setBigDecimal(8, maderaClasificacion.getCosto_por_volumen());
            st.setString(9, maderaClasificacion.getId_administrador());
            st.setString(10, maderaClasificacion.getId_madera());
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
        MaderaAserradaClasif maderaClasificacion = (MaderaAserradaClasif) objeto;
        try {
            this.abrirConexion();
            PreparedStatement st = this.conexion.prepareStatement("DELETE FROM MADERA_ASERRADA_CLASIF WHERE id_administrador = ? AND id_madera = ?");
            st.setString(1, maderaClasificacion.getId_administrador());
            st.setString(2, maderaClasificacion.getId_madera());
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
        List<MaderaAserradaClasif> maderaClasificaciones = null;
        try {
            this.abrirConexion();
            try (PreparedStatement st = this.conexion.prepareStatement("SELECT * FROM V_MADERA_ASERRADA_CLASIF WHERE " + nombre_campo + " like ? AND id_administrador = ?")) {
                st.setString(1, "%" + dato + "%");
                st.setString(2, id_jefe);
                maderaClasificaciones = new ArrayList();
                try (ResultSet rs = st.executeQuery()) {
                    while (rs.next()) {
                        MaderaAserradaClasif maderaClasificacion = (MaderaAserradaClasif) extraerObject(rs);
                        maderaClasificaciones.add(maderaClasificacion);
                    }
                } catch (Exception e) {
                    System.out.println(e);
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
        return maderaClasificaciones;
    }

}
