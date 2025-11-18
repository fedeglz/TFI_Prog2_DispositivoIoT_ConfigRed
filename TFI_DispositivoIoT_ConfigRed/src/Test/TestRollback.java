package Test;

import ar.edu.utn.prog2int.Config.DataBaseConnection;
import java.sql.Connection;
import java.sql.SQLException;



public class TestRollback {

    public static void main(String[] args) {
        
        System.out.println("=== TEST DE ROLLBACK TRANSACCIONAL ===");

        try (Connection con = DataBaseConnection.getConnection()) {
            
            // Desactivamos autocommit para simular transacción manual
            con.setAutoCommit(false);

            try {
                System.out.println("Intentando insertar datos válidos y luego un error...");

                //1. Insertar una configuración válida
                String sql1 = "INSERT INTO configuracion_red (ip, mascara, gateway, dns_primario, dhcp_habilitado) VALUES ('10.0.0.1', '255.255.255.0', '10.0.0.254', '8.8.8.8', true)";
                con.prepareStatement(sql1).executeUpdate();
                System.out.println("Configuración insertada correctamente.");

                //2. Intentar insertar un dispositivo con una configuración inexistente (id_configuracion = 999)
                String sql2 = "INSERT INTO dispositivo_iot (eliminado, serial, modelo, ubicacion, firmware_version, id_configuracion) VALUES (false, 'ROLLBACK-001', 'ESP32-RB', 'Prueba Rollback', 'v1.0', 999)";
                con.prepareStatement(sql2).executeUpdate();

                // Si llegara hasta acá (no debería), hacemos commit
                con.commit();
                System.out.println("Transacción completada (esto no debería verse).");

            } catch (SQLException e) {
                System.err.println("Error detectado: " + e.getMessage());
                System.out.println("Ejecutando rollback...");
                con.rollback();
                System.out.println("Rollback ejecutado correctamente.");
            }

        } catch (SQLException e) {
            System.err.println("Error general: " + e.getMessage());
        }
    }
}

    
