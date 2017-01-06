package entidades.maderaAserrada;

import java.sql.Date;

/**
 *
 * @author lmarcoss
 */
public class EntradaMaderaAserrada {
    private int id_entrada;
    private Date fecha;
    private String id_madera;
    private int num_piezas;
    private String id_empleado;
    private String empleado;
    private String id_administrador;

    public EntradaMaderaAserrada() {
    }

    public EntradaMaderaAserrada(int id_entrada, Date fecha, String id_madera, int num_piezas, String id_empleado, String empleado, String id_administrador) {
        this.id_entrada = id_entrada;
        this.fecha = fecha;
        this.id_madera = id_madera;
        this.num_piezas = num_piezas;
        this.id_empleado = id_empleado;
        this.empleado = empleado;
        this.id_administrador = id_administrador;
    }

    public void setId_entrada(int id_entrada) {
        this.id_entrada = id_entrada;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public void setId_madera(String id_madera) {
        this.id_madera = id_madera;
    }

    public void setNum_piezas(int num_piezas) {
        this.num_piezas = num_piezas;
    }

    public void setId_empleado(String id_empleado) {
        this.id_empleado = id_empleado;
    }

    public void setEmpleado(String empleado) {
        this.empleado = empleado;
    }

    public void setId_administrador(String id_administrador) {
        this.id_administrador = id_administrador;
    }

    public int getId_entrada() {
        return id_entrada;
    }

    public Date getFecha() {
        return fecha;
    }

    public String getId_madera() {
        return id_madera;
    }

    public int getNum_piezas() {
        return num_piezas;
    }

    public String getId_empleado() {
        return id_empleado;
    }

    public String getEmpleado() {
        return empleado;
    }

    public String getId_administrador() {
        return id_administrador;
    }

}
