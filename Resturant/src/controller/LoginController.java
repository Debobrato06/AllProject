/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import database.DBConn;
import database.initialDatabase;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author Debobrato Biswas
 */
public class LoginController implements Initializable {

    ArrayList<String> userInputName = null;
    ArrayList<String> userInputIds = null;

    Stage smallstage = null;
    private double xOffset = 0;
    private double yOffset = 0;

    @FXML
    private JFXButton loginId;
    @FXML
    private JFXTextField userName;
    @FXML
    private JFXPasswordField password;
    @FXML
    private Label faildLogin;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

        initialDatabase initialDatabasesInput = new initialDatabase();
        try {
            initialDatabasesInput.creatDatabse();
            initialDatabasesInput.bankTransaction();
            initialDatabasesInput.bkashTransaction();
            initialDatabasesInput.categoryTable();
            initialDatabasesInput.clientsTable();
            initialDatabasesInput.divissionTable();
            initialDatabasesInput.foodBillTable();
            initialDatabasesInput.foodItemOfferTable();
            initialDatabasesInput.foodItemTypeTable();
            initialDatabasesInput.foodOrderDeliveryTable();
            initialDatabasesInput.foodPaymentTable();
            initialDatabasesInput.foodProductsTable();
            initialDatabasesInput.methodTable();
            initialDatabasesInput.upozillaTable();
            initialDatabasesInput.zillaTable();
            initialDatabasesInput.zillaInsertTable();
            initialDatabasesInput.upozillaInsertTable();
            initialDatabasesInput.methodInsertTable();
            initialDatabasesInput.divissionInsertTable();
            initialDatabasesInput.userTable();
            initialDatabasesInput.userInsertTable();
            initialDatabasesInput.moneyReceiptSettingTable();

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MasterController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(MasterController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void loginAction(ActionEvent event) throws SQLException {

        ///////////////////////
        Connection connection = DBConn.getConnection();
        Statement statement = connection.createStatement();

        try {

            String sql = "SELECT * FROM restaurent.user WHERE userName = '" + userName.getText() + "' AND password = '" + password.getText() + "';";
            ResultSet resultSet = statement.executeQuery(sql);
            userInputName = new ArrayList<String>();
            userInputIds = new ArrayList<String>();

            if (resultSet.next()) {

                // schedule.getItems().add(resultSet.getString("eat_format"));
                String userInput = resultSet.getString("name");
                userInputName.add(userInput);
                int user_id = resultSet.getInt("id");
                userInputIds.add(String.valueOf(user_id));
                String searchUserName = userName.getText();
                userInputName.add(searchUserName);
                System.out.println("PROFILE ::" + searchUserName);
                int userNameIndex = userInputName.indexOf(searchUserName);
                System.out.println("PRO ID::" + userNameIndex);
                 System.out.println(" ID::" + userInputIds);

                System.out.println("PROFILE LOGIN ID ::" + user_id + "PROFILE LOGIN NAME ::" + userInput);

                //////////////////////////////////////
                if (smallstage == null) {

                    try {

                        FXMLLoader fXMLLoader = new FXMLLoader(getClass().getResource("/view/MasterView.fxml"));
                        Parent root2 = (Parent) fXMLLoader.load();
                        MasterController passController = fXMLLoader.getController();
                        //  pc.setParams(this);
                        passController.setId(userNameIndex);
                        Scene scene = new Scene(root2);
                        smallstage = new Stage();
                        Image icon = new Image(getClass().getResourceAsStream("/assets/image/Logo.jpg"));
                        smallstage.getIcons().add(icon);

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

                        Stage stage = (Stage) loginId.getScene().getWindow();
                        stage.close();

                    } catch (Exception e) {
                        System.out.println("Can't load new windows");
                    }
                } else if (smallstage.isShowing()) {
                    smallstage.toFront();
                } else {
                    smallstage.show();
                }

                //////////////////////////////////////////            
            } else {

                String errorLogin = "Sorry! UserName & Password does not match";
                faildLogin.setText(errorLogin);
                System.out.println("Error Login");

                ////////////////////////////
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        ///////////////////////////
    }

    @FXML
    private void forgotPassowrd(ActionEvent event) {
        if (smallstage == null) {

            try {

                FXMLLoader fXMLLoader = new FXMLLoader(getClass().getResource("/view/MasterView.fxml"));
                Parent root2 = (Parent) fXMLLoader.load();
                MasterController pc = fXMLLoader.getController();
                //  pc.setParams(this);
                Scene scene = new Scene(root2);
                smallstage = new Stage();
                Image icon = new Image(getClass().getResourceAsStream("/assets/image/Logo.jpg"));
                smallstage.getIcons().add(icon);

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

                Stage stage = (Stage) loginId.getScene().getWindow();
                stage.close();

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
