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
import javafx.stage.Stage;
import model.drugDetailsModel;

/**
 * FXML Controller class
 *
 * @author User
 */
public class DrugDetailsController implements Initializable {

    @FXML
    private TableView<drugDetailsModel> drugDetailsList;
    @FXML
    private TableColumn<drugDetailsModel, String> id;
    @FXML
    private TableColumn<drugDetailsModel, String> drugName;
    @FXML
    private TableColumn<drugDetailsModel, String> genericName;
    ObservableList<drugDetailsModel> oblist = FXCollections.observableArrayList();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        drugDetailsTable();
    }

    public void drugDetailsTable() {
        try {

            Connection connection = DBConn.getConnection();
            ResultSet resultSet = connection.createStatement().executeQuery("SELECT tbl_drug.id AS drugID, tbl_drug.name AS drugName, tbl_medicine_group.name AS genericName FROM  tbl_drug,tbl_medicine_group WHERE tbl_drug.medicine_group_id = tbl_medicine_group.id ;");

            while (resultSet.next()) {
                String drugId = resultSet.getString("drugID");

                oblist.add(new drugDetailsModel(resultSet.getString("drugID"), resultSet.getString("drugName"), resultSet.getString("genericName")));

            }

        } catch (SQLException ex) {
            Logger.getLogger(DrugDetailsController.class.getName()).log(Level.SEVERE, null, ex);
        }

        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        drugName.setCellValueFactory(new PropertyValueFactory<>("drugName"));
        genericName.setCellValueFactory(new PropertyValueFactory<>("drugGenericName"));

        //TableView. 
        drugDetailsList.setItems(oblist);
    }

}
