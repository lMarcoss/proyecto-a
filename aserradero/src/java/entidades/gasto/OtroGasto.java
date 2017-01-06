package entidades.gasto;

import java.math.BigDecimal;
import java.sql.Date;

/**
 *
 * @author Ricardo Cortés Cruz ->> ricardo.crts.crz@gmail.com
 */
public class OtroGasto {

    private int id_gasto;
    private Date fecha;
    private String id_empleado;
    private String empleado;
    private String nombre_gasto;
    private BigDecimal monto;
    private String observacion;

    public OtroGasto() {

    }

    public OtroGasto(int id_gasto, Date fecha, String id_empleado, String empleado, String nombre_gasto, BigDecimal monto, String observacion) {
        this.id_gasto = id_gasto;
        this.fecha = fecha;
        this.id_empleado = id_empleado;
        this.empleado = empleado;
        this.nombre_gasto = nombre_gasto;
        this.monto = monto;
        this.observacion = observacion;
    }

    public void setId_gasto(int id_gasto) {
        this.id_gasto = id_gasto;
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

    public void setNombre_gasto(String nombre_gasto) {
        this.nombre_gasto = nombre_gasto;
    }

    public void setMonto(BigDecimal monto) {
        this.monto = monto;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public int getId_gasto() {
        return id_gasto;
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

    public String getNombre_gasto() {
        return nombre_gasto;
    }

    public BigDecimal getMonto() {
        return monto;
    }

    public String getObservacion() {
        return observacion;
    }

}
