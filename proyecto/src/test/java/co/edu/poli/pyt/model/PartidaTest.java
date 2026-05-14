package co.edu.poli.pyt.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import co.edu.poli.pyt.model.Partida.EstadoPartida;

public class PartidaTest {

    @Test
    void puntuacionDebeSerCeroAntesDeGanar() {
        Partida partida = new Partida(new Jugador(1, "Jugador"));

        assertEquals(0, partida.getPuntuacion());
    }

    @Test
    void debeAsignarDiezPuntosSiGanaEnPrimerIntento() {
        Partida partida = new Partida(new Jugador(1, "Jugador"));

        partida.intentar(partida.getSolucion());

        assertEquals(EstadoPartida.GANADA, partida.getEstado());
        assertEquals(10, partida.getPuntuacion());
    }

    @Test
    void debeAsignarPuntosSegunElIntentoEnQueGana() {
        assertEquals(8, ganarEnIntento(2));
        assertEquals(6, ganarEnIntento(3));
        assertEquals(4, ganarEnIntento(4));
        assertEquals(2, ganarEnIntento(5));
        assertEquals(1, ganarEnIntento(6));
    }

    private int ganarEnIntento(int numeroIntento) {
        Partida partida = new Partida(new Jugador(1, "Jugador"));
        List<Integer> intentoIncorrecto = generarIntentoIncorrecto(partida.getSolucion());

        for (int i = 1; i < numeroIntento; i++) {
            partida.intentar(intentoIncorrecto);
        }

        partida.intentar(partida.getSolucion());
        return partida.getPuntuacion();
    }

    private List<Integer> generarIntentoIncorrecto(List<Integer> solucion) {
        List<Integer> intento = new ArrayList<>(solucion);
        Collections.rotate(intento, 1);
        return intento;
    }
}
