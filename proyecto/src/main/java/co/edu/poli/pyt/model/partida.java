package co.edu.poli.pyt.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Partida {

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

    private static final int MAX_INTENTOS = 6;

    private final Jugador jugador;
    private final Ecuacion ecuacion;
    private int intentosRestantes;
    private EstadoPartida estado;

    public Partida(Jugador jugador) {
        this.jugador = jugador;
        this.ecuacion = new Ecuacion();
        this.intentosRestantes = MAX_INTENTOS;
        this.estado = EstadoPartida.EN_PROGRESO;
    }

    public List<ColorFeedback> intentar(List<Integer> intento) {
        String error = validarIntento(intento);
        if (error != null || estado != EstadoPartida.EN_PROGRESO) {
            return null;
        }

        intentosRestantes--;

        List<ColorFeedback> feedback = generarFeedback(intento);
        if (todosVerdes(feedback)) {
            estado = EstadoPartida.GANADA;
        } else if (intentosRestantes == 0) {
            estado = EstadoPartida.PERDIDA;
        }

        return feedback;
    }

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

    private boolean todosVerdes(List<ColorFeedback> feedback) {
        for (ColorFeedback color : feedback) {
            if (color != ColorFeedback.VERDE) {
                return false;
            }
        }
        return true;
    }

    public int getIntentosRestantes() {
        return intentosRestantes;
    }

    public EstadoPartida getEstado() {
        return estado;
    }

    public int getResultado() {
        return ecuacion.getResultado();
    }

    public boolean resultadoCorrecto(List<Integer> intento) {
        return ecuacion.resultadoCorrecto(intento);
    }

    public List<Integer> getSolucion() {
        return ecuacion.getNumeros();
    }

    public Jugador getJugador() {
        return jugador;
    }
}
