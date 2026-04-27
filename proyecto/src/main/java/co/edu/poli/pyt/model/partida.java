package co.edu.poli.pyt.model;

import java.util.*;

public class Partida {

    // Enums internos (solo usados aquí)
    public enum EstadoPartida {
        EN_PROGRESO,
        GANADA,
        PERDIDA
    }

    public enum ColorFeedback {
        VERDE,
        AMARILLO,
        GRIS
    }

    private Jugador jugador;
    private Ecuacion ecuacion;
    private int intentosRestantes;
    private EstadoPartida estado;

    private static final int MAX_INTENTOS = 6;

    public Partida(Jugador jugador) {
        this.jugador = jugador;
        this.ecuacion = new Ecuacion();
        this.intentosRestantes = MAX_INTENTOS;
        this.estado = EstadoPartida.EN_PROGRESO;
    }

    // Método principal del juego
    public List<ColorFeedback> intentar(List<Integer> intento) {

        // 1. Validación (NO descuenta intento)
        String error = validarIntento(intento);
        if (error != null) {
            System.out.println(error); // luego va en la UI
            return null;
        }

        // 2. Cuenta intento
        intentosRestantes--;

        // 3. Verifica resultado correcto
        if (ecuacion.resultadoCorrecto(intento)) {

            List<ColorFeedback> feedback = generarFeedback(intento);

            // Si todos son verdes → gana
            if (todosVerdes(feedback)) {
                estado = EstadoPartida.GANADA;
            }

            return feedback;
        }

        // 4. Si se queda sin intentos → pierde
        if (intentosRestantes == 0) {
            estado = EstadoPartida.PERDIDA;
        }

        // Intento válido pero sin feedback
        return null;
    }

    // Genera feedback tipo Wordle SOLO para números
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

    // Validaciones clave del intento
    private String validarIntento(List<Integer> intento) {

        if (intento == null || intento.size() != 4) {
            return "Debes ingresar exactamente 4 números";
        }

        // Rango 1–12
        for (int num : intento) {
            if (num < 1 || num > 12) {
                return "Número fuera de rango (1-12)";
            }
        }

        // Sin repetidos
        Set<Integer> set = new HashSet<>(intento);
        if (set.size() != 4) {
            return "No se permiten números repetidos";
        }

        return null;
    }

    private boolean todosVerdes(List<ColorFeedback> feedback) {
        for (ColorFeedback c : feedback) {
            if (c != ColorFeedback.VERDE) {
                return false;
            }
        }
        return true;
    }

    // Getters
    public int getIntentosRestantes() {
        return intentosRestantes;
    }

    public EstadoPartida getEstado() {
        return estado;
    }

    public int getResultado() {
        return ecuacion.getResultado();
    }

    public Jugador getJugador() {
        return jugador;
    }
}
