package entidades.anticipo;

import java.math.BigDecimal;
import java.sql.Date;

/**
 *
 * @author lmarcoss
 */
public class AnticipoProveedor {
    private int id_anticipo_p;
    private Date fecha;
    private String id_proveedor;
    private String proveedor;
    private String id_empleado;
    private String empleado;
    private BigDecimal monto_anticipo;

    public AnticipoProveedor() {
    }

    public AnticipoProveedor(int id_anticipo_p, Date fecha, String id_proveedor, String proveedor, String id_empleado, String empleado, BigDecimal monto_anticipo) {
        this.id_anticipo_p = id_anticipo_p;
        this.fecha = fecha;
        this.id_proveedor = id_proveedor;
        this.proveedor = proveedor;
        this.id_empleado = id_empleado;
        this.empleado = empleado;
        this.monto_anticipo = monto_anticipo;
    }

    public void setId_anticipo_p(int id_anticipo_p) {
        this.id_anticipo_p = id_anticipo_p;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public void setId_proveedor(String id_proveedor) {
        this.id_proveedor = id_proveedor;
    }

    public void setProveedor(String proveedor) {
        this.proveedor = proveedor;
    }

    public void setId_empleado(String id_empleado) {
        this.id_empleado = id_empleado;
    }

    public void setEmpleado(String empleado) {
        this.empleado = empleado;
    }

    public void setMonto_anticipo(BigDecimal monto_anticipo) {
        this.monto_anticipo = monto_anticipo;
    }

    public int getId_anticipo_p() {
        return id_anticipo_p;
    }

    public Date getFecha() {
        return fecha;
    }

    public String getId_proveedor() {
        return id_proveedor;
    }

    public String getProveedor() {
        return proveedor;
    }

    public String getId_empleado() {
        return id_empleado;
    }

    public String getEmpleado() {
        return empleado;
    }

    public BigDecimal getMonto_anticipo() {
        return monto_anticipo;
    }
    
}
