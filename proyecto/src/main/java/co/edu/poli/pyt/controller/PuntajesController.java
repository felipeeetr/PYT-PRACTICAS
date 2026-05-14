package co.edu.poli.pyt.controller;

import java.sql.SQLException;

import co.edu.poli.pyt.model.Jugador;
import co.edu.poli.pyt.services.JugadorRepository;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

public class PuntajesController {

    @FXML
    private TableView<Jugador> puntajesTable;

    @FXML
    private TableColumn<Jugador, Number> idColumn;

    @FXML
    private TableColumn<Jugador, String> nombreColumn;

    @FXML
    private TableColumn<Jugador, Number> puntajeColumn;

    @FXML
    private TableColumn<Jugador, Number> ganadasColumn;

    @FXML
    private Label mensajeLabel;

    private final JugadorRepository jugadorRepository = new JugadorRepository();

    @FXML
    public void initialize() {
        configurarTabla();
        cargarPuntajes();
    }

    @FXML
    private void cerrar() {
        Stage stage = (Stage) puntajesTable.getScene().getWindow();
        stage.close();
    }

    private void configurarTabla() {
        idColumn.setCellValueFactory(data -> new SimpleIntegerProperty(data.getValue().getId()));
        nombreColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getNombre()));
        puntajeColumn.setCellValueFactory(data -> new SimpleIntegerProperty(data.getValue().getPuntajeTotal()));
        ganadasColumn.setCellValueFactory(data -> new SimpleIntegerProperty(data.getValue().getPartidasGanadas()));
    }

    private void cargarPuntajes() {
        try {
            puntajesTable.getItems().setAll(jugadorRepository.listarRanking());
            mensajeLabel.setText("");
        } catch (SQLException e) {
            mensajeLabel.setText("No se pudo cargar la base de datos.");
        }
    }
}
