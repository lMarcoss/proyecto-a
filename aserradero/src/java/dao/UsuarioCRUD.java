package dao;

import entidades.Usuario;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author lmarcoss
 */
public class UsuarioCRUD extends Conexion{
       
    public void registrar(Usuario usuario) throws Exception{
        try{
            this.abrirConexion();
                PreparedStatement st= this.conexion.prepareStatement(
                        "INSERT INTO USUARIO (id_empleado,nombre_usuario,contrasenia,metodo,email) VALUES (?,?,SHA1(?),?,?)");
            st.setString(1,usuario.id_empleado);
            st.setString(2,usuario.nombre_usuario);
            st.setString(3,usuario.contrasenia);
            st.setString(4,"sha1");
            st.setString(5,usuario.email);
            st.executeUpdate();
        }catch(Exception e){
            System.out.println(e);
            throw e;
        }finally{
            this.cerrarConexion();
        } 
    }
    
    public List<Usuario> listar() throws Exception {
        List<Usuario> usuarios;
        try{
            this.abrirConexion();
            try (PreparedStatement st = this.conexion.prepareStatement("SELECT * FROM USUARIO")) {
                usuarios = new ArrayList();
                try (ResultSet rs = st.executeQuery()) {
                    while (rs.next()) {
                        Usuario usuario = extraerUsuario(rs);
                        usuarios.add(usuario);
                    }
                }
            }catch(Exception e){
                usuarios = null;
                System.out.println(e);
            }
        }catch(Exception e){
            System.out.println(e);
            throw e;
        }finally{
            this.cerrarConexion();
        } 
        return usuarios;
    }
    
    public Usuario modificar(Usuario usuarioM) throws Exception{
        Usuario usuario = null;
        this.abrirConexion();
            try (PreparedStatement st = this.conexion.prepareStatement("SELECT * FROM USUARIO WHERE nombre_usuario = ?")) {
                st.setString(1, usuarioM.getNombre_usuario());
                try (ResultSet rs = st.executeQuery()) {
                    while (rs.next()) {
                        usuario = extraerUsuario(rs);
                    }
                }
            }
        return usuario;
    }
    
    public void actualizar(Usuario usuario) throws Exception{
        try{
            this.abrirConexion();
            PreparedStatement st= this.conexion.prepareStatement("UPDATE USUARIO SET id_empleado = ?, contrasenia = SHA1(?), metodo = ?, email = ? WHERE nombre_usuario = ?");
            st.setString(1, usuario.getId_empleado());
            st.setString(2, usuario.getContrasenia());
            st.setString(3, "sha1");
            st.setString(4, usuario.getEmail());
            st.setString(6, usuario.getNombre_usuario());
            st.executeUpdate();
        }catch(Exception e){
            System.out.println(e);
            throw e;
        }finally{
            this.cerrarConexion();
        } 
    }
    
    public void eliminar(Usuario usuario) throws Exception{
        try{
            this.abrirConexion();
            PreparedStatement st= this.conexion.prepareStatement("DELETE FROM USUARIO WHERE nombre_usuario = ?");
            st.setString(1,usuario.nombre_usuario);
            st.executeUpdate();
        }catch(Exception e){
            System.out.println(e);
            throw e;
        }finally{
            this.cerrarConexion();
        } 
    }
    
    //Buscar por campo y regresar todos los coincidentes
    public List<Usuario> buscar(String nombre_campo,String dato) throws Exception{
        List<Usuario> usuarios;
        try{
            this.abrirConexion();
            try (PreparedStatement st = this.conexion.prepareStatement("SELECT * FROM USUARIO WHERE "+nombre_campo+" like ?")) {
                st.setString(1, "%"+dato+"%");
                usuarios = new ArrayList();
                try (ResultSet rs = st.executeQuery()) {
                    while (rs.next()) {
                        Usuario usuario = extraerUsuario(rs);
                        usuarios.add(usuario);
                    }
                }
            }
        }catch(Exception e){
            System.out.println(e);
            throw e;
        }finally{
            this.cerrarConexion();
        }
        return usuarios;
    }
    
    public Usuario validarUsuario(String nombre_usuario, String contrasenia) throws Exception{
        Usuario user = null;
        try{
            this.abrirConexion();
            try (PreparedStatement st = this.conexion.prepareStatement("SELECT  * FROM VISTA_USUARIO WHERE nombre_usuario = ? AND contrasenia = (SELECT SHA1(?)) AND estatus = ?")) {
                st.setString(1,nombre_usuario);
                st.setString(2,contrasenia);
                st.setString(3,"Activo");
                try (ResultSet rs = st.executeQuery()) {
                    while (rs.next()) {
                        user = extraerUsuario(rs);
                    }
                }catch(Exception e){
                    System.out.println(e);
                }
            }
        }catch(Exception e){
            System.out.println(e);
            throw e;
        }finally{
            this.cerrarConexion();
        }
        return user;
    }

    private Usuario extraerUsuario(ResultSet rs) throws SQLException {
        Usuario usuario = new Usuario();
        usuario.setNombre_usuario(rs.getString("nombre_usuario"));
        usuario.setContrasenia(rs.getString("contrasenia"));
        usuario.setId_empleado(rs.getString("id_empleado"));
        usuario.setId_jefe(rs.getString("id_jefe"));
        usuario.setRol(rs.getString("rol"));
        usuario.setEstatus(rs.getString("estatus"));
        return usuario;
    }
    
}
