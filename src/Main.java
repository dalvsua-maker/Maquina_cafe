//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
void main() {
    //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
    // to see how IntelliJ IDEA suggests fixing it.

    // Inicializamos la máquina
    Maquina miMaquina = new Maquina("Se312345678");
    Scanner sc = new Scanner(System.in);
    boolean salir = false;

    System.out.println("☕ BIENVENIDO A TU MÁQUINA DE CAFÉ ☕");

    while (!salir) {
        System.out.println("\n--- MENÚ DE INTERFAZ ---");
        System.out.println("1. Pedir un café (Servir)");
        System.out.println("2. Crear nueva receta de café");
        System.out.println("3. Recargar suministros");
        System.out.println("4. Ver estado de suministros");
        System.out.println("5. Salir");
        System.out.print("Selecciona una opción: ");

        String opcion = sc.nextLine(); // Leemos como String para evitar errores de buffer

        switch (opcion) {
            case "1":
                miMaquina.pedirCafe();
                break;
            case "2":
                miMaquina.crearCafe();
                break;
            case "3":
                miMaquina.recargarMaquina();
                break;
            case "4":
                imprimirEstado(miMaquina);
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
    sc.close();

}
// Método auxiliar para visualizar rápidamente el stock actual
public static void imprimirEstado(Maquina m) {
    System.out.println("\n--- ESTADO DE INGREDIENTES ---");
    System.out.println("Café: " + m.getgCafe() + "g | Leche: " + m.getgLeche() + "g");
    System.out.println("Cacao: " + m.getgCacao() + "g | Agua: " + m.getMlAgua() + "ml");
    System.out.println("Vasos: " + m.getVasos());
}