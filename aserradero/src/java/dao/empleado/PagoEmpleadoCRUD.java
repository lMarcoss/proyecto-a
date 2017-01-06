
package dao.empleado;

import dao.Conexion;
import entidades.empleado.PagoEmpleado;
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
public class PagoEmpleadoCRUD extends Conexion implements OperacionesCRUD{

    @Override
    public void registrar(Object objeto) throws Exception{
        PagoEmpleado pagoEmpleado = (PagoEmpleado) objeto;
        try{
            this.abrirConexion();
            PreparedStatement st= this.conexion.prepareStatement("INSERT INTO PAGO_EMPLEADO (fecha,id_empleado,monto,observacion) VALUES (?,?,?,?)");
            st = cargarObject(st, pagoEmpleado);
            st.executeUpdate();
        }catch(Exception e){
            System.out.println(e);
            throw e;
        }finally{
            this.cerrarConexion();
        } 
    }

    @Override
    public <T> List listar(String id_jefe) throws Exception{
        List<PagoEmpleado> pagoEmpleadoes;
        try{
            this.abrirConexion();
            try (PreparedStatement st = this.conexion.prepareStatement("SELECT * FROM VISTA_PAGO_EMPLEADO WHERE id_jefe = ?")) {
                st.setString(1, id_jefe);
                pagoEmpleadoes = new ArrayList();
                try (ResultSet rs = st.executeQuery()) {
                    while (rs.next()) {
                        PagoEmpleado pagoEmpleado = (PagoEmpleado) extraerObject(rs);
                        pagoEmpleadoes.add(pagoEmpleado);
                    }
                }
            }catch(Exception e){
                pagoEmpleadoes = null;
                System.out.println(e);
            }
        }catch(Exception e){
            System.out.println(e);
            throw e;
        }finally{
            this.cerrarConexion();
        } 
        return pagoEmpleadoes;
    }

    @Override
    public Object modificar(Object objeto) throws Exception{
        PagoEmpleado pagoEmpleadoM = (PagoEmpleado) objeto;
        PagoEmpleado pagoEmpleado = null;
        this.abrirConexion();
            try (PreparedStatement st = this.conexion.prepareStatement("SELECT * FROM VISTA_PAGO_EMPLEADO WHERE id_pago_empleado = ?")) {
                st.setInt(1, pagoEmpleadoM.getId_pago_empleado());
                try (ResultSet rs = st.executeQuery()) {
                    while (rs.next()) {
                        pagoEmpleado = (PagoEmpleado) extraerObject(rs);
                    }
                }
            }
        return pagoEmpleado;
    }

    @Override
    public void actualizar(Object objeto) throws Exception{
        PagoEmpleado pagoEmpleado = (PagoEmpleado) objeto;
        try{
            this.abrirConexion();
            PreparedStatement st= this.conexion.prepareStatement("UPDATE PAGO_EMPLEADO SET monto = ?,observacion= ? WHERE id_pago_empleado = ?");
            st.setBigDecimal(1,pagoEmpleado.getMonto());
            st.setString(2,pagoEmpleado.getObservacion());
            st.setInt(3,pagoEmpleado.getId_pago_empleado());
            st.executeUpdate();
        }catch(Exception e){
            System.out.println(e);
            throw e;
        }finally{
            this.cerrarConexion();
        } 
    }

    @Override
    public void eliminar(Object objeto) throws Exception{
        PagoEmpleado pagoEmpleado = (PagoEmpleado) objeto;
        try{
            this.abrirConexion();
            PreparedStatement st= this.conexion.prepareStatement("DELETE FROM PAGO_EMPLEADO WHERE id_pago_empleado = ?");
            st.setInt(1,pagoEmpleado.getId_pago_empleado());
            st.executeUpdate();
        }catch(Exception e){
            System.out.println(e);
            throw e;
        }finally{
            this.cerrarConexion();
        } 
    }

    @Override
    public <T> List buscar(String nombre_campo, String dato, String id_jefe) throws Exception{
        List<PagoEmpleado> pagoEmpleadoes;
        try{
            this.abrirConexion();
            try (PreparedStatement st = this.conexion.prepareStatement("SELECT * FROM VISTA_PAGO_EMPLEADO WHERE "+nombre_campo+" like ? AND id_jefe = ?")) {
                st.setString(1, "%"+dato+"%");
                st.setString(2, id_jefe);
                pagoEmpleadoes = new ArrayList();
                try (ResultSet rs = st.executeQuery()) {
                    while (rs.next()) {
                        PagoEmpleado pagoEmpleado = (PagoEmpleado) extraerObject(rs);
                        pagoEmpleadoes.add(pagoEmpleado);
                    }
                }
            }
        }catch(Exception e){
            System.out.println(e);
            throw e;
        }finally{
            this.cerrarConexion();
        }
        return pagoEmpleadoes;
    }

    @Override
    public Object extraerObject(ResultSet rs) throws SQLException {
        PagoEmpleado pagoEmpleado = new PagoEmpleado();
        pagoEmpleado.setId_pago_empleado(rs.getInt("id_pago_empleado"));
        pagoEmpleado.setFecha(rs.getDate("fecha"));
        pagoEmpleado.setId_empleado(rs.getString("id_empleado"));
        pagoEmpleado.setEmpleado(rs.getString("empleado"));
        pagoEmpleado.setMonto(rs.getBigDecimal("monto"));
        pagoEmpleado.setObservacion(rs.getString("observacion"));
        return pagoEmpleado;
    }

    @Override
    public PreparedStatement cargarObject(PreparedStatement st, Object objecto) throws SQLException {
        PagoEmpleado pagoEmpleado = (PagoEmpleado) objecto;
        st.setDate(1,pagoEmpleado.getFecha());    
        st.setString(2,pagoEmpleado.getId_empleado());
        st.setBigDecimal(3,pagoEmpleado.getMonto());
        st.setString(4,pagoEmpleado.getObservacion());
        return st;
    }
}


