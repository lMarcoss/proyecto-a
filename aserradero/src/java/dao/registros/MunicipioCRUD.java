package dao.registros;

import dao.Conexion;
import entidades.registros.Municipio;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author lmarcoss
 */
public class MunicipioCRUD extends Conexion{
    
    public void registrar(Municipio municipio) throws Exception{
        try{
            this.abrirConexion();
            PreparedStatement st= this.conexion.prepareStatement("INSERT INTO MUNICIPIO (nombre_municipio,estado,telefono) VALUES (?,?,?)");
            st.setString(1,municipio.getNombre_municipio());
            st.setString(2,municipio.getEstado());
            st.setString(3,municipio.getTelefono());
            st.executeUpdate();
        }catch(Exception e){
            System.out.println(e);
            throw e;
        }finally{
            this.cerrarConexion();
        } 
    }
    
    public List<Municipio> listar() throws Exception {
        List<Municipio> municipios;
        try{
            this.abrirConexion();
            try (PreparedStatement st = this.conexion.prepareStatement("SELECT * FROM VISTA_MUNICIPIO")) {
                municipios = new ArrayList();
                try (ResultSet rs = st.executeQuery()) {
                    while (rs.next()) {
                        Municipio municipio = extraerMunicipio(rs);
                        municipios.add(municipio);
                    }
                }
            }catch(Exception e){
                municipios = null;
                System.out.println(e);
            }
        }catch(Exception e){
            System.out.println(e);
            throw e;
        }finally{
            this.cerrarConexion();
        } 
        return municipios;
    }
    
    public Municipio modificar(Municipio municipioM) throws Exception{
        Municipio municipio = null;
        this.abrirConexion();
            try (PreparedStatement st = this.conexion.prepareStatement("SELECT * FROM VISTA_MUNICIPIO WHERE nombre_municipio = ?")) {
                st.setString(1, municipioM.getNombre_municipio());
                try (ResultSet rs = st.executeQuery()) {
                    while (rs.next()) {
                        municipio = extraerMunicipio(rs);
                    }
                }
            }
        return municipio;
    }
    
    public void actualizar(Municipio municipio) throws Exception{
        try{
            this.abrirConexion();
            PreparedStatement st= this.conexion.prepareStatement("UPDATE MUNICIPIO SET telefono = ?, estado=? WHERE nombre_municipio = ?");
            st.setString(1,municipio.getTelefono());
            st.setString(2,municipio.getEstado());
            st.setString(3,municipio.getNombre_municipio());
            st.executeUpdate();
        }catch(Exception e){
            throw e;
        }finally{
            this.cerrarConexion();
        } 
    }
    
    public void eliminar(Municipio municipio) throws Exception{
        try{
            this.abrirConexion();
            PreparedStatement st= this.conexion.prepareStatement("DELETE FROM MUNICIPIO WHERE nombre_municipio = ?");
            st.setString(1,municipio.getNombre_municipio());
            st.executeUpdate();
        }catch(Exception e){
            System.out.println(e);
            throw e;
        }finally{
            this.cerrarConexion();
        } 
    }
    
    //Buscar por campo y regresar todos los coincidentes
    public List<Municipio> buscar(String nombre_campo,String dato) throws Exception{
        List<Municipio> municipios;
        try{
            this.abrirConexion();
            try (PreparedStatement st = this.conexion.prepareStatement("SELECT * FROM VISTA_MUNICIPIO WHERE "+nombre_campo+" like ?")) {
                st.setString(1, "%"+dato+"%");
                municipios = new ArrayList();
                try (ResultSet rs = st.executeQuery()) {
                    while (rs.next()) {
                        Municipio municipio = extraerMunicipio(rs);
                        municipios.add(municipio);
                    }
                }
            }
        }catch(Exception e){
            System.out.println(e);
            throw e;
        }finally{
            this.cerrarConexion();
        }
        return municipios;
    }

    private Municipio extraerMunicipio(ResultSet rs) throws SQLException {
        Municipio municipio = new Municipio();
        municipio.setNombre_municipio(rs.getString("nombre_municipio"));
        municipio.setEstado(rs.getString("estado"));
        municipio.setTelefono(rs.getString("telefono"));
        return municipio;
    }

    public List<Municipio> buscarMunicipio(String nombre_municipio,String estado)throws Exception{
        List<Municipio> municipios = null;
        try{
            this.abrirConexion();
            try (PreparedStatement st = this.conexion.prepareStatement("SELECT * FROM VISTA_MUNICIPIO WHERE nombre_municipio = ? AND estado = ?")) {
                st.setString(1, nombre_municipio);
                st.setString(2, estado);
                municipios = new ArrayList();
                try (ResultSet rs = st.executeQuery()) {
                    while (rs.next()) {
                        Municipio municipio = extraerMunicipio(rs);
                        municipios.add(municipio);
                    }
                }
            }
        }catch(Exception e){
            System.out.println(e);
            throw e;
        }finally{
            this.cerrarConexion();
        }
        return municipios;
    }
}
