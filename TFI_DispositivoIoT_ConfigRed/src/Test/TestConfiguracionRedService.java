package Test;

import ar.edu.utn.prog2int.Models.ConfiguracionRed;
import ar.edu.utn.prog2int.Service.ConfiguracionRedService;

public class TestConfiguracionRedService {

    public static void main(String[] args) {
        ConfiguracionRedService service = new ConfiguracionRedService();

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
                    9, false, // ID existente + no eliminado
                    "192.168.1.200", "255.255.255.0", "192.168.1.1", "1.1.1.1", true
            );
            service.actualizar(modificar);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
