package ar.edu.utn.prog2int.Main;

import ar.edu.utn.prog2int.Models.ConfiguracionRed;
import ar.edu.utn.prog2int.Models.DispositivoIoT;
import ar.edu.utn.prog2int.Service.ConfiguracionRedService;
import ar.edu.utn.prog2int.Service.DispositivoIoTService;
import java.util.List;
import java.util.Scanner;

public class AppMenu {

    private final Scanner scanner = new Scanner(System.in);
    private final ConfiguracionRedService configService = new ConfiguracionRedService();
    private final DispositivoIoTService dispositivoService = new DispositivoIoTService();

    // MÉTODO PRINCIPAL DE MENÚ
    public void iniciar() {
        int opcion;
        do {
            mostrarMenuPrincipal();
            opcion = leerEntero("Seleccione una opción: ");

            switch (opcion) {
                case 1 ->
                    menuConfiguracionRed();
                case 2 ->
                    menuDispositivoIoT();
                case 3 ->
                    dispositivoService.pruebaRollbackTransaccional();
                case 0 ->
                    System.out.println("\nSaliendo del sistema...");
                default ->
                    System.out.println("Opción inválida, intente nuevamente.");
            }
        } while (opcion != 0);
    }

    // MENÚ PRINCIPAL
    private void mostrarMenuPrincipal() {
        System.out.println("\n========== MENÚ PRINCIPAL ==========");
        System.out.println("1. CRUD Configuración de Red");
        System.out.println("2. CRUD Dispositivo IoT");
        System.out.println("3. Probar Rollback Transaccional");
        System.out.println("0. Salir");
        System.out.println("====================================");
    }

    // CRUD CONFIGURACIÓN DE RED
    private void menuConfiguracionRed() {
        int opcion;
        do {
            System.out.println("\n--- CRUD CONFIGURACIÓN DE RED ---");
            System.out.println("1. Crear configuración");
            System.out.println("2. Listar configuraciones");
            System.out.println("3. Buscar por ID");
            System.out.println("4. Actualizar configuración");
            System.out.println("5. Eliminar (baja lógica)");
            System.out.println("0. Volver al menú principal");

            opcion = leerEntero("Seleccione una opción: ");

            try {
                switch (opcion) {
                    case 1 ->
                        crearConfiguracion();
                    case 2 ->
                        listarConfiguraciones();
                    case 3 ->
                        buscarConfiguracionPorId();
                    case 4 ->
                        actualizarConfiguracion();
                    case 5 ->
                        eliminarConfiguracion();
                    case 0 ->
                        System.out.println("Volviendo al menú principal...");
                    default ->
                        System.out.println("Opción inválida.");
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        } while (opcion != 0);
    }

    private void crearConfiguracion() throws Exception {
        System.out.println("\n--- CREAR CONFIGURACIÓN ---");
        ConfiguracionRed c = new ConfiguracionRed();
        c.setIp(leerTexto("IP: "));
        c.setMascara(leerTexto("Máscara: "));
        c.setGateway(leerTexto("Gateway: "));
        c.setDnsPrimario(leerTexto("DNS Primario: "));
        c.setDhcpHabilitado(leerBooleano("¿DHCP habilitado? (true/false): "));
        configService.insertar(c);
    }

    private void listarConfiguraciones() throws Exception {
        System.out.println("\n--- LISTA DE CONFIGURACIONES ---");
        List<ConfiguracionRed> lista = configService.getAll();
        if (lista.isEmpty()) {
            System.out.println("No hay configuraciones registradas.");
        } else {
            lista.forEach(System.out::println);
        }
    }

    private void buscarConfiguracionPorId() throws Exception {
        long id = leerEntero("Ingrese ID: ");
        ConfiguracionRed c = configService.getById(id);
        System.out.println(c != null ? c : "No se encontró configuración con ese ID.");
    }

    private void actualizarConfiguracion() throws Exception {
        long id = leerEntero("Ingrese ID a actualizar: ");
        ConfiguracionRed c = configService.getById(id);
        if (c == null) {
            System.out.println("Configuración no encontrada.");
            return;
        }
        c.setIp(leerTexto("Nueva IP: "));
        c.setMascara(leerTexto("Nueva máscara: "));
        c.setGateway(leerTexto("Nuevo gateway: "));
        c.setDnsPrimario(leerTexto("Nuevo DNS primario: "));
        c.setDhcpHabilitado(leerBooleano("¿DHCP habilitado? (true/false): "));
        configService.actualizar(c);
    }

    private void eliminarConfiguracion() throws Exception {
        long id = leerEntero("Ingrese ID a eliminar: ");
        configService.eliminar(id);
    }

    // CRUD DISPOSITIVO IoT
    private void menuDispositivoIoT() {
        int opcion;
        do {
            System.out.println("\n--- CRUD DISPOSITIVO IoT ---");
            System.out.println("1. Crear dispositivo");
            System.out.println("2. Listar dispositivos");
            System.out.println("3. Buscar por ID");
            System.out.println("4. Actualizar dispositivo");
            System.out.println("5. Eliminar (baja lógica)");
            System.out.println("0. Volver al menú principal");

            opcion = leerEntero("Seleccione una opción: ");

            try {
                switch (opcion) {
                    case 1 ->
                        crearDispositivo();
                    case 2 ->
                        listarDispositivos();
                    case 3 ->
                        buscarDispositivoPorId();
                    case 4 ->
                        actualizarDispositivo();
                    case 5 ->
                        eliminarDispositivo();
                    case 0 ->
                        System.out.println("Volviendo al menú principal...");
                    default ->
                        System.out.println("Opción inválida.");
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        } while (opcion != 0);
    }

    private void crearDispositivo() throws Exception {
        System.out.println("\n--- CREAR DISPOSITIVO ---");
        DispositivoIoT d = new DispositivoIoT();
        d.setSerial(leerTexto("Serial: "));
        d.setModelo(leerTexto("Modelo: "));
        d.setUbicacion(leerTexto("Ubicación: "));
        d.setFirmwareVersion(leerTexto("Versión de firmware: "));

        listarConfiguraciones();
        long idConf = leerEntero("Ingrese ID de configuración (0 si ninguna): ");
        if (idConf > 0) {
            ConfiguracionRed conf = configService.getById(idConf);
            if (conf != null) {
                d.setConfiguracionRed(conf);
            } else {
                System.out.println("Configuración no encontrada. Se creará sin red asociada.");
            }
        }

        dispositivoService.insertar(d);
    }

    private void listarDispositivos() throws Exception {
        System.out.println("\n--- LISTA DE DISPOSITIVOS ---");
        List<DispositivoIoT> lista = dispositivoService.getAll();
        if (lista.isEmpty()) {
            System.out.println("No hay dispositivos registrados.");
        } else {
            lista.forEach(System.out::println);
        }
    }

    private void buscarDispositivoPorId() throws Exception {
        long id = leerEntero("Ingrese ID: ");
        DispositivoIoT d = dispositivoService.getById(id);
        System.out.println(d != null ? d : "No se encontró dispositivo con ese ID.");
    }

    private void actualizarDispositivo() throws Exception {
        long id = leerEntero("Ingrese ID del dispositivo a actualizar: ");
        DispositivoIoT d = dispositivoService.getById(id);
        if (d == null) {
            System.out.println("Dispositivo no encontrado.");
            return;
        }
        d.setModelo(leerTexto("Nuevo modelo: "));
        d.setUbicacion(leerTexto("Nueva ubicación: "));
        d.setFirmwareVersion(leerTexto("Nueva versión de firmware: "));
        dispositivoService.actualizar(d);
    }

    private void eliminarDispositivo() throws Exception {
        long id = leerEntero("Ingrese ID del dispositivo a eliminar: ");
        dispositivoService.eliminar(id);
    }

    // MÉTODOS AUXILIARES
    private String leerTexto(String mensaje) {
        System.out.print(mensaje);
        return scanner.nextLine().trim();
    }

    private int leerEntero(String mensaje) {
        while (true) {
            try {
                System.out.print(mensaje);
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Ingrese un número válido.");
            }
        }
    }

    private boolean leerBooleano(String mensaje) {
        while (true) {
            System.out.print(mensaje);
            String valor = scanner.nextLine().trim().toLowerCase();
            if (valor.equalsIgnoreCase("true") || valor.equalsIgnoreCase("false")) {
                return Boolean.parseBoolean(valor);
            }
            System.out.println("Ingrese 'true' o 'false'.");
        }
    }

}
