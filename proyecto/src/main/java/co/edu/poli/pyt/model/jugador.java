package co.edu.poli.pyt.model;

/**
 * Representa a un jugador dentro del juego.
 * 
 * La clase almacena la información básica del jugador,
 * incluyendo su identificador y nombre.
 */
public class Jugador {

    /**
     * Identificador único del jugador.
     */
    private int id;

    /**
     * Nombre del jugador.
     */
    private String nombre;
    private int puntajeTotal;
    private int partidasGanadas;

    /**
     * Constructor de la clase Jugador.
     * 
     * @param id     identificador único del jugador
     * @param nombre nombre del jugador
     */
    public Jugador(int id, String nombre) {
        this(id, nombre, 0, 0);
    }

    public Jugador(int id, String nombre, int puntajeTotal, int partidasGanadas) {
        this.id = id;
        this.nombre = nombre;
        this.puntajeTotal = puntajeTotal;
        this.partidasGanadas = partidasGanadas;
    }

    /**
     * Obtiene el identificador del jugador.
     * 
     * @return id del jugador
     */
    public int getId() {
        return id;
    }

    /**
     * Obtiene el nombre del jugador.
     * 
     * @return nombre del jugador
     */
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
