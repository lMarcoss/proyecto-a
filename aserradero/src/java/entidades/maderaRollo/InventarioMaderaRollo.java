package entidades.maderaRollo;

import java.math.BigDecimal;

/**
 *
 * @author Ricardo Cortés Cruz ->> ricardo.crts.crz@gmail.com
 */
public class InventarioMaderaRollo {

    private String id_jefe;
    private int num_pieza_primario;
    private BigDecimal volumen_primario;
    private BigDecimal costo_primario;
    private BigDecimal costo_total_primario;
    private int num_pieza_secundario;
    private BigDecimal volumen_secundario;
    private BigDecimal costo_secundario;
    private BigDecimal costo_total_secundario;
    private int num_pieza_terciario;
    private BigDecimal volumen_terciario;
    private BigDecimal costo_terciario;
    private BigDecimal costo_total_terciario;
    private int num_pieza_total;
    private BigDecimal volumen_total;
    private BigDecimal costo_total;

    public InventarioMaderaRollo() {
    }

    public InventarioMaderaRollo(String id_jefe, int num_pieza_primario, BigDecimal volumen_primario, BigDecimal costo_primario, BigDecimal costo_total_primario, int num_pieza_secundario, BigDecimal volumen_secundario, BigDecimal costo_secundario, BigDecimal costo_total_secundario, int num_pieza_terciario, BigDecimal volumen_terciario, BigDecimal costo_terciario, BigDecimal costo_total_terciario, int num_pieza_total, BigDecimal volumen_total, BigDecimal costo_total) {
        this.id_jefe = id_jefe;
        this.num_pieza_primario = num_pieza_primario;
        this.volumen_primario = volumen_primario;
        this.costo_primario = costo_primario;
        this.costo_total_primario = costo_total_primario;
        this.num_pieza_secundario = num_pieza_secundario;
        this.volumen_secundario = volumen_secundario;
        this.costo_secundario = costo_secundario;
        this.costo_total_secundario = costo_total_secundario;
        this.num_pieza_terciario = num_pieza_terciario;
        this.volumen_terciario = volumen_terciario;
        this.costo_terciario = costo_terciario;
        this.costo_total_terciario = costo_total_terciario;
        this.num_pieza_total = num_pieza_total;
        this.volumen_total = volumen_total;
        this.costo_total = costo_total;
    }

    public void setId_jefe(String id_jefe) {
        this.id_jefe = id_jefe;
    }

    public void setNum_pieza_primario(int num_pieza_primario) {
        this.num_pieza_primario = num_pieza_primario;
    }

    public void setVolumen_primario(BigDecimal volumen_primario) {
        this.volumen_primario = volumen_primario;
    }

    public void setCosto_primario(BigDecimal costo_primario) {
        this.costo_primario = costo_primario;
    }

    public void setCosto_total_primario(BigDecimal costo_total_primario) {
        this.costo_total_primario = costo_total_primario;
    }

    public void setNum_pieza_secundario(int num_pieza_secundario) {
        this.num_pieza_secundario = num_pieza_secundario;
    }

    public void setVolumen_secundario(BigDecimal volumen_secundario) {
        this.volumen_secundario = volumen_secundario;
    }

    public void setCosto_secundario(BigDecimal costo_secundario) {
        this.costo_secundario = costo_secundario;
    }

    public void setCosto_total_secundario(BigDecimal costo_total_secundario) {
        this.costo_total_secundario = costo_total_secundario;
    }

    public void setNum_pieza_terciario(int num_pieza_terciario) {
        this.num_pieza_terciario = num_pieza_terciario;
    }

    public void setVolumen_terciario(BigDecimal volumen_terciario) {
        this.volumen_terciario = volumen_terciario;
    }

    public void setCosto_terciario(BigDecimal costo_terciario) {
        this.costo_terciario = costo_terciario;
    }

    public void setCosto_total_terciario(BigDecimal costo_total_terciario) {
        this.costo_total_terciario = costo_total_terciario;
    }

    public void setNum_pieza_total(int num_pieza_total) {
        this.num_pieza_total = num_pieza_total;
    }

    public void setVolumen_total(BigDecimal volumen_total) {
        this.volumen_total = volumen_total;
    }

    public void setCosto_total(BigDecimal costo_total) {
        this.costo_total = costo_total;
    }

    public String getId_jefe() {
        return id_jefe;
    }

    public int getNum_pieza_primario() {
        return num_pieza_primario;
    }

    public BigDecimal getVolumen_primario() {
        return volumen_primario;
    }

    public BigDecimal getCosto_primario() {
        return costo_primario;
    }

    public BigDecimal getCosto_total_primario() {
        return costo_total_primario;
    }

    public int getNum_pieza_secundario() {
        return num_pieza_secundario;
    }

    public BigDecimal getVolumen_secundario() {
        return volumen_secundario;
    }

    public BigDecimal getCosto_secundario() {
        return costo_secundario;
    }

    public BigDecimal getCosto_total_secundario() {
        return costo_total_secundario;
    }

    public int getNum_pieza_terciario() {
        return num_pieza_terciario;
    }

    public BigDecimal getVolumen_terciario() {
        return volumen_terciario;
    }

    public BigDecimal getCosto_terciario() {
        return costo_terciario;
    }

    public BigDecimal getCosto_total_terciario() {
        return costo_total_terciario;
    }

    public int getNum_pieza_total() {
        return num_pieza_total;
    }

    public BigDecimal getVolumen_total() {
        return volumen_total;
    }

    public BigDecimal getCosto_total() {
        return costo_total;
    }

}
