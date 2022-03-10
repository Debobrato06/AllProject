/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sms.helper;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.BorderPane;

/**
 *
 * @author devbi
 */
public class DynaimicViews {
    
    private  DynaimicViews(){
        
    }
    
    public static void loaderBorderCenter(BorderPane borderPane,String resoource){
        
        try {
            Parent dashboard=FXMLLoader.load(new DynaimicViews().getClass().getResource("/sms/views/"+resoource+".fxml"));
            borderPane.setCenter(dashboard);
        } catch (IOException ex) {
            Logger.getLogger(DynaimicViews.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}
