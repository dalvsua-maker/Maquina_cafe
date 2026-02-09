package Java.Practica5.src;


import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        // Inicializamos la máquina
        Maquina miMaquina = new Maquina(10);
        miMaquina.iniciar();
        miMaquina.cerrarScanner();
    }

}