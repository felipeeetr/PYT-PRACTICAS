package co.edu.poli.pyt.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Representa una partida del juego matemático.
 * 
 * La clase administra la lógica principal de la partida,
 * incluyendo los intentos del jugador, validación de números,
 * generación de feedback y control del estado del juego.
 */
public class Partida {

    /**
     * Estados posibles de una partida.
     */
    public enum EstadoPartida {

        /**
         * La partida continúa en ejecución.
         */
        EN_PROGRESO,

        /**
         * El jugador encontró la solución correcta.
         */
        GANADA,

        /**
         * El jugador agotó todos los intentos.
         */
        PERDIDA
    }

    /**
     * Colores utilizados como retroalimentación para cada número ingresado.
     */
    public enum ColorFeedback {

        /**
         * El número está en la posición correcta.
         */
        VERDE,

        /**
         * El número existe en la solución pero en otra posición.
         */
        AMARILLO,

        /**
         * El número no pertenece a la solución.
         */
        GRIS
    }

    /**
     * Cantidad máxima de intentos permitidos.
     */
    private static final int MAX_INTENTOS = 6;
    private static final int[] PUNTOS_POR_INTENTO = { 10, 8, 6, 4, 2, 1 };

    /**
     * Jugador asociado a la partida.
     */
    private final Jugador jugador;

    /**
     * Ecuación generada para la partida.
     */
    private final Ecuacion ecuacion;

    /**
     * Número de intentos restantes del jugador.
     */
    private int intentosRestantes;
<<<<<<< HEAD
    private int puntuacion;
=======

    /**
     * Estado actual de la partida.
     */
>>>>>>> 216b72e0421b35ebaa6c83666a57d363b8b71b00
    private EstadoPartida estado;

    /**
     * Constructor de la clase Partida.
     * 
     * Inicializa una nueva partida con una ecuación aleatoria,
     * el máximo número de intentos y el estado en progreso.
     * 
     * @param jugador jugador que participa en la partida
     */
    public Partida(Jugador jugador) {
        this.jugador = jugador;
        this.ecuacion = new Ecuacion();
        this.intentosRestantes = MAX_INTENTOS;
        this.puntuacion = 0;
        this.estado = EstadoPartida.EN_PROGRESO;
    }

    /**
     * Procesa un intento realizado por el jugador.
     * 
     * Valida el intento, genera el feedback correspondiente
     * y actualiza el estado de la partida.
     * 
     * @param intento lista de números ingresados por el jugador
     * @return lista de colores de retroalimentación o null si el intento es inválido
     */
    public List<ColorFeedback> intentar(List<Integer> intento) {
        String error = validarIntento(intento);

        if (error != null || estado != EstadoPartida.EN_PROGRESO) {
            return null;
        }

        intentosRestantes--;

        List<ColorFeedback> feedback = generarFeedback(intento);

        if (todosVerdes(feedback)) {
            estado = EstadoPartida.GANADA;
            puntuacion = calcularPuntuacion();
        } else if (intentosRestantes == 0) {
            estado = EstadoPartida.PERDIDA;
        }

        return feedback;
    }

    /**
     * Valida que el intento cumpla las reglas del juego.
     * 
     * Las validaciones incluyen:
     * - Tener exactamente 4 números
     * - Estar dentro del rango permitido (1-12)
     * - No contener números repetidos
     * 
     * @param intento lista de números ingresados
     * @return mensaje de error si el intento es inválido o null si es válido
     */
    public String validarIntento(List<Integer> intento) {

        if (intento == null || intento.size() != 4) {
            return "Debes ingresar exactamente 4 numeros";
        }

        for (int num : intento) {
            if (num < 1 || num > 12) {
                return "Numero fuera de rango (1-12)";
            }
        }

        Set<Integer> set = new HashSet<>(intento);

        if (set.size() != 4) {
            return "No se permiten numeros repetidos";
        }

        return null;
    }

    /**
     * Genera la retroalimentación visual del intento realizado.
     * 
     * - VERDE: número correcto en posición correcta.
     * - AMARILLO: número correcto en posición incorrecta.
     * - GRIS: número inexistente en la solución.
     * 
     * @param intento intento ingresado por el jugador
     * @return lista de colores de retroalimentación
     */
    private List<ColorFeedback> generarFeedback(List<Integer> intento) {

        List<ColorFeedback> feedback = new ArrayList<>();
        List<Integer> solucion = ecuacion.getNumeros();

        for (int i = 0; i < solucion.size(); i++) {

            if (intento.get(i).equals(solucion.get(i))) {
                feedback.add(ColorFeedback.VERDE);

            } else if (solucion.contains(intento.get(i))) {
                feedback.add(ColorFeedback.AMARILLO);

            } else {
                feedback.add(ColorFeedback.GRIS);
            }
        }

        return feedback;
    }

    /**
     * Verifica si todos los colores del feedback son verdes.
     * 
     * Esto indica que el jugador encontró la solución correcta.
     * 
     * @param feedback lista de colores generados
     * @return true si todos son verdes, false en caso contrario
     */
    private boolean todosVerdes(List<ColorFeedback> feedback) {

        for (ColorFeedback color : feedback) {
            if (color != ColorFeedback.VERDE) {
                return false;
            }
        }

        return true;
    }

<<<<<<< HEAD
    private int calcularPuntuacion() {
        int intentosUsados = MAX_INTENTOS - intentosRestantes;
        return PUNTOS_POR_INTENTO[intentosUsados - 1];
    }

=======
    /**
     * Obtiene la cantidad de intentos restantes.
     * 
     * @return número de intentos restantes
     */
>>>>>>> 216b72e0421b35ebaa6c83666a57d363b8b71b00
    public int getIntentosRestantes() {
        return intentosRestantes;
    }

<<<<<<< HEAD
    public int getPuntuacion() {
        return puntuacion;
    }

=======
    /**
     * Obtiene el estado actual de la partida.
     * 
     * @return estado de la partida
     */
>>>>>>> 216b72e0421b35ebaa6c83666a57d363b8b71b00
    public EstadoPartida getEstado() {
        return estado;
    }

    /**
     * Obtiene el resultado de la ecuación actual.
     * 
     * @return resultado de la ecuación
     */
    public int getResultado() {
        return ecuacion.getResultado();
    }

    /**
     * Verifica si el intento produce el resultado correcto.
     * 
     * @param intento lista de números ingresados
     * @return true si el resultado coincide, false en caso contrario
     */
    public boolean resultadoCorrecto(List<Integer> intento) {
        return ecuacion.resultadoCorrecto(intento);
    }

    /**
     * Obtiene la solución de la ecuación.
     * 
     * @return lista de números solución
     */
    public List<Integer> getSolucion() {
        return ecuacion.getNumeros();
    }

    /**
     * Obtiene el jugador asociado a la partida.
     * 
     * @return jugador de la partida
     */
    public Jugador getJugador() {
        return jugador;
    }
}
