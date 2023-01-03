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

        try{
            int id = Integer.parseInt(ModifyPartIDLabel.getText());
            String name = ModifyPartNameTextField.getText();
            int stock = Integer.parseInt(ModifyPartInventoryTextField.getText());
            double price = Double.parseDouble(ModifyPartInventoryTextField.getText());
            int min = Integer.parseInt(ModifyPartMinTextField.getText());
            int max = Integer.parseInt(ModifyPartMaxTextField.getText());

            if( min > max){
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Warning!");
                alert.setContentText("Minimum can not be greater than maximum, must be less than or equal to.");
                alert.showAndWait();
            } else if ( stock > max || stock < min) {
                Alert alert = new Alert (Alert.AlertType.WARNING);
                alert.setTitle("Warning!");
                alert.setContentText("Stock can not be greater than maximum or less than minimum.");
                alert.showAndWait();

            } else if (name.isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Warning!");
                alert.setContentText("Name field is blank.");
                alert.showAndWait();
            }
            else {
                if(ModifyInHouse.isSelected()){
                    int machineID = Integer.parseInt(ModifyPartMachineIDTextField.getText());
                    Inventory.addPart(new InHouse());
                } else if (ModifyOutsourced.isSelected()) {
                    String companyName = ModifyPartMachineIDTextField.getText();
                    Inventory.addPart(new Outsourced());
                }
                stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                scene = FXMLLoader.load(Main.class.getResource("Main.fxml"));
                stage.setScene(new Scene(scene));
                stage.show();
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

    public void partToBeUpdated(Part part){
        ModifyPartIDLabel.setText(String.valueOf(part.getId()));
        ModifyPartNameTextField.setText(String.valueOf(part.getName()));
        ModifyPartInventoryTextField.setText(String.valueOf(part.getName()));
        ModifyPartPriceTextField.setText(String.valueOf(part.getPrice()));
        ModifyPartMinTextField.setText(String.valueOf(part.getMin()));
        ModifyPartMaxTextField.setText(String.valueOf(part.getMax()));

        if(part instanceof InHouse){
            ModifyInHouse.setSelected(true);
            changingLabel.setText("Machine ID");
            ModifyPartMachineIDTextField.setText(String.valueOf(((InHouse) part).getMachineId()));
        }else {
            ModifyOutsourced.setSelected(true);
            changingLabel.setText("Company Name");
            ModifyPartMachineIDTextField.setText(String.valueOf(((Outsourced)part).getCompanyName()));
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}

