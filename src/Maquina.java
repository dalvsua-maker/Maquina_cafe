import java.util.ArrayList;
import java.util.Scanner;

public class Maquina {
    String nSerie = "";
    private static final Scanner teclado = new Scanner(System.in);

    ArrayList<Cafe> Cafes = new ArrayList<Cafe>();
    int vasos = 100;
    int gCafe = 100;
    int gLeche = 1000;
    int gCacao = 1000;
    int mlAgua = 1000;

    public Maquina(String nSerie) {
        this.nSerie = nSerie;
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

    public Cafe pedirCafe() {
        Cafe c = null;

        int seleccion = -1;

        do {
            mostrarCafes();
            System.out.print("Introduce el número de tu elección: ");

            // Validamos que sea un número para evitar errores (InputMismatchException)
            if (teclado.hasNextInt()) {
                seleccion = teclado.nextInt() - 1;

                // Si el número no está entre 1 y 7, avisamos
                if (seleccion < 0 || seleccion > Cafes.size()-1) {
                    System.out.println("⚠️ Opción no válida. Por favor, elige del 1 al 7.");
                }
            } else {
                System.out.println("⚠️ Error: Debes introducir un número, no letras.");
                teclado.next(); // Limpia el buffer para evitar un bucle infinito
            }

        } while (seleccion < 0 || seleccion > Cafes.size()-1);
        c = Cafes.get(seleccion);
        if (c.gCafe > gCafe) {
            System.out.println("Cafe no disponible,falta cafe");
            c = null;
        } else if (c.gLeche > gLeche) {
            System.out.println("Cafe no disponible,falta leche");
            c = null;

        } else if (c.gCacao > gCacao) {
            System.out.println("Cafe no disponible,falta Cacao");
            c = null;

        } else if (vasos < 1) {
            System.out.println("Cafe no disponible,faltan vasos");
            c = null;

        } else if (mlAgua < (c.gCafe + c.gLeche + c.gCacao)) {
            System.out.println("Cafe no disponible,falta agua");
            c = null;

        } else {
            gCafe = gCafe - c.gCafe;
            gCacao = gCacao - c.gCacao;
            gLeche = gLeche - c.gLeche;
            mlAgua = mlAgua - (c.gCafe + c.gCacao + c.gLeche);
            vasos = vasos - 1;
            System.out.println("Cafe servido!");
        }

        return c;
    }

   public void recargarMaquina() {
    String[] ingredientes = {"Café", "Cacao", "Leche", "Agua"};
    int[] cantidadesActuales = {gCafe, gCacao, gLeche, mlAgua};
    
    int ingredienteSeleccionado = seleccionarIngrediente(ingredientes, cantidadesActuales);
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
            System.out.println((i + 1) + ". " + ingredientes[i] + ": " + cantidades[i]);
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

private int solicitarCantidad() {
    int cantidad = -1;
    System.out.println("Elige la cantidad a rellenar:");
    
    do {
        if (teclado.hasNextInt()) {
            cantidad = teclado.nextInt();
            if (cantidad < 1 || cantidad > 1000) {
                System.out.println("⚠️ La cantidad debe estar entre 1 y 1000.");
            }
        } else {
            System.out.println("⚠️ Error: Debes introducir un número, no letras.");
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
}    public void crearCafe(){


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
        for (int i = 0; i < Cafes.size(); i++) {
            System.out.println((i + 1 + ": ") + Cafes.get(i).toString());

        }
    }
}
