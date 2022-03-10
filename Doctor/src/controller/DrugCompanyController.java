/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import JDBC.DBConn;
import Model.ComplaintsModel;
import Model.DrugCompanyModel;
import Model.MedicalTestModel;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;

/**
 * FXML Controller class
 *
 * @author devbi
 */
public class DrugCompanyController implements Initializable {

    Stage smallstage = null;
    private double xOffset = 0;
    private double yOffset = 0;

    @FXML
    private TableView<DrugCompanyModel> drugCompanyTable;
    @FXML
    private TableColumn<DrugCompanyModel, String> id;
    @FXML
    private TableColumn<DrugCompanyModel, String> company_name;
    @FXML
    private TableColumn<DrugCompanyModel, String> priority;
    @FXML
    private TableColumn<DrugCompanyModel, Boolean> action;

    @FXML
    private TextField companyNameInput;
    @FXML
    private TextField priorityInput;

    ObservableList<DrugCompanyModel> oblist = FXCollections.observableArrayList();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

        try {

            Connection connection = DBConn.getConnection();
            ResultSet resultSet = connection.createStatement().executeQuery("SELECT * FROM prescription.tbl_drug_company t");
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

                oblist.add(new DrugCompanyModel(resultSet.getString("id"), resultSet.getString("company_name"), resultSet.getString("priority"), btn));

            }

        } catch (SQLException ex) {
            Logger.getLogger(DrugCompanyController.class.getName()).log(Level.SEVERE, null, ex);
        }

        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        company_name.setCellValueFactory(new PropertyValueFactory<>("company_name"));
        priority.setCellValueFactory(new PropertyValueFactory<>("priority"));
        action.setCellValueFactory(new PropertyValueFactory<>("Action"));

        //TableView. 
        drugCompanyTable.setItems(oblist);

    }

    public String openPopUpWindow(String id) {

        try {

            FXMLLoader fXMLLoader = new FXMLLoader(getClass().getResource("/view/DrugCompanyPopup.fxml"));
            Parent root2 = (Parent) fXMLLoader.load();
            DrugCompanyPopupController pc = fXMLLoader.getController();
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

    private static java.sql.Date getCurrentDate() {
        java.util.Date today = new java.util.Date();
        return new java.sql.Date(today.getTime());

    }

    private static java.sql.Timestamp getCurrentTimeStamp() {

        java.util.Date today = new java.util.Date();
        return new java.sql.Timestamp(today.getTime());

    }

    @FXML
    private void drugCompanyNameSave(ActionEvent event) throws SQLException {

        Image img = new Image("/images/success5.gif");
        Notifications notifi = Notifications.create().title("Save Successfull").text("Successfull").graphic(new ImageView(img)).hideAfter(Duration.seconds(3)).position(Pos.TOP_CENTER);

        String companyName = companyNameInput.getText();
        String companyPriority = priorityInput.getText();

        Connection connection = DBConn.getConnection();
        String drugCompanySQL = "INSERT INTO prescription.tbl_drug_company (company_name, priority, insert_time, doctor_id) VALUES(?,?,?,?)";
        // PreparedStatement=null;id, complaint, doctor_id, insert_time, last_update
        try {

            PreparedStatement pst = connection.prepareStatement(drugCompanySQL);
            pst.setString(1, companyName);
            pst.setString(2, companyPriority);
            pst.setDate(3, getCurrentDate());
            pst.setTimestamp(3, getCurrentTimeStamp());
            pst.setString(4, "3");
            pst.execute();
            pst.close();
            companyNameInput.clear();
            priorityInput.clear();

            notifi.show();

        } catch (SQLException e) {
            Logger.getLogger(ChiefComplaintsController.class.getName()).log(Level.SEVERE, null, e);
            System.out.println(e);
            Notifications notificaton = Notifications.create().title("Save Unsuccessful").text("Unsuccessful").graphic(null).hideAfter(Duration.seconds(3)).position(Pos.TOP_CENTER);

            notificaton.showError();
        }

    }

}
