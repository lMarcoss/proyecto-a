package entidades.venta;

import java.math.BigDecimal;
import java.sql.Date;

/**
 *
 * @author lmarcoss
 */
public class Venta {

    public String id_venta;
    public Date fecha;
    public String id_cliente;
    public String cliente;
    public String id_empleado;
    public String empleado;
    public String estatus;
    private String tipo_venta;
    private BigDecimal pago;
    private String id_jefe;
    private BigDecimal monto; // Es el único atributo que se extrae de los tipos de ventas
    private String ticket; // Es el único atributo que se extrae de los tipos de ventas

    public Venta() {
    }

    public Venta(String id_venta, Date fecha, String id_cliente, String cliente, String id_empleado, String empleado, String estatus, String tipo_venta, BigDecimal pago, String id_jefe, BigDecimal monto, String ticket) {
        this.id_venta = id_venta;
        this.fecha = fecha;
        this.id_cliente = id_cliente;
        this.cliente = cliente;
        this.id_empleado = id_empleado;
        this.empleado = empleado;
        this.estatus = estatus;
        this.tipo_venta = tipo_venta;
        this.pago = pago;
        this.id_jefe = id_jefe;
        this.monto = monto;
        this.ticket = ticket;
    }

    public void setId_venta(String id_venta) {
        this.id_venta = id_venta;
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

    public void setEstatus(String estatus) {
        this.estatus = estatus;
    }

    public void setTipo_venta(String tipo_venta) {
        this.tipo_venta = tipo_venta;
    }

    public void setPago(BigDecimal pago) {
        this.pago = pago;
    }

    public void setId_jefe(String id_jefe) {
        this.id_jefe = id_jefe;
    }

    public void setMonto(BigDecimal monto) {
        this.monto = monto;
    }

    public void setTicket(String ticket) {
        this.ticket = ticket;
    }

    public String getId_venta() {
        return id_venta;
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

    public String getEstatus() {
        return estatus;
    }

    public String getTipo_venta() {
        return tipo_venta;
    }

    public BigDecimal getPago() {
        return pago;
    }

    public String getId_jefe() {
        return id_jefe;
    }

    public BigDecimal getMonto() {
        return monto;
    }

    public String getTicket() {
        return ticket;
    }

}
