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
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.medicineTypeModel;
import model.rackModel;

/**
 * FXML Controller class
 *
 * @author User
 */
public class MedicineTypeController implements Initializable {

    @FXML
    private TableView<medicineTypeModel> medicineTypeList;
    @FXML
    private TableColumn<medicineTypeModel, String> id;
    @FXML
    private TableColumn<medicineTypeModel, String> medicineName;
    ObservableList<medicineTypeModel> oblist = FXCollections.observableArrayList();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
         medicineTypeTable();
    }    
    public void medicineTypeTable() {
        try {

            Connection connection = DBConn.getConnection();
            ResultSet resultSet = connection.createStatement().executeQuery("SELECT * FROM pharmacy_shop.tbl_medicine_type t;");

            while (resultSet.next()) {
                String drugId = resultSet.getString("id");
                System.out.println("DRUG NAME ::" + resultSet.getString("name"));

                oblist.add(new medicineTypeModel(resultSet.getString("id"), resultSet.getString("name")));

            }

        } catch (SQLException ex) {
            Logger.getLogger(DrugDetailsController.class.getName()).log(Level.SEVERE, null, ex);
        }

        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        medicineName.setCellValueFactory(new PropertyValueFactory<>("medicineTypeName"));

        //TableView. 
        medicineTypeList.setItems(oblist);
    }

    
}
