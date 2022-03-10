/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.jfoenix.controls.JFXTextField;
import database.DBConnect;
import helper.AutoCompleteTextField;
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
import javafx.fxml.Initializable;

/**
 * FXML Controller class
 *
 * @author Debobrato Biswas
 */
public class AppropriateController implements Initializable {

    ArrayList<String> englishWord = null;
    ArrayList<String> wordId = null;

    @FXML
    private JFXTextField englishField;
    @FXML
    private JFXTextField banglaField;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
         englishField.setStyle("-fx-text-inner-color: white");
         banglaField.setStyle("-fx-text-inner-color: white");
        ////????????????? Find The word Start ???????????????\\\\\\\\\\\\\\\\
        String sql = "SELECT * FROM tbl_appropriatePreposition";
        try {

            Connection connection = DBConnect.getConnection();
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            englishWord = new ArrayList<String>();
            wordId = new ArrayList<String>();

            while (rs.next()) {
                // id, english, bangla, noun, verb, synonym, antonym
                String englishWordInput = rs.getString("english");
                englishWord.add(englishWordInput);
               
                int wordIdInput = rs.getInt("id");
                wordId.add(String.valueOf(wordIdInput));
                System.out.println("Preposition ID" + wordIdInput);

            }

            new AutoCompleteTextField().bindAutoCompletion(englishField, 15, true, englishWord);
            System.out.println("Preposition WORD" + englishWord);
            System.out.println("Preposition ID" + wordId);

        } catch (SQLException e) {

            System.out.println(e.getMessage());
            System.out.println("englishWord:" + englishWord);
        }

        ////////////////////////////////////////////////////////////////////////////////////////////
    }

    @FXML
    private void english(ActionEvent event) {
        String searchProductName = englishField.getText();
        System.out.println("ENGLISH::" + searchProductName);

        int searchIndex = englishWord.indexOf(searchProductName);
        String searchId = wordId.get(searchIndex);

        System.out.println("ID::" + searchId);

        String translateSql = "SELECT * FROM tbl_appropriatePreposition WHERE id IN (" + searchId + ");  ";

        try {
            Connection connection = DBConnect.getConnection();
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(translateSql);

            while (rs.next()) {
                String banglaInput = rs.getString("bangla");
                
                System.out.println("BANGLA WORD" + banglaInput);
                banglaField.setText(banglaInput);
                

            }

        } catch (SQLException ex) {
            Logger.getLogger(AppropriateController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void bangla(ActionEvent event) {
    }

}
