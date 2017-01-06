package entidades.maderaRollo;

import java.math.BigDecimal;
import java.sql.Date;

/**
 *
 * @author Ricardo Cortés Cruz ->> ricardo.crts.crz@gmail.com Modificado por:
 * lmarcoss
 */
public class EntradaMaderaRollo {

    private int id_entrada;
    private Date fecha;
    private String id_proveedor;
    private String proveedor;
    private String id_chofer;
    private String chofer;
    private String id_empleado;
    private String empleado;
    private String id_jefe;
    private int num_pieza_primario;
    private BigDecimal volumen_primario;
    private BigDecimal costo_primario;
    private int num_pieza_secundario;
    private BigDecimal volumen_secundario;
    private BigDecimal costo_secundario;
    private int num_pieza_terciario;
    private BigDecimal volumen_terciario;
    private BigDecimal costo_terciario;
    private int num_pieza_total;
    private BigDecimal volumen_total;
    private BigDecimal costo_total;
    private int id_pago;

    public EntradaMaderaRollo() {
    }

    public EntradaMaderaRollo(int id_entrada, Date fecha, String id_proveedor, String proveedor, String id_chofer, String chofer, String id_empleado, String empleado, String id_jefe, int num_pieza_primario, BigDecimal volumen_primario, BigDecimal costo_primario, int num_pieza_secundario, BigDecimal volumen_secundario, BigDecimal costo_secundario, int num_pieza_terciario, BigDecimal volumen_terciario, BigDecimal costo_terciario, int num_pieza_total, BigDecimal volumen_total, BigDecimal costo_total, int id_pago) {
        this.id_entrada = id_entrada;
        this.fecha = fecha;
        this.id_proveedor = id_proveedor;
        this.proveedor = proveedor;
        this.id_chofer = id_chofer;
        this.chofer = chofer;
        this.id_empleado = id_empleado;
        this.empleado = empleado;
        this.id_jefe = id_jefe;
        this.num_pieza_primario = num_pieza_primario;
        this.volumen_primario = volumen_primario;
        this.costo_primario = costo_primario;
        this.num_pieza_secundario = num_pieza_secundario;
        this.volumen_secundario = volumen_secundario;
        this.costo_secundario = costo_secundario;
        this.num_pieza_terciario = num_pieza_terciario;
        this.volumen_terciario = volumen_terciario;
        this.costo_terciario = costo_terciario;
        this.num_pieza_total = num_pieza_total;
        this.volumen_total = volumen_total;
        this.costo_total = costo_total;
        this.id_pago = id_pago;
    }

    public void setId_entrada(int id_entrada) {
        this.id_entrada = id_entrada;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public void setId_proveedor(String id_proveedor) {
        this.id_proveedor = id_proveedor;
    }

    public void setProveedor(String proveedor) {
        this.proveedor = proveedor;
    }

    public void setId_chofer(String id_chofer) {
        this.id_chofer = id_chofer;
    }

    public void setChofer(String chofer) {
        this.chofer = chofer;
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

    public void setCosto_primario(BigDecimal costo_primario) {
        this.costo_primario = costo_primario;
    }

    public void setNum_pieza_secundario(int num_pieza_secundario) {
        this.num_pieza_secundario = num_pieza_secundario;
    }

    public void setVolumen_secundario(BigDecimal volumen_secundario) {
        this.volumen_secundario = volumen_secundario;
    }

    public void setCosto_secundario(BigDecimal costo_secundario) {
        this.costo_secundario = costo_secundario;
    }

    public void setNum_pieza_terciario(int num_pieza_terciario) {
        this.num_pieza_terciario = num_pieza_terciario;
    }

    public void setVolumen_terciario(BigDecimal volumen_terciario) {
        this.volumen_terciario = volumen_terciario;
    }

    public void setCosto_terciario(BigDecimal costo_terciario) {
        this.costo_terciario = costo_terciario;
    }

    public void setNum_pieza_total(int num_pieza_total) {
        this.num_pieza_total = num_pieza_total;
    }

    public void setVolumen_total(BigDecimal volumen_total) {
        this.volumen_total = volumen_total;
    }

    public void setCosto_total(BigDecimal costo_total) {
        this.costo_total = costo_total;
    }

    public void setId_pago(int id_pago) {
        this.id_pago = id_pago;
    }

    public int getId_entrada() {
        return id_entrada;
    }

    public Date getFecha() {
        return fecha;
    }

    public String getId_proveedor() {
        return id_proveedor;
    }

    public String getProveedor() {
        return proveedor;
    }

    public String getId_chofer() {
        return id_chofer;
    }

    public String getChofer() {
        return chofer;
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

    public BigDecimal getCosto_primario() {
        return costo_primario;
    }

    public int getNum_pieza_secundario() {
        return num_pieza_secundario;
    }

    public BigDecimal getVolumen_secundario() {
        return volumen_secundario;
    }

    public BigDecimal getCosto_secundario() {
        return costo_secundario;
    }

    public int getNum_pieza_terciario() {
        return num_pieza_terciario;
    }

    public BigDecimal getVolumen_terciario() {
        return volumen_terciario;
    }

    public BigDecimal getCosto_terciario() {
        return costo_terciario;
    }

    public int getNum_pieza_total() {
        return num_pieza_total;
    }

    public BigDecimal getVolumen_total() {
        return volumen_total;
    }

    public BigDecimal getCosto_total() {
        return costo_total;
    }

    public int getId_pago() {
        return id_pago;
    }

}
