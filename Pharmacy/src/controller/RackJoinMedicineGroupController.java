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
import model.drugDetailsModel;
import model.rackJoinMedicineGroupModel;

/**
 * FXML Controller class
 *
 * @author User
 */
public class RackJoinMedicineGroupController implements Initializable {

    @FXML
    private TableView<rackJoinMedicineGroupModel> rackJoinMedicineGroupList;
    @FXML
    private TableColumn<rackJoinMedicineGroupModel, String> id;
    @FXML
    private TableColumn<rackJoinMedicineGroupModel, String> genericName;
    @FXML
    private TableColumn<rackJoinMedicineGroupModel, String> rackName;

    ObservableList<rackJoinMedicineGroupModel> oblist = FXCollections.observableArrayList();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        rackJoinMedicineGroupTable();
    }

    public void rackJoinMedicineGroupTable() {
        try {

            Connection connection = DBConn.getConnection();
            // ResultSet resultSet = connection.createStatement().executeQuery("SELECT *FROM pharmacy_shop.tbl_rack_join_medicine_group;");
            ResultSet resultSet = connection.createStatement().executeQuery("SELECT tbl_rack_join_medicine_group.id AS joinId,tbl_rack_join_medicine_group.group_id , tbl_rack_join_medicine_group.rack_id, tbl_medicine_group.name AS groupName,tbl_rack.name AS rackName FROM tbl_rack_join_medicine_group, tbl_medicine_group, tbl_rack WHERE tbl_rack_join_medicine_group.group_id = tbl_medicine_group.id AND tbl_rack_join_medicine_group.rack_id = tbl_rack.id;");
//SELECT suppliers.supplier_id, suppliers.supplier_name, orders.order_date
//FROM suppliers 
//INNER JOIN orders
//ON suppliers.supplier_id = orders.supplier_id;
            while (resultSet.next()) {
//                String drugId = resultSet.getString("tbl_rack_join_medicine_group.id");
                oblist.add(new rackJoinMedicineGroupModel(resultSet.getString("joinId"), resultSet.getString("groupName"), resultSet.getString("rackName")));

            }

        } catch (SQLException ex) {
            Logger.getLogger(RackJoinMedicineGroupController.class.getName()).log(Level.SEVERE, null, ex);
        }

        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        genericName.setCellValueFactory(new PropertyValueFactory<>("genericName"));
        rackName.setCellValueFactory(new PropertyValueFactory<>("rackName"));

        //TableView. 
        rackJoinMedicineGroupList.setItems(oblist);
    }

}
