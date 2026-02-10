
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class Maquina {
    // Constantes
    private static final int MAX_CAPACIDAD = 1000;
    private static final int MAX_GRAMOS_RECETA = 200;
    private static final int MIN_GRAMOS_RECETA = 0;
    private static final int CAPACIDAD_INICIAL_INGREDIENTES = 10;
    private static final int CAPACIDAD_INICIAL_VASOS = 100;
    private static final int CAPACIDAD_INICIAL_CACAO = 1000;

    String nSerie = "";
    private static final Scanner teclado = new Scanner(System.in);

    ArrayList<Cafe> Cafes = new ArrayList<Cafe>();
    private int vasos = CAPACIDAD_INICIAL_VASOS; // ✅ Usar constante
    private int gCafe = CAPACIDAD_INICIAL_INGREDIENTES;
    private int gLeche = CAPACIDAD_INICIAL_INGREDIENTES;
    private int gCacao = CAPACIDAD_INICIAL_CACAO;
    private int mlAgua = CAPACIDAD_INICIAL_INGREDIENTES;

    /**
     * Crea una nueva instancia de la máquina de café.
     * 
     * Este constructor se encarga de establecer la identidad única de la máquina
     * mediante la generación automática de un número de serie y de configurar
     * el catálogo de productos disponibles.
     * 
     * @param longitudNserie El número de caracteres o dígitos que tendrá el
     *                       número de serie generado.
     * @see #generarNumeroSerie(int)
     * @see #inicializarMenuCafes()
     */

    public Maquina(int longitudNserie) {
        this.nSerie = generarNumeroSerie(longitudNserie);
        inicializarMenuCafes();

    }

    /**
     * Devuelve el numero de serie de la maquina.
     * 
     * @return el numero de serie de la maquina.
     */
    public String getnSerie() {
        return nSerie;
    }

    /**
     * Establece el numero de serie de la maquina.
     * 
     * @param nSerie el numero de serie de la maquina.
     */
    public void setnSerie(String nSerie) {
        this.nSerie = nSerie;
    }

    /**
     * Devuelve la lista de cafes que se encuentran en la maquina.
     * 
     * @return la lista de cafes que se encuentran en la maquina.
     */

    /**
     * Devuelve la lista de cafes que se encuentran en la maquina.
     * 
     * @return la lista de cafes que se encuentran en la maquina.
     */
    public ArrayList<Cafe> getCafes() {
        return Cafes;
    }

    /**
     * Establece la lista de cafes que se encuentran en la maquina.
     * 
     * @param cafes la lista de cafes que se encuentran en la maquina.
     */
    public void setCafes(ArrayList<Cafe> cafes) {
        Cafes = cafes;
    }

    /**
     * Devuelve la capacidad actual de los vasos de la maquina.
     * 
     * @return la capacidad actual de los vasos de la maquina.
     */
    public int getVasos() {
        return vasos;
    }

    /**
     * Establece la capacidad actual de los vasos de la maquina.
     * 
     * @param vasos la capacidad actual de los vasos de la maquina.
     */
    public void setVasos(int vasos) {
        this.vasos = vasos;
    }

    /**
     * Devuelve la cantidad actual de café en gramos.
     *
     * @return la cantidad de café en gramos
     */
    public int getgCafe() {
        return gCafe;
    }

    /**
     * Establece la cantidad actual de café en gramos.
     *
     * @param gCafe la cantidad actual de café en gramos.
     */
    public void setgCafe(int gCafe) {
        this.gCafe = gCafe;
    }

    /**
     * Devuelve la cantidad actual de leche en gramos.
     * 
     * @return la cantidad actual de leche en gramos.
     */
    public int getgLeche() {
        return gLeche;
    }

    /**
     * Establece la cantidad actual de leche en gramos.
     * 
     * @param gLeche la cantidad actual de leche en gramos.
     */
    public void setgLeche(int gLeche) {
        this.gLeche = gLeche;
    }

    /**
     * Devuelve la cantidad actual de cacao en gramos.
     * 
     * @return la cantidad actual de cacao en gramos.
     */
    public int getgCacao() {
        return gCacao;
    }

    /**
     * Establece la cantidad actual de cacao en gramos.
     * 
     * @param gCacao la cantidad actual de cacao en gramos.
     */
    public void setgCacao(int gCacao) {
        this.gCacao = gCacao;
    }

    /**
     * Devuelve la cantidad actual de agua en mililitros.
     * 
     * @return la cantidad actual de agua en mililitros.
     */
    public int getMlAgua() {
        return mlAgua;
    }

    /**
     * Establece la cantidad actual de agua en mililitros.
     * 
     * @param mlAgua la cantidad actual de agua en mililitros.
     */
    public void setMlAgua(int mlAgua) {
        this.mlAgua = mlAgua;
    }

    /**
     * Inicializa la lista de cafes con los cafes predeterminados.
     * 
     */
    private void inicializarMenuCafes() {
        Cafes.add(new Cafe("Espresso", 18, 0, 0));
        Cafes.add(new Cafe("Macchiato", 18, 10, 0));
        Cafes.add(new Cafe("Capuccino", 18, 150, 5));
        Cafes.add(new Cafe("Latte", 18, 250, 0));
        Cafes.add(new Cafe("Mocha", 18, 200, 30));
        Cafes.add(new Cafe("Cortado", 18, 20, 0));
        Cafes.add(new Cafe("Chocolate", 0, 100, 50));
    }

    /**
     * Inicializa la maquina de cafe, abriendo la interfaz al usuario.
     */
    public void iniciar() {
        interfaz(); // ✅ Llamar externamente
    }

    /**
     * Cierra el objeto Scanner que se utiliza para leer la entrada del usuario.
     */
    public void cerrarScanner() {
        teclado.close();
    }

    /**
     * Interfaz de la maquina de cafe, donde se interactua con el usuario.
     * Muestra un menu con opciones para interactuar con la maquina y se encarga de
     * gestionar las opciones seleccionadas.
     */
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

    /**
     * Imprime el estado actual de la maquina, incluyendo
     * cantidad de cafe, leche, cacao y agua.
     * 
     * @param m la maquina cuyo estado se va a imprimir
     */

    private static void imprimirEstado(Maquina m) {
        System.out.println("\n--- ESTADO DE INGREDIENTES ---");
        System.out.println("Café: " + m.getgCafe() + "g | Leche: " + m.getgLeche() + "g");
        System.out.println("Cacao: " + m.getgCacao() + "g | Agua: " + m.getMlAgua() + "ml");
        System.out.println("Vasos: " + m.getVasos());
    }

    /**
     * Genera un número de serie de la forma "SE" seguido de
     * <code>cantidadDigitos</code> dígitos aleatorios entre 0 y 9.
     * 
     * @param cantidadDigitos la cantidad de dígitos que se van a generar
     * @return el número de serie generado
     */
    private static String generarNumeroSerie(int cantidadDigitos) {
        StringBuilder sb = new StringBuilder("SE");

        for (int i = 0; i < cantidadDigitos; i++) {
            // Genera un número entre 0 (inclusivo) y 10 (exclusivo)
            int digito = ThreadLocalRandom.current().nextInt(0, 10);
            sb.append(digito);
        }

        return sb.toString();
    }

    /**
     * Pide un cafe al usuario y verifica si hay stock suficiente para servirlo.
     * Si hay stock, devuelve el cafe servido. Si no hay, muestra un mensaje de
     * error y devuelve null.
     * 
     * @return el cafe servido o null si no hay stock
     */
    public Cafe pedirCafe() {
        Cafe cafeSeleccionado = seleccionarCafeDelMenu();

        if (verificarStock(cafeSeleccionado)) {
            return servirCafe(cafeSeleccionado);
        } else {
            mostrarErroresStock(cafeSeleccionado);
            return null;
        }
    }

    /**
     * Muestra al usuario el menú de cafés disponibles y pide que seleccione uno.
     * Luego devuelve el objeto Cafe correspondiente a la selección del usuario.
     * 
     * @return el objeto Cafe seleccionado por el usuario
     */
    private Cafe seleccionarCafeDelMenu() {
        mostrarCafes();
        int seleccion = leerEnteroEnRango("Introduce el número de tu elección: ", 1, Cafes.size()) - 1;
        return Cafes.get(seleccion);
    }

    /**
     * Verifica si hay stock suficiente para preparar un café.
     * Se devuelve true si hay stock, false en caso contrario.
     * 
     * @param c el objeto Cafe que se va a verificar
     * @return true si hay stock, false en caso contrario
     */
    private boolean verificarStock(Cafe c) {
        return gCafe >= c.getgCafe()
                && gLeche >= c.getgLeche()
                && gCacao >= c.getgCacao()
                && vasos >= 1
                && mlAgua >= (c.getgCafe() + c.getgLeche() + c.getgCacao());
    }

    /**
     * Muestra un mensaje de error indicando los ingredientes que faltan para
     * preparar un café.
     * Se muestra por consola un mensaje que comienza con "❌ No se puede preparar el
     * café por falta de:\n"
     * seguido de las listas de ingredientes que faltan.
     * 
     * @param c el objeto Cafe que se va a verificar
     */
    private void mostrarErroresStock(Cafe c) {
        StringBuilder errores = new StringBuilder("❌ No se puede preparar el café por falta de:\n");
        if (gCafe < c.getgCafe())
            errores.append("- Café\n");
        if (gLeche < c.getgLeche())
            errores.append("- Leche\n");
        if (gCacao < c.getgCacao())
            errores.append("- Cacao\n");
        if (vasos < 1)
            errores.append("- Vasos\n");
        if (mlAgua < (c.getgCafe() + c.getgLeche() + c.getgCacao()))
            errores.append("- Agua\n");
        System.out.println(errores.toString());
    }

    /**
     * Sirve un café, restando la cantidad de cada ingrediente de la máquina.
     * Se muestra por consola un mensaje indicando el nombre del café servido.
     * 
     * @param c el objeto Cafe que se va a servir
     * @return el objeto Cafe servido
     */
    private Cafe servirCafe(Cafe c) {
        gCafe -= c.getgCafe();
        gLeche -= c.getgLeche();
        gCacao -= c.getgCacao();
        mlAgua -= (c.getgCafe() + c.getgLeche() + c.getgCacao());
        vasos--;
        System.out.println("☕ ¡Café servido! Disfrute su " + c.getNombre());
        return c;
    }

    /**
     * Recarga la cantidad de ingredientes de la máquina.
     * Se muestra un menú con los ingredientes actuales y se pide que seleccione
     * uno.
     * Luego se pide la cantidad a recargar y se verifica si se puede o no.
     * Si se puede, se realiza la recarga y se muestra un mensaje de confirmación.
     * Si no se puede, se muestra un mensaje de límite superado.
     */
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

    /**
     * Realiza la recarga de un ingrediente en la máquina.
     * Se ingresa la cantidad a recargar y se verifica si se puede o no.
     * Si se puede, se realiza la recarga y se muestra un mensaje de confirmación.
     * Si no se puede, se muestra un mensaje de límite superado.
     * 
     * @param ingrediente el ingrediente a recargar
     * @param cantidad    la cantidad a recargar del ingrediente
     */
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

    /**
     * Muestra un menú para seleccionar un ingrediente y devuelve la selección
     * realizada.
     * El menú muestra la lista de ingredientes y sus cantidades actuales.
     * Se pide al usuario que seleccione una opción y se devuelve la selección
     * realizada.
     * La selección se realiza a través de un entero en rango de 1 a n, donde n es
     * la cantidad
     * de ingredientes.
     * 
     * @param ingredientes lista de ingredientes
     * @param cantidades   lista de cantidades actuales de cada ingrediente
     * @return la selección realizada
     */
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

    /**
     * Pide al usuario que seleccione una cantidad para rellenar un ingrediente.
     * Se muestra un mensaje con el límite de capacidad de la máquina y se pide al
     * usuario que
     * seleccione una cantidad en rango de 1 a MAX_CAPACIDAD.
     * La selección se realiza a través de un entero en rango de 1 a MAX_CAPACIDAD.
     * 
     * @return la cantidad seleccionada por el usuario
     */
    private int solicitarCantidad() {
        System.out.println("Elige la cantidad a rellenar (No se puede exceder el límite de " + MAX_CAPACIDAD + "):");
        return leerEnteroEnRango("Cantidad: ", 1, MAX_CAPACIDAD);
    }

    /**
     * Verifica si se puede rellenar la cantidad de un ingrediente sin exceder el
     * límite de capacidad
     * de la máquina.
     * 
     * @param cantidadActual    la cantidad actual del ingrediente
     * @param cantidadARellenar la cantidad a rellenar del ingrediente
     * @return true si se puede rellenar, false en caso contrario
     */
    private boolean puedoRecargar(int cantidadActual, int cantidadARellenar) {
        return (cantidadActual + cantidadARellenar) <= MAX_CAPACIDAD;
    }

    /**
     * Crea una nueva receta de café.
     * Pide al usuario que introduzca los gramos de cada ingrediente y el nombre del
     * café.
     * Verifica si ya existe un café con ese nombre y, en caso de que no exista,
     * crea la receta y la agrega a la lista de recetas.
     * Muestra un mensaje de confirmación al finalizar el proceso.
     */
    public void crearCafe() {
        System.out.println("\n--- CREAR NUEVA RECETA(Al menos un ingrediente debe tener al menos 1 gramo) ---");
        boolean vacio = true;
        int gCafe = leerEnteroEnRango("Introduce los gramos de Café (0-200): ", MIN_GRAMOS_RECETA, MAX_GRAMOS_RECETA);
        int gLeche = leerEnteroEnRango("Introduce los gramos de Leche (0-200): ", MIN_GRAMOS_RECETA, MAX_GRAMOS_RECETA);
        int gCacao = leerEnteroEnRango("Introduce los gramos de Cacao (0-200): ", MIN_GRAMOS_RECETA, MAX_GRAMOS_RECETA);

        while (vacio) {
            if (gCafe > 0 || gLeche > 0 || gCacao > 0) {
                vacio = false;
            } else {
                System.out.println("Al menos un ingrediente debe tener al menos 1 gramo");
                gCafe = leerEnteroEnRango("Introduce los gramos de Café (0-200): ", MIN_GRAMOS_RECETA,
                        MAX_GRAMOS_RECETA);
                gLeche = leerEnteroEnRango("Introduce los gramos de Leche (0-200): ", MIN_GRAMOS_RECETA,
                        MAX_GRAMOS_RECETA);
                gCacao = leerEnteroEnRango("Introduce los gramos de Cacao (0-200): ", MIN_GRAMOS_RECETA,
                        MAX_GRAMOS_RECETA);

            }
        }
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

    /**
     * Verifica si ya existe un café con el nombre especificado.
     * 
     * @param nombre el nombre del café a buscar
     * @return true si existe, false en caso contrario
     */
    private boolean existeCafe(String nombre) {
        return Cafes.stream()
                .anyMatch(cafe -> cafe.getNombre().equalsIgnoreCase(nombre));
    }

    /**
     * Lee un nombre del cafe hasta que se introduzca un nombre válido.
     * Un nombre es válido si no contiene números y no es vacío.
     * Se muestra un mensaje de error si el nombre es inválido.
     * 
     * @return El nombre válido introducido por el usuario
     */
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

    /**
     * Muestra por pantalla la lista de cafés con sus ingredientes.
     * La lista se ordena por el ID del café (1, 2, ...).
     * La cabecera se define como "ID", "NOMBRE" e "INGREDIENTES".
     * El contenido se muestra en la forma "ID | NOMBRE | INGREDIENTES".
     * Los ingredientes se muestran en la forma "C:%dg L:%dg Ca:%dg".
     */
    private void mostrarCafes() {
        System.out.println("\n======= MENÚ DE CAFÉS =======");
        // Definimos la cabecera: %-3s (ID), %-15s (Nombre), %-25s (Ingredientes)
        // El signo '-' alinea el texto a la izquierda
        System.out.printf("%-3s | %-15s | %s%n", "ID", "NOMBRE", "INGREDIENTES");
        System.out.println("---------------------------------------------------------");

        for (int i = 0; i < Cafes.size(); i++) {
            Cafe c = Cafes.get(i);
            String ingredientes = String.format("C:%dg L:%dg Ca:%dg", c.getgCafe(), c.getgLeche(), c.getgCacao());

            // %-3d: entero, 3 espacios. %-15s: texto, 15 espacios.
            System.out.printf("%-3d | %-15s | %s%n", (i + 1), c.getNombre(), ingredientes);
        }
        System.out.println("---------------------------------------------------------");
    }

}
