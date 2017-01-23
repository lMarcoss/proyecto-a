package entidades.empleado;

/**
 *
 * @author lmarcoss
 */
public class Usuario {

    public String nombre_usuario;
    public String contrasenia;
    public String email;
    public String id_empleado;
    public String empleado;
    public String id_jefe;
    public String rol;
    public String estatus;

    public Usuario() {
    }

    public Usuario(String nombre_usuario, String contrasenia, String email, String id_empleado, String empleado, String id_jefe, String rol, String estatus) {
        this.nombre_usuario = nombre_usuario;
        this.contrasenia = contrasenia;
        this.email = email;
        this.id_empleado = id_empleado;
        this.empleado = empleado;
        this.id_jefe = id_jefe;
        this.rol = rol;
        this.estatus = estatus;
    }

    public void setNombre_usuario(String nombre_usuario) {
        this.nombre_usuario = nombre_usuario;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public void setRol(String rol) {
        this.rol = rol;
    }

    public void setEstatus(String estatus) {
        this.estatus = estatus;
    }

    public String getNombre_usuario() {
        return nombre_usuario;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public String getEmail() {
        return email;
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

    public String getRol() {
        return rol;
    }

    public String getEstatus() {
        return estatus;
    }
    
}
