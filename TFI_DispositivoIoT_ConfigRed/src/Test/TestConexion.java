package Test;

import ar.edu.utn.prog2int.Config.DataBaseConnection;
import java.sql.Connection;


public class TestConexion {
    
    public static void main(String[] args) {
        try (Connection con = DataBaseConnection.getConnection()) {
            if (con != null) {
                System.out.println("Conexión exitosa usando .env");
            }
        } catch (Exception e) {
            System.out.println("Error de conexión: " + e.getMessage());
        }
    }
    
}
