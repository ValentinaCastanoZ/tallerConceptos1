package ventasR.Controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import ventasR.database.DataBase;
import ventasR.exception.RutaInvalidaException;
import ventasR.model.ClienteNatural;
import ventasR.model.Empresa;

public class RegistroClienteNaturalController {

    ClienteNatural clienteNaturalSeleccionado;

    ObservableList<ClienteNatural> listaClienteNatural = FXCollections.observableArrayList(Empresa.getInstance().getClientesNaturales());

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnActualizarCliente;

    @FXML
    private Button btnAtras;

    @FXML
    private Button btnEliminarCliente;

    @FXML
    private Button btnRegistrarCliente;

    @FXML
    private Button btnVentas;

    @FXML
    private TableColumn<ClienteNatural, String> columEmail;

    @FXML
    private TableColumn<ClienteNatural, String> columId;

    @FXML
    private TableColumn<ClienteNatural, String> columNombre;

    @FXML
    private DatePicker dateFechaN;

    @FXML
    private TableView<ClienteNatural> tabClientesNaturales;

    @FXML
    private TextField txtApellido;

    @FXML
    private TextField txtDireccion;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtIdentificacion;

    @FXML
    private TextField txtNombre;

    @FXML
    private TextField txtTelefono;
    @FXML
    private AnchorPane ventana;

    private ObservableList<ClienteNatural> clientesObservables;

    @FXML
    void actualizarClienteEvent(ActionEvent event) {

        ClienteNatural clienteSeleccionado = this.tabClientesNaturales.getSelectionModel().getSelectedItem();

        ClienteNatural clienteNuevo = new ClienteNatural(
                this.txtNombre.getText(),this.txtApellido.getText(),this.txtIdentificacion.getText(),this.txtDireccion.getText(),
                this.txtTelefono.getText(),this.txtEmail.getText(),this.dateFechaN.getValue()
        );

        if(clienteSeleccionado!=null){
            DataBase.getInstance().actualizarClienteNatural(clienteSeleccionado, clienteNuevo);
            refreshTable();
        }else {

            Alert alerta = new Alert(Alert.AlertType.ERROR,"Seleccione un cliente primero",ButtonType.OK);
            alerta.showAndWait();

        }
    }

    @FXML
    void atrasEvent(ActionEvent event) throws IOException {
        new ViewController(ventana, "/views/inicio.fxml");

    }
    @FXML
    void eliminarClienteEvent(ActionEvent event) {

        ClienteNatural clienteSeleccionado = this.tabClientesNaturales.getSelectionModel().getSelectedItem();

        if(clienteSeleccionado!=null){
            DataBase.getInstance().eliminarClienteNatural(clienteSeleccionado);
            refreshTable();
        }else {

            Alert alerta = new Alert(Alert.AlertType.ERROR,"Seleccione un cliente primero",ButtonType.OK);
            alerta.showAndWait();

        }

    }

    @FXML
    void registrarClienteEvent(ActionEvent event) {

        ClienteNatural cliente = new ClienteNatural(
                this.txtNombre.getText(),this.txtApellido.getText(),this.txtIdentificacion.getText(),this.txtDireccion.getText(),
                this.txtTelefono.getText(),this.txtEmail.getText(),this.dateFechaN.getValue()
        );

        DataBase.getInstance().crearClienteNatural(cliente);
        refreshTable();

    }


    @FXML
    void ventasEvent(ActionEvent event) throws IOException{
        new ViewController(ventana, "/views/registroVentas.fxml");

    }

    @FXML
    void initialize() throws RutaInvalidaException {

        refreshTable();

      
    }

    public void refreshTable(){
        clientesObservables = FXCollections.observableArrayList();

        this.columNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        this.columId.setCellValueFactory(new PropertyValueFactory<>("identificacion"));
        this.columEmail.setCellValueFactory(new PropertyValueFactory<>("email"));

        this.clientesObservables.addAll(DataBase.getInstance().getListaClientesNaturales());
        this.tabClientesNaturales.setItems(clientesObservables);
    }

}
