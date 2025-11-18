package ar.edu.utn.prog2int.Config;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

// Gestiona la conexion con la base de datos MYSQL
public class DataBaseConnection {

    private static Connection connection = null;

    public static Connection getConnection() throws SQLException {
        if (connection == null || connection.isClosed()) {
            try {
                
                // Carga las variables del archivo .env
                Properties props = new Properties();
                props.load(new FileInputStream(".env"));

                String url = props.getProperty("DB_URL");
                String user = props.getProperty("DB_USER");
                String password = props.getProperty("DB_PASSWORD");

                connection = DriverManager.getConnection(url, user, password);
                System.out.println("Conexión establecida con la base de datos.");
            } catch (IOException e) {
                System.err.println("Error al leer el archivo .env: " + e.getMessage());
                throw new SQLException("Error cargando configuración desde .env.");
            } catch (SQLException e) {
                System.err.println("Error al conectar con la base de datos: " + e.getMessage());
                throw e;
            }
        }
        return connection;
    }

    public static void closeConnection() {
        try {
            
            if (connection != null && !connection.isClosed()) {
                connection.close();
                System.out.println("Conexión cerrada correctamente.");
            }
        } catch (SQLException e) {
            System.err.println("Error al cerrar la conexión: " + e.getMessage());
        }
    }

}
