/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import database.DBConn;
import database.initialDatabase;
import helper.DynaimicViews;
import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
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
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 *
 * @author Debobrato Biswas
 */
public class MasterController implements Initializable {

    ArrayList<String> profileNameInput = null;
    ArrayList<String> profileInputIds = null;

    private Label label;
    @FXML
    private ImageView list;
    @FXML
    private ImageView about;
    @FXML
    private Button close;
    @FXML
    private Button minimiz;
    @FXML
    private BorderPane borderpane;

    private DBConn database = new DBConn();
    private Connection connection;
    private Statement statement;
    private ResultSet resultSet;
    @FXML
    private Button profileName;
    @FXML
    private Label profileID;

    private void handleButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");
        label.setText("Hello World!");
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        


        
        ///////////////////////////
        profileID.setVisible(false);
        String usersql = "select*from restaurent.user";
        try {
            connection = database.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(usersql);
            while (resultSet.next()) { //user_name, email, password, address, phone, photo
                int nameId = resultSet.getInt("id");
                String nameInput = resultSet.getString("name");
                String userNameInput = resultSet.getString("userName");
                String emailInput = resultSet.getString("email");
                String phoneInput = resultSet.getString("phone");
                String addressInput = resultSet.getString("address");
                String imageInput = resultSet.getString("photo");

//                profileName.setText(nameInput);
//                name.setText(nameInput);
//                userName.setText(userNameInput);
//                email.setText(emailInput);
//                address.setText(addressInput);
//                phone.setText(phoneInput);
                String userFindsql = "select*from restaurent.userWHERE id IN (" + nameId + ");  ";
                try {
                    connection = database.getConnection();
                    statement = connection.createStatement();
                    resultSet = statement.executeQuery(usersql);
                    while (resultSet.next()) { //user_name, email, password, address, phone, photo
                        int nameIdInput = resultSet.getInt("id");
                        String name = resultSet.getString("name");
                        String userName = resultSet.getString("userName");
                        String email = resultSet.getString("email");
                        String phone = resultSet.getString("phone");
                        String address = resultSet.getString("address");
                        String image = resultSet.getString("photo");

                    }

                } catch (SQLException ex) {
                    Logger.getLogger(ProfileController.class.getName()).log(Level.SEVERE, null, ex);
                }

            }

        } catch (SQLException ex) {
            Logger.getLogger(ProfileController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void addInvoice(ActionEvent event) throws IOException {
        DynaimicViews.loaderBorderCenter(borderpane, "InvoiceGenerate");

    }

    @FXML
    private void about(ActionEvent event) throws IOException {
        Parent Prescription = FXMLLoader.load(getClass().getResource("/view/About.fxml"));
        borderpane.setCenter(Prescription);
    }

    @FXML
    private void web(ActionEvent event) {
        try {
            Desktop.getDesktop().browse(new URI("http://hrsoftbd.com/"));
        } catch (IOException e1) {
            e1.printStackTrace();
        } catch (URISyntaxException e1) {
            e1.printStackTrace();
        }
    }

    @FXML
    private void closeAction(ActionEvent event) {
        Stage stage = (Stage) close.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void minimizeAction(ActionEvent event) {
        Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        stage.setIconified(true);
    }

    @FXML
    private void invoiceList(ActionEvent event) {
        DynaimicViews.loaderBorderCenter(borderpane, "InvoiceList");
    }

    @FXML
    private void Synchronize(ActionEvent event) {
        DynaimicViews.loaderBorderCenter(borderpane, "Synchronize");
    }

    @FXML
    private void MoneyReceiptSetting(ActionEvent event) {
        DynaimicViews.loaderBorderCenter(borderpane, "MoneyReceiptSetting");

    }

    @FXML
    private void profileNameAction(ActionEvent event) throws IOException {

        String searchProfileName = profileID.getText();
        System.out.println("PROFILE ::" + searchProfileName);

        String userFindsql = "select*from restaurent.user WHERE id IN (" + searchProfileName + ");  ";
        try {
            connection = database.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(userFindsql);
            while (resultSet.next()) { //user_name, email, password, address, phone, photo
                int nameIdInput = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String userName = resultSet.getString("userName");
                String email = resultSet.getString("email");
                String phone = resultSet.getString("phone");
                String address = resultSet.getString("address");
                String image = resultSet.getString("photo");
                System.out.println("PRO ID::" + name);

                // profileName.setText(name);
            }

        } catch (SQLException ex) {
            Logger.getLogger(ProfileController.class.getName()).log(Level.SEVERE, null, ex);
        }

        FXMLLoader fXMLLoader = new FXMLLoader(getClass().getResource("/view/profile.fxml"));
        Parent root2 = (Parent) fXMLLoader.load();
        borderpane.setCenter(root2);
        ProfileController passController = fXMLLoader.getController();
        passController.setId(searchProfileName);

        // DynaimicViews.loaderBorderCenter(borderpane, "profile");
    }

    void setId(int userNameIndex) {
        String userFindsql = "select*from restaurent.user WHERE id IN (" + userNameIndex + ");  ";
        try {
            connection = database.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(userFindsql);
            while (resultSet.next()) { //user_name, email, password, address, phone, photo
                String nameIdInput = resultSet.getString("id");
                String name = resultSet.getString("name");
                String userName = resultSet.getString("userName");
                String email = resultSet.getString("email");
                String phone = resultSet.getString("phone");
                String address = resultSet.getString("address");
                String image = resultSet.getString("photo");

                profileName.setText(name);
                profileID.setText(nameIdInput);

            }

        } catch (SQLException ex) {
            Logger.getLogger(ProfileController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
