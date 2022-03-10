/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import Database.DBCon;
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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.companyModel;
import model.medicineModel;

/**
 * FXML Controller class
 *
 * @author Debobrato Biswas
 */
public class MedicinesController implements Initializable {

    Stage smallstage = null;
    private double xOffset = 0;
    private double yOffset = 0;
    @FXML
    private TableView<medicineModel> drugListTable;
    @FXML
    private TableColumn<medicineModel, String> sl;
    @FXML
    private TableColumn<medicineModel, String> drugNameCloumn;
    @FXML
    private TableColumn<medicineModel, String> drugPriceCloumn;
    @FXML
    private TableColumn<medicineModel, String> drugCompanyCloumn;
    @FXML
    private TableColumn<medicineModel, String> drugPowerCloumn;
    @FXML
    private TableColumn<medicineModel, String> quantityCloumn;
    @FXML
    private TableColumn<medicineModel, String> mfgDateCloumn;
    @FXML
    private TableColumn<medicineModel, String> expDateCloumn;
    @FXML
    private TableColumn<medicineModel, String> insetDateCloumn;
    @FXML
    private TableColumn<medicineModel, String> updateDateCloumn;
    @FXML
    private TableColumn<medicineModel, String> drugType;
    ObservableList<medicineModel> oblist = FXCollections.observableArrayList();

    ArrayList<String> medicine = null;
    ArrayList<String> medicineId = null;
    @FXML
    private TextField searchMedicineText;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        medicineList();

        ////????????????? Find The word Start ???????????????\\\\\\\\\\\\\\\\
        String sql = "SELECT * FROM ashamedicalhall.tbl_medicine";
        try {

            Connection connection = DBCon.getConnection();
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            medicine = new ArrayList<String>();
            medicineId = new ArrayList<String>();

            while (rs.next()) {

                String drugNameInput = rs.getString("drug_name");
                medicine.add(drugNameInput);

                int drugIdIdInput = rs.getInt("drug_id");
                medicineId.add(String.valueOf(drugIdIdInput));
                System.out.println("Medicine ID" + drugIdIdInput);

            }

            new AutoCompleteTextField().bindAutoCompletion(searchMedicineText, 15, true, medicine);
            System.out.println("Medicine" + medicine);
            System.out.println("Medicine ID" + medicineId); 

        } catch (SQLException e) {

            System.out.println(e.getMessage());
            System.out.println("Medicine:" + medicine);
        }
    }

    public void medicineList() {
        try {

            Connection connection = DBCon.getConnection();
            ResultSet resultSet = connection.createStatement().executeQuery("SELECT * FROM ashamedicalhall.tbl_medicine t;");
            while (resultSet.next()) {

//, , , , , , ,                                                                                                                                                                                                                                                                                              , , , , 
                oblist.add(new medicineModel(resultSet.getString("drug_id"), resultSet.getString("drug_name"), resultSet.getString("drug_power"), resultSet.getString("drug_company"), resultSet.getString("drug_price"), resultSet.getString("menufecture_date"), resultSet.getString("expair_date"), resultSet.getString("insert_date"), resultSet.getString("update_date"), resultSet.getString("drug_quantity"), resultSet.getString("is_online"), resultSet.getString("drug_type")));

            }

        } catch (SQLException ex) {
            Logger.getLogger(MedicinesController.class.getName()).log(Level.SEVERE, null, ex);
        }
        //drug_id, drug_name, drug_power, drug_company, drug_price, menufecture_date, 
        //expair_date, insert_date, update_date, drug_quantity, is_online, drug_type;
        sl.setCellValueFactory(new PropertyValueFactory<>("drug_id"));
        drugNameCloumn.setCellValueFactory(new PropertyValueFactory<>("drug_name"));
        drugPriceCloumn.setCellValueFactory(new PropertyValueFactory<>("drug_price"));
        drugCompanyCloumn.setCellValueFactory(new PropertyValueFactory<>("drug_company"));
        drugPowerCloumn.setCellValueFactory(new PropertyValueFactory<>("drug_power"));

        quantityCloumn.setCellValueFactory(new PropertyValueFactory<>("drug_quantity"));
        mfgDateCloumn.setCellValueFactory(new PropertyValueFactory<>("menufecture_date"));
        expDateCloumn.setCellValueFactory(new PropertyValueFactory<>("expair_date"));
        insetDateCloumn.setCellValueFactory(new PropertyValueFactory<>("insert_date"));

        updateDateCloumn.setCellValueFactory(new PropertyValueFactory<>("update_date"));
        drugType.setCellValueFactory(new PropertyValueFactory<>("drug_type"));

        //TableView. 
        drugListTable.setItems(oblist);
    }

    @FXML
    private void addMedicinePopup(ActionEvent event) {
        if (smallstage == null) {

            try {

                FXMLLoader fXMLLoader = new FXMLLoader(getClass().getResource("/view/addMedicinePopUp.fxml"));
                Parent root2 = (Parent) fXMLLoader.load();
//                PrescriptionGeneratePopupController pc = fXMLLoader.getController();
//                pc.setParams(this);
                Scene scene = new Scene(root2);
                smallstage = new Stage();

                smallstage.initStyle(StageStyle.UNDECORATED);
                root2.setOnMousePressed(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        xOffset = event.getSceneX();
                        yOffset = event.getSceneY();
                    }
                });
                root2.setOnMouseDragged(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        smallstage.setX(event.getScreenX() - xOffset);
                        smallstage.setY(event.getScreenY() - yOffset);
                    }
                });

                smallstage.setScene(scene);
                smallstage.show();

            } catch (Exception e) {
                System.out.println("Can't load new windows");
            }
        } else if (smallstage.isShowing()) {
            smallstage.toFront();
        } else {
            smallstage.show();
        }

    }

    @FXML
    private void searchMedicine(ActionEvent event) {
        String searchProductName = searchMedicineText.getText();
        System.out.println("ENGLISH::" + searchProductName);
//medicine  medicineId
        int searchIndex = medicine.indexOf(searchProductName);
        String searchId = medicineId.get(searchIndex);

        System.out.println("PRO ID::" + searchId);

        String translateSql = "SELECT * FROM ashamedicalhall.tbl_medicine WHERE drug_id IN (" + searchId + ");  ";

        try {
            Connection connection = DBCon.getConnection();
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(translateSql);

            while (rs.next()) {
                String drugNameInput = rs.getString("drug_name");
//                String nounWordInput = rs.getString("noun");
//                String verbWordInput = rs.getString("verb");
//                String synonymWordInput = rs.getString("synonym");
//                String antonymWordInput = rs.getString("antonym");
                System.out.println("Drug Name :::" + drugNameInput);
                //BanglaWord.setText(banglaInput);

            }

        } catch (SQLException ex) {
            Logger.getLogger(MedicinesController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void searchMedicineButton(ActionEvent event) {
    }

}
