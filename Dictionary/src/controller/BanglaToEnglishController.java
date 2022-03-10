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
import javafx.scene.control.TextArea;

/**
 * FXML Controller class
 *
 * @author Debobrato Biswas
 */
public class BanglaToEnglishController implements Initializable {

    ArrayList<String> banglaWord = null;
    ArrayList<String> wordId = null;

    @FXML
    private JFXTextField english;
    @FXML
    private JFXTextField bangla;
    @FXML
    private TextArea allPartShow;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        english.setStyle("-fx-text-inner-color: white");
        bangla.setStyle("-fx-text-inner-color: white");

        ////????????????? Find The word Start ???????????????\\\\\\\\\\\\\\\\
        String sql = "SELECT * FROM tbl_translateEtoBTable";
        try {

            Connection connection = DBConnect.getConnection();
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            banglaWord = new ArrayList<String>();
            wordId = new ArrayList<String>();

            while (rs.next()) {
                // id, english, bangla, noun, verb, synonym, antonym
                String englishWordInput = rs.getString("bangla");
                banglaWord.add(englishWordInput);

                int wordIdInput = rs.getInt("id");
                wordId.add(String.valueOf(wordIdInput));
                System.out.println("DICTIONARY ID" + wordIdInput);

            }

            new AutoCompleteTextField().bindAutoCompletion(bangla, 15, true, banglaWord);
            System.out.println("ENGLISH WORD" + banglaWord);
            System.out.println("DICTIONARY ID" + wordId);

        } catch (SQLException e) {

            System.out.println(e.getMessage());
            System.out.println("englishWord:" + banglaWord);
        }

        ////////////////////////////////////////////////////////////////////////////////////////////
    }

    @FXML
    private void banglaWordSearch(ActionEvent event) {

        String searchProductName = bangla.getText();
        System.out.println("Bangla Word ::" + searchProductName);

        int searchIndex = banglaWord.indexOf(searchProductName);
        String searchId = wordId.get(searchIndex);

        System.out.println("Bangla ID::" + searchId);

        String translateSql = "SELECT * FROM tbl_translateEtoBTable WHERE id IN (" + searchId + ");  ";

        try {
            Connection connection = DBConnect.getConnection();
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(translateSql);

            while (rs.next()) {
                String englishInput = rs.getString("english");
                String nounWordInput = rs.getString("noun");
                String verbWordInput = rs.getString("verb");
                String synonymWordInput = rs.getString("synonym");
                String antonymWordInput = rs.getString("antonym");
                System.out.println("English WORD" + englishInput);
                english.setText(englishInput);
                allPartShow.setText(" NOUN :" + nounWordInput + "\r\n" + " VERB :" + verbWordInput + "\r\n" + " SYNONYM :" + synonymWordInput + "\r\n" + " ANTONYM :" + antonymWordInput);

            }

        } catch (SQLException ex) {
            Logger.getLogger(BanglaToEnglishController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
