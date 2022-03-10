/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import javafx.scene.control.Button;

/**
 *
 * @author Debobrato Biswas
 */
public class paymentModel {
    String id, clientIds, netAmountInputValue, paidAmountInputValue,returnAmountInputValue,paymentDateTime;
    Button Acttion;

    public paymentModel(String id, String clientIds, String netAmountInputValue, String paidAmountInputValue, String returnAmountInputValue, String paymentDateTime, Button Acttion) {
        this.id = id;
        this.clientIds = clientIds;
        this.netAmountInputValue = netAmountInputValue;
        this.paidAmountInputValue = paidAmountInputValue;
        this.returnAmountInputValue = returnAmountInputValue;
        this.paymentDateTime = paymentDateTime;
        this.Acttion = Acttion;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getClientIds() {
        return clientIds;
    }

    public void setClientIds(String clientIds) {
        this.clientIds = clientIds;
    }

    public String getNetAmountInputValue() {
        return netAmountInputValue;
    }

    public void setNetAmountInputValue(String netAmountInputValue) {
        this.netAmountInputValue = netAmountInputValue;
    }

    public String getPaidAmountInputValue() {
        return paidAmountInputValue;
    }

    public void setPaidAmountInputValue(String paidAmountInputValue) {
        this.paidAmountInputValue = paidAmountInputValue;
    }

    public String getReturnAmountInputValue() {
        return returnAmountInputValue;
    }

    public void setReturnAmountInputValue(String returnAmountInputValue) {
        this.returnAmountInputValue = returnAmountInputValue;
    }

    public String getPaymentDateTime() {
        return paymentDateTime;
    }

    public void setPaymentDateTime(String paymentDateTime) {
        this.paymentDateTime = paymentDateTime;
    }

    public Button getActtion() {
        return Acttion;
    }

    public void setActtion(Button Acttion) {
        this.Acttion = Acttion;
    }
    
    
}
