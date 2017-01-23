package entidades.registros;

/**
 *
 * @author lmarcoss
 */
public class Cliente {

    private String id_cliente;
    private String id_persona;
    private String cliente;
    private String id_jefe;

    public Cliente() {
    }

    public Cliente(String id_cliente, String id_persona, String cliente, String id_jefe) {
        this.id_cliente = id_cliente;
        this.id_persona = id_persona;
        this.cliente = cliente;
        this.id_jefe = id_jefe;
    }

    public void setId_cliente(String id_cliente) {
        this.id_cliente = id_cliente;
    }

    public void setId_persona(String id_persona) {
        this.id_persona = id_persona;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public void setId_jefe(String id_jefe) {
        this.id_jefe = id_jefe;
    }

    public String getId_cliente() {
        return id_cliente;
    }

    public String getId_persona() {
        return id_persona;
    }

    public String getCliente() {
        return cliente;
    }

    public String getId_jefe() {
        return id_jefe;
    }

}
