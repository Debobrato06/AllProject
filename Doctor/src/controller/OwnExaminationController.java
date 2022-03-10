/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import JDBC.DBConn;
import Model.DrugTakeFormatModel;
import Model.OwnExaminationModel;
import Model.RulesForPatientModel;
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
public class OwnExaminationController implements Initializable {

    Stage smallstage = null;
    private double xOffset = 0;
    private double yOffset = 0;

    @FXML
    private TextField ownExaminationInput;

    @FXML
    private TableView<OwnExaminationModel> ownExaminationTable;
    @FXML
    private TableColumn<OwnExaminationModel, String> id;
    @FXML
    private TableColumn<OwnExaminationModel, String> OwnExaminationTitle;
    @FXML
    private TableColumn<OwnExaminationModel, Boolean> action;

    ObservableList<OwnExaminationModel> oblist = FXCollections.observableArrayList();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

        try {
            // TODO

            Connection connection = DBConn.getConnection();
            ResultSet resultSet = connection.createStatement().executeQuery("SELECT * FROM prescription.tbl_own_examinations t");
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

                oblist.add(new OwnExaminationModel(resultSet.getString("id"), resultSet.getString("oe_title"), btn));

            }

        } catch (SQLException ex) {
            Logger.getLogger(OwnExaminationController.class.getName()).log(Level.SEVERE, null, ex);
        }

        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        OwnExaminationTitle.setCellValueFactory(new PropertyValueFactory<>("oe_title"));
        action.setCellValueFactory(new PropertyValueFactory<>("Action"));
     
        System.out.println("dsgdg"+OwnExaminationTitle);
        //TableView. 
        ownExaminationTable.setItems(oblist);

    }

    private static java.sql.Date getCurrentDate() {
        java.util.Date today = new java.util.Date();
        return new java.sql.Date(today.getTime());

    }

    private static java.sql.Timestamp getCurrentTimeStamp() {

        java.util.Date today = new java.util.Date();
        return new java.sql.Timestamp(today.getTime());

    }

    public String openPopUpWindow(String id) {

        try {

            FXMLLoader fXMLLoader = new FXMLLoader(getClass().getResource("/view/OwnExaminationUpdatePopup.fxml"));
            Parent root2 = (Parent) fXMLLoader.load();
            OwnExaminationUpdatePopupController pc = fXMLLoader.getController();
            pc.getOwnExamination(Integer.valueOf(id));

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
    private void ownExaminationInputSave(ActionEvent event) throws SQLException {

        Image img = new Image("/images/success5.gif");

        Notifications notifi = Notifications.create().title("Save Successfull").text("Successfull").graphic(new ImageView(img)).hideAfter(Duration.seconds(3)).position(Pos.TOP_CENTER);

        String ownExamination = ownExaminationInput.getText();

        Connection connection = DBConn.getConnection();
        String ownExaminationSQL = "INSERT INTO prescription.tbl_own_examinations (oe_title, doctor_id, insert_by, insert_time) VALUES(?,?,?,?)";
        
        try {

            PreparedStatement pst = connection.prepareStatement(ownExaminationSQL);
            pst.setString(1, ownExamination);
            pst.setString(2, "0");
            pst.setString(3, "0");
            pst.setDate(4, getCurrentDate());
            pst.setTimestamp(4, getCurrentTimeStamp());

            pst.execute();
            pst.close();
            ownExaminationInput.clear();

            notifi.show();

        } catch (SQLException e) {
            Logger.getLogger(ChiefComplaintsController.class.getName()).log(Level.SEVERE, null, e);
            System.out.println(e);
            Notifications notificaton = Notifications.create().title("Save Unsuccessful").text("Unsuccessful").graphic(null).hideAfter(Duration.seconds(3)).position(Pos.TOP_CENTER);

            notificaton.showError();
        }

    }

}
