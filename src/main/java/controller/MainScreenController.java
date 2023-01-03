package controller;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.stage.Stage;
import localhost.c482.Main;
import model.Inventory;
import model.Part;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.Optional;
import java.util.ResourceBundle;

import static javafx.fxml.FXMLLoader.load;




public class MainScreenController implements Initializable {
    @FXML
    private Button MainScreenExit;
    @FXML
    public TableView<Part> MainPartsTable;
    @FXML
    public TableColumn<Part, Integer> MainPartsIDColumn;
    @FXML
    public TableColumn<Part, String> MainPartsNameColumn;
    @FXML
    public TableColumn<Part, Integer> MainPartsInventoryColumn;
    @FXML
    public TableColumn<Part, Double> MainPartsPriceColumn;
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
    Stage stage;
    Parent scene;
    public MainScreenController(){

    }
    @FXML
    private Label welcomeText;

    @FXML
    public void ModifyPartScreen(ActionEvent event) throws IOException {
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(Main.class.getResource("ModifyPart.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    @FXML
    public void OnActionDeletePart(ActionEvent event) {
    }
    //runtime error here for changing FXMLLoader
    public void OnClickAddPart(ActionEvent event) throws IOException {
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(Main.class.getResource("AddPart.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }
    public void onActionExitApplication(ActionEvent event) throws IOException{
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Do you want to exit Inventory Management System?");
        Optional<ButtonType> result = alert.showAndWait();
        if(result.isPresent() && result.get() == ButtonType.OK);
        System.exit(0);
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


    MainPartsIDColumn.setCellValueFactory(cellData -> cellData.getValue().partIDProperty().asObject());
    MainPartsNameColumn.setCellValueFactory(cellData -> cellData.getValue().partNameProperty());
    MainPartsInventoryColumn.setCellValueFactory(cellData -> cellData.getValue().partStockProperty().asObject());
    MainPartsPriceColumn.setCellValueFactory(cellData -> cellData.getValue().partPriceProperty().asObject());
    updatePartTableViw();
    }

    public void updatePartTableViw(){
        MainPartsTable.setItems(Inventory.getAllParts());
    }


}