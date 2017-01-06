package entidades.registros;

/**
 *
 * @author lmarcoss
 */
public class Localidad {

    public String nombre_localidad;
    public String nombre_municipio;
    public String estado;
    public String telefono_localidad;

    public Localidad() {
    }

    public Localidad(String nombre_localidad, String nombre_municipio, String estado, String telefono_localidad) {
        this.nombre_localidad = nombre_localidad;
        this.nombre_municipio = nombre_municipio;
        this.estado = estado;
        this.telefono_localidad = telefono_localidad;
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

    public void setTelefono_localidad(String telefono_localidad) {
        this.telefono_localidad = telefono_localidad;
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

    public String getTelefono_localidad() {
        return telefono_localidad;
    }

}
