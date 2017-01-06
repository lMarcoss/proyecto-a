package entidades.anticipo;

import java.math.BigDecimal;
import java.sql.Date;

/**
 *
 * @author lmarcoss
 */
public class AnticipoCliente {
    private int id_anticipo_c;
    private Date fecha;
    private String id_cliente;
    private String cliente;
    private String id_empleado;
    private String empleado;
    private BigDecimal monto_anticipo;

    public AnticipoCliente() {
    }

    public AnticipoCliente(int id_anticipo_c, Date fecha, String id_cliente, String cliente, String id_empleado, String empleado, BigDecimal monto_anticipo) {
        this.id_anticipo_c = id_anticipo_c;
        this.fecha = fecha;
        this.id_cliente = id_cliente;
        this.cliente = cliente;
        this.id_empleado = id_empleado;
        this.empleado = empleado;
        this.monto_anticipo = monto_anticipo;
    }

    public void setId_anticipo_c(int id_anticipo_c) {
        this.id_anticipo_c = id_anticipo_c;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public void setId_cliente(String id_cliente) {
        this.id_cliente = id_cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
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

    public int getId_anticipo_c() {
        return id_anticipo_c;
    }

    public Date getFecha() {
        return fecha;
    }

    public String getId_cliente() {
        return id_cliente;
    }

    public String getCliente() {
        return cliente;
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
