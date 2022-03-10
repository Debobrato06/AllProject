/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import JDBC.DBConn;
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
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;

/**
 * FXML Controller class
 *
 * @author Debobrato Biswas
 */
public class RulesForPatientPopupController implements Initializable {

    public int tableId = 0;
    ArrayList<String> patientRulesInputName = null;
    ArrayList<String> patientRulesInputId = null;

    @FXML
    private Button close;
    @FXML
    private TextField patientRulesInputField;
    @FXML
    private TextField patientCatagoryInputField;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    private static java.sql.Date getCurrentDate() {
        java.util.Date today = new java.util.Date();
        return new java.sql.Date(today.getTime());

    }

    private static java.sql.Timestamp getCurrentTimeStamp() {

        java.util.Date today = new java.util.Date();
        return new java.sql.Timestamp(today.getTime());

    }

    @FXML
    private void update(ActionEvent event) throws SQLException {

        Image img = new Image("/images/success5.gif");

        Notifications notifi = Notifications.create().title("Save Successfull").text("Successfull").graphic(new ImageView(img)).hideAfter(Duration.seconds(3)).position(Pos.TOP_CENTER);

        String  patientRulesInput = patientRulesInputField.getText();
        String patientCatagoryInput = patientCatagoryInputField.getText();
        
        Connection connection = DBConn.getConnection();
        /////id, rules, patient_category, doctor_id
        String drugTakeFormatSQL = "UPDATE tbl_rules_for_patient SET  rules = ?,patient_category = ?,doctor_id = ? WHERE id =" + tableId;

        try {

            PreparedStatement pst = connection.prepareStatement(drugTakeFormatSQL);
            pst.setString(1, patientRulesInput);
            pst.setString(2, patientCatagoryInput);
            pst.setString(3, "3");
       
            pst.execute();
            pst.close();

            //drugNameInputField.setText(eatFormat);
            notifi.show();

        } catch (SQLException e) {
            Logger.getLogger(ChiefComplaintsController.class.getName()).log(Level.SEVERE, null, e);
            System.out.println(e);
            Notifications notificaton = Notifications.create().title("Save Unsuccessful").text("Unsuccessful").graphic(null).hideAfter(Duration.seconds(3)).position(Pos.TOP_CENTER);

            notificaton.showError();
        }
        Stage stage = (Stage) close.getScene().getWindow();
        stage.close();

    }

    @FXML
    private void reset(ActionEvent event) {
        patientRulesInputField.clear();
        patientCatagoryInputField.clear();
    }

    @FXML
    private void close(ActionEvent event) {
        Stage stage = (Stage) close.getScene().getWindow();
        stage.close();
    }

    void getRulesForPatient(int id) {
        this.tableId = id;

        try {
            Connection connection = DBConn.getConnection();
            ResultSet resultSet = connection.createStatement().executeQuery("SELECT * FROM tbl_rules_for_patient where id=" + tableId);

            patientRulesInputName = new ArrayList<String>();
            patientRulesInputId = new ArrayList<String>();

            while (resultSet.next()) {
                String patientRulesNameInput = resultSet.getString("rules");
                patientRulesInputName.add(patientRulesNameInput);
                patientRulesInputField.setText(patientRulesNameInput);
                int patientRulesIdInput = resultSet.getInt("id");
                patientRulesInputId.add(String.valueOf(patientRulesIdInput));

                String patientCatagoryInput = resultSet.getString("patient_category");
                patientCatagoryInputField.setText(patientCatagoryInput);
            }

        } catch (SQLException e) {

            System.out.println(e.getMessage());
            System.out.println("drugTakeFormatName:");

        }

    }

}
