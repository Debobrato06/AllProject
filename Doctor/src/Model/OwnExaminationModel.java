/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;

/**
 *
 * @author devbi
 */
public class OwnExaminationModel {

    String id, oe_title;
     Button Action;

    

    public OwnExaminationModel(String id, String oe_title, Button Action) {
        this.id = id;
        this.oe_title = oe_title;
        this.Action = Action;
    }

    public Button getAction() {
        return Action;
    }

    public void setAction(Button Action) {
        this.Action = Action;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOe_title() {
        return oe_title;
    }

    public void setOe_title(String oe_title) {
        this.oe_title = oe_title;
    }

   
}
