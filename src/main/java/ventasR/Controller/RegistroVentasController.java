package ventasR.Controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import ventasR.database.DataBase;
import ventasR.model.Ventas;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class RegistroVentasController implements Initializable {

    @FXML
    private Button btnAtras;

    @FXML
    private TableColumn<?, ?> colCantidadExistencia;

    @FXML
    private TableColumn<?, ?> colCodigo;

    @FXML
    private TableColumn<?, ?> colDescripcion;

    @FXML
    private TableColumn<?, ?> colNombre;

    @FXML
    private TableColumn<?, ?> colValorUnitario;

    @FXML
    private TableView<Ventas> tblVentas;

    @FXML
    private TextField txtCantidadExistencia;

    @FXML
    private TextField txtCodigo;

    @FXML
    private TextField txtDescripcion;

    @FXML
    private TextField txtNombre;

    @FXML
    private TextField txtValorUnitario;

    @FXML
    private AnchorPane ventana;

    private ObservableList<Ventas> ventasObservables;



    @FXML
    void atrasEvent(ActionEvent event) throws IOException {
        new ViewController(ventana, "/views/inicio.fxml");
    }

    @FXML
    void eliminarVenta(ActionEvent event) {

        Ventas ventaSeleccionada = this.tblVentas.getSelectionModel().getSelectedItem();

        if(ventaSeleccionada!=null){

            DataBase.getInstance().eliminarVentana(ventaSeleccionada);
            refreshTable();
        }else{

            Alert alerta = new Alert(Alert.AlertType.ERROR,"Seleccione una venta primero",ButtonType.OK);
            alerta.showAndWait();

        }

    }

    @FXML
    void registrarVenta(ActionEvent event) {

        Ventas ventaNueva = new Ventas(
                this.txtCodigo.getText(),this.txtNombre.getText(),this.txtDescripcion.getText(),Double.parseDouble(this.txtValorUnitario.getText()),
                Integer.parseInt(this.txtCantidadExistencia.getText())
        );

        DataBase.getInstance().crearVenta(ventaNueva);
        refreshTable();
    }


    @FXML
    void seleccionarVenta(MouseEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        refreshTable();

    }

    public void refreshTable (){
        ventasObservables = FXCollections.observableArrayList();

        this.colCodigo.setCellValueFactory(new PropertyValueFactory<>("codigo"));
        this.colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        this.colDescripcion.setCellValueFactory(new PropertyValueFactory<>("descripcion"));
        this.colValorUnitario.setCellValueFactory(new PropertyValueFactory<>("valorUnitario"));
        this.colCantidadExistencia.setCellValueFactory(new PropertyValueFactory<>("cantidadExistencia"));

        this.ventasObservables.addAll(DataBase.getInstance().getListaVentas());
        this.tblVentas.setItems(ventasObservables);
    }
}
