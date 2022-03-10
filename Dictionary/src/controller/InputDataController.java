/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.jfoenix.controls.JFXTextField;
import database.DBConnect;
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

/**
 * FXML Controller class
 *
 * @author Debobrato Biswas
 */
public class InputDataController implements Initializable {

    @FXML
    private JFXTextField english;
    @FXML
    private JFXTextField bangla;
    @FXML
    private JFXTextField noun;
    @FXML
    private JFXTextField verb;
    @FXML
    private JFXTextField synonym;
    @FXML
    private JFXTextField antonym;

//    private DBConnect database = new DBConnect();
//    private Connection connection;
//    private Statement statement;
//    private ResultSet resultSet;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void submit(ActionEvent event) throws SQLException {

        String englishInput = english.getText();
        String banglaInput = bangla.getText();
        String nounInput = noun.getText();
        String verbInput = verb.getText();
        String synonymInput = synonym.getText();
        String antonymInput = antonym.getText();

        Connection connection = DBConnect.getConnection();
        String foodPayment = "INSERT INTO dictionary.tbl_translateEtoBTable ( english, bangla, noun, verb, synonym, antonym) VALUES(?,?,?,?,?,?)";

        try {

            PreparedStatement pst = connection.prepareStatement(foodPayment, Statement.RETURN_GENERATED_KEYS);

            pst.setString(1, englishInput);
            pst.setString(2, banglaInput);
            pst.setString(3, nounInput);
            pst.setString(4, verbInput);
            pst.setString(5, synonymInput);
            pst.setString(6, antonymInput);

            pst.executeUpdate();

            pst.close();

            english.clear();
            bangla.clear();
            noun.clear();
            verb.clear();
            synonym.clear();
            antonym.clear();

        } catch (SQLException e) {
            Logger.getLogger(InputDataController.class.getName()).log(Level.SEVERE, null, e);
            System.out.println(e);
        }

    }

    @FXML
    private void reset(ActionEvent event) {
        english.clear();
        bangla.clear();
        noun.clear();
        verb.clear();
        synonym.clear();
        antonym.clear();
    }

}
