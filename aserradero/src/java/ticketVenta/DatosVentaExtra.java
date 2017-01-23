package ticketVenta;


/**
 *
 * @author lmarcoss
 */
public class DatosVentaExtra {
    private String tipo;
    private String observacion;
    private float monto;

    public DatosVentaExtra() {
    }

    public DatosVentaExtra(String tipo, String observacion, float monto) {
        this.tipo = tipo;
        this.observacion = observacion;
        this.monto = monto;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public void setMonto(float monto) {
        this.monto = monto;
    }

    public String getTipo() {
        return tipo;
    }

    public String getObservacion() {
        return observacion;
    }

    public float getMonto() {
        return monto;
    }
}
