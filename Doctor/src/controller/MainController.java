/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import static controller.MasterPage.stage;
import helper.DynaimicViews;
import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
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
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import static javafx.scene.paint.Color.web;
import static javafx.scene.paint.Color.web;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

/**
 *
 * @author devbi
 */
public class MainController implements Initializable {

    @FXML
    private BorderPane borderpane;

    private WebEngine engine;
    @FXML
    private ImageView s_image;

    @FXML
    private Button minimize;
    @FXML
    private Button exit;

    private void handleButtonAction(ActionEvent event) {
        // System.out.println("You clicked me!");
        // label.setText("Hello World!");
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        ///TODO
        try {

            // TODO
            Parent NewPrescription = FXMLLoader.load(getClass().getResource("/view/NewPrescription.fxml"));

            borderpane.setCenter(NewPrescription);

        } catch (IOException ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void dashboard(MouseEvent event) throws IOException {
        //DynaimicViews.loaderBorderCenter(borderpane, "NewPrescription");
        Parent Prescription = FXMLLoader.load(getClass().getResource("/view/NewPrescription.fxml"));
        borderpane.setCenter(Prescription);
    }

    @FXML
    private void RulesForPatient(MouseEvent event) throws IOException {
        Parent Prescription = FXMLLoader.load(getClass().getResource("/view/RulesForPatient.fxml"));
        borderpane.setCenter(Prescription);
    }

    @FXML
    private void OwnExamination(MouseEvent event) throws IOException {
//        Parent Prescription = FXMLLoader.load(getClass().getResource("/view/Own Examination.fxml"));
        Parent Prescription = FXMLLoader.load(getClass().getResource("/view/Own Examination.fxml"));
        borderpane.setCenter(Prescription);
    }

    @FXML
    private void ChifComplaints(MouseEvent event) throws IOException {
        Parent Prescription = FXMLLoader.load(getClass().getResource("/view/Chief Complaints.fxml"));
        borderpane.setCenter(Prescription);
    }

    @FXML
    private void MadicalTest(MouseEvent event) throws IOException {
        Parent Prescription = FXMLLoader.load(getClass().getResource("/view/MedicalTest.fxml"));
        borderpane.setCenter(Prescription);
    }

    @FXML
    private void DrugGeneric(MouseEvent event) throws IOException {
        Parent Prescription = FXMLLoader.load(getClass().getResource("/view/DrugGeneric.fxml"));
        borderpane.setCenter(Prescription);
    }

    @FXML
    private void DrugCompany(MouseEvent event) throws IOException {
        Parent Prescription = FXMLLoader.load(getClass().getResource("/view/DrugCompany.fxml"));
        borderpane.setCenter(Prescription);
    }

    @FXML
    private void PrescriptionDashbord(MouseEvent event) throws IOException {
        Parent Prescription = FXMLLoader.load(getClass().getResource("/view/PrescriptionGenerate.fxml"));
        borderpane.setCenter(Prescription);
    }

    @FXML
    private void DrugTakeFormat(MouseEvent event) throws IOException {
        Parent Prescription = FXMLLoader.load(getClass().getResource("/view/DrugTakeFormet.fxml"));
        borderpane.setCenter(Prescription);
    }

    private void Setting(MouseEvent event) throws IOException {
        Parent Prescription = FXMLLoader.load(getClass().getResource("/view/Setting.fxml"));
        borderpane.setCenter(Prescription);
    }

    @FXML
    private void Profile(MouseEvent event) throws IOException {
        Parent Prescription = FXMLLoader.load(getClass().getResource("/view/DrProfile.fxml"));
        borderpane.setCenter(Prescription);
    }

    @FXML
    private void WEB(MouseEvent event) throws IOException, URISyntaxException {

        Parent Prescription = FXMLLoader.load(getClass().getResource("/view/Web.fxml"));
        borderpane.setCenter(Prescription);

    }

    @FXML
    private void Web(ActionEvent event) throws URISyntaxException, IOException {

    }

    @FXML
    private void PrescriptionList(MouseEvent event) throws IOException {
        Parent Prescription = FXMLLoader.load(getClass().getResource("/view/PrescriptionList.fxml"));
        borderpane.setCenter(Prescription);
    }

    @FXML
    private void DrugList(MouseEvent event) throws IOException {
        Parent Prescription = FXMLLoader.load(getClass().getResource("/view/DrugList.fxml"));
        borderpane.setCenter(Prescription);
    }

    @FXML
    private void minimize(ActionEvent event) {
        Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        stage.setIconified(true);

    }

    @FXML
    private void exit(ActionEvent event) {
        Stage stage = (Stage) exit.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void notification(ActionEvent event) throws IOException {
        Parent Prescription = FXMLLoader.load(getClass().getResource("/view/Setting.fxml"));
        borderpane.setCenter(Prescription);
    }

}
