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
public class DrugGenericPopupController implements Initializable {

    public int tableId = 0;
    ArrayList<String> drugGenericName = null;
    ArrayList<String> drugGenericIds = null;

    @FXML
    private Button close;
    @FXML
    private TextField drugGenericNameUpdate;

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

        String genericName = drugGenericNameUpdate.getText();

        Connection connection = DBConn.getConnection();
        String drugGenericNameSQL = "UPDATE tbl_drug_generic SET  name = ?,doctor_id = ?,insert_time = ? WHERE id =" + tableId;
        //"UPDATE tbl_drug_generic SET  name = ?,doctor_id = ?,insert_time = ?, last_update = ? WHERE id =" + tableId
        try {

            PreparedStatement pst = connection.prepareStatement(drugGenericNameSQL);
            pst.setString(1, genericName);
            pst.setString(2, "5");
            pst.setDate(3, getCurrentDate());
            //pst.setTimestamp(3, getCurrentTimeStamp());

            pst.execute();
            pst.close();
            // genericNameInput.clear();

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

    void getDrugGeneric(int id) {
        this.tableId = id;

        try {
            Connection connection = DBConn.getConnection();
            ResultSet resultSet = connection.createStatement().executeQuery("SELECT * FROM tbl_drug_generic where id=" + tableId);
/////////chiefComplaintsName,  chiefComplaintsIds/////chiefComplaintsUpdate

            drugGenericName = new ArrayList<String>();
            drugGenericIds = new ArrayList<String>();
            while (resultSet.next()) {

                String drugGenericNameInput = resultSet.getString("name");
                drugGenericName.add(drugGenericNameInput);
                drugGenericNameUpdate.setText(drugGenericNameInput);

                int drugGenericId = resultSet.getInt("id");
                drugGenericIds.add(String.valueOf(drugGenericId));
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println("Can't loaded the page");
        }

    }

    @FXML
    private void reset(ActionEvent event) {

        drugGenericNameUpdate.clear();
    }

    @FXML
    private void close(ActionEvent event) {
        Stage stage = (Stage) close.getScene().getWindow();
        stage.close();
    }

}
