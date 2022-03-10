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
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.todaySaleListModel;

/**
 * FXML Controller class
 *
 * @author User
 */
public class TodaySaleListController implements Initializable {

    private DBConn database = new DBConn();
    private Connection connection;
    private Statement statement;
    private ResultSet resultSet;

    ObservableList<todaySaleListModel> oblist = FXCollections.observableArrayList();
    @FXML
    private TableView<todaySaleListModel> todaySaleList;
    @FXML
    private TableColumn<todaySaleListModel, String> id;
    @FXML
    private TableColumn<todaySaleListModel, String> drugName;
    @FXML
    private TableColumn<todaySaleListModel, String> quantity;
    @FXML
    private TableColumn<todaySaleListModel, String> buyAmountPerDrug;
    @FXML
    private TableColumn<todaySaleListModel, String> discountAmount;
    @FXML
    private TableColumn<todaySaleListModel, String> netAmount;
    @FXML
    private TableColumn<todaySaleListModel, String> moneyReceiptNo;
    @FXML
    private Label date;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        date.setVisible(false);
        todaySaleListTable();

    }


    public void todaySaleListTable() {
        try {

            DateTimeFormatter datepatern = DateTimeFormatter.ofPattern("yyyy-MM-dd");

            LocalDateTime now = LocalDateTime.now();

            String Today = now.format(datepatern).toString();
            System.out.println("Today DATE ::::" + Today);
          
            String todaySalesListSql = "SELECT tbl_medicine.id AS medicineID,tbl_drug.name AS drugName, tbl_medicine.buy_amount AS medicineAmount, tbl_sale_record.quantity AS medicineQuantity, tbl_sale_record.discount AS discountAmount, tbl_sale_record.net_amount AS netAmount , tbl_sale_record.money_receipt_id AS moneyReceiptId FROM tbl_sale_record LEFT JOIN tbl_medicine ON tbl_sale_record.medicine_id = tbl_medicine.id LEFT JOIN tbl_drug ON tbl_medicine.drug_id =  tbl_drug.id WHERE tbl_sale_record.insert_time = '" + Today + "' ;";
            connection = database.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(todaySalesListSql);
            System.out.println("ResultSet ::::" + todaySalesListSql);
            int SL = 0;
            while (resultSet.next()) {
                oblist.add(new todaySaleListModel(resultSet.getString("medicineID"), resultSet.getString("drugName"), resultSet.getString("medicineQuantity"), resultSet.getString("medicineAmount"), resultSet.getString("discountAmount"), resultSet.getString("netAmount"), resultSet.getString("moneyReceiptId")));

            }

        } catch (SQLException ex) {
            Logger.getLogger(TodaySaleListController.class.getName()).log(Level.SEVERE, null, ex);
        }
//medicineId, drugName, drugQuantity,drugBuyAmount,discountAmountInput,netAmountInput,moneyReceiptNumber;

        id.setCellValueFactory(new PropertyValueFactory<>("medicineId"));
        drugName.setCellValueFactory(new PropertyValueFactory<>("drugName"));
        quantity.setCellValueFactory(new PropertyValueFactory<>("drugQuantity"));
        buyAmountPerDrug.setCellValueFactory(new PropertyValueFactory<>("drugBuyAmount"));
        discountAmount.setCellValueFactory(new PropertyValueFactory<>("discountAmountInput"));
        netAmount.setCellValueFactory(new PropertyValueFactory<>("netAmountInput"));
        moneyReceiptNo.setCellValueFactory(new PropertyValueFactory<>("moneyReceiptNumber"));

        //TableView. 
        todaySaleList.setItems(oblist);
    }

}
