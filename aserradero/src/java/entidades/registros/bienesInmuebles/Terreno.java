package entidades.registros.bienesInmuebles;

import java.math.BigDecimal;

/**
 *
 * @author lmarcoss
 */
public class Terreno {

    private int id_terreno;
    private String nombre;
    private String dimension;
    private String direccion;
    private String nombre_localidad;
    private String nombre_municipio;
    private String estado;
    private BigDecimal valor_estimado;
    private String id_empleado;
    private String empleado;
    private String id_jefe;

    public Terreno() {
    }

    public Terreno(int id_terreno, String nombre, String dimension, String direccion, String nombre_localidad, String nombre_municipio, String estado, BigDecimal valor_estimado, String id_empleado, String empleado, String id_jefe) {
        this.id_terreno = id_terreno;
        this.nombre = nombre;
        this.dimension = dimension;
        this.direccion = direccion;
        this.nombre_localidad = nombre_localidad;
        this.nombre_municipio = nombre_municipio;
        this.estado = estado;
        this.valor_estimado = valor_estimado;
        this.id_empleado = id_empleado;
        this.empleado = empleado;
        this.id_jefe = id_jefe;
    }

    public void setId_terreno(int id_terreno) {
        this.id_terreno = id_terreno;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setDimension(String dimension) {
        this.dimension = dimension;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public void setNombre_localidad(String nombre_localidad) {
        this.nombre_localidad = nombre_localidad;
    }

    public void setNombre_municipio(String nombre_municipio) {
        this.nombre_municipio = nombre_municipio;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public void setValor_estimado(BigDecimal valor_estimado) {
        this.valor_estimado = valor_estimado;
    }

    public void setId_empleado(String id_empleado) {
        this.id_empleado = id_empleado;
    }

    public void setEmpleado(String empleado) {
        this.empleado = empleado;
    }

    public void setId_jefe(String id_jefe) {
        this.id_jefe = id_jefe;
    }

    public int getId_terreno() {
        return id_terreno;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDimension() {
        return dimension;
    }

    public String getDireccion() {
        return direccion;
    }

    public String getNombre_localidad() {
        return nombre_localidad;
    }

    public String getNombre_municipio() {
        return nombre_municipio;
    }

    public String getEstado() {
        return estado;
    }

    public BigDecimal getValor_estimado() {
        return valor_estimado;
    }

    public String getId_empleado() {
        return id_empleado;
    }

    public String getEmpleado() {
        return empleado;
    }

    public String getId_jefe() {
        return id_jefe;
    }

}
