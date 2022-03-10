/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import helper.DynaimicViews;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 *
 * @author User
 */
public class MainController implements Initializable {

    @FXML
    private Button minimize;
    @FXML
    private Button exit;
    @FXML
    private BorderPane borderpane;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        DynaimicViews.loaderBorderCenter(borderpane, "Dashboard");

    }

    @FXML
    private void minimize(ActionEvent event) {
        Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        stage.setIconified(true);
    }

    @FXML
    private void exit(ActionEvent event) {
        Stage stage = (Stage) exit.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void Dashbord(ActionEvent event) {
        DynaimicViews.loaderBorderCenter(borderpane, "Dashboard");
    }

    @FXML
    private void medicineGroup(ActionEvent event) {
        DynaimicViews.loaderBorderCenter(borderpane, "MedicineGroup");
    }

    @FXML
    private void drugDetails(ActionEvent event) {
        DynaimicViews.loaderBorderCenter(borderpane, "DrugDetails");
    }

    @FXML
    private void medicineList(ActionEvent event) {
        DynaimicViews.loaderBorderCenter(borderpane, "MedicineList");
    }

    @FXML
    private void orderGenerate(ActionEvent event) {
        DynaimicViews.loaderBorderCenter(borderpane, "OrderGenerate");
    }

    @FXML
    private void sales(ActionEvent event) {
        DynaimicViews.loaderBorderCenter(borderpane, "Sales");
    }

    @FXML
    private void rack(ActionEvent event) {
        DynaimicViews.loaderBorderCenter(borderpane, "Rack");
    }

    @FXML
    private void rackJoin(ActionEvent event) {
        DynaimicViews.loaderBorderCenter(borderpane, "RackJoinMedicineGroup");
    }

    @FXML
    private void medicineType(ActionEvent event) {
        DynaimicViews.loaderBorderCenter(borderpane, "MedicineType");
    }

    @FXML
    private void moneyReceipt(ActionEvent event) {
        DynaimicViews.loaderBorderCenter(borderpane, "moneyReceiptList");
    }

}
