/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.jfoenix.controls.JFXTextField;
import database.DBConn;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

/**
 * FXML Controller class
 *
 * @author Debobrato Biswas
 */
public class MoneyReceiptSettingController implements Initializable {

    @FXML
    private JFXTextField restaurantName;
    @FXML
    private JFXTextField restaurantAddress;
    @FXML
    private JFXTextField phoneNumber;
    @FXML
    private JFXTextField email;

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

    private static String getCurrentTimeStamp() {

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm");
        LocalDateTime now = LocalDateTime.now();
        return dtf.format(now);

    }

    @FXML
    private void updateAction(ActionEvent event) throws SQLException {
        String restaurantNameInput = restaurantName.getText();
        String address = restaurantAddress.getText();
        String phoneNo = phoneNumber.getText();
        String emailAddress = email.getText();

        Connection connection = DBConn.getConnection();
        String foodPayment = "INSERT INTO restaurent.tbl_money_receipt_setting ( restaurent_name, restaurent_address, email, phone, title_tag, bottom_text, insert_by, insert_time ) VALUES(?,?,?,?,?,?,?,?)";

        try {

            PreparedStatement pst = connection.prepareStatement(foodPayment, Statement.RETURN_GENERATED_KEYS);
            pst.setString(1, restaurantNameInput);
            pst.setString(2, address);
            pst.setString(3, emailAddress);
            pst.setString(4, phoneNo);
            pst.setString(5, "No thing");
            pst.setString(6, "0");
            pst.setString(7, "1");

            //pst.setDate(9, getCurrentDate());
            pst.setString(8, getCurrentTimeStamp());

            int affectedRows = pst.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Creating Client failed, no rows affected.");
            }

            try (ResultSet generatedKeys = pst.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    //  foodPaymentIdInput = (int) generatedKeys.getLong(1);
                } else {
                    throw new SQLException("Creating Client failed, no ID obtained.");
                }
            }

            pst.close();

        } catch (SQLException e) {
            Logger.getLogger(InvoiceGenerateController.class.getName()).log(Level.SEVERE, null, e);
            System.out.println(e);
        }
    }

}
