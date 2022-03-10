/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import Database.DBCon;
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
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.companyModel;

/**
 * FXML Controller class
 *
 * @author Debobrato Biswas
 */
public class CompaniesController implements Initializable {

    Stage smallstage = null;
    private double xOffset = 0;
    private double yOffset = 0;
    @FXML
    private TableView<companyModel> companyShowTable;
    @FXML
    private TableColumn<companyModel, String> SL;
    @FXML
    private TableColumn<companyModel, String> CompanyName;

    ObservableList<companyModel> oblist = FXCollections.observableArrayList();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
       
        companyList();
    }

    @FXML
    private void addCompany(ActionEvent event) {
        if (smallstage == null) {

            try {

                FXMLLoader fXMLLoader = new FXMLLoader(getClass().getResource("/view/addCompaniesPopup.fxml"));
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

    public void companyList() {
        try {

            Connection connection = DBCon.getConnection();
            ResultSet resultSet = connection.createStatement().executeQuery("SELECT * FROM ashamedicalhall.tbl_companies t;");
            while (resultSet.next()) {

                ////company_id, company_name, insert_time
                oblist.add(new companyModel(resultSet.getString("company_id"), resultSet.getString("company_name")));

            }

        } catch (SQLException ex) {
            Logger.getLogger(CompaniesController.class.getName()).log(Level.SEVERE, null, ex);
        }

        SL.setCellValueFactory(new PropertyValueFactory<>("SL"));
        CompanyName.setCellValueFactory(new PropertyValueFactory<>("CompanyModelName"));

        //TableView. 
        companyShowTable.setItems(oblist);
    }
}
