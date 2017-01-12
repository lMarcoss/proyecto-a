package dao.maderaAserrada;

import dao.Conexion;
import entidades.maderaAserrada.InventarioMaderaAserrada;
import interfaces.OperacionesCRUD;
import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Marcos
 */
public class InventarioMaderaAserradaCRUD extends Conexion implements OperacionesCRUD {

    @Override
    public void registrar(Object objeto) throws Exception {
    }

    @Override
    public <T> List listar(String id_jefe, String rol) throws Exception {
        List<InventarioMaderaAserrada> inventarioMaderaProducciones;
        try {
            this.abrirConexion();
            try (PreparedStatement st = this.conexion.prepareStatement("SELECT * FROM INVENTARIO_M_ASERRADA WHERE id_administrador = ? AND num_piezas > 0")) {
                st.setString(1, id_jefe);
                inventarioMaderaProducciones = new ArrayList();
                try (ResultSet rs = st.executeQuery()) {
                    while (rs.next()) {
                        InventarioMaderaAserrada inventarioMaderaProduccion = (InventarioMaderaAserrada) extraerObject(rs);
                        inventarioMaderaProducciones.add(inventarioMaderaProduccion);
                    }
                } catch (Exception e) {
                    System.out.println(e);
                }
            } catch (Exception e) {
                inventarioMaderaProducciones = null;
                System.out.println(e);
            }
        } catch (Exception e) {
            System.out.println(e);
            throw e;
        } finally {
            this.cerrarConexion();
        }
        return inventarioMaderaProducciones;
    }

    @Override
    public void eliminar(Object objeto) throws Exception {

    }

    @Override
    public <T> List buscar(String nombre_campo, String dato, String id_jefe, String rol) throws Exception {
        List<InventarioMaderaAserrada> inventarioMaderaProducciones = null;
        try {
            this.abrirConexion();
            try (PreparedStatement st = this.conexion.prepareStatement("SELECT * FROM INVENTARIO_M_ASERRADA WHERE " + nombre_campo + " like ? AND id_administrador = ?")) {
                st.setString(1, "%" + dato + "%");
                st.setString(2, id_jefe);
                inventarioMaderaProducciones = new ArrayList();
                try (ResultSet rs = st.executeQuery()) {
                    while (rs.next()) {
                        InventarioMaderaAserrada inventarioMaderaProduccion = (InventarioMaderaAserrada) extraerObject(rs);
                        inventarioMaderaProducciones.add(inventarioMaderaProduccion);
                    }
                } catch (Exception e) {
                    System.out.println(e);
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
        return inventarioMaderaProducciones;
    }

    @Override
    public Object extraerObject(ResultSet rs) throws SQLException {
        InventarioMaderaAserrada inventario = new InventarioMaderaAserrada();
        inventario.setId_madera(rs.getString("id_madera"));
        inventario.setNum_piezas(rs.getInt("num_piezas"));
        inventario.setVolumen_unitario(rs.getBigDecimal("volumen_unitario"));
        inventario.setVolumen_total(rs.getBigDecimal("volumen_total"));
        inventario.setCosto_por_volumen(rs.getBigDecimal("costo_por_volumen"));
        inventario.setCosto_total(rs.getBigDecimal("costo_total"));
        return inventario;
    }

    @Override
    public PreparedStatement cargarObject(PreparedStatement st, Object objecto) throws SQLException {
        return null;
    }

    @Override
    public Object modificar(Object objeto) throws Exception {
        return null;
    }

    @Override
    public void actualizar(Object objeto) throws Exception {
    }

    public BigDecimal consultarCostoTotalInventario(String id_admin) throws Exception {
        BigDecimal costo_total = BigDecimal.valueOf(Double.valueOf("0"));
        try {
            this.abrirConexion();
            try (PreparedStatement st = this.conexion.prepareStatement("SELECT SUM(costo_total) AS costo_total FROM INVENTARIO_M_ASERRADA WHERE id_administrador = ? GROUP BY id_administrador;")) {
                st.setString(1, id_admin);
                try (ResultSet rs = st.executeQuery()) {
                    while (rs.next()) {
                        costo_total = rs.getBigDecimal("costo_total");
                    }
                } catch (Exception e) {
                    System.out.println(e);
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
        return costo_total;
    }

    // Consulta piezas, volumen y costo total de inventario
    public InventarioMaderaAserrada consultaTotalInventario(String id_jefe) throws Exception {
        InventarioMaderaAserrada inventario = null;
        try {
            this.abrirConexion();
            try (PreparedStatement st = this.conexion.prepareStatement("SELECT SUM(num_piezas) as num_piezas, SUM(volumen_total) as volumen_total, SUM(costo_total) as costo_total FROM INVENTARIO_M_ASERRADA WHERE id_administrador = ?")) {
                st.setString(1, id_jefe);
                try (ResultSet rs = st.executeQuery()) {
                    while (rs.next()) {
                        inventario = (InventarioMaderaAserrada) extraerTotalInventario(rs);
                    }
                } catch (Exception e) {
                    System.out.println(e);
                }
            } catch (Exception e) {
                inventario = null;
                System.out.println(e);
            }
        } catch (Exception e) {
            System.out.println(e);
            throw e;
        } finally {
            this.cerrarConexion();
        }
        return inventario;
    }

    private InventarioMaderaAserrada extraerTotalInventario(ResultSet rs) throws SQLException {
        InventarioMaderaAserrada inventario = new InventarioMaderaAserrada();
        inventario.setNum_piezas(rs.getInt("num_piezas"));
        inventario.setVolumen_total(rs.getBigDecimal("volumen_total"));
        inventario.setCosto_total(rs.getBigDecimal("costo_total"));
        return inventario;
    }
}
