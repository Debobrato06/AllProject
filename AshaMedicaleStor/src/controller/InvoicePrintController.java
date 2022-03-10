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
import javafx.scene.text.Text;
import javafx.util.Callback;
import model.paymentModel;

/**
 * FXML Controller class
 *
 * @author Debobrato Biswas
 */
public class InvoicePrintController implements Initializable {
//invoicePrintModel, String

    @FXML
    private TableView<paymentModel> productDescription;
    @FXML
    private TableColumn<paymentModel, String> id;
    @FXML
    private TableColumn<paymentModel, String> DescriptionInput;
    @FXML
    private TableColumn<paymentModel, String> quantity;
    @FXML
    private TableColumn<paymentModel, String> productPriceinput;

    @FXML
    private Label totalPriceValue;
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
    private Label invoiceInputValue;

    Label productIDText = new Label();

    ObservableList<paymentModel> oblist = FXCollections.observableArrayList();
    @FXML
    private AnchorPane anchorPane;
    @FXML
    private Text invoiceNumber;
    @FXML
    private Label drugPayemntID;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

        drugPayemntID.setVisible(false);
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

    void setID(int drugPaymentIdInput) {

        System.out.println("DrugPayment_ID::::" + drugPaymentIdInput);
        drugPayemntID.setText(String.valueOf(drugPaymentIdInput));
        getInvoice();
        getFoodBill();
        print(anchorPane);
    }

    private void getInvoice() {
        String drug_payment_id = drugPayemntID.getText();
        System.out.println("Drug Payment Id::::" + drug_payment_id);
        String catagaryNameSqlQuery = "SELECT * FROM tbl_payment WHERE payment_id IN (" + drug_payment_id + ");  ";

        try {
            connection = database.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(catagaryNameSqlQuery);
            while (resultSet.next()) {

                String totalPrice = resultSet.getString("total_price");
                String invoiceInput = resultSet.getString("invoice_no");
                String paidAmountINput = resultSet.getString("paid_amount");
                String returnAmountINput = resultSet.getString("return_amount");

                invoiceNumber.setText(invoiceInput);
                totalPriceValue.setText(totalPrice);
                cash.setText(paidAmountINput);
                change.setText(returnAmountINput);

            }

        } catch (SQLException ex) {
            Logger.getLogger(InvoicePrintController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void getFoodBill() {
        String invoiceINput = invoiceNumber.getText();

        System.out.println("invoice::::" + invoiceINput);
        // id, drug_name, drug_perprice, quantity, total_price, invoice_no  tbl_drugbill
//        String foodBillSqlQuery = "SELECT tbl_medicine.drug_name, tbl_medicine.drug_price, tbl_drug_bill.quantity FROM tbl_drug_bill LEFT JOIN tbl_medicine ON tbl_drug_bill.drug_id = tbl_medicine.drug_id WHERE invoice IN (" + invoiceINput + ");  ";
        String drugBillSqlQuery = "SELECT * FROM tbl_drugbill WHERE invoice_no IN (" + invoiceINput + ");  ";

        try {
            connection = database.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(drugBillSqlQuery);

            int sl = 1;
            while (resultSet.next()) { //drug_name, drug_perprice, quantity, total_price

                oblist.add(new paymentModel(String.valueOf(sl++), resultSet.getString("drug_name"), resultSet.getString("quantity"), resultSet.getString("total_price")));

            }

        } catch (SQLException ex) {
            Logger.getLogger(InvoicePrintController.class.getName()).log(Level.SEVERE, null, ex);
        }

        id.setCellValueFactory(new PropertyValueFactory<>("sl"));
        DescriptionInput.setCellValueFactory(new PropertyValueFactory<>("drugName"));
        quantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        productPriceinput.setCellValueFactory(new PropertyValueFactory<>("price"));

        productDescription.setRowFactory(new Callback<TableView<paymentModel>, TableRow<paymentModel>>() {
            @Override
            public TableRow<paymentModel> call(TableView<paymentModel> paramP) {
                return new TableRow<paymentModel>() {
                    @Override
                    protected void updateItem(paymentModel paramT, boolean paramBoolean) {
                        String style = "-fx-background-color:#ffffff;";
                        setStyle(style);

                    }
                };
            }
        });

        productDescription.setItems(oblist);
    }
}
