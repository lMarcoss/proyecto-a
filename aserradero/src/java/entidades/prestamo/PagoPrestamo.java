package entidades.prestamo;

import java.math.BigDecimal;
import java.sql.Date;

/**
 *
 * @author lmarcoss
 */
public class PagoPrestamo {
    private int id_pago;
    private int id_prestamo;
    private Date fecha;
    private String id_administrador;
    private String id_empleado;
    private String empleado;
    private String id_prestador;
    private String prestador;
    private BigDecimal monto_prestamo;
    private BigDecimal monto_pago;
    private BigDecimal monto_por_pagar;

    public PagoPrestamo() {
    }

    public PagoPrestamo(int id_pago, int id_prestamo, Date fecha, String id_administrador, String id_empleado, String empleado, String id_prestador, String prestador, BigDecimal monto_prestamo, BigDecimal monto_pago, BigDecimal monto_por_pagar) {
        this.id_pago = id_pago;
        this.id_prestamo = id_prestamo;
        this.fecha = fecha;
        this.id_administrador = id_administrador;
        this.id_empleado = id_empleado;
        this.empleado = empleado;
        this.id_prestador = id_prestador;
        this.prestador = prestador;
        this.monto_prestamo = monto_prestamo;
        this.monto_pago = monto_pago;
        this.monto_por_pagar = monto_por_pagar;
    }

    public void setId_pago(int id_pago) {
        this.id_pago = id_pago;
    }

    public void setId_prestamo(int id_prestamo) {
        this.id_prestamo = id_prestamo;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public void setId_administrador(String id_administrador) {
        this.id_administrador = id_administrador;
    }

    public void setId_empleado(String id_empleado) {
        this.id_empleado = id_empleado;
    }

    public void setEmpleado(String empleado) {
        this.empleado = empleado;
    }

    public void setId_prestador(String id_prestador) {
        this.id_prestador = id_prestador;
    }

    public void setPrestador(String prestador) {
        this.prestador = prestador;
    }

    public void setMonto_prestamo(BigDecimal monto_prestamo) {
        this.monto_prestamo = monto_prestamo;
    }

    public void setMonto_pago(BigDecimal monto_pago) {
        this.monto_pago = monto_pago;
    }

    public void setMonto_por_pagar(BigDecimal monto_por_pagar) {
        this.monto_por_pagar = monto_por_pagar;
    }

    public int getId_pago() {
        return id_pago;
    }

    public int getId_prestamo() {
        return id_prestamo;
    }

    public Date getFecha() {
        return fecha;
    }

    public String getId_administrador() {
        return id_administrador;
    }

    public String getId_empleado() {
        return id_empleado;
    }

    public String getEmpleado() {
        return empleado;
    }

    public String getId_prestador() {
        return id_prestador;
    }

    public String getPrestador() {
        return prestador;
    }

    public BigDecimal getMonto_prestamo() {
        return monto_prestamo;
    }

    public BigDecimal getMonto_pago() {
        return monto_pago;
    }

    public BigDecimal getMonto_por_pagar() {
        return monto_por_pagar;
    }
    
}
