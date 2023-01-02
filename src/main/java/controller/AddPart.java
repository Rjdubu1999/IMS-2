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

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;


//controller for AddPart class which uses the AddPart.fxml view
public class AddPart implements Initializable {

    private static int id = 100;
    public Label AddPartIDLabel;
    public RadioButton AddPartInHouseRadio;
    public RadioButton AddPartOutsourcedRadio;

    Stage stage;
    Parent scene;
    @FXML
    public Label changingLabel;
    @FXML
    public TextField AddPartIDTextField;
    @FXML
    public TextField AddPartNameTextField;
    @FXML
    public TextField AddPartInventoryTextField;
    @FXML
    public TextField AddPartPriceTextField;
    @FXML
    public TextField AddPartMaxTextField;
    @FXML
    public TextField AddPartMinTextField;
    @FXML
    public TextField AddPartMachineIDTextField;



    public static int getPartIDCount(){
        id++;
        return id;
    }


    // This method make the cancel button when clicked bring the user back to
    // the main screen and erases the data they've input.
    public void addPartCancelButton(ActionEvent event)throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Exit to Main Screen?");
        Optional<ButtonType> result = alert.showAndWait();
        if(result.isPresent() && result.get() == ButtonType.OK){
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = FXMLLoader.load(Main.class.getResource("Main.fxml"));
            stage.setScene(new Scene(scene));
            stage.show();
        }
    }


    //  This method changes the text of the MachineID/Company Name label to
//  Machine ID if user clicks the in house radio button.
    public void inHouseRadioButton(ActionEvent event) {
        changingLabel.setText("Machine ID");
        AddPartOutsourcedRadio.setSelected(false);
    }


    //  This method changes the text of the MachineID/Company Name label to
//  Company Name if user clicks the in outsourced radio button.
    public void addPartsOutsourcedRadioButton(ActionEvent event) {
        changingLabel.setText("Company Name");
        AddPartInHouseRadio.setSelected(false);
    }


    public void addPartsSaveButton(ActionEvent event) throws IOException{
        try{
            int id = Integer.parseInt(AddPartIDTextField.getText());
            String name = AddPartNameTextField.getText();
            int stock = Integer.parseInt(AddPartInventoryTextField.getText());
            double price = Double.parseDouble(AddPartInventoryTextField.getText());
            int min = Integer.parseInt(AddPartMinTextField.getText());
            int max = Integer.parseInt(AddPartMaxTextField.getText());

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
                if(AddPartInHouseRadio.isSelected()){
                    int machineID = Integer.parseInt(AddPartMachineIDTextField.getText());
                    Inventory.addPart(new InHouse(id, name,price, stock,min,max,machineID));
                } else if (AddPartOutsourcedRadio.isSelected()) {
                    String companyName = AddPartMachineIDTextField.getText();
                    Inventory.addPart(new Outsourced(id, name, price, stock, min ,max, companyName));
                    }
                stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                scene = FXMLLoader.load(Main.class.getResource("Main.fxml"));
                stage.setScene(new Scene(scene));
                stage.show();
            }
            }
        catch(NumberFormatException e){
            //Alert alert = new Alert(Alert.AlertType.WARNING);
           // alert.setTitle("Warning!");
           // alert.setContentText("Blank Fields. Please Complete all fields.");
           // alert.showAndWait();

            }

        }



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
   AddPartInHouseRadio.setSelected(true);
   id = getPartIDCount();
   AddPartIDTextField.setText(String.valueOf(id));
   AddPartIDTextField.setEditable(false);


    }









}
