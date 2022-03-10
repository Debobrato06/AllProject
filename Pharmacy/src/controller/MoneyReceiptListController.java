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
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.moneyReceiptModel;
import model.recentExpairModel;

/**
 * FXML Controller class
 *
 * @author User
 */
public class MoneyReceiptListController implements Initializable {

    ObservableList<moneyReceiptModel> oblist = FXCollections.observableArrayList();

    @FXML
    private TableView<moneyReceiptModel> moneyReceiptList;
    @FXML
    private TableColumn<moneyReceiptModel, String> SL;
    @FXML
    private TableColumn<moneyReceiptModel, String> moneyReceiptNo;
    @FXML
    private TableColumn<moneyReceiptModel, String> discountAmount;
    @FXML
    private TableColumn<moneyReceiptModel, String> netAmount;
    @FXML
    private TableColumn<moneyReceiptModel, String> action;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

        try {

            Connection connection = DBConn.getConnection();
            ResultSet resultSet = connection.createStatement().executeQuery("SELECT * FROM pharmacy_shop.tbl_money_receipt ;");
            while (resultSet.next()) {
                String moneyReceiptinput = resultSet.getString("id");
                Button btn = new Button();
                btn.setText("Details");
                btn.setMaxWidth(80);
                //  btn.setMaxHeight(20);
                btn.setLayoutX(0);
                btn.setStyle("-fx-background-color: green;-fx-text-fill:white;");
                btn.setOnAction(new EventHandler<ActionEvent>() {

                    @Override
                    public void handle(ActionEvent arg0) {
                        // TODO Auto-generated method stub  

                        try {

                            FXMLLoader fXMLLoader = new FXMLLoader(getClass().getResource("/view/OrderPrint.fxml"));
                            Parent root2 = (Parent) fXMLLoader.load();
                            OrderPrintController mController = fXMLLoader.getController();

                            Scene scene = new Scene(root2);
                            Stage stage = new Stage();

                            mController.setIdDetails(moneyReceiptinput);

                            System.out.println("Money Receipt ID:" + moneyReceiptinput);
                            stage.setScene(scene);
                            stage.show();

                        } catch (Exception e) {
                            Logger.getLogger(OrderGenerateController.class.getName()).log(Level.SEVERE, null, e);
                            System.out.println(e);
                        }
                        ////////////
                        System.out.println("Button clicked");

                    }
                });

                ////id, shop_id, money_receipt_code, discount, net_amount
                oblist.add(new moneyReceiptModel(resultSet.getString("id"), resultSet.getString("money_receipt_code"), resultSet.getString("discount"), resultSet.getString("net_amount"), btn));

            }

        } catch (SQLException ex) {
            Logger.getLogger(moneyReceiptModel.class.getName()).log(Level.SEVERE, null, ex);
        }

//sl, moneyReceiptNumber, Discount, NetAmount; action
        SL.setCellValueFactory(new PropertyValueFactory<>("sl"));
        moneyReceiptNo.setCellValueFactory(new PropertyValueFactory<>("moneyReceiptNumber"));
        discountAmount.setCellValueFactory(new PropertyValueFactory<>("Discount"));
        netAmount.setCellValueFactory(new PropertyValueFactory<>("NetAmount"));
        action.setCellValueFactory(new PropertyValueFactory<>("action"));

        //TableView. 
        moneyReceiptList.setItems(oblist);

    }

}
