package ar.edu.utn.prog2int.Main;

import ar.edu.utn.prog2int.Config.DataBaseConnection;
import java.sql.Connection;
import java.sql.SQLException;

public class TestConexion {
    
    public static void main(String[] args) {
        try {
            Connection conn = DataBaseConnection.getConnection();
            if (conn != null && !conn.isClosed()) {
                System.out.println("✅ Prueba de conexión exitosa con la base de datos iot_db");
            }
            DataBaseConnection.closeConnection();
        } catch (SQLException e) {
            System.err.println("❌ Error durante la prueba de conexión: " + e.getMessage());
        }
    }
    
}
