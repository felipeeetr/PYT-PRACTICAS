package co.edu.poli.pyt.controller;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class InicioController {

    @FXML
    private void iniciarJuego(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(
                getClass().getResource("/co/edu/poli/pyt/view/juego.fxml")
        );

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(loader.load()));
        stage.show();
    }

    @FXML
    private void mostrarInstrucciones() {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Instrucciones");
        alert.setHeaderText("Como jugar Ooodle");
        alert.setContentText(
                "El sistema genera una ecuacion secreta con 4 numeros diferentes entre 1 y 12.\n\n"
                + "La estructura siempre es:\n"
                + "a * b - c + d = x\n\n"
                + "Tu objetivo es descubrir los numeros a, b, c y d en el orden correcto.\n\n"
                + "Como jugar:\n"
                + "1. Selecciona 4 numeros usando el teclado.\n"
                + "2. Presiona Check para calificar el intento.\n"
                + "3. Cada intento cuenta, aunque tu ecuacion no produzca exactamente el resultado x.\n"
                + "4. Tienes 6 intentos para encontrar la ecuacion secreta.\n\n"
                + "Colores:\n"
                + "Verde: el numero esta en la posicion correcta.\n"
                + "Amarillo: el numero existe, pero va en otra posicion.\n"
                + "Gris: el numero no hace parte de la solucion.\n\n"
                + "Reglas:\n"
                + "Solo se usan numeros del 1 al 12 y no se permiten repetidos en un intento."
        );
        alert.showAndWait();
    }
}
