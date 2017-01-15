package dao.cuenta;

import dao.Conexion;
import entidades.cuenta.BalanceCuenta;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author lmarcoss
 */
public class BalanceCuentaCRUD extends Conexion {

    public List<BalanceCuenta> listar(String id_jefe, String rol) throws Exception {
        List<BalanceCuenta> listaBalance = null;
        String consulta;
        if (rol.equals("Administrador")) {
            consulta = "SELECT * FROM BALANCE_CUENTA WHERE id_administrador = ?";
            try {
                this.abrirConexion();
                try (PreparedStatement st = this.conexion.prepareStatement(consulta)) {
                    st.setString(1, id_jefe);
                    listaBalance = new ArrayList();
                    try (ResultSet rs = st.executeQuery()) {
                        while (rs.next()) {
                            BalanceCuenta balance = (BalanceCuenta) extraerBalanceCuenta(rs);
                            listaBalance.add(balance);
                        }
                    }
                } catch (Exception e) {
                    listaBalance = null;
                    System.out.println(e);
                }
            } catch (Exception e) {
                System.out.println(e);
                throw e;
            } finally {
                this.cerrarConexion();
            }
        }

        return listaBalance;
    }

    private BalanceCuenta extraerBalanceCuenta(ResultSet rs) throws SQLException {
        BalanceCuenta balance = new BalanceCuenta();
        balance.setId_administrador(rs.getString("id_administrador"));
        balance.setCuenta_inicial(rs.getString("cuenta_inicial"));
        balance.setBienes_inmuebles(rs.getString("bienes_inmuebles"));
        balance.setPago_empleado(rs.getString("pago_empleado"));
        balance.setGastos(rs.getString("gastos"));
        balance.setCuenta_por_pagar(rs.getString("cuenta_por_pagar"));
        balance.setCuenta_por_cobrar(rs.getString("cuenta_por_cobrar"));
        balance.setAnticipo_proveedor(rs.getString("anticipo_proveedor"));
        balance.setAnticipo_cliente(rs.getString("anticipo_cliente"));
        balance.setPagos_compra(rs.getString("pagos_compra"));
        balance.setVenta_en_efectivo(rs.getString("venta_en_efectivo"));
        balance.setPrestamo(rs.getString("prestamo"));
        balance.setInventario_m_rollo(rs.getString("inventario_m_rollo"));
        balance.setInventario_m_aserrada(rs.getString("inventario_m_aserrada"));
        return balance;
    }

    public String consultar_cuenta_total(String id_jefe, String rol) throws Exception {
        String cuenta_total = null;
        try {
                this.abrirConexion();
                try (PreparedStatement st = this.conexion.prepareStatement("SELECT cuenta_total FROM CUENTA_TOTAL WHERE id_administrador = ?")) {
                    st.setString(1, id_jefe);
                    try (ResultSet rs = st.executeQuery()) {
                        while (rs.next()) {
                            cuenta_total = rs.getString("cuenta_total");
                        }
                    }
                } catch (Exception e) {
                    System.out.println(e);
                }
            } catch (Exception e) {
                System.out.println(e);
                throw e;
            } finally {
                this.cerrarConexion();
            }
        return cuenta_total;
    }

}
