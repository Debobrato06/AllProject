/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import JDBC.DBConn;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;

/**
 * FXML Controller class
 *
 * @author devbi
 */
public class AddDrugController implements Initializable {

    ArrayList<String> drugCompanyNamecomboBox = null;
    ArrayList<String> drugCompanyIdscomboBox = null;

    ArrayList<String> drugGenericNamecomboBox = null;
    ArrayList<String> drugGenericIdscomboBox = null;

    private BorderPane borderpane;
    @FXML
    private ChoiceBox<String> drugCompanyName;
    @FXML
    private ChoiceBox<String> drugGenericName;
    @FXML
    private Button resetIds;
    @FXML
    private Button saveIds;
    @FXML
    private TextField drugNameInputField;

    public void choiceBoxButtonPushed() {
        //choiceBoxLabel.setText("My Favourite:\n"+choiceBox.getValue().toString());
        drugCompanyName.getValue().toString();
        drugGenericName.getValue().toString();
        // choiceBox2.getValue().toString();
    }

    private static java.sql.Date getCurrentDate() {
        java.util.Date today = new java.util.Date();
        return new java.sql.Date(today.getTime());

    }

    private static java.sql.Timestamp getCurrentTimeStamp() {

        java.util.Date today = new java.util.Date();
        return new java.sql.Timestamp(today.getTime());

    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        getDrugCompanyName();
        getDrugGenericName();

    }

    private void getDrugCompanyName() {

        try {

            Connection connection = DBConn.getConnection();
            ResultSet resultSet = connection.createStatement().executeQuery("SELECT * FROM prescription.tbl_drug_company t");

            drugCompanyNamecomboBox = new ArrayList<String>();
            drugCompanyIdscomboBox = new ArrayList<String>();

            while (resultSet.next()) {
                //id, company_name, priority, insert_time, doctor_id

                String drugCompanyNameInput = resultSet.getString("company_name");
                drugCompanyName.getItems().add(drugCompanyNameInput);

                drugCompanyNamecomboBox.add(drugCompanyNameInput);
                int drugCompanyIds = resultSet.getInt("id");
                drugCompanyIdscomboBox.add(String.valueOf(drugCompanyIds));

            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println("Company List:");
        }

    }

    private void getDrugGenericName() {

        try {
            Connection connection = DBConn.getConnection();
            ResultSet rs = connection.createStatement().executeQuery("SELECT * FROM prescription.tbl_drug_generic t");

            drugGenericNamecomboBox = new ArrayList<String>();
            drugGenericIdscomboBox = new ArrayList<String>();

            while (rs.next()) {
                //Gender.getItems().add(resultSet.getString("gender"));id, name, doctor_id, insert_time

                String drugGenericNameInput = rs.getString("name");
                drugGenericName.getItems().add(drugGenericNameInput);

                drugGenericNamecomboBox.add(drugGenericNameInput);
                int drugGenericIds = rs.getInt("id");
                drugGenericIdscomboBox.add(String.valueOf(drugGenericIds));

            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println("Generic List:");
        }

    }

    @FXML
    private void DrugList(MouseEvent event) throws IOException {
        Parent Prescription = FXMLLoader.load(getClass().getResource("/view/DrugList.fxml"));
        borderpane.setCenter(Prescription);
    }

    @FXML
    private void reset(ActionEvent event) {

        drugNameInputField.clear();
        drugCompanyName.getSelectionModel().clearSelection();
        drugGenericName.getSelectionModel().clearSelection();

    }

    @FXML
    private void save(ActionEvent event) throws SQLException {

        Image img = new Image("/images/success5.gif");
        Notifications notifi = Notifications.create().title("Save Successfull").text("Successfull").graphic(new ImageView(img)).hideAfter(Duration.seconds(3)).position(Pos.TOP_CENTER);

        String drugNameInput = drugNameInputField.getText();

        String drugCompanyNameInput = drugCompanyName.getValue().toString();
        int drugCompanyNameIndex = drugCompanyNamecomboBox.indexOf(drugCompanyNameInput);

        String drugGenericNameInput = drugGenericName.getValue().toString();
        int drugGenericNameIndex = drugGenericNamecomboBox.indexOf(drugGenericNameInput);

        Connection connection = DBConn.getConnection();
        String rulesForPatientSQL = "INSERT INTO prescription.tbl_drug (drug_name, drug_generic_id, drug_company_id, doctor_id, insert_time) VALUES(?,?,?,?,?)";
        // PreparedStatement=null;id, complaint, doctor_id, insert_time, last_update
        try {

            PreparedStatement pst = connection.prepareStatement(rulesForPatientSQL);
            pst.setString(1, drugNameInput);
            pst.setString(2, drugGenericIdscomboBox.get(drugGenericNameIndex));
            pst.setString(3, drugCompanyIdscomboBox.get(drugCompanyNameIndex));
            pst.setString(4, "2");
            pst.setDate(5, getCurrentDate());
            // pst.setTimestamp(5, getCurrentTimeStamp());

            pst.execute();
            pst.close();
            drugNameInputField.clear();
            drugCompanyName.getSelectionModel().clearSelection();
            drugGenericName.getSelectionModel().clearSelection();

            notifi.show();

        } catch (SQLException e) {
            Logger.getLogger(ChiefComplaintsController.class.getName()).log(Level.SEVERE, null, e);
            System.out.println(e);
            Notifications notificaton = Notifications.create().title("Save Unsuccessful").text("Unsuccessful").graphic(null).hideAfter(Duration.seconds(3)).position(Pos.TOP_CENTER);

            notificaton.showError();
        }

    }

}
