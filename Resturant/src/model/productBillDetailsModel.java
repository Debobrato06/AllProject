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
public class productBillDetailsModel {

    String id, productNameValue, quantityValue, priceValue;

    Button action;

//    public productBillDetailsModel(Button action) {
//        this.action = action;
//    }

    public productBillDetailsModel(String id, String productNameValue, String quantityValue, String priceValue,Button action) {
        this.id = id;
        this.productNameValue = productNameValue;
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

    public String getProductNameValue() {
        return productNameValue;
    }

    public void setProductNameValue(String productNameValue) {
        this.productNameValue = productNameValue;
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
