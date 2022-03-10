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
public class OwnExaminationUpdatePopupController implements Initializable {

    public int tableId = 0;
    ArrayList<String> ownExaminationName = null;
    ArrayList<String> ownExaminationIds = null;

    @FXML
    private Button close;
    @FXML
    private TextField ownExaminationInput;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void update(ActionEvent event) throws SQLException {

        Image img = new Image("/images/success5.gif");

        Notifications notifi = Notifications.create().title("Save Successfull").text("Successfull").graphic(new ImageView(img)).hideAfter(Duration.seconds(3)).position(Pos.TOP_CENTER);

        String ownExaminationNameInput = ownExaminationInput.getText();

        Connection connection = DBConn.getConnection();
        /////oe_title, doctor_id, insert_by, insert_time
        String drugTakeFormatSQL = "UPDATE tbl_own_examinations SET  oe_title = ?,doctor_id = ?,insert_by = ?, insert_time = ? WHERE id =" + tableId;

        try {

            PreparedStatement pst = connection.prepareStatement(drugTakeFormatSQL);
            pst.setString(1, ownExaminationNameInput);
            pst.setString(2, "2");
            pst.setString(3, "5");
            pst.setDate(4, getCurrentDate());
            pst.setTimestamp(4, getCurrentTimeStamp());

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

    private static java.sql.Date getCurrentDate() {
        java.util.Date today = new java.util.Date();
        return new java.sql.Date(today.getTime());

    }

    private static java.sql.Timestamp getCurrentTimeStamp() {

        java.util.Date today = new java.util.Date();
        return new java.sql.Timestamp(today.getTime());

    }

    void getOwnExamination(int id) {
        this.tableId = id;

        try {
            Connection connection = DBConn.getConnection();
            ResultSet resultSet = connection.createStatement().executeQuery("SELECT * FROM tbl_own_examinations where id=" + tableId);

            ownExaminationName = new ArrayList<String>();
            ownExaminationIds = new ArrayList<String>();

            while (resultSet.next()) {

                String ownExaminationTitle = resultSet.getString("oe_title");
                ownExaminationName.add(ownExaminationTitle);
                ownExaminationInput.setText(ownExaminationTitle);

                int ownExaminationId = resultSet.getInt("id");
                ownExaminationIds.add(String.valueOf(ownExaminationId));
            }
            

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println("Can't loaded the page");
        }

    }

    @FXML
    private void reset(ActionEvent event) {
        ownExaminationInput.clear();
    }

    @FXML
    private void close(ActionEvent event) {
        Stage stage = (Stage) close.getScene().getWindow();
        stage.close();
    }

}
