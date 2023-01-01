package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AddPart implements Initializable {
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
    @FXML
    public RadioButton InHouseRadio;
    @FXML
    public RadioButton OutsourcedRadio;
    @FXML
    public Button AddPartSaveButton;




    public void OnActionInHouseRadio(ActionEvent event) {
    }

    public void OnActionOutsourcedRadio(ActionEvent event) {
    }

    public void OnActionSave(ActionEvent event) throws IOException {

    }

    public void OnActionCancel(ActionEvent event) {
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void inHouseRadioButton(ActionEvent event) {
    }

    public void addPartsOutsourcedRadioButton(ActionEvent event) {
    }

    public void addPartsSaveButton(ActionEvent event) {
    }

    public void addPartsCancelButton(ActionEvent event) {
    }
}
