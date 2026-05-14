package co.edu.poli.pyt.model;

public class Jugador {

    private int id;
    private String nombre;
    private int puntajeTotal;
    private int partidasGanadas;

    public Jugador(int id, String nombre) {
        this(id, nombre, 0, 0);
    }

    public Jugador(int id, String nombre, int puntajeTotal, int partidasGanadas) {
        this.id = id;
        this.nombre = nombre;
        this.puntajeTotal = puntajeTotal;
        this.partidasGanadas = partidasGanadas;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public int getPuntajeTotal() {
        return puntajeTotal;
    }

    public int getPartidasGanadas() {
        return partidasGanadas;
    }
}
