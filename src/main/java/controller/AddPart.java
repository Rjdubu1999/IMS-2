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
import java.text.DecimalFormat;
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
    private String exceptionMessage = new String();
    private boolean isOutsourced;

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
            String name = AddPartNameTextField.getText();
            String stock = AddPartInventoryTextField.getText();
            String price = AddPartPriceTextField.getText();
            String min = AddPartMinTextField.getText();
            String max = AddPartMaxTextField.getText();
            String machineID = AddPartMachineIDTextField.getText();

            try{
                exceptionMessage = Part.partValidator(name, Integer.parseInt(min), Integer.parseInt(max), Integer.parseInt(stock), Double.parseDouble(price), exceptionMessage);
                if(exceptionMessage.length() > 0){
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Error Adding Part");
                    alert.setHeaderText("Error");
                    alert.setContentText(exceptionMessage);
                    alert.showAndWait();
                    exceptionMessage = "";
                }else {
                    if (isOutsourced == false) {
                        InHouse inhousePart = new InHouse();

                        inhousePart.setId(getPartIDCount());
                        inhousePart.setName(name);
                        inhousePart.setPrice(Double.parseDouble(price));
                        inhousePart.setStock(Integer.parseInt(stock));
                        inhousePart.setMin(Integer.parseInt(min));
                        inhousePart.setMax(Integer.parseInt(max));
                        inhousePart.setMachineId(Integer.parseInt(machineID));
                        Inventory.addPart(inhousePart);
                    }
                    else {
                        Outsourced outsourcedPart = new Outsourced();
                        outsourcedPart.setId(getPartIDCount());
                        outsourcedPart.setName(name);
                        outsourcedPart.setStock(Integer.parseInt(stock));
                        outsourcedPart.setMin(Integer.parseInt(min));
                        outsourcedPart.setMax(Integer.parseInt(max));
                        outsourcedPart.setPrice(Double.parseDouble(price));
                        Inventory.addPart(outsourcedPart);
                    }
                    Parent savePart = FXMLLoader.load(Main.class.getResource("Main.fxml"));
                    Scene scene = new Scene(savePart);
                    Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
                    window.setScene(scene);
                    window.show();

                }

            }
        catch(NumberFormatException e){
           // Alert alert = new Alert(Alert.AlertType.INFORMATION);
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
