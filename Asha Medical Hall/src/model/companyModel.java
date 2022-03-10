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
public class companyModel {
    String SL,CompanyModelName;

    public companyModel(String SL, String CompanyModelName) {
        this.SL = SL;
        this.CompanyModelName = CompanyModelName;
    }

    public String getSL() {
        return SL;
    }

    public void setSL(String SL) {
        this.SL = SL;
    }

    public String getCompanyModelName() {
        return CompanyModelName;
    }

    public void setCompanyModelName(String CompanyModelName) {
        this.CompanyModelName = CompanyModelName;
    }
    
    
}
