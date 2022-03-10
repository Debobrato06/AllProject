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
public class ComplaintsModel {

    String id, complaint, insert_time, last_update;
    private Button Action;
    
     

   
   


    public ComplaintsModel(String id, String complaint, String insert_time, String last_update,Button Action) {
        this.id = id;
        this.complaint = complaint;
        this.insert_time = insert_time;
        this.last_update = last_update;
         this.Action = Action;
    }

    public String getId() {
        return id;
    }
    
     public Button getAction() {
        return Action;
    }

    public void setAction(Button Action) {
        this.Action = Action;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getComplaint() {
        return complaint;
    }

    public void setComplaint(String complaint) {
        this.complaint = complaint;
    }

    public String getInsert_time() {
        return insert_time;
    }

    public void setInsert_time(String insert_time) {
        this.insert_time = insert_time;
    }

    public String getLast_update() {
        return last_update;
    }

    public void setLast_update(String last_update) {
        this.last_update = last_update;
    }

}
