package ar.edu.utn.prog2int.Config;



import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


// Gestiona la conexion con la base de datos MYSQL
public class DataBaseConnection {
    
    //Datos de conexión — adaptá según tu entorno
    private static final String URL = "jdbc:mysql://localhost:3306/iot_db?useSSL=false&serverTimezone=UTC";
    private static final String USER = "root";       // usuario MySQL
    private static final String PASSWORD = "";       // contraseña MySQL

    private static Connection connection = null;

    /**
     * Devuelve una conexión activa a la base de datos.
     */
    public static Connection getConnection() throws SQLException {
        if (connection == null || connection.isClosed()) {
            try {
                connection = DriverManager.getConnection(URL, USER, PASSWORD);
                System.out.println("✅Conexión establecida con la base de datos.");
            } catch (SQLException e) {
                System.err.println("❌Error al conectar con la base de datos: " + e.getMessage());
                throw e;
            }
        }
        return connection;
    }

    /**
     * Cierra la conexión activa (si existe).
     */
    public static void closeConnection() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
                System.out.println("Conexión cerrada correctamente.");
            }
        } catch (SQLException e) {
            System.err.println("❌Error al cerrar la conexión: " + e.getMessage());
        }
    }
    
    
}
