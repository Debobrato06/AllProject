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
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.print.PrinterJob;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.web.WebEngine;
import javafx.stage.Window;
import javafx.util.Callback;
import model.OrderPrintModel;

/**
 * FXML Controller class
 *
 * @author User
 */
public class OrderPrintController implements Initializable {
    
    private DBConn database = new DBConn();
    private Connection connection;
    private Statement statement;
    private ResultSet resultSet;
    
    @FXML
    private AnchorPane anchorPane;
    @FXML
    private Label cashReceipt;
    @FXML
    private Label totalPriceValue;
    
    @FXML
    private Label invoiceInputValue;
    @FXML
    private Label discountAmount;
    
    private final Label jobStatus = new Label();
    WebEngine engine = new WebEngine();
    Window owner;
    
    @FXML
    private TableView<OrderPrintModel> salesRecordList;
    @FXML
    private TableColumn<OrderPrintModel, String> id;
    @FXML
    private TableColumn<OrderPrintModel, String> medicineName;
    @FXML
    private TableColumn<OrderPrintModel, String> medicineQantity;
    @FXML
    private TableColumn<OrderPrintModel, String> medicinePrice;
    
    ObservableList<OrderPrintModel> oblist = FXCollections.observableArrayList();
    @FXML
    private Label salesId;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        invoiceInputValue.setVisible(false);
        salesId.setVisible(false);
        
    }
    
    private void print(Node node) {
        // Define the Job Status Message
        jobStatus.textProperty().unbind();
        jobStatus.setText("Creating a printer job...");

        // Create a printer job for the default printer
        PrinterJob job = PrinterJob.createPrinterJob();
        
        if (job != null) {
            // Show the printer job status
            jobStatus.textProperty().bind(job.jobStatusProperty().asString());

            // Print the node
            boolean printed = job.printPage(node);
            
            if (printed) {
                // End the printer job
                job.endJob();
                
            } else {
                // Write Error Message
                jobStatus.textProperty().unbind();
                jobStatus.setText("Printing failed.");
            }
        } else {
            // Write Error Message
            jobStatus.setText("Could not create a printer job.");
        }
    }
    
    void setID(int moneyReceiptinput) {
        System.out.println("Sales_ID::::" + moneyReceiptinput);
        salesId.setText(String.valueOf(moneyReceiptinput));
        salesRecordList.setItems(null);
        getDrugSales();
        getMoneyReceipt();
        print(anchorPane);
    }
    
    void setIdDetails(String moneyReceiptinput) {
        
        System.out.println("Sales_ID::::" + moneyReceiptinput);
        salesId.setText(String.valueOf(moneyReceiptinput));
        salesRecordList.setItems(null);
        getDrugSales();
        getMoneyReceipt();
        print(anchorPane);
    }
    
    private void getMoneyReceipt() {
        String orderINput = salesId.getText();
        System.out.println("invoice::::" + orderINput);
        
        String foodPaymentSqlQuery = "SELECT * FROM tbl_sale_record WHERE money_receipt_id IN (" + orderINput + ");  ";
        try {
            connection = database.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(foodPaymentSqlQuery);
            while (resultSet.next()) { //discount, net_amount

                String moneyReceiptIdInput = resultSet.getString("money_receipt_id");
                String discuntInput = resultSet.getString("discount");
                String netAmountINput = resultSet.getString("net_amount");
                
                invoiceInputValue.setText(moneyReceiptIdInput);
                totalPriceValue.setText(netAmountINput);
                discountAmount.setText(discuntInput);
                
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(OrderPrintController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    ///////////////////////
    private void getDrugSales() {
        String orderINput = salesId.getText();
        System.out.println("invoice::::" + orderINput);
        String drugSalesRecordSqlQuery = "SELECT tbl_drug.id AS drugId, tbl_drug.name AS drugName, tbl_medicine.buy_amount AS Price, tbl_sale_record.id AS salesId, tbl_sale_record.quantity AS drugQuantity FROM tbl_sale_record LEFT JOIN tbl_medicine ON tbl_medicine.id = tbl_sale_record.medicine_id LEFT JOIN tbl_drug ON tbl_drug.id = tbl_medicine.drug_id WHERE tbl_sale_record.money_receipt_id = " + orderINput + ";  ";
        
        System.out.println(drugSalesRecordSqlQuery);
        
        try {
            connection = database.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(drugSalesRecordSqlQuery);
            int sl = 1;
            while (resultSet.next()) {
                
                System.out.println("DDDDDD Id :::: " + resultSet.getString("drugId"));
                oblist.add(new OrderPrintModel(String.valueOf(sl++), resultSet.getString("drugName"), resultSet.getString("drugQuantity"), resultSet.getString("Price")));
                
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(OrderPrintController.class.getName()).log(Level.SEVERE, null, ex);
        }
        id.setCellValueFactory(new PropertyValueFactory<>("SL"));
        medicineName.setCellValueFactory(new PropertyValueFactory<>("medicineNameInput"));
        medicineQantity.setCellValueFactory(new PropertyValueFactory<>("medicineQuantity"));
        medicinePrice.setCellValueFactory(new PropertyValueFactory<>("netAmount"));
        
        salesRecordList.setRowFactory(new Callback<TableView<OrderPrintModel>, TableRow<OrderPrintModel>>() {
            @Override
            public TableRow<OrderPrintModel> call(TableView<OrderPrintModel> paramP) {
                return new TableRow<OrderPrintModel>() {
                    @Override
                    protected void updateItem(OrderPrintModel paramT, boolean paramBoolean) {
                        String style = "-fx-background-color:#ffffff;";
                        setStyle(style);
                        
                    }
                };
            }
        });
        
        salesRecordList.setItems(oblist);
    }

    /////////////////////////
}
