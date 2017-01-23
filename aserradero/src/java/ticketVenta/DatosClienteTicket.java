package ticketVenta;

import java.sql.Date;

/**
 *
 * @author lmarcoss
 */
public class DatosClienteTicket {
    private String id_venta;
    private Date fecha;
    private String tipo_venta;
    private String id_jefe;
    private String id_cliente;
    private String id_persona;
    private String cliente;
    private String direccion;
    private String localidad;
    private String municipio;
    private String estado;
    

    public DatosClienteTicket() {
    }

    public DatosClienteTicket(String id_venta, Date fecha, String tipo_venta, String id_jefe, String id_cliente, String id_persona, String cliente, String direccion, String localidad, String municipio, String estado) {
        this.id_venta = id_venta;
        this.fecha = fecha;
        this.tipo_venta = tipo_venta;
        this.id_jefe = id_jefe;
        this.id_cliente = id_cliente;
        this.id_persona = id_persona;
        this.cliente = cliente;
        this.direccion = direccion;
        this.localidad = localidad;
        this.municipio = municipio;
        this.estado = estado;
    }

    public void setId_venta(String id_venta) {
        this.id_venta = id_venta;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public void setTipo_venta(String tipo_venta) {
        this.tipo_venta = tipo_venta;
    }

    public void setId_jefe(String id_jefe) {
        this.id_jefe = id_jefe;
    }

    public void setId_cliente(String id_cliente) {
        this.id_cliente = id_cliente;
    }

    public void setId_persona(String id_persona) {
        this.id_persona = id_persona;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    public void setMunicipio(String municipio) {
        this.municipio = municipio;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getId_venta() {
        return id_venta;
    }

    public Date getFecha() {
        return fecha;
    }

    public String getTipo_venta() {
        return tipo_venta;
    }

    public String getId_jefe() {
        return id_jefe;
    }

    public String getId_cliente() {
        return id_cliente;
    }

    public String getId_persona() {
        return id_persona;
    }

    public String getCliente() {
        return cliente;
    }

    public String getDireccion() {
        return direccion;
    }

    public String getLocalidad() {
        return localidad;
    }

    public String getMunicipio() {
        return municipio;
    }

    public String getEstado() {
        return estado;
    }

    
}
