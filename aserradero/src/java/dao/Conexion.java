package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author lmarcoss
 */
public class Conexion {
    
    protected Connection conexion;
    //JDBC driver nombre y base de datos url
    private final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    private final String DB_URL = "jdbc:mysql://localhost/aserradero";
    
    
    // base de datos credenciales
    private final String USER = "root";
    private final String PASSWORD = "";
    
    public void abrirConexion() throws Exception{
        try{
            Class.forName(JDBC_DRIVER);
            conexion = DriverManager.getConnection(DB_URL,USER,PASSWORD);
        }catch(SQLException e){
            throw e;
        }
    }
    
    public void cerrarConexion() throws SQLException{
        if(conexion != null){
            conexion.close();
        }
    }
}
