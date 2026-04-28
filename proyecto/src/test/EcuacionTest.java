package co.edu.poli.pyt.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

public class EcuacionTest {

    @Test
    void deberiaCalcularResultadoCorrecto() {
        Ecuacion eq = new Ecuacion();

        List<Integer> numeros = List.of(2, 3, 4, 5);

        int resultado = eq.calcularResultado(numeros);

        assertEquals((2 * 3) - 4 + 5, resultado);
    }

    @Test
    void deberiaDetectarResultadoCorrecto() {
        Ecuacion eq = new Ecuacion();

        List<Integer> numeros = eq.getNumeros();

        assertTrue(eq.resultadoCorrecto(numeros));
    }

    @Test
    void deberiaDetectarResultadoIncorrecto() {
        Ecuacion eq = new Ecuacion();

        List<Integer> intentoIncorrecto = List.of(1, 1, 1, 1);

        assertFalse(eq.resultadoCorrecto(intentoIncorrecto));
    }

    @Test
    void numerosGeneradosDebenSerUnicos() {
        Ecuacion eq = new Ecuacion();

        List<Integer> nums = eq.getNumeros();

        long distintos = nums.stream().distinct().count();

        assertEquals(4, distintos);
    }

    @Test
    void numerosDebenEstarEnRango() {
        Ecuacion eq = new Ecuacion();

        List<Integer> nums = eq.getNumeros();

        for (int num : nums) {
            assertTrue(num >= 1 && num <= 12);
        }
    }
}