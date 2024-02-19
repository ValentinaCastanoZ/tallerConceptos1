package ventasR.Controller;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import ventasR.app.App;

import java.io.IOException;
import java.util.Objects;

public class ViewController {
    private Stage stage;
    private Scene scene;
    private Parent root;
    public ViewController(AnchorPane currentAnchorPane, String fxml) throws IOException {

        AnchorPane nextAnchorPane = FXMLLoader.load(Objects.requireNonNull(App.class.getResource(fxml)));
        currentAnchorPane.getChildren().removeAll();
        currentAnchorPane.getChildren().setAll(nextAnchorPane);
    }
}
