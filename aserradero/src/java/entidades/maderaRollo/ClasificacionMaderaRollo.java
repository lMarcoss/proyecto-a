package entidades.maderaRollo;

import java.math.BigDecimal;

/**
 *
 * @author Ricardo Cortés Cruz ->> ricardo.crts.crz@gmail.com
 */
public class ClasificacionMaderaRollo {

    public String id_proveedor;
    public String proveedor;
    public String id_jefe;
    public String clasificacion;
    public BigDecimal costo;

    public ClasificacionMaderaRollo() {

    }

    public ClasificacionMaderaRollo(String id_proveedor, String proveedor, String id_jefe, String clasificacion, BigDecimal costo) {
        this.id_proveedor = id_proveedor;
        this.proveedor = proveedor;
        this.id_jefe = id_jefe;
        this.clasificacion = clasificacion;
        this.costo = costo;
    }

    public void setId_proveedor(String id_proveedor) {
        this.id_proveedor = id_proveedor;
    }

    public void setProveedor(String proveedor) {
        this.proveedor = proveedor;
    }

    public void setId_jefe(String id_jefe) {
        this.id_jefe = id_jefe;
    }

    public void setClasificacion(String clasificacion) {
        this.clasificacion = clasificacion;
    }

    public void setCosto(BigDecimal costo) {
        this.costo = costo;
    }

    public String getId_proveedor() {
        return id_proveedor;
    }

    public String getProveedor() {
        return proveedor;
    }

    public String getId_jefe() {
        return id_jefe;
    }

    public String getClasificacion() {
        return clasificacion;
    }

    public BigDecimal getCosto() {
        return costo;
    }

}
