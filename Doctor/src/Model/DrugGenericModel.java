/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import javafx.scene.control.Button;

/**
 *
 * @author devbi
 */
public class DrugGenericModel {

    String id, name, insert_time;
     private Button Action;
    
   

   public Button getAction() {
        return Action;
    }

    public void setAction(Button Action) {
        this.Action = Action;
    }



    public DrugGenericModel(String id, String name, String insert_time,Button Action) {
        this.id = id;
        this.name = name;
        this.insert_time = insert_time;
        this.Action = Action;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInsert_time() {
        return insert_time;
    }

    public void setInsert_time(String insert_time) {
        this.insert_time = insert_time;
    }

}
