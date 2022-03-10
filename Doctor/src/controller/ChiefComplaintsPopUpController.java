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
public class ChiefComplaintsPopUpController implements Initializable {

    public int tableId = 0;
    ArrayList<String> chiefComplaintsName = null;
    ArrayList<String> chiefComplaintsIds = null;

    @FXML
    private TextField chiefComplaintsUpdate;
    @FXML
    private Button close;
    @FXML
    private Button reset;

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

        String complaint = chiefComplaintsUpdate.getText();

        Connection connection = DBConn.getConnection();
        String chiefComplaintsSQL = "UPDATE tbl_chief_complaints SET  complaint = ?,doctor_id = ?,insert_time = ?, last_update = ? WHERE id =" + tableId;
        // PreparedStatement=null;id, complaint, doctor_id, insert_time, last_update
    
        try {

            PreparedStatement pst = connection.prepareStatement(chiefComplaintsSQL);
            pst.setString(1, complaint);
            pst.setString(2, "2");
            pst.setDate(3, getCurrentDate());
            pst.setTimestamp(3, getCurrentTimeStamp());
            pst.setDate(4, getCurrentDate());
            pst.setTimestamp(4, getCurrentTimeStamp());
            
            pst.execute();
            pst.close();
           // chiefComplaintsUpdate.clear();

            Image img = new Image("/images/success5.gif");
            Notifications notifi = Notifications.create().title("Save Successfull").text("Successfull").graphic(new ImageView(img)).hideAfter(Duration.seconds(3)).position(Pos.TOP_CENTER);

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
    private void close(ActionEvent event) {
        Stage stage = (Stage) close.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void reset(ActionEvent event) {
        chiefComplaintsUpdate.clear();
    }

    void getChiefComplaints(int id) {
        this.tableId = id;

        try {
            Connection connection = DBConn.getConnection();
            ResultSet resultSet = connection.createStatement().executeQuery("SELECT * FROM tbl_chief_complaints where id=" + tableId);
/////////chiefComplaintsName,  chiefComplaintsIds/////chiefComplaintsUpdate

            chiefComplaintsName = new ArrayList<String>();
            chiefComplaintsIds = new ArrayList<String>();
            while (resultSet.next()) {

                String chiefComplaintsNameInput = resultSet.getString("complaint");
                chiefComplaintsName.add(chiefComplaintsNameInput);
                chiefComplaintsUpdate.setText(chiefComplaintsNameInput);

                int chiefComplaintsId = resultSet.getInt("id");
                chiefComplaintsIds.add(String.valueOf(chiefComplaintsId));
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println("Can't loaded the page");
        }

    }

}
