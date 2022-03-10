/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import JDBC.DBConn;
import Model.DrugGenericModel;
import Model.DrugListModel;
import Model.MedicalTestModel;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ObservableValue;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author devbi
 */
public class DrugListController implements Initializable {

    @FXML
    private BorderPane borderpane;

    Stage smallstage = null;
    private double xOffset = 0;
    private double yOffset = 0;

    //private ComboBox comboBox;
    @FXML
    private TableView<DrugListModel> drugListTable;
    @FXML
    private TableColumn<DrugListModel, String> id;
    @FXML
    private TableColumn<DrugListModel, String> drug_name;
    @FXML
    private TableColumn<DrugListModel, String> drug_generic_id;
    @FXML
    private TableColumn<DrugListModel, Boolean> action;
    @FXML
    private TableColumn<DrugListModel, String> drug_company_id;

    ObservableList<DrugListModel> oblist = FXCollections.observableArrayList();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

        try {

            Connection connection = DBConn.getConnection();
            ResultSet resultSet = connection.createStatement().executeQuery("SELECT * FROM prescription.tbl_drug t");
            while (resultSet.next()) {

                Button btn = new Button();
                btn.setMaxWidth(13);
                btn.setMaxHeight(13);
                btn.setLayoutX(0);
                btn.setStyle("-fx-background-color: green;");
                Image image1 = new Image("/assets/images/678134-128.png");
                ImageView img1 = new ImageView(image1);
                img1.setFitWidth(13);
                img1.setFitHeight(13);
                btn.setGraphic(img1);
                btn.setId(resultSet.getString("id"));
                btn.setOnAction(new EventHandler<ActionEvent>() {

                    @Override
                    public void handle(ActionEvent t) {
                        System.out.println("Button Clicked ID : " + btn.getId());
                        openPopUpWindow(btn.getId());
                    }

                });

                oblist.add(new DrugListModel(resultSet.getString("id"), resultSet.getString("drug_name"), resultSet.getString("drug_generic_id"), resultSet.getString("drug_company_id"), btn));

            }

        } catch (SQLException ex) {
            Logger.getLogger(MedicalTestController.class.getName()).log(Level.SEVERE, null, ex);
        }

        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        drug_name.setCellValueFactory(new PropertyValueFactory<>("drug_name"));
        drug_generic_id.setCellValueFactory(new PropertyValueFactory<>("drug_generic_id"));
        drug_company_id.setCellValueFactory(new PropertyValueFactory<>("drug_company_id"));
        action.setCellValueFactory(new PropertyValueFactory<>("Action"));

        //TableView. 
        drugListTable.setItems(oblist);

    }

    public String openPopUpWindow(String id) {

        try {

            FXMLLoader fXMLLoader = new FXMLLoader(getClass().getResource("/view/DrugListPopup.fxml"));
            Parent root2 = (Parent) fXMLLoader.load();
            DrugListPopupController pc = fXMLLoader.getController();
            pc.getRulesForPatient(Integer.valueOf(id));

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
            Logger.getLogger(ChiefComplaintsController.class.getName()).log(Level.SEVERE, null, e);
            System.out.println("Can't load new windows");
        }

        return "";

    }

    @FXML
    private void addDrug(MouseEvent event) throws IOException {
        Parent Prescription = FXMLLoader.load(getClass().getResource("/view/AddDrug.fxml"));
        borderpane.setCenter(Prescription);
    }

}
