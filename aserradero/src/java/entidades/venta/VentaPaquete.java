package entidades.venta;

import java.math.BigDecimal;

/**
 *
 * @author lmarcoss
 */
public class VentaPaquete {

    private String id_administrador;
    private String id_venta;
    private int numero_paquete;
    private String id_madera;
    private int num_piezas;
    private BigDecimal volumen;
    private BigDecimal monto;
    private String tipo_madera;

    public VentaPaquete() {
    }

    public VentaPaquete(String id_administrador, String id_venta, int numero_paquete, String id_madera, int num_piezas, BigDecimal volumen, BigDecimal monto, String tipo_madera) {
        this.id_administrador = id_administrador;
        this.id_venta = id_venta;
        this.numero_paquete = numero_paquete;
        this.id_madera = id_madera;
        this.num_piezas = num_piezas;
        this.volumen = volumen;
        this.monto = monto;
        this.tipo_madera = tipo_madera;
    }

    public void setId_administrador(String id_administrador) {
        this.id_administrador = id_administrador;
    }

    public void setId_venta(String id_venta) {
        this.id_venta = id_venta;
    }

    public void setNumero_paquete(int numero_paquete) {
        this.numero_paquete = numero_paquete;
    }

    public void setId_madera(String id_madera) {
        this.id_madera = id_madera;
    }

    public void setNum_piezas(int num_piezas) {
        this.num_piezas = num_piezas;
    }

    public void setVolumen(BigDecimal volumen) {
        this.volumen = volumen;
    }

    public void setMonto(BigDecimal monto) {
        this.monto = monto;
    }

    public void setTipo_madera(String tipo_madera) {
        this.tipo_madera = tipo_madera;
    }

    public String getId_administrador() {
        return id_administrador;
    }

    public String getId_venta() {
        return id_venta;
    }

    public int getNumero_paquete() {
        return numero_paquete;
    }

    public String getId_madera() {
        return id_madera;
    }

    public int getNum_piezas() {
        return num_piezas;
    }

    public BigDecimal getVolumen() {
        return volumen;
    }

    public BigDecimal getMonto() {
        return monto;
    }

    public String getTipo_madera() {
        return tipo_madera;
    }

}
