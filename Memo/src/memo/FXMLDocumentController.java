/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package memo;

import helper.DynaimicViews;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;

/**
 *
 * @author Debobrato Biswas
 */
public class FXMLDocumentController implements Initializable {

    private Label label;
    @FXML
    private BorderPane borderpane;

    private void handleButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");
        label.setText("Hello World!");
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void memo(ActionEvent event) {
        DynaimicViews.loaderBorderCenter(borderpane, "memo");
    }

    @FXML
    private void productList(ActionEvent event) {
         DynaimicViews.loaderBorderCenter(borderpane, "ProductList");
    }

}
