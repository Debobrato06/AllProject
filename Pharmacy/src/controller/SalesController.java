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
import model.salesListModel;

/**
 * FXML Controller class
 *
 * @author User
 */
public class SalesController implements Initializable {

    @FXML
    private TableView<salesListModel> salesList;
    @FXML
    private TableColumn<salesListModel, String> id;
    @FXML
    private TableColumn<salesListModel, String> medicineName;
    @FXML
    private TableColumn<salesListModel, String> quantity;
    @FXML
    private TableColumn<salesListModel, String> discount;
    @FXML
    private TableColumn<salesListModel, String> netAmount;
    @FXML
    private TableColumn<salesListModel, String> moneyReceiptNo;

    ObservableList<salesListModel> oblist = FXCollections.observableArrayList();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        salesRecordListTable();
    }

    public void salesRecordListTable() {
        try {

            Connection connection = DBConn.getConnection();
            ResultSet resultSet = connection.createStatement().executeQuery("SELECT tbl_sale_record.id AS salesID, tbl_sale_record.quantity AS salesQuantity, tbl_sale_record.discount AS salesDiscount, tbl_sale_record.net_amount AS salesAmount, tbl_drug.name AS drugName, tbl_money_receipt.money_receipt_code AS moneyReceiptCode FROM  tbl_sale_record,tbl_medicine,tbl_drug,tbl_money_receipt WHERE tbl_sale_record.medicine_id = tbl_medicine.id AND tbl_medicine.drug_id = tbl_drug.id AND tbl_sale_record.money_receipt_id = tbl_money_receipt.id;");

            while (resultSet.next()) {
                String drugId = resultSet.getString("salesID");
                oblist.add(new salesListModel(resultSet.getString("salesID"), resultSet.getString("drugName"), resultSet.getString("salesQuantity"), resultSet.getString("salesDiscount"), resultSet.getString("salesAmount"), resultSet.getString("moneyReceiptCode")));

            }

        } catch (SQLException ex) {
            Logger.getLogger(MedicineListController.class.getName()).log(Level.SEVERE, null, ex);
        }
        id.setCellValueFactory(new PropertyValueFactory<>("salesId"));
        medicineName.setCellValueFactory(new PropertyValueFactory<>("medicineName"));
        quantity.setCellValueFactory(new PropertyValueFactory<>("medicineQuantity"));
        discount.setCellValueFactory(new PropertyValueFactory<>("discount"));
        netAmount.setCellValueFactory(new PropertyValueFactory<>("netAmount"));
        moneyReceiptNo.setCellValueFactory(new PropertyValueFactory<>("moneyReceiptNo"));

        //TableView. 
        salesList.setItems(oblist);
    }

}
