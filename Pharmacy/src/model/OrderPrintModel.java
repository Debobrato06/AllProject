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
public class OrderPrintModel {

    String SL, medicineNameInput, medicineQuantity, netAmount;

    public OrderPrintModel(String SL, String medicineNameInput, String medicineQuantity, String netAmount) {
        this.SL = SL;
        this.medicineNameInput = medicineNameInput;
        this.medicineQuantity = medicineQuantity;
        this.netAmount = netAmount;
    }

    public String getSL() {
        return SL;
    }

    public void setSL(String SL) {
        this.SL = SL;
    }

    public String getMedicineNameInput() {
        return medicineNameInput;
    }

    public void setMedicineNameInput(String medicineNameInput) {
        this.medicineNameInput = medicineNameInput;
    }

    public String getMedicineQuantity() {
        return medicineQuantity;
    }

    public void setMedicineQuantity(String medicineQuantity) {
        this.medicineQuantity = medicineQuantity;
    }

    public String getNetAmount() {
        return netAmount;
    }

    public void setNetAmount(String netAmount) {
        this.netAmount = netAmount;
    }

}
