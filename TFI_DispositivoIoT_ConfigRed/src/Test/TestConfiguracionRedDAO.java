
package Test;

import ar.edu.utn.prog2int.Dao.ConfiguracionRedDAO;
import ar.edu.utn.prog2int.Models.ConfiguracionRed;
import ar.edu.utn.prog2int.Config.DataBaseConnection;
import java.sql.Connection;
import java.util.List;


public class TestConfiguracionRedDAO {   

    public static void main(String[] args) {
        
          try (Connection con = DataBaseConnection.getConnection()) {
            ConfiguracionRedDAO dao = new ConfiguracionRedDAO();

            System.out.println("=== INSERT ===");
            ConfiguracionRed nueva = new ConfiguracionRed(
                    "192.168.1.100",
                    "255.255.255.0",
                    "192.168.1.1",
                    "8.8.8.8",
                    true
            );
            dao.insertar(nueva, con);

            System.out.println("\n=== LISTAR TODAS ===");
            List<ConfiguracionRed> lista = dao.getAll();
            for (ConfiguracionRed c : lista) {
                System.out.println(c);
            }

            System.out.println("\n=== OBTENER POR ID ===");
            ConfiguracionRed buscada = dao.getById(1); // cambia el ID si hace falta
            if (buscada != null) {
                System.out.println("Configuración encontrada: " + buscada);
            } else {
                System.out.println("No se encontró la configuración con ese ID.");
            }

            System.out.println("\n=== UPDATE ===");
            if (buscada != null) {
                buscada.setDnsPrimario("1.1.1.1");
                buscada.setGateway("192.168.1.254");
                dao.actualizar(buscada, con);
                System.out.println("Configuración actualizada: " + buscada);
            }

            System.out.println("\n=== DELETE (LÓGICO) ===");
            dao.eliminar(2); // cambia el ID que quieras eliminar
            System.out.println("Eliminación lógica realizada.");

            System.out.println("\n=== LISTAR DESPUÉS DE DELETE ===");
            List<ConfiguracionRed> listaFinal = dao.getAll();
            for (ConfiguracionRed c : listaFinal) {
                System.out.println(c);
            }

        } catch (Exception e) {
            System.out.println("Error durante las pruebas: " + e.getMessage());
            e.printStackTrace();
        }
    }
}

    
