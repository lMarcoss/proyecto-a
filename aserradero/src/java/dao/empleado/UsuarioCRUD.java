package dao.empleado;

import dao.Conexion;
import entidades.empleado.Usuario;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author lmarcoss password = "4@*" + usuario.contrasenia + "&#+"; // Clave de
 * Seguridad para contraseña
 */
public class UsuarioCRUD extends Conexion {

    private final String clave1 = "^4%m@C*&%c#L+=";
    private final String clave2 = "$|2A!T>A0";

    // ('COHA820724HOCRNN02COHA8207','Antonio',concat('$|2A',sha2('^4%m@C*antonio&%c#L+=',224),'!T!A0'));
    public void registrar(Usuario usuario) throws Exception {
        try {
            this.abrirConexion();
            PreparedStatement st = this.conexion.prepareStatement(
                    "INSERT INTO USUARIO (id_empleado,nombre_usuario,contrasenia) VALUES (?,?,sha2(concat(?,?,?),224))");
            st.setString(1, usuario.id_empleado);
            st.setString(2, usuario.getNombre_usuario());
            st.setString(3, clave1);
            st.setString(4, usuario.getContrasenia());
            st.setString(5, clave2);
            st.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
            throw e;
        } finally {
            this.cerrarConexion();
        }
    }

    public List<Usuario> listar(String id_jefe) throws Exception {
        List<Usuario> usuarios;
        try {
            this.abrirConexion();
            try (PreparedStatement st = this.conexion.prepareStatement("SELECT * FROM VISTA_USUARIO WHERE (rol = 'Administrador' OR rol = 'Empleado' OR rol = 'Vendedor') AND id_jefe = ?")) {
                st.setString(1, id_jefe);
                usuarios = new ArrayList();
                try (ResultSet rs = st.executeQuery()) {
                    while (rs.next()) {
                        Usuario usuario = extraerUsuario(rs, "");
                        usuarios.add(usuario);
                    }
                }
            } catch (Exception e) {
                usuarios = null;
                System.out.println(e);
            }
        } catch (Exception e) {
            System.out.println(e);
            throw e;
        } finally {
            this.cerrarConexion();
        }
        return usuarios;
    }

    public Usuario modificar(Usuario usuarioM) throws Exception {
        Usuario usuario = null;
        this.abrirConexion();
        try (PreparedStatement st = this.conexion.prepareStatement("SELECT * FROM VISTA_USUARIO WHERE nombre_usuario = ?")) {
            st.setString(1, usuarioM.getNombre_usuario());
            try (ResultSet rs = st.executeQuery()) {
                while (rs.next()) {
                    usuario = extraerUsuario(rs, "");
                }
            }
        }
        return usuario;
    }

    public void actualizar(Usuario usuario) throws Exception {
        try {
            this.abrirConexion();
            PreparedStatement st = this.conexion.prepareStatement("UPDATE USUARIO SET contrasenia = sha2(concat(?,?,?),224) WHERE nombre_usuario = ? AND id_empleado = ?");
            st.setString(1, clave1);
            st.setString(2, usuario.getContrasenia());
            st.setString(3, clave2);
            st.setString(4, usuario.getNombre_usuario());
            st.setString(5, usuario.getId_empleado());
            st.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
            throw e;
        } finally {
            this.cerrarConexion();
        }
    }

    public void eliminar(Usuario usuario) throws Exception {
        try {
            this.abrirConexion();
            PreparedStatement st = this.conexion.prepareStatement("DELETE FROM USUARIO WHERE nombre_usuario = ?");
            st.setString(1, usuario.nombre_usuario);
            st.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
            throw e;
        } finally {
            this.cerrarConexion();
        }
    }

    //Buscar por campo y regresar todos los coincidentes
    public List<Usuario> buscar(String nombre_campo, String dato) throws Exception {
        List<Usuario> usuarios;
        try {
            this.abrirConexion();
            try (PreparedStatement st = this.conexion.prepareStatement("SELECT * FROM USUARIO WHERE " + nombre_campo + " like ?")) {
                st.setString(1, "%" + dato + "%");
                usuarios = new ArrayList();
                try (ResultSet rs = st.executeQuery()) {
                    while (rs.next()) {
                        Usuario usuario = extraerUsuario(rs, "");
                        usuarios.add(usuario);
                    }
                }
            }
        } catch (Exception e) {
            System.out.println(e);
            throw e;
        } finally {
            this.cerrarConexion();
        }
        return usuarios;
    }

    public Usuario validarUsuario(String nombre_usuario, String contrasenia) throws Exception {
        String consulta = "SELECT  * FROM VISTA_USUARIO "
                + "WHERE nombre_usuario = ?"
                + "AND estatus = ? "
                + "AND contrasenia = sha2(concat(?,?,?),224) AND (rol = 'Administrador' OR rol = 'Empleado' OR rol = 'Vendedor')";
        Usuario user = null;
        try {
            this.abrirConexion();
            try (PreparedStatement st = this.conexion.prepareStatement(consulta)) {
                st.setString(1, nombre_usuario);
                st.setString(2, "Activo");
                st.setString(3, clave1);
                st.setString(4, contrasenia);
                st.setString(5, clave2);
                try (ResultSet rs = st.executeQuery()) {
                    while (rs.next()) {
                        user = extraerUsuario(rs, "validar");
                    }
                } catch (Exception e) {
                    System.out.println(e);
                }
            }
        } catch (Exception e) {
            System.out.println(e);
            throw e;
        } finally {
            this.cerrarConexion();
        }
        return user;
    }

    private Usuario extraerUsuario(ResultSet rs, String action) throws SQLException {
        Usuario usuario = new Usuario();
        usuario.setNombre_usuario(rs.getString("nombre_usuario"));
        if (action.equals("validar")) {
            usuario.setContrasenia(rs.getString("contrasenia"));
        }
        usuario.setId_empleado(rs.getString("id_empleado"));
        usuario.setEmpleado(rs.getString("empleado"));
        usuario.setId_jefe(rs.getString("id_jefe"));
        usuario.setRol(rs.getString("rol"));
        usuario.setEstatus(rs.getString("estatus"));
        return usuario;
    }

    public boolean existeUsuario() throws Exception {
        boolean existe = false;
        try {
            this.abrirConexion();
            try (PreparedStatement st = this.conexion.prepareStatement("SELECT * FROM USUARIO, EMPLEADO WHERE USUARIO.id_empleado = EMPLEADO.id_empleado AND estatus = ? AND rol = 'Administrador'")) {
                st.setString(1, "Activo");
                try (ResultSet rs = st.executeQuery()) {
                    while (rs.next()) {
                        existe = true;
                    }
                } catch (Exception e) {
                    System.out.println(e);
                }
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
