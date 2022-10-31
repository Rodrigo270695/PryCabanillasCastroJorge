
package controlador;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import modelo.Cliente;

public class ClienteController {
    
    Conexion estado = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    String sql = "";

    public List listar() {

        List lista = new ArrayList();
        sql = "SELECT * FROM cliente ORDER BY cliente_id DESC";

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
//
//    public void eliminar(int id) throws Exception {
//
//        sql = "DELETE FROM cliente WHERE cliente_id = ?";
//
//        try {
//
//            con = estado.conectar();
//            ps = con.prepareStatement(sql);
//            ps.setInt(1, id);
//            ps.executeUpdate();
//
//        } catch (PSQLException pe) {
//            pe.printStackTrace(System.err);
//            throw new Exception("El continente no se puede eliminar, porque está siendo USADO");
//        } catch (SQLException e) {
//            e.printStackTrace(System.err);
//        } finally {
//            try {
//                con.close();
//                ps.close();
//            } catch (SQLException ex) {
//                ex.printStackTrace(System.err);
//            }
//        }
//
//    }
//
//    public Object obtenerdato(int id) {
//
//        Agente cliente = new Agente();
//        sql = "SELECT * FROM cliente WHERE cliente_id = " + id;
//
//        try {
//
//            con = estado.conectar();
//            ps = con.prepareStatement(sql);
//            rs = ps.executeQuery();
//
//            if (rs.next()) {
//                cliente.setAgenteId(rs.getInt(1));
//                cliente.setNombreCompleto(rs.getString(2));
//                cliente.setDireccion(rs.getString(3));
//                cliente.setCorreo(rs.getString(4));
//                cliente.setFoto(rs.getBinaryStream(5));
//            }
//
//        } catch (SQLException e) {
//            e.printStackTrace(System.err);
//        } finally {
//            try {
//                con.close();
//                ps.close();
//                rs.close();
//            } catch (SQLException ex) {
//                ex.printStackTrace(System.err);
//            }
//        }
//
//        return cliente;
//
//    }
//
//    public List buscar(Object obj) {
//
//        List lista = new ArrayList();
//        sql = "SELECT * FROM cliente WHERE nombre_completo LIKE '%"+obj+"%'\n"
//                + "OR direccion LIKE '%"+obj+"%'\n"
//                + "OR correo LIKE '%"+obj+"%'";
//
//        try {
//
//            con = estado.conectar();
//            ps = con.prepareStatement(sql);
//            rs = ps.executeQuery();
//
//            while (rs.next()) {
//                Agente cliente = new Agente();
//                cliente.setAgenteId(rs.getInt(1));
//                cliente.setNombreCompleto(rs.getString(2));
//                cliente.setDireccion(rs.getString(3));
//                cliente.setCorreo(rs.getString(4));
//                cliente.setFoto(rs.getBinaryStream(5));
//                lista.add(cliente);
//            }
//
//        } catch (SQLException e) {
//            e.printStackTrace(System.err);
//        } finally {
//            try {
//                con.close();
//                ps.close();
//                rs.close();
//            } catch (SQLException ex) {
//                ex.printStackTrace(System.err);
//            }
//        }
//
//        return lista;
//
//    }
//
//    public void registrar(Object obj) throws Exception {
//
//        Agente cliente = (Agente) obj;
//        sql = "INSERT INTO cliente(nombre_completo, direccion, correo, foto) VALUES(?,?,?,?)";
//
//        try {
//
//            con = estado.conectar();
//            ps = con.prepareStatement(sql);
//            ps.setString(1, cliente.getNombreCompleto());
//            ps.setString(2, cliente.getDireccion());
//            ps.setString(3, cliente.getCorreo());
//            ps.setBinaryStream(4, cliente.getFoto());
//            ps.executeUpdate();
//
//        } catch (PSQLException pe) {
//            pe.printStackTrace(System.err);
//            throw new Exception("Ya existe el Agente");
//        } catch (SQLException e) {
//            e.printStackTrace(System.err);
//        } finally {
//            try {
//                con.close();
//                ps.close();
//            } catch (SQLException ex) {
//                ex.printStackTrace(System.err);
//            }
//        }
//
//    }
//
//    public void modificar(Object obj) throws Exception {
//
//        Agente cliente = (Agente) obj;
//        sql = "UPDATE cliente SET nombre_completo=?, direccion=?, correo=?, foto=? WHERE cliente_id = ?";
//
//        try {
//
//            con = estado.conectar();
//            ps = con.prepareStatement(sql);
//            ps.setString(1, cliente.getNombreCompleto());
//            ps.setString(2, cliente.getDireccion());
//            ps.setString(3, cliente.getCorreo());
//            ps.setBinaryStream(4, cliente.getFoto());
//            ps.setInt(5, cliente.getAgenteId());
//            ps.executeUpdate();
//
//        } catch (PSQLException pe) {
//            pe.printStackTrace(System.err);
//            throw new Exception("Ya existe el Agente");
//        } catch (SQLException e) {
//            e.printStackTrace(System.err);
//        } finally {
//            try {
//                con.close();
//                ps.close();
//            } catch (SQLException ex) {
//                ex.printStackTrace(System.err);
//            }
//        }
//
//    }
    
}
