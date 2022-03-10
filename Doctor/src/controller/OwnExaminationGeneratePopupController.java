/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import JDBC.DBConn;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Debobrato Biswas
 */
public class OwnExaminationGeneratePopupController implements Initializable {

    ArrayList<String> ownExaminationName = null;
    ArrayList<String> ownExaminationIds = null;

    @FXML
    private TextField ownExaminationInput;
    @FXML
    private ComboBox<String> ownExamination;
    @FXML
    private Button reset;
    @FXML
    private Button close;

    PrescriptionGenerateController mController;

    public void setParams(PrescriptionGenerateController controller) {
        this.mController = controller;

    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        getOwnExamination();

    }

    @FXML
    private void save(ActionEvent event) {

        String ownExaminationNameInput = (String) ownExamination.getSelectionModel().getSelectedItem();
        int ownExaminationIndex = ownExamination.getSelectionModel().getSelectedIndex();
        String selectedownExaminationID = ownExaminationIds.get(ownExaminationIndex);

        String ownExaminationValue = ownExaminationInput.getText();

        System.out.println(ownExaminationIds.toArray());
        System.out.println(ownExaminationName.toArray());

        System.out.println("Selected Index " + ownExaminationIndex);
        System.out.println("Selected ID " + selectedownExaminationID);

        mController.ownExaminationDetails(ownExaminationNameInput, ownExaminationValue, selectedownExaminationID);
        Stage stage = (Stage) close.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void reset(ActionEvent event) {
        ownExaminationInput.clear();
        ownExamination.getSelectionModel().clearSelection();
    }

    @FXML
    private void close(ActionEvent event) {
        Stage stage = (Stage) close.getScene().getWindow();
        stage.close();
    }

    private void getOwnExamination() {

        try {
            Connection connection = DBConn.getConnection();

            ResultSet resultSet = connection.createStatement().executeQuery("SELECT * FROM tbl_own_examinations");

            ownExaminationName = new ArrayList<String>();
            ownExaminationIds = new ArrayList<String>();

            while (resultSet.next()) {//id, oe_title, doctor_id, insert_by, insert_time
                ownExamination.getItems().add(resultSet.getString("oe_title"));

                String ownExaminationTitle = resultSet.getString("oe_title");
                ownExaminationName.add(ownExaminationTitle);

                int ownExaminationId = resultSet.getInt("id");
                ownExaminationIds.add(String.valueOf(ownExaminationId));
            }
            System.out.println("OwnExaminationName name" + ownExaminationName);
            System.out.println("OwnExaminationIds ID" + ownExaminationIds);

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println("Can't loaded the page");
        }

    }

}
