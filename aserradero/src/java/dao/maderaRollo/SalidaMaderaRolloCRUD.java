package dao.maderaRollo;

import dao.Conexion;
import entidades.maderaRollo.SalidaMaderaRollo;
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
public class SalidaMaderaRolloCRUD extends Conexion implements OperacionesCRUD {

    @Override
    public void registrar(Object objeto) throws Exception {
        SalidaMaderaRollo salida = (SalidaMaderaRollo) objeto;
        try {
            this.abrirConexion();
            PreparedStatement st = this.conexion.prepareStatement(
                    "INSERT INTO SALIDA_M_ROLLO (fecha,id_empleado,num_pieza_primario,volumen_primario,num_pieza_secundario,volumen_secundario,num_pieza_terciario,volumen_terciario) values(?,?,?,?,?,?,?,?)");
            st = cargarObject(st, salida);
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
        SalidaMaderaRollo salida = (SalidaMaderaRollo) objeto;
        st.setDate(1, salida.getFecha());
        st.setString(2, salida.getId_empleado());
        st.setInt(3, salida.getNum_pieza_primario());
        st.setBigDecimal(4, salida.getVolumen_primario());
        st.setInt(5, salida.getNum_pieza_secundario());
        st.setBigDecimal(6, salida.getVolumen_secundario());
        st.setInt(7, salida.getNum_pieza_terciario());
        st.setBigDecimal(8, salida.getVolumen_terciario());
        return st;
    }

    @Override
    public <T> List listar(String id_jefe, String rol) throws Exception {
        List<SalidaMaderaRollo> salidas = null;
        String consulta;
        if (rol.equals("Administrador")) {
            consulta = "SELECT * FROM VISTA_SALIDA_M_ROLLO WHERE id_jefe = ? ORDER BY fecha DESC";
        } else {
            consulta = "SELECT * FROM VISTA_SALIDA_M_ROLLO WHERE id_jefe = ? AND fecha = CURDATE() ORDER BY fecha DESC";
        }
        try {
            this.abrirConexion();
            try (PreparedStatement st = this.conexion.prepareStatement(consulta)) {
                st.setString(1, id_jefe);
                salidas = new ArrayList<>();
                try (ResultSet rs = st.executeQuery()) {
                    while (rs.next()) {
                        SalidaMaderaRollo salida = (SalidaMaderaRollo) extraerObject(rs);
                        salidas.add(salida);
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
            cerrarConexion();
        }

        return salidas;
    }

    @Override
    public Object extraerObject(ResultSet rs) throws SQLException {
        SalidaMaderaRollo salida = new SalidaMaderaRollo();
        salida.setId_salida(rs.getInt("id_salida"));
        salida.setFecha(rs.getDate("fecha"));
        salida.setId_empleado(rs.getString("id_empleado"));
        salida.setEmpleado(rs.getString("empleado"));
        salida.setId_jefe(rs.getString("id_jefe"));
        salida.setNum_pieza_primario(rs.getInt("num_pieza_primario"));
        salida.setVolumen_primario(rs.getBigDecimal("volumen_primario"));
        salida.setNum_pieza_secundario(rs.getInt("num_pieza_secundario"));
        salida.setVolumen_secundario(rs.getBigDecimal("volumen_secundario"));
        salida.setNum_pieza_terciario(rs.getInt("num_pieza_terciario"));
        salida.setVolumen_terciario(rs.getBigDecimal("volumen_terciario"));
        salida.setNum_pieza_total(rs.getInt("num_pieza_total"));
        salida.setVolumen_total(rs.getBigDecimal("volumen_total"));
        return salida;
    }

    @Override
    public Object modificar(Object objeto) throws Exception {
        SalidaMaderaRollo salidaM = (SalidaMaderaRollo) objeto;
        SalidaMaderaRollo salida = null;
        this.abrirConexion();
        try (PreparedStatement st = this.conexion.prepareStatement("SELECT * FROM VISTA_SALIDA_M_ROLLO WHERE id_salida = ?")) {
            st.setInt(1, salidaM.getId_salida());
            try (ResultSet rs = st.executeQuery()) {
                while (rs.next()) {
                    salida = (SalidaMaderaRollo) extraerObject(rs);
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return salida;
    }

    @Override
    public void actualizar(Object objeto) throws Exception {
        SalidaMaderaRollo salida = (SalidaMaderaRollo) objeto;
        try {
            this.abrirConexion();
            PreparedStatement st = this.conexion.prepareStatement("UPDATE SALIDA_M_ROLLO SET fecha = ?, num_pieza_primario = ?, volumen_primario = ?, num_pieza_secundario = ?, volumen_secundario = ?, num_pieza_terciario = ?, volumen_terciario = ? WHERE id_salida = ?");
            st.setDate(1, salida.getFecha());
            st.setInt(2, salida.getNum_pieza_primario());
            st.setBigDecimal(3, salida.getVolumen_primario());
            st.setInt(4, salida.getNum_pieza_secundario());
            st.setBigDecimal(5, salida.getVolumen_secundario());
            st.setInt(6, salida.getNum_pieza_terciario());
            st.setBigDecimal(7, salida.getVolumen_terciario());
            st.setInt(8, salida.getId_salida());
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
    }

    public <T> List buscar(String nombre_campo, String dato, String id_jefe, String rol) throws Exception {
        List<SalidaMaderaRollo> salidas;
        String consulta;
        if (rol.equals("Administrador")) {
            consulta = "SELECT * FROM VISTA_SALIDA_M_ROLLO WHERE ? like ? AND id_jefe = ? ORDER BY fecha DESC";
        } else {
            consulta = "SELECT * FROM VISTA_SALIDA_M_ROLLO WHERE ? like ? AND id_jefe = ? AND fecha = CURDATE() ORDER BY fecha DESC";
        }
        try {
            this.abrirConexion();
            try (PreparedStatement st = this.conexion.prepareStatement(consulta)) {
                st.setString(1, nombre_campo);
                st.setString(2, "%" + dato + "%");
                st.setString(3, id_jefe);
                salidas = new ArrayList<>();
                try (ResultSet rs = st.executeQuery()) {
                    while (rs.next()) {
                        SalidaMaderaRollo salida = (SalidaMaderaRollo) extraerObject(rs);
                        salidas.add(salida);
                    }
                } catch (Exception e) {
                    System.out.println(e);
                    throw e;
                }
            } catch (Exception e) {
                System.out.println(e);
                throw e;
            }
        } catch (Exception e) {
            System.out.println(e);
            throw e;
        } finally {
            this.cerrarConexion();
        }
        return salidas;
    }

}
