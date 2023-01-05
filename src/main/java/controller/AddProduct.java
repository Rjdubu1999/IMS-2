package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import localhost.c482.Main;
import model.Inventory;
import model.Part;
import model.Product;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class AddProduct implements Initializable {

    private static int id = 1000;
    public TextField AddProductAddPartSearchField;

    public static int getProductIDCount(){
        id++;
        return id;
    }

    @FXML
    public Label changingLabel;
    @FXML
    public TextField AddProductIDTextField;
    @FXML
    public TextField AddProductNameTextField;
    @FXML
    public TextField AddProductInventoryTextField;
    @FXML
    public TextField AddProductPriceTextField;
    @FXML
    public TextField AddProductMaxTextField;
    @FXML
    public TextField AddProductMinTextField;


    @FXML
    public TextField AddProductMachineIDTextField;
    @FXML
    public TableView<Part> AddProductAddPartTable;
    @FXML
    public TableColumn<Part, Integer> AddProductAddPartIDColumn;
    @FXML
    public TableColumn<Part, String> AddProductAddPartNameColumn;
    @FXML
    public TableColumn<Part, Integer> AddProductAddPartInventoryColumn;
    @FXML
    public TableColumn<Part, Double> AddProductAddPartPriceColumn;
    @FXML
    public TableView<Part> AddProductRemovePartTable;
    @FXML
    public TableColumn<Part, Integer> AddPartRemoveIDColumn;
    @FXML
    public TableColumn<Part, String> AddPartRemoveNameColumn;
    @FXML
    public TableColumn<Part, Integer> AddPartRemoveInventoryColumn;
    @FXML
    public TableColumn<Part, Double> AddPartRemovePriceColumn;
    @FXML
    public Button AddProductAddPartButton;


    Stage stage;
    Parent scene;

    private ObservableList<Part> associatedParts = FXCollections.observableArrayList();
    private ObservableList<Part> AddPartsList = FXCollections.observableArrayList();
    private String exceptionMessage = new String();
    private int productID;
    public void updatePartTable(){
        AddProductAddPartTable.setItems(Inventory.getAllParts());
    }
    public void updateLowerPartTable(){
        AddProductRemovePartTable.setItems(Inventory.getAllParts());
    }
    public AddProduct() {

    }




    public void OnActionSave(ActionEvent event) throws IOException {

        String name = AddProductNameTextField.getText();
        String stock = AddProductInventoryTextField.getText();
        String price = AddProductPriceTextField.getText();
        String min = AddProductMinTextField.getText();
        String max = AddProductMaxTextField.getText();



        try{
            exceptionMessage = Product.productValidator(name, Integer.parseInt(min),Integer.parseInt(max),Integer.parseInt(stock),Double.parseDouble(price),AddPartsList, exceptionMessage);
            if(exceptionMessage.length() > 0){
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Error");
                alert.setContentText("Error adding product");
                alert.showAndWait();
            }else {
                Product newProduct = new Product();

                newProduct.setId(id);
                newProduct.setName(name);
                newProduct.setMax(Integer.parseInt(max));
                newProduct.setMin(Integer.parseInt(min));
                newProduct.setPrice(Double.parseDouble(price));
                newProduct.setStock(Integer.parseInt(stock));
                newProduct.setAssociatedParts(AddPartsList);
                Inventory.addProduct(newProduct);

                Parent saveProduct = FXMLLoader.load(Main.class.getResource("Main.fxml"));
                Scene scene = new Scene(saveProduct);
                Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
                window.setScene(scene);
                window.show();
            }

        }
        catch (NumberFormatException e){
            //maybe fill with another error
        }




    }

    public void OnActionRemoveAssociatedButton(ActionEvent event)  {
        Part removePart = AddProductRemovePartTable.getSelectionModel().getSelectedItem();
        if(removePart == null){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Error");
            alert.setContentText("No part selected to delete");

        } else if (removePart != null) {
            Alert newalert = new Alert(Alert.AlertType.CONFIRMATION);
            newalert.setTitle("Warning");
            newalert.setContentText("Do you want to remove selected part?");
            Optional<ButtonType> remove = newalert.showAndWait();
            if(remove.isPresent() && remove.get() == ButtonType.OK){
                AddPartsList.remove(removePart);
                AddProductRemovePartTable.setItems(AddPartsList);
            }


        }


    }

        public void OnActionCancel(ActionEvent event) throws IOException {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Exit to Main Screen?");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                scene = FXMLLoader.load(Main.class.getResource("Main.fxml"));
                stage.setScene(new Scene(scene));
                stage.show();

            }


    }
    public void OnActionAddPart(ActionEvent event) {
        Part part = AddProductAddPartTable.getSelectionModel().getSelectedItem();
        if(part == null){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Error");
            alert.setContentText("Select Part to add");
        }else {
            AddPartsList.add(part);
            AddProductRemovePartTable.setItems(AddPartsList);
        }

    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //setting values in cells on top parts table in add product view
        AddProductAddPartTable.setItems(Inventory.getAllParts());
    AddProductAddPartIDColumn.setCellValueFactory(cellData -> cellData.getValue().partIDProperty().asObject());
    AddProductAddPartNameColumn.setCellValueFactory(cellData -> cellData.getValue().partNameProperty());
    AddProductAddPartPriceColumn.setCellValueFactory(cellData -> cellData.getValue().partPriceProperty().asObject());
    AddProductAddPartInventoryColumn.setCellValueFactory(cellData -> cellData.getValue().partStockProperty().asObject());
        //updatePartTable();



    AddPartRemoveIDColumn.setCellValueFactory(cellData -> cellData.getValue().partIDProperty().asObject());
    AddPartRemoveNameColumn.setCellValueFactory(cellData -> cellData.getValue().partNameProperty());
    AddPartRemoveInventoryColumn.setCellValueFactory(cellData -> cellData.getValue().partStockProperty().asObject());
    AddPartRemovePriceColumn.setCellValueFactory(cellData -> cellData.getValue().partPriceProperty().asObject());
        //updateLowerPartTable();

        id = getProductIDCount();
        AddProductIDTextField.setText(String.valueOf(id));
        AddProductIDTextField.setEditable(false);


    }


    public void onActionSearchParts(ActionEvent event) {
        String searchPart = AddProductAddPartSearchField.getText();
        try{
            ObservableList<Part> tempPart = Inventory.lookupPart(searchPart);
            if(tempPart.size() == 0){
                int searchPartID = Integer.parseInt(searchPart);
                Part findPart = Inventory.lookupPart(searchPartID);
                tempPart.add(findPart);
                if(findPart == null){
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Error");
                    alert.setContentText("No part found");
                    alert.showAndWait();
                }
                }
            AddProductAddPartTable.setItems(tempPart);
            }catch(NumberFormatException e){

        }
    }

    public void onKeyTypedSearch(KeyEvent keyEvent) {
        if ( AddProductAddPartSearchField.getText().isEmpty()){
            AddProductAddPartTable.setItems(Inventory.getAllParts());
        }
    }
}
