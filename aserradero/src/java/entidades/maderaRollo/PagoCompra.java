package entidades.maderaRollo;

import java.math.BigDecimal;
import java.sql.Date;

/**
 *
 * @author lmarcoss
 */
public class PagoCompra {
    private int id_pago;
    private Date fecha;
    private String id_proveedor;
    private String proveedor;
    private String id_administrador;
    private BigDecimal monto_pago;
    private BigDecimal monto_por_pagar;
    private BigDecimal monto_por_cobrar;

    public PagoCompra() {
    }

    public PagoCompra(int id_pago, Date fecha, String id_proveedor, String proveedor, String id_administrador, BigDecimal monto_pago, BigDecimal monto_por_pagar, BigDecimal monto_por_cobrar) {
        this.id_pago = id_pago;
        this.fecha = fecha;
        this.id_proveedor = id_proveedor;
        this.proveedor = proveedor;
        this.id_administrador = id_administrador;
        this.monto_pago = monto_pago;
        this.monto_por_pagar = monto_por_pagar;
        this.monto_por_cobrar = monto_por_cobrar;
    }

    public void setId_pago(int id_pago) {
        this.id_pago = id_pago;
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

    public void setId_administrador(String id_administrador) {
        this.id_administrador = id_administrador;
    }

    public void setMonto_pago(BigDecimal monto_pago) {
        this.monto_pago = monto_pago;
    }

    public void setMonto_por_pagar(BigDecimal monto_por_pagar) {
        this.monto_por_pagar = monto_por_pagar;
    }

    public void setMonto_por_cobrar(BigDecimal monto_por_cobrar) {
        this.monto_por_cobrar = monto_por_cobrar;
    }

    public int getId_pago() {
        return id_pago;
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

    public String getId_administrador() {
        return id_administrador;
    }

    public BigDecimal getMonto_pago() {
        return monto_pago;
    }

    public BigDecimal getMonto_por_pagar() {
        return monto_por_pagar;
    }

    public BigDecimal getMonto_por_cobrar() {
        return monto_por_cobrar;
    }
    
}
