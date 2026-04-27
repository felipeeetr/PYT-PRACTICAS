package co.edu.poli.pyt;

import java.util.List;

import co.edu.poli.pyt.model.Jugador;
import co.edu.poli.pyt.model.Partida;

public class PruebaJuego {

    public static void main(String[] args) {

        Jugador jugador = new Jugador(1, "Pipe");
        Partida partida = new Partida(jugador);

        System.out.println("Resultado objetivo: " + partida.getResultado());

        // Intento de prueba
        List<Integer> intento = List.of(1, 2, 3, 4);

        System.out.println("Intento: " + intento);

        var feedback = partida.intentar(intento);

        if (feedback != null) {
            System.out.println("Feedback: " + feedback);
        } else {
            System.out.println("Sin feedback (resultado incorrecto o inválido)");
        }

        System.out.println("Intentos restantes: " + partida.getIntentosRestantes());
        System.out.println("Estado: " + partida.getEstado());
    }
}