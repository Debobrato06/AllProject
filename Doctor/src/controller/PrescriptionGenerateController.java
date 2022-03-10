/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import controller.PrescriptionGeneratePopupController;
import controller.PrescriptionGeneratePrintController;
import JDBC.DBConn;
import Model.DrugCompanyModel;
import Model.DrugListModel;
import Model.OwnExaminationModel;
import Model.RulesForPatientModel;
import controller.ChiefComplaintsController;
import static controller.MasterPage.stage;
import helper.AutoCompleteTextField;
import impl.org.controlsfx.autocompletion.AutoCompletionTextFieldBinding;
import javafx.stage.Stage;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.print.PageLayout;
import javafx.print.PageOrientation;
import javafx.print.Paper;
import javafx.print.Printer;
import javafx.print.PrinterJob;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.StageStyle;
import javafx.stage.Window;
import javafx.stage.WindowEvent;
import javafx.util.Callback;
import static javax.management.Query.value;
import static jdk.nashorn.tools.ShellFunctions.input;
import np.com.ngopal.control.AutoFillTextBox;
import org.controlsfx.control.CheckComboBox;
import org.controlsfx.control.IndexedCheckModel;

/**
 * FXML Controller class
 *
 * @author devbi
 */
public class PrescriptionGenerateController implements Initializable {

    Connection connection;
    Stage smallstage = null;
    Stage smallstage2 = null;

    List<List<String>> prescription_medicine = new ArrayList<List<String>>();

    // 0 = Name, 1 = Value, 2 = Index or ID
    List<List<String>> own_examination = new ArrayList<List<String>>();

    List<String> lstFile;
    ObservableList<DrugListModel> oblist = FXCollections.observableArrayList();

    //0 = drug name, 1 = drug id, 2 = drug type id, 3 = power, 4 = bottom text
    ArrayList<String> comboBoxRefarenceName = null;
    ArrayList<String> comboBoxRefarenceIds = null;

    ArrayList<String> checkComboBoxChiefCompalintsName = null;
    ArrayList<String> checkComboBoxChiefCompalintsIds = null;

    ArrayList<String> checkComboBoxAdviceTestName = null;
    ArrayList<String> checkComboBoxAdviceTestIds = null;

    ArrayList<String> checkComboBoxAdviceName = null;
    ArrayList<String> checkComboBoxAdviceIds = null;

    ArrayList<String> genderName = null;
    ArrayList<String> genderIds = null;

    /////////////Array List End /////
    int initialIdex = 0;
    PrescriptionGeneratePrintController display;
    private double xOffset = 0;
    private double yOffset = 0;

    @FXML
    private CheckComboBox<String> chiefComplaintsName;
    private CheckBox ownExamination;
    @FXML
    private CheckComboBox<String> testName;
    @FXML
    private CheckComboBox<String> adviceName;
    @FXML
    private DatePicker NextVisitDate;

    @FXML
    private Button generatePrescriptionIds;
    @FXML
    private AnchorPane anchorPane;

    ////?????Pane ID start ????////
    private Pane pane;

    ////?????Patient Informetion ID start ????////
    @FXML
    private TextField patientName;
    @FXML
    private TextField patientAge;
    @FXML
    private TextField patientPhone;
    @FXML
    private ComboBox Gender;
    @FXML
    private DatePicker date;
    @FXML
    private TextField patientAddress;
    @FXML
    private ComboBox Reference;
    ////?????Patient Informetion ID End ????////
    @FXML
    private VBox vbxInner;
    @FXML
    private VBox vBoxOwnExamination;

    ////?????Drug Informetion ID End ????////
    public PrescriptionGenerateController() throws SQLException {
        this.connection = DBConn.getConnection();
    }

    public void setParams(PrescriptionGeneratePrintController controller) {
        this.display = controller;

    }

    /**
     * Initializes the controller class.
     *
     */
    public void comboBoxWasUpdate() {

        Gender.getValue().toString();
        Reference.getValue().toString();

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        // TODO
        getGender();
        getReference();
        getChiefComplaints();
        getMedicalTest();
        getRulesForPatient();

    }

    private void getGender() {
        try {

            ResultSet resultSet = connection.createStatement().executeQuery("SELECT * FROM prescription.tbl_gender t");

            genderName = new ArrayList<String>();
            genderIds = new ArrayList<String>();

            while (resultSet.next()) {
                //Gender.getItems().add(resultSet.getString("gender"));idtbl_gender, gender, insert_time

                String gender_name = resultSet.getString("gender");
                Gender.getItems().add(gender_name);

                genderName.add(gender_name);
                int gender_ID = resultSet.getInt("idtbl_gender");
                genderIds.add(String.valueOf(gender_ID));

            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println("Gender List:");
        }
    }

    private void getReference() {
        try {
            ResultSet resultSet = connection.createStatement().executeQuery("SELECT * FROM prescription.tbl_reference t");

            comboBoxRefarenceName = new ArrayList<String>();
            comboBoxRefarenceIds = new ArrayList<String>();

            while (resultSet.next()) {
                //Refference.getItems().add(resultSet.getString("name"));

                String reffarence_name = resultSet.getString("name");
                Reference.getItems().add(reffarence_name);

                comboBoxRefarenceName.add(reffarence_name);
                int reffarence_ID = resultSet.getInt("id");
                comboBoxRefarenceIds.add(String.valueOf(reffarence_ID));

            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println("Refarence:");
        }

    }

    private void getChiefComplaints() {
        try {
            ResultSet resultSet = connection.createStatement().executeQuery("SELECT * FROM prescription.tbl_chief_complaints t");

            checkComboBoxChiefCompalintsName = new ArrayList<String>();
            checkComboBoxChiefCompalintsIds = new ArrayList<String>();

            while (resultSet.next()) {
                // CC.getItems().add(resultSet.getString("complaint"));

                String ChiefCompalints_name = resultSet.getString("complaint");
                chiefComplaintsName.getItems().add(ChiefCompalints_name);
                checkComboBoxChiefCompalintsName.add(ChiefCompalints_name);

                int ChiefCompalints_name_ID = resultSet.getInt("id");
                checkComboBoxChiefCompalintsIds.add(String.valueOf(ChiefCompalints_name_ID));

            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println("ChiefCompalints:");
        }

    }

    private void getMedicalTest() {
        try {
            ResultSet resultSet = connection.createStatement().executeQuery("SELECT * FROM prescription.tbl_medical_test t");

            checkComboBoxAdviceTestName = new ArrayList<String>();
            checkComboBoxAdviceTestIds = new ArrayList<String>();

            while (resultSet.next()) {

                String AdviceTest_name = resultSet.getString("test_name");
                testName.getItems().add(AdviceTest_name);
                checkComboBoxAdviceTestName.add(AdviceTest_name);

                int AdviceTest_ID = resultSet.getInt("id");
                checkComboBoxAdviceTestIds.add(String.valueOf(AdviceTest_ID));

            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println("AdviceTest:");
        }

    }

    private void getRulesForPatient() {
        try {
            ResultSet resultSet = connection.createStatement().executeQuery("SELECT * FROM prescription.tbl_rules_for_patient t");

            checkComboBoxAdviceName = new ArrayList<String>();
            checkComboBoxAdviceIds = new ArrayList<String>();

            while (resultSet.next()) {

                String Advice_name = resultSet.getString("rules");
                adviceName.getItems().add(Advice_name);
                checkComboBoxAdviceName.add(Advice_name);

                int Advice_ID = resultSet.getInt("id");
                checkComboBoxAdviceIds.add(String.valueOf(Advice_ID));

            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println("Advice:");
        }

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
    void generatePrescription(ActionEvent event) throws SQLException {

        int client_id = saveClients();
        String chiefComplaintIds = getChiefComplaintIds();
        String testIds = getTestIds();
        String rulesForPatientIds = getRulesForPatientIds();

        String prescriptionDate = date.getValue().toString();
        String nextVisitDate = NextVisitDate.getValue().toString();
        String referenceName = Reference.getValue().toString();
        int referenceNameIndex = comboBoxRefarenceName.indexOf(referenceName);

        // Prescription Save Start
        String prescription = "INSERT INTO prescription.tbl_prescription (pid, client_id, reference_id, prescription_date, chief_complaint_ids, test_ids, next_visit_date, rules_for_patient_ids, insert_time, insert_by) VALUES(?,?,?,?,?,?,?,?,?,?)";
        int PrescriptionId = 0;
        try {

            PreparedStatement pst = connection.prepareStatement(prescription, Statement.RETURN_GENERATED_KEYS);

            pst.setString(1, "03");
            pst.setString(2, String.valueOf(client_id));
            pst.setString(3, comboBoxRefarenceIds.get(referenceNameIndex));
            pst.setString(4, prescriptionDate);
            pst.setString(5, chiefComplaintIds);
            pst.setString(6, testIds);
            pst.setString(7, nextVisitDate);
            pst.setString(8, rulesForPatientIds);
            pst.setTimestamp(9, getCurrentTimeStamp());
            pst.setString(10, "03");

            int affectedRows = pst.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Creating Client failed, no rows affected.");
            }

            try (ResultSet generatedKeys = pst.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    PrescriptionId = (int) generatedKeys.getLong(1);
                } else {
                    throw new SQLException("Creating Client failed, no ID obtained.");
                }
            }

            pst.close();

        } catch (SQLException e) {
            Logger.getLogger(ChiefComplaintsController.class.getName()).log(Level.SEVERE, null, e);
            System.out.println(e);
        }
        // Prescription Save End

        // Medicine Save 
        savePrescriptionMedicine(PrescriptionId);

        saveOwnExamination(PrescriptionId);

        //Open New stage and pass value to new stage. Start
        String PatientName = patientName.getText();
        String PatientAge = patientAge.getText();
        String gender = (String) Gender.getSelectionModel().getSelectedItem();
        String phone = patientPhone.getText();
        LocalDate Date = date.getValue();
        String Address = patientAddress.getText();
        String refference = (String) Reference.getSelectionModel().getSelectedItem();

        IndexedCheckModel<String> chiefComplaintName = chiefComplaintsName.getCheckModel();

        IndexedCheckModel<String> testNameInput = testName.getCheckModel();

        IndexedCheckModel<String> adviceNameInput = adviceName.getCheckModel();
        LocalDate NextVisitDateInput = NextVisitDate.getValue();

        try {

            List<List<String>> prescription_medicine1 = prescription_medicine;
            FXMLLoader fXMLLoader = new FXMLLoader(getClass().getResource("/view/PrescriptionGeneratePrint.fxml"));
            fXMLLoader.load();

            display = fXMLLoader.getController();
            display.setText(PatientName, PatientAge, gender, phone, Address, Date, refference, chiefComplaintName, testNameInput, adviceNameInput, NextVisitDateInput, prescription_medicine1, own_examination);

            Parent root = (Parent) fXMLLoader.getRoot();
            Scene scene = new Scene(root);
            Stage stage = new Stage();

            stage.initStyle(StageStyle.UNDECORATED);
            root.setOnMousePressed(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    xOffset = event.getSceneX();
                    yOffset = event.getSceneY();
                }
            });
            root.setOnMouseDragged(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    stage.setX(event.getScreenX() - xOffset);
                    stage.setY(event.getScreenY() - yOffset);
                }
            });
            stage.setScene(scene);

            stage.showAndWait();

        } catch (Exception e) {
            Logger.getLogger(PrescriptionGeneratePopupController.class.getName()).log(Level.SEVERE, null, e);
            System.out.println("Can't load new windows");
        }
        //Open New stage and pass value to new stage. End

    }

    @FXML
    private void addPrescriptionMedicin(ActionEvent event) {

        if (smallstage == null) {

            try {

                FXMLLoader fXMLLoader = new FXMLLoader(getClass().getResource("/view/PrescriptionGeneratePopup.fxml"));
                Parent root2 = (Parent) fXMLLoader.load();
                PrescriptionGeneratePopupController pc = fXMLLoader.getController();
                pc.setParams(this);
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

    private int saveClients() {

        int ClientReturnKey = 0;

        String PatientName = patientName.getText();

        String Phone = patientPhone.getText();
        String PatientAgeInsert = patientAge.getText();
        String gender = Gender.getValue().toString();
        String Address = patientAddress.getText();
        String refference = Reference.getValue().toString();
        int ComboBoxReffarenceIndex = comboBoxRefarenceName.indexOf(refference);

        String clients = "INSERT INTO prescription.tbl_clients ( phone,patient_name,gender,address, doctor_id ,insert_time) VALUES(?,?,?,?,?,?)";

        try {

            PreparedStatement pst = connection.prepareStatement(clients, Statement.RETURN_GENERATED_KEYS);
            pst.setString(1, Phone);
            pst.setString(2, PatientName);
            pst.setString(3, gender);
            pst.setString(4, Address);
            pst.setString(5, comboBoxRefarenceIds.get(ComboBoxReffarenceIndex));
            pst.setDate(6, getCurrentDate());
            pst.setTimestamp(6, getCurrentTimeStamp());

            int affectedRows = pst.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Creating Client failed, no rows affected.");
            }

            try (ResultSet generatedKeys = pst.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    ClientReturnKey = (int) generatedKeys.getLong(1);
                } else {
                    throw new SQLException("Creating Client failed, no ID obtained.");
                }
            }

            pst.close();

        } catch (SQLException e) {
            Logger.getLogger(ChiefComplaintsController.class.getName()).log(Level.SEVERE, null, e);
            System.out.println(e);
        }

        return ClientReturnKey;
    }

    void savePrescriptionMedicine(int PrescriptionId) {

        String prescription = "INSERT INTO prescription.tbl_prescription_medicine (prescription_id, drug_id, drug_type_id, power, bottom_text) VALUES(?,?,?,?,?)";

        try {

            PreparedStatement pst = connection.prepareStatement(prescription, Statement.RETURN_GENERATED_KEYS);

            //List<List<String>> prescription_medicine = new ArrayList<List<String>>();
            //0 = drug name, 1 = drug id, 2 = drug type id, 3 = power, 4 = bottom text
            for (int i = 0; i < prescription_medicine.size(); i++) {

                List<String> prescriptionMedicineDetails = prescription_medicine.get(i);
                pst.setString(1, String.valueOf(PrescriptionId));
                pst.setString(2, prescriptionMedicineDetails.get(1));
                pst.setString(3, prescriptionMedicineDetails.get(2));
                pst.setString(4, prescriptionMedicineDetails.get(3));
                pst.setString(5, prescriptionMedicineDetails.get(4));
                pst.execute();

            }

            pst.close();

        } catch (SQLException e) {
            Logger.getLogger(ChiefComplaintsController.class.getName()).log(Level.SEVERE, null, e);
            System.out.println(e);
        }

    }

    private String getChiefComplaintIds() {

        String ChiefComplaint = chiefComplaintsName.getCheckModel().getCheckedItems().toString();
        ChiefComplaint = ChiefComplaint.replace("[", "");
        ChiefComplaint = ChiefComplaint.replace("]", "");
        List<String> items = Arrays.asList(ChiefComplaint.split("\\s*,\\s*"));
        ChiefComplaint = "";
        for (int i = 0; i < items.size(); i++) {

            if (i > 0) {
                ChiefComplaint += ",";
            }
            int ChiefComplaintIndex = checkComboBoxChiefCompalintsName.indexOf(items.get(i));
            System.out.println("ChiefComplaint item: " + items.get(i));
            ChiefComplaint += checkComboBoxChiefCompalintsIds.get(ChiefComplaintIndex);

        }
        return ChiefComplaint;
    }

    private String getTestIds() {

        String testIds = testName.getCheckModel().getCheckedItems().toString();
        testIds = testIds.replace("[", "");
        testIds = testIds.replace("]", "");
        List<String> item = Arrays.asList(testIds.split("\\s*,\\s*"));

        testIds = "";

        for (int i = 0; i < item.size(); i++) {

            if (i > 0) {
                testIds += ",";
            }
            int TestIndex = checkComboBoxAdviceTestName.indexOf(item.get(i));
            System.out.println("Test item: " + item.get(i));
            testIds += checkComboBoxAdviceTestIds.get(TestIndex);

        }
        return testIds;
    }

    private String getRulesForPatientIds() {

        checkComboBoxAdviceIds.size();

        String rulesForPatientIds = adviceName.getCheckModel().getCheckedItems().toString();
        rulesForPatientIds = rulesForPatientIds.replace("[", "");
        rulesForPatientIds = rulesForPatientIds.replace("]", "");
        List<String> itemss = Arrays.asList(rulesForPatientIds.split("\\s*,\\s*"));
        rulesForPatientIds = "";
        for (int i = 0; i < itemss.size(); i++) {

            if (i > 0) {
                rulesForPatientIds += ",";
            }
            int AdviceIndex = checkComboBoxAdviceName.indexOf(itemss.get(i));
            System.out.println(checkComboBoxAdviceIds.size());
            rulesForPatientIds += checkComboBoxAdviceIds.get(AdviceIndex);

        }
        return rulesForPatientIds;
    }

    public void prescriptionMedicineDetails(String drug_name, String drug_id, String drug_type_id, String drug_type, String drug_power, String bottom_text) {
        //List<List<String>> prescription_medicine = new ArrayList<List<String>>();
        //0 = drug name, 1 = drug id, 2 = drug type id, 3 = power, 4 = bottom text

        List<String> prescriptionMedicineDetails = new ArrayList<String>();
        prescriptionMedicineDetails.add(drug_name);
        prescriptionMedicineDetails.add(drug_id);
        prescriptionMedicineDetails.add(drug_type_id);
        prescriptionMedicineDetails.add(drug_power);
        prescriptionMedicineDetails.add(bottom_text);
        prescriptionMedicineDetails.add(drug_type);
        prescription_medicine.add(prescriptionMedicineDetails);

        System.out.println("Medicine Details : ");
        System.out.println(prescription_medicine);
        //medicine window reset.
        smallstage = null;

        if (prescription_medicine.size() > 0) {
            int i = prescription_medicine.size() - 1;
            List<String> prescriptionMedicineDetails2 = prescription_medicine.get(i);

            Pane temppane = new Pane();

            Label serial = new Label(String.valueOf(prescription_medicine.size()));
            serial.setLayoutX(40);
            serial.setLayoutY(10);
            temppane.getChildren().add(serial);

            Label medicinetype = new Label(drug_type);
            medicinetype.setLayoutX(60);
            medicinetype.setLayoutY(10);
            medicinetype.setTextFill(Color.web("#0076a3"));
            medicinetype.setStyle("-fx-font-weight: bold");
            temppane.getChildren().add(medicinetype);

            Label medicinename = new Label(prescriptionMedicineDetails2.get(0));
            medicinename.setLayoutX(110);
            medicinename.setLayoutY(10);
            medicinename.setTextFill(Color.web("#00134d"));
            medicinename.setFont(new Font("Arial", 15));
            medicinename.setStyle("-fx-font-weight: bold");
            temppane.getChildren().add(medicinename);

            Label medicinepower = new Label(drug_power);
            medicinepower.setLayoutX(210);
            medicinepower.setLayoutY(10);
            temppane.getChildren().add(medicinepower);

            Label bottomtext = new Label(bottom_text);
            bottomtext.setLayoutX(60);
            bottomtext.setLayoutY(30);
            temppane.getChildren().add(bottomtext);

            vbxInner.getChildren().add(temppane);

        }

    }

    @FXML
    private void addOwnExamination(ActionEvent event) {

        if (smallstage2 == null) {

            try {

                FXMLLoader fXMLLoader = new FXMLLoader(getClass().getResource("/view/OwnExaminationGeneratePopup.fxml"));
                Parent root2 = (Parent) fXMLLoader.load();
                OwnExaminationGeneratePopupController pc = fXMLLoader.getController();
                pc.setParams(this);
                Scene scene = new Scene(root2);
                smallstage2 = new Stage();

                smallstage2.initStyle(StageStyle.UNDECORATED);
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
                        smallstage2.setX(event.getScreenX() - xOffset);
                        smallstage2.setY(event.getScreenY() - yOffset);
                    }
                });

                smallstage2.setScene(scene);
                smallstage2.show();

            } catch (Exception e) {
                System.out.println("Can't load new windows");
            }
        } else if (smallstage2.isShowing()) {
            smallstage2.toFront();
        } else {
            smallstage2.show();
        }

    }

    private void saveOwnExamination(int PrescriptionId) {

        String prescription = "INSERT INTO prescription.tbl_prescription_oe (prescription_id, own_examination_id, own_examination_value) VALUES(?,?,?)";

        try {

            PreparedStatement pst = connection.prepareStatement(prescription, Statement.RETURN_GENERATED_KEYS);

            for (int i = 0; i < own_examination.size(); i++) {

                List<String> ownExaminationDetails = own_examination.get(i);
                pst.setString(1, String.valueOf(PrescriptionId));
                pst.setString(2, ownExaminationDetails.get(2));
                pst.setString(3, ownExaminationDetails.get(1));

                pst.execute();

            }

            pst.close();

        } catch (SQLException e) {
            Logger.getLogger(ChiefComplaintsController.class.getName()).log(Level.SEVERE, null, e);
            System.out.println(e);
        }

    }

    void ownExaminationDetails(String ownExaminationNameInput, String ownExaminationValue, String selectedownExaminationID) {

        List<String> ownExaminationDetails = new ArrayList<String>();
        // 0 = Name, 1 = Value, 2 = Index or ID
        //ownExaminationDetails.add(ownExaminationId);
        ownExaminationDetails.add(ownExaminationNameInput);
        ownExaminationDetails.add(ownExaminationValue);
        ownExaminationDetails.add(selectedownExaminationID);
        own_examination.add(ownExaminationDetails);

        System.out.println("OwnExamination Details : ");

        System.out.println(own_examination);

        //medicine window reset.
        smallstage2 = null;

        if (own_examination.size() > 0) {
            int i = own_examination.size() - 1;
            List<String> ownExaminationDetails2 = own_examination.get(i);

            Pane temppane1 = new Pane();

            Label serial = new Label(String.valueOf(own_examination.size()));
            serial.setLayoutX(20);
            serial.setLayoutY(10);
            temppane1.getChildren().add(serial);

            Label medicinetype = new Label(ownExaminationNameInput);
            medicinetype.setLayoutX(35);
            medicinetype.setLayoutY(10);
            medicinetype.setTextFill(Color.web("#0076a3"));
            medicinetype.setStyle("-fx-font-weight: bold");
            temppane1.getChildren().add(medicinetype);

            Label medicinename = new Label(ownExaminationValue);
            medicinename.setLayoutX(120);
            medicinename.setLayoutY(10);
            medicinename.setTextFill(Color.web("#00134d"));
            medicinename.setFont(new Font("Arial", 15));
            medicinename.setStyle("-fx-font-weight: bold");
            temppane1.getChildren().add(medicinename);

            vBoxOwnExamination.getChildren().add(temppane1);

        }

    }

}
