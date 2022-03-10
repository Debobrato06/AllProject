/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import model.productBillDetailsModel;
import Database.DBCon;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
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
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Debobrato Biswas
 */
public class NewSalesController implements Initializable {

    List<List<String>> productAllDetails = new ArrayList<List<String>>();

    ObservableList<productBillDetailsModel> selectedProductItem = FXCollections.observableArrayList();

    ArrayList<String> drugNameInput = null;
    ArrayList<String> drugNameId = null;

    ArrayList<String> clientPhoneInput = null;
    ArrayList<String> clientId = null;

    private int count = 0;
    private int previousProductId = 0;

    private DBCon database = new DBCon();
    private Connection connection;
    private Statement statement;
    private ResultSet resultSet;

    @FXML
    private BorderPane borderpanfoodview;
    @FXML
    private ScrollPane scrollPane;
    //Drug show part
    @FXML
    private Label drugCategory;
    @FXML
    private HBox drugCategoryHbox;
    @FXML
    private HBox drugHBox;
    @FXML
    private JFXTextField filterDrugItems;
    @FXML
    private VBox drugShowVBox;

    HBox hboxx = new HBox();

    ///Bill part
    @FXML
    private Button incrementButton;
    @FXML
    private Button decrementButton;
    @FXML
    private Label totalPrice;
    @FXML
    private TextField vatInput;
    @FXML
    private Label total;
    @FXML
    private Label vat;
    @FXML
    private Label netAmount;
    @FXML
    private Label returnAmount;
    @FXML
    private TextField paidAmount;
    /// Table view
    @FXML
    private TableView<productBillDetailsModel> drugOrderTable;//drugOrderTable,sl,name.quantityNumber.price,Action
    @FXML
    private TableColumn<productBillDetailsModel, String> sl;
    @FXML
    private TableColumn<productBillDetailsModel, String> name;
    @FXML
    private TableColumn<productBillDetailsModel, String> quantityNumber;
    @FXML
    private TableColumn<productBillDetailsModel, String> price;
//    private TableColumn<productBillDetailsModel, String> Action;
    //invoice & client details
    @FXML
    private Label invoiceNo;
    @FXML
    private JFXTextField clientName;
    @FXML
    private JFXTextField phoneNumber;
    @FXML
    private JFXTextField clientAddress;
    ///Drug order part
    @FXML
    private Label drugName;
    @FXML
    private Label perDrugPrice;
    @FXML
    private TextField addQuantityTextfield;

    ///
    @FXML
    private Label productId;

    ///
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            // TODO
            getDrugCatagory();
        } catch (SQLException ex) {
            Logger.getLogger(NewSalesController.class.getName()).log(Level.SEVERE, null, ex);
        }
        drugShowVBox.getChildren().addAll(hboxx);
        drugShowVBox.setSpacing(15);
        drugShowVBox.setStyle("-fx-background-color:White;");

        invoiceNo.setText(getCurrentTimeStampAsInvoice());

    }

    private static String getCurrentTimeStampAsInvoice() {

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyMMddHHmmss");
        LocalDateTime now = LocalDateTime.now();
        return dtf.format(now);

    }

    private static java.sql.Date getCurrentDate() {
        java.util.Date today = new java.util.Date();
        return new java.sql.Date(today.getTime());

    }

    private static String getCurrentTimeStamp() {

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm");
        LocalDateTime now = LocalDateTime.now();
        return dtf.format(now);

    }

    @FXML
    private void savePrint(ActionEvent event) throws IOException, SQLException {

        int clientID = saveClient();
        int foodPaymentIdInput = saveDrugPayment(clientID);
        saveDrugBill(clientID);
        System.out.println("C ID :" + clientID);

        saveClient();

        try {

            FXMLLoader fXMLLoader = new FXMLLoader(getClass().getResource("/view/InvoicePrint.fxml"));
            Parent root2 = (Parent) fXMLLoader.load();
            InvoicePrintController mController = fXMLLoader.getController();

            Scene scene = new Scene(root2);
            Stage stage = new Stage();

            mController.setID(foodPaymentIdInput);

            System.out.println("FoodPayment ID:" + foodPaymentIdInput);
            stage.setScene(scene);
            stage.show();

        } catch (Exception e) {
            Logger.getLogger(NewSalesController.class.getName()).log(Level.SEVERE, null, e);
            System.out.println(e);
        }

//        Parent root = FXMLLoader.load(getClass().getResource("/view/InvoicePrint.fxml"));
//
//        Scene scene = new Scene(root);
//        Stage stage = new Stage();
//
//        stage.setScene(scene);
//        stage.show();
    }
//addQuantityTextfield  perDrugPrice

    @FXML
    private void incrementButton(ActionEvent event) {
        addQuantityTextfield.setText(String.valueOf(++count));
        String perPrice = perDrugPrice.getText();
        String quantityInput = addQuantityTextfield.getText();
        String price = String.valueOf(Float.valueOf(perPrice) * Float.valueOf(quantityInput));
        totalPrice.setText(price);
    }

    @FXML
    private void decrementButton(ActionEvent event) {
        // int counting = count--;
        addQuantityTextfield.setText(String.valueOf(--count));
        String perPrice = perDrugPrice.getText();
        String quantityInput = addQuantityTextfield.getText();
        String price = String.valueOf(Float.valueOf(perPrice) * Float.valueOf(quantityInput));
        totalPrice.setText(price);
    }

    @FXML
    private void OkButton(ActionEvent event) {

        String productNameID = productId.getText();
        String productNameInputValue = drugName.getText();
        String quantityInputValue = addQuantityTextfield.getText();
        String perpriceInputValue = perDrugPrice.getText();
        String totalpriceInputValue = totalPrice.getText();

        productDetails(productNameID, productNameInputValue, quantityInputValue, totalpriceInputValue, perpriceInputValue);

        List<String> productDetailsTemp = new ArrayList<String>();
        Double totalPrice = 0.0;

        drugOrderTable.getItems().clear();

        for (int i = 0; productAllDetails.size() > i; i++) {

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
            btn.setId(productNameID);
            //String ids = btn.getId();

            btn.setOnAction(new EventHandler<ActionEvent>() {

                @Override
                public void handle(ActionEvent t) {
                    System.out.println("Button Clicked ID : " + btn.getId());
                    // openPopUpWindow(btn.getId());

                    List<String> productDetailsTemp = new ArrayList<String>();
                    Double totalPrice1 = 0.0;
                    for (int i = 0; productAllDetails.size() > i; i--) {
                        productDetailsTemp = productAllDetails.get(i);
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

            productDetailsTemp = productAllDetails.get(i);
            selectedProductItem.add(new productBillDetailsModel(String.valueOf(i + 1), productDetailsTemp.get(1), productDetailsTemp.get(2), productDetailsTemp.get(3), btn));
            totalPrice += (Float.valueOf(productDetailsTemp.get(4)) * Integer.valueOf(productDetailsTemp.get(2)));

        }

        total.setText(String.valueOf(totalPrice));
        Double vatAmount = totalPrice * 0.15;
        vat.setText(String.valueOf(vatAmount));
        netAmount.setText(String.valueOf(vatAmount + totalPrice));
//,sl,name.quantityNumber.price,Action
        sl.setCellValueFactory(new PropertyValueFactory<>("id"));
        name.setCellValueFactory(new PropertyValueFactory<>("productNameValue"));
        quantityNumber.setCellValueFactory(new PropertyValueFactory<>("quantityValue"));
        price.setCellValueFactory(new PropertyValueFactory<>("priceValue"));
        //Action.setCellValueFactory(new PropertyValueFactory<>("action"));

        drugOrderTable.setItems(selectedProductItem);
    }

    ///////////////Drug Catagory
    private void getDrugCatagory() throws SQLException {
        String sqlQuery = "SELECT * FROM ashamedicalhall.tbl_drugtype t;  ";
        List<Button> buttonlist = new ArrayList<>();

        try {
            connection = database.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sqlQuery);
            while (resultSet.next()) { //type_id, drug_type
                String idInput = resultSet.getString("type_id");
                String productNameInput = resultSet.getString("drug_type");

                Button DrugCatagoryButton = new Button(productNameInput);
                DrugCatagoryButton.setMaxHeight(35);
                DrugCatagoryButton.setMaxWidth(100);
                DrugCatagoryButton.setMinWidth(100);

                DrugCatagoryButton.setId(resultSet.getString("type_id"));

                DrugCatagoryButton.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent e) {

                        System.out.println("ID :" + idInput);

                        drugNameId = new ArrayList<String>();//

                        String drugSqlQuery = "SELECT * FROM ashamedicalhall.tbl_medicine WHERE drug_type IN (" + idInput + ");  ";
                        List<Button> buttonlist = new ArrayList<>();
                        try {
                            //drug_id, drug_name, drug_power, drug_company, drug_price, menufecture_date, 
                            //expair_date, insert_date, update_date, drug_quantity, is_online, drug_type                           
                            connection = database.getConnection();
                            statement = connection.createStatement();
                            resultSet = statement.executeQuery(drugSqlQuery);
                            while (resultSet.next()) {
                                int product_id = resultSet.getInt("drug_id");
                                drugNameId.add(String.valueOf(product_id));

                                String productNameInput = resultSet.getString("drug_name");
                                String productPrice = resultSet.getString("drug_price");
                                String productTypeInput = resultSet.getString("drug_type");
                                Button DrugButton = new Button(productNameInput);
                                DrugButton.setMaxHeight(107);
                                DrugButton.setMaxWidth(138);

                                DrugButton.setId(resultSet.getString("drug_id"));

                                DrugButton.setOnAction(new EventHandler<ActionEvent>() {
                                    @Override
                                    public void handle(ActionEvent e) {

                                        System.out.println("Product ID:" + product_id);
                                        productId.setText(String.valueOf(product_id));
                                        ///////////////type_id, drug_type
                                        String catagaryNameSqlQuery = "SELECT * FROM tbl_drugtype WHERE type_id IN (" + productTypeInput + ");  ";

                                        try {
                                            connection = database.getConnection();
                                            statement = connection.createStatement();
                                            resultSet = statement.executeQuery(catagaryNameSqlQuery);
                                            while (resultSet.next()) { //id, name_bn, name_en, insert_by, insert_time
                                                //  String idInputFoodItem = resultSet.getString("id");
                                                String productNameInput = resultSet.getString("drug_type");

                                                drugCategory.setText(productNameInput);
                                            }

                                        } catch (SQLException ex) {
                                            Logger.getLogger(NewSalesController.class.getName()).log(Level.SEVERE, null, ex);
                                        }
                                        ////////////// 
                                        if (previousProductId != Integer.valueOf(DrugButton.getId())) {
                                            count = 0;
                                            previousProductId = 0;
                                        }
                                        drugName.setText(productNameInput);
                                        perDrugPrice.setText(productPrice);
//
                                        addQuantityTextfield.setText(String.valueOf(++count));
//
                                        String perPrice = perDrugPrice.getText();
                                        String quantityInput = addQuantityTextfield.getText();
                                        String price = String.valueOf(Float.valueOf(perPrice) * Float.valueOf(quantityInput));
                                        totalPrice.setText(price);
                                        previousProductId = Integer.valueOf(DrugButton.getId());
                                    }
                                });
//                                Image img = new Image("/assets/image/19.jpg");
//                                ImageView imgView = new ImageView(img);
//                                imgView.setFitHeight(80);
//                                imgView.setFitWidth(80);
//                                DrugButton.setGraphic(imgView);
                                DrugButton.setAlignment(Pos.BOTTOM_CENTER);
                                DrugButton.setStyle("-fx-background-color:#33ffff;-fx-border-color: gray;");
                                DrugButton.setContentDisplay(ContentDisplay.TOP);
                                DrugButton.setTextFill(Color.BLACK);

                                buttonlist.add(DrugButton);

                            }

                            hboxx.getChildren().clear();
                            hboxx.setSpacing(10);
                            hboxx.setPadding(Insets.EMPTY);
                            hboxx.getChildren().addAll(buttonlist);

                        } catch (SQLException ex) {
                            Logger.getLogger(NewSalesController.class.getName()).log(Level.SEVERE, null, ex);
                        }

                    }
                });
                DrugCatagoryButton.setAlignment(Pos.CENTER);

                DrugCatagoryButton.setStyle("-fx-background-color:#264d73;-fx-font-weight: bold;-fx-font-size: 14px;");
                DrugCatagoryButton.setContentDisplay(ContentDisplay.TOP);
                DrugCatagoryButton.setTextFill(Color.WHITE);

                buttonlist.add(DrugCatagoryButton);

            }

            drugHBox.getChildren().clear();
            drugHBox.setSpacing(10);
            drugHBox.getChildren().addAll(buttonlist);

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            resultSet.close();
            statement.close();

        }

    }

    //////////////
    private void productDetails(String productNameID, String productNameInputValue, String quantityInputValue, String totalpriceInputValue, String perpriceInputValue) {

        List<String> productDetails2 = new ArrayList<String>();
        productDetails2.add(productNameID);
        productDetails2.add(productNameInputValue);
        productDetails2.add(quantityInputValue);
        productDetails2.add(totalpriceInputValue);
        productDetails2.add(perpriceInputValue);

        productAllDetails.add(productDetails2);

        System.out.println("Product Details : " + productAllDetails);

    }

    @FXML
    private void returnAmount(ActionEvent event) {
        String netAmountInput = netAmount.getText();
        String paidAmountInput = paidAmount.getText();
        String returnAmountinput = String.valueOf(Float.valueOf(paidAmountInput) - Float.valueOf(netAmountInput));
        returnAmount.setText(returnAmountinput);
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

    private int saveClient() throws SQLException {

        int clientIdinput = 0;
        String PatientName = clientName.getText();
        String Phone = phoneNumber.getText();
        String Address = clientAddress.getText();

        Connection connection = DBCon.getConnection();
        try (PreparedStatement checkAccountExists = connection.prepareStatement(
                "SELECT * FROM tbl_client WHERE client_phone = ?")) {
            checkAccountExists.setString(1, Phone);

            try (ResultSet rs = checkAccountExists.executeQuery()) {

                if (rs.next()) {
                    while (rs.next()) {
                        clientIdinput = rs.getInt("client_id");
                        System.out.println("Id = " + rs.getInt("client_id"));
                    }

                } else {
                    System.out.println("New Client Found00");
                    try {//client_id, client_name, client_phone, client_address, is_online
                        String clients = "INSERT INTO tbl_client (  client_name, client_phone, client_address, is_online) VALUES(?,?,?,?)";

                        PreparedStatement pst = connection.prepareStatement(clients, Statement.RETURN_GENERATED_KEYS);
                        pst.setString(1, PatientName);
                        pst.setString(2, Phone);
                        pst.setString(3, Address);
                        pst.setString(4, "0");
                        int affectedRows = pst.executeUpdate();

                        if (affectedRows == 0) {
                            throw new SQLException("Creating Client failed, no rows affected.");
                        }

                        try (ResultSet generatedKeys = pst.getGeneratedKeys()) {
                            if (generatedKeys.next()) {
                                clientIdinput = (int) generatedKeys.getLong(1);
                            } else {
                                throw new SQLException("Creating Client failed, no ID obtained.");
                            }
                        }

                        pst.close();
                        clientName.clear();
                        phoneNumber.clear();
                        clientAddress.clear();

                    } catch (SQLException e) {
                        Logger.getLogger(NewSalesController.class.getName()).log(Level.SEVERE, null, e);
                        System.out.println(e);
                    }
                }
                return clientIdinput;
            }

        }
    }

    ///////////////////////////
    private int saveDrugPayment(int clientID) throws SQLException {
        int drugPaymentIdInput = 0;

        String invoiceInput = invoiceNo.getText();
        String totalAmountInput = total.getText();
        String vatInput = vat.getText();
        String netAmountInput = netAmount.getText();
        String paidAmountInput = paidAmount.getText();
        String returnAmountInput = returnAmount.getText();

        Connection connection = DBCon.getConnection();
        String foodPayment = "INSERT INTO tlb_drug_payment ( client_id, invoice, amount, discount, vat, net_amount, paid_amount, return_amount, payment_date, insert_time, order_approve, approve_time, delivery_time, is_online) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

        try {

            PreparedStatement pst = connection.prepareStatement(foodPayment, Statement.RETURN_GENERATED_KEYS);
            pst.setString(1, String.valueOf(clientID));
            pst.setString(2, invoiceInput);
            pst.setString(3, totalAmountInput);
            pst.setString(4, "0");
            pst.setString(5, vatInput);
            pst.setString(6, netAmountInput);
            pst.setString(7, paidAmountInput);
            pst.setString(8, returnAmountInput);
            pst.setDate(9, getCurrentDate());
            pst.setString(10, getCurrentTimeStamp());
            pst.setString(11, "1");
            pst.setString(12, getCurrentTimeStamp());
            pst.setString(13, getCurrentTimeStamp());
            pst.setString(14, "0");
         

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
            clientName.clear();
            phoneNumber.clear();

        } catch (SQLException e) {
            Logger.getLogger(NewSalesController.class.getName()).log(Level.SEVERE, null, e);
            System.out.println(e);
        }

        return drugPaymentIdInput;
    }

    private void saveDrugBill(int clientID) throws SQLException {

        String invoiceInput = invoiceNo.getText();

        Connection connection = DBCon.getConnection();

        for (int i = 0; i < productAllDetails.size(); i++) {

            List<String> productDetailstemp = new ArrayList<String>();
            productDetailstemp = productAllDetails.get(i);

            productDetailstemp.get(0); // productDetails2.add(ID);
            productDetailstemp.get(1); // productDetails2.add(NAME);
            productDetailstemp.get(2); // productDetails2.add(quantityInputValue);
            productDetailstemp.get(3); // productDetails2.add(totalpriceInputValue);
            productDetailstemp.get(4); // productDetails2.add(perpriceInputValue);

            String foodPayment = "INSERT INTO tbl_drug_bill (drug_id, quantity, invoice, client_id, bill_date,  is_online ) VALUES(?,?,?,?,?,?)";

            try {

                PreparedStatement pst = connection.prepareStatement(foodPayment);

                pst.setString(1, String.valueOf(productDetailstemp.get(0)));
                pst.setString(2, String.valueOf(productDetailstemp.get(2)));
                pst.setString(3, invoiceInput);
                pst.setString(4, String.valueOf(clientID));
                pst.setDate(5, getCurrentDate());
                //pst.setString(5, "02121");
                pst.setString(6, "0");

                pst.execute();
                pst.close();
                clientName.clear();
                phoneNumber.clear();

            } catch (SQLException e) {
                Logger.getLogger(NewSalesController.class.getName()).log(Level.SEVERE, null, e);
                System.out.println(e);
            }

        }

    }

    ///////////////////////
}
