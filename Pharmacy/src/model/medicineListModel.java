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
public class medicineListModel {
    //int medicineId;
    String medicineId, drugName, drugPower, medicineTypeName,drugQuantity,drugBuyAmount,drugSalePerQuantity,drugExpired, drugRemark;

    public medicineListModel(String medicineId, String drugName, String drugPower, String medicineTypeName, String drugQuantity, String drugBuyAmount, String drugSalePerQuantity, String drugExpired, String drugRemark) {
        this.medicineId = medicineId;
        this.drugName = drugName;
        this.drugPower = drugPower;
        this.medicineTypeName = medicineTypeName;
        this.drugQuantity = drugQuantity;
        this.drugBuyAmount = drugBuyAmount;
        this.drugSalePerQuantity = drugSalePerQuantity;
        this.drugExpired = drugExpired;
        this.drugRemark = drugRemark;
    }

    public String getMedicineId() {
        return medicineId;
    }

    public void setMedicineId(String medicineId) {
        this.medicineId = medicineId;
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

    public String getMedicineTypeName() {
        return medicineTypeName;
    }

    public void setMedicineTypeName(String medicineTypeName) {
        this.medicineTypeName = medicineTypeName;
    }

    public String getDrugQuantity() {
        return drugQuantity;
    }

    public void setDrugQuantity(String drugQuantity) {
        this.drugQuantity = drugQuantity;
    }

    public String getDrugBuyAmount() {
        return drugBuyAmount;
    }

    public void setDrugBuyAmount(String drugBuyAmount) {
        this.drugBuyAmount = drugBuyAmount;
    }

    public String getDrugSalePerQuantity() {
        return drugSalePerQuantity;
    }

    public void setDrugSalePerQuantity(String drugSalePerQuantity) {
        this.drugSalePerQuantity = drugSalePerQuantity;
    }

    public String getDrugExpired() {
        return drugExpired;
    }

    public void setDrugExpired(String drugExpired) {
        this.drugExpired = drugExpired;
    }

    public String getDrugRemark() {
        return drugRemark;
    }

    public void setDrugRemark(String drugRemark) {
        this.drugRemark = drugRemark;
    }

   

    
}
