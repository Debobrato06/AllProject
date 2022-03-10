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
public class medicineGenericModel {
     String id, medicineGenericName;

    public medicineGenericModel(String id, String medicineGenericName) {
        this.id = id;
        this.medicineGenericName = medicineGenericName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMedicineGenericName() {
        return medicineGenericName;
    }

    public void setMedicineGenericName(String medicineGenericName) {
        this.medicineGenericName = medicineGenericName;
    }
     
    
}
