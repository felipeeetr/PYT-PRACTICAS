package co.edu.poli.pyt.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class Ecuacion {

    private List<Integer> numeros; // solución
    private int resultado;

    public Ecuacion() {
        generar();
    }

    // Genera 4 números únicos entre 1 y 12
    private void generar() {

        Set<Integer> set = new HashSet<>();
        Random rand = new Random();

        while (set.size() < 4) {
            set.add(rand.nextInt(12) + 1);
        }

        numeros = new ArrayList<>(set);
        resultado = calcularResultado(numeros);
    }

    // (a * b) - c + d
    public int calcularResultado(List<Integer> nums) {
        return (nums.get(0) * nums.get(1)) - nums.get(2) + nums.get(3);
    }

    // Verifica si el intento da el mismo resultado
    public boolean resultadoCorrecto(List<Integer> intento) {
        return calcularResultado(intento) == resultado;
    }

    // Getters
    public List<Integer> getNumeros() {
        return numeros;
    }

    public int getResultado() {
        return resultado;
    }
}
