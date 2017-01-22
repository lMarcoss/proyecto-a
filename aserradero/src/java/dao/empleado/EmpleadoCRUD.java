package dao.empleado;

import dao.Conexion;
import entidades.empleado.Empleado;
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
public class EmpleadoCRUD extends Conexion implements OperacionesCRUD {

    @Override
    public void registrar(Object objeto) throws Exception {
        Empleado empleado = (Empleado) objeto;
        try {
            this.abrirConexion();
            PreparedStatement st = this.conexion.prepareStatement(
                    "INSERT INTO EMPLEADO (id_empleado,id_persona,id_jefe,rol,estatus) VALUES (?,?,?,?,?)");
            st = cargarObject(st, empleado);
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
        //Sólo cargamos los datos que se insertan en la tabla EMPLEADO
        Empleado empleado = (Empleado) objeto;
        st.setString(1, empleado.getId_persona());//El id_empleado se calcula concatenando el id_persona con el id_jefe con el trigger de la BD
        st.setString(2, empleado.getId_persona());
        st.setString(3, empleado.getId_jefe());
        st.setString(4, empleado.getRol());
        st.setString(5, empleado.getEstatus());
        return st;
    }

    @Override
    public <T> List listar(String id_jefe, String rol) throws Exception {
        List<Empleado> empleados;
        try {
            this.abrirConexion();
            try (PreparedStatement st = this.conexion.prepareStatement("SELECT * FROM PERSONAL_EMPLEADO WHERE id_jefe = ? ORDER BY rol, empleado")) {
                st.setString(1, id_jefe);
                empleados = new ArrayList();
                try (ResultSet rs = st.executeQuery()) {
                    while (rs.next()) {
                        Empleado empleado = (Empleado) extraerObject(rs);
                        empleados.add(empleado);
                    }
                }
            } catch (Exception e) {
                empleados = null;
                System.out.println(e);
            }
        } catch (Exception e) {
            System.out.println(e);
            throw e;
        } finally {
            this.cerrarConexion();
        }
        return empleados;
    }

    @Override
    public Object extraerObject(ResultSet rs) throws SQLException {
        // Extraemos todos los atributos del objeto
        Empleado empleado = new Empleado();
        empleado.setId_empleado(rs.getString("id_empleado"));
        empleado.setId_persona(rs.getString("id_persona"));
        empleado.setEmpleado(rs.getString("empleado"));
        empleado.setId_jefe(rs.getString("id_jefe"));
        empleado.setRol(rs.getString("rol"));
        empleado.setEstatus(rs.getString("estatus"));
        return empleado;
    }

    @Override
    public Object modificar(Object objeto) throws Exception {
        Empleado empleadoM = (Empleado) objeto;
        Empleado empleado = null;
        this.abrirConexion();
        try (PreparedStatement st = this.conexion.prepareStatement("SELECT * FROM PERSONAL_EMPLEADO WHERE id_empleado = ? AND rol = ?")) {
            st.setString(1, empleadoM.getId_empleado());
            st.setString(2, empleadoM.getRol());
            try (ResultSet rs = st.executeQuery()) {
                while (rs.next()) {
                    empleado = (Empleado) extraerObject(rs);
                }
            }
        }
        return empleado;
    }

    @Override
    public void actualizar(Object objeto) throws Exception {
        Empleado empleado = (Empleado) objeto;
        try {
            this.abrirConexion();
            PreparedStatement st = this.conexion.prepareStatement("UPDATE EMPLEADO SET estatus = ? WHERE id_empleado = ? AND rol = ?");
            st.setString(1, empleado.getEstatus());
            st.setString(2, empleado.getId_empleado());
            st.setString(3, empleado.getRol());
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
        Empleado empleado = (Empleado) objeto;
        try {
            this.abrirConexion();
            PreparedStatement st = this.conexion.prepareStatement("DELETE FROM EMPLEADO WHERE id_empleado = ? AND id_jefe = ? AND rol = ?");
            st.setString(1, empleado.getId_empleado());
            st.setString(2, empleado.getId_jefe());
            st.setString(3, empleado.getRol());
            st.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
            throw e;
        } finally {
            this.cerrarConexion();
        }
    }

    public <T> List buscar(String nombre_campo, String dato, String id_jefe, String rol) throws Exception {
        List<Empleado> empleados;
        try {
            this.abrirConexion();
            try (PreparedStatement st = this.conexion.prepareStatement("SELECT * FROM PERSONAL_EMPLEADO WHERE " + nombre_campo + " like ? AND id_jefe")) {
                st.setString(1, "%" + dato + "%");
                st.setString(2, id_jefe);
                empleados = new ArrayList();
                try (ResultSet rs = st.executeQuery()) {
                    while (rs.next()) {
                        Empleado empleado = (Empleado) extraerObject(rs);
                        empleados.add(empleado);
                    }
                }
            }
        } catch (Exception e) {
            System.out.println(e);
            throw e;
        } finally {
            this.cerrarConexion();
        }
        return empleados;
    }

    public <T> List listarEmpleadoPorRol(String rol) throws Exception {
        List<Empleado> empleados;
        try {
            this.abrirConexion();
            try (PreparedStatement st = this.conexion.prepareStatement("SELECT * FROM PERSONAL_EMPLEADO WHERE rol = ?")) {
                st.setString(1, rol);
                empleados = new ArrayList();
                try (ResultSet rs = st.executeQuery()) {
                    while (rs.next()) {
                        Empleado empleado = (Empleado) extraerObject(rs);
                        empleados.add(empleado);
                    }
                }
            } catch (Exception e) {
                empleados = null;
                System.out.println(e);
            }
        } catch (Exception e) {
            System.out.println(e);
            throw e;
        } finally {
            this.cerrarConexion();
        }
        return empleados;
    }

    public List<Empleado> empleadosParaUsuario(String id_jefe) throws Exception {
        List<Empleado> empleados;
        try {
            this.abrirConexion();
            try (PreparedStatement st = this.conexion.prepareStatement("SELECT * FROM PERSONAL_EMPLEADO WHERE rol = 'Administrador' AND id_empleado NOT IN (SELECT id_empleado FROM USUARIO)")) {
                empleados = new ArrayList();
                try (ResultSet rs = st.executeQuery()) {
                    while (rs.next()) {
                        Empleado empleado = (Empleado) extraerObject(rs);
                        empleados.add(empleado);
                    }
                }
                PreparedStatement st1 = this.conexion.prepareStatement("SELECT * FROM PERSONAL_EMPLEADO WHERE (rol = 'Empleado' OR rol = 'Vendedor') AND id_jefe = ? AND id_empleado NOT IN (SELECT id_empleado FROM USUARIO)");
                st1.setString(1, id_jefe);
                System.out.println("jefe: " + id_jefe);
                try (ResultSet rs1 = st1.executeQuery()) {
                    while (rs1.next()) {
                        Empleado empleado = (Empleado) extraerObject(rs1);
                        empleados.add(empleado);
                        System.out.println("Encontrado");
                    }
                }
            } catch (Exception e) {
                empleados = null;
                System.out.println(e);
            }
        } catch (Exception e) {
            System.out.println(e);
            throw e;
        } finally {
            this.cerrarConexion();
        }
        return empleados;
    }

    public boolean buscarEmpleado(String id_empleado, String id_jefe, String rol) throws Exception {
        boolean existe = false;
        try {
            this.abrirConexion();
            try (PreparedStatement st = this.conexion.prepareStatement("SELECT * FROM EMPLEADO WHERE id_empleado = ? AND id_jefe = ? AND rol = ?")) {
                st.setString(1, id_empleado);
                st.setString(2, id_jefe);
                st.setString(3, rol);
                try (ResultSet rs = st.executeQuery()) {
                    while (rs.next()) {
                        existe = true;
                    }
                }
            } catch (Exception e) {
                existe = false;
                System.out.println(e);
            }
        } catch (Exception e) {
            System.out.println(e);
            throw e;
        } finally {
            this.cerrarConexion();
        }
        return existe;
    }
}