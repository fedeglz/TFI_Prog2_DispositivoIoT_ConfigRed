package Test;

import ar.edu.utn.prog2int.Models.ConfiguracionRed;
import ar.edu.utn.prog2int.Service.ConfiguracionRedService;

public class TestConfiguracionRedService {

    public static void main(String[] args) {
        ConfiguracionRedService service = new ConfiguracionRedService();

        // INSERT
        try {

            System.out.println("=== TEST: INSERT ===");
            ConfiguracionRed nueva = new ConfiguracionRed(
                    "10.10.10.10",
                    "255.255.255.0",
                    "10.10.10.1",
                    "8.8.4.4",
                    false
            );

            service.insertar(nueva);

            System.out.println("\n=== TEST: GET ALL ===");
            for (ConfiguracionRed c : service.getAll()) {
                System.out.println(c);
            }

            System.out.println("\n=== TEST: UPDATE ===");
            ConfiguracionRed modificar = new ConfiguracionRed(
                    9L, false, // ID existente + no eliminado
                    "192.168.1.200", "255.255.255.0", "192.168.1.1", "1.1.1.1", true
            );
            service.actualizar(modificar);

        } catch (Exception e) {
            e.printStackTrace();
        }

        // UPDATE
        try {
            // Buscamos un registro existente
            ConfiguracionRed config = new ConfiguracionRed(1L, false, "192.168.0.200",
                    "255.255.255.0", "192.168.0.1", "8.8.4.4", true);

            // Actualizamos el registro
            service.actualizar(config);

        } catch (Exception e) {
            e.printStackTrace();
        }

        // ELIMINAR
        System.out.println("\n=== TEST: DELETE (BAJA LÓGICA) ===");

        try {
            service.eliminar(3L); // Por ejemplo, eliminar el registro con ID 3
            System.out.println("Eliminación completada correctamente.");
        } catch (Exception e) {
            System.out.println("Error al eliminar: " + e.getMessage());
        }

        System.out.println("\n=== TEST: GET ALL (Después del delete) ===");

        try {
            for (ConfiguracionRed c : service.getAll()) {
                System.out.println(c);
            }
        } catch (Exception e) {
            System.out.println("Error al listar configuraciones: " + e.getMessage());
        }

    }

}
