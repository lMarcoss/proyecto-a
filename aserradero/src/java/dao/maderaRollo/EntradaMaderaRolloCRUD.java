package dao.maderaRollo;

import dao.Conexion;
import entidades.maderaRollo.EntradaMaderaRollo;
import interfaces.OperacionesCRUD;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author rcortes Modificador por lmarcoss
 */
public class EntradaMaderaRolloCRUD extends Conexion implements OperacionesCRUD {

    @Override
    public void registrar(Object objeto) throws Exception {
        EntradaMaderaRollo entrada = (EntradaMaderaRollo) objeto;
        try {
            this.abrirConexion();
            PreparedStatement st = this.conexion.prepareStatement(
                    "INSERT INTO ENTRADA_M_ROLLO ("
                    + "fecha,id_proveedor,id_chofer,id_empleado,num_pieza_primario,volumen_primario,costo_primario,"
                    + "num_pieza_secundario,volumen_secundario,costo_secundario,"
                    + "num_pieza_terciario,volumen_terciario,costo_terciario,id_pago )"
                    + "values(?,?,?,?,?,?,null,?,?,null,?,?,null,?)"); // Los valores nulos se cargan con el trigger de la tabla en sql
            st = cargarObject(st, entrada);
            st.executeUpdate();
        } catch (Exception e) {
            System.err.println(e);
            throw e;
        } finally {
            this.cerrarConexion();
        }
    }

    @Override
    public PreparedStatement cargarObject(PreparedStatement st, Object objeto) throws SQLException {
        EntradaMaderaRollo entrada = (EntradaMaderaRollo) objeto;
        st.setDate(1, entrada.getFecha());
        st.setString(2, entrada.getId_proveedor());
        st.setString(3, entrada.getId_chofer());
        st.setString(4, entrada.getId_empleado());
        st.setInt(5, entrada.getNum_pieza_primario());
        st.setBigDecimal(6, entrada.getVolumen_primario());
        st.setInt(7, entrada.getNum_pieza_secundario());
        st.setBigDecimal(8, entrada.getVolumen_secundario());
        st.setInt(9, entrada.getNum_pieza_terciario());
        st.setBigDecimal(10, entrada.getVolumen_terciario());
        st.setInt(11, 0);
        return st;
    }

    @Override
    public <T> List listar(String id_jefe, String rol) throws Exception {
        List<EntradaMaderaRollo> entradas = null;
        String consulta;
        if (rol.equals("Administrador")) {
            consulta = "SELECT * FROM VISTA_ENTRADA_M_ROLLO WHERE id_jefe = ? ORDER BY fecha desc";
        } else {
            consulta = "SELECT * FROM VISTA_ENTRADA_M_ROLLO WHERE id_jefe = ? AND fecha = CURDATE() ORDER BY fecha desc";
        }
        try {
            this.abrirConexion();
            try (PreparedStatement st = this.conexion.prepareStatement(consulta)) {
                st.setString(1, id_jefe);
                entradas = new ArrayList<>();
                try (ResultSet rs = st.executeQuery()) {
                    while (rs.next()) {
                        EntradaMaderaRollo entrada = (EntradaMaderaRollo) extraerObject(rs);
                        entradas.add(entrada);
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
            cerrarConexion();
        }
        return entradas;
    }

    @Override
    public Object extraerObject(ResultSet rs) throws SQLException {
        EntradaMaderaRollo entrada = new EntradaMaderaRollo();
        entrada.setId_entrada(rs.getInt("id_entrada"));
        entrada.setFecha(rs.getDate("fecha"));
        entrada.setId_proveedor(rs.getString("id_proveedor"));
        entrada.setProveedor(rs.getString("proveedor"));
        entrada.setId_chofer(rs.getString("id_chofer"));
        entrada.setChofer(rs.getString("chofer"));
        entrada.setId_empleado(rs.getString("id_empleado"));
        entrada.setEmpleado(rs.getString("empleado"));
        entrada.setId_jefe(rs.getString("id_jefe"));
        entrada.setNum_pieza_primario(rs.getInt("num_pieza_primario"));
        entrada.setVolumen_primario(rs.getBigDecimal("volumen_primario"));
        entrada.setCosto_primario(rs.getBigDecimal("costo_primario"));
        entrada.setNum_pieza_secundario(rs.getInt("num_pieza_secundario"));
        entrada.setVolumen_secundario(rs.getBigDecimal("volumen_secundario"));
        entrada.setCosto_secundario(rs.getBigDecimal("costo_secundario"));
        entrada.setNum_pieza_terciario(rs.getInt("num_pieza_terciario"));
        entrada.setVolumen_terciario(rs.getBigDecimal("volumen_terciario"));
        entrada.setCosto_terciario(rs.getBigDecimal("costo_terciario"));
        entrada.setNum_pieza_total(rs.getInt("num_pieza_total"));
        entrada.setVolumen_total(rs.getBigDecimal("volumen_total"));
        entrada.setCosto_total(rs.getBigDecimal("costo_total"));
        entrada.setId_pago(rs.getInt("id_pago"));

        return entrada;
    }

    @Override
    public Object modificar(Object objeto) throws Exception {
        EntradaMaderaRollo entradaM = (EntradaMaderaRollo) objeto;
        EntradaMaderaRollo entrada = null;
        this.abrirConexion();
        try (PreparedStatement st = this.conexion.prepareStatement("SELECT * FROM VISTA_ENTRADA_M_ROLLO WHERE id_entrada = ?")) {
            st.setInt(1, entradaM.getId_entrada());
            try (ResultSet rs = st.executeQuery()) {
                while (rs.next()) {
                    entrada = (EntradaMaderaRollo) extraerObject(rs);
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return entrada;
    }

    @Override
    public void actualizar(Object objeto) throws Exception {
        EntradaMaderaRollo entrada = (EntradaMaderaRollo) objeto;
        try {
            this.abrirConexion();
            PreparedStatement st = this.conexion.prepareStatement("UPDATE ENTRADA_M_ROLLO SET id_proveedor= ?, id_chofer = ?, "
                    + "num_pieza_primario = ?, volumen_primario = ?, "
                    + "num_pieza_secundario = ?, volumen_secundario = ?, "
                    + "num_pieza_terciario = ?, volumen_terciario = ? WHERE id_entrada = ?");
            st.setString(1, entrada.getId_proveedor());
            st.setString(2, entrada.getId_chofer());
            st.setInt(3, entrada.getNum_pieza_primario());
            st.setBigDecimal(4, entrada.getVolumen_primario());
            st.setInt(5, entrada.getNum_pieza_secundario());
            st.setBigDecimal(6, entrada.getVolumen_secundario());
            st.setInt(7, entrada.getNum_pieza_terciario());
            st.setBigDecimal(8, entrada.getVolumen_terciario());
            st.setInt(9, entrada.getId_entrada());
            st.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
            throw e;
        } finally {
            this.cerrarConexion();
        }
    }

    @Override
    public void eliminar(Object objeto) throws Exception {
        EntradaMaderaRollo entrada = (EntradaMaderaRollo) objeto;
        try {
            this.abrirConexion();
            PreparedStatement st = this.conexion.prepareStatement("DELETE FROM ENTRADA_M_ROLLO WHERE id_entrada = ?");
            st.setInt(1, entrada.getId_entrada());
            st.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
            throw e;
        } finally {
            this.cerrarConexion();
        }
    }

    @Override
    public <T> List buscar(String nombre_campo, String dato, String id_jefe, String rol) throws Exception {
        List<EntradaMaderaRollo> entradas;
        String consulta;
        if (rol.equals("Administrador")) {
            consulta = "SELECT * FROM VISTA_ENTRADA_M_ROLLO WHERE " + nombre_campo + " like ? AND id_jefe = ? ORDER BY fecha desc";
        } else {
            consulta = "SELECT * FROM VISTA_ENTRADA_M_ROLLO WHERE " + nombre_campo + " like ? AND id_jefe = ? AND fecha = CURDATE() ORDER BY fecha desc";
        }
        try {
            this.abrirConexion();
            try (PreparedStatement st = this.conexion.prepareStatement(consulta)) {
                st.setString(1, "%" + dato + "%");
                st.setString(2, id_jefe);
                entradas = new ArrayList<>();
                try (ResultSet rs = st.executeQuery()) {
                    while (rs.next()) {
                        EntradaMaderaRollo entrada = (EntradaMaderaRollo) extraerObject(rs);
                        entradas.add(entrada);
                    }
                }
            }
        } catch (Exception e) {
            System.out.println(e);
            throw e;
        } finally {
            this.cerrarConexion();
        }
        return entradas;
    }

    public List<EntradaMaderaRollo> consultarMaderaPago(int id_pago, String id_proveedor) throws Exception {
        List<EntradaMaderaRollo> entradas;
        try {
            this.abrirConexion();
            try (PreparedStatement st = this.conexion.prepareStatement("SELECT * FROM VISTA_ENTRADA_M_ROLLO WHERE id_proveedor = ? AND id_pago = ? ORDER BY fecha DESC")) {
                st.setString(1, id_proveedor);
                st.setInt(2, id_pago);
                entradas = new ArrayList<>();
                try (ResultSet rs = st.executeQuery()) {
                    while (rs.next()) {
                        EntradaMaderaRollo entrada = (EntradaMaderaRollo) extraerObject(rs);
                        entradas.add(entrada);
                    }
                }
            }
        } catch (Exception e) {
            System.out.println(e);
            throw e;
        } finally {
            this.cerrarConexion();
        }
        return entradas;
    }

    public EntradaMaderaRollo consultarTotalMaderaPago(int id_pago, String id_proveedor) throws Exception {
        EntradaMaderaRollo entrada = null;
        try {
            this.abrirConexion();
            try (PreparedStatement st = this.conexion.prepareStatement(
                    "SELECT "
                    + "SUM(num_pieza_primario) AS num_pieza_primario, "
                    + "SUM(volumen_primario) AS volumen_primario, "
                    + "SUM(num_pieza_secundario) AS num_pieza_secundario, "
                    + "SUM(volumen_secundario) AS volumen_secundario, "
                    + "SUM(num_pieza_terciario) AS num_pieza_terciario, "
                    + "SUM(volumen_terciario) AS volumen_terciario, "
                    + "SUM(num_pieza_total) AS num_pieza_total, "
                    + "SUM(volumen_total) AS volumen_total, "
                    + "SUM(costo_total) AS costo_total "
                    + "FROM VISTA_ENTRADA_M_ROLLO WHERE id_proveedor = ? AND id_pago = ? GROUP BY id_pago, id_proveedor")) {
                st.setString(1, id_proveedor);
                st.setInt(2, id_pago);
                try (ResultSet rs = st.executeQuery()) {
                    while (rs.next()) {
                        entrada = (EntradaMaderaRollo) extraerTotalEntrada(rs);
                    }
                }
            }
        } catch (Exception e) {
            System.out.println(e);
            throw e;
        } finally {
            this.cerrarConexion();
        }
        return entrada;
    }

    private EntradaMaderaRollo extraerTotalEntrada(ResultSet rs) throws SQLException {
        EntradaMaderaRollo entrada = new EntradaMaderaRollo();
        entrada.setNum_pieza_primario(rs.getInt("num_pieza_primario"));
        entrada.setVolumen_primario(rs.getBigDecimal("volumen_primario"));
        entrada.setNum_pieza_secundario(rs.getInt("num_pieza_secundario"));
        entrada.setVolumen_secundario(rs.getBigDecimal("volumen_secundario"));
        entrada.setNum_pieza_terciario(rs.getInt("num_pieza_terciario"));
        entrada.setVolumen_terciario(rs.getBigDecimal("volumen_terciario"));
        entrada.setNum_pieza_total(rs.getInt("num_pieza_total"));
        entrada.setVolumen_total(rs.getBigDecimal("volumen_total"));
        entrada.setCosto_total(rs.getBigDecimal("costo_total"));
        return entrada;
    }

}
