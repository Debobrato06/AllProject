/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Debobrato Biswas
 */
public class AddProductController implements Initializable {

    private ProductListController mController;
    @FXML
    private Button save;
    @FXML
    private Button reset;
    @FXML
    private Button close;
    @FXML
    private TextField productName;
    @FXML
    private TextField brandName;
    @FXML
    private TextField productQuantity;
    @FXML
    private TextArea productDetails;

    /**
     * Initializes the controller class.
     */
    public void setParams(ProductListController controller) {
        this.mController = controller;

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void save(ActionEvent event) {
    }

    @FXML
    private void reset(ActionEvent event) {
        productName.clear();
        brandName.clear();
        productQuantity.clear();
        productDetails.clear();
    }

    @FXML
    private void close(ActionEvent event) {
        Stage stage = (Stage) close.getScene().getWindow();
        stage.close();
    }

}
