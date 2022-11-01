
package controlador;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import modelo.Prestamo;
import org.postgresql.util.PSQLException;

public class PrestamoController {
    
    ClienteController clienteC = new ClienteController();
    Conexion estado = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    String sql = "";
    
    public List listar() {

        List lista = new ArrayList();
        sql = "SELECT * FROM prestamo ORDER BY prestamoid DESC";

        try {

            con = estado.conectar();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                Prestamo prestamo = new Prestamo();
                prestamo.setPrestamoId(rs.getInt(1));
                prestamo.setFecha(rs.getDate(2));
                prestamo.setMonto(rs.getDouble(3));
                prestamo.setMoneda(rs.getString(4));
                prestamo.setCuotas(rs.getInt(5));
                prestamo.setInteres(rs.getInt(6));
                prestamo.setCliente(clienteC.obtenerdato(rs.getInt(7)));
                lista.add(prestamo);
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

        sql = "DELETE FROM prestamo WHERE prestamoid = ?";

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

    public Prestamo obtenerdato(int id) {

        Prestamo prestamo = new Prestamo();
        sql = "SELECT * FROM prestamo WHERE prestamoid = " + id;

        try {

            con = estado.conectar();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            if (rs.next()) {
                prestamo.setPrestamoId(rs.getInt(1));
                prestamo.setFecha(rs.getDate(2));
                prestamo.setMonto(rs.getDouble(3));
                prestamo.setMoneda(rs.getString(4));
                prestamo.setCuotas(rs.getInt(5));
                prestamo.setInteres(rs.getInt(6));
                prestamo.setCliente(clienteC.obtenerdato(rs.getInt(7)));
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

        return prestamo;

    }

//    public List buscar(Object obj) {
//
//        List lista = new ArrayList();
//        sql = "SELECT * FROM prestamo WHERE nombres LIKE '%"+obj+"%'\n"
//                + "OR apellidos LIKE '%"+obj+"%'\n"
//                + "OR documentoc LIKE '%"+obj+"%'";
//
//        try {
//
//            con = estado.conectar();
//            ps = con.prepareStatement(sql);
//            rs = ps.executeQuery();
//
//            while (rs.next()) {
//                Prestamo prestamo = new Prestamo();
//                prestamo.setPrestamoId(rs.getInt(1));
//                prestamo.setDocumento(rs.getString(2));
//                prestamo.setNombres(rs.getString(3));
//                prestamo.setApellidos(rs.getString(4));
//                prestamo.setDireccion(rs.getString(5));
//                prestamo.setTelefono(rs.getString(6));
//                prestamo.setCorreo(rs.getString(7));
//                lista.add(prestamo);
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

    public void registrar(Prestamo prestamo) throws Exception {

        sql = "INSERT INTO prestamo(fecha, monto, moneda, cuotas,interes,clienteid) "
                + "VALUES(?,?,?,?,?,?)";

        try {

            con = estado.conectar();
            ps = con.prepareStatement(sql);
            ps.setDate(1, prestamo.getFecha());
            ps.setDouble(2, prestamo.getMonto());
            ps.setString(3, prestamo.getMoneda());
            ps.setInt(4, prestamo.getCuotas());
            ps.setDouble(5, prestamo.getInteres());
            ps.setInt(6, prestamo.getCliente().getClienteId());
            ps.executeUpdate();

        } catch (PSQLException pe) {
            pe.printStackTrace(System.err);
            throw new Exception("Ya existe el Prestamo");
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

//    public void modificar(Object obj) throws Exception {
//
//        Prestamo prestamo = (Prestamo) obj;
//        sql = "UPDATE prestamo SET documentoc=?,nombres=?,apellidos=?,direccion=?,telefono=?,correo=? "
//                + "WHERE prestamoid = ?";
//
//        try {
//
//            con = estado.conectar();
//            ps = con.prepareStatement(sql);
//            ps.setString(1, prestamo.getDocumento());
//            ps.setString(2, prestamo.getNombres());
//            ps.setString(3, prestamo.getApellidos());
//            ps.setString(4, prestamo.getDireccion());
//            ps.setString(5, prestamo.getTelefono());
//            ps.setString(6, prestamo.getCorreo());
//            ps.setInt(7, prestamo.getPrestamoId());
//            ps.executeUpdate();
//
//        } catch (PSQLException pe) {
//            pe.printStackTrace(System.err);
//            throw new Exception("Ya existe el Prestamo");
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
    
}
