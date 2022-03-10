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
public class moneyReceiptModel {

    String sl, moneyReceiptNumber, Discount, NetAmount;
    Button action;

    public moneyReceiptModel(String sl, String moneyReceiptNumber, String Discount, String NetAmount, Button action) {
        this.sl = sl;
        this.moneyReceiptNumber = moneyReceiptNumber;
        this.Discount = Discount;
        this.NetAmount = NetAmount;
        this.action = action;
    }

    public String getSl() {
        return sl;
    }

    public void setSl(String sl) {
        this.sl = sl;
    }

    public String getMoneyReceiptNumber() {
        return moneyReceiptNumber;
    }

    public void setMoneyReceiptNumber(String moneyReceiptNumber) {
        this.moneyReceiptNumber = moneyReceiptNumber;
    }

    public String getDiscount() {
        return Discount;
    }

    public void setDiscount(String Discount) {
        this.Discount = Discount;
    }

    public String getNetAmount() {
        return NetAmount;
    }

    public void setNetAmount(String NetAmount) {
        this.NetAmount = NetAmount;
    }

    public Button getAction() {
        return action;
    }

    public void setAction(Button action) {
        this.action = action;
    }
    
    

}
