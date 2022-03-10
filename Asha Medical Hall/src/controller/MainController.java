/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import helper.DynaimicViews;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 *
 * @author Debobrato Biswas
 */
public class MainController implements Initializable {

    @FXML
    private Button exit;
    @FXML
    private Button minimize;
    @FXML
    private BorderPane borderpane;
    @FXML
    private AnchorPane anchorePnae;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
//           homePane.setStyle("-fx-background-image:url('/assets/image/oeQzEF.png');"
//                + "-fx-background-position: center, center;\n"
//                + "-fx-background-repeat: no-repeat;\n"
//                + "-fx-background-size: cover, auto;");
//        
//        
//        initiatlDB initialDatabasesInput = new initiatlDB();
//        try {
//            initialDatabasesInput.creatDatabse();
//            initialDatabasesInput.translateTable();
//            initialDatabasesInput.translateEtoBTable();
//            initialDatabasesInput.appropriatePrepositionTable();
//
//        } catch (ClassNotFoundException ex) {
//            Logger.getLogger(MasterController.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        
       // DynaimicViews.loaderBorderCenter(borderpane, "Article");
    }

    @FXML
    private void exit(ActionEvent event) {
        Stage stage = (Stage) exit.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void minimize(ActionEvent event) {
        Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        stage.setIconified(true);
    }

    @FXML
    private void dashboard(ActionEvent event) {
         DynaimicViews.loaderBorderCenter(borderpane, "DesaboardView");
    }

    @FXML
    private void newSales(ActionEvent event) {
        DynaimicViews.loaderBorderCenter(borderpane, "newSales");
    }

    @FXML
    private void medicines(ActionEvent event) {
        DynaimicViews.loaderBorderCenter(borderpane, "medicines");
    }

    @FXML
    private void companies(ActionEvent event) {
        DynaimicViews.loaderBorderCenter(borderpane, "companies");
    }

    @FXML
    private void viewSaleRecords(ActionEvent event) {
        DynaimicViews.loaderBorderCenter(borderpane, "viewSaleRecords");
    }

    @FXML
    private void isOnline(ActionEvent event) {
        DynaimicViews.loaderBorderCenter(borderpane, "synchronize");
    }

}
