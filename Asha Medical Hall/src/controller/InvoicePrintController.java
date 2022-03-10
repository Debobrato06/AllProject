/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import Database.DBCon;
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
import javafx.util.Callback;
import model.invoicePrintModel;
import model.productBillDetailsModel;

/**
 * FXML Controller class
 *
 * @author Debobrato Biswas
 */
public class InvoicePrintController implements Initializable {

    @FXML
    private TableView<invoicePrintModel> productDescription;
    @FXML
    private TableColumn<invoicePrintModel, String> id;
    @FXML
    private TableColumn<invoicePrintModel, String> DescriptionInput;
    @FXML
    private TableColumn<invoicePrintModel, String> quantity;
    @FXML
    private TableColumn<invoicePrintModel, String> productPriceinput;

    @FXML
    private Label totalPriceValue;
    @FXML
    private Label vatValue;
    @FXML
    private Label netAmountValue;
    @FXML
    private Label cash;
    @FXML
    private Label change;

    @FXML
    private Label cashReceipt;

    private DBCon database = new DBCon();
    private Connection connection;
    private Statement statement;
    private ResultSet resultSet;

    private final Label jobStatus = new Label();

    @FXML
    private Label foodPayemntID;
    @FXML
    private Label invoiceInputValue;

    Label productIDText = new Label();

    ObservableList<invoicePrintModel> oblist = FXCollections.observableArrayList();
    @FXML
    private AnchorPane anchorPane;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

        foodPayemntID.setVisible(false);
        invoiceInputValue.setVisible(false);
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

    void setID(int foodPaymentIdInput) {
        System.out.println("FoodPayment_ID::::" + foodPaymentIdInput);
        foodPayemntID.setText(String.valueOf(foodPaymentIdInput));
        getInvoice();
        getFoodBill();
        print(anchorPane);

    }

    private void getInvoice() {
        String drug_payment_id = foodPayemntID.getText();
        System.out.println("invoice::::" + drug_payment_id);//drug_payment_id, 
        String catagaryNameSqlQuery = "SELECT * FROM tlb_drug_payment WHERE drug_payment_id IN (" + drug_payment_id + ");  ";
//id, client_id, invoice, amount, discount, vat, net_amount, paid_amount, return_amount, payment_date 
        try {
            connection = database.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(catagaryNameSqlQuery);
            while (resultSet.next()) { //id, name_bn, name_en, insert_by, insert_time
                String clientIdInput = resultSet.getString("client_id");
                String invoiceInput = resultSet.getString("invoice");
                String amountINput = resultSet.getString("amount");
                String vatINput = resultSet.getString("vat");
                String netAmountINput = resultSet.getString("net_amount");
                String paidAmountINput = resultSet.getString("paid_amount");
                String returnAmountINput = resultSet.getString("return_amount");

                invoiceInputValue.setText(invoiceInput);

                totalPriceValue.setText(amountINput);
                vatValue.setText(vatINput);
                netAmountValue.setText(netAmountINput);
                cash.setText(paidAmountINput);
                change.setText(returnAmountINput);

            }

        } catch (SQLException ex) {
            Logger.getLogger(NewSalesController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void getFoodBill() {
        String invoiceINput = invoiceInputValue.getText();
//drug_id, drug_name, drug_power, drug_company, drug_price, menufecture_date, 
//expair_date, insert_date, update_date, drug_quantity, is_online, drug_type    tbl_medicine
        System.out.println("invoice::::" + invoiceINput);
        String foodBillSqlQuery = "SELECT tbl_medicine.drug_name, tbl_medicine.drug_price, tbl_drug_bill.quantity FROM tbl_drug_bill LEFT JOIN tbl_medicine ON tbl_drug_bill.drug_id = tbl_medicine.drug_id WHERE invoice IN (" + invoiceINput + ");  ";
//drug_bill_id, drug_id, quantity, invoice, client_id, bill_date, is_online   tbl_drug_bill
        try {
            connection = database.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(foodBillSqlQuery);

            int sl = 1;
            while (resultSet.next()) { //id, name_bn, name_en, insert_by, insert_time

                oblist.add(new invoicePrintModel(String.valueOf(sl++), resultSet.getString("drug_name"), resultSet.getString("quantity"), resultSet.getString("drug_price")));

            }

        } catch (SQLException ex) {
            Logger.getLogger(NewSalesController.class.getName()).log(Level.SEVERE, null, ex);
        }

        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        DescriptionInput.setCellValueFactory(new PropertyValueFactory<>("description"));
        quantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        productPriceinput.setCellValueFactory(new PropertyValueFactory<>("price"));

        productDescription.setRowFactory(new Callback<TableView<invoicePrintModel>, TableRow<invoicePrintModel>>() {
            @Override
            public TableRow<invoicePrintModel> call(TableView<invoicePrintModel> paramP) {
                return new TableRow<invoicePrintModel>() {
                    @Override
                    protected void updateItem(invoicePrintModel paramT, boolean paramBoolean) {
                        String style = "-fx-background-color:#ffffff;";
                        setStyle(style);

                    }
                };
            }
        });

        productDescription.setItems(oblist);
    }

}
