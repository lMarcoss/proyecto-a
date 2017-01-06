package entidades.empleado;

import java.math.BigDecimal;

/**
 *
 * @author lmarcoss
 */
public class Administrador {

    private String id_administrador;
    private String nombre;
    private BigDecimal cuenta_inicial;

    public Administrador() {
    }

    public Administrador(String id_administrador, String nombre, BigDecimal cuenta_inicial) {
        this.id_administrador = id_administrador;
        this.nombre = nombre;
        this.cuenta_inicial = cuenta_inicial;
    }

    public void setId_administrador(String id_administrador) {
        this.id_administrador = id_administrador;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setCuenta_inicial(BigDecimal cuenta_inicial) {
        this.cuenta_inicial = cuenta_inicial;
    }

    public String getId_administrador() {
        return id_administrador;
    }

    public String getNombre() {
        return nombre;
    }

    public BigDecimal getCuenta_inicial() {
        return cuenta_inicial;
    }

}
