package dao.maderaRollo;

import dao.Conexion;
import entidades.maderaRollo.InventarioMaderaRollo;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author rcortes Modificado por lmarcoss
 */
public class InventarioMaderaRolloCRUD extends Conexion {

    public <T> List listar(String id_jefe) throws Exception {
        List<InventarioMaderaRollo> inventarios;
        try {
            this.abrirConexion();
            try (PreparedStatement st = this.conexion.prepareStatement("SELECT * FROM INVENTARIO_M_ROLLO WHERE id_jefe = ? AND costo_total > 0")) {
                st.setString(1, id_jefe);
                inventarios = new ArrayList<>();
                try (ResultSet rs = st.executeQuery()) {
                    while (rs.next()) {
                        InventarioMaderaRollo inventario = (InventarioMaderaRollo) extraerObject(rs);
                        inventarios.add(inventario);
                    }
                } catch (Exception e) {
                    System.out.println(e);
                }
            } catch (Exception e) {
                inventarios = null;
                System.out.println(e);
            }
        } catch (Exception e) {
            System.out.println(e);
            throw e;
        } finally {
            cerrarConexion();
        }
        return inventarios;
    }
    

    public Object extraerObject(ResultSet rs) throws SQLException {
        InventarioMaderaRollo inventario = new InventarioMaderaRollo();
        inventario.setId_jefe(rs.getString("id_jefe"));
        inventario.setNum_pieza_primario(rs.getInt("num_pieza_primario"));
        inventario.setVolumen_primario(rs.getBigDecimal("volumen_primario"));
        inventario.setCosto_primario(rs.getBigDecimal("costo_primario"));
        inventario.setCosto_total_primario(rs.getBigDecimal("costo_total_primario"));
        inventario.setNum_pieza_secundario(rs.getInt("num_pieza_secundario"));
        inventario.setVolumen_secundario(rs.getBigDecimal("volumen_secundario"));
        inventario.setCosto_secundario(rs.getBigDecimal("costo_secundario"));
        inventario.setCosto_total_secundario(rs.getBigDecimal("costo_total_secundario"));
        inventario.setNum_pieza_terciario(rs.getInt("num_pieza_terciario"));
        inventario.setVolumen_terciario(rs.getBigDecimal("volumen_terciario"));
        inventario.setCosto_terciario(rs.getBigDecimal("costo_terciario"));
        inventario.setCosto_total_terciario(rs.getBigDecimal("costo_total_terciario"));
        inventario.setNum_pieza_total(rs.getInt("num_pieza_total"));
        inventario.setVolumen_total(rs.getBigDecimal("volumen_total"));
        inventario.setCosto_total(rs.getBigDecimal("costo_total"));
        return inventario;
    }

}
