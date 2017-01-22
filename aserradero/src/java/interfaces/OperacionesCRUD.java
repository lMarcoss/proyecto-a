
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
    
    /*Objeto genérico*/
    //Objeto a modificar: registro
    public Object modificar(Object objeto) throws Exception; 
    // Actualizar un objeto: registro
    public void actualizar(Object objeto) throws Exception;
    //Eliminar objeto: registro
    public void eliminar(Object objeto) throws Exception;
    
    
}
