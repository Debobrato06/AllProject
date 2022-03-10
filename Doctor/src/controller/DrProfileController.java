/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;

/**
 * FXML Controller class
 *
 * @author devbi
 */
public class DrProfileController implements Initializable {

    List<String> lstFile;

    @FXML
    private BorderPane borderpane;
    @FXML
    private Label fileChosenField;
    @FXML
    private Button choosFile;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        lstFile = new ArrayList<>();
        lstFile.add("*.jpg");
        lstFile.add("*.png");
        lstFile.add("*.jpge");

        //BorderPane
        Parent NewPrescription;
        try {
            NewPrescription = FXMLLoader.load(getClass().getResource("/view/UserInfo.fxml"));
            borderpane.setCenter(NewPrescription);
        } catch (IOException ex) {
            Logger.getLogger(DrProfileController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void choosFile(ActionEvent event) {
        FileChooser FC = new FileChooser();
        // File selectedFile=FC.showOpenDialog(null);
        FC.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image File", lstFile));
        File F = FC.showOpenDialog(null);
        if (F != null) {
            fileChosenField.setText("Select File::" + F.getAbsolutePath());
        }
    }

    @FXML
    private void passwordCahnge(MouseEvent event) throws IOException {
        Parent Prescription = FXMLLoader.load(getClass().getResource("/view/PasswordChange.fxml"));
        borderpane.setCenter(Prescription);
    }

    @FXML
    private void userInfo(MouseEvent event) throws IOException {
        Parent Prescription = FXMLLoader.load(getClass().getResource("/view/UserInfo.fxml"));
        borderpane.setCenter(Prescription);
    }

}
