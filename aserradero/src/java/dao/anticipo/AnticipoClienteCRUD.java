package dao.anticipo;

import dao.Conexion;
import entidades.anticipo.AnticipoCliente;
import interfaces.OperacionesCRUD;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Marcos
 */
public class AnticipoClienteCRUD extends Conexion implements OperacionesCRUD{

    @Override
    public void registrar(Object objeto) throws Exception {
        AnticipoCliente anticipoCliente = (AnticipoCliente) objeto;
        try{
            this.abrirConexion();
            PreparedStatement st = this.conexion.prepareStatement(
                        "INSERT INTO ANTICIPO_CLIENTE (fecha,id_cliente,id_empleado,monto_anticipo) VALUES (?,?,?,?)");
            st = cargarObject(st, anticipoCliente);
            st.executeUpdate();
        }catch(Exception e){
            System.out.println(e);
            throw e;
        }finally{
            this.cerrarConexion();
        } 
    }

    @Override
    public <T> List listar(String id_jefe, String rol) throws Exception {
        List<AnticipoCliente> anticipoClientes;
        String consulta;
        if(rol.equals("Administrador")){
            consulta = "SELECT * FROM VISTA_ANTICIPO_CLIENTE WHERE id_jefe = ? ORDER BY id_anticipo_c DESC";
        }else{
            consulta = "SELECT * FROM VISTA_ANTICIPO_CLIENTE WHERE id_jefe = ? AND fecha = CURDATE() ORDER BY id_anticipo_c DESC";
        }
        try{
            this.abrirConexion();
            try (PreparedStatement st = this.conexion.prepareStatement(consulta)) {
                st.setString(1, id_jefe);
                anticipoClientes = new ArrayList();
                try (ResultSet rs = st.executeQuery()) {
                    while (rs.next()) {
                        AnticipoCliente anticipoCliente = (AnticipoCliente) extraerObject(rs);
                        anticipoClientes.add(anticipoCliente);
                    }
                }
            }catch(Exception e){
                anticipoClientes = null;
                System.out.println(e);
            }
        }catch(Exception e){
            System.out.println(e);
            throw e;
        }finally{
            this.cerrarConexion();
        } 
        return anticipoClientes;
    }

    @Override
    public Object modificar(Object objeto) throws Exception {
        AnticipoCliente anticipoClienteM = (AnticipoCliente) objeto;
        AnticipoCliente anticipoCliente = null;
        this.abrirConexion();
            try (PreparedStatement st = this.conexion.prepareStatement("SELECT * FROM VISTA_ANTICIPO_CLIENTE WHERE id_anticipo_c=?")) {
                st.setInt(1, anticipoClienteM.getId_anticipo_c());
            
                try (ResultSet rs = st.executeQuery()) {
                    while (rs.next()) {
                        anticipoCliente = (AnticipoCliente) extraerObject(rs);
                    }
                }
            }
        return anticipoCliente;
    }

    @Override
    public void actualizar(Object objeto) throws Exception {
        AnticipoCliente anticipoCliente = (AnticipoCliente) objeto;
        try{
            this.abrirConexion();
            PreparedStatement st= this.conexion.prepareStatement("UPDATE ANTICIPO_CLIENTE SET monto_anticipo = ? WHERE id_anticipo_c=?");
            st.setBigDecimal(1,anticipoCliente.getMonto_anticipo());
            st.setInt(2,anticipoCliente.getId_anticipo_c());
            st.executeUpdate();
        }catch(Exception e){
            System.out.println(e);
            throw e;
        }finally{
            this.cerrarConexion();
        }
    }

    @Override
    public void eliminar(Object objeto) throws Exception {
        AnticipoCliente anticipoCliente = (AnticipoCliente) objeto;
        try{
            this.abrirConexion();
            PreparedStatement st= this.conexion.prepareStatement("DELETE FROM ANTICIPO_CLIENTE WHERE id_anticipo_c = ?");
            st.setInt(1,anticipoCliente.getId_anticipo_c());
            st.executeUpdate();
        }catch(Exception e){
            System.out.println(e);
            throw e;
        }finally{
            this.cerrarConexion();
        }
    }

    @Override
    public <T> List buscar(String nombre_campo, String dato, String id_jefe, String rol) throws Exception {
        List<AnticipoCliente> anticipoClientes;
        String consulta;
        if(rol.equals("Administrador")){
            consulta = "SELECT * FROM VISTA_ANTICIPO_CLIENTE WHERE "+nombre_campo+" like ? AND id_jefe = ? ORDER BY fecha DESC";
        }else{
            consulta = "SELECT * FROM VISTA_ANTICIPO_CLIENTE WHERE "+nombre_campo+" like ? AND id_jefe = ? AND fecha = CURDATE() ORDER BY fecha DESC";
        }
        try{
            this.abrirConexion();
            try (PreparedStatement st = this.conexion.prepareStatement(consulta)) {
                st.setString(1, "%"+dato+"%");
                st.setString(2, id_jefe);
                anticipoClientes = new ArrayList();
                try (ResultSet rs = st.executeQuery()) {
                    while (rs.next()) {
                        AnticipoCliente anticipoCliente = (AnticipoCliente) extraerObject(rs);
                        anticipoClientes.add(anticipoCliente);
                    }
                }
            }
        }catch(Exception e){
            System.out.println(e);
            throw e;
        }finally{
            this.cerrarConexion();
        }
        return anticipoClientes;
    }
    
    @Override
    public Object extraerObject(ResultSet rs) throws SQLException {
        AnticipoCliente anticipoCliente = new AnticipoCliente();
        anticipoCliente.setId_anticipo_c(rs.getInt("id_anticipo_c"));
        anticipoCliente.setFecha(rs.getDate("fecha"));
        anticipoCliente.setId_cliente(rs.getString("id_cliente"));
        anticipoCliente.setCliente(rs.getString("cliente"));
        anticipoCliente.setId_empleado(rs.getString("id_empleado"));
        anticipoCliente.setEmpleado(rs.getString("empleado"));
        anticipoCliente.setMonto_anticipo(rs.getBigDecimal("monto_anticipo"));
        
        return anticipoCliente;
    }

    @Override
    public PreparedStatement cargarObject(PreparedStatement st, Object objeto) throws SQLException {
        AnticipoCliente anticipoCliente = (AnticipoCliente) objeto;
        st.setDate(1,anticipoCliente.getFecha());
        st.setString(2,anticipoCliente.getId_cliente());
        st.setString(3,anticipoCliente.getId_empleado());
        st.setBigDecimal(4,anticipoCliente.getMonto_anticipo());
        return st;
    }
}

