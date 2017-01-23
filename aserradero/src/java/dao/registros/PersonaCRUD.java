package dao.registros;

import dao.Conexion;
import entidades.registros.Persona;
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
public class PersonaCRUD extends Conexion implements OperacionesCRUD {

    @Override
    public void registrar(Object objeto) throws Exception {
        Persona persona = (Persona) objeto;
        try {
            this.abrirConexion();
            PreparedStatement st = this.conexion.prepareStatement(
                    "INSERT INTO PERSONA (id_persona,nombre,apellido_paterno,apellido_materno,nombre_localidad,nombre_municipio,estado,direccion,sexo,fecha_nacimiento,telefono) VALUES (?,?,?,?,?,?,?,?,?,?,?)");
            st = cargarObject(st, persona);
            st.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
            throw e;
        } finally {
            this.cerrarConexion();
        }
    }

    @Override
    public PreparedStatement cargarObject(PreparedStatement st, Object objeto) throws SQLException {
        Persona persona = (Persona) objeto;
        st.setString(1, persona.getId_persona());
        st.setString(2, persona.getNombre());
        st.setString(3, persona.getApellido_paterno());
        st.setString(4, persona.getApellido_materno());
        st.setString(5, persona.getNombre_localidad());
        st.setString(6, persona.getNombre_municipio());
        st.setString(7, persona.getEstado());
        st.setString(8, persona.getDireccion());
        st.setString(9, persona.getSexo());
        st.setDate(10, persona.getFecha_nacimiento());
        st.setString(11, persona.getTelefono());
        return st;
    }

    @Override
    public <T> List listar(String id_jefe, String rol) throws Exception {
        List<Persona> personas;
        try {
            this.abrirConexion();
            try (PreparedStatement st = this.conexion.prepareStatement("SELECT * FROM VISTA_PERSONA")) {
                personas = new ArrayList();
                try (ResultSet rs = st.executeQuery()) {
                    while (rs.next()) {
                        Persona persona = (Persona) extraerObject(rs);
                        personas.add(persona);
                    }
                }
            } catch (Exception e) {
                personas = null;
                System.out.println(e);
            }
        } catch (Exception e) {
            System.out.println(e);
            throw e;
        } finally {
            this.cerrarConexion();
        }
        return personas;
    }

    @Override
    public Object extraerObject(ResultSet rs) throws SQLException {
        Persona persona = new Persona();
        persona.setId_persona(rs.getString("id_persona"));
        persona.setNombre(rs.getString("nombre"));
        persona.setNombre_localidad(rs.getString("nombre_localidad"));
        persona.setNombre_municipio(rs.getString("nombre_municipio"));
        persona.setEstado(rs.getString("estado"));
        persona.setDireccion(rs.getString("direccion"));
        persona.setSexo(rs.getString("sexo"));
        persona.setFecha_nacimiento(rs.getDate("fecha_nacimiento"));
        persona.setTelefono(rs.getString("telefono"));
        return persona;
    }

    @Override
    public Object modificar(Object objeto) throws Exception {
        Persona personaM = (Persona) objeto;
        Persona persona = null;
        this.abrirConexion();
        try (PreparedStatement st = this.conexion.prepareStatement("SELECT * FROM PERSONA WHERE id_persona = ?")) {
            st.setString(1, personaM.getId_persona());
            try (ResultSet rs = st.executeQuery()) {
                while (rs.next()) {
                    persona = (Persona) extraerPersona(rs);
                }
            }
        }
        return persona;
    }

    private Object extraerPersona(ResultSet rs) throws SQLException {
        Persona persona = new Persona();
        persona.setId_persona(rs.getString("id_persona"));
        persona.setNombre(rs.getString("nombre"));
        persona.setApellido_paterno(rs.getString("apellido_paterno"));
        persona.setApellido_materno(rs.getString("apellido_materno"));
        persona.setNombre_localidad(rs.getString("nombre_localidad"));
        persona.setNombre_municipio(rs.getString("nombre_municipio"));
        persona.setEstado(rs.getString("estado"));
        persona.setDireccion(rs.getString("direccion"));
        persona.setSexo(rs.getString("sexo"));
        persona.setFecha_nacimiento(rs.getDate("fecha_nacimiento"));
        persona.setTelefono(rs.getString("telefono"));
        return persona;
    }

    @Override
    public void actualizar(Object objeto) throws Exception {
        Persona persona = (Persona) objeto;
        try {
            this.abrirConexion();
            PreparedStatement st = this.conexion.prepareStatement(
                    "UPDATE PERSONA SET nombre = ?, apellido_paterno = ?, apellido_materno = ?, nombre_localidad = ?, nombre_municipio = ?, estado = ?, direccion = ?, sexo = ?, fecha_nacimiento = ?, telefono = ? WHERE id_persona = ?");
            st.setString(1, persona.getNombre());
            st.setString(2, persona.getApellido_paterno());
            st.setString(3, persona.getApellido_materno());
            st.setString(4, persona.getNombre_localidad());
            st.setString(5, persona.getNombre_municipio());
            st.setString(6, persona.getEstado());
            st.setString(7, persona.getDireccion());
            st.setString(8, persona.getSexo());
            st.setDate(9, persona.getFecha_nacimiento());
            st.setString(10, persona.getTelefono());
            st.setString(11, persona.getId_persona());
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
        Persona persona = (Persona) objeto;
        try {
            this.abrirConexion();
            PreparedStatement st = this.conexion.prepareStatement("DELETE FROM PERSONA WHERE id_persona = ?");
            st.setString(1, persona.getId_persona());
            st.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
            throw e;
        } finally {
            this.cerrarConexion();
        }
    }

    public <T> List buscar(String nombre_campo, String dato, String id_jefe, String rol) throws Exception {
        List<Persona> personas;
        try {
            this.abrirConexion();
            try (PreparedStatement st = this.conexion.prepareStatement("SELECT * FROM VISTA_PERSONA WHERE " + nombre_campo + " like ?")) {
                st.setString(1, "%" + dato + "%");
                personas = new ArrayList();
                try (ResultSet rs = st.executeQuery()) {
                    while (rs.next()) {
                        Persona persona = (Persona) extraerObject(rs);
                        personas.add(persona);
                    }
                }
            }
        } catch (Exception e) {
            System.out.println(e);
            throw e;
        } finally {
            this.cerrarConexion();
        }
        return personas;
    }

    public List<Persona> buscarPorId(String id_persona) throws Exception {
        List<Persona> personas = null;
        try {
            this.abrirConexion();
            try (PreparedStatement st = this.conexion.prepareStatement("SELECT * FROM VISTA_PERSONA WHERE id_persona = ?")) {
                st.setString(1, id_persona);
                personas = new ArrayList();
                try (ResultSet rs = st.executeQuery()) {
                    while (rs.next()) {
                        Persona persona = (Persona) extraerObject(rs);
                        personas.add(persona);
                    }
                }
            }
        } catch (Exception e) {
            System.out.println(e);
            throw e;
        } finally {
            this.cerrarConexion();
        }
        return personas;
    }

    // registro: define para qué se desea registrar las personas a consultar: para administrador, cliente o  proveedor
    public <T> List listarPersonasPara(String registro, String id_jefe) throws Exception {
        //Creamos la consulta
        String consulta = "";
        switch (registro) {
            case "cliente": // Personas para nuevos clientes
                consulta = "SELECT * FROM VISTA_PERSONA WHERE id_persona NOT IN (SELECT id_persona FROM CLIENTE WHERE id_jefe = ?)";
                break;
            case "proveedor":// Personas para nuevo proveedor
                consulta = "SELECT * FROM VISTA_PERSONA WHERE id_persona NOT IN (SELECT id_persona FROM PROVEEDOR WHERE id_jefe = ?)";
                break;
            case "administrador":// Personas para nuevo administrador
                consulta = "SELECT * FROM VISTA_PERSONA WHERE id_persona NOT IN (SELECT id_persona FROM EMPLEADO WHERE rol = 'Administrador');";
                break;
        }
        //Ejecutamos la consulta
        List<Persona> personas;
        try {
            this.abrirConexion();
            try (PreparedStatement st = this.conexion.prepareStatement(consulta)) {
                if (registro.equals("cliente") || registro.equals("proveedor")) {
                    st.setString(1, id_jefe);
                }
                personas = new ArrayList();
                try (ResultSet rs = st.executeQuery()) {
                    while (rs.next()) {
                        Persona persona = (Persona) extraerObject(rs);
                        personas.add(persona);
                    }
                }
            } catch (Exception e) {
                personas = null;
                System.out.println(e);
            }
        } catch (Exception e) {
            System.out.println(e);
            throw e;
        } finally {
            this.cerrarConexion();
        }
        return personas;
    }

    public boolean existePersona(String id_persona) throws Exception {
        boolean existe = false;
        try {
            this.abrirConexion();
            try (PreparedStatement st = this.conexion.prepareStatement("SELECT * FROM PERSONA WHERE id_persona = ?")) {
                st.setString(1, id_persona);
                try (ResultSet rs = st.executeQuery()) {
                    while (rs.next()) {
                        existe = true;
                    }
                }
            }
        } catch (Exception e) {
            System.out.println(e);
            throw e;
        } finally {
            this.cerrarConexion();
        }
        return existe;
    }
}
