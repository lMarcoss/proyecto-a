package entidades.anticipo;

import java.math.BigDecimal;

/**
 *
 * @author lmarcoss
 */
public class CuentaPorPagar {
    private String id_persona; // puede ser id_cliente o id_persona
    private String persona; //puede ser cliente o persona
    private String id_jefe;
    private BigDecimal monto;

    public CuentaPorPagar() {
    }

    public CuentaPorPagar(String id_persona, String persona, String id_jefe, BigDecimal monto) {
        this.id_persona = id_persona;
        this.persona = persona;
        this.id_jefe = id_jefe;
        this.monto = monto;
    }

    public void setId_persona(String id_persona) {
        this.id_persona = id_persona;
    }

    public void setPersona(String persona) {
        this.persona = persona;
    }

    public void setId_jefe(String id_jefe) {
        this.id_jefe = id_jefe;
    }

    public void setMonto(BigDecimal monto) {
        this.monto = monto;
    }

    public String getId_persona() {
        return id_persona;
    }

    public String getPersona() {
        return persona;
    }

    public String getId_jefe() {
        return id_jefe;
    }

    public BigDecimal getMonto() {
        return monto;
    }
}
