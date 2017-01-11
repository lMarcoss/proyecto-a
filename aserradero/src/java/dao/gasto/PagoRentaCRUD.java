package dao.gasto;

import dao.Conexion;
import entidades.gasto.PagoRenta;
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
public class PagoRentaCRUD extends Conexion implements OperacionesCRUD {

    @Override
    public void registrar(Object objeto) throws Exception {
        PagoRenta pagorenta = (PagoRenta) objeto;
        try {
            this.abrirConexion();
            PreparedStatement st = this.conexion.prepareStatement(
                    "INSERT INTO PAGO_RENTA (id_pago_renta, fecha, nombre_persona, id_empleado, monto, observacion) VALUES (?,?,?,?,?,?) ");
            st = cargarObject(st, pagorenta);
            st.executeUpdate();
        } catch (Exception e) {
            System.err.println(e);
            throw e;
        } finally {
            this.cerrarConexion();
        }
    }

    @Override
    public <T> List listar(String id_jefe, String rol) throws Exception {
        List<PagoRenta> pagorentas;
        String consulta;
        if (rol.equals("Administrador")) {
            consulta = "SELECT * FROM VISTA_PAGO_RENTA WHERE id_jefe = ? ORDER BY fecha DESC";
        } else {
            consulta = "SELECT * FROM VISTA_PAGO_RENTA WHERE id_jefe = ? AND fecha = CURDATE() ORDER BY fecha DESC";
        }
        try {
            this.abrirConexion();
            try (PreparedStatement st = this.conexion.prepareStatement(consulta)) {
                st.setString(1, id_jefe);
                pagorentas = new ArrayList<>();
                try (ResultSet rs = st.executeQuery()) {
                    while (rs.next()) {
                        PagoRenta pagorenta = (PagoRenta) extraerObject(rs);
                        pagorentas.add(pagorenta);
                    }
                } catch (Exception e) {
                    System.out.println(e);
                }
            } catch (Exception e) {
                pagorentas = null;
                System.out.println(e);
            }
        } catch (Exception e) {
            System.out.println(e);
            throw e;
        } finally {
            cerrarConexion();
        }
        return pagorentas;
    }

    @Override
    public Object modificar(Object objeto) throws Exception {
        PagoRenta pagorentaM = (PagoRenta) objeto;
        PagoRenta pagorenta = null;
        this.abrirConexion();
        try (PreparedStatement st = this.conexion.prepareStatement("SELECT * FROM VISTA_PAGO_RENTA WHERE id_pago_renta = ?")) {
            st.setString(1, pagorentaM.getId_pago_renta());
            try (ResultSet rs = st.executeQuery()) {
                while (rs.next()) {
                    pagorenta = (PagoRenta) extraerObject(rs);
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return pagorenta;
    }

    @Override
    public void actualizar(Object objeto) throws Exception {
        PagoRenta pagorenta = (PagoRenta) objeto;
        try {
            this.abrirConexion();
            PreparedStatement st = this.conexion.prepareStatement("UPDATE PAGO_RENTA SET fecha = ?, nombre_persona = ?, id_empleado = ?, monto = ?, observacion = ? where id_pago_renta = ?");
            st.setString(1, pagorenta.getFecha());
            st.setString(2, pagorenta.getNombre_persona());
            st.setString(3, pagorenta.getId_empleado());
            st.setString(4, String.valueOf(pagorenta.getMonto()));
            st.setString(5, pagorenta.getObservacion());
            st.setString(6, pagorenta.getId_pago_renta());
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
        PagoRenta pagorenta = (PagoRenta) objeto;
        try {
            this.abrirConexion();
            PreparedStatement st = this.conexion.prepareStatement("DELETE FROM PAGO_RENTA WHERE id_pago_renta = ?");
            st.setString(1, pagorenta.getId_pago_renta());
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
        List<PagoRenta> pagorentas;
        String consulta;
        if (rol.equals("Administrador")) {
            consulta = "SELECT * FROM VISTA_PAGO_RENTA WHERE " + nombre_campo + " like ? AND id_jefe = ? ORDER BY fecha DESC";
        } else {
            consulta = "SELECT * FROM VISTA_PAGO_RENTA WHERE " + nombre_campo + " like ? AND id_jefe = ? AND fecha = CURDATE() ORDER BY fecha DESC";
        }
        try {
            this.abrirConexion();
            try (PreparedStatement st = this.conexion.prepareStatement(consulta)) {
                st.setString(1, "%" + dato + "%");
                st.setString(2, id_jefe);
                pagorentas = new ArrayList<>();
                try (ResultSet rs = st.executeQuery()) {
                    while (rs.next()) {
                        PagoRenta pagorenta = (PagoRenta) extraerObject(rs);
                        pagorentas.add(pagorenta);
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
        } catch (Exception e) {
            System.out.println(e);
            throw e;
        }
        return pagorentas;
    }

    @Override
    public Object extraerObject(ResultSet rs) throws SQLException {
        PagoRenta pagorenta = new PagoRenta();
        pagorenta.setId_pago_renta(rs.getString("id_pago_renta"));
        pagorenta.setFecha(rs.getString("fecha"));
        pagorenta.setNombre_persona(rs.getString("nombre_persona"));
        pagorenta.setId_empleado(rs.getString("id_empleado"));
        pagorenta.setEmpleado(rs.getString("empleado"));
        pagorenta.setId_jefe(rs.getString("id_jefe"));
        pagorenta.setMonto(rs.getBigDecimal("monto"));
        pagorenta.setObservacion(rs.getString("observacion"));
        return pagorenta;
    }

    @Override
    public PreparedStatement cargarObject(PreparedStatement st, Object objeto) throws SQLException {
        PagoRenta pagorenta = (PagoRenta) objeto;
        st.setString(1, pagorenta.getId_pago_renta());
        st.setString(2, pagorenta.getFecha());
        st.setString(3, pagorenta.getNombre_persona());
        st.setString(4, pagorenta.getId_empleado());
        st.setString(5, String.valueOf(pagorenta.getMonto()));
        st.setString(6, pagorenta.getObservacion());
        return st;
    }

}
