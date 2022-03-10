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
public class DrugTakeFormatModel {

    String id, eat_format, insert_time;
    Button Action;

    public Button getAction() {
        return Action;
    }

    public void setAction(Button Action) {
        this.Action = Action;
    }

    public DrugTakeFormatModel(String id, String eat_format, String insert_time, Button Action) {
        this.id = id;
        this.eat_format = eat_format;
        this.insert_time = insert_time;
        this.Action = Action;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEat_format() {
        return eat_format;
    }

    public void setEat_format(String eat_format) {
        this.eat_format = eat_format;
    }

    public String getInsert_time() {
        return insert_time;
    }

    public void setInsert_time(String insert_time) {
        this.insert_time = insert_time;
    }

}
