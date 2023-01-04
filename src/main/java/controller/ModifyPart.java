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
import localhost.c482.Main;
import model.InHouse;
import model.Inventory;
import model.Outsourced;
import model.Part;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;


import static controller.MainScreenController.partToModifyIndex;

public class ModifyPart implements Initializable {
    @FXML
    public Label changingLabel;
    @FXML
    public TextField ModifyPartIDTextField;
    @FXML
    public TextField ModifyPartNameTextField;
    @FXML
    public TextField ModifyPartInventoryTextField;
    @FXML
    public TextField ModifyPartPriceTextField;
    @FXML
    public TextField ModifyPartMaxTextField;
    @FXML
    public TextField ModifyPartMinTextField;
    @FXML
    public TextField ModifyPartMachineIDTextField;
    @FXML
    public RadioButton ModifyInHouse;
    @FXML
    public RadioButton ModifyOutsourced;
    @FXML private Label ModifyPartIDLabel;


    private boolean isOutsourced;
    int partIndex = partToModifyIndex();
    private String exceptionMessage = new String();
    private int id;

    Stage stage;
    Parent scene;
    @FXML

    public void ModifyPartInHouseButton(ActionEvent event) {
        changingLabel.setText("Machine ID");
        ModifyOutsourced.setSelected(false);

    }

    @FXML
    public void ModifyPartOutsourcedRadioButton(ActionEvent event) {
        changingLabel.setText("Company Name");
        ModifyInHouse.setSelected(false);
    }
    @FXML
    public void ModifyPartSaveButton(ActionEvent event) throws IOException{
        String name = ModifyPartNameTextField.getText();
        String stock = ModifyPartInventoryTextField.getText();
        String price = ModifyPartPriceTextField.getText();
        String min = ModifyPartMinTextField.getText();
        String max = ModifyPartMaxTextField.getText();
        String machineID = ModifyPartMachineIDTextField.getText();

        try{
            exceptionMessage = Part.partValidator(name, Integer.parseInt(min), Integer.parseInt(max), Integer.parseInt(stock), Double.parseDouble(price), exceptionMessage);
            if(exceptionMessage.length() > 0){
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Error Adding Part");
                alert.setHeaderText("Error");
                alert.setContentText(exceptionMessage);
                alert.showAndWait();
            }else {
                if (isOutsourced == false) {
                    InHouse iHPart = new InHouse();

                    iHPart.setId((id));
                    iHPart.setName(name);
                    iHPart.setPrice(Double.parseDouble(price));
                    iHPart.setStock(Integer.parseInt(stock));
                    iHPart.setMin(Integer.parseInt(min));
                    iHPart.setMax(Integer.parseInt(max));
                    iHPart.setMachineId(Integer.parseInt(machineID));
                    Inventory.updatePart(partIndex, iHPart);
                }
                else {
                    Outsourced oPart = new Outsourced();
                    oPart.setId((id));
                    oPart.setName(name);
                    oPart.setStock(Integer.parseInt(stock));
                    oPart.setMin(Integer.parseInt(min));
                    oPart.setMax(Integer.parseInt(max));
                    oPart.setPrice(Double.parseDouble(price));
                    Inventory.updatePart(partIndex, oPart);
                }
                Parent modifyPart = FXMLLoader.load(Main.class.getResource("Main.fxml"));
                Scene scene = new Scene(modifyPart);
                Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
                window.setScene(scene);
                window.show();

            }
        }
        catch(NumberFormatException e){
            // Alert alert = new Alert(Alert.AlertType.WARNING);
            // alert.setTitle("Warning!");
            //alert.setContentText("Blank Fields. Please Complete all fields.");
            // alert.showAndWait();

        }

    }

    @FXML
    public void ModifyPartCancelButton(ActionEvent event) throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Exit to Main Screen?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = FXMLLoader.load(Main.class.getResource("Main.fxml"));
            stage.setScene(new Scene(scene));
            stage.show();
        }
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        Part part = Inventory.getAllParts().get(partIndex);
        id = Inventory.getAllParts().get(partIndex).getId();
        ModifyPartIDTextField.setText(String.valueOf(id));
        ModifyPartIDTextField.setEditable(false);
        ModifyPartNameTextField.setText(part.getName());
        ModifyPartPriceTextField.setText(Double.toString(part.getPrice()));
        ModifyPartMaxTextField.setText(Integer.toString(part.getMax()));
        ModifyPartMinTextField.setText(Integer.toString(part.getMin()));
        ModifyPartInventoryTextField.setText(Integer.toString(part.getStock()));

        if(part instanceof InHouse){
            ModifyPartMachineIDTextField.setText(Integer.toString(((InHouse)Inventory.getAllParts().get(partIndex)).getMachineId()));
            changingLabel.setText("Machine ID");
            ModifyInHouse.setSelected(true);
        }
        else{
            ModifyPartMachineIDTextField.setText(((Outsourced)Inventory.getAllParts().get(partIndex)).getCompanyName());
            changingLabel.setText("Company Name");
            ModifyOutsourced.setSelected(true);
        }
    }
}

