
/**
 * Constructor para crear una receta de café personalizada.
 * 
 * Define las proporciones de ingredientes (café, leche y cacao) que 
 * componen una bebida específica.
 * 
 * @param nombre Nombre descriptivo de la bebida (ej. "Capuchino").
 * @param gCafe  Cantidad de café necesaria en gramos.
 * @param gLeche Cantidad de leche necesaria en gramos (0 si no lleva).
 * @param gCacao Cantidad de cacao necesaria en gramos (0 si no lleva).
 */
public class Cafe {

    String nombre;
 private int gCafe;
  private  int gLeche;
  private  int gCacao;
/**
 * Constructor para crear una receta de café personalizada.
 * 
 * Define las proporciones de ingredientes (café, leche y cacao) que 
 * componen una bebida específica.
 * 
 * @param nombre Nombre descriptivo de la bebida (ej. "Capuchino").
 * @param gCafe  Cantidad de café necesaria en gramos.
 * @param gLeche Cantidad de leche necesaria en gramos (0 si no lleva).
 * @param gCacao Cantidad de cacao necesaria en gramos (0 si no lleva).
 */
    public Cafe(String nombre, int gCafe, int gLeche, int gCacao) {
        this.nombre = nombre;
        this.gCafe = gCafe;
        this.gLeche = gLeche;
        this.gCacao = gCacao;

    }

/**
 * Devuelve el nombre descriptivo de la bebida.
 *
 * @return el nombre descriptivo de la bebida.
 */
    public String getNombre() {
        return nombre;
    }

/**
 * Establece el nombre descriptivo de la bebida.
 * 
 * @param nombre el nombre descriptivo de la bebida.
 */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

/**
 * Devuelve la cantidad de café (en gramos) necesaria para preparar
 * la bebida.
 * 
 * @return la cantidad de café necesaria en gramos.
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
 * */
    public void setgLeche(int gLeche) {
        this.gLeche = gLeche;
    }

/**
 * Devuelve la cantidad actual de cacao (en gramos) necesaria para preparar
 * la bebida.
 * 
 * @return la cantidad actual de cacao necesaria en gramos.
 */
    public int getgCacao() {
        return gCacao;
    }

    /**
     * Establece la cantidad actual de cacao (en gramos) necesaria para preparar
     * la bebida.
     * 
     * @param gCacao la cantidad actual de cacao necesaria en gramos.
     */
    public void setgCacao(int gCacao) {
        this.gCacao = gCacao;
    }

/**
 * Returns a string representation of the object.
 * 
 * The string representation will have the following format: 
 * "Cafe{nombre='X', gCafe=Y, gLeche=Z, gCacao=W}" where X is the name of the coffee, Y is the amount of coffee in grams, Z is the amount of milk in grams and W is the amount of cacao in grams.
 * 
 * @return a string representation of the object.
 */
    @Override
    public String toString() {
        return "Cafe{" +
                "nombre='" + nombre + '\'' +
                ", gCafe=" + gCafe +
                ", gLeche=" + gLeche +
                ", gCacao=" + gCacao +
                '}';
    }
}
