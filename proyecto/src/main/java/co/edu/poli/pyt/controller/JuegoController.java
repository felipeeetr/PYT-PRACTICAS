package co.edu.poli.pyt.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import co.edu.poli.pyt.model.Jugador;
import co.edu.poli.pyt.model.Partida;
import co.edu.poli.pyt.model.Partida.ColorFeedback;
import co.edu.poli.pyt.model.Partida.EstadoPartida;
import co.edu.poli.pyt.services.JugadorRepository;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Controlador principal de la interfaz del juego.
 * 
 * La clase administra toda la interacción entre la vista
 * y la lógica del juego, incluyendo:
 * 
 * - Selección de números
 * - Validación de intentos
 * - Actualización del tablero
 * - Retroalimentación visual
 * - Control del estado de la partida
 * - Reinicio del juego
 */
public class JuegoController {

    /**
     * Cantidad máxima de intentos permitidos.
     */
    private static final int MAX_INTENTOS = 6;

    /**
     * Cantidad de números requeridos por intento.
     */
    private static final int NUMEROS_POR_INTENTO = 4;

    /**
     * Contenedor principal del tablero.
     */
    @FXML
    private VBox tablero;

    /**
     * Etiqueta que muestra el resultado de la ecuación.
     */
    @FXML
    private Label resultadoLabel;

    /**
     * Etiqueta utilizada para mostrar mensajes al jugador.
     */
    @FXML
    private Label mensajeLabel;

    /**
     * Etiqueta que muestra los intentos restantes.
     */
    @FXML
    private Label intentosLabel;

    @FXML
    private Label jugadorLabel;

    @FXML
    private Label r0c0, r0c1, r0c2, r0c3, r0Resultado;

    @FXML
    private Label r1c0, r1c1, r1c2, r1c3, r1Resultado;

    @FXML
    private Label r2c0, r2c1, r2c2, r2c3, r2Resultado;

    @FXML
    private Label r3c0, r3c1, r3c2, r3c3, r3Resultado;

    @FXML
    private Label r4c0, r4c1, r4c2, r4c3, r4Resultado;

    @FXML
    private Label r5c0, r5c1, r5c2, r5c3, r5Resultado;

    /**
     * Instancia de la partida actual.
     */
    private Partida partida;
<<<<<<< HEAD
    private Jugador jugador;
    private final JugadorRepository jugadorRepository = new JugadorRepository();
=======

    /**
     * Lista bidimensional que representa las casillas del tablero.
     */
>>>>>>> 216b72e0421b35ebaa6c83666a57d363b8b71b00
    private final List<List<Label>> casillas = new ArrayList<>();

    /**
     * Lista de etiquetas que muestran los resultados por fila.
     */
    private final List<Label> resultados = new ArrayList<>();

    /**
     * Lista de números ingresados en el intento actual.
     */
    private final List<Integer> intentoActual = new ArrayList<>();

    /**
     * Índice de la fila actual del tablero.
     */
    private int filaActual;

    /**
     * Inicializa el controlador y prepara el tablero del juego.
     * 
     * Este método es ejecutado automáticamente por JavaFX
     * al cargar la vista FXML.
     */
    @FXML
    public void initialize() {
        enlazarTablero();
        reiniciarPartida();
    }

<<<<<<< HEAD
    public void configurarJugador(Jugador jugador) {
        this.jugador = jugador;
        reiniciarPartida();
    }

=======
    /**
     * Agrega un número al intento actual del jugador.
     * 
     * También valida que:
     * - La partida siga en progreso
     * - No existan números repetidos
     * - No se excedan las 4 casillas permitidas
     * 
     * @param event evento generado al presionar un botón numérico
     */
>>>>>>> 216b72e0421b35ebaa6c83666a57d363b8b71b00
    @FXML
    private void seleccionarNumero(ActionEvent event) {

        if (!partidaEnProgreso()) {
            return;
        }

        Button boton = (Button) event.getSource();
        int numero = Integer.parseInt(boton.getText());

        if (intentoActual.contains(numero)) {
            mostrarMensaje("No se permiten numeros repetidos en el intento.", "message-error");
            return;
        }

        if (intentoActual.size() == NUMEROS_POR_INTENTO) {
            mostrarMensaje("Ya llenaste las 4 casillas. Presiona Check.", "message-error");
            return;
        }

        intentoActual.add(numero);
        casillas.get(filaActual).get(intentoActual.size() - 1).setText(String.valueOf(numero));

        limpiarMensaje();
    }

    /**
     * Elimina el último número ingresado del intento actual.
     */
    @FXML
    private void borrarNumero() {

        if (!partidaEnProgreso() || intentoActual.isEmpty()) {
            return;
        }

        int ultimaPosicion = intentoActual.size() - 1;

        intentoActual.remove(ultimaPosicion);
        casillas.get(filaActual).get(ultimaPosicion).setText("");

        limpiarMensaje();
    }

    /**
     * Comprueba el intento actual realizado por el jugador.
     * 
     * Valida el intento, genera el feedback visual
     * y actualiza el estado de la partida.
     */
    @FXML
    private void comprobarIntento() {

        if (!partidaEnProgreso()) {
            return;
        }

        String error = partida.validarIntento(intentoActual);

        if (error != null) {
            mostrarMensaje(error, "message-error");
            return;
        }

        List<ColorFeedback> feedback = partida.intentar(new ArrayList<>(intentoActual));

        pintarFeedback(feedback);
        actualizarEstado();

        if (partida.getEstado() == EstadoPartida.EN_PROGRESO) {
            filaActual++;
            intentoActual.clear();

            mostrarMensaje("Intento calificado. Sigue probando posiciones.", "message-info");
        }
    }

    /**
     * Reinicia completamente la partida actual.
     * 
     * Genera una nueva ecuación y limpia el tablero.
     */
    @FXML
    private void reiniciarPartida() {
<<<<<<< HEAD
        Jugador jugadorPartida = jugador != null ? jugador : new Jugador(0, "Invitado");
        partida = new Partida(jugadorPartida);
=======

        partida = new Partida(new Jugador(1, "Jugador"));

>>>>>>> 216b72e0421b35ebaa6c83666a57d363b8b71b00
        filaActual = 0;
        intentoActual.clear();

        resultadoLabel.setText(String.valueOf(partida.getResultado()));
<<<<<<< HEAD
        actualizarJugador();
=======

>>>>>>> 216b72e0421b35ebaa6c83666a57d363b8b71b00
        limpiarMensaje();
        limpiarTablero();
        actualizarIntentos();
    }

    /**
     * Regresa a la pantalla principal del juego.
     * 
     * @param event evento generado al presionar el botón
     * @throws IOException si ocurre un error al cargar el archivo FXML
     */
    @FXML
    private void volverInicio(ActionEvent event) throws IOException {

        Parent inicio = FXMLLoader.load(
                getClass().getResource("/co/edu/poli/pyt/view/inicio.fxml")
        );

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        stage.setScene(new Scene(inicio));
        stage.show();
    }

    /**
     * Relaciona las casillas visuales del tablero con listas dinámicas.
     * 
     * Facilita el acceso y manipulación de las filas del tablero.
     */
    private void enlazarTablero() {

        casillas.clear();
        resultados.clear();

        casillas.add(List.of(r0c0, r0c1, r0c2, r0c3));
        casillas.add(List.of(r1c0, r1c1, r1c2, r1c3));
        casillas.add(List.of(r2c0, r2c1, r2c2, r2c3));
        casillas.add(List.of(r3c0, r3c1, r3c2, r3c3));
        casillas.add(List.of(r4c0, r4c1, r4c2, r4c3));
        casillas.add(List.of(r5c0, r5c1, r5c2, r5c3));

        resultados.addAll(List.of(
                r0Resultado,
                r1Resultado,
                r2Resultado,
                r3Resultado,
                r4Resultado,
                r5Resultado
        ));
    }

    /**
     * Limpia visualmente todas las casillas del tablero.
     * 
     * También elimina estilos de retroalimentación anteriores.
     */
    private void limpiarTablero() {

        String resultado = String.valueOf(partida.getResultado());

        for (List<Label> fila : casillas) {

            for (Label casilla : fila) {
                casilla.setText("");
                casilla.getStyleClass().removeAll(
                        "cell-green",
                        "cell-yellow",
                        "cell-gray"
                );
            }
        }

        for (Label resultadoFila : resultados) {
            resultadoFila.setText(resultado);
        }
    }

    /**
     * Aplica los colores de retroalimentación a las casillas del tablero.
     * 
     * @param feedback lista de colores generados para el intento
     */
    private void pintarFeedback(List<ColorFeedback> feedback) {

        List<Label> fila = casillas.get(filaActual);

        for (int i = 0; i < feedback.size(); i++) {

            Label casilla = fila.get(i);

            casilla.getStyleClass().removeAll(
                    "cell-green",
                    "cell-yellow",
                    "cell-gray"
            );

            switch (feedback.get(i)) {

                case VERDE -> casilla.getStyleClass().add("cell-green");

                case AMARILLO -> casilla.getStyleClass().add("cell-yellow");

                case GRIS -> casilla.getStyleClass().add("cell-gray");
            }
        }
    }

    /**
     * Actualiza el estado visual y lógico de la partida.
     * 
     * Muestra mensajes de victoria o derrota cuando corresponde.
     */
    private void actualizarEstado() {

        actualizarIntentos();

        if (partida.getEstado() == EstadoPartida.GANADA) {
<<<<<<< HEAD
            guardarVictoria();
=======

            mostrarMensaje(
                    "Ganaste. Encontraste la ecuacion.",
                    "message-win"
            );

>>>>>>> 216b72e0421b35ebaa6c83666a57d363b8b71b00
        } else if (partida.getEstado() == EstadoPartida.PERDIDA) {

            mostrarMensaje(
                    "Perdiste. La ecuacion era " + formatearSolucion() + ".",
                    "message-loss"
            );
        }
    }

    /**
     * Actualiza la etiqueta de intentos restantes.
     */
    private void actualizarIntentos() {
        intentosLabel.setText("Intentos: " + partida.getIntentosRestantes());
    }

<<<<<<< HEAD
    private void actualizarJugador() {
        if (jugador == null) {
            jugadorLabel.setText("Invitado");
        } else {
            jugadorLabel.setText("Jugador: " + jugador.getNombre() + " | ID: " + jugador.getId());
        }
    }

    private void guardarVictoria() {
        int puntos = partida.getPuntuacion();

        if (jugador == null) {
            mostrarMensaje("Ganaste. Puntos de esta partida: " + puntos + " (invitado, no se guarda).", "message-win");
            return;
        }

        try {
            jugadorRepository.registrarVictoria(jugador, puntos);
            mostrarMensaje("Ganaste. Sumaste " + puntos + " puntos.", "message-win");
        } catch (SQLException e) {
            mostrarMensaje("Ganaste, pero no se pudo guardar el puntaje.", "message-error");
        }
    }

=======
    /**
     * Verifica si la partida sigue activa.
     * 
     * @return true si la partida está en progreso, false en caso contrario
     */
>>>>>>> 216b72e0421b35ebaa6c83666a57d363b8b71b00
    private boolean partidaEnProgreso() {
        return partida != null
                && partida.getEstado() == EstadoPartida.EN_PROGRESO;
    }

    /**
     * Construye un texto legible con la solución completa.
     * 
     * @return ecuación solución formateada
     */
    private String formatearSolucion() {

        List<Integer> solucion = partida.getSolucion();

        return solucion.get(0) + " * "
                + solucion.get(1) + " - "
                + solucion.get(2) + " + "
                + solucion.get(3)
                + " = "
                + partida.getResultado();
    }

    /**
     * Muestra un mensaje en pantalla con un estilo visual específico.
     * 
     * @param texto  mensaje a mostrar
     * @param estilo clase CSS asociada al mensaje
     */
    private void mostrarMensaje(String texto, String estilo) {

        mensajeLabel.getStyleClass().removeAll(
                "message-info",
                "message-error",
                "message-win",
                "message-loss"
        );

        mensajeLabel.getStyleClass().add(estilo);
        mensajeLabel.setText(texto);
    }

    /**
     * Limpia el mensaje mostrado actualmente en pantalla.
     */
    private void limpiarMensaje() {

        mensajeLabel.getStyleClass().removeAll(
                "message-info",
                "message-error",
                "message-win",
                "message-loss"
        );

        mensajeLabel.setText("");
    }
}
