/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import database.DBConn;
import helper.DynaimicViews;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;

/**
 * FXML Controller class
 *
 * @author User
 */
public class ProfileController implements Initializable {

    @FXML
    private BorderPane borderpane;
    @FXML
    private Label name;
    @FXML
    private Label email;
    @FXML
    private Label address;
    @FXML
    private Label phone;
    @FXML
    private ImageView profileImage;
    @FXML
    private Label profileName;

    private DBConn database = new DBConn();
    private Connection connection;
    private Statement statement;
    private ResultSet resultSet;
    @FXML
    private Label userName;
    @FXML
    private Label userId;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        userId.setVisible(false);

    }

    @FXML
    private void Update(ActionEvent event) throws IOException {
        String updateProfileName = userId.getText();
        System.out.println("PROFILE ::" + updateProfileName);

        String userFindsql = "select*from restaurent.user WHERE id IN (" + updateProfileName + ");  ";
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

        FXMLLoader fXMLLoader = new FXMLLoader(getClass().getResource("/view/update.fxml"));
        Parent root2 = (Parent) fXMLLoader.load();
        borderpane.setCenter(root2);
        UpdateController passController = fXMLLoader.getController();
        passController.setId(updateProfileName);

        // DynaimicViews.loaderBorderCenter(borderpane, "update");
    }

    void setId(String searchProfileName) {
        String userFindsql = "select*from restaurent.user WHERE id IN (" + searchProfileName + ");  ";
        try {
            connection = database.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(userFindsql);
            while (resultSet.next()) { //user_name, email, password, address, phone, photo

                String nameIdInput = resultSet.getString("id");
                String nameInput = resultSet.getString("name");
                String userNameInput = resultSet.getString("userName");
                String emailInput = resultSet.getString("email");
                String phoneInput = resultSet.getString("phone");
                String addressInput = resultSet.getString("address");
                String imageInput = resultSet.getString("photo");

                userId.setText(nameIdInput);
                profileName.setText(nameInput);
                name.setText(nameInput);
                userName.setText(userNameInput);
                email.setText(emailInput);
                address.setText(addressInput);
                phone.setText(phoneInput);

            }

        } catch (SQLException ex) {
            Logger.getLogger(ProfileController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
