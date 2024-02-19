package ventasR.Controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

public class InicioController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnClientes;

    @FXML
    private Button btnProductos;

    @FXML
    private AnchorPane ventana;

    @FXML
    void clientesEvent(ActionEvent event) throws IOException{
            new ViewController(ventana, "/views/registroCliente.fxml");

    }

    @FXML
    void productosEfvent(ActionEvent event) throws IOException{
            new ViewController(ventana, "/views/registroVentas.fxml");

    }

    @FXML
    void initialize() {

//        DataBase.getInstance().getListaVentas().add(new Ventas("1234","Default sell","Venta por defecto",15000,666));
//        DataBase.getInstance().getListaClientesNaturales().add(new ClienteNatural("Default","Client","1234","Avenida Siempre viva","7327002","client@gmail.com", LocalDate.now()));
//        DataBase.getInstance().getListaClientesJuridicos().add(new ClienteJuridico("Client","Default","666","avenida juridica","3205889632","777"));

    }

}
