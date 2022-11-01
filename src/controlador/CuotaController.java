
package controlador;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import modelo.Cuota;
import org.postgresql.util.PSQLException;

public class CuotaController {
    
    Conexion estado = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    String sql = "";
    
    public void generarCuotas(Cuota cuota){
        
        sql = "insert into cuota(fecha_pago,monto_cuota,monto_interes,prestamoid) "
                + "values(?,?,?,?)";
        
        try {
            
            con = estado.conectar();
            ps = con.prepareStatement(sql);
            ps.setDate(1, cuota.getFechaPago());
            ps.setDouble(2, cuota.getMontoCuota());
            ps.setDouble(3, cuota.getMontoInteres());
            ps.setInt(4, cuota.getPrestamo().getPrestamoId());
            ps.executeUpdate();
            
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
