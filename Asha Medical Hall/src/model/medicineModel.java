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
public class medicineModel {
    String drug_id, drug_name, drug_power, drug_company, drug_price, menufecture_date, expair_date, insert_date, update_date, drug_quantity, is_online, drug_type;

    public medicineModel(String drug_id, String drug_name, String drug_power, String drug_company, String drug_price, String menufecture_date, String expair_date, String insert_date, String update_date, String drug_quantity, String is_online, String drug_type) {
        this.drug_id = drug_id;
        this.drug_name = drug_name;
        this.drug_power = drug_power;
        this.drug_company = drug_company;
        this.drug_price = drug_price;
        this.menufecture_date = menufecture_date;
        this.expair_date = expair_date;
        this.insert_date = insert_date;
        this.update_date = update_date;
        this.drug_quantity = drug_quantity;
        this.is_online = is_online;
        this.drug_type = drug_type;
    }

    

    public String getDrug_id() {
        return drug_id;
    }

    public void setDrug_id(String drug_id) {
        this.drug_id = drug_id;
    }

    public String getDrug_name() {
        return drug_name;
    }

    public void setDrug_name(String drug_name) {
        this.drug_name = drug_name;
    }

    public String getDrug_power() {
        return drug_power;
    }

    public void setDrug_power(String drug_power) {
        this.drug_power = drug_power;
    }

    public String getDrug_company() {
        return drug_company;
    }

    public void setDrug_company(String drug_company) {
        this.drug_company = drug_company;
    }

    public String getDrug_price() {
        return drug_price;
    }

    public void setDrug_price(String drug_price) {
        this.drug_price = drug_price;
    }

    public String getMenufecture_date() {
        return menufecture_date;
    }

    public void setMenufecture_date(String menufecture_date) {
        this.menufecture_date = menufecture_date;
    }

    public String getExpair_date() {
        return expair_date;
    }

    public void setExpair_date(String expair_date) {
        this.expair_date = expair_date;
    }

    public String getInsert_date() {
        return insert_date;
    }

    public void setInsert_date(String insert_date) {
        this.insert_date = insert_date;
    }

    public String getUpdate_date() {
        return update_date;
    }

    public void setUpdate_date(String update_date) {
        this.update_date = update_date;
    }

    public String getDrug_quantity() {
        return drug_quantity;
    }

    public void setDrug_quantity(String drug_quantity) {
        this.drug_quantity = drug_quantity;
    }

    public String getIs_online() {
        return is_online;
    }

    public void setIs_online(String is_online) {
        this.is_online = is_online;
    }

    public String getDrug_type() {
        return drug_type;
    }

    public void setDrug_type(String drug_type) {
        this.drug_type = drug_type;
    }
    
}
