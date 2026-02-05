public class Cafe {

    String nombre;
    int gCafe;
    int gLeche;
    int gCacao;

    public Cafe(String nombre, int gCafe, int gLeche, int gCacao) {
        this.nombre = nombre;
        this.gCafe = gCafe;
        this.gLeche = gLeche;
        this.gCacao = gCacao;

    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
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
