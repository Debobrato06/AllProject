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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import model.paymentModel;
import model.productBillDetailsModel;

/**
 * FXML Controller class
 *
 * @author Debobrato Biswas
 */
public class InvoiceListController implements Initializable {

    @FXML
    private TableView<paymentModel> invoiceListTable;
    @FXML
    private TableColumn<paymentModel, String> id;
    @FXML
    private TableColumn<paymentModel, String> clientID;
    @FXML
    private TableColumn<paymentModel, String> netAmountCloumn;
    @FXML
    private TableColumn<paymentModel, String> paidAmountCloumn;
    @FXML
    private TableColumn<paymentModel, String> returnAmountCloumn;
    @FXML
    private TableColumn<paymentModel, String> paymentDate;
    @FXML
    private TableColumn<paymentModel, String> action;

    ObservableList<paymentModel> oblist = FXCollections.observableArrayList();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

        try {

            Connection connection = DBConn.getConnection();
            ResultSet resultSet = connection.createStatement().executeQuery("SELECT * FROM restaurent.tbl_food_payment t;");
            while (resultSet.next()) {
                String foodPaymentId = resultSet.getString("id");
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

                            FXMLLoader fXMLLoader = new FXMLLoader(getClass().getResource("/view/InvoicePrint.fxml"));
                            Parent root2 = (Parent) fXMLLoader.load();
                            InvoicePrintController mController = fXMLLoader.getController();

                            Scene scene = new Scene(root2);
                            Stage stage = new Stage();

                            mController.setID(foodPaymentId);

                            System.out.println("FoodPayment ID:" + foodPaymentId);
                            stage.setScene(scene);
                            stage.show();

                        } catch (Exception e) {
                            Logger.getLogger(InvoiceGenerateController.class.getName()).log(Level.SEVERE, null, e);
                            System.out.println(e);
                        }

                        ////////////
                        System.out.println("Button clicked");

                    }
                });

                ////id, client_id, invoice, amount, discount, vat, net_amount, paid_amount, return_amount, payment_date, user_id, method_id, insert_time, insert_by, order_approve, approved_time, delivery_time, is_online
                oblist.add(new paymentModel(resultSet.getString("id"), resultSet.getString("client_id"), resultSet.getString("net_amount"), resultSet.getString("paid_amount"), resultSet.getString("return_amount"), resultSet.getString("payment_date"), btn));

            }

        } catch (SQLException ex) {
            Logger.getLogger(productBillDetailsModel.class.getName()).log(Level.SEVERE, null, ex);
        }
///id, clientIds, netAmountInputValue, paidAmountInputValue,returnAmountInputValue,paymentDateTime,Acttion;
//netAmountCloumn  paidAmountCloumn  returnAmountCloumn  paymentDate  action
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        clientID.setCellValueFactory(new PropertyValueFactory<>("clientIds"));
        netAmountCloumn.setCellValueFactory(new PropertyValueFactory<>("netAmountInputValue"));
        paidAmountCloumn.setCellValueFactory(new PropertyValueFactory<>("paidAmountInputValue"));
        returnAmountCloumn.setCellValueFactory(new PropertyValueFactory<>("returnAmountInputValue"));
        paymentDate.setCellValueFactory(new PropertyValueFactory<>("paymentDateTime"));
        action.setCellValueFactory(new PropertyValueFactory<>("Acttion"));

        //TableView. 
        invoiceListTable.setItems(oblist);

    }

}
