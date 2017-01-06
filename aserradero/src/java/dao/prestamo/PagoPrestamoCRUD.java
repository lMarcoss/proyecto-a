package dao.prestamo;

import dao.Conexion;
import entidades.prestamo.PagoPrestamo;
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
public class PagoPrestamoCRUD extends Conexion implements  OperacionesCRUD{

    @Override
    public void registrar(Object objeto) throws Exception {
        PagoPrestamo pagoPrestamo = (PagoPrestamo) objeto;
        try {
            this.abrirConexion();
            PreparedStatement st = this.conexion.prepareStatement(
                    "INSERT INTO PAGO_PRESTAMO (id_prestamo, fecha, id_empleado, monto_pago) values (?, ?, ?, ?)");
            st = cargarObject(st, pagoPrestamo);
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
        PagoPrestamo pagoPrestamo = (PagoPrestamo) objeto;
        st.setInt(1, pagoPrestamo.getId_prestamo());
        st.setDate(2, pagoPrestamo.getFecha());
        st.setString(3, pagoPrestamo.getId_empleado());
        st.setBigDecimal(4, pagoPrestamo.getMonto_pago());
        return st;
    }

    @Override
    public <T> List listar(String id_jefe) throws Exception {
        List<PagoPrestamo> pagoPrestamos;
        try {
            this.abrirConexion();
            try (PreparedStatement st = this.conexion.prepareStatement("SELECT * FROM VISTA_PAGO_PRESTAMO WHERE id_administrador = ?")) {
                st.setString(1, id_jefe);
                pagoPrestamos = new ArrayList<>();
                try (ResultSet rs = st.executeQuery()) {
                    while (rs.next()) {
                        PagoPrestamo pagoPrestamo = (PagoPrestamo) extraerObject(rs);
                        pagoPrestamos.add(pagoPrestamo);
                    }
                } catch (Exception e) {
                    System.out.println(e);
                }
            } catch (Exception e) {
                pagoPrestamos = null;
                System.out.println(e);
            }
        } catch (Exception e) {
            System.out.println(e);
            throw e;
        } finally {
            cerrarConexion();
        }
        return pagoPrestamos;
    }

    @Override
    public Object extraerObject(ResultSet rs) throws SQLException {
        PagoPrestamo pagoPrestamo = new PagoPrestamo();
        pagoPrestamo.setId_pago(rs.getInt("id_pago"));
        pagoPrestamo.setId_prestamo(rs.getInt("id_prestamo"));
        pagoPrestamo.setFecha(rs.getDate("fecha"));
        pagoPrestamo.setId_administrador(rs.getString("id_administrador"));
        pagoPrestamo.setId_empleado(rs.getString("id_empleado"));
        pagoPrestamo.setEmpleado(rs.getString("empleado"));
        pagoPrestamo.setId_prestador(rs.getString("id_prestador"));
        pagoPrestamo.setPrestador(rs.getString("prestador"));
        pagoPrestamo.setMonto_prestamo(rs.getBigDecimal("monto_prestamo"));
        pagoPrestamo.setMonto_pago(rs.getBigDecimal("monto_pago"));
        pagoPrestamo.setMonto_por_pagar(rs.getBigDecimal("monto_por_pagar"));
        return pagoPrestamo;
    }

    @Override
    public Object modificar(Object objeto) throws Exception {
        PagoPrestamo pagoPrestamoM = (PagoPrestamo) objeto;
        PagoPrestamo pagoPrestamo = null;
        this.abrirConexion();
        try (PreparedStatement st = this.conexion.prepareStatement("SELECT * FROM VISTA_PAGO_PRESTAMO WHERE id_pago = ? AND id_prestamo = ?")) {
            st.setInt(1, pagoPrestamoM.getId_pago());
            st.setInt(2, pagoPrestamoM.getId_prestamo());
            try (ResultSet rs = st.executeQuery()) {
                while (rs.next()) {
                    pagoPrestamo = (PagoPrestamo) extraerObject(rs);
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return pagoPrestamo;
    }

    @Override
    public void actualizar(Object objeto) throws Exception {
        PagoPrestamo pagoPrestamo = (PagoPrestamo) objeto;
        try {
            this.abrirConexion();
            PreparedStatement st = this.conexion.prepareStatement("UPDATE PAGO_PRESTAMO SET fecha = ?, id_empleado = ?, monto_pago = ? where id_pago = ? AND id_prestamo = ?");
            st.setDate(1, pagoPrestamo.getFecha());
            st.setString(2, pagoPrestamo.getId_empleado());
            st.setBigDecimal(3, pagoPrestamo.getMonto_pago());
            st.setInt(4, pagoPrestamo.getId_pago());
            st.setInt(5, pagoPrestamo.getId_prestamo());
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
        PagoPrestamo pagoPrestamo = (PagoPrestamo) objeto;
        try {
            this.abrirConexion();
            PreparedStatement st = this.conexion.prepareStatement("DELETE FROM PAGO_PRESTAMO WHERE id_pago = ?");
            st.setInt(1, pagoPrestamo.getId_pago());
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
        List<PagoPrestamo> pagoPrestamos;
        try {
            this.abrirConexion();
            try (PreparedStatement st = this.conexion.prepareStatement("SELECT * FROM VISTA_PAGO_PRESTAMO WHERE " + nombre_campo + " like ? AND id_administrador = ?")) {
                st.setString(1, "%" + dato + "%");
                st.setString(2, id_jefe);
                pagoPrestamos = new ArrayList<>();
                try (ResultSet rs = st.executeQuery()) {
                    while (rs.next()) {
                        PagoPrestamo pagoPrestamo = (PagoPrestamo) extraerObject(rs);
                        pagoPrestamos.add(pagoPrestamo);
                    }
                }
            }
        } catch (Exception e) {
            System.out.println(e);
            throw e;
        } finally {
            this.cerrarConexion();
        }
        return pagoPrestamos;
    }
}
