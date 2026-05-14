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

    /**
     * Constructor de la clase Jugador.
     * 
     * @param id     identificador único del jugador
     * @param nombre nombre del jugador
     */
    public Jugador(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
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
}