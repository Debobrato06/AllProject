/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import database.DBConn;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

/**
 * FXML Controller class
 *
 * @author User
 */
public class UpdateController implements Initializable {

    @FXML
    private ImageView image;
    @FXML
    private TextField nameInput;
    @FXML
    private TextField phoneInput;
    @FXML
    private TextField emailInput;
    @FXML
    private TextField addressInput;
    @FXML
    private TextField userName;

    private DBConn database = new DBConn();
    private Connection connection;
    private Statement statement;
    private ResultSet resultSet;
    @FXML
    private Label ID;
    @FXML
    private TextField password;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        ID.setVisible(false);
    }

    @FXML
    private void update(ActionEvent event) throws SQLException {

        String userIdInput = ID.getText();
        String nameUpdate = nameInput.getText();
        String userNameUpdate = userName.getText();
        String phoneUpdate = phoneInput.getText();
        String emailUpdate = emailInput.getText();
        String addressUpdate = addressInput.getText();
        String passwordUpdate = password.getText();

        connection = database.getConnection();
        try {//id, name, user_name, email, password, address, phone, photo
            String updateUser = "UPDATE  restaurent.user SET  name = ?,userName = ?, email = ?, password = ?, address = ?, phone = ?, photo = ? WHERE id = (" + userIdInput + ")";

            PreparedStatement pst = connection.prepareStatement(updateUser, Statement.RETURN_GENERATED_KEYS);
            pst.setString(1, nameUpdate);
            pst.setString(2, userNameUpdate);
            pst.setString(3, emailUpdate);
            pst.setString(4, passwordUpdate);
            pst.setString(5, addressUpdate);
            pst.setString(6, phoneUpdate);
            pst.setString(7, "Photo");
            pst.executeUpdate();

            pst.close();

        } catch (SQLException e) {
            Logger.getLogger(InvoiceGenerateController.class.getName()).log(Level.SEVERE, null, e);
            System.out.println(e);
        }

    }

    void setId(String updateProfileName) {

        String userFindsql = "select*from restaurent.user WHERE id IN (" + updateProfileName + ");  ";
        try {
            connection = database.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(userFindsql);
            while (resultSet.next()) { //user_name, email, password, address, phone, photo

                String nameIdInput = resultSet.getString("id");
                String name = resultSet.getString("name");
                String userNameInput = resultSet.getString("userName");
                String email = resultSet.getString("email");
                String phone = resultSet.getString("phone");
                String address = resultSet.getString("address");
                String pass = resultSet.getString("password");
                String image = resultSet.getString("photo");

                nameInput.setText(name);
                userName.setText(userNameInput);
                phoneInput.setText(phone);
                emailInput.setText(email);
                addressInput.setText(address);
                password.setText(pass);
                ID.setText(nameIdInput);

            }

        } catch (SQLException ex) {
            Logger.getLogger(UpdateController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
