package co.edu.poli.pyt.model;

import java.util.ArrayList;
import java.util.List;

public class Partida {

    private int id;
    private EstadoJuego estado;
    private int intentosRestantes;
    private Ecuacion ecuacion;
    private List<Intento> intentos;

    public Partida(int id, Ecuacion ecuacion) {
        this.id = id;
        this.ecuacion = ecuacion;
        this.estado = EstadoJuego.EN_PROGRESO;
        this.intentosRestantes = 6;
        this.intentos = new ArrayList<>();
    }

    // 🔥 MÉTODO PRINCIPAL
    public Resultado procesarIntento(List<Integer> numerosUsuario) {

        // 1. Validar tamaño
        if (numerosUsuario == null || numerosUsuario.size() != 4) {
            throw new IllegalArgumentException("Debe ingresar exactamente 4 números");
        }

        // 2. Calcular resultado matemático
        int calculo = numerosUsuario.get(0)
                + (numerosUsuario.get(1) * numerosUsuario.get(2))
                - numerosUsuario.get(3);

        boolean esCorrectoMatematico = calculo == ecuacion.getResultado();

        // 3. Generar feedback tipo Wordle
        List<String> feedback = generarFeedback(numerosUsuario, ecuacion.getNumeros());

        // 4. Verificar victoria
        boolean aciertoTotal = numerosUsuario.equals(ecuacion.getNumeros());

        // 5. Mensaje unificado
        String mensaje;

        if (aciertoTotal) {
            mensaje = "Correcto";
            estado = EstadoJuego.GANADO;
        } else if (!esCorrectoMatematico) {
            mensaje = "Resultado incorrecto";
        } else {
            mensaje = "Sigue intentando";
        }

        // 6. Crear resultado
        Resultado resultado = new Resultado(aciertoTotal, feedback, mensaje);

        // 7. Registrar intento
        Intento intento = new Intento(numerosUsuario, resultado);
        intentos.add(intento);

        // 8. Reducir intentos
        intentosRestantes--;

        // 9. Verificar derrota
        if (intentosRestantes == 0 && !aciertoTotal) {
            estado = EstadoJuego.PERDIDO;
        }

        return resultado;
    }

    // 🔥 Feedback tipo Wordle (simple)
    private List<String> generarFeedback(List<Integer> usuario, List<Integer> real) {

        List<String> feedback = new ArrayList<>();

        for (int i = 0; i < usuario.size(); i++) {

            if (usuario.get(i).equals(real.get(i))) {
                feedback.add("VERDE");
            } else if (real.contains(usuario.get(i))) {
                feedback.add("AMARILLO");
            } else {
                feedback.add("GRIS");
            }
        }

        return feedback;
    }

    public EstadoJuego getEstado() {
        return estado;
    }

    public int getIntentosRestantes() {
        return intentosRestantes;
    }

    public List<Intento> getIntentos() {
        return intentos;
    }

    public Ecuacion getEcuacion() {
        return ecuacion;
    }
}