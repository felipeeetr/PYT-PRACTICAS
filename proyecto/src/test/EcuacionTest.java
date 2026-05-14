package co.edu.poli.pyt.model;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

/**
 * Clase de pruebas unitarias para la clase Ecuacion.
 * 
 * Esta clase verifica el correcto funcionamiento de:
 * 
 * - El cálculo del resultado matemático
 * - La validación de resultados
 * - La generación de números únicos
 * - El rango permitido de números
 */
public class EcuacionTest {

    /**
     * Verifica que el cálculo de la ecuación
     * se realice correctamente.
     */
    @Test
    void deberiaCalcularResultadoCorrecto() {

        Ecuacion eq = new Ecuacion();

        List<Integer> numeros = List.of(2, 3, 4, 5);

        int resultado = eq.calcularResultado(numeros);

        assertEquals((2 * 3) - 4 + 5, resultado);
    }

    /**
     * Verifica que el sistema detecte correctamente
     * una solución válida.
     */
    @Test
    void deberiaDetectarResultadoCorrecto() {

        Ecuacion eq = new Ecuacion();

        List<Integer> numeros = eq.getNumeros();

        assertTrue(eq.resultadoCorrecto(numeros));
    }

    /**
     * Verifica que el sistema detecte correctamente
     * un resultado incorrecto.
     */
    @Test
    void deberiaDetectarResultadoIncorrecto() {

        Ecuacion eq = new Ecuacion();

        List<Integer> intentoIncorrecto = List.of(1, 1, 1, 1);

        assertFalse(eq.resultadoCorrecto(intentoIncorrecto));
    }

    /**
     * Verifica que los números generados
     * en la ecuación sean únicos.
     */
    @Test
    void numerosGeneradosDebenSerUnicos() {

        Ecuacion eq = new Ecuacion();

        List<Integer> nums = eq.getNumeros();

        long distintos = nums.stream().distinct().count();

        assertEquals(4, distintos);
    }

    /**
     * Verifica que los números generados
     * se encuentren dentro del rango permitido.
     */
    @Test
    void numerosDebenEstarEnRango() {

        Ecuacion eq = new Ecuacion();

        List<Integer> nums = eq.getNumeros();

        for (int num : nums) {
            assertTrue(num >= 1 && num <= 12);
        }
    }
}