/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import database.initiatlDB;
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
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 *
 * @author User
 */
public class MasterController implements Initializable {

    @FXML
    private BorderPane borderpane;
    @FXML
    private Button close;
    @FXML
    private Button minimiz;
    @FXML
    private Pane homePane;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        homePane.setStyle("-fx-background-image:url('/assets/image/oeQzEF.png');"
                + "-fx-background-position: center, center;\n"
                + "-fx-background-repeat: no-repeat;\n"
                + "-fx-background-size: cover, auto;");
        
        
        initiatlDB initialDatabasesInput = new initiatlDB();
        try {
            initialDatabasesInput.creatDatabse();
            initialDatabasesInput.translateTable();
            initialDatabasesInput.translateEtoBTable();
            initialDatabasesInput.appropriatePrepositionTable();

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MasterController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void home(ActionEvent event) {
        DynaimicViews.loaderBorderCenter(borderpane, "home");
    }

    @FXML
    private void dictionary(ActionEvent event) throws IOException {
        Parent Prescription = FXMLLoader.load(getClass().getResource("/view/dictionary.fxml"));
        borderpane.setCenter(Prescription);
        //DynaimicViews.loaderBorderCenter(borderpane, "Dictionary");
    }

    @FXML
    private void about(ActionEvent event) {
        DynaimicViews.loaderBorderCenter(borderpane, "about");
    }

    @FXML
    private void close(ActionEvent event) {
        Stage stage = (Stage) close.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void minimiz(ActionEvent event) {
        Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        stage.setIconified(true);
    }

    private void dataInput(ActionEvent event) {
        DynaimicViews.loaderBorderCenter(borderpane, "InputData");
    }

    private void E2B(ActionEvent event) {
        DynaimicViews.loaderBorderCenter(borderpane, "dictionary");
    }

    private void B2E(ActionEvent event) {
        DynaimicViews.loaderBorderCenter(borderpane, "BanglaToEnglish");
    }

    @FXML
    private void tenses(ActionEvent event) {
        DynaimicViews.loaderBorderCenter(borderpane, "Tenses");
    }

    @FXML
    private void preposition(ActionEvent event) {
        DynaimicViews.loaderBorderCenter(borderpane, "Preposition");
    }

    @FXML
    private void articles(ActionEvent event) {
        DynaimicViews.loaderBorderCenter(borderpane, "Article");
    }

}
