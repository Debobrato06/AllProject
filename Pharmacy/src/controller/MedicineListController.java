/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import database.DBConn;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.medicineListModel;
import model.rackJoinMedicineGroupModel;

/**
 * FXML Controller class
 *
 * @author User
 */
public class MedicineListController implements Initializable {

    @FXML
    private TableView<medicineListModel> medicineList;
    @FXML
    private TableColumn<medicineListModel, String> id;
    @FXML
    private TableColumn<medicineListModel, String> drugName;
    @FXML
    private TableColumn<medicineListModel, String> power;
    @FXML
    private TableColumn<medicineListModel, String> medicineType;
    @FXML
    private TableColumn<medicineListModel, String> quantity;
    @FXML
    private TableColumn<medicineListModel, String> buyAmount;
    @FXML
    private TableColumn<medicineListModel, String> salePerQuantity;
    @FXML
    private TableColumn<medicineListModel, String> expired;
    @FXML
    private TableColumn<medicineListModel, String> remark;

    ObservableList<medicineListModel> oblist = FXCollections.observableArrayList();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        medicineListTable();
    }

    public void medicineListTable() {
        try {

            Connection connection = DBConn.getConnection();
            ResultSet resultSet = connection.createStatement().executeQuery("SELECT tbl_medicine.id AS medicineID, tbl_medicine.power AS medicinePower, tbl_medicine.quantity AS medicineQuantity, tbl_medicine.buy_amount AS medicineAmount, tbl_medicine.sale_per_quantity AS medicineSalePreQuantity, tbl_medicine.expired AS medicineExpired, tbl_medicine.remark AS medicineremark, tbl_drug.name AS drugName, tbl_medicine_type.name AS typeName FROM  tbl_medicine,tbl_drug,tbl_medicine_type  WHERE tbl_drug.id = tbl_medicine.drug_id AND tbl_medicine_type.id = tbl_medicine.medicine_type_id;");

            while (resultSet.next()) {
                System.out.println("DRUG NAME ::" + resultSet.getString("drugName"));
                oblist.add(new medicineListModel(resultSet.getString("medicineID"), resultSet.getString("drugName"), resultSet.getString("medicinePower"), resultSet.getString("typeName"), resultSet.getString("medicineQuantity"), resultSet.getString("medicineAmount"), resultSet.getString("medicineSalePreQuantity"), resultSet.getString("medicineExpired"), resultSet.getString("medicineremark")));
            }

        } catch (SQLException ex) {
            Logger.getLogger(MedicineListController.class.getName()).log(Level.SEVERE, null, ex);
        }

        id.setCellValueFactory(new PropertyValueFactory<>("medicineId"));
        drugName.setCellValueFactory(new PropertyValueFactory<>("drugName"));
        power.setCellValueFactory(new PropertyValueFactory<>("drugPower"));
        medicineType.setCellValueFactory(new PropertyValueFactory<>("medicineTypeName"));
        quantity.setCellValueFactory(new PropertyValueFactory<>("drugQuantity"));
        buyAmount.setCellValueFactory(new PropertyValueFactory<>("drugBuyAmount"));
        salePerQuantity.setCellValueFactory(new PropertyValueFactory<>("drugSalePerQuantity"));
        expired.setCellValueFactory(new PropertyValueFactory<>("drugExpired"));
        remark.setCellValueFactory(new PropertyValueFactory<>("drugRemark"));

        //TableView. 
        medicineList.setItems(oblist);
    }

}
