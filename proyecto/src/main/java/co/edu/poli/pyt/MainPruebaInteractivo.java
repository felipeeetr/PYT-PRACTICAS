package co.edu.poli.pyt;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import co.edu.poli.pyt.model.Ecuacion;
import co.edu.poli.pyt.model.EstadoJuego;
import co.edu.poli.pyt.model.Partida;
import co.edu.poli.pyt.model.Resultado;


public class MainPruebaInteractivo {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        // 🔥 Crear ecuación ALEATORIA
        Ecuacion ecuacion = Ecuacion.generarEcuacion();

        // ⚠️ SOLO PARA DEBUG (puedes comentar esto si quieres jugar “a ciegas”)
        System.out.println("DEBUG (respuesta): " + ecuacion.getExpresion() + " = " + ecuacion.getResultado());

        // 🔥 Crear partida
        Partida partida = new Partida(1, ecuacion);

        System.out.println("\n🎮 Bienvenido al juego!");
        System.out.println("Debes completar: _ + _ * _ - _ = " + ecuacion.getResultado());
        System.out.println("Tienes 6 intentos\n");

        while (partida.getEstado() == EstadoJuego.EN_PROGRESO) {

            List<Integer> numeros = new ArrayList<>();

            System.out.println("Ingresa 4 números (entre 1 y 12):");

            for (int i = 0; i < 4; i++) {
                System.out.print("Número " + (i + 1) + ": ");
                numeros.add(scanner.nextInt());
            }

            try {
                Resultado resultado = partida.procesarIntento(numeros);

                System.out.println("\n🧠 Resultado: " + resultado.getMensaje());
                System.out.println("🎨 Feedback: " + resultado.getFeedback());
                System.out.println("❤️ Intentos restantes: " + partida.getIntentosRestantes());
                System.out.println("----------------------------------");

            } catch (Exception e) {
                System.out.println("❌ Error: " + e.getMessage());
            }
        }

        // 🔥 Resultado final
        if (partida.getEstado() == EstadoJuego.GANADO) {
            System.out.println("\n🎉 ¡Ganaste!");
        } else {
            System.out.println("\n💀 Perdiste...");
            System.out.println("La ecuación era: " + ecuacion.getExpresion() + " = " + ecuacion.getResultado());
        }

        scanner.close();
    }
}