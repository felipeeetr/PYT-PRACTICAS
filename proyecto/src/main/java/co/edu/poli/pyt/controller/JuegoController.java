package co.edu.poli.pyt.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import co.edu.poli.pyt.model.Jugador;
import co.edu.poli.pyt.model.Partida;
import co.edu.poli.pyt.model.Partida.ColorFeedback;
import co.edu.poli.pyt.model.Partida.EstadoPartida;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class JuegoController {

    private static final int MAX_INTENTOS = 6;
    private static final int NUMEROS_POR_INTENTO = 4;

    @FXML
    private VBox tablero;

    @FXML
    private Label resultadoLabel;

    @FXML
    private Label mensajeLabel;

    @FXML
    private Label intentosLabel;

    private Partida partida;
    private final List<List<Label>> casillas = new ArrayList<>();
    private final List<Integer> intentoActual = new ArrayList<>();
    private int filaActual;

    @FXML
    public void initialize() {
        reiniciarPartida();
    }

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

    @FXML
    private void reiniciarPartida() {
        partida = new Partida(new Jugador(1, "Jugador"));
        filaActual = 0;
        intentoActual.clear();
        resultadoLabel.setText(String.valueOf(partida.getResultado()));
        limpiarMensaje();
        construirTablero();
        actualizarIntentos();
    }

    @FXML
    private void volverInicio(ActionEvent event) throws IOException {
        Parent inicio = FXMLLoader.load(getClass().getResource("/co/edu/poli/pyt/view/inicio.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(inicio));
        stage.show();
    }

    private void construirTablero() {
        tablero.getChildren().clear();
        casillas.clear();

        for (int fila = 0; fila < MAX_INTENTOS; fila++) {
            HBox filaTablero = new HBox(10);
            filaTablero.setAlignment(javafx.geometry.Pos.CENTER);

            List<Label> casillasFila = new ArrayList<>();
            agregarCasilla(filaTablero, casillasFila);
            agregarOperador(filaTablero, "*");
            agregarCasilla(filaTablero, casillasFila);
            agregarOperador(filaTablero, "-");
            agregarCasilla(filaTablero, casillasFila);
            agregarOperador(filaTablero, "+");
            agregarCasilla(filaTablero, casillasFila);
            agregarOperador(filaTablero, "=");
            agregarResultado(filaTablero);

            casillas.add(casillasFila);
            tablero.getChildren().add(filaTablero);
        }
    }

    private void agregarCasilla(HBox filaTablero, List<Label> casillasFila) {
        Label casilla = new Label();
        casilla.getStyleClass().add("cell");
        casillasFila.add(casilla);
        filaTablero.getChildren().add(casilla);
    }

    private void agregarOperador(HBox filaTablero, String operador) {
        Label label = new Label(operador);
        label.getStyleClass().add("operator");
        filaTablero.getChildren().add(label);
    }

    private void agregarResultado(HBox filaTablero) {
        Label resultado = new Label(String.valueOf(partida.getResultado()));
        resultado.getStyleClass().add("result");
        filaTablero.getChildren().add(resultado);
    }

    private void pintarFeedback(List<ColorFeedback> feedback) {
        List<Label> fila = casillas.get(filaActual);

        for (int i = 0; i < feedback.size(); i++) {
            Label casilla = fila.get(i);
            casilla.getStyleClass().removeAll("cell-green", "cell-yellow", "cell-gray");

            switch (feedback.get(i)) {
                case VERDE -> casilla.getStyleClass().add("cell-green");
                case AMARILLO -> casilla.getStyleClass().add("cell-yellow");
                case GRIS -> casilla.getStyleClass().add("cell-gray");
            }
        }
    }

    private void actualizarEstado() {
        actualizarIntentos();

        if (partida.getEstado() == EstadoPartida.GANADA) {
            mostrarMensaje("Ganaste. Encontraste la ecuacion.", "message-win");
        } else if (partida.getEstado() == EstadoPartida.PERDIDA) {
            mostrarMensaje("Perdiste. La ecuacion era " + formatearSolucion() + ".", "message-loss");
        }
    }

    private void actualizarIntentos() {
        intentosLabel.setText("Intentos: " + partida.getIntentosRestantes());
    }

    private boolean partidaEnProgreso() {
        return partida != null && partida.getEstado() == EstadoPartida.EN_PROGRESO;
    }

    private String formatearSolucion() {
        List<Integer> solucion = partida.getSolucion();
        return solucion.get(0) + " * " + solucion.get(1) + " - " + solucion.get(2) + " + " + solucion.get(3)
                + " = " + partida.getResultado();
    }

    private void mostrarMensaje(String texto, String estilo) {
        mensajeLabel.getStyleClass().removeAll("message-info", "message-error", "message-win", "message-loss");
        mensajeLabel.getStyleClass().add(estilo);
        mensajeLabel.setText(texto);
    }

    private void limpiarMensaje() {
        mensajeLabel.getStyleClass().removeAll("message-info", "message-error", "message-win", "message-loss");
        mensajeLabel.setText("");
    }
}
