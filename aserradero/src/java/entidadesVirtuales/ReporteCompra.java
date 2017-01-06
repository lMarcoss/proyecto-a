/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package entidadesVirtuales;

/**
 *
 * @author rcortes
 */
public class ReporteCompra {
    private String id_compra;
    private String fecha;
    private float num_piezas;
    private String id_proveedor;
    private String nombre_proveedor;
    private String id_chofer;
    private String nombre_chofer;
    private String id_empleado;
    private String nombre_empleado;
    private float vol_primario;
    private float vol_secundario;
    private float vol_terciario;
    private float monto_total;
    private float volumen_total;

    public ReporteCompra(String id_compra, String fecha, float num_piezas, String id_proveedor, String nombre_proveedor, String id_chofer, String nombre_chofer, String id_empleado, String nombre_empleado, float vol_primario, float vol_secundario, float vol_terciario, float monto_total, float volumen_total) {
        this.id_compra = id_compra;
        this.fecha = fecha;
        this.num_piezas = num_piezas;
        this.id_proveedor = id_proveedor;
        this.nombre_proveedor = nombre_proveedor;
        this.id_chofer = id_chofer;
        this.nombre_chofer = nombre_chofer;
        this.id_empleado = id_empleado;
        this.nombre_empleado = nombre_empleado;
        this.vol_primario = vol_primario;
        this.vol_secundario = vol_secundario;
        this.vol_terciario = vol_terciario;
        this.monto_total = monto_total;
        this.volumen_total = volumen_total;
    }

    public ReporteCompra() {
        
    }

    public String getId_compra() {
        return id_compra;
    }

    public void setId_compra(String id_compra) {
        this.id_compra = id_compra;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public float getNum_piezas() {
        return num_piezas;
    }

    public void setNum_piezas(float num_piezas) {
        this.num_piezas = num_piezas;
    }

    public String getId_proveedor() {
        return id_proveedor;
    }

    public void setId_proveedor(String id_proveedor) {
        this.id_proveedor = id_proveedor;
    }

    public String getNombre_proveedor() {
        return nombre_proveedor;
    }

    public void setNombre_proveedor(String nombre_proveedor) {
        this.nombre_proveedor = nombre_proveedor;
    }

    public String getId_chofer() {
        return id_chofer;
    }

    public void setId_chofer(String id_chofer) {
        this.id_chofer = id_chofer;
    }

    public String getNombre_chofer() {
        return nombre_chofer;
    }

    public void setNombre_chofer(String nombre_chofer) {
        this.nombre_chofer = nombre_chofer;
    }

    public String getId_empleado() {
        return id_empleado;
    }

    public void setId_empleado(String id_empleado) {
        this.id_empleado = id_empleado;
    }

    public String getNombre_empleado() {
        return nombre_empleado;
    }

    public void setNombre_empleado(String nombre_empleado) {
        this.nombre_empleado = nombre_empleado;
    }

    public float getVol_primario() {
        return vol_primario;
    }

    public void setVol_primario(float vol_primario) {
        this.vol_primario = vol_primario;
    }

    public float getVol_secundario() {
        return vol_secundario;
    }

    public void setVol_secundario(float vol_secundario) {
        this.vol_secundario = vol_secundario;
    }

    public float getVol_terciario() {
        return vol_terciario;
    }

    public void setVol_terciario(float vol_terciario) {
        this.vol_terciario = vol_terciario;
    }

    public float getMonto_total() {
        return monto_total;
    }

    public void setMonto_total(float monto_total) {
        this.monto_total = monto_total;
    }

    public float getVolumen_total() {
        return volumen_total;
    }

    public void setVolumen_total(float volumen_total) {
        this.volumen_total=volumen_total;
    }
    
    
}
