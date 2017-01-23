package entidades.gasto;

import java.math.BigDecimal;

/**
 *
 * @author Ricardo Cortés Cruz ->> ricardo.crts.crz@gmail.com
 * Modificado por: lmarcoss
 */

public class PagoRenta{
    private String id_pago_renta;
    private String fecha;
    private String nombre_persona;
    private String id_empleado;
    private String empleado;
    private String id_jefe;
    private BigDecimal monto;
    private String observacion;

   public PagoRenta(){

   }

    public PagoRenta(String id_pago_renta, String fecha, String nombre_persona, String id_empleado, String empleado, String id_jefe, BigDecimal monto, String observacion) {
        this.id_pago_renta = id_pago_renta;
        this.fecha = fecha;
        this.nombre_persona = nombre_persona;
        this.id_empleado = id_empleado;
        this.empleado = empleado;
        this.id_jefe = id_jefe;
        this.monto = monto;
        this.observacion = observacion;
    }

    public void setId_pago_renta(String id_pago_renta) {
        this.id_pago_renta = id_pago_renta;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public void setNombre_persona(String nombre_persona) {
        this.nombre_persona = nombre_persona;
    }

    public void setId_empleado(String id_empleado) {
        this.id_empleado = id_empleado;
    }

    public void setEmpleado(String empleado) {
        this.empleado = empleado;
    }

    public void setId_jefe(String id_jefe) {
        this.id_jefe = id_jefe;
    }

    public void setMonto(BigDecimal monto) {
        this.monto = monto;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public String getId_pago_renta() {
        return id_pago_renta;
    }

    public String getFecha() {
        return fecha;
    }

    public String getNombre_persona() {
        return nombre_persona;
    }

    public String getId_empleado() {
        return id_empleado;
    }

    public String getEmpleado() {
        return empleado;
    }

    public String getId_jefe() {
        return id_jefe;
    }

    public BigDecimal getMonto() {
        return monto;
    }

    public String getObservacion() {
        return observacion;
    }
   
 }
