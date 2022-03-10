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
import javafx.scene.layout.BorderPane;

/**
 * FXML Controller class
 *
 * @author Debobrato Biswas
 */
public class PrepositionController implements Initializable {

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
    private void simple(ActionEvent event) {
        DynaimicViews.loaderBorderCenter(borderpane, "Simple");
    }

    @FXML
    private void doub(ActionEvent event) {
        DynaimicViews.loaderBorderCenter(borderpane, "Double");
    }

    @FXML
    private void compound(ActionEvent event) {
        DynaimicViews.loaderBorderCenter(borderpane, "Compound");
    }

    @FXML
    private void phrase(ActionEvent event) {
        DynaimicViews.loaderBorderCenter(borderpane, "Phrase");
    }

    @FXML
    private void praticiple(ActionEvent event) {
        DynaimicViews.loaderBorderCenter(borderpane, "Participle");
    }

    @FXML
    private void disguised(ActionEvent event) {
        DynaimicViews.loaderBorderCenter(borderpane, "Disguised");
    }

    @FXML
    private void appropriate(ActionEvent event) {
        DynaimicViews.loaderBorderCenter(borderpane, "Appropriate");
    }

    @FXML
    private void input(ActionEvent event) {
        DynaimicViews.loaderBorderCenter(borderpane, "InputPreposition");
    }
    
}
