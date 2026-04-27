package co.edu.poli.pyt.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Ecuacion {

    private String expresion;
    private int resultado;
    private List<Integer> numeros;

    public Ecuacion(String expresion, int resultado, List<Integer> numeros) {
        this.expresion = expresion;
        this.resultado = resultado;
        this.numeros = numeros;
    }

    public static Ecuacion generarEcuacion() {

        Random random = new Random();

        int a = random.nextInt(12) + 1;
        int b = random.nextInt(12) + 1;
        int c = random.nextInt(12) + 1;
        int d = random.nextInt(12) + 1;

        int resultado = a + (b * c) - d;

        String expresion = a + " + " + b + " * " + c + " - " + d;

        List<Integer> numeros = new ArrayList<>();
        numeros.add(a);
        numeros.add(b);
        numeros.add(c);
        numeros.add(d);

        return new Ecuacion(expresion, resultado, numeros);
    }

    public int getResultado() {
        return resultado;
    }

    public List<Integer> getNumeros() {
        return numeros;
    }

    public String getExpresion() {
        return expresion;
    }
}
