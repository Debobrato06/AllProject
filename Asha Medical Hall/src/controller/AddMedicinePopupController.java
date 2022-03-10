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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Debobrato Biswas
 */
public class AddMedicinePopupController implements Initializable {

    @FXML
    private Button exit;
    @FXML
    private TextField medicineName;
    @FXML
    private TextField medicinePower;
    @FXML
    private TextField medicinePrice;
    @FXML
    private ComboBox companyName;
    @FXML
    private DatePicker mfgDate;
    @FXML
    private DatePicker expDate;
    @FXML
    private TextField medicineQuantity;
    @FXML
    private ComboBox medicineType;

    ArrayList<String> drugTypeName = null;
    ArrayList<String> drugTypeIds = null;

    ArrayList<String> companyNameList = null;
    ArrayList<String> companyIds = null;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
//        companyName.getItems().addAll("Square Pharmaceuticals Ltd.", "Beximco Pharmaceuticals Limited");
//        medicineType.getItems().addAll("Tablet", "Lequed", "Captual");

        try {
            Connection connection = DBCon.getConnection();

            ResultSet resultSet = connection.createStatement().executeQuery("SELECT * FROM ashamedicalhall.tbl_companies");

            companyNameList = new ArrayList<String>();
            companyIds = new ArrayList<String>();

            while (resultSet.next()) {//company_id, company_name, insert_time
                companyName.getItems().add(resultSet.getString("company_name"));

                String eat_format = resultSet.getString("company_name");
                companyNameList.add(eat_format);
                int drugType_id = resultSet.getInt("company_id");
                companyIds.add(String.valueOf(drugType_id));
            }
            System.out.println("ComapnyName name" + companyNameList);
            System.out.println("ComapnyNameIds name" + companyIds);

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println("Can't loaded the page");
        }

/////////
        ///////////////  
        try {
            Connection connection = DBCon.getConnection();

            ResultSet resultSet = connection.createStatement().executeQuery("SELECT * FROM ashamedicalhall.tbl_drugtype");

            drugTypeName = new ArrayList<String>();
            drugTypeIds = new ArrayList<String>();

            while (resultSet.next()) {//type_id, drug_type
                medicineType.getItems().add(resultSet.getString("drug_type"));

                String eat_format = resultSet.getString("drug_type");
                drugTypeName.add(eat_format);
                int drugType_id = resultSet.getInt("type_id");
                drugTypeIds.add(String.valueOf(drugType_id));
            }
            System.out.println("DrugTypeName name" + drugTypeName);
            System.out.println("DrugTypeIds name" + drugTypeIds);

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println("Can't loaded the page");
        }

/////////
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
    private void exit(ActionEvent event) {
        Stage stage = (Stage) exit.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void Save(ActionEvent event) throws SQLException {

        //        Image img = new Image("/assets/images/success5.gif");
//        Notifications notifi = Notifications.create().title("Save Successfull").text("Successfull").graphic(new ImageView(img)).hideAfter(Duration.seconds(3)).position(Pos.TOP_CENTER);
        String medicineNameInput = medicineName.getText();
        String medicinePowerInput = medicinePower.getText();
        String medicinePriceInput = medicinePrice.getText();
        String medicineQuantityInput = medicineQuantity.getText();
        String companyNameInput = (String) companyName.getSelectionModel().getSelectedItem();
       // String medicineTypeInput = (String) medicineType.getSelectionModel().getSelectedItem();
        String mfgDateInput = mfgDate.getEditor().getText();
        String expDateInput = expDate.getEditor().getText();

        String medicineTypeInput = (String) medicineType.getSelectionModel().getSelectedItem();
        int medicine_typeIndex = medicineType.getSelectionModel().getSelectedIndex();
        String medicine_type_id = drugTypeIds.get(medicine_typeIndex);

        System.out.println("Selected Index " + medicine_typeIndex);
        System.out.println("Selected ID " + medicine_type_id);
//        LocalDate mfgDateInput = mfgDate.getValue();
//        LocalDate expDateInput = expDate.getValue();

        Connection connection = DBCon.getConnection();
        String medicineSQL = "INSERT INTO ashamedicalhall.tbl_medicine (drug_name, drug_power,drug_company,drug_price,menufecture_date,expair_date,insert_date,update_date,drug_quantity,is_online,drug_type) VALUES(?,?,?,?,?,?,?,?,?,?,?)";
        //drug_id, drug_name, drug_power, drug_company, drug_price, menufecture_date, expair_date, insert_date, update_date, drug_quantity, is_online
        try {

            PreparedStatement pst = connection.prepareStatement(medicineSQL);
            pst.setString(1, medicineNameInput);
            pst.setString(2, medicinePowerInput);
            pst.setString(3, companyNameInput);
            pst.setString(4, medicinePriceInput);
            pst.setString(5, mfgDateInput);
            pst.setString(6, expDateInput);
            pst.setDate(7, getCurrentDate());
            pst.setDate(8, getCurrentDate());
            pst.setString(9, medicineQuantityInput);
            pst.setString(10, "0");
            pst.setString(11, medicine_type_id);
            //pst.setTimestamp(3, getCurrentTimeStamp());

            pst.execute();
            pst.close();
            medicineName.clear();
            medicinePower.clear();
            medicinePrice.clear();
            medicineQuantity.clear();
            companyName.getSelectionModel().clearSelection();
            medicineType.getSelectionModel().clearSelection();
            mfgDate.setValue(null);
            expDate.getEditor().clear();

            //notifi.show();
        } catch (SQLException e) {
            Logger.getLogger(AddCompaniesPopupController.class.getName()).log(Level.SEVERE, null, e);
            System.out.println(e);
//            Notifications notificaton = Notifications.create().title("Save Unsuccessful").text("Unsuccessful").graphic(null).hideAfter(Duration.seconds(3)).position(Pos.TOP_CENTER);
//            notificaton.showError();
        }

    }

    @FXML
    private void Reset(ActionEvent event) {
        medicineName.clear();
        medicinePower.clear();
        medicinePrice.clear();
        medicineQuantity.clear();
        companyName.getSelectionModel().clearSelection();
        mfgDate.setValue(null);
        expDate.getEditor().clear();

    }

}
