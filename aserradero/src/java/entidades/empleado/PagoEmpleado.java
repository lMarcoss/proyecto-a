package entidades.empleado;

import java.math.BigDecimal;
import java.sql.Date;

/**
 *
 * @author lmarcoss
 */
public class PagoEmpleado {
    private int id_pago_empleado;
    private Date fecha;
    private String id_empleado;
    private String empleado;
    private BigDecimal monto;
    private String observacion;

    public PagoEmpleado() {
    }

    public PagoEmpleado(int id_pago_empleado, Date fecha, String id_empleado, String empleado, BigDecimal monto, String observacion) {
        this.id_pago_empleado = id_pago_empleado;
        this.fecha = fecha;
        this.id_empleado = id_empleado;
        this.empleado = empleado;
        this.monto = monto;
        this.observacion = observacion;
    }

    public void setId_pago_empleado(int id_pago_empleado) {
        this.id_pago_empleado = id_pago_empleado;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public void setId_empleado(String id_empleado) {
        this.id_empleado = id_empleado;
    }

    public void setEmpleado(String empleado) {
        this.empleado = empleado;
    }

    public void setMonto(BigDecimal monto) {
        this.monto = monto;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }
    
    public int getId_pago_empleado() {
        return id_pago_empleado;
    }

    public Date getFecha() {
        return fecha;
    }

    public String getId_empleado() {
        return id_empleado;
    }

    public String getEmpleado() {
        return empleado;
    }

    public BigDecimal getMonto() {
        return monto;
    }

    public String getObservacion() {
        return observacion;
    }
    
}
