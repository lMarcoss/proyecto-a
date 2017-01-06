package dao.gasto;

import dao.Conexion;
import entidades.gasto.PagoLuz;
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
public class PagoLuzCRUD extends Conexion implements OperacionesCRUD {

    @Override
    public void registrar(Object objeto) throws Exception {
        PagoLuz pagoluz = (PagoLuz) objeto;
        try {
            this.abrirConexion();
            PreparedStatement st = this.conexion.prepareStatement("INSERT INTO PAGO_LUZ (id_pago_luz, fecha, id_empleado, monto, observacion) values (?,?,?,?,?)");
            st = cargarObject(st, pagoluz);
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
        List<PagoLuz> pagosluz;
        try {
            this.abrirConexion();
            try (PreparedStatement st = this.conexion.prepareStatement("SELECT * FROM VISTA_PAGO_LUZ WHERE id_jefe = ? ORDER BY fecha DESC")) {
                st.setString(1, id_jefe);
                pagosluz = new ArrayList<>();
                try (ResultSet rs = st.executeQuery()) {
                    while (rs.next()) {
                        PagoLuz pagoluz = (PagoLuz) extraerObject(rs);
                        pagosluz.add(pagoluz);
                    }
                }
            } catch (Exception e) {
                pagosluz = null;
                System.out.println(e);
            }
        } catch (Exception e) {
            System.out.println(e);
            throw e;
        } finally {
            cerrarConexion();
        }
        return pagosluz;
    }

    @Override
    public Object modificar(Object objeto) throws Exception {
        PagoLuz pagoluzM = (PagoLuz) objeto;
        PagoLuz pagoluz = null;
        this.abrirConexion();
        try (PreparedStatement st = this.conexion.prepareStatement("SELECT * FROM VISTA_PAGO_LUZ WHERE id_pago_luz = ?")) {
            st.setString(1, pagoluzM.getId_pago_luz());
            try (ResultSet rs = st.executeQuery()) {
                while (rs.next()) {
                    pagoluz = (PagoLuz) extraerObject(rs);
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return pagoluz;
    }

    @Override
    public void actualizar(Object objeto) throws Exception {
        PagoLuz pagoluz = (PagoLuz) objeto;
        try {
            this.abrirConexion();
            PreparedStatement st = this.conexion.prepareStatement("UPDATE PAGO_LUZ SET fecha = ?, id_empleado = ?, monto = ?, observacion = ? where id_pago_luz = ?");
            st.setString(1, pagoluz.getFecha());
            st.setString(2, pagoluz.getId_empleado());
            st.setString(3, String.valueOf(pagoluz.getMonto()));
            st.setString(4, pagoluz.getObservacion());
            st.setString(5, pagoluz.getId_pago_luz());
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
        PagoLuz pagoluz = (PagoLuz) objeto;
        try {
            this.abrirConexion();
            PreparedStatement st = this.conexion.prepareStatement("DELETE FROM PAGO_LUZ WHERE id_pago_luz = ?");
            st.setString(1, pagoluz.getId_pago_luz());
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
        List<PagoLuz> pagosluz;
        try {
            this.abrirConexion();
            try (PreparedStatement st = this.conexion.prepareStatement("SELECT * FROM VISTA_PAGO_LUZ WHERE " + nombre_campo + " like ? AND id_jefe = ? ORDER BY fecha DESC")) {
                st.setString(1, "%" + dato + "%");
                st.setString(2, id_jefe);
                pagosluz = new ArrayList<>();
                try (ResultSet rs = st.executeQuery()) {
                    while (rs.next()) {
                        PagoLuz pagoluz = (PagoLuz) extraerObject(rs);
                        pagosluz.add(pagoluz);
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
        return pagosluz;
    }

    @Override
    public Object extraerObject(ResultSet rs) throws SQLException {
        PagoLuz pagoluz = new PagoLuz();
        pagoluz.setId_pago_luz(rs.getString("id_pago_luz"));
        pagoluz.setFecha(rs.getString("fecha"));
        pagoluz.setId_empleado(rs.getString("id_empleado"));
        pagoluz.setEmpleado(rs.getString("empleado"));
        pagoluz.setMonto(rs.getBigDecimal("monto"));
        pagoluz.setObservacion(rs.getString("observacion"));
        return pagoluz;
    }

    @Override
    public PreparedStatement cargarObject(PreparedStatement st, Object objeto) throws SQLException {
        PagoLuz pagoluz = (PagoLuz) objeto;
        st.setString(1, pagoluz.getId_pago_luz());
        st.setString(2, pagoluz.getFecha());
        st.setString(3, pagoluz.getId_empleado());
        st.setString(4, String.valueOf(pagoluz.getMonto()));
        st.setString(5, pagoluz.getObservacion());
        return st;
    }

}
