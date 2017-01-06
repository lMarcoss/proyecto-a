package entidades.venta;

import java.math.BigDecimal;
import java.sql.Date;

/**
 *
 * @author lmarcoss
 */
public class VentaExtra {
    private Date fecha;
    private String id_venta;
    private String id_cliente;
    private String cliente;
    private String direccion_cliente;
    private String id_empleado;
    private String empleado;
    private String id_jefe;
    private String estatus;
    private String tipo;
    private BigDecimal monto;
    private String observacion;

    public VentaExtra() {
    }

    public VentaExtra(String id_venta, String tipo, BigDecimal monto, String observacion) {
        this.id_venta = id_venta;
        this.tipo = tipo;
        this.monto = monto;
        this.observacion = observacion;
    }

    public VentaExtra(Date fecha, String id_venta, String id_cliente, String cliente, String direccion_cliente, String id_empleado, String empleado, String id_jefe, String estatus, String tipo, BigDecimal monto, String observacion) {
        this.fecha = fecha;
        this.id_venta = id_venta;
        this.id_cliente = id_cliente;
        this.cliente = cliente;
        this.direccion_cliente = direccion_cliente;
        this.id_empleado = id_empleado;
        this.empleado = empleado;
        this.id_jefe = id_jefe;
        this.estatus = estatus;
        this.tipo = tipo;
        this.monto = monto;
        this.observacion = observacion;
    }
    

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public void setId_venta(String id_venta) {
        this.id_venta = id_venta;
    }

    public void setId_cliente(String id_cliente) {
        this.id_cliente = id_cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public void setDireccion_cliente(String direccion_cliente) {
        this.direccion_cliente = direccion_cliente;
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

    public void setEstatus(String estatus) {
        this.estatus = estatus;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public void setMonto(BigDecimal monto) {
        this.monto = monto;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public Date getFecha() {
        return fecha;
    }

    public String getId_venta() {
        return id_venta;
    }

    public String getId_cliente() {
        return id_cliente;
    }

    public String getCliente() {
        return cliente;
    }

    public String getDireccion_cliente() {
        return direccion_cliente;
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

    public String getEstatus() {
        return estatus;
    }

    public String getTipo() {
        return tipo;
    }

    public BigDecimal getMonto() {
        return monto;
    }

    public String getObservacion() {
        return observacion;
    }

    
}
