package ventasR.Controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

public class RegistroClientesController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnAtras;

    @FXML
    private Button btnClienteJuridico;

    @FXML
    private Button btnClienteNatural;

    @FXML
    private AnchorPane ventana;


    @FXML
    void atrasEvent(ActionEvent event) throws IOException {
        new ViewController(ventana, "/views/inicio.fxml");

    }

    @FXML
    void clienteJuridicoEvent(ActionEvent event) throws IOException {
        new ViewController(ventana, "/views/registroClientesJuridicos.fxml");

    }

    @FXML
    void clienteNaturalEvent(ActionEvent event) throws IOException {
        new ViewController(ventana, "/views/registroClientesNaturales.fxml");

    }

    @FXML
    void initialize() {



    }

}
