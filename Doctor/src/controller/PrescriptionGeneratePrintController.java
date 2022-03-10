/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import org.controlsfx.control.IndexedCheckModel;

/**
 * FXML Controller class
 *
 * @author devbi
 */
public class PrescriptionGeneratePrintController implements Initializable {

    @FXML
    private Label date;
    @FXML
    private Label chiefComplaintsName;
    @FXML
    private Label testName;
   
    @FXML
    private Label adviceName;
    @FXML
    private Label nextVisitDate;

    @FXML
    private Label patientName;
    @FXML
    private Label patientAge;
    @FXML
    private Label patientSex;
    @FXML
    private Label phone;

    @FXML
    private Button Exit;
    @FXML
    private VBox vBox;
    @FXML
    private VBox vBoxOwnExamination;

    /**
     * Initializes the controller class.
     *
     */
    ///////////////
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
     

    }

    @FXML
    private void exit(ActionEvent event) {
        Stage stage = (Stage) Exit.getScene().getWindow();
        stage.close();
    }

    void setText(String PatientName, String PatientAge, String gender, String phone, String Address, LocalDate Date, String refference, IndexedCheckModel<String> chiefComplaintName, IndexedCheckModel<String> testNameInput, IndexedCheckModel<String> adviceNameInput, LocalDate NextVisitDateInput, List<List<String>> prescription_medicine_list, List<List<String>> own_examination) {
        this.patientName.setText(PatientName);
        this.patientAge.setText(PatientAge);
        patientSex.setText(gender);
        this.phone.setText(phone);
        this.date.setText(Date.toString());

        String chiefComplaintexts = chiefComplaintName.getCheckedItems().toString();
        String chiefComplaintNameList = getchiefComplaintNameList(chiefComplaintexts);
        this.chiefComplaintsName.setText(chiefComplaintNameList);
        String testtexts = testNameInput.getCheckedItems().toString();
        String testNameList = gettestNameList(testtexts);
        this.testName.setText(testNameList);
        this.adviceName.setText(adviceNameInput.getCheckedItems().toString());
        this.nextVisitDate.setText(NextVisitDateInput.toString());

        for (int i = 0; i < prescription_medicine_list.size(); i++) {

            List<String> prescriptionMedicineDetails2 = prescription_medicine_list.get(i);

            Pane temppane = new Pane();

            Label serial = new Label(String.valueOf(1 + i));
            serial.setLayoutX(40);
            serial.setLayoutY(10);
            temppane.getChildren().add(serial);

            Label medicinetype = new Label(prescriptionMedicineDetails2.get(5));
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

            Label medicinepower = new Label(prescriptionMedicineDetails2.get(3));
            medicinepower.setLayoutX(210);
            medicinepower.setLayoutY(10);
            temppane.getChildren().add(medicinepower);

            Label bottomtext = new Label(prescriptionMedicineDetails2.get(4));
            bottomtext.setLayoutX(60);
            bottomtext.setLayoutY(30);
            temppane.getChildren().add(bottomtext);

            vBox.getChildren().add(temppane);

        }
        for (int j = 0; j < own_examination.size(); j++) {

            List<String> ownExaminationDetails = own_examination.get(j);

            Pane temppane1 = new Pane();

            Label serial = new Label(String.valueOf(1 + j));
            serial.setLayoutX(20);
            serial.setLayoutY(10);
            temppane1.getChildren().add(serial);

            Label ownExaminationName = new Label(ownExaminationDetails.get(0));
            ownExaminationName.setLayoutX(35);
            ownExaminationName.setLayoutY(10);
            ownExaminationName.setTextFill(Color.web("#0076a3"));
            ownExaminationName.setStyle("-fx-font-weight: bold");
            temppane1.getChildren().add(ownExaminationName);

            Label ownExaminationValue = new Label(ownExaminationDetails.get(1));
            ownExaminationValue.setLayoutX(120);
            ownExaminationValue.setLayoutY(10);
            ownExaminationValue.setTextFill(Color.web("#00134d"));
            ownExaminationValue.setFont(new Font("Arial", 15));
            ownExaminationValue.setStyle("-fx-font-weight: bold");
            temppane1.getChildren().add(ownExaminationValue);

            vBoxOwnExamination.getChildren().add(temppane1);

        }

    }

    private String getchiefComplaintNameList(String chiefComplaintexts) {

        chiefComplaintexts = chiefComplaintexts.replace("[", "> ");
        chiefComplaintexts = chiefComplaintexts.replace("]", "");
        List<String> items = Arrays.asList(chiefComplaintexts.split(","));

        chiefComplaintexts = "";
        for (int i = 0; i < items.size(); i++) {

            if (i > 0) {
                chiefComplaintexts += "\n" + ">";

            }
            chiefComplaintexts += items.get(i);

        }
        System.out.println("items : " + chiefComplaintexts.toString());
        return chiefComplaintexts;
    }

    private String gettestNameList(String testtexts) {

        testtexts = testtexts.replace("[", "> ");
        testtexts = testtexts.replace("]", "");
        List<String> item = Arrays.asList(testtexts.split(","));

        testtexts = "";

        for (int i = 0; i < item.size(); i++) {

            if (i > 0) {
                testtexts += "\n" + ">";
            }
            testtexts += item.get(i);

        }
        System.out.println("items : " + testtexts.toString());
        return testtexts;

    }

}
