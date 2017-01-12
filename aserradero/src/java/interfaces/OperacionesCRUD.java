
package interfaces;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author lmarcoss
 */
public interface OperacionesCRUD{
    //Métodos para registrar un objeto
    public void registrar(Object objeto) throws Exception;
    public PreparedStatement cargarObject(PreparedStatement st, Object objeto) throws SQLException;
    
    //Método genérico: Devolver una lista de cualquier tipo de objeto
    public abstract <T> List listar(String id_jefe,String rol) throws Exception;
    public Object extraerObject(ResultSet rs) throws SQLException;
    
    //Devuelve un objeto del tipo de objeto que recibe: Objeto genérico
    public Object modificar(Object objeto) throws Exception;
    public void actualizar(Object objeto) throws Exception;
    public void eliminar(Object objeto) throws Exception;
    
    //Método genérico: Puede devolver una lista de cualquier tipo de objeto
    public <T> List buscar(String nombre_campo, String dato, String id_jefe, String rol) throws Exception;
    
    
}
