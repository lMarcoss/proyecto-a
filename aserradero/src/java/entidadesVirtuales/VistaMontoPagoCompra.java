package entidadesVirtuales;

import java.math.BigDecimal;

/**
 *
 * @author lmarcoss
 */
public class VistaMontoPagoCompra {

    private String id_proveedor;
    private String proveedor;
    private BigDecimal monto_por_pagar;
    private BigDecimal cuenta_por_cobrar;
    private String id_administrador;

    public VistaMontoPagoCompra() {
    }

    public VistaMontoPagoCompra(String id_proveedor, String proveedor, BigDecimal monto_por_pagar, BigDecimal cuenta_por_cobrar, String id_administrador) {
        this.id_proveedor = id_proveedor;
        this.proveedor = proveedor;
        this.monto_por_pagar = monto_por_pagar;
        this.cuenta_por_cobrar = cuenta_por_cobrar;
        this.id_administrador = id_administrador;
    }

    public void setId_proveedor(String id_proveedor) {
        this.id_proveedor = id_proveedor;
    }

    public void setProveedor(String proveedor) {
        this.proveedor = proveedor;
    }

    public void setMonto_por_pagar(BigDecimal monto_por_pagar) {
        this.monto_por_pagar = monto_por_pagar;
    }

    public void setId_administrador(String id_administrador) {
        this.id_administrador = id_administrador;
    }

    public void setCuenta_por_cobrar(BigDecimal cuenta_por_cobrar) {
        this.cuenta_por_cobrar = cuenta_por_cobrar;
    }

    public String getId_proveedor() {
        return id_proveedor;
    }

    public String getProveedor() {
        return proveedor;
    }

    public BigDecimal getMonto_por_pagar() {
        return monto_por_pagar;
    }

    public String getId_administrador() {
        return id_administrador;
    }

    public BigDecimal getCuenta_por_cobrar() {
        return cuenta_por_cobrar;
    }
}
