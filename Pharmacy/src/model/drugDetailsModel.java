/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author User
 */
public class drugDetailsModel {
     String id, drugName, drugGenericName;

    public drugDetailsModel(String id, String drugName, String drugGenericName) {
        this.id = id;
        this.drugName = drugName;
        this.drugGenericName = drugGenericName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDrugName() {
        return drugName;
    }

    public void setDrugName(String drugName) {
        this.drugName = drugName;
    }

    public String getDrugGenericName() {
        return drugGenericName;
    }

    public void setDrugGenericName(String drugGenericName) {
        this.drugGenericName = drugGenericName;
    }
     
    
}
