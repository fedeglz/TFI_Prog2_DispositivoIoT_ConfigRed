package Test;

import ar.edu.utn.prog2int.Models.ConfiguracionRed;
import ar.edu.utn.prog2int.Models.DispositivoIoT;
import ar.edu.utn.prog2int.Service.ConfiguracionRedService;
import ar.edu.utn.prog2int.Service.DispositivoIoTService;
import java.util.List;


public class TestDispositivoIoT {

    public static void main(String[] args) {

        DispositivoIoTService dispositivoService = new DispositivoIoTService();
        ConfiguracionRedService configService = new ConfiguracionRedService();

        try {
            System.out.println("========== PRUEBA CRUD DISPOSITIVO IoT ==========\n");

            // 1️⃣ INSERTAR UNA CONFIGURACIÓN DE RED
            ConfiguracionRed config = new ConfiguracionRed();
            config.setIp("192.168.0.50");
            config.setMascara("255.255.255.0");
            config.setGateway("192.168.0.1");
            config.setDnsPrimario("8.8.8.8");
            config.setDhcpHabilitado(false);
            configService.insertar(config);

            // 2️⃣ INSERTAR UN DISPOSITIVO IoT
            DispositivoIoT disp = new DispositivoIoT();
            disp.setSerial("SN-2025-001");
            disp.setModelo("ESP32-CAM");
            disp.setUbicacion("Laboratorio IoT UTN");
            disp.setFirmwareVersion("v1.0.0");
            disp.setConfiguracionRed(config);

            dispositivoService.insertar(disp);

            // 3️⃣ LISTAR TODOS LOS DISPOSITIVOS
            System.out.println("\n--- Lista de dispositivos ---");
            List<DispositivoIoT> dispositivos = dispositivoService.getAll();
            for (DispositivoIoT d : dispositivos) {
                System.out.println("ID: " + d.getId() + " | Modelo: " + d.getModelo() + " | IP: "
                        + (d.getConfiguracionRed() != null ? d.getConfiguracionRed().getIp() : "N/A"));
            }

            // 4️⃣ ACTUALIZAR UN DISPOSITIVO
            System.out.println("\n--- Actualizando dispositivo ID " + disp.getId() + " ---");
            disp.setFirmwareVersion("v1.1.0");
            disp.setUbicacion("Aula 205 - Redes IoT");
            dispositivoService.actualizar(disp);

            // 5️⃣ ELIMINAR (BAJA LÓGICA)
            System.out.println("\n--- Eliminando dispositivo ID " + disp.getId() + " ---");
            dispositivoService.eliminar(disp.getId());

            // 6️⃣ LISTAR NUEVAMENTE PARA VER CAMBIOS
            System.out.println("\n--- Lista actualizada ---");
            dispositivos = dispositivoService.getAll();
            for (DispositivoIoT d : dispositivos) {
                System.out.println("ID: " + d.getId() + " | Modelo: " + d.getModelo()
                        + " | Eliminado: " + d.isEliminado());
            }

            System.out.println("\n--- PRUEBA COMPLETA SIN ERRORES ---");

        } catch (Exception e) {
            System.err.println("❌ Error en el test: " + e.getMessage());
            e.printStackTrace();
        }
    }

}
