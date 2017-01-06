package ticketVenta;

import java.math.BigDecimal;

/**
 *
 * @author lmarcoss
 */
public class Madera {
    private String id_madera;
    private String  grueso_f;
    private String  ancho_f;
    private String  largo_f;
    private BigDecimal  volumen_unitario;
    private int num_piezas;
    private BigDecimal costo_volumen;
    private BigDecimal volumen_total;
    private BigDecimal costo_total;

    public Madera() {
    }

    public Madera(String id_madera, String grueso_f, String ancho_f, String largo_f, BigDecimal volumen_unitario, int num_piezas, BigDecimal costo_volumen, BigDecimal volumen_total, BigDecimal costo_total) {
        this.id_madera = id_madera;
        this.grueso_f = grueso_f;
        this.ancho_f = ancho_f;
        this.largo_f = largo_f;
        this.volumen_unitario = volumen_unitario;
        this.num_piezas = num_piezas;
        this.costo_volumen = costo_volumen;
        this.volumen_total = volumen_total;
        this.costo_total = costo_total;
    }

    public void setId_madera(String id_madera) {
        this.id_madera = id_madera;
    }

    public void setGrueso_f(String grueso_f) {
        this.grueso_f = grueso_f;
    }

    public void setAncho_f(String ancho_f) {
        this.ancho_f = ancho_f;
    }

    public void setLargo_f(String largo_f) {
        this.largo_f = largo_f;
    }

    public void setVolumen_unitario(BigDecimal volumen_unitario) {
        this.volumen_unitario = volumen_unitario;
    }

    public void setNum_piezas(int num_piezas) {
        this.num_piezas = num_piezas;
    }

    public void setCosto_volumen(BigDecimal costo_volumen) {
        this.costo_volumen = costo_volumen;
    }

    public void setVolumen_total(BigDecimal volumen_total) {
        this.volumen_total = volumen_total;
    }

    public void setCosto_total(BigDecimal costo_total) {
        this.costo_total = costo_total;
    }

    public String getId_madera() {
        return id_madera;
    }

    public String getGrueso_f() {
        return grueso_f;
    }

    public String getAncho_f() {
        return ancho_f;
    }

    public String getLargo_f() {
        return largo_f;
    }

    public BigDecimal getVolumen_unitario() {
        return volumen_unitario;
    }

    public int getNum_piezas() {
        return num_piezas;
    }

    public BigDecimal getCosto_volumen() {
        return costo_volumen;
    }

    public BigDecimal getVolumen_total() {
        return volumen_total;
    }

    public BigDecimal getCosto_total() {
        return costo_total;
    }
    
}
