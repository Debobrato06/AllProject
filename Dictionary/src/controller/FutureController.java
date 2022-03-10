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
public class FutureController implements Initializable {

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
    private void indefinite(ActionEvent event) {
        DynaimicViews.loaderBorderCenter(borderpane, "FutureIndefinite");
    }

    @FXML
    private void continuous(ActionEvent event) {
        DynaimicViews.loaderBorderCenter(borderpane, "FutureContinuous");
    }

    @FXML
    private void perfect(ActionEvent event) {
        DynaimicViews.loaderBorderCenter(borderpane, "FuturePerfect");
    }

    @FXML
    private void perfectContinuous(ActionEvent event) {
        DynaimicViews.loaderBorderCenter(borderpane, "FuturePerfectContinuous");
    }
    
}
