
package entidades.registros;

import java.sql.Date;

/**
 *
 * @author lmarcoss
 */
public class Persona {
    private String id_persona;
    private String nombre;
    private String apellido_paterno;
    private String apellido_materno;
    private String nombre_localidad;
    private String nombre_municipio;
    private String estado;
    private String direccion;
    private String sexo;
    private Date fecha_nacimiento;
    private String telefono;

    public Persona() {
    }

    public Persona(String id_persona, String nombre, String apellido_paterno, String apellido_materno, String nombre_localidad, String nombre_municipio, String estado, String direccion, String sexo, Date fecha_nacimiento, String telefono) {
        this.id_persona = id_persona;
        this.nombre = nombre;
        this.apellido_paterno = apellido_paterno;
        this.apellido_materno = apellido_materno;
        this.nombre_localidad = nombre_localidad;
        this.nombre_municipio = nombre_municipio;
        this.estado = estado;
        this.direccion = direccion;
        this.sexo = sexo;
        this.fecha_nacimiento = fecha_nacimiento;
        this.telefono = telefono;
    }

    public void setId_persona(String id_persona) {
        this.id_persona = id_persona;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellido_paterno(String apellido_paterno) {
        this.apellido_paterno = apellido_paterno;
    }

    public void setApellido_materno(String apellido_materno) {
        this.apellido_materno = apellido_materno;
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

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public void setFecha_nacimiento(Date fecha_nacimiento) {
        this.fecha_nacimiento = fecha_nacimiento;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getId_persona() {
        return id_persona;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido_paterno() {
        return apellido_paterno;
    }

    public String getApellido_materno() {
        return apellido_materno;
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

    public String getDireccion() {
        return direccion;
    }

    public String getSexo() {
        return sexo;
    }

    public Date getFecha_nacimiento() {
        return fecha_nacimiento;
    }

    public String getTelefono() {
        return telefono;
    }

    
}
