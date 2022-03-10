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
public class MedicalTestPopupController implements Initializable {

    public int tableId = 0;
    ArrayList<String> testInputName = null;
    ArrayList<String> testInputId = null;

    @FXML
    private TextField testNameInput;
    @FXML
    private TextField testCatagoryNameInput;
    @FXML
    private Button close;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    void getRulesForPatient(int id) {
        this.tableId = id;

        try {
            Connection connection = DBConn.getConnection();
            ResultSet resultSet = connection.createStatement().executeQuery("SELECT * FROM tbl_medical_test where id=" + tableId);

            testInputName = new ArrayList<String>();
            testInputId = new ArrayList<String>();

            while (resultSet.next()) {
                String testNameInputUpdate = resultSet.getString("test_name");
                testInputName.add(testNameInputUpdate);
                testNameInput.setText(testNameInputUpdate);
                int patientRulesIdInput = resultSet.getInt("id");
                testInputId.add(String.valueOf(patientRulesIdInput));

                String testCatagoryInput = resultSet.getString("test_category");
                testCatagoryNameInput.setText(testCatagoryInput);
            }

        } catch (SQLException e) {

            System.out.println(e.getMessage());
            System.out.println("drugTakeFormatName:");

        }

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
        ////"UPDATE tbl_medical_test SET  test_name = ?,test_category = ?,insert_time = ?,doctor_id = ? WHERE id =" + tableId

        Image img = new Image("/images/success5.gif");
        Notifications notifi = Notifications.create().title("Save Successfull").text("Successfull").graphic(new ImageView(img)).hideAfter(Duration.seconds(3)).position(Pos.TOP_CENTER);

        String testName = testNameInput.getText();
        String testCatagoryName = testCatagoryNameInput.getText();

        Connection connection = DBConn.getConnection();
        String medicalTestSQL = "UPDATE tbl_medical_test SET  test_name = ?,test_category = ?,insert_time = ?,doctor_id = ? WHERE id =" + tableId;
        // PreparedStatement=null;id, complaint, doctor_id, insert_time, last_update
        try {

            PreparedStatement pst = connection.prepareStatement(medicalTestSQL);
            pst.setString(1, testName);
            pst.setString(2, testCatagoryName);
            pst.setDate(3, getCurrentDate());
            pst.setTimestamp(3, getCurrentTimeStamp());
            pst.setString(4, "4");

            pst.execute();
            pst.close();
//            testNameInput.clear();

            notifi.show();
            Stage stage = (Stage) close.getScene().getWindow();
            stage.close();

        } catch (SQLException e) {
            Logger.getLogger(ChiefComplaintsController.class.getName()).log(Level.SEVERE, null, e);
            System.out.println(e);
            Notifications notificaton = Notifications.create().title("Save Unsuccessful").text("Unsuccessful").graphic(null).hideAfter(Duration.seconds(3)).position(Pos.TOP_CENTER);

            notificaton.showError();
        }

    }

    @FXML
    private void reset(ActionEvent event) {
        testNameInput.clear();
        testCatagoryNameInput.clear();
    }

    @FXML
    private void close(ActionEvent event) {
        Stage stage = (Stage) close.getScene().getWindow();
        stage.close();
    }

}
