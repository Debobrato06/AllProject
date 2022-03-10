/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import javafx.scene.control.Button;

/**
 *
 * @author devbi
 */
public class PrescriptionListModel {

    String id, pid, client_id, reference_id, prescription_date, chief_complaint_ids, test_ids, next_visit_date, rules_for_patient_ids, insert_time, insert_by;
    private Button action;
    
     

    public PrescriptionListModel(Button action) {
        this.action = action;
    }
    

    public Button getAction() {
        return action;
    }

    public void setAction(Button action) {
        this.action = action;
    }

    public PrescriptionListModel(String id, String pid, String client_id, String reference_id, String prescription_date, String chief_complaint_ids, String test_ids, String next_visit_date, String rules_for_patient_ids, String insert_time, String insert_by) {
        this.id = id;
        this.pid = pid;
        this.client_id = client_id;
        this.reference_id = reference_id;
        this.prescription_date = prescription_date;
        this.chief_complaint_ids = chief_complaint_ids;
        this.test_ids = test_ids;
        this.next_visit_date = next_visit_date;
        this.rules_for_patient_ids = rules_for_patient_ids;
        this.insert_time = insert_time;
        this.insert_by = insert_by;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getClient_id() {
        return client_id;
    }

    public void setClient_id(String client_id) {
        this.client_id = client_id;
    }

    public String getReference_id() {
        return reference_id;
    }

    public void setReference_id(String reference_id) {
        this.reference_id = reference_id;
    }

    public String getPrescription_date() {
        return prescription_date;
    }

    public void setPrescription_date(String prescription_date) {
        this.prescription_date = prescription_date;
    }

    public String getChief_complaint_ids() {
        return chief_complaint_ids;
    }

    public void setChief_complaint_ids(String chief_complaint_ids) {
        this.chief_complaint_ids = chief_complaint_ids;
    }

    public String getTest_ids() {
        return test_ids;
    }

    public void setTest_ids(String test_ids) {
        this.test_ids = test_ids;
    }

    public String getNext_visit_date() {
        return next_visit_date;
    }

    public void setNext_visit_date(String next_visit_date) {
        this.next_visit_date = next_visit_date;
    }

    public String getRules_for_patient_ids() {
        return rules_for_patient_ids;
    }

    public void setRules_for_patient_ids(String rules_for_patient_ids) {
        this.rules_for_patient_ids = rules_for_patient_ids;
    }

    public String getInsert_time() {
        return insert_time;
    }

    public void setInsert_time(String insert_time) {
        this.insert_time = insert_time;
    }

    public String getInsert_by() {
        return insert_by;
    }

    public void setInsert_by(String insert_by) {
        this.insert_by = insert_by;
    }

}
