package entidades.prestamo;

import java.math.BigDecimal;
    import java.sql.Date;

/**
 *
 * @author lmarcoss
 */
public class Prestamo {
    private int id_prestamo;
    private Date fecha; 			
    private String id_prestador;
    private String prestador;
    private String id_empleado;
    private String empleado;
    private String id_administrador;
    private BigDecimal monto_prestamo;
    private int interes;
    private BigDecimal interes_mesual;
    private BigDecimal monto_pagado;
    private BigDecimal monto_por_pagar;

    public Prestamo() {
    }

    public Prestamo(int id_prestamo, Date fecha, String id_prestador, String prestador, String id_empleado, String empleado, String id_administrador, BigDecimal monto_prestamo, int interes, BigDecimal interes_mesual, BigDecimal monto_pagado, BigDecimal monto_por_pagar) {
        this.id_prestamo = id_prestamo;
        this.fecha = fecha;
        this.id_prestador = id_prestador;
        this.prestador = prestador;
        this.id_empleado = id_empleado;
        this.empleado = empleado;
        this.id_administrador = id_administrador;
        this.monto_prestamo = monto_prestamo;
        this.interes = interes;
        this.interes_mesual = interes_mesual;
        this.monto_pagado = monto_pagado;
        this.monto_por_pagar = monto_por_pagar;
    }

    public void setId_prestamo(int id_prestamo) {
        this.id_prestamo = id_prestamo;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public void setId_prestador(String id_prestador) {
        this.id_prestador = id_prestador;
    }

    public void setPrestador(String prestador) {
        this.prestador = prestador;
    }

    public void setId_empleado(String id_empleado) {
        this.id_empleado = id_empleado;
    }

    public void setEmpleado(String empleado) {
        this.empleado = empleado;
    }

    public void setId_administrador(String id_administrador) {
        this.id_administrador = id_administrador;
    }

    public void setMonto_prestamo(BigDecimal monto_prestamo) {
        this.monto_prestamo = monto_prestamo;
    }

    public void setInteres(int interes) {
        this.interes = interes;
    }

    public void setInteres_mesual(BigDecimal interes_mesual) {
        this.interes_mesual = interes_mesual;
    }

    public void setMonto_pagado(BigDecimal monto_pagado) {
        this.monto_pagado = monto_pagado;
    }

    public void setMonto_por_pagar(BigDecimal monto_por_pagar) {
        this.monto_por_pagar = monto_por_pagar;
    }

    public int getId_prestamo() {
        return id_prestamo;
    }

    public Date getFecha() {
        return fecha;
    }

    public String getId_prestador() {
        return id_prestador;
    }

    public String getPrestador() {
        return prestador;
    }

    public String getId_empleado() {
        return id_empleado;
    }

    public String getEmpleado() {
        return empleado;
    }

    public String getId_administrador() {
        return id_administrador;
    }

    public BigDecimal getMonto_prestamo() {
        return monto_prestamo;
    }

    public int getInteres() {
        return interes;
    }

    public BigDecimal getInteres_mesual() {
        return interes_mesual;
    }

    public BigDecimal getMonto_pagado() {
        return monto_pagado;
    }

    public BigDecimal getMonto_por_pagar() {
        return monto_por_pagar;
    }    
}
