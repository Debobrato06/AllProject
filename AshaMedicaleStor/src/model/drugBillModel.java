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
public class drugBillModel {
    //String idBill, drug-nameBill, drug_perpriceBill, quantityBill, total_priceBill;
    String idBill,name,PerPrice,quantity,totalPrice;

    public drugBillModel(String idBill, String name, String PerPrice, String quantity, String totalPrice) {
        this.idBill = idBill;
        this.name = name;
        this.PerPrice = PerPrice;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
    }

    public String getIdBill() {
        return idBill;
    }

    public void setIdBill(String idBill) {
        this.idBill = idBill;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPerPrice() {
        return PerPrice;
    }

    public void setPerPrice(String PerPrice) {
        this.PerPrice = PerPrice;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(String totalPrice) {
        this.totalPrice = totalPrice;
    }
    
    
}
