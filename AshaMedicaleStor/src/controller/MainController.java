/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import Database.DBCon;
import Database.initialDatabase;
import helper.AutoCompleteTextField;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.drugBillModel;
import model.drugModel;

/**
 * FXML Controller class
 *
 * @author Debobrato Biswas
 */
public class MainController implements Initializable {

    Stage smallstage = null;
    private double xOffset = 0;
    private double yOffset = 0;

    private DBCon database = new DBCon();
    private Connection connection;
    private Statement statement;
    private ResultSet resultSet;

    ArrayList<String> drugNameList = null;
    ArrayList<String> drugNameListId = null;

    List<List<String>> productAllDetails = new ArrayList<List<String>>();
    ObservableList<drugBillModel> selectedProductItem = FXCollections.observableArrayList();

    @FXML
    private TableView<drugBillModel> DrugListTable;
    @FXML
    private TableColumn<drugBillModel, String> sl;
    @FXML
    private TableColumn<drugBillModel, String> drugNameColumn;
    @FXML
    private TableColumn<drugBillModel, String> quantityColumn;
    @FXML
    private TableColumn<drugBillModel, String> perPriceColumn;
    @FXML
    private TableColumn<drugBillModel, String> totalPriceColumn;
    @FXML
    private Button close;
    @FXML
    private TextField drugSearchTextField;
    @FXML
    private TextField quantity;

    ObservableList<drugModel> oblist = FXCollections.observableArrayList();
    @FXML
    private Label perPrice;
    @FXML
    private Label perPrice1;
    @FXML
    private Label productID;
    @FXML
    private Label totalAmount;
    @FXML
    private Label returnAmount;
    @FXML
    private TextField paidAmount;
    @FXML
    private Label invoice;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO    getCurrentTimeStampAsInvoice
        
         initialDatabase initialDatabasesInput = new initialDatabase();
        try {
            initialDatabasesInput.creatDatabse();
            initialDatabasesInput.tbl_drug();
            initialDatabasesInput.drugBillTable();
            initialDatabasesInput.tblPayment();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        }

        perPrice.setVisible(false);
        productID.setVisible(false);
        invoice.setText(getCurrentTimeStampAsInvoice());
        ///////////

        String drugNameSearchSqlQuery = "Select * from tbl_drug  ";

        try {

            connection = database.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(drugNameSearchSqlQuery);

            drugNameList = new ArrayList<String>();
            drugNameListId = new ArrayList<String>();

            while (resultSet.next()) {
                // drug_id, drug_name, drug_power, drug_price
                String drugNameInput = resultSet.getString("drug_name");
                drugNameList.add(drugNameInput);

                int drugNameListInput = resultSet.getInt("drug_id");
                drugNameListId.add(String.valueOf(drugNameListInput));
                System.out.println("DRUG ID" + drugNameListInput);

            }

            new AutoCompleteTextField().bindAutoCompletion(drugSearchTextField, 15, true, drugNameList);
            System.out.println("Drug Name" + drugNameList);

        } catch (SQLException e) {

            System.out.println(e.getMessage());
            System.out.println("englishWord:" + drugNameList);
        }

        ///////////
    }

    private static String getCurrentTimeStampAsInvoice() {

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyMMddHHmmss");
        LocalDateTime now = LocalDateTime.now();
        return dtf.format(now);

    }

    @FXML
    private void close(ActionEvent event) {
        Stage stage = (Stage) close.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void minimize(ActionEvent event) {
        Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        stage.setIconified(true);
    }

    @FXML
    private void DrugSearchTeaxtFIeld(ActionEvent event) throws SQLException {
        System.out.println("DEV");
        //drugNameList,,drugNameListId
        String searchDrugName = drugSearchTextField.getText();
        System.out.println("Drug Name ::" + searchDrugName);

        int searchIndex = drugNameList.indexOf(searchDrugName);
        String searchId = drugNameListId.get(searchIndex);

        System.out.println("Drug ID ::" + searchId);

        String translateSql = "SELECT * FROM tbl_drug WHERE drug_id IN (" + searchId + ");  ";

        try {
            connection = database.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(translateSql);

            while (resultSet.next()) {
                String durgNameInput = resultSet.getString("drug_name");
                String nounWordInput = resultSet.getString("drug_power");
                String drugPerPrice = resultSet.getString("drug_price");

                System.out.println("DRUG NAME:::" + durgNameInput);

                oblist.add(new drugModel(resultSet.getString("drug_id"), resultSet.getString("drug_name"), resultSet.getString("drug_power"), resultSet.getString("drug_price")));

                perPrice.setText(drugPerPrice);
                productID.setText(searchId);

            }

        } catch (SQLException ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void Print(ActionEvent event) throws SQLException {

        int drugBillId = saveDrugBill();
        // saveDrugPayment(drugBillId);
        int drugPaymentIdInput = saveDrugPayment(drugBillId);
        System.out.println("D-P ID :" + drugPaymentIdInput);

        try {

            FXMLLoader fXMLLoader = new FXMLLoader(getClass().getResource("/View/InvoicePrint.fxml"));
            Parent root2 = (Parent) fXMLLoader.load();
            InvoicePrintController mController = fXMLLoader.getController();

            Scene scene = new Scene(root2);
            Stage stage = new Stage();

            mController.setID(drugPaymentIdInput);
            System.out.println("DrugPayment ID:" + drugPaymentIdInput);
            stage.setScene(scene);
            stage.show();

        } catch (Exception e) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, e);
            System.out.println(e);
        }
    }

    @FXML
    private void OKButton(ActionEvent event) {

        String drugNameID = productID.getText();
        String drugNameInputValue = drugSearchTextField.getText();
        String drugQuantity = quantity.getText();
        String drugPerPrice = perPrice.getText();
        System.out.println("Drug Per Price ::" + drugPerPrice);
        String totalpriceInputValue = String.valueOf(Float.valueOf(drugPerPrice) * Float.valueOf(drugQuantity));
        System.out.println("TOTAL PRICE ::" + totalpriceInputValue);

        productDetails(drugNameID, drugNameInputValue, drugQuantity, totalpriceInputValue, drugPerPrice);

        List<String> productDetailsTemp = new ArrayList<String>();
        Double totalPrice = 0.0;

        DrugListTable.getItems().clear();

        for (int i = 0; productAllDetails.size() > i; i++) {

            productDetailsTemp = productAllDetails.get(i);
            selectedProductItem.add(new drugBillModel(String.valueOf(i + 1), productDetailsTemp.get(1), productDetailsTemp.get(2), productDetailsTemp.get(3), productDetailsTemp.get(4)));
            totalPrice += (Float.valueOf(productDetailsTemp.get(4)) * Integer.valueOf(productDetailsTemp.get(2)));

        }
//paidAmount  returnAmount totalAmount
        totalAmount.setText(String.valueOf(totalPrice));
//        Double vatAmount = 00.00;
//        vat.setText(String.valueOf(vatAmount));
//        netAmount.setText(String.valueOf(vatAmount + totalPrice));

////,idBill,name,PerPrice,quantity,totalPrice;  //quantityColumn totalPriceColumn
        sl.setCellValueFactory(new PropertyValueFactory<>("idBill"));
        drugNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        quantityColumn.setCellValueFactory(new PropertyValueFactory<>("PerPrice"));
        perPriceColumn.setCellValueFactory(new PropertyValueFactory<>("totalPrice"));
        totalPriceColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));

        DrugListTable.setItems(selectedProductItem);

    }

    private void productDetails(String drugNameID, String drugNameInputValue, String drugQuantity, String drugPerPrice, String totalpriceInputValue) {

        List<String> productDetails2 = new ArrayList<String>();
        productDetails2.add(drugNameID);
        productDetails2.add(drugNameInputValue);
        productDetails2.add(drugQuantity);
        productDetails2.add(drugPerPrice);
        productDetails2.add(totalpriceInputValue);

        productAllDetails.add(productDetails2);

        System.out.println("Drug Details : " + productAllDetails);
    }

    @FXML
    private void paidAmount(ActionEvent event) {

        String totalAmountInput = totalAmount.getText();
        String paidAmountInput = paidAmount.getText();
        String returnAmountinput = String.valueOf(Float.valueOf(paidAmountInput) - Float.valueOf(totalAmountInput));
        returnAmount.setText(returnAmountinput);
    }

    ////////////////////////////
    private int saveDrugPayment(int drugBillId) throws SQLException {
        int drugPaymentIdInput = 0;

        String drugNameID = productID.getText();
        String drugNameInputValue = drugSearchTextField.getText();
        String invoiceInput = invoice.getText();
        String totalAmountInput = totalAmount.getText();
        String paidAmountInput = paidAmount.getText();
        String returnAmountInput = returnAmount.getText();
        String Vat = "0";

        Connection connection = DBCon.getConnection();
        //payment_id, total_price, vat, paid_amount, return_amount, invoice_no, drugBillId
        String drugPayment = "INSERT INTO tbl_payment ( total_price, vat, paid_amount, return_amount, invoice_no,drugBillId) VALUES(?,?,?,?,?,?)";

        try {

            PreparedStatement pst = connection.prepareStatement(drugPayment, Statement.RETURN_GENERATED_KEYS);

            pst.setString(1, totalAmountInput);
            pst.setString(2, Vat);
            pst.setString(3, paidAmountInput);
            pst.setString(4, returnAmountInput);
            pst.setString(5, invoiceInput);
            pst.setInt(6, drugBillId);

            int affectedRows = pst.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Creating Client failed, no rows affected.");
            }

            try (ResultSet generatedKeys = pst.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    drugPaymentIdInput = (int) generatedKeys.getLong(1);
                } else {
                    throw new SQLException("Creating Client failed, no ID obtained.");
                }
            }

            pst.close();

        } catch (SQLException e) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, e);
            System.out.println(e);
        }

        return drugPaymentIdInput;
    }

    private int saveDrugBill() throws SQLException {

        int drugBillIdinput = 0;
        String invoiceInput = invoice.getText();

        Connection connection = DBCon.getConnection();

        for (int i = 0; i < productAllDetails.size(); i++) {

            List<String> productDetailstemp = new ArrayList<String>();
            productDetailstemp = productAllDetails.get(i);

            productDetailstemp.get(0); // productDetails2.add(ID);
            productDetailstemp.get(1); // productDetails2.add(NAME);
            productDetailstemp.get(2); // productDetails2.add(quantityInputValue);
            productDetailstemp.get(3); // productDetails2.add(totalpriceInputValue);
            productDetailstemp.get(4); // productDetails2.add(perpriceInputValue);
//ashamedicalstore.tbl_drugbill t;id, drug-name, drug_perprice, quantity, total_price, invoice_no
            String drugBill = "INSERT INTO tbl_drugbill ( drug_name, drug_perprice, quantity, total_price, invoice_no ) VALUES(?,?,?,?,?)";

            try {

                PreparedStatement pst = connection.prepareStatement(drugBill, Statement.RETURN_GENERATED_KEYS);
//(0drugNameID, 1 drugNameInputValue, 2 drugQuantity, 3drugPerPrice, 4 totalpriceInputValue)

                pst.setString(1, String.valueOf(productDetailstemp.get(1)));
                pst.setString(2, String.valueOf(productDetailstemp.get(4)));
                pst.setString(3, String.valueOf(productDetailstemp.get(2)));
                pst.setString(4, String.valueOf(productDetailstemp.get(3)));
                pst.setString(5, invoiceInput);

//                pst.execute();
                int affectedRows = pst.executeUpdate();

                if (affectedRows == 0) {
                    throw new SQLException("Creating Client failed, no rows affected.");
                }

                try (ResultSet generatedKeys = pst.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        drugBillIdinput = (int) generatedKeys.getLong(1);
                    } else {
                        throw new SQLException("Creating Client failed, no ID obtained.");
                    }
                }
                pst.close();

            } catch (SQLException e) {
                Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, e);
                System.out.println(e);
            }

        }
        return drugBillIdinput;

    }

    ///////////////////////
//    private void saveDrugPayment(int drugBillId) {
//           }
    @FXML
    private void addDrug(ActionEvent event) {

        if (smallstage == null) {

            try {

                FXMLLoader fXMLLoader = new FXMLLoader(getClass().getResource("/View/addDrugPopup.fxml"));
                Parent root2 = (Parent) fXMLLoader.load();
//                PrescriptionGeneratePopupController pc = fXMLLoader.getController();
//                pc.setParams(this);
                Scene scene = new Scene(root2);
                smallstage = new Stage();

                smallstage.initStyle(StageStyle.UNDECORATED);
                root2.setOnMousePressed(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        xOffset = event.getSceneX();
                        yOffset = event.getSceneY();
                    }
                });
                root2.setOnMouseDragged(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        smallstage.setX(event.getScreenX() - xOffset);
                        smallstage.setY(event.getScreenY() - yOffset);
                    }
                });

                smallstage.setScene(scene);
                smallstage.show();

            } catch (Exception e) {
                System.out.println("Can't load new windows");
            }
        } else if (smallstage.isShowing()) {
            smallstage.toFront();
        } else {
            smallstage.show();
        }

    }
}
