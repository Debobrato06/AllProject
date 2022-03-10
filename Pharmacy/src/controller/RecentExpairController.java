/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import database.DBConn;
import java.net.URL;
import static java.nio.file.Files.list;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import static java.util.Collections.list;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.medicineListModel;
import model.recentExpairModel;

/**
 * FXML Controller class
 *
 * @author User
 */
public class RecentExpairController implements Initializable {

    private DBConn database = new DBConn();
    private Connection connection;
    private Statement statement;
    private ResultSet resultSet;

    ObservableList<recentExpairModel> oblist = FXCollections.observableArrayList();
    @FXML
    private TableView<recentExpairModel> recentExpairList;
    @FXML
    private TableColumn<recentExpairModel, String> id;
    @FXML
    private TableColumn<recentExpairModel, String> drugNameInput;
    @FXML
    private TableColumn<recentExpairModel, String> recentExpairDate;
    @FXML
    private Label date;

    /**
     * Initializes the controller class. id drugNameInput recentExpairDate
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        date.setVisible(false);
        recentExpairListTable();
    }

  
    public void recentExpairListTable() {
        try {

            DateTimeFormatter datepatern = DateTimeFormatter.ofPattern("yyyy-MM-dd");

            LocalDateTime now = LocalDateTime.now();
            LocalDateTime then = now.plusDays(7);

            String Today = now.format(datepatern).toString();
            String next7day = then.format(datepatern).toString();
            System.out.println("Today DATE ::::" + Today);
            System.out.println("Next DATE ::::" + next7day);

            String recentExpairListSql = "SELECT tbl_medicine.id AS medicineID, tbl_medicine.expired AS medicineExpired, tbl_drug.name AS drugName FROM  tbl_medicine LEFT JOIN tbl_drug ON tbl_drug.id = tbl_medicine.drug_id  WHERE tbl_medicine.expired >= '" + Today + "' AND tbl_medicine.expired <= '" + next7day + "';";
            connection = database.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(recentExpairListSql);
            System.out.println("ResultSet ::::" + recentExpairListSql);

            while (resultSet.next()) {
                int SL = 0;

                oblist.add(new recentExpairModel(resultSet.getString(SL + 1), resultSet.getString("drugName"), resultSet.getString("medicineExpired")));

            }

        } catch (SQLException ex) {
            Logger.getLogger(RecentExpairController.class.getName()).log(Level.SEVERE, null, ex);
        }

        id.setCellValueFactory(new PropertyValueFactory<>("SL"));
        drugNameInput.setCellValueFactory(new PropertyValueFactory<>("drugName"));
        recentExpairDate.setCellValueFactory(new PropertyValueFactory<>("expairDate"));

        //TableView. 
        recentExpairList.setItems(oblist);
    }

}
