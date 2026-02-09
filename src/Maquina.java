import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class Maquina {
    // Constantes
    private static final int MAX_CAPACIDAD = 1000;
    private static final int MAX_GRAMOS_RECETA = 200;
    private static final int MIN_GRAMOS_RECETA = 1;
    private static final int CAPACIDAD_INICIAL_INGREDIENTES = 10;
    private static final int CAPACIDAD_INICIAL_VASOS = 100;
    private static final int CAPACIDAD_INICIAL_CACAO = 1000;

    String nSerie = "";
    private static final Scanner teclado = new Scanner(System.in);

    ArrayList<Cafe> Cafes = new ArrayList<Cafe>();
    int vasos = CAPACIDAD_INICIAL_VASOS; // ✅ Usar constante
    int gCafe = CAPACIDAD_INICIAL_INGREDIENTES;
    int gLeche = CAPACIDAD_INICIAL_INGREDIENTES;
    int gCacao = CAPACIDAD_INICIAL_CACAO;
    int mlAgua = CAPACIDAD_INICIAL_INGREDIENTES;

    public Maquina(int longitud) {
        this.nSerie = generarNumeroSerie(longitud);
        inicializarMenuCafes();

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

    private void inicializarMenuCafes() {
        Cafes.add(new Cafe("Espresso", 18, 0, 0));
        Cafes.add(new Cafe("Macchiato", 18, 10, 0));
        Cafes.add(new Cafe("Capuccino", 18, 150, 5));
        Cafes.add(new Cafe("Latte", 18, 250, 0));
        Cafes.add(new Cafe("Mocha", 18, 200, 30));
        Cafes.add(new Cafe("Cortado", 18, 20, 0));
        Cafes.add(new Cafe("Chocolate", 0, 100, 50));
    }

    public void iniciar() {
        interfaz(); // ✅ Llamar externamente
    }

    public void cerrarScanner() {
        teclado.close();
    }

    private void interfaz() {

        boolean salir = false;

        System.out.println("☕BIENVENIDO A TU MÁQUINA DE CAFÉ DE AULA ESTUDIO");

        while (!salir) {
            System.out.println("\n--- MENÚ DE INTERFAZ ---(Nserie: " + nSerie + ")");
            System.out.println("1. Pedir un café (Servir)");
            System.out.println("2. Crear nueva receta de café");
            System.out.println("3. Recargar suministros");
            System.out.println("4. Ver estado de suministros");
            System.out.println("5. Salir");
            System.out.print("Selecciona una opción: ");

            String opcion = teclado.nextLine(); // Leemos como String para evitar errores de buffer

            switch (opcion) {
                case "1":
                    pedirCafe();
                    break;
                case "2":
                    crearCafe();
                    break;
                case "3":
                    recargarMaquina();
                    break;
                case "4":
                    imprimirEstado(this);
                    break;
                case "5":
                    System.out.println("¡Gracias por usar la máquina! Saliendo...");
                    salir = true;
                    break;
                default:
                    System.out.println("⚠️ Opción no válida. Inténtalo de nuevo.");
                    break;
            }
        }

    }

    private static void imprimirEstado(Maquina m) {
        System.out.println("\n--- ESTADO DE INGREDIENTES ---");
        System.out.println("Café: " + m.getgCafe() + "g | Leche: " + m.getgLeche() + "g");
        System.out.println("Cacao: " + m.getgCacao() + "g | Agua: " + m.getMlAgua() + "ml");
        System.out.println("Vasos: " + m.getVasos());
    }

    private static String generarNumeroSerie(int cantidadDigitos) {
        StringBuilder sb = new StringBuilder("SE");

        for (int i = 0; i < cantidadDigitos; i++) {
            // Genera un número entre 0 (inclusivo) y 10 (exclusivo)
            int digito = ThreadLocalRandom.current().nextInt(0, 10);
            sb.append(digito);
        }

        return sb.toString();
    }

public Cafe pedirCafe() {
    Cafe cafeSeleccionado = seleccionarCafeDelMenu();
    
    if (verificarStock(cafeSeleccionado)) {
        return servirCafe(cafeSeleccionado);
    } else {
        mostrarErroresStock(cafeSeleccionado);
        return null;
    }
}

private Cafe seleccionarCafeDelMenu() {
    mostrarCafes();
    int seleccion = leerEnteroEnRango("Introduce el número de tu elección: ", 1, Cafes.size()) - 1;
    return Cafes.get(seleccion);
}

private boolean verificarStock(Cafe c) {
    return gCafe >= c.getgCafe() 
        && gLeche >= c.getgLeche() 
        && gCacao >= c.getgCacao() 
        && vasos >= 1 
        && mlAgua >= (c.getgCafe() + c.getgLeche() + c.getgCacao());
}

private void mostrarErroresStock(Cafe c) {
    StringBuilder errores = new StringBuilder("❌ No se puede preparar el café por falta de:\n");
    if (gCafe < c.getgCafe()) errores.append("- Café\n");
    if (gLeche < c.getgLeche()) errores.append("- Leche\n");
    if (gCacao < c.getgCacao()) errores.append("- Cacao\n");
    if (vasos < 1) errores.append("- Vasos\n");
    if (mlAgua < (c.getgCafe() + c.getgLeche() + c.getgCacao())) errores.append("- Agua\n");
    System.out.println(errores.toString());
}

private Cafe servirCafe(Cafe c) {
    gCafe -= c.getgCafe();
    gLeche -= c.getgLeche();
    gCacao -= c.getgCacao();
    mlAgua -= (c.getgCafe() + c.getgLeche() + c.getgCacao());
    vasos--;
    System.out.println("☕ ¡Café servido! Disfrute su " + c.getNombre());
    return c;
}

    public void recargarMaquina() {
        String[] ingredientes = { "Café", "Cacao", "Leche", "Agua", "Vasos", "Salir" }; // ✅ Añadir Vasos
        int[] cantidadesActuales = { gCafe, gCacao, gLeche, mlAgua, vasos, 0 }; // ✅ Añadir vasos

        int ingredienteSeleccionado = seleccionarIngrediente(ingredientes, cantidadesActuales);

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

    private void realizarRecarga(int ingrediente, int cantidad) {
        switch (ingrediente) {
            case 0:
                gCafe += cantidad;
                break;
            case 1:
                gCacao += cantidad;
                break;
            case 2:
                gLeche += cantidad;
                break;
            case 3:
                mlAgua += cantidad;
                break;
            case 4: // ✅ AÑADIR ESTE CASO
                vasos += cantidad;
                break;
        }
    }

    private int seleccionarIngrediente(String[] ingredientes, int[] cantidades) {
        System.out.println("¿Qué quieres rellenar?:");
        for (int i = 0; i < ingredientes.length; i++) {
            if (i == ingredientes.length - 1) {
                System.out.println((i + 1) + ". " + ingredientes[i]);
            } else {
                System.out.println((i + 1) + ". " + ingredientes[i] + ": " + cantidades[i]);
            }
        }

        return leerEnteroEnRango("Selecciona una opción: ", 1, ingredientes.length) - 1;
    }

    // Los demás métodos permanecen igual
    private int solicitarCantidad() {
        System.out.println("Elige la cantidad a rellenar (No se puede exceder el límite de " + MAX_CAPACIDAD + "):");
        return leerEnteroEnRango("Cantidad: ", 1, MAX_CAPACIDAD);
    }

    private boolean puedoRecargar(int cantidadActual, int cantidadARellenar) {
        return (cantidadActual + cantidadARellenar) <= MAX_CAPACIDAD; // ✅ Usar constante
    }
public void crearCafe() {
    System.out.println("\n--- CREAR NUEVA RECETA ---");
    
    int gCafe = leerEnteroEnRango("Introduce los gramos de Café (1-200): ", MIN_GRAMOS_RECETA, MAX_GRAMOS_RECETA);
    int gLeche = leerEnteroEnRango("Introduce los gramos de Leche (1-200): ", MIN_GRAMOS_RECETA, MAX_GRAMOS_RECETA);
    int gCacao = leerEnteroEnRango("Introduce los gramos de Cacao (1-200): ", MIN_GRAMOS_RECETA, MAX_GRAMOS_RECETA);

    String nombre = leerNombreCafe();
    
    // ✅ Validar duplicados
    if (existeCafe(nombre)) {
        System.out.println("⚠️ Ya existe un café con ese nombre.");
        return;
    }
    
    Cafe c = new Cafe(nombre, gCafe, gLeche, gCacao);
    Cafes.add(c);
    System.out.println("✅ ¡Café '" + nombre + "' creado exitosamente!");
}

private boolean existeCafe(String nombre) {
    return Cafes.stream()
                .anyMatch(cafe -> cafe.getNombre().equalsIgnoreCase(nombre));
}

private String leerNombreCafe() {
    String nombre;
    while (true) {
        System.out.print("Introduce el nombre del nuevo café: ");
        nombre = teclado.nextLine().trim();

        if (!nombre.isEmpty() && nombre.matches("^[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+$")) {
            return nombre;
        }
        System.out.println("⚠️ Nombre no válido. No uses números ni lo dejes vacío.");
    }
}

    /**
     * Lee un entero del usuario dentro de un rango válido
     * 
     * @param mensaje Mensaje a mostrar
     * @param min     Valor mínimo aceptable
     * @param max     Valor máximo aceptable
     * @return El entero validado
     */
    private int leerEnteroEnRango(String mensaje, int min, int max) {
        int valor = -1;

        do {
            System.out.print(mensaje);

            if (teclado.hasNextInt()) {
                valor = teclado.nextInt();
                teclado.nextLine(); // Limpiar buffer

                if (valor < min || valor > max) {
                    System.out.println("⚠️ El valor debe estar entre " + min + " y " + max);
                }
            } else {
                System.out.println("⚠️ Error: Debes introducir un número entero.");
                teclado.nextLine(); // Limpiar buffer
            }
        } while (valor < min || valor > max);

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
