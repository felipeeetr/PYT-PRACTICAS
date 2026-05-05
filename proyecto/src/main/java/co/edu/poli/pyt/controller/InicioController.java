package co.edu.poli.pyt.controller;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
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
}
