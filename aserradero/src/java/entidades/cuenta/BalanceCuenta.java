package entidades.cuenta;

/**
 *
 * @author lmarcoss
 */
public class BalanceCuenta {

    private String id_administrador;
    private String cuenta_inicial;
    private String bienes_inmuebles;
    private String pago_empleado;
    private String gastos;
    private String cuenta_por_pagar;
    private String cuenta_por_cobrar;
    private String pagos_compra;
    private String venta_en_efectivo;
    private String prestamo;
    private String inventario_m_rollo;
    private String inventario_m_aserrada;

    public BalanceCuenta() {
    }

    public BalanceCuenta(String id_administrador, String cuenta_inicial, String bienes_inmuebles, String pago_empleado, String gastos, String cuenta_por_pagar, String cuenta_por_cobrar, String pagos_compra, String venta_en_efectivo, String prestamo, String inventario_m_rollo, String inventario_m_aserrada) {
        this.id_administrador = id_administrador;
        this.cuenta_inicial = cuenta_inicial;
        this.bienes_inmuebles = bienes_inmuebles;
        this.pago_empleado = pago_empleado;
        this.gastos = gastos;
        this.cuenta_por_pagar = cuenta_por_pagar;
        this.cuenta_por_cobrar = cuenta_por_cobrar;
        this.pagos_compra = pagos_compra;
        this.venta_en_efectivo = venta_en_efectivo;
        this.prestamo = prestamo;
        this.inventario_m_rollo = inventario_m_rollo;
        this.inventario_m_aserrada = inventario_m_aserrada;
    }

    public void setId_administrador(String id_administrador) {
        this.id_administrador = id_administrador;
    }

    public void setCuenta_inicial(String cuenta_inicial) {
        this.cuenta_inicial = cuenta_inicial;
    }

    public void setBienes_inmuebles(String bienes_inmuebles) {
        this.bienes_inmuebles = bienes_inmuebles;
    }

    public void setPago_empleado(String pago_empleado) {
        this.pago_empleado = pago_empleado;
    }

    public void setGastos(String gastos) {
        this.gastos = gastos;
    }

    public void setCuenta_por_pagar(String cuenta_por_pagar) {
        this.cuenta_por_pagar = cuenta_por_pagar;
    }

    public void setCuenta_por_cobrar(String cuenta_por_cobrar) {
        this.cuenta_por_cobrar = cuenta_por_cobrar;
    }

    public void setPagos_compra(String pagos_compra) {
        this.pagos_compra = pagos_compra;
    }

    public void setVenta_en_efectivo(String venta_en_efectivo) {
        this.venta_en_efectivo = venta_en_efectivo;
    }

    public void setPrestamo(String prestamo) {
        this.prestamo = prestamo;
    }

    public void setInventario_m_rollo(String inventario_m_rollo) {
        this.inventario_m_rollo = inventario_m_rollo;
    }

    public void setInventario_m_aserrada(String inventario_m_aserrada) {
        this.inventario_m_aserrada = inventario_m_aserrada;
    }

    public String getId_administrador() {
        return id_administrador;
    }

    public String getCuenta_inicial() {
        return cuenta_inicial;
    }

    public String getBienes_inmuebles() {
        return bienes_inmuebles;
    }

    public String getPago_empleado() {
        return pago_empleado;
    }

    public String getGastos() {
        return gastos;
    }

    public String getCuenta_por_pagar() {
        return cuenta_por_pagar;
    }

    public String getCuenta_por_cobrar() {
        return cuenta_por_cobrar;
    }

    public String getPagos_compra() {
        return pagos_compra;
    }

    public String getVenta_en_efectivo() {
        return venta_en_efectivo;
    }

    public String getPrestamo() {
        return prestamo;
    }

    public String getInventario_m_rollo() {
        return inventario_m_rollo;
    }

    public String getInventario_m_aserrada() {
        return inventario_m_aserrada;
    }

}
