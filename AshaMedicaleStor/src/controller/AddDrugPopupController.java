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
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Debobrato Biswas
 */
public class AddDrugPopupController implements Initializable {

    @FXML
    private TextField medicineName;
    @FXML
    private TextField medicinePower;
    @FXML
    private TextField medicinePrice;
    @FXML
    private Button exit;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void SAVE(ActionEvent event) throws SQLException {
        String medicineNameInput = medicineName.getText();
        String medicinePowerInput = medicinePower.getText();
        String medicinePriceInput = medicinePrice.getText();

        Connection connection = DBCon.getConnection();
        String medicineSQL = "INSERT INTO ashamedicalstore.tbl_drug (drug_name, drug_power, drug_price) VALUES(?,?,?)";
        //drug_id, drug_name, drug_power, drug_company, drug_price, menufecture_date, expair_date, insert_date, update_date, drug_quantity, is_online
        try {

            PreparedStatement pst = connection.prepareStatement(medicineSQL);
            pst.setString(1, medicineNameInput);
            pst.setString(2, medicinePowerInput);
            pst.setString(3, medicinePriceInput);

            //pst.setTimestamp(3, getCurrentTimeStamp());
            pst.execute();
            pst.close();
            medicineName.clear();
            medicinePower.clear();
            medicinePrice.clear();

            //notifi.show();
        } catch (SQLException e) {
            Logger.getLogger(AddDrugPopupController.class.getName()).log(Level.SEVERE, null, e);
            System.out.println(e);
//            Notifications notificaton = Notifications.create().title("Save Unsuccessful").text("Unsuccessful").graphic(null).hideAfter(Duration.seconds(3)).position(Pos.TOP_CENTER);
//            notificaton.showError();
        }
    }

    @FXML
    private void RESET(ActionEvent event) {
        medicineName.clear();
        medicinePower.clear();
        medicinePrice.clear();
    }

    @FXML
    private void EXIT(ActionEvent event) {
        Stage stage = (Stage) exit.getScene().getWindow();
        stage.close();
    }

}
