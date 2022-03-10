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
public class recentExpairModel {
    
    String SL , drugName , expairDate;

    public recentExpairModel(String SL, String drugName, String expairDate) {
        this.SL = SL;
        this.drugName = drugName;
        this.expairDate = expairDate;
    }

    public String getSL() {
        return SL;
    }

    public void setSL(String SL) {
        this.SL = SL;
    }

    public String getDrugName() {
        return drugName;
    }

    public void setDrugName(String drugName) {
        this.drugName = drugName;
    }

    public String getExpairDate() {
        return expairDate;
    }

    public void setExpairDate(String expairDate) {
        this.expairDate = expairDate;
    }
    
    
}
