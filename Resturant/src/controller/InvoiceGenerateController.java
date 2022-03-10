/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.jfoenix.controls.JFXTextField;
import database.DBConn;
import helper.AutoCompleteTextField;
import helper.DynaimicViews;
import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.sql.Blob;
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
import javafx.beans.Observable;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Point2D;
import javafx.geometry.Pos;
import javafx.print.PageOrientation;
import javafx.print.Paper;
import javafx.print.Printer;
import javafx.print.PrinterJob;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.InputMethodEvent;
import static javafx.scene.input.KeyCode.E;
import static javafx.scene.input.KeyCode.R;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Box;
import javafx.scene.text.TextAlignment;
import javafx.scene.web.WebEngine;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.Window;
import javafx.util.Callback;
import model.productBillDetailsModel;

/**
 * FXML Controller class
 *
 * @author Debobrato Biswas
 */
public class InvoiceGenerateController implements Initializable {

    List<List<String>> productAllDetails = new ArrayList<List<String>>();

    ArrayList<String> productNameInput = null;
    ArrayList<String> productNameId = null;

    ArrayList<String> productName1 = null;
    ArrayList<String> productId1 = null;

    ArrayList<String> clientPhoneInput = null;
    ArrayList<String> clientId = null;

    @FXML
    private Label productName;
    @FXML
    private Label productCategory;
    @FXML
    private Label perProductPrice;
    @FXML
    private Label quantity;
    @FXML
    private Label totalPrice;
    @FXML
    private BorderPane borderpane;

    @FXML
    private TableView productOrderTable;
    @FXML
    private TableColumn id;
    @FXML
    private TableColumn name;
    @FXML
    private TableColumn quantityNumber;
    @FXML
    private TableColumn price;
    @FXML
    private TableColumn Action;

    @FXML
    private JFXTextField clientName;
    @FXML
    private JFXTextField phoneNumber;

    ObservableList<productBillDetailsModel> selectedProductItem = FXCollections.observableArrayList();

    HBox hboxx = new HBox();

    List<String> listOfSomething = null;

    private DBConn database = new DBConn();
    private Connection connection;
    private Statement statement;
    private ResultSet resultSet;

    @FXML
    private Label total;
    @FXML
    private Label vat;
    @FXML
    private Label netAmount;
    @FXML
    private TextField paidAmount;
    @FXML
    private Label returnAmount;

    @FXML
    private HBox foodHBox;
    @FXML
    private VBox foodItemVBox;

    @FXML
    private JFXTextField filterFoodItems;

    private int count = 0;
    private int previousProductId = 0;
    @FXML
    private HBox foodCategory;
    @FXML
    private Button incrementButton;
    @FXML
    private Button decrementButton;
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private Label invoiceNo;
    @FXML
    private TextField vatInput;
    @FXML
    private AnchorPane anchorPane;
    @FXML
    private BorderPane borderpanfoodview;
    @FXML
    private Label productId;
    @FXML
    private Label productItem;
    @FXML
    private FlowPane flowPane;

    private final Label jobStatus = new Label();
    WebEngine engine = new WebEngine();
    Window owner;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        productId.setVisible(false);

        try {
            // TODO  #ecf2f8
            getFoodTime();
            getFoodItem();
            foodProductSearch();
        } catch (SQLException ex) {
            Logger.getLogger(InvoiceGenerateController.class.getName()).log(Level.SEVERE, null, ex);
        }

        invoiceNo.setText(getCurrentTimeStampAsInvoice());

    }

    @FXML
    private void savePrint(ActionEvent event) throws SQLException, IOException {

        //int productIdInput =getProductId();
        int clientID = saveClient();
        int foodPaymentIdInput = saveFoodPayment(clientID);
        saveFoodBill(clientID);
        System.out.println("C ID :" + clientID);
        //setParams(mController);
        clearAllField();

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
            Logger.getLogger(InvoiceGenerateController.class.getName()).log(Level.SEVERE, null, e);
            System.out.println(e);
        }

    }

    @FXML
    private void addProduct(ActionEvent event) {
        quantity.setText(String.valueOf(++count));
        String perPrice = perProductPrice.getText();
        String quantityInput = quantity.getText();
        String price = String.valueOf(Float.valueOf(perPrice) * Float.valueOf(quantityInput));
        totalPrice.setText(price);

    }

    @FXML
    private void deleteProduct(ActionEvent event) {
        // int counting = count--;
        quantity.setText(String.valueOf(--count));
        String perPrice = perProductPrice.getText();
        String quantityInput = quantity.getText();
        String price = String.valueOf(Float.valueOf(perPrice) * Float.valueOf(quantityInput));
        totalPrice.setText(price);

    }

    @FXML
    private void okButton(ActionEvent event) throws SQLException {

        String productNameID = productId.getText();
        String productNameInputValue = productName.getText();
        String quantityInputValue = quantity.getText();
        String perpriceInputValue = perProductPrice.getText();
        String totalpriceInputValue = totalPrice.getText();

        productDetails(productNameID, productNameInputValue, quantityInputValue, totalpriceInputValue, perpriceInputValue);

        List<String> productDetailsTemp = new ArrayList<String>();
        Double totalPrice = 0.0;

        productOrderTable.getItems().clear();

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

            btn.setOnAction(new EventHandler<ActionEvent>() {

                @Override
                public void handle(ActionEvent t) {
                    System.out.println("Button Clicked ID : " + btn.getId());

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

        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        name.setCellValueFactory(new PropertyValueFactory<>("productNameValue"));
        quantityNumber.setCellValueFactory(new PropertyValueFactory<>("quantityValue"));
        price.setCellValueFactory(new PropertyValueFactory<>("priceValue"));
        Action.setCellValueFactory(new PropertyValueFactory<>("action"));

        productOrderTable.setItems(selectedProductItem);

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

    private static java.sql.Timestamp getCurrentTimeStampExceptSecond() {

        java.util.Date today = new java.util.Date();
        return new java.sql.Timestamp(today.getTime());

    }

    private static java.sql.Timestamp getCurrentTimeStampOnlyDate() {

        java.util.Date today = new java.util.Date();
        return new java.sql.Timestamp(today.getTime());

    }

    private static String getCurrentTimeStampAsInvoice() {

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyMMddHHmmss");
        LocalDateTime now = LocalDateTime.now();
        return dtf.format(now);

    }

    private int saveClient() throws SQLException {

        int clientIdinput = 0;
        String PatientName = clientName.getText();
        String Phone = phoneNumber.getText();

        Connection connection = DBConn.getConnection();
        try (PreparedStatement checkAccountExists = connection.prepareStatement(
                "SELECT * FROM tbl_clients WHERE phone = ?")) {
            checkAccountExists.setString(1, Phone);

            try (ResultSet rs = checkAccountExists.executeQuery()) {

                if (rs.next()) {
                    while (rs.next()) {
                        clientIdinput = rs.getInt("id");
                        System.out.println("Id = " + rs.getInt("id"));
                    }

                } else {
                    System.out.println("New Client Found00");
                    try {
                        String clients = "INSERT INTO restaurent.tbl_clients (  name, phone, email, is_online) VALUES(?,?,?,?)";

                        PreparedStatement pst = connection.prepareStatement(clients, Statement.RETURN_GENERATED_KEYS);
                        pst.setString(1, PatientName);
                        pst.setString(2, Phone);
                        pst.setString(3, "info@gmail.com");
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

                    } catch (SQLException e) {
                        Logger.getLogger(InvoiceGenerateController.class.getName()).log(Level.SEVERE, null, e);
                        System.out.println(e);
                    }
                }
                return clientIdinput;
            }

        }
    }

    private int saveFoodPayment(int clientID) throws SQLException {
        int foodPaymentIdInput = 0;

        String invoiceInput = invoiceNo.getText();
        String totalAmountInput = total.getText();
        String vatInput = vat.getText();
        String netAmountInput = netAmount.getText();
        String paidAmountInput = paidAmount.getText();
        String returnAmountInput = returnAmount.getText();

        Connection connection = DBConn.getConnection();
        String foodPayment = "INSERT INTO restaurent.tbl_food_payment ( client_id, invoice, amount, discount, vat, net_amount, paid_amount, return_amount, payment_date, user_id, method_id, insert_time, insert_by, order_approve, approved_time, delivery_time, is_online) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

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
            pst.setString(10, "1");
            pst.setString(11, "2");
            pst.setString(12, getCurrentTimeStamp());
            pst.setString(13, "3");
            pst.setString(14, "1");
            pst.setString(15, getCurrentTimeStamp());
            pst.setString(16, getCurrentTimeStamp());
            pst.setString(17, "0");

            int affectedRows = pst.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Creating Client failed, no rows affected.");
            }

            try (ResultSet generatedKeys = pst.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    foodPaymentIdInput = (int) generatedKeys.getLong(1);
                } else {
                    throw new SQLException("Creating Client failed, no ID obtained.");
                }
            }

            pst.close();
            clientName.clear();
            phoneNumber.clear();

        } catch (SQLException e) {
            Logger.getLogger(InvoiceGenerateController.class.getName()).log(Level.SEVERE, null, e);
            System.out.println(e);
        }

        return foodPaymentIdInput;
    }

    private void saveFoodBill(int clientID) throws SQLException {

        String invoiceInput = invoiceNo.getText();

        Connection connection = DBConn.getConnection();

        for (int i = 0; i < productAllDetails.size(); i++) {

            List<String> productDetailstemp = new ArrayList<String>();
            productDetailstemp = productAllDetails.get(i);

            productDetailstemp.get(0); // productDetails2.add(ID);
            productDetailstemp.get(1); // productDetails2.add(NAME);
            productDetailstemp.get(2); // productDetails2.add(quantityInputValue);
            productDetailstemp.get(3); // productDetails2.add(totalpriceInputValue);
            productDetailstemp.get(4); // productDetails2.add(perpriceInputValue);

            String foodPayment = "INSERT INTO restaurent.tbl_food_bill (food_product_id, quantity, invoice, client_id, bill_date, insert_time, user_id, insert_by, is_online ) VALUES(?,?,?,?,?,?,?,?,?)";

            try {

                PreparedStatement pst = connection.prepareStatement(foodPayment);

                pst.setString(1, String.valueOf(productDetailstemp.get(0)));
                pst.setString(2, String.valueOf(productDetailstemp.get(2)));
                pst.setString(3, invoiceInput);
                pst.setString(4, String.valueOf(clientID));
                pst.setDate(5, getCurrentDate());
                pst.setString(6, getCurrentTimeStamp());
                pst.setString(7, "1");
                pst.setString(8, "2");
                pst.setString(9, "0");

                pst.execute();
                pst.close();
                clientName.clear();
                phoneNumber.clear();

            } catch (SQLException e) {
                Logger.getLogger(InvoiceGenerateController.class.getName()).log(Level.SEVERE, null, e);
                System.out.println(e);
            }

        }

    }

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
    private void paidAmountAction(ActionEvent event) {
        String netAmountInput = netAmount.getText();
        String paidAmountInput = paidAmount.getText();
        String returnAmountinput = String.valueOf(Float.valueOf(paidAmountInput) - Float.valueOf(netAmountInput));
        returnAmount.setText(returnAmountinput);
    }

    private void clearAllField() {

        paidAmount.clear();
        clientName.clear();
        phoneNumber.clear();
        returnAmount.setText(null);
        netAmount.setText(null);
        total.setText(null);
        vat.setText(null);
        productOrderTable.setItems(null);
        productName.setText(null);
        productCategory.setText(null);
        perProductPrice.setText(null);
        quantity.setText(null);
        totalPrice.setText(null);

    }

    @FXML
    private void returnAmountOutput(KeyEvent event) {
//        String netAmountInput = netAmount.getText();
//        String paidAmountInput = paidAmount.getText();
//        String returnAmountinput = String.valueOf(Double.valueOf(paidAmountInput) - Double.valueOf(netAmountInput));
//        returnAmount.setText(returnAmountinput);
    }

    private void getFoodTime() throws SQLException {
        String sqlQuery = "SELECT * FROM restaurent.tbl_category t;  ";
        List<Button> buttonlist = new ArrayList<>();

        try {
            connection = database.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sqlQuery);
            while (resultSet.next()) { //id, name_bn, name_en, url_make, parent_id, priority, deleted, insert_by, insert_time
                String idInput = resultSet.getString("id");
                String categoryNameInput = resultSet.getString("name_en");

                Button FoodcategoryButton = new Button(categoryNameInput);
                FoodcategoryButton.setMaxHeight(35);
                FoodcategoryButton.setMaxWidth(100);
                FoodcategoryButton.setMinWidth(100);

                FoodcategoryButton.setId(resultSet.getString("id"));

                FoodcategoryButton.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent e) {

                        System.out.println("ID :" + idInput);

                        productNameId = new ArrayList<String>();

                        String foodProductSqlQuery = "SELECT * FROM tbl_food_products WHERE category_id IN (" + idInput + ");  ";
                        List<Button> buttonlist = new ArrayList<>();
                        try {
                            connection = database.getConnection();
                            statement = connection.createStatement();
                            resultSet = statement.executeQuery(foodProductSqlQuery);
                            while (resultSet.next()) {
                                int product_id = resultSet.getInt("id");
                                productNameId.add(String.valueOf(product_id));

                                String productNameInput = resultSet.getString("name");
                                String productPrice = resultSet.getString("price");
                                String productCategoryInput = resultSet.getString("category_id");
                                Button FoodproductButton = new Button(productNameInput);
                                FoodproductButton.setMaxHeight(107);
                                FoodproductButton.setMaxWidth(138);

                                FoodproductButton.setId(resultSet.getString("id"));

                                FoodproductButton.setOnAction(new EventHandler<ActionEvent>() {
                                    @Override
                                    public void handle(ActionEvent e) {

                                        System.out.println("Product ID:" + product_id);
                                        productId.setText(String.valueOf(product_id));
                                        ///////////////
                                        String catagaryNameSqlQuery = "SELECT * FROM tbl_category WHERE id IN (" + productCategoryInput + ");  ";

                                        try {
                                            connection = database.getConnection();
                                            statement = connection.createStatement();
                                            resultSet = statement.executeQuery(catagaryNameSqlQuery);
                                            while (resultSet.next()) {
                                                String productNameInput = resultSet.getString("name_en");

                                                productCategory.setText(productNameInput);
                                            }

                                        } catch (SQLException ex) {
                                            Logger.getLogger(InvoiceGenerateController.class.getName()).log(Level.SEVERE, null, ex);
                                        }

                                        //////////////
                                        if (previousProductId != Integer.valueOf(FoodproductButton.getId())) {
                                            count = 0;
                                            previousProductId = 0;
                                        }
                                        productName.setText(productNameInput);
                                        perProductPrice.setText(productPrice);

                                        quantity.setText(String.valueOf(++count));

                                        String perPrice = perProductPrice.getText();
                                        String quantityInput = quantity.getText();
                                        String price = String.valueOf(Float.valueOf(perPrice) * Float.valueOf(quantityInput));
                                        totalPrice.setText(price);
                                        previousProductId = Integer.valueOf(FoodproductButton.getId());

                                    }
                                });
                                Image img = new Image("/assets/image/19.jpg");
                                ImageView imgView = new ImageView(img);
                                imgView.setFitHeight(80);
                                imgView.setFitWidth(80);
                                FoodproductButton.setGraphic(imgView);
                                FoodproductButton.setAlignment(Pos.BOTTOM_CENTER);
                                FoodproductButton.setStyle("-fx-background-color:White;-fx-border-color: gray;");
                                FoodproductButton.setContentDisplay(ContentDisplay.TOP);
                                FoodproductButton.setTextFill(Color.BLACK);

                                buttonlist.add(FoodproductButton);

                            }

                            flowPane.getChildren().clear();
                            flowPane.setHgap(10);
                            flowPane.setVgap(10);
                            flowPane.getChildren().addAll(buttonlist);

                        } catch (SQLException ex) {
                            Logger.getLogger(InvoiceGenerateController.class.getName()).log(Level.SEVERE, null, ex);
                        }

                    }
                });

                FoodcategoryButton.setAlignment(Pos.CENTER);

                FoodcategoryButton.setStyle("-fx-background-color:#264d73;-fx-font-weight: bold;-fx-font-size: 14px;");
                FoodcategoryButton.setContentDisplay(ContentDisplay.TOP);
                FoodcategoryButton.setTextFill(Color.WHITE);

                buttonlist.add(FoodcategoryButton);

            }

            foodHBox.getChildren().clear();
            foodHBox.setSpacing(10);
            foodHBox.getChildren().addAll(buttonlist);

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            resultSet.close();
            statement.close();

        }

    }

    private void getFoodItem() throws SQLException {
        String sqlQuery = "SELECT * FROM restaurent.tbl_food_item_type t;  ";
        List<Button> buttonlist = new ArrayList<>();
        try {
            connection = database.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sqlQuery);
            while (resultSet.next()) { //id, name_bn, name_en, insert_by, insert_time
                String idInputFoodItem = resultSet.getString("id");
                String productNameInput = resultSet.getString("name_en");

                Button FoodItem = new Button(productNameInput);
                FoodItem.setMaxHeight(35);
                FoodItem.setMaxWidth(80);
                FoodItem.setMinWidth(80);

                FoodItem.setId(resultSet.getString("id"));
///////////////////////FOODPRODUCTLIST START///////////////
                FoodItem.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent e) {

                        System.out.println("FoodItemID :" + idInputFoodItem);
                        String foodProductSqlQuery = "SELECT * FROM tbl_food_products WHERE item_type_id IN (" + idInputFoodItem + ");  ";
                        List<Button> buttonlist = new ArrayList<>();
                        try {
                            connection = database.getConnection();
                            statement = connection.createStatement();
                            resultSet = statement.executeQuery(foodProductSqlQuery);
                            while (resultSet.next()) {
                                int product_id = resultSet.getInt("id");
                                //productNameId.add(String.valueOf(resultSet.getInt("id")));

                                String productNameInput = resultSet.getString("name");
                                String productPrice = resultSet.getString("price");
                                String productItemTypeInput = resultSet.getString("item_type_id");
                                Button FoodproductButton = new Button(productNameInput);
                                FoodproductButton.setMaxHeight(107);
                                FoodproductButton.setMaxWidth(138);

                                FoodproductButton.setId(resultSet.getString("id"));

                                // FoodButton.setId(resultSet.getString("id"));
                                FoodproductButton.setOnAction(new EventHandler<ActionEvent>() {
                                    @Override
                                    public void handle(ActionEvent e) {

                                        System.out.println("Product ID:" + product_id);
                                        productId.setText(String.valueOf(product_id));
                                        ///////////////

                                        //////////////
                                        if (previousProductId != Integer.valueOf(FoodproductButton.getId())) {
                                            count = 0;
                                            previousProductId = 0;
                                        }
                                        productName.setText(productNameInput);
                                        perProductPrice.setText(productPrice);
                                        productCategory.setText(productItemTypeInput);

                                        quantity.setText(String.valueOf(++count));

                                        String perPrice = perProductPrice.getText();
                                        String quantityInput = quantity.getText();
                                        String price = String.valueOf(Float.valueOf(perPrice) * Float.valueOf(quantityInput));
                                        totalPrice.setText(price);
                                        previousProductId = Integer.valueOf(FoodproductButton.getId());
                                    }
                                });
                                Image img = new Image("/assets/image/19.jpg");
                                ImageView imgView = new ImageView(img);
                                imgView.setFitHeight(80);
                                imgView.setFitWidth(80);
                                FoodproductButton.setGraphic(imgView);
                                FoodproductButton.setAlignment(Pos.BOTTOM_CENTER);
                                FoodproductButton.setStyle("-fx-background-color:White;-fx-border-color: gray;");
                                FoodproductButton.setContentDisplay(ContentDisplay.TOP);
                                FoodproductButton.setTextFill(Color.BLACK);

                                buttonlist.add(FoodproductButton);

                            }

                            flowPane.getChildren().clear();
                            flowPane.setHgap(10);
                            flowPane.setVgap(10);
                            flowPane.getChildren().addAll(buttonlist);

                        } catch (SQLException ex) {
                            Logger.getLogger(InvoiceGenerateController.class.getName()).log(Level.SEVERE, null, ex);
                        }

                    }
                });
//////////////////////////////////////////////////////FOODPRODUCTLIST END
                FoodItem.setAlignment(Pos.BOTTOM_CENTER);
                FoodItem.setStyle("-fx-background-color:#264d73;");
                FoodItem.setContentDisplay(ContentDisplay.TOP);
                FoodItem.setTextFill(Color.WHITE);

                buttonlist.add(FoodItem);

            }

            foodItemVBox.getChildren().clear();
            foodItemVBox.setSpacing(10);
            foodItemVBox.getChildren().addAll(buttonlist);

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            resultSet.close();
            statement.close();

        }
    }

    private void foodProductSearch() {
        try {
            Connection connection = DBConn.getConnection();

            ResultSet resultSet = connection.createStatement().executeQuery("SELECT name,id FROM tbl_food_products");
            productNameInput = new ArrayList<String>();
            productNameId = new ArrayList<String>();

            while (resultSet.next()) {
                String product_name = resultSet.getString("name");
                productNameInput.add(product_name);
                int productId = resultSet.getInt("id");
                productNameId.add(String.valueOf(productId));

            }

            new AutoCompleteTextField().bindAutoCompletion(filterFoodItems, 15, true, productNameInput);
            System.out.println("Product  name" + productNameInput);
            System.out.println("Product id" + productNameId);

        } catch (SQLException e) {

            System.out.println(e.getMessage());
            System.out.println("productNameInput:");
        }

    }

    @FXML
    private void foodNameSearch(ActionEvent event) throws SQLException {
//productNameInput,productNameId
        String searchProductName = filterFoodItems.getText();
        productNameInput.add(searchProductName);
        System.out.println("PRO ::" + searchProductName);

        int searchProductNameIndex = productNameInput.indexOf(searchProductName);
        String searchProductNameId = productNameId.get(searchProductNameIndex);

        System.out.println("PRO ID::" + searchProductNameId);

        String foodProductSqlQuery = "SELECT * FROM restaurent.tbl_food_products WHERE id IN (" + searchProductNameId + ");  ";
        List<Button> buttonlist = new ArrayList<>();
        try {
            connection = database.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(foodProductSqlQuery);
            while (resultSet.next()) {
                int product_id = resultSet.getInt("id");
                String productNameInput = resultSet.getString("name");
                String productPrice = resultSet.getString("price");
                String productItemTypeInput = resultSet.getString("item_type_id");
                String imageInput = resultSet.getString("photo");
                Button FoodproductButton = new Button(productNameInput);
                FoodproductButton.setMaxHeight(107);
                FoodproductButton.setMaxWidth(138);

                FoodproductButton.setId(resultSet.getString("id"));

                FoodproductButton.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent e) {

                        System.out.println("Product ID:" + product_id);

                        if (previousProductId != Integer.valueOf(FoodproductButton.getId())) {
                            count = 0;
                            previousProductId = 0;
                        }
                        productName.setText(productNameInput);
                        perProductPrice.setText(productPrice);
                        productCategory.setText(productItemTypeInput);

                        quantity.setText(String.valueOf(++count));

                        String perPrice = perProductPrice.getText();
                        String quantityInput = quantity.getText();
                        String price = String.valueOf(Float.valueOf(perPrice) * Float.valueOf(quantityInput));
                        totalPrice.setText(price);
                        previousProductId = Integer.valueOf(FoodproductButton.getId());
                    }
                });
                Image img = new Image("/assets/image/19.jpg");
                ImageView imgView = new ImageView(img);
                imgView.setFitHeight(80);
                imgView.setFitWidth(80);
                FoodproductButton.setGraphic(imgView);
                FoodproductButton.setAlignment(Pos.BOTTOM_CENTER);
                FoodproductButton.setStyle("-fx-background-color:White;-fx-border-color: gray;");
                FoodproductButton.setContentDisplay(ContentDisplay.TOP);
                FoodproductButton.setTextFill(Color.BLACK);

                buttonlist.add(FoodproductButton);

            }

            flowPane.getChildren().clear();
            flowPane.setHgap(10);
            flowPane.setVgap(10);
            flowPane.getChildren().addAll(buttonlist);

        } catch (SQLException ex) {
            Logger.getLogger(InvoiceGenerateController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void vatInputAction(ActionEvent event) {
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

}
