package entidades.maderaRollo;

import java.math.BigDecimal;
import java.sql.Date;

/**
 *
 * @author lmarcoss
 */
public class SalidaMaderaRollo {

    private int id_salida;
    private Date fecha;
    private String id_empleado;
    private String empleado;
    private String id_jefe;
    private int num_pieza_primario;
    private BigDecimal volumen_primario;
    private int num_pieza_secundario;
    private BigDecimal volumen_secundario;
    private int num_pieza_terciario;
    private BigDecimal volumen_terciario;
    private int num_pieza_total;
    private BigDecimal volumen_total;

    public SalidaMaderaRollo() {
    }

    public SalidaMaderaRollo(int id_salida, Date fecha, String id_empleado, String empleado, String id_jefe, int num_pieza_primario, BigDecimal volumen_primario, int num_pieza_secundario, BigDecimal volumen_secundario, int num_pieza_terciario, BigDecimal volumen_terciario, int num_pieza_total, BigDecimal volumen_total) {
        this.id_salida = id_salida;
        this.fecha = fecha;
        this.id_empleado = id_empleado;
        this.empleado = empleado;
        this.id_jefe = id_jefe;
        this.num_pieza_primario = num_pieza_primario;
        this.volumen_primario = volumen_primario;
        this.num_pieza_secundario = num_pieza_secundario;
        this.volumen_secundario = volumen_secundario;
        this.num_pieza_terciario = num_pieza_terciario;
        this.volumen_terciario = volumen_terciario;
        this.num_pieza_total = num_pieza_total;
        this.volumen_total = volumen_total;
    }

    public void setId_salida(int id_salida) {
        this.id_salida = id_salida;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
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

    public void setNum_pieza_primario(int num_pieza_primario) {
        this.num_pieza_primario = num_pieza_primario;
    }

    public void setVolumen_primario(BigDecimal volumen_primario) {
        this.volumen_primario = volumen_primario;
    }

    public void setNum_pieza_secundario(int num_pieza_secundario) {
        this.num_pieza_secundario = num_pieza_secundario;
    }

    public void setVolumen_secundario(BigDecimal volumen_secundario) {
        this.volumen_secundario = volumen_secundario;
    }

    public void setNum_pieza_terciario(int num_pieza_terciario) {
        this.num_pieza_terciario = num_pieza_terciario;
    }

    public void setVolumen_terciario(BigDecimal volumen_terciario) {
        this.volumen_terciario = volumen_terciario;
    }

    public void setNum_pieza_total(int num_pieza_total) {
        this.num_pieza_total = num_pieza_total;
    }

    public void setVolumen_total(BigDecimal volumen_total) {
        this.volumen_total = volumen_total;
    }

    public int getId_salida() {
        return id_salida;
    }

    public Date getFecha() {
        return fecha;
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

    public int getNum_pieza_primario() {
        return num_pieza_primario;
    }

    public BigDecimal getVolumen_primario() {
        return volumen_primario;
    }

    public int getNum_pieza_secundario() {
        return num_pieza_secundario;
    }

    public BigDecimal getVolumen_secundario() {
        return volumen_secundario;
    }

    public int getNum_pieza_terciario() {
        return num_pieza_terciario;
    }

    public BigDecimal getVolumen_terciario() {
        return volumen_terciario;
    }

    public int getNum_pieza_total() {
        return num_pieza_total;
    }

    public BigDecimal getVolumen_total() {
        return volumen_total;
    }

}
