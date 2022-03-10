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
public class TensesController implements Initializable {

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
    private void present(ActionEvent event) {
        DynaimicViews.loaderBorderCenter(borderpane, "Present");
    }

    @FXML
    private void past(ActionEvent event) {
        DynaimicViews.loaderBorderCenter(borderpane, "Past");
    }

    @FXML
    private void future(ActionEvent event) {
        DynaimicViews.loaderBorderCenter(borderpane, "Future");
    }
    
}
