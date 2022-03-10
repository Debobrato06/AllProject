/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;

/**
 *
 * @author devbi
 */
public class DrugListModel {

    String id, drug_name, drug_generic_id, drug_company_id;
    Button Action;

    public DrugListModel(String id, String drug_name, String drug_generic_id, String drug_company_id, Button Action) {
        this.id = id;
        this.drug_name = drug_name;
        this.drug_generic_id = drug_generic_id;
        this.drug_company_id = drug_company_id;
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

    public String getDrug_name() {
        return drug_name;
    }

    public void setDrug_name(String drug_name) {
        this.drug_name = drug_name;
    }

    public String getDrug_generic_id() {
        return drug_generic_id;
    }

    public void setDrug_generic_id(String drug_generic_id) {
        this.drug_generic_id = drug_generic_id;
    }

    public String getDrug_company_id() {
        return drug_company_id;
    }

    public void setDrug_company_id(String drug_company_id) {
        this.drug_company_id = drug_company_id;
    }

    public Object selectedProperty() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
