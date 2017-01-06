package entidades.maderaAserrada;

import java.math.BigDecimal;

/**
 *
 * @author lmarcoss
 */
public class InventarioMaderaAserrada {
    private String id_madera;
    private int num_piezas;
    private BigDecimal volumen_unitario;
    private BigDecimal volumen_total;
    private BigDecimal costo_por_volumen;
    private BigDecimal costo_total;

    public InventarioMaderaAserrada() {
    }

    public InventarioMaderaAserrada(String id_madera, int num_piezas, BigDecimal volumen_unitario, BigDecimal volumen_total, BigDecimal costo_por_volumen, BigDecimal costo_total) {
        this.id_madera = id_madera;
        this.num_piezas = num_piezas;
        this.volumen_unitario = volumen_unitario;
        this.volumen_total = volumen_total;
        this.costo_por_volumen = costo_por_volumen;
        this.costo_total = costo_total;
    }

    public void setId_madera(String id_madera) {
        this.id_madera = id_madera;
    }

    public void setNum_piezas(int num_piezas) {
        this.num_piezas = num_piezas;
    }

    public void setVolumen_unitario(BigDecimal volumen_unitario) {
        this.volumen_unitario = volumen_unitario;
    }

    public void setVolumen_total(BigDecimal volumen_total) {
        this.volumen_total = volumen_total;
    }

    public void setCosto_por_volumen(BigDecimal costo_por_volumen) {
        this.costo_por_volumen = costo_por_volumen;
    }

    public void setCosto_total(BigDecimal costo_total) {
        this.costo_total = costo_total;
    }

    public String getId_madera() {
        return id_madera;
    }

    public int getNum_piezas() {
        return num_piezas;
    }

    public BigDecimal getVolumen_unitario() {
        return volumen_unitario;
    }

    public BigDecimal getVolumen_total() {
        return volumen_total;
    }

    public BigDecimal getCosto_por_volumen() {
        return costo_por_volumen;
    }

    public BigDecimal getCosto_total() {
        return costo_total;
    }

        
}
