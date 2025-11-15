package ar.edu.utn.prog2int.Main;

public class Main {

    public static void main(String[] args) {
        
        System.out.println("=======================================");
        System.out.println("     SISTEMA DE GESTIÓN IoT - UTN");
        System.out.println("=======================================");

        AppMenu menu = new AppMenu();
        menu.iniciar();

        System.out.println("\n Sistema finalizado correctamente.");
    }
}
