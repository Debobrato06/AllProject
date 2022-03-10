/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import Database.DBCon;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
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

/**
 * FXML Controller class
 *
 * @author Debobrato Biswas
 */
public class AddCompaniesPopupController implements Initializable {

    @FXML
    private TextField companyName;
    @FXML
    private Button close;

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
    private void save(ActionEvent event) throws SQLException {
//        Image img = new Image("/assets/images/success5.gif");
//        Notifications notifi = Notifications.create().title("Save Successfull").text("Successfull").graphic(new ImageView(img)).hideAfter(Duration.seconds(3)).position(Pos.TOP_CENTER);

        String companyNameInput = companyName.getText();

        Connection connection = DBCon.getConnection();
        String companyNameSQL = "INSERT INTO ashamedicalhall.tbl_companies (company_name, insert_time) VALUES(?,?)";
        //company_id, company_name, insert_time
        try {

            PreparedStatement pst = connection.prepareStatement(companyNameSQL);
            pst.setString(1, companyNameInput);
            pst.setDate(2, getCurrentDate());
            //pst.setTimestamp(3, getCurrentTimeStamp());

            pst.execute();
            pst.close();
            companyName.clear();

            //notifi.show();

        } catch (SQLException e) {
            Logger.getLogger(AddCompaniesPopupController.class.getName()).log(Level.SEVERE, null, e);
            System.out.println(e);
//            Notifications notificaton = Notifications.create().title("Save Unsuccessful").text("Unsuccessful").graphic(null).hideAfter(Duration.seconds(3)).position(Pos.TOP_CENTER);
//            notificaton.showError();
        }
    }

    @FXML
    private void reset(ActionEvent event) {
        companyName.setText(null);
    }

    @FXML
    private void close(ActionEvent event) {
        Stage stage = (Stage) close.getScene().getWindow();
        stage.close();
    }

}
