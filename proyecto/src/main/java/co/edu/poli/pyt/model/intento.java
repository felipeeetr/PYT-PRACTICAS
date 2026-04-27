package co.edu.poli.pyt.model;

import java.util.List;

public class Intento {

    private List<Integer> numeros;
    private Resultado resultado;

    public Intento(List<Integer> numeros, Resultado resultado) {
        this.numeros = numeros;
        this.resultado = resultado;
    }

    public List<Integer> getNumeros() {
        return numeros;
    }

    public Resultado getResultado() {
        return resultado;
    }
}