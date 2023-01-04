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
import model.Product;

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
    public TableView<Product> MainProductTable;
    @FXML
    public TableColumn<Product, Integer> MainProductIDColumn;
    @FXML
    public TableColumn<Product, String> MainProductNameColumn;
    @FXML
    public TableColumn<Product, Integer> MainProductInventoryColumn;
    @FXML
    public TableColumn<Product, Double> MainProductPriceColumn;
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
    public static int partToModifyIndex(){
        return modifyPartIndex ;
    }
    private static Part modifySelected;
    private static int modifyPartIndex;

    @FXML
    public void ModifyPartScreen(ActionEvent event) throws IOException {
        modifySelected = MainPartsTable.getSelectionModel().getSelectedItem();
        modifyPartIndex = Inventory.getAllParts().indexOf(modifySelected);
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
    public void onClickAddProduct(ActionEvent event)  throws  IOException{
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(Main.class.getResource("AddProduct.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();

    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


    MainPartsIDColumn.setCellValueFactory(cellData -> cellData.getValue().partIDProperty().asObject());
    MainPartsNameColumn.setCellValueFactory(cellData -> cellData.getValue().partNameProperty());
    MainPartsInventoryColumn.setCellValueFactory(cellData -> cellData.getValue().partStockProperty().asObject());
    MainPartsPriceColumn.setCellValueFactory(cellData -> cellData.getValue().partPriceProperty().asObject());
    updatePartTableView();

    MainProductIDColumn.setCellValueFactory(cellData -> cellData.getValue().productIDProperty().asObject());
    MainProductNameColumn.setCellValueFactory(cellData -> cellData.getValue().productNameProperty());
    MainProductInventoryColumn.setCellValueFactory(cellData -> cellData.getValue().productInventoryProperty().asObject());
    MainProductPriceColumn.setCellValueFactory(cellData -> cellData.getValue().productPriceProperty().asObject());
    updateProductTableView();
    }

    public void updatePartTableView(){
        MainPartsTable.setItems(Inventory.getAllParts());
    }
    public void updateProductTableView(){
        MainProductTable.setItems(Inventory.getAllProducts());
    }

}