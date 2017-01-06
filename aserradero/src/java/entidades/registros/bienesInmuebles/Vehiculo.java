package entidades.registros.bienesInmuebles;

import java.math.BigDecimal;

/**
 *
 * @author Ricardo Cortés Cruz ->> ricardo.crts.crz@gmail.com
 */
 
 public class Vehiculo{
   private int id_vehiculo;
   private String matricula;
   private String tipo;
   private String color;
   private String carga_admitida;
   private String motor;
   private String modelo;
   private BigDecimal costo;
   private String id_empleado;
   private String empleado;

   public Vehiculo(){

   }

    public Vehiculo(int id_vehiculo, String matricula, String tipo, String color, String carga_admitida, String motor, String modelo, BigDecimal costo, String id_empleado, String empleado) {
        this.id_vehiculo = id_vehiculo;
        this.matricula = matricula;
        this.tipo = tipo;
        this.color = color;
        this.carga_admitida = carga_admitida;
        this.motor = motor;
        this.modelo = modelo;
        this.costo = costo;
        this.id_empleado = id_empleado;
        this.empleado = empleado;
    }

    public void setId_vehiculo(int id_vehiculo) {
        this.id_vehiculo = id_vehiculo;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setCarga_admitida(String carga_admitida) {
        this.carga_admitida = carga_admitida;
    }

    public void setMotor(String motor) {
        this.motor = motor;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public void setCosto(BigDecimal costo) {
        this.costo = costo;
    }

    public void setId_empleado(String id_empleado) {
        this.id_empleado = id_empleado;
    }

    public void setEmpleado(String empleado) {
        this.empleado = empleado;
    }

    public int getId_vehiculo() {
        return id_vehiculo;
    }

    public String getMatricula() {
        return matricula;
    }

    public String getTipo() {
        return tipo;
    }

    public String getColor() {
        return color;
    }

    public String getCarga_admitida() {
        return carga_admitida;
    }

    public String getMotor() {
        return motor;
    }

    public String getModelo() {
        return modelo;
    }

    public BigDecimal getCosto() {
        return costo;
    }

    public String getId_empleado() {
        return id_empleado;
    }

    public String getEmpleado() {
        return empleado;
    }


 }
