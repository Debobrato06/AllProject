/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import JDBC.DBConn;
import helper.AutoCompleteTextField;
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
public class DrugTakeFormetPopupController implements Initializable {

    Connection connection;
    PreparedStatement pst = null;
    ResultSet rs = null;

    ArrayList<String> drugTakeFormatName = null;
    ArrayList<String> drugTakeFormatId = null;

    public int tableId = 0;

    @FXML
    private Button close;

    @FXML
    private TextField drugTakeFormatInputField;

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
        //String drugTakeFormat = drugNameInputField.getText();

        Image img = new Image("/images/success5.gif");

        Notifications notifi = Notifications.create().title("Save Successfull").text("Successfull").graphic(new ImageView(img)).hideAfter(Duration.seconds(3)).position(Pos.TOP_CENTER);

        String drugTakeFormat = drugTakeFormatInputField.getText();

        Connection connection = DBConn.getConnection();

        String drugTakeFormatSQL = "UPDATE tbl_drug_take_format SET  eat_format = ?,doctor_id = ?,insert_time = ? WHERE id =" + tableId;

        try {

            PreparedStatement pst = connection.prepareStatement(drugTakeFormatSQL);
            pst.setString(1, drugTakeFormat);
            pst.setString(2, "3");
            pst.setDate(3, getCurrentDate());
            pst.setTimestamp(3, getCurrentTimeStamp());

            pst.execute();
            pst.close();

            //drugNameInputField.setText(eatFormat);
            drugTakeFormatInputField.clear();

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
        drugTakeFormatInputField.clear();
    }

    @FXML
    private void close(ActionEvent event) {
        Stage stage = (Stage) close.getScene().getWindow();
        stage.close();
    }

    void getDrugTakeFormat(int id) {

        this.tableId = id;

        try {
            Connection connection = DBConn.getConnection();
            ResultSet resultSet = connection.createStatement().executeQuery("SELECT * FROM tbl_drug_take_format where id=" + id);

            drugTakeFormatName = new ArrayList<String>();
            drugTakeFormatId = new ArrayList<String>();

            while (resultSet.next()) {
                String drugTakeFormatNameInput = resultSet.getString("eat_format");
                drugTakeFormatName.add(drugTakeFormatNameInput);
                drugTakeFormatInputField.setText(drugTakeFormatNameInput);
                int drugTakeFormatIdInput = resultSet.getInt("id");
                drugTakeFormatId.add(String.valueOf(drugTakeFormatIdInput));
                
                
            }

            

        } catch (SQLException e) {

            System.out.println(e.getMessage());
            System.out.println("drugTakeFormatName:");

        }
    }

}
