package dao.registros;

import dao.Conexion;
import entidades.registros.Cliente;
import interfaces.OperacionesCRUD;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author lmarcoss
 */
public class ClienteCRUD extends Conexion implements OperacionesCRUD {

    @Override
    public void registrar(Object objeto) throws Exception {
        Cliente cliente = (Cliente) objeto;
        try {
            this.abrirConexion();
            PreparedStatement st = this.conexion.prepareStatement(
                    "INSERT INTO CLIENTE (id_cliente,id_persona,id_jefe) VALUES (?,?,?)");
            st = cargarObject(st, cliente);
            st.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
            throw e;
        } finally {
            this.cerrarConexion();
        }
    }

    @Override
    public <T> List listar(String id_jefe, String rol) throws Exception {
        List<Cliente> clientes;
        try {
            this.abrirConexion();
            try (PreparedStatement st = this.conexion.prepareStatement("SELECT * FROM PERSONAL_CLIENTE WHERE id_jefe = ?")) {
                st.setString(1, id_jefe);
                clientes = new ArrayList();
                try (ResultSet rs = st.executeQuery()) {
                    while (rs.next()) {
                        Cliente cliente = (Cliente) extraerObject(rs);
                        clientes.add(cliente);
                    }
                }
            } catch (Exception e) {
                clientes = null;
                System.out.println(e);
            }
        } catch (Exception e) {
            System.out.println(e);
            throw e;
        } finally {
            this.cerrarConexion();
        }
        return clientes;
    }

    @Override
    public Object modificar(Object objeto) throws Exception {
        return null;
    }

    @Override
    public void actualizar(Object objeto) throws Exception {
    }

    @Override
    public void eliminar(Object objeto) throws Exception {
        Cliente cliente = (Cliente) objeto;
        try {
            this.abrirConexion();
            PreparedStatement st = this.conexion.prepareStatement("DELETE FROM CLIENTE WHERE id_cliente = ? AND id_jefe = ?");
            st.setString(1, cliente.getId_cliente());
            st.setString(2, cliente.getId_jefe());
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
        List<Cliente> clientes;
        try {
            this.abrirConexion();
            try (PreparedStatement st = this.conexion.prepareStatement("SELECT * FROM PERSONAL_CLIENTE WHERE " + nombre_campo + " like ? AND id_jefe = ?")) {
                st.setString(1, "%" + dato + "%");
                st.setString(2, id_jefe);
                clientes = new ArrayList();
                try (ResultSet rs = st.executeQuery()) {
                    while (rs.next()) {
                        Cliente cliente = (Cliente) extraerObject(rs);
                        clientes.add(cliente);
                    }
                }
            }
        } catch (Exception e) {
            System.out.println(e);
            throw e;
        } finally {
            this.cerrarConexion();
        }
        return clientes;
    }

    @Override
    public Object extraerObject(ResultSet rs) throws SQLException {
        Cliente cliente = new Cliente();
        cliente.setId_cliente(rs.getString("id_cliente"));
        cliente.setId_persona(rs.getString("id_persona"));
        cliente.setCliente(rs.getString("cliente"));
        cliente.setId_jefe(rs.getString("id_jefe"));
        cliente.setJefe(rs.getString("jefe"));
        return cliente;
    }

    @Override
    public PreparedStatement cargarObject(PreparedStatement st, Object objeto) throws SQLException {
        Cliente cliente = (Cliente) objeto;
        st.setString(1, cliente.getId_persona()); //se cambia con el trigger de la BD
        st.setString(2, cliente.getId_persona());
        st.setString(3, cliente.getId_jefe());
        return st;
    }
}
