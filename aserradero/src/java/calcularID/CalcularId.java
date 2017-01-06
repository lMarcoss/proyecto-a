
package calcularID;

/**
 *
 * @author lmarcoss
 */
    
// Clase para calcular Id empleado, Id cliente, e Id proveedor y Id del prestador para prestamos a través del Id persona e id jefe o idAdministrador para el caso de IdPrestador
public class CalcularId {
    // Se concatena el id_persona + los 8 primeros caracteres del id_administrador
    public String CalcularId(String id_persona, String id_jefe) {
        String id;
        System.out.println("calcularID.CalcularIdECP.CalcularId()");
        id = id_persona+ id_jefe.substring(0,8);
        return id;
    }
    
    
}
