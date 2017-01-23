package entidades.registros;

/**
 *
 * @author lmarcoss
 */
public class Proveedor {

    private String id_proveedor;
    private String id_persona;
    private String proveedor;
    private String id_jefe;

    public Proveedor() {
    }

    public Proveedor(String id_proveedor, String id_persona, String proveedor, String id_jefe) {
        this.id_proveedor = id_proveedor;
        this.id_persona = id_persona;
        this.proveedor = proveedor;
        this.id_jefe = id_jefe;
    }

    public void setId_proveedor(String id_proveedor) {
        this.id_proveedor = id_proveedor;
    }

    public void setId_persona(String id_persona) {
        this.id_persona = id_persona;
    }

    public void setProveedor(String proveedor) {
        this.proveedor = proveedor;
    }

    public void setId_jefe(String id_jefe) {
        this.id_jefe = id_jefe;
    }

    public String getId_proveedor() {
        return id_proveedor;
    }

    public String getId_persona() {
        return id_persona;
    }

    public String getProveedor() {
        return proveedor;
    }

    public String getId_jefe() {
        return id_jefe;
    }

}
