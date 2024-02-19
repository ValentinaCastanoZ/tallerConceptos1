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
import ventasR.model.ClienteJuridico;

public class RegistroClienteJuridicoController {

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
    private TextField txtApellido;

    @FXML
    private TextField txtDireccion;

    @FXML
    private TextField txtIdentificacion;

    @FXML
    private TextField txtNit;

    @FXML
    private TextField txtNombre;

    @FXML
    private TextField txtTelefono;

    @FXML
    private AnchorPane ventana;

    @FXML
    private TableColumn<?, ?> colId;

    @FXML
    private TableColumn<?, ?> colNit;

    @FXML
    private TableColumn<?, ?> colNombre;

    @FXML
    private TableView<ClienteJuridico> tblClientes;

    private ObservableList<ClienteJuridico> clientesObservables;

    @FXML
    void actualizarClienteEvent(ActionEvent event) {

        ClienteJuridico clienteSeleccionado = this.tblClientes.getSelectionModel().getSelectedItem();

        ClienteJuridico clienteNuevo = new ClienteJuridico(
                this.txtNombre.getText(),this.txtApellido.getText(),this.txtIdentificacion.getText(),this.txtDireccion.getText(),
                this.txtTelefono.getText(),this.txtNit.getText()
        );

        if(clienteSeleccionado!=null){
            DataBase.getInstance().actualizarClienteJuridico(clienteSeleccionado, clienteNuevo);
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

        ClienteJuridico clienteSeleccionado = this.tblClientes.getSelectionModel().getSelectedItem();

        if(clienteSeleccionado!=null){
            DataBase.getInstance().eliminarClienteJuridico(clienteSeleccionado);
            refreshTable();
        }else {

            Alert alerta = new Alert(Alert.AlertType.ERROR,"Seleccione un cliente primero",ButtonType.OK);
            alerta.showAndWait();

        }

    }

    @FXML
    void registrarClienteEvent(ActionEvent event) {

        ClienteJuridico clienteNuevo = new ClienteJuridico(
                this.txtNombre.getText(),this.txtApellido.getText(),this.txtIdentificacion.getText(),this.txtDireccion.getText(),
                this.txtTelefono.getText(),this.txtNit.getText()
        );

        DataBase.getInstance().crearClienteJuridico(clienteNuevo);
        refreshTable();
    }

    @FXML
    void ventasEvent(ActionEvent event) throws IOException{
        new ViewController(ventana, "/views/registroVentas.fxml");
    }

    @FXML
    void initialize() {
        refreshTable();
    }

    public void refreshTable(){
        clientesObservables = FXCollections.observableArrayList();

        this.colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        this.colId.setCellValueFactory(new PropertyValueFactory<>("identificacion"));
        this.colNit.setCellValueFactory(new PropertyValueFactory<>("nit"));

        this.clientesObservables.addAll(DataBase.getInstance().getListaClientesJuridicos());
        this.tblClientes.setItems(clientesObservables);
    }

}
