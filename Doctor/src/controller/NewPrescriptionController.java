/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import helper.DynaimicViews;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

/**
 * FXML Controller class
 *
 * @author devbi
 */
public class NewPrescriptionController implements Initializable {

    @FXML
    private BorderPane borderpane;
    @FXML
    private AnchorPane anchorpane;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void NewPrescription(MouseEvent event) throws IOException {
        Parent Prescription = FXMLLoader.load(getClass().getResource("/view/PrescriptionGenerate.fxml"));
        borderpane.setCenter(Prescription);
    }

    @FXML
    private void TodayPrescriptionList(MouseEvent event) throws IOException {
        Parent Prescription = FXMLLoader.load(getClass().getResource("/view/PrescriptionList.fxml"));
        borderpane.setCenter(Prescription);
    }

    @FXML
    private void TotalPrescriptionList(MouseEvent event) throws IOException {
        Parent Prescription = FXMLLoader.load(getClass().getResource("/view/TotalPrescriptionList.fxml"));
        borderpane.setCenter(Prescription);
    }

    @FXML
    private void AppoinmentList(MouseEvent event) throws IOException {
        Parent Prescription = FXMLLoader.load(getClass().getResource("/view/AppoinmentList.fxml"));
        borderpane.setCenter(Prescription);
    }
}
