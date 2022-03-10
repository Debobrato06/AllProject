/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.jfoenix.controls.JFXTextField;
import database.DBConn;
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
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import model.medicineBillListModel;

/**
 * FXML Controller class
 *
 * @author User
 */
public class OrderGenerateController implements Initializable {

    List<List<String>> drugAllDetails = new ArrayList<List<String>>();

    ArrayList<String> drugNameInput = null;
    ArrayList<String> drugNameId = null;

    @FXML
    private HBox hBox;
    @FXML
    private JFXTextField drugSearch;
    @FXML
    private VBox vBox;
    @FXML
    private FlowPane flowPane;
    @FXML
    private Label invoiceNo;
    @FXML
    private JFXTextField clientName;
    @FXML
    private JFXTextField phone;
    private Label totalPrice;
    @FXML
    private Label vat;
    @FXML
    private Label netAmount;
    @FXML
    private TextField paidAmount;
    @FXML
    private Label returnAmount;
    @FXML
    private Label drugName;
    @FXML
    private Label quantity;
    @FXML
    private Label perPrice;
    @FXML
    private Button increment;
    @FXML
    private Button decriment;
    @FXML
    private Label Generic;
    @FXML
    private Label drugtype;
    @FXML
    private TextField vatInput;
    @FXML
    private Label Price;

    private int count = 0;
    private int previousProductId = 0;

    private DBConn database = new DBConn();
    private Connection connection;
    private Statement statement;
    private ResultSet resultSet;
    @FXML
    private TableView drugList;
    @FXML
    private TableColumn id;
    @FXML
    private TableColumn medicineName;
    @FXML
    private TableColumn medicineQuantity;
    @FXML
    private TableColumn medicinePerPrice;
    @FXML
    private TableColumn action;

    ObservableList<medicineBillListModel> selectedProductItem = FXCollections.observableArrayList();
    @FXML
    private Label total;

    @FXML
    private JFXTextField address;
    @FXML
    private Label power;

    @FXML
    private TextField discount;
    @FXML
    private Label medicineIdLable;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        medicineIdLable.setVisible(false);
        drugSearch();
//        try {
//
//            getDrugCategory();
//            getDrugType();
//        } catch (SQLException ex) {
//            Logger.getLogger(OrderGenerateController.class.getName()).log(Level.SEVERE, null, ex);
//        }

        invoiceNo.setText(getCurrentTimeStampAsInvoice());
    }

    private static java.sql.Timestamp getCurrentTimeStampExceptSecond() {

        java.util.Date today = new java.util.Date();
        return new java.sql.Timestamp(today.getTime());

    }

    private static String getCurrentTimeStampOnlyDate() {

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDateTime now = LocalDateTime.now();
        return dtf.format(now);

    }

    private static String getCurrentTimeStampAsInvoice() {

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyMMddHHmmss");
        LocalDateTime now = LocalDateTime.now();
        return dtf.format(now);

    }
    ///////////////////////

    private int saveSaleRecord(int moneyReceiptinput) throws SQLException {
//id, medicine_id, quantity, discount, net_amount, money_receipt_id, insert_time, insert_by, is_online
        int SaleRecordInput = 0;
        String medicineIdInput = medicineIdLable.getText();
        String quantityInput = phone.getText();
        String discountInput = discount.getText();
        String netAmountInput = netAmount.getText();
        String moneyReceiptInputNo = invoiceNo.getText();

        Connection connection = DBConn.getConnection();

        for (int i = 0; i < drugAllDetails.size(); i++) {

            List<String> drugDetailstemp = new ArrayList<String>();
            drugDetailstemp = drugAllDetails.get(i);
            //0 medicineNameID, 1  medicineNameInputValue, 2 quantityInputValue, 3 totalpriceInputValue,
//4 perpriceInputValue, 5 discountInputValue

            drugDetailstemp.get(0); // productDetails2.add(ID);
            drugDetailstemp.get(1); // productDetails2.add(NAME);
            drugDetailstemp.get(2); // productDetails2.add(quantityInputValue);
            drugDetailstemp.get(3); // productDetails2.add(totalpriceInputValue);
            drugDetailstemp.get(4); // productDetails2.add(perpriceInputValue);

            String saleRecord = "INSERT INTO pharmacy_shop.tbl_sale_record (medicine_id, quantity, discount, net_amount, money_receipt_id, insert_time, insert_by, is_online) VALUES(?,?,?,?,?,?,?,?)";

            try {

                PreparedStatement pst = connection.prepareStatement(saleRecord);

                pst.setString(1, String.valueOf(drugDetailstemp.get(0)));
                pst.setString(2, String.valueOf(drugDetailstemp.get(2)));
                pst.setString(3, discountInput);
                pst.setString(4, netAmountInput);
                pst.setInt(5, moneyReceiptinput);
                pst.setString(6, getCurrentTimeStampOnlyDate());
                pst.setString(7, "1");
                pst.setString(8, "0");

                pst.execute();
                pst.close();
                clientName.clear();

            } catch (SQLException e) {
                Logger.getLogger(OrderGenerateController.class.getName()).log(Level.SEVERE, null, e);
                System.out.println(e);
            }

        }
        return 0;

    }

    ///////////////////////
    ///////////////////////
    private int saveMoneyReceipt() throws SQLException {

        int moneyReceiptinput = 0;
        String clientNameInput = clientName.getText();
        String phoneInput = phone.getText();
        String addressInput = address.getText();
        String discountInput = discount.getText();
        String netAmountInput = netAmount.getText();
        String invoiceInput = invoiceNo.getText();

        Connection connection = DBConn.getConnection();
        try (PreparedStatement checkAccountExists = connection.prepareStatement(
                "SELECT * FROM tbl_money_receipt WHERE client_contact = ?")) {
            checkAccountExists.setString(1, phoneInput);

            try (ResultSet rs = checkAccountExists.executeQuery()) {

                if (rs.next()) {
                    while (rs.next()) {
                        moneyReceiptinput = rs.getInt("id");
                        System.out.println("Id = " + rs.getInt("id"));
                    }

                } else {
                    System.out.println("New Client Found00");
                    try {
                        String clients = "INSERT INTO pharmacy_shop.tbl_money_receipt (  shop_id, money_receipt_code, discount, net_amount, client_name, client_contact, client_address, insert_time, insert_by, status, is_online) VALUES(?,?,?,?,?,?,?,?,?,?,?)";

                        PreparedStatement pst = connection.prepareStatement(clients, Statement.RETURN_GENERATED_KEYS);
                        pst.setString(1, "1");
                        pst.setString(2, invoiceInput);
                        pst.setString(3, discountInput);
                        pst.setString(4, netAmountInput);
                        pst.setString(5, clientNameInput);
                        pst.setString(6, phoneInput);
                        pst.setString(7, addressInput);
                        pst.setString(8, getCurrentTimeStampOnlyDate());
                        pst.setString(9, "0");
                        pst.setString(10, "1");
                        pst.setString(11, "0");
//                        pst.executeUpdate();
                        int affectedRows = pst.executeUpdate();

                        if (affectedRows == 0) {
                            throw new SQLException("Creating Client failed, no rows affected.");
                        }

                        try (ResultSet generatedKeys = pst.getGeneratedKeys()) {
                            if (generatedKeys.next()) {
                                moneyReceiptinput = (int) generatedKeys.getLong(1);
                            } else {
                                throw new SQLException("Creating Client failed, no ID obtained.");
                            }
                        }

                        pst.close();
//                        clientName.clear();
//                        phoneNumber.clear();

                    } catch (SQLException e) {
                        Logger.getLogger(OrderGenerateController.class.getName()).log(Level.SEVERE, null, e);
                        System.out.println(e);
                    }
                }
                // return moneyReceiptinput;
            }

        }
        return moneyReceiptinput;
    }

    ///////////////////////
    private void getDrugCategory() throws SQLException {
        String sqlQuery = "SELECT * FROM pharmacy_shop.tbl_medicine_group t;  ";
        List<Button> buttonlist = new ArrayList<>();

        try {
            connection = database.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sqlQuery);
            while (resultSet.next()) { //id, name, shop_id, insert_time, insert_by
                String genericIdInput = resultSet.getString("id");
                String categoryNameInput = resultSet.getString("name");

                Button drugCategoryButton = new Button(categoryNameInput);
                drugCategoryButton.setMaxHeight(35);
                drugCategoryButton.setMaxWidth(100);
                drugCategoryButton.setMinWidth(100);

                drugCategoryButton.setId(resultSet.getString("id"));

                drugCategoryButton.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent e) {

                        System.out.println("Generic ID :" + genericIdInput);

                        String foodProductSqlQuery = "SELECT tbl_medicine.id AS medicineID, tbl_medicine.power AS medicinePower, tbl_medicine.quantity AS medicineQuantity, tbl_medicine.buy_amount AS medicineAmount,tbl_medicine.sale_per_quantity AS medicineSalePreQuantity, tbl_medicine.expired AS medicineExpired, tbl_medicine.remark AS medicineremark, tbl_drug.id AS drugID,tbl_drug.name AS drugName, tbl_medicine_type.name AS typeName, tbl_medicine_group.name AS genericName  FROM  tbl_medicine LEFT JOIN tbl_drug ON tbl_medicine.drug_id = tbl_drug.id LEFT JOIN tbl_medicine_type  ON  tbl_medicine.medicine_type_id = tbl_medicine_type.id   LEFT JOIN tbl_medicine_group ON tbl_drug.medicine_group_id = tbl_medicine_group.id WHERE tbl_drug.medicine_group_id = " + genericIdInput + "; ";
                        //String drugSqlQuery = "SELECT tbl_food_products.name, tbl_food_products.price, tbl_food_bill.quantity FROM tbl_food_bill LEFT JOIN tbl_food_products ON tbl_food_bill.food_product_id = tbl_food_products.id WHERE invoice IN (" + invoiceINput + ");  ";
                        List<Button> buttonlist = new ArrayList<>();
                        try {
                            connection = database.getConnection();
                            statement = connection.createStatement();
                            resultSet = statement.executeQuery(foodProductSqlQuery);
                            while (resultSet.next()) {
                                String medicineIdInput = resultSet.getString("medicineID");
                                String drug_id = resultSet.getString("drugID");
                                String drugNameInput = resultSet.getString("drugName");
                                String drugPrice = resultSet.getString("medicineAmount");
                                String drugPower = resultSet.getString("medicinePower");
                                String drugTypeInput = resultSet.getString("typeName");
                                String drugGenericName = resultSet.getString("genericName");
                                Button drugButton = new Button(drugNameInput);
                                drugButton.setMaxHeight(107);
                                drugButton.setMaxWidth(138);

                                drugButton.setId(medicineIdInput);
                                System.out.println("Medicine ::::::" + medicineIdInput);
                                drugButton.setOnAction(new EventHandler<ActionEvent>() {
                                    @Override
                                    public void handle(ActionEvent e) {

                                        System.out.println("Medicine ID:" + medicineIdInput);
                                        medicineIdLable.setText(medicineIdInput);

                                        if (previousProductId != Integer.valueOf(drugButton.getId())) {
                                            count = 0;
                                            previousProductId = 0;
                                        }
                                        drugName.setText(drugNameInput);
                                        Generic.setText(drugGenericName);
                                        drugtype.setText(drugTypeInput);
                                        perPrice.setText(drugPrice);
                                        power.setText(drugPower);

                                        quantity.setText(String.valueOf(++count));

                                        String perPriceInput = perPrice.getText();
                                        String quantityInput = quantity.getText();
                                        String price = String.valueOf(Float.valueOf(perPriceInput) * Float.valueOf(quantityInput));
                                        Price.setText(price);
                                        previousProductId = Integer.valueOf(drugButton.getId());
                                    }
                                });
                                Image img = new Image("/assets/image/11.png");
                                ImageView imgView = new ImageView(img);
                                imgView.setFitHeight(80);
                                imgView.setFitWidth(80);
                                drugButton.setGraphic(imgView);
                                drugButton.setAlignment(Pos.BOTTOM_CENTER);
                                drugButton.setStyle("-fx-background-color:White;-fx-border-color: gray;");
                                drugButton.setContentDisplay(ContentDisplay.TOP);
                                drugButton.setTextFill(Color.BLACK);

                                buttonlist.add(drugButton);

                            }

                            flowPane.getChildren().clear();
                            flowPane.setHgap(10);
                            flowPane.setVgap(10);
                            flowPane.getChildren().addAll(buttonlist);

                        } catch (SQLException ex) {
                            Logger.getLogger(OrderGenerateController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                });

                drugCategoryButton.setAlignment(Pos.CENTER);

                drugCategoryButton.setStyle("-fx-background-color:#264d73;-fx-font-weight: bold;-fx-font-size: 14px;");
                drugCategoryButton.setContentDisplay(ContentDisplay.TOP);
                drugCategoryButton.setTextFill(Color.WHITE);

                buttonlist.add(drugCategoryButton);

            }

            hBox.getChildren().clear();
            hBox.setSpacing(10);
            hBox.getChildren().addAll(buttonlist);

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            resultSet.close();
            statement.close();

        }

    }

    //////////////////
    ///////////////////////
    private void getDrugType() throws SQLException {
        String sqlQuery = "SELECT * FROM pharmacy_shop.tbl_medicine_type t;  ";
        List<Button> buttonlist = new ArrayList<>();

        try {
            connection = database.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sqlQuery);
            while (resultSet.next()) { //id, name
                String idInput = resultSet.getString("id");
                String categoryNameInput = resultSet.getString("name");

                Button drugCategoryButton = new Button(categoryNameInput);
                drugCategoryButton.setMaxHeight(35);
                drugCategoryButton.setMaxWidth(100);
                drugCategoryButton.setMinWidth(100);

                drugCategoryButton.setId(resultSet.getString("id"));

                drugCategoryButton.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent e) {

                        System.out.println("Medicine Type ID :" + idInput);

                        //String foodProductSqlQuery = "SELECT tbl_medicine.id AS medicineID, tbl_medicine.power AS medicinePower, tbl_medicine.quantity AS medicineQuantity, tbl_medicine.buy_amount AS medicineAmount,tbl_medicine.sale_per_quantity AS medicineSalePreQuantity, tbl_medicine.expired AS medicineExpired, tbl_medicine.remark AS medicineremark, tbl_drug.id AS drugID,tbl_drug.name AS drugName, tbl_medicine_type.name AS typeName, tbl_medicine_group.name AS genericName  FROM  tbl_medicine LEFT JOIN tbl_drug ON tbl_medicine.drug_id = tbl_drug.id LEFT JOIN tbl_medicine_group  ON tbl_drug.medicine_group_id = tbl_medicine_group.id  LEFT JOIN tbl_medicine_type ON tbl_medicine.medicine_type_id =  (" + idInput + " ) ORDER BY medicine_type_id;";
//SELECT tbl_medicine.id AS medicineID, tbl_medicine.power AS medicinePower, tbl_medicine.buy_amount AS medicineAmount, tbl_medicine_group.name AS genericName , tbl_medicine_type.name AS typeName, tbl_drug.id AS drugID, tbl_drug.name AS drugName FROM  tbl_drug,tbl_medicine,tbl_medicine_group,tbl_medicine_type, WHERE tbl_medicine.drug_id = tbl_drug.id  AND tbl_drug.medicine_group_id = tbl_medicine_group.id AND tbl_medicine.medicine_type_id IN (" + idInput + " ),tbl_drug,tbl_medicine_type
                        String foodProductSqlQuery = "SELECT tbl_medicine.id AS medicineIDInput, tbl_medicine.power AS medicinePower, tbl_medicine.quantity AS medicineQuantity, tbl_medicine.buy_amount AS medicineAmount,tbl_medicine.sale_per_quantity AS medicineSalePreQuantity, tbl_medicine.expired AS medicineExpired, tbl_medicine.remark AS medicineremark, tbl_drug.id AS drugID,tbl_drug.name AS drugName, tbl_medicine_type.name AS typeName, tbl_medicine_group.name AS genericName  FROM  tbl_medicine LEFT JOIN tbl_drug ON tbl_medicine.drug_id = tbl_drug.id LEFT JOIN tbl_medicine_type  ON  tbl_medicine.medicine_type_id = tbl_medicine_type.id   LEFT JOIN tbl_medicine_group ON tbl_drug.medicine_group_id = tbl_medicine_group.id WHERE tbl_medicine.medicine_type_id = " + idInput + " ORDER BY medicine_type_id; ";

                        List<Button> buttonlist = new ArrayList<>();
                        try {
                            connection = database.getConnection();
                            statement = connection.createStatement();
                            resultSet = statement.executeQuery(foodProductSqlQuery);
                            while (resultSet.next()) {
                                String medicineIdInput = resultSet.getString("medicineIDInput");
                                String drug_id = resultSet.getString("drugID");
                                String drugNameInput = resultSet.getString("drugName");
                                String drugPrice = resultSet.getString("medicineAmount");
                                String drugPower = resultSet.getString("medicinePower");
                                String drugTypeInput = resultSet.getString("typeName");
                                String drugGenericName = resultSet.getString("genericName");
                                Button drugButton = new Button(drugNameInput);
                                drugButton.setMaxHeight(107);
                                drugButton.setMaxWidth(138);

                                drugButton.setId(resultSet.getString("medicineIDInput"));
                                System.out.println("Medicine ::::::" + medicineIdInput);
                                drugButton.setOnAction(new EventHandler<ActionEvent>() {
                                    @Override
                                    public void handle(ActionEvent e) {

                                        System.out.println("Medicine ID:" + medicineIdInput);
                                        medicineIdLable.setText(medicineIdInput);

                                        if (previousProductId != Integer.valueOf(drugButton.getId())) {
                                            count = 0;
                                            previousProductId = 0;
                                        }
                                        drugName.setText(drugNameInput);
                                        Generic.setText(drugGenericName);
                                        drugtype.setText(drugTypeInput);
                                        perPrice.setText(drugPrice);

                                        quantity.setText(String.valueOf(++count));

                                        String perPriceInput = perPrice.getText();
                                        String quantityInput = quantity.getText();
                                        String price = String.valueOf(Float.valueOf(perPriceInput) * Float.valueOf(quantityInput));
                                        Price.setText(price);
                                        previousProductId = Integer.valueOf(drugButton.getId());
                                    }
                                });
                                Image img = new Image("/assets/image/11.png");
                                ImageView imgView = new ImageView(img);
                                imgView.setFitHeight(80);
                                imgView.setFitWidth(80);
                                drugButton.setGraphic(imgView);
                                drugButton.setAlignment(Pos.BOTTOM_CENTER);
                                drugButton.setStyle("-fx-background-color:White;-fx-border-color: gray;");
                                drugButton.setContentDisplay(ContentDisplay.TOP);
                                drugButton.setTextFill(Color.BLACK);

                                buttonlist.add(drugButton);

                            }

                            flowPane.getChildren().clear();
                            flowPane.setHgap(10);
                            flowPane.setVgap(10);
                            flowPane.getChildren().addAll(buttonlist);

                        } catch (SQLException ex) {
                            Logger.getLogger(OrderGenerateController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                });

                drugCategoryButton.setAlignment(Pos.CENTER);

                drugCategoryButton.setStyle("-fx-background-color:#264d73;-fx-font-weight: bold;-fx-font-size: 14px;");
                drugCategoryButton.setContentDisplay(ContentDisplay.TOP);
                drugCategoryButton.setTextFill(Color.WHITE);

                buttonlist.add(drugCategoryButton);

            }

            vBox.getChildren().clear();
            vBox.setSpacing(10);
            vBox.getChildren().addAll(buttonlist);

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            resultSet.close();
            statement.close();

        }

    }

    //////////////////
    private void drugSearch() {
        try {
            Connection connection = DBConn.getConnection();

            ResultSet resultSet = connection.createStatement().executeQuery("SELECT name,id FROM pharmacy_shop.tbl_drug");
            drugNameInput = new ArrayList<String>();
            drugNameId = new ArrayList<String>();
//id, name, medicine_group_id, company_id, insert_by, insert_time, last_update
            while (resultSet.next()) {
                String product_name = resultSet.getString("name");
                drugNameInput.add(product_name);

                int productId = resultSet.getInt("id");
                drugNameId.add(String.valueOf(productId));

            }

            new AutoCompleteTextField().bindAutoCompletion(drugSearch, 15, true, drugNameInput);
            System.out.println("Drug  name" + drugNameInput);
            System.out.println("Drug id" + drugNameId);

        } catch (SQLException e) {

            System.out.println(e.getMessage());

        }

    }

    @FXML
    private void drugSearchAction(ActionEvent event) {
        String searchDrugName = drugSearch.getText();
        drugNameInput.add(searchDrugName);
        System.out.println("Drug ::" + searchDrugName);

        int searchDrugNameIndex = drugNameInput.indexOf(searchDrugName);
        String searchDrugNameId = drugNameId.get(searchDrugNameIndex);

        System.out.println("Drug ID::" + searchDrugNameId);

        //LEFT JOIN tbl_drug ON tbl_medicine.drug_id = tbl_drug.id LEFT JOIN tbl_medicine_type  ON  tbl_medicine.medicine_type_id = tbl_medicine_type.id   LEFT JOIN tbl_medicine_group ON tbl_drug.medicine_group_id = tbl_medicine_group.id WHERE tbl_medicine.medicine_type_id = " + idInput + " ORDER BY medicine_type_id; ";                                
        // String drugSearchSqlQuery = "SELECT tbl_medicine.id AS medicineID, tbl_medicine.power AS medicinePower, tbl_medicine.quantity AS medicineQuantity, tbl_medicine.buy_amount AS medicineAmount,tbl_medicine.sale_per_quantity AS medicineSalePreQuantity, tbl_medicine.expired AS medicineExpired, tbl_medicine.remark AS medicineremark, tbl_drug.id AS drugID, tbl_drug.name AS drugName, tbl_medicine_type.name AS typeName, tbl_medicine_group.name AS genericName FROM  tbl_medicine  LEFT JOIN tbl_drug ON tbl_medicine.drug_id = tbl_drug.id LEFT JOIN tbl_medicine_group ON  tbl_medicine_group.id = tbl_drug.medicine_group_id LEFT JOIN tbl_medicine_type ON tbl_medicine_type.id = tbl_medicine.medicine_type_id WHERE tbl_medicine.drug_id = " + searchDrugNameId + " ORDER BY drug_id ; ";
        List<Button> buttonlist = new ArrayList<>();

        try {
            connection = database.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT tbl_medicine.id AS medicineID, tbl_medicine.power AS medicinePower, tbl_medicine.quantity AS medicineQuantity, tbl_medicine.buy_amount AS medicineAmount,tbl_medicine.sale_per_quantity AS medicineSalePreQuantity, tbl_medicine.expired AS medicineExpired, tbl_medicine.remark AS medicineremark, tbl_drug.id AS drugID, tbl_drug.name AS drugName, tbl_medicine_type.name AS typeName, tbl_medicine_group.name AS genericName FROM  tbl_medicine  LEFT JOIN tbl_drug ON tbl_medicine.drug_id = tbl_drug.id LEFT JOIN tbl_medicine_group ON  tbl_medicine_group.id = tbl_drug.medicine_group_id LEFT JOIN tbl_medicine_type ON tbl_medicine_type.id = tbl_medicine.medicine_type_id WHERE tbl_medicine.drug_id = " + searchDrugNameId + " ORDER BY drug_id ; ");
            while (resultSet.next()) {

                String medicineIdInput = resultSet.getString("drugID");
                String drug_id = resultSet.getString("drugID");
                String drugNameInput = resultSet.getString("drugName");
                String drugPrice = resultSet.getString("medicineAmount");
                String drugPower = resultSet.getString("medicinePower");
                String drugTypeInput = resultSet.getString("typeName");
                String drugGenericName = resultSet.getString("genericName");
                Button drugButton = new Button(drugNameInput);
                drugButton.setMaxHeight(107);
                drugButton.setMaxWidth(138);

                drugButton.setId(drug_id.toString());

                drugButton.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent e) {

                        System.out.println("Medicine_ID:" + medicineIdInput);
                        medicineIdLable.setText(medicineIdInput);

                        if (previousProductId != Integer.valueOf(drugButton.getId())) {
                            count = 0;
                            previousProductId = 0;
                        }
                        drugName.setText(drugNameInput);
                        Generic.setText(drugGenericName);
                        drugtype.setText(drugTypeInput);
                        perPrice.setText(drugPrice);
                        power.setText(drugPower);
                        quantity.setText(String.valueOf(++count));

                        String perPriceInput = perPrice.getText();
                        String quantityInput = quantity.getText();
                        String price = String.valueOf(Float.valueOf(perPriceInput) * Float.valueOf(quantityInput));
                        Price.setText(price);
                        previousProductId = Integer.valueOf(drugButton.getId());
                    }
                });
                Image img = new Image("/assets/image/11.png");
                ImageView imgView = new ImageView(img);
                imgView.setFitHeight(80);
                imgView.setFitWidth(80);
                drugButton.setGraphic(imgView);
                drugButton.setAlignment(Pos.BOTTOM_CENTER);
                drugButton.setStyle("-fx-background-color:White;-fx-border-color: gray;");
                drugButton.setContentDisplay(ContentDisplay.TOP);
                drugButton.setTextFill(Color.BLACK);

                buttonlist.add(drugButton);

            }

            flowPane.getChildren().clear();
            flowPane.setHgap(10);
            flowPane.setVgap(10);
            flowPane.getChildren().addAll(buttonlist);

        } catch (SQLException ex) {
            System.out.println("SQLException:" + ex);
            Logger.getLogger(OrderGenerateController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void saveButton(ActionEvent event) {
        try {
//            drugList.setItems(null);
            int moneyReceiptinput = saveMoneyReceipt();
            saveSaleRecord(moneyReceiptinput);

            try {

                FXMLLoader fXMLLoader = new FXMLLoader(getClass().getResource("/view/OrderPrint.fxml"));
                Parent root2 = (Parent) fXMLLoader.load();
                OrderPrintController mController = fXMLLoader.getController();

                Scene scene = new Scene(root2);
                Stage stage = new Stage();

                mController.setID(moneyReceiptinput);

                System.out.println("Money Receipt ID:" + moneyReceiptinput);
                stage.setScene(scene);
                stage.show();

            } catch (Exception e) {
                Logger.getLogger(OrderGenerateController.class.getName()).log(Level.SEVERE, null, e);
                System.out.println(e);
            }
        } catch (SQLException ex) {
            Logger.getLogger(OrderGenerateController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void okButton(ActionEvent event) {

        String medicineNameID = medicineIdLable.getText();
        String medicineNameInputValue = drugName.getText();
        String quantityInputValue = quantity.getText();
        String perpriceInputValue = perPrice.getText();
        String totalpriceInputValue = Price.getText();
        String discountInputValue = discount.getText();

        productDetails(medicineNameID, medicineNameInputValue, quantityInputValue, totalpriceInputValue, perpriceInputValue, discountInputValue);

        List<String> productDetailsTemp = new ArrayList<String>();
        Double totalPrice = 0.0;

        drugList.getItems().clear();
        for (int i = 0; drugAllDetails.size() > i; i++) {

            Button btn = new Button();
            btn.setMaxWidth(13);
            btn.setMaxHeight(13);
            btn.setLayoutX(0);
            btn.setStyle("-fx-background-color: red;");
            Image image1 = new Image("/assets/image/211652-128.png");
            ImageView img1 = new ImageView(image1);
            img1.setFitWidth(13);
            img1.setFitHeight(13);
            btn.setGraphic(img1);
            btn.setId(medicineNameID);

            btn.setOnAction(new EventHandler<ActionEvent>() {

                @Override
                public void handle(ActionEvent t) {
                    System.out.println("Button Clicked ID : " + btn.getId());

                    List<String> productDetailsTemp = new ArrayList<String>();
                    Double totalPrice1 = 0.0;
                    for (int i = 0; drugAllDetails.size() > i; i--) {
                        productDetailsTemp = drugAllDetails.get(i);
                        selectedProductItem.remove(i);
                        totalPrice1 += (Float.valueOf(productDetailsTemp.get(4)) * Integer.valueOf(productDetailsTemp.get(2)));
                        System.out.println("Button Clicked Price : " + totalPrice1);
                    }

                    total.setText(String.valueOf(totalPrice1));
                    Double vatAmount = totalPrice1 * 0.15;
                    vat.setText(String.valueOf(vatAmount));
                    netAmount.setText(String.valueOf(vatAmount + totalPrice1));

                }

            });

            productDetailsTemp = drugAllDetails.get(i);
            selectedProductItem.add(new medicineBillListModel(String.valueOf(i + 1), productDetailsTemp.get(1), productDetailsTemp.get(2), productDetailsTemp.get(3), btn));
            totalPrice += (Float.valueOf(productDetailsTemp.get(4)) * Integer.valueOf(productDetailsTemp.get(2)));

        }

        total.setText(String.valueOf(totalPrice));
        Double vatAmount = totalPrice * 0.15;
        vat.setText(String.valueOf(vatAmount));
        netAmount.setText(String.valueOf(vatAmount + totalPrice));

        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        medicineName.setCellValueFactory(new PropertyValueFactory<>("drugNameValue"));
        medicineQuantity.setCellValueFactory(new PropertyValueFactory<>("quantityValue"));
        medicinePerPrice.setCellValueFactory(new PropertyValueFactory<>("priceValue"));
        action.setCellValueFactory(new PropertyValueFactory<>("action"));

        drugList.setItems(selectedProductItem);

    }

    @FXML
    private void increment(ActionEvent event) {
        quantity.setText(String.valueOf(++count));
        String perPriceInput = perPrice.getText();
        String quantityInput = quantity.getText();
        String price = String.valueOf(Float.valueOf(perPriceInput) * Float.valueOf(quantityInput));
        Price.setText(price);
    }

    @FXML
    private void decriment(ActionEvent event) {
        quantity.setText(String.valueOf(--count));
        String perPriceInput = perPrice.getText();
        String quantityInput = quantity.getText();
        String price = String.valueOf(Float.valueOf(perPriceInput) * Float.valueOf(quantityInput));
        Price.setText(price);
    }

    private void productDetails(String medicineNameID, String medicineNameInputValue, String quantityInputValue, String totalpriceInputValue, String perpriceInputValue, String discountInputValue) {
//0 medicineNameID, 1  medicineNameInputValue, 2 quantityInputValue, 3 totalpriceInputValue,
//4 perpriceInputValue, 5 discountInputValue
        List<String> productDetails2 = new ArrayList<String>();
        productDetails2.add(medicineNameID);
        productDetails2.add(medicineNameInputValue);
        productDetails2.add(quantityInputValue);
        productDetails2.add(totalpriceInputValue);
        productDetails2.add(perpriceInputValue);
        productDetails2.add(discountInputValue);

        drugAllDetails.add(productDetails2);

        System.out.println("Product Details : " + drugAllDetails);

    }

    @FXML
    private void vatInput(ActionEvent event) {
        String vatInputValue = vatInput.getText();
        Float vatValue = Float.valueOf(vatInputValue) / 100;
        System.out.println("vatValue" + vatValue);

        String totalPriceString = total.getText();
        Float tottalPriceFloat = Float.valueOf(totalPriceString);

        int vatAmount = (int) (tottalPriceFloat * vatValue);
        vat.setText(String.valueOf(vatAmount));
        int netAmountUpdate = (int) (vatAmount + tottalPriceFloat);
        netAmount.setText(String.valueOf(netAmountUpdate));

        String PaidAmountInput = paidAmount.getText();
        String returnAmountOutput = String.valueOf(Float.valueOf(PaidAmountInput) - Float.valueOf(netAmountUpdate));
        returnAmount.setText(returnAmountOutput);
    }

    @FXML
    private void paidAmount(ActionEvent event) {
        String netAmountInput = netAmount.getText();
        String paidAmountInput = paidAmount.getText();
        String returnAmountinput = String.valueOf(Float.valueOf(paidAmountInput) - Float.valueOf(netAmountInput));
        returnAmount.setText(returnAmountinput);
    }

    @FXML
    private void discount(ActionEvent event) {
        String discountInputValue = discount.getText();
        Float discountValue = Float.valueOf(discountInputValue);
        System.out.println("Discount Value :::" + discountValue);

        String totalPriceString = netAmount.getText();
        Float tottalPriceFloat = Float.valueOf(totalPriceString);

        int netAmountUpdate = (int) (tottalPriceFloat - discountValue);
        netAmount.setText(String.valueOf(netAmountUpdate));

        String PaidAmountInput = paidAmount.getText();
        String returnAmountOutput = String.valueOf(Float.valueOf(PaidAmountInput) - Float.valueOf(netAmountUpdate));
        returnAmount.setText(returnAmountOutput);
    }

}
