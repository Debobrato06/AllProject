/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Debobrato Biswas
 */
public class drugModel {

    String id, drugName,drugPower, drugPerPrice;

    public drugModel(String id, String drugName, String drugPower, String drugPerPrice) {
        this.id = id;
        this.drugName = drugName;
        this.drugPower = drugPower;
        this.drugPerPrice = drugPerPrice;
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

    public String getDrugPower() {
        return drugPower;
    }

    public void setDrugPower(String drugPower) {
        this.drugPower = drugPower;
    }

    public String getDrugPerPrice() {
        return drugPerPrice;
    }

    public void setDrugPerPrice(String drugPerPrice) {
        this.drugPerPrice = drugPerPrice;
    }

   

}
