/*
 * Clase para reporte de ventas por paquete y por mayoreo
 */
package entidades.venta;

import java.sql.Date;

/**
 *
 * @author lmarcoss
 */
public class ReporteVentaPM {

    private String id_madera;
    private int num_piezas;
    private String volumen;
    private String tipo_madera;
    private String monto;

    public ReporteVentaPM() {
    }

    public ReporteVentaPM(String id_madera, int num_piezas, String volumen, String tipo_madera, String monto) {
        this.id_madera = id_madera;
        this.num_piezas = num_piezas;
        this.volumen = volumen;
        this.tipo_madera = tipo_madera;
        this.monto = monto;
    }

    public String getId_madera() {
        return id_madera;
    }

    public void setId_madera(String id_madera) {
        this.id_madera = id_madera;
    }

    public int getNum_piezas() {
        return num_piezas;
    }

    public void setNum_piezas(int num_piezas) {
        this.num_piezas = num_piezas;
    }

    public String getVolumen() {
        return volumen;
    }

    public void setVolumen(String volumen) {
        this.volumen = volumen;
    }

    public String getTipo_madera() {
        return tipo_madera;
    }

    public void setTipo_madera(String tipo_madera) {
        this.tipo_madera = tipo_madera;
    }

    public String getMonto() {
        return monto;
    }

    public void setMonto(String monto) {
        this.monto = monto;
    }

}
