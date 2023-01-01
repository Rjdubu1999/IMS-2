package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static javafx.fxml.FXMLLoader.load;

public class MainScreenController implements Initializable {
    @FXML
    private Button MainScreenExit;
    @FXML
    public TableView MainPartsTable;
    @FXML
    public TableColumn MainPartsIDColumn;
    @FXML
    public TableColumn MainPartsNameColumn;
    @FXML
    public TableColumn MainPartsInventoryColumn;
    @FXML
    public TableColumn MainPartsPriceColumn;
    @FXML
    public TextField MainPartsTextField;
    @FXML
    public TableView MainProductTable;
    @FXML
    public TableColumn MainProductIDColumn;
    @FXML
    public TableColumn MainProductNameColumn;
    @FXML
    public TableColumn MainProductInventoryColumn;
    @FXML
    public TableColumn MainProductPriceColumn;
    @FXML
    public TextField MainProductTextField;
    @FXML
    public Button AddPartScreen;
    @FXML
    public Button MainScreenAddPartButton;

    @FXML
    private Label welcomeText;





    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
    @FXML
    public void ModifyPartScreen(ActionEvent event) {
    }


    @FXML
    public void OnActionDeletePart(ActionEvent event) {
    }

    public void OnClickAddPart(ActionEvent event) throws IOException {
        Parent addParts = FXMLLoader.load(getClass().getClassLoader().getResource("localhost.c482/AddPart.fxml"));
        Scene scene = new Scene(addParts);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }
}