
import java.util.Scanner;

public class Main {
/**
 * Punto de entrada al programa.
 *
 * Se encarga de inicializar una máquina con un numero de serie de longitud 10 y
 * luego la inicia.
 *
 * @param args los argumentos del programa. No se utilizan.
 */
    public static void main(String[] args) {

        // Inicializamos la máquina
        Maquina miMaquina = new Maquina(10);
        miMaquina.iniciar();
        miMaquina.cerrarScanner();
    }

}