
package entidades.registros;

/**
 *
 * @author lmarcoss
 */
public class Municipio {
    private String nombre_municipio;
    private String estado;
    private String telefono;

    public Municipio() {
    }

    public Municipio(String nombre_municipio, String estado, String telefono) {
        this.nombre_municipio = nombre_municipio;
        this.estado = estado;
        this.telefono = telefono;
    }

    public void setNombre_municipio(String nombre_municipio) {
        this.nombre_municipio = nombre_municipio;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
    
    public String getNombre_municipio() {
        return nombre_municipio;
    }

    public String getEstado() {
        return estado;
    }

    public String getTelefono() {
        return telefono;
    }
    
}
