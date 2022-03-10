/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import JDBC.DBConn;
import Model.OwnExaminationModel;
import Model.PrescriptionListModel;
import com.sun.prism.impl.Disposer.Record;
import static controller.MasterPage.stage;
import helper.DynaimicViews;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
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
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import static javafx.scene.input.KeyCode.T;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import static javafx.scene.paint.Color.color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;
import static org.controlsfx.control.action.ActionMap.action;

/**
 * FXML Controller class
 *
 * @author devbi
 */
public class PrescriptionListController implements Initializable {

    Stage smallstage = null;
    private double xOffset = 0;
    private double yOffset = 0;

    @FXML
    private Button AddPrescription;
    @FXML
    private BorderPane borderpane;

    ObservableList<PrescriptionListModel> oblist = FXCollections.observableArrayList();

    @FXML
    private TableView<PrescriptionListModel> table;
    @FXML
    private TableColumn<PrescriptionListModel, String> id;
    @FXML
    private TableColumn<PrescriptionListModel, String> patientIds;
    @FXML
    private TableColumn<PrescriptionListModel, String> clainteIds;
    @FXML
    private TableColumn<PrescriptionListModel, String> referanceIds;
    @FXML
    private TableColumn<PrescriptionListModel, String> prescriptionDate;
    @FXML
    private TableColumn<PrescriptionListModel, String> chiefComplaintsIds;
    @FXML
    private TableColumn<PrescriptionListModel, String> testIds;
    @FXML
    private TableColumn<PrescriptionListModel, String> nextVisitDate;
    @FXML
    private TableColumn<PrescriptionListModel, String> rulesForPatientIds;
    @FXML
    private TableColumn<PrescriptionListModel, String> insertTime;
//    @FXML
//    private TableColumn<PrescriptionListModel, String> insertBy;
    @FXML
    private TableColumn<PrescriptionListModel, Boolean> action;

    //action.setSortable(false);
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //   // TODO

        try {
            // TODO

            Connection connection = DBConn.getConnection();
            ResultSet resultSet = connection.createStatement().executeQuery("SELECT * FROM prescription.tbl_prescription t");
            while (resultSet.next()) {
                //  id, pid, client_id, reference_id, prescription_date, chief_complaint_ids, test_ids, next_visit_date, rules_for_patient_ids, insert_time, insert_by       
                oblist.add(new PrescriptionListModel(resultSet.getString("id"), resultSet.getString("pid"), resultSet.getString("client_id"), resultSet.getString("reference_id"), resultSet.getString("prescription_date"), resultSet.getString("chief_complaint_ids"), resultSet.getString("test_ids"), resultSet.getString("next_visit_date"), resultSet.getString("rules_for_patient_ids"), resultSet.getString("insert_time"), resultSet.getString("insert_by")));

            }

        } catch (SQLException ex) {
            Logger.getLogger(OwnExaminationController.class.getName()).log(Level.SEVERE, null, ex);
        }

        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        patientIds.setCellValueFactory(new PropertyValueFactory<>("pid"));

        clainteIds.setCellValueFactory(new PropertyValueFactory<>("client_id"));
        referanceIds.setCellValueFactory(new PropertyValueFactory<>("reference_id"));
        prescriptionDate.setCellValueFactory(new PropertyValueFactory<>("prescription_date"));
        chiefComplaintsIds.setCellValueFactory(new PropertyValueFactory<>("chief_complaint_ids"));
        testIds.setCellValueFactory(new PropertyValueFactory<>("test_ids"));
        nextVisitDate.setCellValueFactory(new PropertyValueFactory<>("next_visit_date"));
        rulesForPatientIds.setCellValueFactory(new PropertyValueFactory<>("rules_for_patient_ids"));
        insertTime.setCellValueFactory(new PropertyValueFactory<>("insert_time"));
        // insertBy.setCellValueFactory(new PropertyValueFactory<>("insert_by"));

        action.setCellValueFactory(
                new Callback<TableColumn.CellDataFeatures<PrescriptionListModel, Boolean>, ObservableValue<Boolean>>() {

            @Override
            public ObservableValue<Boolean> call(TableColumn.CellDataFeatures<PrescriptionListModel, Boolean> p) {
                return new SimpleBooleanProperty(p.getValue() != null);
            }
        });

        action.setCellFactory(
                new Callback<TableColumn<PrescriptionListModel, Boolean>, TableCell<PrescriptionListModel, Boolean>>() {

            @Override
            public TableCell<PrescriptionListModel, Boolean> call(TableColumn<PrescriptionListModel, Boolean> p) {
                return new ButtonCell();
            }

        });

        //TableView. 
        table.setItems(oblist);

    }

    private class ButtonCell extends TableCell<PrescriptionListModel, Boolean> {

        final Button cellButton = new Button("");
        final Button updateButton = new Button("");

        ButtonCell() {

            //Action when the button is pressed
            cellButton.setOnAction(new EventHandler<ActionEvent>() {

                @Override
                public void handle(ActionEvent t) {
                    // get Selected Item
                    PrescriptionListModel currentList = (PrescriptionListModel) ButtonCell.this.getTableView().getItems().get(ButtonCell.this.getIndex());
                    //remove selected item from the table list
                    oblist.remove(currentList);

                }
            });

            updateButton.setOnAction(new EventHandler<ActionEvent>() {

                @Override
                public void handle(ActionEvent t) {
                    // get Selected Item
                    // PrescriptionListModel currentList = (PrescriptionListModel) ButtonCell.this.getTableView().getItems().get(ButtonCell.this.getIndex());
                    //remove selected item from the table list
                    //  oblist.remove(currentList);
                }
            });

        }

        //Display button if the row is not empty
        @Override
        protected void updateItem(Boolean t, boolean empty) {
            super.updateItem(t, empty);
            if (!empty) {

//                setGraphic(updateButton);
//                updateButton.setMaxWidth(13);
//                updateButton.setMaxHeight(13);
//                updateButton.setLayoutX(0);
//                updateButton.setStyle("-fx-background-color: green;");
//                Image image1 = new Image("/assets/images/211652-128.png");
//                ImageView img1 = new ImageView(image1);
//                img1.setFitWidth(13);
//                img1.setFitHeight(13);
//                updateButton.setGraphic(img1);
                setGraphic(cellButton);
                cellButton.setMaxWidth(13);
                cellButton.setMaxHeight(13);
                cellButton.setStyle("-fx-background-color: #e62e00;");
                Image image = new Image("/assets/images/211652-128.png");
                ImageView img = new ImageView(image);
                img.setFitWidth(13);
                img.setFitHeight(13);
                cellButton.setGraphic(img);
            }

        }

    }

    @FXML
    private void AddPrescription(MouseEvent event) throws IOException {
        Parent Prescription = FXMLLoader.load(getClass().getResource("/view/PrescriptionGenerate.fxml"));
        borderpane.setCenter(Prescription);
    }

}
