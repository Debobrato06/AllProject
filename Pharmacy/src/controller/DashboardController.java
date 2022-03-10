/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import helper.DynaimicViews;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;

/**
 * FXML Controller class
 *
 * @author User
 */
public class DashboardController implements Initializable {

    @FXML
    private Button medicineList;
    @FXML
    private Button recentExpair;
    @FXML
    private BorderPane borderpane;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void medicineListAction(ActionEvent event) {
        DynaimicViews.loaderBorderCenter(borderpane, "MedicineList");
    }

    @FXML
    private void recentExpair(ActionEvent event) {
        DynaimicViews.loaderBorderCenter(borderpane, "recentExpair");
    }

    @FXML
    private void todaySale(ActionEvent event) {
        DynaimicViews.loaderBorderCenter(borderpane, "todaySaleList");
    }

    @FXML
    private void availableMedicine(ActionEvent event) {
        DynaimicViews.loaderBorderCenter(borderpane, "MedicineList");
    }
    
}
