/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import javafx.scene.control.Button;

/**
 *
 * @author User
 */
public class medicineBillListModel {
     String id, drugNameValue, quantityValue, priceValue;

    Button action;

    public medicineBillListModel(String id, String drugNameValue, String quantityValue, String priceValue, Button action) {
        this.id = id;
        this.drugNameValue = drugNameValue;
        this.quantityValue = quantityValue;
        this.priceValue = priceValue;
        this.action = action;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDrugNameValue() {
        return drugNameValue;
    }

    public void setDrugNameValue(String drugNameValue) {
        this.drugNameValue = drugNameValue;
    }

    public String getQuantityValue() {
        return quantityValue;
    }

    public void setQuantityValue(String quantityValue) {
        this.quantityValue = quantityValue;
    }

    public String getPriceValue() {
        return priceValue;
    }

    public void setPriceValue(String priceValue) {
        this.priceValue = priceValue;
    }

    public Button getAction() {
        return action;
    }

    public void setAction(Button action) {
        this.action = action;
    }
    
    
}
