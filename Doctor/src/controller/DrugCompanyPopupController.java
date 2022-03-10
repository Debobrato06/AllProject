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
public class DrugCompanyPopupController implements Initializable {

    public int tableId = 0;
    ArrayList<String> companyNameInput = null;
    ArrayList<String> priorityUpdateId = null;

    @FXML
    private Button close;
    @FXML
    private TextField companyNameUpdate;
    @FXML
    private TextField priorityUpdate;

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
            ResultSet resultSet = connection.createStatement().executeQuery("SELECT * FROM tbl_drug_company where id=" + tableId);

            companyNameInput = new ArrayList<String>();
            priorityUpdateId = new ArrayList<String>();

            while (resultSet.next()) {
                String companyNameInputUpdate = resultSet.getString("company_name");
                companyNameInput.add(companyNameInputUpdate);
                companyNameUpdate.setText(companyNameInputUpdate);
                int companyNameUpdateIdInput = resultSet.getInt("id");
                priorityUpdateId.add(String.valueOf(companyNameUpdateIdInput));

                String priorityInput = resultSet.getString("priority");
                priorityUpdate.setText(priorityInput);
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

        Image img = new Image("/images/success5.gif");
        Notifications notifi = Notifications.create().title("Save Successfull").text("Successfull").graphic(new ImageView(img)).hideAfter(Duration.seconds(3)).position(Pos.TOP_CENTER);

        String companyName = companyNameUpdate.getText();
        String companyPriority = priorityUpdate.getText();

        Connection connection = DBConn.getConnection();
        String drugCompanySQL = "UPDATE tbl_drug_company SET  company_name = ?,priority = ?,insert_time = ?,doctor_id = ? WHERE id =" + tableId;
        //  "UPDATE tbl_drug_company SET  company_name = ?,priority = ?,insert_time = ?,doctor_id = ? WHERE id =" + tableId
        try {

            PreparedStatement pst = connection.prepareStatement(drugCompanySQL);
            pst.setString(1, companyName);
            pst.setString(2, companyPriority);
            pst.setDate(3, getCurrentDate());
            pst.setTimestamp(3, getCurrentTimeStamp());
            pst.setString(4, "3");
            pst.execute();
            pst.close();

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

        companyNameUpdate.clear();
        priorityUpdate.clear();
    }

    @FXML
    private void close(ActionEvent event) {

        Stage stage = (Stage) close.getScene().getWindow();
        stage.close();
    }

}
