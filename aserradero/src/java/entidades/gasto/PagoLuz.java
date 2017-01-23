package entidades.gasto;

import java.math.BigDecimal;

/**
 *
 * @author Ricardo Cortés Cruz ->> ricardo.crts.crz@gmail.com
 */

public class PagoLuz{
    private String id_pago_luz;
    private String fecha;
    private String id_empleado;
    private String empleado;
    private BigDecimal monto;
    private String observacion;

    public PagoLuz(){

    }

    public PagoLuz(String id_pago_luz, String fecha, String id_empleado, String empleado, BigDecimal monto, String observacion) {
        this.id_pago_luz = id_pago_luz;
        this.fecha = fecha;
        this.id_empleado = id_empleado;
        this.empleado = empleado;
        this.monto = monto;
        this.observacion = observacion;
    }

    public void setId_pago_luz(String id_pago_luz) {
        this.id_pago_luz = id_pago_luz;
    }

    public void setFecha(String fecha) {
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

    public String getId_pago_luz() {
        return id_pago_luz;
    }

    public String getFecha() {
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
