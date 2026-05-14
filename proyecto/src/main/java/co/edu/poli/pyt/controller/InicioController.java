package co.edu.poli.pyt.controller;

import java.io.IOException;
import java.sql.SQLException;

import co.edu.poli.pyt.model.Jugador;
import co.edu.poli.pyt.services.JugadorRepository;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class InicioController {

    @FXML
    private TextField nombreField;

    @FXML
    private Label mensajeLabel;

    private final JugadorRepository jugadorRepository = new JugadorRepository();

    @FXML
    private void iniciarComoInvitado(ActionEvent event) throws IOException {
        abrirJuego(event, null);
    }

    @FXML
    private void iniciarConUsuario(ActionEvent event) throws IOException {
        String nombre = nombreField.getText();
        if (nombre == null || nombre.isBlank()) {
            mostrarMensaje("Escribe un nombre para iniciar sesion.");
            return;
        }

        try {
            Jugador jugador = jugadorRepository.buscarOCrearPorNombre(nombre);
            abrirJuego(event, jugador);
        } catch (SQLException e) {
            mostrarMensaje("No se pudo conectar con la base de datos.");
        }
    }

    @FXML
    private void verPuntajes(ActionEvent event) throws IOException {
        Parent puntajes = FXMLLoader.load(getClass().getResource("/co/edu/poli/pyt/view/puntajes.fxml"));
        Stage owner = (Stage) ((Node) event.getSource()).getScene().getWindow();

        Stage stage = new Stage();
        stage.setTitle("Puntajes");
        stage.initOwner(owner);
        stage.initModality(Modality.WINDOW_MODAL);
        stage.setScene(new Scene(puntajes));
        stage.showAndWait();
    }

    @FXML
    private void mostrarInstrucciones(ActionEvent event) throws IOException {
        Parent instrucciones = FXMLLoader.load(getClass().getResource("/co/edu/poli/pyt/view/instrucciones.fxml"));
        Stage owner = (Stage) ((Node) event.getSource()).getScene().getWindow();

        Stage stage = new Stage();
        stage.setTitle("Instrucciones");
        stage.initOwner(owner);
        stage.initModality(Modality.WINDOW_MODAL);
        stage.setScene(new Scene(instrucciones));
        stage.showAndWait();
    }

    private void abrirJuego(ActionEvent event, Jugador jugador) throws IOException {
        FXMLLoader loader = new FXMLLoader(
                getClass().getResource("/co/edu/poli/pyt/view/juego.fxml")
        );

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Parent juego = loader.load();
        JuegoController controller = loader.getController();
        controller.configurarJugador(jugador);

        stage.setScene(new Scene(juego));
        stage.show();
    }

    private void mostrarMensaje(String texto) {
        mensajeLabel.setText(texto);
    }
}
