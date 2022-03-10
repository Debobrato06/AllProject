/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.jfoenix.controls.JFXTextField;
import database.DBConnect;
import helper.AutoCompleteTextField;
import java.io.File;
import java.net.MalformedURLException;
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
import javafx.scene.control.TextArea;
import javafx.scene.text.Font;

/**
 * FXML Controller class
 *
 * @author User
 */
public class EnglishToBanglaController implements Initializable {

    ArrayList<String> englishWord = null;
    ArrayList<String> wordId = null;
    @FXML
    private JFXTextField EnglishWord;
    @FXML
    private JFXTextField BanglaWord;
    @FXML
    private TextArea allShow;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         EnglishWord.setStyle("-fx-text-inner-color: white");
         BanglaWord.setStyle("-fx-text-inner-color: white");
        ////????????????? Find The word Start ???????????????\\\\\\\\\\\\\\\\
        String sql = "SELECT * FROM tbl_translateEtoBTable";
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
                System.out.println("DICTIONARY ID" + wordIdInput);

            }

            new AutoCompleteTextField().bindAutoCompletion(EnglishWord, 15, true, englishWord);
            System.out.println("ENGLISH WORD" + englishWord);
            System.out.println("DICTIONARY ID" + wordId);

        } catch (SQLException e) {

            System.out.println(e.getMessage());
            System.out.println("englishWord:" + englishWord);
        }

        ////////////////////////////////////////////////////////////////////////////////////////////
    }

    @FXML
    private void EnglishWordSearch(ActionEvent event) {

        String searchProductName = EnglishWord.getText();
        System.out.println("ENGLISH::" + searchProductName);

        int searchIndex = englishWord.indexOf(searchProductName);
        String searchId = wordId.get(searchIndex);

        System.out.println("PRO ID::" + searchId);

        String translateSql = "SELECT * FROM tbl_translateEtoBTable WHERE id IN (" + searchId + ");  ";

        try {
            Connection connection = DBConnect.getConnection();
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(translateSql);

            while (rs.next()) {
                String banglaInput = rs.getString("bangla");
                String nounWordInput = rs.getString("noun");
                String verbWordInput = rs.getString("verb");
                String synonymWordInput = rs.getString("synonym");
                String antonymWordInput = rs.getString("antonym");
                System.out.println("BANGLA WORD" + banglaInput);
                BanglaWord.setText(banglaInput);
                allShow.setText(" NOUN :" + nounWordInput + "\r\n" + " VERB :" + verbWordInput + "\r\n" + " SYNONYM :" + synonymWordInput + "\r\n" + " ANTONYM :" + antonymWordInput);

            }

        } catch (SQLException ex) {
            Logger.getLogger(EnglishToBanglaController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
