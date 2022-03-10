/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import JDBC.DBConn;
import static controller.MasterPage.stage;
import helper.AutoCompleteTextField;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.concurrent.Callable;
import static java.util.concurrent.Executors.callable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;
import javafx.util.Callback;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;

/**
 * FXML Controller class
 *
 * @author devbi
 */
public class PrescriptionGeneratePopupController implements Initializable {

    ArrayList<String> drugNameFilter = null;
    ArrayList<String> drugIdFilter = null;

    ArrayList<String> drugTypeName = null;
    ArrayList<String> drugTypeIds = null;

    ArrayList<String> scheduleItem = null;
    ArrayList<String> scheduleId = null;

    ArrayList<String> timeItem = null;
    ArrayList<String> timeId = null;

    @FXML
    private ComboBox<String> drugType;
    @FXML
    private TextField flterDrug_id;
    @FXML
    private TextField power;
    @FXML
    private ComboBox<String> schedule;
    @FXML
    private ComboBox<String> unit;
    @FXML
    private ComboBox<String> duration;
    @FXML
    private ComboBox<String> time;
    @FXML
    private TextField duration_number;
    @FXML
    private TextField timeNumber;
    @FXML
    private TextField continueValue;
    @FXML
    private Label minute;
    @FXML
    private Button save;

    PrescriptionGenerateController mController;
    public Stage stages = new Stage();
    @FXML
    private Button reset;
    @FXML
    private Button close;

    /**
     * Initializes the controller class.
     */
    public void comboBoxWasUpdate() {

        unit.getValue().toString();
        duration.getValue().toString();
        time.getValue().toString();

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        unit.getItems().addAll("Unit 1/2", "Unit 1/3", "Unit 1/4", "Unit 2/4", "Unit 3/4", "Unit 1", "Unit 2", "Unit 3", "Unit 4", "Unit 5", "Unit 6", "Unit 7", "Unit 8");

        // unit.getItems().addAll("মাত্রা ১/২", "মাত্রা ১/৩", "মাত্রা ১/৪", "মাত্রা ২/৪", "মাত্রা ১", "মাত্রা ২", "মাত্রা ৩", "মাত্রা ৪", "মাত্রা ৫", "মাত্রা ৬", "মাত্রা ৭", "মাত্রা ৮", "মাত্রা ৯");
        //////////
        try {
            Connection connection = DBConn.getConnection();
            ResultSet resultSet = connection.createStatement().executeQuery("SELECT * FROM prescription.tbl_drug_continue_rules t");

            while (resultSet.next()) {

                duration.getItems().add(resultSet.getString("type_name_en"));
                System.out.println("Duration name" + resultSet.getString("type_name_en"));
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println("duration:");
        }
        //////////
        //////////
        try {
            Connection connection = DBConn.getConnection();
            ResultSet resultSet = connection.createStatement().executeQuery("SELECT * FROM prescription.tbl_drug_take_time_rules t");

            timeItem = new ArrayList<String>();
            timeId = new ArrayList<String>();
            while (resultSet.next()) {

                time.getItems().add(resultSet.getString("drug_take_time_en"));

                String time_item = resultSet.getString("drug_take_time_en");
                timeItem.add(time_item);
                int time_id = resultSet.getInt("id");
                timeId.add(String.valueOf(time_id));
            }

            System.out.println("Item name" + timeItem);
            System.out.println("Item id" + timeId);

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println("timeItem is not show in the combobox.");
        }
        //////////
        try {
            Connection connection = DBConn.getConnection();
            ResultSet resultSet = connection.createStatement().executeQuery("SELECT drug_name,id FROM tbl_drug");

            drugNameFilter = new ArrayList<String>();
            drugIdFilter = new ArrayList<String>();

            while (resultSet.next()) {
                String Drug_Name = resultSet.getString("drug_name");
                drugNameFilter.add(Drug_Name);
                int drug_generic_id = resultSet.getInt("id");
                drugIdFilter.add(String.valueOf(drug_generic_id));
            }

            new AutoCompleteTextField().bindAutoCompletion(flterDrug_id, 15, true, drugNameFilter);
            System.out.println("Drug  name" + drugNameFilter);
            System.out.println("Drug id" + drugIdFilter);

        } catch (SQLException e) {

            System.out.println(e.getMessage());
            System.out.println("drugNameFilter:");
        }

        ///////////////  
        try {
            Connection connection = DBConn.getConnection();

            ResultSet resultSet = connection.createStatement().executeQuery("SELECT * FROM tbl_drug_type");

            drugTypeName = new ArrayList<String>();
            drugTypeIds = new ArrayList<String>();

            while (resultSet.next()) {
                drugType.getItems().add(resultSet.getString("name"));

                String eat_format = resultSet.getString("name");
                drugTypeName.add(eat_format);
                int drugType_id = resultSet.getInt("id");
                drugTypeIds.add(String.valueOf(drugType_id));
            }
            System.out.println("DrugTypeName name" + drugTypeName);
            System.out.println("DrugTypeIds name" + drugTypeIds);

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println("Can't loaded the page");
        }

/////////
        try {
            Connection connection = DBConn.getConnection();

            ResultSet resultSet = connection.createStatement().executeQuery("SELECT eat_format,id FROM tbl_drug_take_format");

            scheduleItem = new ArrayList<String>();
            scheduleId = new ArrayList<String>();

            while (resultSet.next()) {
                schedule.getItems().add(resultSet.getString("eat_format"));

                String eat_format = resultSet.getString("eat_format");
                scheduleItem.add(eat_format);
                int schedule_id = resultSet.getInt("id");
                scheduleId.add(String.valueOf(schedule_id));
            }
            System.out.println("Schedule name" + scheduleItem);
            System.out.println("Schedule name" + scheduleId);

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println("Schedule name is not show .");
        }

/////////
    }

    public void setParams(PrescriptionGenerateController controller) {
        this.mController = controller;

    }

    @FXML
    private void filterDrug(ActionEvent event) {
    }

    @FXML
    private void save(ActionEvent event) {
        // Notifications notifi = Notifications.create().title("Save Successfully").text(" Successfully ").graphic(null).hideAfter();
        // String drug_type_id = "";
        String drug_type = (String) drugType.getSelectionModel().getSelectedItem();
        int drug_typeIndex = drugType.getSelectionModel().getSelectedIndex();
        String drug_type_id = drugTypeIds.get(drug_typeIndex);

        System.out.println("Selected Index " + drug_typeIndex);
        System.out.println("Selected ID " + drug_type_id);

        String drug_name = flterDrug_id.getText();
        String drug_power = power.getText();
        String scheduleName = (String) schedule.getSelectionModel().getSelectedItem();
        String unitValue = (String) unit.getSelectionModel().getSelectedItem();
        String duration_value = duration_number.getText();
        String durationName = (String) duration.getSelectionModel().getSelectedItem();
        String continue_value = continueValue.getText();
        String time_value = timeNumber.getText();
        String minute_value = minute.getText();
        String drug_take_time_rules = (String) time.getSelectionModel().getSelectedItem();

        int drug_name_index = drugNameFilter.indexOf(drug_name);
        String drug_id = drugIdFilter.get(drug_name_index);

        setParams(mController);

        String bottom_text = "";
        if (!scheduleName.isEmpty()) {
            bottom_text += scheduleName + ",  ";
        }
        if (!unitValue.isEmpty()) {
            bottom_text += unitValue + ",  ";

        }
        if (!duration_value.toString().isEmpty()) {
            bottom_text += duration_value.toString() + " ";
        }
        if (!durationName.isEmpty()) {
            bottom_text += durationName + "  ";
        }
        if (!continue_value.isEmpty()) {
            bottom_text += continue_value + ",  ";
        }

        if (!time_value.toString().isEmpty()) {
            bottom_text += time_value.toString() + "  ";
        }
        if (!minute_value.toString().isEmpty()) {
            bottom_text += minute_value.toString() + ",  ";
        }
        if (!drug_take_time_rules.isEmpty()) {
            bottom_text += drug_take_time_rules;
        }

        //0 = drug name, 1 = drug id, 2 = drug type id, 3 = power, 4 = bottom text
        mController.prescriptionMedicineDetails(drug_name, drug_id, drug_type_id, drug_type, drug_power, bottom_text);

        Stage stage = (Stage) close.getScene().getWindow();
        stage.close();

    }

    @FXML
    private void reset(ActionEvent event) {
        //drugNameFilter.clear();
        flterDrug_id.clear();
        power.clear();
        duration_number.clear();
        timeNumber.clear();
        schedule.getSelectionModel().clearSelection();
        duration.getSelectionModel().clearSelection();
        time.getSelectionModel().clearSelection();
        unit.getSelectionModel().clearSelection();
        drugType.getSelectionModel().clearSelection();

    }

    @FXML
    private void close(ActionEvent event) {
        Stage stage = (Stage) close.getScene().getWindow();
        stage.close();
    }

}
