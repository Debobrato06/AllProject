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
public class MedicalTestModel {

    String id, test_name, test_category, insert_time;
     Button Action;

    public MedicalTestModel(String id, String test_name, String test_category, String insert_time,Button Action) {
        this.id = id;
        this.test_name = test_name;
        this.test_category = test_category;
        this.insert_time = insert_time;
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

    public String getTest_name() {
        return test_name;
    }

    public void setTest_name(String test_name) {
        this.test_name = test_name;
    }

    public String getTest_category() {
        return test_category;
    }

    public void setTest_category(String test_category) {
        this.test_category = test_category;
    }

    public String getInsert_time() {
        return insert_time;
    }

    public void setInsert_time(String insert_time) {
        this.insert_time = insert_time;
    }

}
