/*
* Datos necesarios para pago compra
 */
package entidades.maderaRollo;

import java.math.BigDecimal;

/**
 *
 * @author lmarcoss
 */
public class CuentaPago {

    private String id_proveedor;
    private String proveedor;
    private BigDecimal monto_total_madera;
    private BigDecimal cuenta_por_cobrar;
    private BigDecimal cuenta_por_pagar;

    public CuentaPago() {
    }

    public CuentaPago(String id_proveedor, String proveedor, BigDecimal monto_total_madera, BigDecimal cuenta_por_cobrar, BigDecimal cuenta_por_pagar) {
        this.id_proveedor = id_proveedor;
        this.proveedor = proveedor;
        this.monto_total_madera = monto_total_madera;
        this.cuenta_por_cobrar = cuenta_por_cobrar;
        this.cuenta_por_pagar = cuenta_por_pagar;
    }

    public void setId_proveedor(String id_proveedor) {
        this.id_proveedor = id_proveedor;
    }

    public void setProveedor(String proveedor) {
        this.proveedor = proveedor;
    }

    public void setMonto_total_madera(BigDecimal monto_total_madera) {
        this.monto_total_madera = monto_total_madera;
    }

    public void setCuenta_por_cobrar(BigDecimal cuenta_por_cobrar) {
        this.cuenta_por_cobrar = cuenta_por_cobrar;
    }

    public void setCuenta_por_pagar(BigDecimal cuenta_por_pagar) {
        this.cuenta_por_pagar = cuenta_por_pagar;
    }

    public String getId_proveedor() {
        return id_proveedor;
    }

    public String getProveedor() {
        return proveedor;
    }

    public BigDecimal getMonto_total_madera() {
        return monto_total_madera;
    }

    public BigDecimal getCuenta_por_cobrar() {
        return cuenta_por_cobrar;
    }

    public BigDecimal getCuenta_por_pagar() {
        return cuenta_por_pagar;
    }

}
