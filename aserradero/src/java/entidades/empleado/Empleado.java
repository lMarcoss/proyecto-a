package entidades.empleado;

/**
 *
 * @author lmarcoss
 */
public class Empleado {

    private String id_empleado;
    private String id_persona;
    private String empleado;
    private String id_jefe;
    private String rol;
    private String estatus;

    public Empleado() {
    }

    public Empleado(String id_empleado, String id_persona, String empleado, String id_jefe, String rol, String estatus) {
        this.id_empleado = id_empleado;
        this.id_persona = id_persona;
        this.empleado = empleado;
        this.id_jefe = id_jefe;
        this.rol = rol;
        this.estatus = estatus;
    }

    public void setId_empleado(String id_empleado) {
        this.id_empleado = id_empleado;
    }

    public void setEmpleado(String empleado) {
        this.empleado = empleado;
    }

    public void setId_persona(String id_persona) {
        this.id_persona = id_persona;
    }

    public void setId_jefe(String id_jefe) {
        this.id_jefe = id_jefe;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public void setEstatus(String estatus) {
        this.estatus = estatus;
    }

    public String getId_empleado() {
        return id_empleado;
    }

    public String getEmpleado() {
        return empleado;
    }

    public String getId_persona() {
        return id_persona;
    }

    public String getId_jefe() {
        return id_jefe;
    }

    public String getRol() {
        return rol;
    }

    public String getEstatus() {
        return estatus;
    }

}
