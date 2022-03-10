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
public class salesListModel {
    String salesId, medicineName, medicineQuantity, discount,netAmount,moneyReceiptNo;

    public salesListModel(String salesId, String medicineName, String medicineQuantity, String discount, String netAmount, String moneyReceiptNo) {
        this.salesId = salesId;
        this.medicineName = medicineName;
        this.medicineQuantity = medicineQuantity;
        this.discount = discount;
        this.netAmount = netAmount;
        this.moneyReceiptNo = moneyReceiptNo;
    }

    public String getSalesId() {
        return salesId;
    }

    public void setSalesId(String salesId) {
        this.salesId = salesId;
    }

    public String getMedicineName() {
        return medicineName;
    }

    public void setMedicineName(String medicineName) {
        this.medicineName = medicineName;
    }

    public String getMedicineQuantity() {
        return medicineQuantity;
    }

    public void setMedicineQuantity(String medicineQuantity) {
        this.medicineQuantity = medicineQuantity;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    public String getNetAmount() {
        return netAmount;
    }

    public void setNetAmount(String netAmount) {
        this.netAmount = netAmount;
    }

    public String getMoneyReceiptNo() {
        return moneyReceiptNo;
    }

    public void setMoneyReceiptNo(String moneyReceiptNo) {
        this.moneyReceiptNo = moneyReceiptNo;
    }
    
    
}
