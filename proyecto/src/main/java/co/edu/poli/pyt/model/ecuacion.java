package co.edu.poli.pyt.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

/**
 * Representa la ecuación matemática utilizada en el juego.
 * 
 * La clase se encarga de generar automáticamente una combinación
 * de 4 números únicos entre 1 y 12, calcular el resultado de la
 * operación matemática y validar intentos realizados por el jugador.
 * 
 * La estructura de la ecuación es:
 * (a * b) - c + d
 */
public class Ecuacion {

    /**
     * Lista de números que conforman la solución de la ecuación.
     */
    private List<Integer> numeros;

    /**
     * Resultado final de la ecuación generada.
     */
    private int resultado;

    /**
     * Constructor de la clase.
     * 
     * Genera automáticamente una nueva ecuación aleatoria.
     */
    public Ecuacion() {
        generar();
    }

    /**
     * Genera 4 números únicos aleatorios entre 1 y 12.
     * 
     * Posteriormente mezcla los números y calcula el
     * resultado correspondiente de la ecuación.
     */
    private void generar() {

        Set<Integer> set = new HashSet<>();
        Random rand = new Random();

        while (set.size() < 4) {
            set.add(rand.nextInt(12) + 1);
        }

        numeros = new ArrayList<>(set);
        Collections.shuffle(numeros, rand);
        resultado = calcularResultado(numeros);
    }

    /**
     * Calcula el resultado de la ecuación matemática
     * usando la estructura:
     * (a * b) - c + d
     * 
     * @param nums lista de números a evaluar
     * @return resultado de la operación matemática
     */
    public int calcularResultado(List<Integer> nums) {
        return (nums.get(0) * nums.get(1)) - nums.get(2) + nums.get(3);
    }

    /**
     * Verifica si el intento ingresado por el jugador
     * produce el mismo resultado de la ecuación original.
     * 
     * @param intento lista de números ingresados por el jugador
     * @return true si el resultado coincide, false en caso contrario
     */
    public boolean resultadoCorrecto(List<Integer> intento) {
        return calcularResultado(intento) == resultado;
    }

    /**
     * Obtiene la lista de números de la ecuación.
     * 
     * @return lista de números solución
     */
    public List<Integer> getNumeros() {
        return numeros;
    }

    /**
     * Obtiene el resultado de la ecuación.
     * 
     * @return resultado de la operación
     */
    public int getResultado() {
        return resultado;
    }
}
