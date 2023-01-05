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
import javafx.stage.Stage;
import localhost.c482.Main;
import model.Inventory;
import model.Part;
import model.Product;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import static controller.MainScreenController.productModifyIndex;
import static model.Inventory.getAllParts;

public class ModifyProduct implements Initializable {

    @FXML
    public TextField ModifyProductSearchField;
    Stage stage;
    Parent scene;
    private String exceptionMessage = new String();
    private int id;
    private int productIndex = productModifyIndex();

    public void updatePartTable() {
        UpperPartTable.setItems(Inventory.getAllParts());
    }

    public void updateLowerPartTable() {
        LowerPartTable.setItems(Inventory.getAllParts());
    }

    private ObservableList<Part> associatedParts = FXCollections.observableArrayList();
    @FXML
    public TextField ModifyProductIDTextField;
    @FXML
    public TextField ModifyProductNameTextField;
    @FXML
    public TextField ModifyProductInventoryTextField;
    @FXML
    public TextField ModifyProductPriceTextField;
    @FXML
    public TextField ModifyProductMaxTextField;
    @FXML
    public TextField ModifyProductMinTextField;
    @FXML
    public TextField ModifyProductMachineIDTextField;
    @FXML
    public Button ModifyProductSave;
    @FXML
    public TableView<Part> UpperPartTable;
    @FXML
    public TableColumn<Part, Integer> UpperPartIDCol;
    @FXML
    public TableColumn<Part, String> UpperPartNameCol;
    @FXML
    public TableColumn<Part, Integer> UpperPartInventoryCol;
    @FXML
    public TableColumn<Part, Double> UpperPartPriceCol;
    @FXML
    public TableView<Part> LowerPartTable;
    @FXML
    public TableColumn<Part, Integer> LowerPartIDCol;
    @FXML
    public TableColumn<Part, String> LowerPartNameCol;
    @FXML
    public TableColumn<Part, Integer> LowerPartInventoryCol;
    @FXML
    public TableColumn<Part, Double> LowerPartPriceCol;


    public ModifyProduct() {

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
        Part part = UpperPartTable.getSelectionModel().getSelectedItem();
        if(part == null){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Error");
            alert.setContentText("Select Part to add");
        }else {
            associatedParts.add(part);
            LowerPartTable.setItems(associatedParts);
        }

    }


    public void OnActionRemoveAssociated(ActionEvent event) {
        Part removePart = LowerPartTable.getSelectionModel().getSelectedItem();
        if (removePart == null) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Error");
            alert.setContentText("No part selected to delete");

        } else if (removePart != null) {
            Alert newalert = new Alert(Alert.AlertType.CONFIRMATION);
            newalert.setTitle("Warning");
            newalert.setContentText("Do you want to remove selected part?");
            Optional<ButtonType> remove = newalert.showAndWait();
            if (remove.isPresent() && remove.get() == ButtonType.OK) {
                associatedParts.remove(removePart);
                LowerPartTable.setItems(associatedParts);
            }


        }


    }


    public void OnActionSave(ActionEvent event) throws IOException {
        String name = ModifyProductNameTextField.getText();
        String stock = ModifyProductInventoryTextField.getText();
        String price = ModifyProductPriceTextField.getText();
        String min = ModifyProductMinTextField.getText();
        String max = ModifyProductMaxTextField.getText();

        try {
            exceptionMessage = Product.productValidator(name, Integer.parseInt(min), Integer.parseInt(max), Integer.parseInt(stock), Double.parseDouble(price), associatedParts, exceptionMessage);
            if (exceptionMessage.length() > 0) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Error");
                alert.setContentText("Error Modifying product");
                alert.showAndWait();

            } else {

                Product modProduct = new Product();

                modProduct.setId(id);
                modProduct.setName(name);
                modProduct.setMax(Integer.parseInt(max));
                modProduct.setMin(Integer.parseInt(min));
                modProduct.setPrice(Double.parseDouble(price));
                modProduct.setStock(Integer.parseInt(stock));
                modProduct.setAssociatedParts(associatedParts);
                Inventory.updateProduct(productIndex, modProduct);

                Parent saveProduct = FXMLLoader.load(Main.class.getResource("Main.fxml"));
                Scene scene = new Scene(saveProduct);
                Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
                window.setScene(scene);
                window.show();
            }

        } catch (NumberFormatException e) {
            //maybe fill with another error
        }


    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Product product = Inventory.getAllProducts().get(productIndex);
        id = Inventory.getAllProducts().get(productIndex).getId();
        ModifyProductIDTextField.setText(String.valueOf(id));
        ModifyProductIDTextField.setEditable(false);
        ModifyProductNameTextField.setText(product.getName());
        ModifyProductInventoryTextField.setText(Integer.toString(product.getStock()));
        ModifyProductMaxTextField.setText(Integer.toString(product.getMax()));
        ModifyProductMinTextField.setText(Integer.toString(product.getMin()));
        ModifyProductPriceTextField.setText(Double.toString(product.getPrice()));


        UpperPartIDCol.setCellValueFactory(cellData -> cellData.getValue().partIDProperty().asObject());
        UpperPartNameCol.setCellValueFactory(cellData -> cellData.getValue().partNameProperty());
        UpperPartInventoryCol.setCellValueFactory(cellData -> cellData.getValue().partStockProperty().asObject());
        UpperPartPriceCol.setCellValueFactory(cellData -> cellData.getValue().partPriceProperty().asObject());
        associatedParts = product.getAssociatedParts();
        updatePartTable();

        LowerPartIDCol.setCellValueFactory(cellData -> cellData.getValue().partIDProperty().asObject());
        LowerPartNameCol.setCellValueFactory(cellData -> cellData.getValue().partNameProperty());
        LowerPartInventoryCol.setCellValueFactory(cellData -> cellData.getValue().partStockProperty().asObject());
        LowerPartPriceCol.setCellValueFactory(cellData -> cellData.getValue().partPriceProperty().asObject());
        updateLowerPartTable();


    }

    public void emptyAssociatedTable(SortEvent<TableView<Part>> tableViewSortEvent) {
    }

    public void OnActionSearch(ActionEvent event) {
        String searchPart = ModifyProductSearchField.getText();
        try {
            ObservableList<Part> tempPart = Inventory.lookupPart(searchPart);
            if (tempPart.size() == 0) {
                int searchPartID = Integer.parseInt(searchPart);
                Part findPart = Inventory.lookupPart(searchPartID);
                tempPart.add(findPart);
                if (findPart == null) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Error");
                    alert.setContentText("No part found");
                    alert.showAndWait();
                }
            }
            UpperPartTable.setItems(tempPart);
        } catch (NumberFormatException e) {

        }
    }

    public void OnKeyTypedSearch(KeyEvent keyEvent) {
        if (ModifyProductSearchField.getText().isEmpty()) {
            UpperPartTable.setItems(Inventory.getAllParts());
        }
    }
}
