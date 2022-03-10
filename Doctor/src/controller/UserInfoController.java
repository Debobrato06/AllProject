/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;

/**
 * FXML Controller class
 *
 * @author devbi
 */
public class UserInfoController implements Initializable {

    @FXML
    private ChoiceBox choiceBox;
    @FXML
    private ChoiceBox choiceBox1;
    @FXML
    private ChoiceBox choiceBox2;

    public void choiceBoxButtonPushed() {

        choiceBox.getValue().toString();
        choiceBox1.getValue().toString();
        choiceBox2.getValue().toString();
    }

    public void comboBoxWasUpdate() {
        //comboBox.getValue().toString();
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

        choiceBox.getItems().addAll("ASD", "ADS", "DSA");
        choiceBox1.getItems().addAll("ASD", "ADS", "DSA");
        choiceBox2.getItems().addAll("ASD", "ADS", "DSA");

    }

}
