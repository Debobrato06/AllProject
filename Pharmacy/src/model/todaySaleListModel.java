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
public class todaySaleListModel {
    
    String medicineId, drugName, drugQuantity,drugBuyAmount,discountAmountInput,netAmountInput,moneyReceiptNumber;

    public todaySaleListModel(String medicineId, String drugName, String drugQuantity, String drugBuyAmount, String discountAmountInput, String netAmountInput, String moneyReceiptNumber) {
        this.medicineId = medicineId;
        this.drugName = drugName;
        this.drugQuantity = drugQuantity;
        this.drugBuyAmount = drugBuyAmount;
        this.discountAmountInput = discountAmountInput;
        this.netAmountInput = netAmountInput;
        this.moneyReceiptNumber = moneyReceiptNumber;
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

    public String getDiscountAmountInput() {
        return discountAmountInput;
    }

    public void setDiscountAmountInput(String discountAmountInput) {
        this.discountAmountInput = discountAmountInput;
    }

    public String getNetAmountInput() {
        return netAmountInput;
    }

    public void setNetAmountInput(String netAmountInput) {
        this.netAmountInput = netAmountInput;
    }

    public String getMoneyReceiptNumber() {
        return moneyReceiptNumber;
    }

    public void setMoneyReceiptNumber(String moneyReceiptNumber) {
        this.moneyReceiptNumber = moneyReceiptNumber;
    }



    
}
