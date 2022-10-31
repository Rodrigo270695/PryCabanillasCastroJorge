
package controlador;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
    
     private final String URL = "jdbc:postgresql://localhost:5432/bd_prestamo";
    private final String USER = "postgres";
    private final String PASS = "admin";
    
    public Connection conectar(){
        
        Connection con = null;
        
        try {
            
            con = DriverManager.getConnection(URL, USER, PASS);
            
        } catch (SQLException e) {
            e.printStackTrace(System.err);
        }
        
        return con;
    }
    
}
