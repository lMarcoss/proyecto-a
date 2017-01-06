package dao.prestamo;

import dao.Conexion;
import entidades.prestamo.Prestamo;
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
public class PrestamoCRUD extends Conexion implements OperacionesCRUD {

    @Override
    public void registrar(Object objeto) throws Exception {
        Prestamo prestamo = (Prestamo) objeto;
        try {
            this.abrirConexion();
            PreparedStatement st = this.conexion.prepareStatement(
                    "INSERT INTO PRESTAMO (fecha,id_prestador,id_empleado,monto_prestamo,interes) VALUES (?,?,?,?,?)");
            st = cargarObject(st, prestamo);
            st.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
            throw e;
        } finally {
            this.cerrarConexion();
        }
    }

    @Override
    public <T> List listar(String id_jefe) throws Exception {
        List<Prestamo> prestamos;
        try {
            this.abrirConexion();
            try (PreparedStatement st = this.conexion.prepareStatement("SELECT * FROM VISTA_PRESTAMO WHERE id_administrador = ?")) {
                st.setString(1, id_jefe);
                prestamos = new ArrayList();
                try (ResultSet rs = st.executeQuery()) {
                    while (rs.next()) {
                        Prestamo prestamo = (Prestamo) extraerObject(rs);
                        prestamos.add(prestamo);
                    }
                }
            } catch (Exception e) {
                prestamos = null;
                System.out.println(e);
            }
        } catch (Exception e) {
            System.out.println(e);
            throw e;
        } finally {
            this.cerrarConexion();
        }
        return prestamos;
    }

    @Override
    public Object modificar(Object objeto) throws Exception {
        Prestamo prestamoM = (Prestamo) objeto;
        Prestamo prestamo = null;
        this.abrirConexion();
        try (PreparedStatement st = this.conexion.prepareStatement("SELECT * FROM VISTA_PRESTAMO WHERE id_prestamo = ?")) {
            st.setInt(1, prestamoM.getId_prestamo());
            try (ResultSet rs = st.executeQuery()) {
                while (rs.next()) {
                    prestamo = (Prestamo) extraerObject(rs);
                }
            }
        }
        return prestamo;
    }

    @Override
    public void actualizar(Object objeto) throws Exception {
        Prestamo prestamo = (Prestamo) objeto;
        try {
            this.abrirConexion();
            PreparedStatement st = this.conexion.prepareStatement("UPDATE PRESTAMO SET fecha = ?, id_prestador = ?, monto_prestamo = ?, interes = ? WHERE id_prestamo = ?");
            st.setDate(1, prestamo.getFecha());
            st.setString(2, prestamo.getId_prestador());
            st.setBigDecimal(3, prestamo.getMonto_prestamo());
            st.setInt(4, prestamo.getInteres());
            st.setInt(5, prestamo.getId_prestamo());
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
        Prestamo prestamo = (Prestamo) objeto;
        try {
            this.abrirConexion();
            PreparedStatement st = this.conexion.prepareStatement("DELETE FROM PRESTAMO WHERE id_prestamo = ?");
            st.setInt(1, prestamo.getId_prestamo());
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
        List<Prestamo> prestamos;
        try {
            this.abrirConexion();
            try (PreparedStatement st = this.conexion.prepareStatement("SELECT * FROM VISTA_PRESTAMO WHERE " + nombre_campo + " like ? AND id_administrador = ?")) {
                st.setString(1, "%" + dato + "%");
                st.setString(2, id_jefe);
                prestamos = new ArrayList();
                try (ResultSet rs = st.executeQuery()) {
                    while (rs.next()) {
                        Prestamo prestamo = (Prestamo) extraerObject(rs);
                        prestamos.add(prestamo);
                    }
                }
            }
        } catch (Exception e) {
            System.out.println(e);
            throw e;
        } finally {
            this.cerrarConexion();
        }
        return prestamos;
    }

    @Override
    public Object extraerObject(ResultSet rs) throws SQLException {
        Prestamo prestamo = new Prestamo();
        prestamo.setId_prestamo(rs.getInt("id_prestamo"));
        prestamo.setFecha(rs.getDate("fecha"));
        prestamo.setId_prestador(rs.getString("id_prestador"));
        prestamo.setPrestador(rs.getString("prestador"));
        prestamo.setId_empleado(rs.getString("id_empleado"));
        prestamo.setEmpleado(rs.getString("empleado"));
        prestamo.setId_administrador(rs.getString("id_administrador"));
        prestamo.setMonto_prestamo(rs.getBigDecimal("monto_prestamo"));
        prestamo.setInteres(rs.getInt("interes"));
        prestamo.setInteres_mesual(rs.getBigDecimal("interes_mensual"));
        prestamo.setMonto_pagado(rs.getBigDecimal("monto_pagado"));
        prestamo.setMonto_por_pagar(rs.getBigDecimal("monto_por_pagar"));
        return prestamo;
    }

    @Override
    public PreparedStatement cargarObject(PreparedStatement st, Object objeto) throws SQLException {
        Prestamo prestamo = (Prestamo) objeto;
        st.setDate(1, prestamo.getFecha());
        st.setString(2, prestamo.getId_prestador());
        st.setString(3, prestamo.getId_empleado());
        st.setBigDecimal(4, prestamo.getMonto_prestamo());
        st.setInt(5, prestamo.getInteres());
        return st;
    }

    public List<Prestamo> buscarPorId(String id_prestamo) throws Exception {
        List<Prestamo> prestamos;
        try {
            this.abrirConexion();
            try (PreparedStatement st = this.conexion.prepareStatement("SELECT * FROM VISTA_PRESTAMO WHERE id_prestamo = ?")) {
                st.setString(1, id_prestamo);
                prestamos = new ArrayList();
                try (ResultSet rs = st.executeQuery()) {
                    while (rs.next()) {
                        Prestamo prestamo = (Prestamo) extraerObject(rs);
                        prestamos.add(prestamo);
                    }
                }
            }
        } catch (Exception e) {
            System.out.println(e);
            throw e;
        } finally {
            this.cerrarConexion();
        }
        return prestamos;
    }

    //Lista de prestamos por pagar
    public <T> List listarPrestamoPorPagar() throws Exception {
        List<Prestamo> prestamos;
        try {
            this.abrirConexion();
            try (PreparedStatement st = this.conexion.prepareStatement(
                    "SELECT * FROM VISTA_PRESTAMO WHERE monto_por_pagar > 0")) {
                prestamos = new ArrayList();
                try (ResultSet rs = st.executeQuery()) {
                    while (rs.next()) {
                        Prestamo prestamo = (Prestamo) extraerObject(rs);
                        prestamos.add(prestamo);
                    }
                }
            } catch (Exception e) {
                prestamos = null;
                System.out.println(e);
            }
        } catch (Exception e) {
            System.out.println(e);
            throw e;
        } finally {
            this.cerrarConexion();
        }
        return prestamos;
    }

    //Construir objeto prestamo por persona
    public Object extraerPrestamoPorPersona(ResultSet rs) throws SQLException {
        Prestamo prestamo = new Prestamo();
        prestamo.setId_administrador(rs.getString("id_administrador"));
        prestamo.setId_prestador(rs.getString("id_prestador"));
        prestamo.setPrestador(rs.getString("prestador"));
        prestamo.setMonto_prestamo(rs.getBigDecimal("monto_total"));
        prestamo.setInteres_mesual(rs.getBigDecimal("interes_total"));
        return prestamo;
    }

    // Buscar un prestamo por persona
    public <T> List buscarMontoPorPersona(String nombre_campo, String dato) throws Exception {
        List<Prestamo> prestamos;
        try {
            this.abrirConexion();
            try (PreparedStatement st = this.conexion.prepareStatement("SELECT * FROM PRESTAMO_TOTAL_PERSONA WHERE " + nombre_campo + " like ?")) {
                st.setString(1, "%" + dato + "%");
                prestamos = new ArrayList();
                try (ResultSet rs = st.executeQuery()) {
                    while (rs.next()) {
                        Prestamo prestamo = (Prestamo) extraerPrestamoPorPersona(rs);
                        prestamos.add(prestamo);
                    }
                }
            }
        } catch (Exception e) {
            System.out.println(e);
            throw e;
        } finally {
            this.cerrarConexion();
        }
        return prestamos;
    }
}
