package entidades.maderaAserrada;

import java.math.BigDecimal;

/**
 * Clase: Clasificación de madera aserrada
 *
 * @author lmarcoss
 */
public class MaderaAserradaClasif {

    private String id_administrador;
    private String id_empleado;
    private String empleado;
    private String id_madera;
    private BigDecimal grueso;
    private String grueso_f;
    private BigDecimal ancho;
    private String ancho_f;
    private BigDecimal largo;
    private String largo_f;
    private BigDecimal volumen;
    private BigDecimal costo_por_volumen;

    public MaderaAserradaClasif() {
    }

    public MaderaAserradaClasif(String id_administrador, String id_empleado, String empleado, String id_madera, BigDecimal grueso, String grueso_f, BigDecimal ancho, String ancho_f, BigDecimal largo, String largo_f, BigDecimal volumen, BigDecimal costo_por_volumen) {
        this.id_administrador = id_administrador;
        this.id_empleado = id_empleado;
        this.empleado = empleado;
        this.id_madera = id_madera;
        this.grueso = grueso;
        this.grueso_f = grueso_f;
        this.ancho = ancho;
        this.ancho_f = ancho_f;
        this.largo = largo;
        this.largo_f = largo_f;
        this.volumen = volumen;
        this.costo_por_volumen = costo_por_volumen;
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

    public void setId_madera(String id_madera) {
        this.id_madera = id_madera;
    }

    public void setGrueso(BigDecimal grueso) {
        this.grueso = grueso;
    }

    public void setGrueso_f(String grueso_f) {
        this.grueso_f = grueso_f;
    }

    public void setAncho(BigDecimal ancho) {
        this.ancho = ancho;
    }

    public void setAncho_f(String ancho_f) {
        this.ancho_f = ancho_f;
    }

    public void setLargo(BigDecimal largo) {
        this.largo = largo;
    }

    public void setLargo_f(String largo_f) {
        this.largo_f = largo_f;
    }

    public void setVolumen(BigDecimal volumen) {
        this.volumen = volumen;
    }

    public void setCosto_por_volumen(BigDecimal costo_por_volumen) {
        this.costo_por_volumen = costo_por_volumen;
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

    public String getId_madera() {
        return id_madera;
    }

    public BigDecimal getGrueso() {
        return grueso;
    }

    public String getGrueso_f() {
        return grueso_f;
    }

    public BigDecimal getAncho() {
        return ancho;
    }

    public String getAncho_f() {
        return ancho_f;
    }

    public BigDecimal getLargo() {
        return largo;
    }

    public String getLargo_f() {
        return largo_f;
    }

    public BigDecimal getVolumen() {
        return volumen;
    }

    public BigDecimal getCosto_por_volumen() {
        return costo_por_volumen;
    }

}
