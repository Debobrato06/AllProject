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
public class RulesForPatientModel {

    String id, rules, patient_category;
    Button Action;

    public RulesForPatientModel(String id, String rules, String patient_category, Button Action) {
        this.id = id;
        this.rules = rules;
        this.patient_category = patient_category;
        this.Action = Action;
    }

    public Button getAction() {
        return Action;
    }

    public void setAction(Button Action) {
        this.Action = Action;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRules() {
        return rules;
    }

    public void setRules(String rules) {
        this.rules = rules;
    }

    public String getPatient_category() {
        return patient_category;
    }

    public void setPatient_category(String patient_category) {
        this.patient_category = patient_category;
    }

}
