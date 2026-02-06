import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class Maquina {
    String nSerie = "";
    private static final Scanner teclado = new Scanner(System.in);

    ArrayList<Cafe> Cafes = new ArrayList<Cafe>();
    int vasos = 100;
    int gCafe = 10;
    int gLeche = 10;
    int gCacao = 1000;
    int mlAgua = 10;

    public Maquina(int longitud) {
        this.nSerie = generarNumeroSerie(longitud);
        Cafes.add(new Cafe("Espresso", 18, 0, 0));
        Cafes.add(new Cafe("Macchiato", 18, 10, 0));
        Cafes.add(new Cafe("Capuccino", 18, 150, 5));
        Cafes.add(new Cafe("Latte", 18, 250, 0));
        Cafes.add(new Cafe("Mocha", 18, 200, 30));
        Cafes.add(new Cafe("Cortado", 18, 20, 0));
        Cafes.add(new Cafe("Chocolate", 0, 100, 50));
    }

    public String getnSerie() {
        return nSerie;
    }

    public void setnSerie(String nSerie) {
        this.nSerie = nSerie;
    }

    public ArrayList<Cafe> getCafes() {
        return Cafes;
    }

    public void setCafes(ArrayList<Cafe> cafes) {
        Cafes = cafes;
    }

    public int getVasos() {
        return vasos;
    }

    public void setVasos(int vasos) {
        this.vasos = vasos;
    }

    public int getgCafe() {
        return gCafe;
    }

    public void setgCafe(int gCafe) {
        this.gCafe = gCafe;
    }

    public int getgLeche() {
        return gLeche;
    }

    public void setgLeche(int gLeche) {
        this.gLeche = gLeche;
    }

    public int getgCacao() {
        return gCacao;
    }

    public void setgCacao(int gCacao) {
        this.gCacao = gCacao;
    }

    public int getMlAgua() {
        return mlAgua;
    }

    public void setMlAgua(int mlAgua) {
        this.mlAgua = mlAgua;
    }
    public static String generarNumeroSerie(int cantidadDigitos) {
        StringBuilder sb = new StringBuilder("SE");

        for (int i = 0; i < cantidadDigitos; i++) {
            // Genera un número entre 0 (inclusivo) y 10 (exclusivo)
            int digito = ThreadLocalRandom.current().nextInt(0, 10);
            sb.append(digito);
        }

        return sb.toString();
    }
    public Cafe pedirCafe() {
        Cafe c = null;
        int seleccion = -1;

        // 1. Selección del café
        do {
            mostrarCafes();
            System.out.print("Introduce el número de tu elección: ");
            if (teclado.hasNextInt()) {
                seleccion = teclado.nextInt() - 1;
                if (seleccion < 0 || seleccion > Cafes.size() - 1) {
                    System.out.println("⚠️ Opción no válida.");
                }
            } else {
                System.out.println("⚠️ Error: Debes introducir un número.");
                teclado.next();
            }
        } while (seleccion < 0 || seleccion > Cafes.size() - 1);

        c = Cafes.get(seleccion);

        // 2. Validación de ingredientes (Acumulativa)
        boolean hayStock = true;
        StringBuilder errores = new StringBuilder("❌ No se puede preparar el café por falta de:\n");

        if (gCafe < c.gCafe) {
            errores.append("- Café\n");
            hayStock = false;
        }
        if (gLeche < c.gLeche) {
            errores.append("- Leche\n");
            hayStock = false;
        }
        if (gCacao < c.gCacao) {
            errores.append("- Cacao\n");
            hayStock = false;
        }
        if (vasos < 1) {
            errores.append("- Vasos\n");
            hayStock = false;
        }
        if (mlAgua < (c.gCafe + c.gLeche + c.gCacao)) {
            errores.append("- Agua\n");
            hayStock = false;
        }

        // 3. Resultado final
        if (!hayStock) {
            System.out.println(errores.toString());
            return null; // Retornamos null si falta algo
        } else {
            // Descontar ingredientes
            gCafe -= c.gCafe;
            gCacao -= c.gCacao;
            gLeche -= c.gLeche;
            mlAgua -= (c.gCafe + c.gCacao + c.gLeche);
            vasos--;

            System.out.println("☕ ¡Café servido! Disfrute su " + c.nombre);
            return c;
        }
    }


   public void recargarMaquina() {
    String[] ingredientes = {"Café", "Cacao", "Leche", "Agua", "Salir"};
    int[] cantidadesActuales = {gCafe, gCacao, gLeche, mlAgua, 0}; // 0 para "Salir"
    
    int ingredienteSeleccionado = seleccionarIngrediente(ingredientes, cantidadesActuales);
    
    // Si elige "Salir" (última opción), termina el método
    if (ingredienteSeleccionado == ingredientes.length - 1) {
        System.out.println("Saliendo del menú de recarga...");
        return;
    }
    
    int cantidadARellenar = solicitarCantidad();
    
    if (puedoRecargar(cantidadesActuales[ingredienteSeleccionado], cantidadARellenar)) {
        realizarRecarga(ingredienteSeleccionado, cantidadARellenar);
        System.out.println("¡Recarga completa!");
    } else {
        System.out.println("Límite superado");
    }
}

private int seleccionarIngrediente(String[] ingredientes, int[] cantidades) {
    int seleccion = -1;
    do {
        System.out.println("¿Qué quieres rellenar?:");
        for (int i = 0; i < ingredientes.length; i++) {
            // No mostramos cantidad para la opción "Salir"
            if (i == ingredientes.length - 1) {
                System.out.println((i + 1) + ". " + ingredientes[i]);
            } else {
                System.out.println((i + 1) + ". " + ingredientes[i] + ": " + cantidades[i]);
            }
        }
        
        if (teclado.hasNextInt()) {
            seleccion = teclado.nextInt() - 1;
            if (seleccion < 0 || seleccion >= ingredientes.length) {
                System.out.println("⚠️ Opción no válida. Elige del 1 al " + ingredientes.length);
            }
        } else {
            System.out.println("⚠️ Error: Debes introducir un número, no letras.");
            teclado.next();
        }
    } while (seleccion < 0 || seleccion >= ingredientes.length);
    
    return seleccion;
}

// Los demás métodos permanecen igual
private int solicitarCantidad() {
    int cantidad = -1;
    System.out.println("Elige la cantidad a rellenar(No se puede exceder el limite de 1000):");
    
    do {
        if (teclado.hasNextInt()) {
            cantidad = teclado.nextInt();
            if (cantidad < 1 || cantidad > 1000) {
                System.out.println("⚠️ La cantidad debe estar entre 1 y 1000.");
            }
        } else {
            System.out.println("⚠️ Error: Debes introducir un número entero, no letras.");
            teclado.next();
        }
    } while (cantidad < 1 || cantidad > 1000);
    
    return cantidad;
}

private boolean puedoRecargar(int cantidadActual, int cantidadARellenar) {
    return (cantidadActual + cantidadARellenar) <= 1000;
}

private void realizarRecarga(int ingrediente, int cantidad) {
    switch (ingrediente) {
        case 0: gCafe += cantidad; break;
        case 1: gCacao += cantidad; break;
        case 2: gLeche += cantidad; break;
        case 3: mlAgua += cantidad; break;
    }
}

    public void crearCafe(){


        String nombre;
        int gCafe = validarGramo(teclado, "Café");
        int gLeche = validarGramo(teclado, "Leche");
        int gCacao = validarGramo(teclado, "Cacao");

        // Bucle de validación
        while (true) {
            System.out.print("Introduce el nombre del nuevo café: ");
            nombre = teclado.nextLine().trim(); // Trim elimina espacios innecesarios al inicio/final

            // Validación: Que no esté vacío y que solo contenga letras (y espacios)
            if (!nombre.isEmpty() && nombre.matches("^[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+$")) {
                break; // Nombre válido, salimos del bucle
            } else {
                System.out.println("⚠️ Nombre no válido. Asegúrate de no usar números ni dejarlo vacío.");
            }
        }
        Cafe c=new Cafe(nombre,gCafe,gLeche,gCacao);
        Cafes.add(c);


    }
    // Método reutilizable para validar cada ingrediente
    public static int validarGramo(Scanner sc, String ingrediente) {
        int valor = -1;
        while (valor < 1 || valor > 200) { // El bucle sigue mientras el valor no esté entre 1 y 200
            System.out.print("Introduce los gramos de " + ingrediente + " (1-200): ");

            if (sc.hasNextInt()) {
                int entrada = sc.nextInt(); // LEEMOS UNA SOLA VEZ
                sc.nextLine(); // Limpiamos el buffer inmediatamente

                if (entrada >= 1 && entrada <= 200) {
                    valor = entrada; // El valor es válido, saldrá del bucle
                } else {
                    System.out.println("⚠️ Error: La cantidad debe estar entre 1 y 200.");
                }
            } else {
                System.out.println("⚠️ Error: Introduce un número entero válido.");
                sc.nextLine(); // Limpia el buffer si el usuario metió letras
            }
        }
        return valor;
    }


    private void mostrarCafes() {
        System.out.println("\n======= MENÚ DE CAFÉS =======");
        // Definimos la cabecera: %-3s (ID), %-15s (Nombre), %-25s (Ingredientes)
        // El signo '-' alinea el texto a la izquierda
        System.out.printf("%-3s | %-15s | %s%n", "ID", "NOMBRE", "INGREDIENTES");
        System.out.println("---------------------------------------------------------");

        for (int i = 0; i < Cafes.size(); i++) {
            Cafe c = Cafes.get(i);
            String ingredientes = String.format("C:%dg L:%dg Ca:%dg", c.gCafe, c.gLeche, c.gCacao);

            // %-3d: entero, 3 espacios. %-15s: texto, 15 espacios.
            System.out.printf("%-3d | %-15s | %s%n", (i + 1), c.nombre, ingredientes);
        }
        System.out.println("---------------------------------------------------------");
    }

}
