
package controlador;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import modelo.Cliente;
import org.postgresql.util.PSQLException;

public class ClienteController {
    
    Conexion estado = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    String sql = "";

    public List listar() {

        List lista = new ArrayList();
        sql = "SELECT * FROM cliente ORDER BY clienteid DESC";

        try {

            con = estado.conectar();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                Cliente cliente = new Cliente();
                cliente.setClienteId(rs.getInt(1));
                cliente.setDocumento(rs.getString(2));
                cliente.setNombres(rs.getString(3));
                cliente.setApellidos(rs.getString(4));
                cliente.setDireccion(rs.getString(5));
                cliente.setTelefono(rs.getString(6));
                cliente.setCorreo(rs.getString(7));
                lista.add(cliente);
            }

        } catch (SQLException e) {
            e.printStackTrace(System.err);
        } finally {
            try {
                con.close();
                ps.close();
                rs.close();
            } catch (SQLException ex) {
                ex.printStackTrace(System.err);
            }
        }

        return lista;

    }

    public void eliminar(int id) throws Exception {

        sql = "DELETE FROM cliente WHERE clienteid = ?";

        try {

            con = estado.conectar();
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();

        } catch (PSQLException pe) {
            pe.printStackTrace(System.err);
            throw new Exception("El continente no se puede eliminar, porque está siendo USADO");
        } catch (SQLException e) {
            e.printStackTrace(System.err);
        } finally {
            try {
                con.close();
                ps.close();
            } catch (SQLException ex) {
                ex.printStackTrace(System.err);
            }
        }

    }

    public Cliente obtenerdato(int id) {

        Cliente cliente = new Cliente();
        sql = "SELECT * FROM cliente WHERE clienteid = " + id;

        try {

            con = estado.conectar();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            if (rs.next()) {
                cliente.setClienteId(rs.getInt(1));
                cliente.setDocumento(rs.getString(2));
                cliente.setNombres(rs.getString(3));
                cliente.setApellidos(rs.getString(4));
                cliente.setDireccion(rs.getString(5));
                cliente.setTelefono(rs.getString(6));
                cliente.setCorreo(rs.getString(7));
            }

        } catch (SQLException e) {
            e.printStackTrace(System.err);
        } finally {
            try {
                con.close();
                ps.close();
                rs.close();
            } catch (SQLException ex) {
                ex.printStackTrace(System.err);
            }
        }

        return cliente;

    }

    public List buscar(Object obj) {

        List lista = new ArrayList();
        sql = "SELECT * FROM cliente WHERE nombres LIKE '%"+obj+"%'\n"
                + "OR apellidos LIKE '%"+obj+"%'\n"
                + "OR documentoc LIKE '%"+obj+"%'";

        try {

            con = estado.conectar();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                Cliente cliente = new Cliente();
                cliente.setClienteId(rs.getInt(1));
                cliente.setDocumento(rs.getString(2));
                cliente.setNombres(rs.getString(3));
                cliente.setApellidos(rs.getString(4));
                cliente.setDireccion(rs.getString(5));
                cliente.setTelefono(rs.getString(6));
                cliente.setCorreo(rs.getString(7));
                lista.add(cliente);
            }

        } catch (SQLException e) {
            e.printStackTrace(System.err);
        } finally {
            try {
                con.close();
                ps.close();
                rs.close();
            } catch (SQLException ex) {
                ex.printStackTrace(System.err);
            }
        }

        return lista;

    }

    public void registrar(Cliente cliente) throws Exception {

        sql = "INSERT INTO cliente(documentoc, nombres, apellidos, direccion,telefono,correo) "
                + "VALUES(?,?,?,?,?,?)";

        try {

            con = estado.conectar();
            ps = con.prepareStatement(sql);
            ps.setString(1, cliente.getDocumento());
            ps.setString(2, cliente.getNombres());
            ps.setString(3, cliente.getApellidos());
            ps.setString(4, cliente.getDireccion());
            ps.setString(5, cliente.getTelefono());
            ps.setString(6, cliente.getCorreo());
            ps.executeUpdate();

        } catch (PSQLException pe) {
            pe.printStackTrace(System.err);
            throw new Exception("Ya existe el Cliente");
        } catch (SQLException e) {
            e.printStackTrace(System.err);
        } finally {
            try {
                con.close();
                ps.close();
            } catch (SQLException ex) {
                ex.printStackTrace(System.err);
            }
        }

    }

    public void modificar(Object obj) throws Exception {

        Cliente cliente = (Cliente) obj;
        sql = "UPDATE cliente SET documentoc=?,nombres=?,apellidos=?,direccion=?,telefono=?,correo=? "
                + "WHERE clienteid = ?";

        try {

            con = estado.conectar();
            ps = con.prepareStatement(sql);
            ps.setString(1, cliente.getDocumento());
            ps.setString(2, cliente.getNombres());
            ps.setString(3, cliente.getApellidos());
            ps.setString(4, cliente.getDireccion());
            ps.setString(5, cliente.getTelefono());
            ps.setString(6, cliente.getCorreo());
            ps.setInt(7, cliente.getClienteId());
            ps.executeUpdate();

        } catch (PSQLException pe) {
            pe.printStackTrace(System.err);
            throw new Exception("Ya existe el Cliente");
        } catch (SQLException e) {
            e.printStackTrace(System.err);
        } finally {
            try {
                con.close();
                ps.close();
            } catch (SQLException ex) {
                ex.printStackTrace(System.err);
            }
        }

    }
    
}
