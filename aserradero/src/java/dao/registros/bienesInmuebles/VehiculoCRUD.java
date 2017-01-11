package dao.registros.bienesInmuebles;

import dao.Conexion;
import entidades.registros.bienesInmuebles.Vehiculo;
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
public class VehiculoCRUD extends Conexion implements OperacionesCRUD {

    @Override
    public void registrar(Object objeto) throws Exception {
        Vehiculo vehiculo = (Vehiculo) objeto;
        try {
            this.abrirConexion();
            PreparedStatement st = this.conexion.prepareStatement(""
                    + "INSERT INTO VEHICULO (id_vehiculo,matricula, tipo, color, carga_admitida, motor,modelo, costo, id_empleado) values (?,?,?,?,?,?,?,?,?)");
            st = cargarObject(st, vehiculo);
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
        List<Vehiculo> vehiculos;
        try {
            this.abrirConexion();
            try (PreparedStatement st = this.conexion.prepareStatement("SELECT * FROM VISTA_VEHICULO WHERE id_jefe = ?")) {
                st.setString(1, id_jefe);
                vehiculos = new ArrayList<>();
                try (ResultSet rs = st.executeQuery()) {
                    while (rs.next()) {
                        Vehiculo vehiculo = (Vehiculo) extraerObject(rs);
                        vehiculos.add(vehiculo);
                    }
                } catch (Exception e) {
                    System.out.println(e);
                }
            } catch (Exception e) {
                vehiculos = null;
                System.out.println(e);
            }
        } catch (Exception e) {
            System.out.println(e);
            throw e;
        } finally {
            cerrarConexion();
        }
        return vehiculos;
    }

    @Override
    public Object modificar(Object objeto) throws Exception {
        Vehiculo vehiculoM = (Vehiculo) objeto;
        Vehiculo vehiculo = null;
        this.abrirConexion();
        try (PreparedStatement st = this.conexion.prepareStatement("SELECT * FROM VISTA_VEHICULO WHERE id_vehiculo = ?")) {
            st.setInt(1, vehiculoM.getId_vehiculo());
            try (ResultSet rs = st.executeQuery()) {
                while (rs.next()) {
                    vehiculo = (Vehiculo) extraerObject(rs);
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return vehiculo;
    }

    @Override
    public void actualizar(Object objeto) throws Exception {
        Vehiculo vehiculo = (Vehiculo) objeto;
        try {
            this.abrirConexion();
            PreparedStatement st = this.conexion.prepareStatement("UPDATE VEHICULO SET matricula = ?,tipo = ?, color = ?, carga_admitida = ?, motor = ?, modelo=?, costo=? where id_vehiculo=?");
            st.setString(1, vehiculo.getMatricula());
            st.setString(2, vehiculo.getTipo());
            st.setString(3, vehiculo.getColor());
            st.setString(4, vehiculo.getCarga_admitida());
            st.setString(5, vehiculo.getMotor());
            st.setString(6, vehiculo.getModelo());
            st.setString(7, String.valueOf(vehiculo.getCosto()));
            st.setInt(8, vehiculo.getId_vehiculo());
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
        Vehiculo vehiculo = (Vehiculo) objeto;
        try {
            this.abrirConexion();
            PreparedStatement st = this.conexion.prepareStatement("DELETE FROM VEHICULO WHERE id_vehiculo = ?");
            st.setInt(1, vehiculo.getId_vehiculo());
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
        List<Vehiculo> vehiculos;
        try {
            this.abrirConexion();
            try (PreparedStatement st = this.conexion.prepareStatement("SELECT * FROM VISTA_VEHICULO WHERE " + nombre_campo + " like ? AND id_jefe = ?")) {
                st.setString(1, "%" + dato + "%");
                st.setString(2, id_jefe);
                vehiculos = new ArrayList<>();
                try (ResultSet rs = st.executeQuery()) {
                    while (rs.next()) {
                        Vehiculo vehiculo = (Vehiculo) extraerObject(rs);
                        vehiculos.add(vehiculo);
                    }
                }
            }
        } catch (Exception e) {
            System.out.println(e);
            throw e;
        } finally {
            this.cerrarConexion();
        }
        return vehiculos;
    }

    @Override
    public Object extraerObject(ResultSet rs) throws SQLException {
        Vehiculo vehiculo = new Vehiculo();
        vehiculo.setId_vehiculo(Integer.valueOf(rs.getString("id_vehiculo")));
        vehiculo.setMatricula(rs.getString("matricula"));
        vehiculo.setTipo(rs.getString("tipo"));
        vehiculo.setColor(rs.getString("color"));
        vehiculo.setCarga_admitida(rs.getString("carga_admitida"));
        vehiculo.setMotor(rs.getString("motor"));
        vehiculo.setModelo(rs.getString("modelo"));
        vehiculo.setCosto(rs.getBigDecimal("costo"));
        vehiculo.setId_empleado(rs.getString("id_empleado"));
        vehiculo.setEmpleado((rs.getString("empleado")));
        return vehiculo;
    }

    @Override
    public PreparedStatement cargarObject(PreparedStatement st, Object objeto) throws SQLException {
        Vehiculo vehiculo = (Vehiculo) objeto;
        st.setInt(1, vehiculo.getId_vehiculo());
        st.setString(2, vehiculo.getMatricula());
        st.setString(3, vehiculo.getTipo());
        st.setString(4, vehiculo.getColor());
        st.setString(5, vehiculo.getCarga_admitida());
        st.setString(6, vehiculo.getMotor());
        st.setString(7, vehiculo.getModelo());
        st.setBigDecimal(8, vehiculo.getCosto());
        st.setString(9, vehiculo.getId_empleado());
        return st;
    }
}
