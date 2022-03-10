/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package helper;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

/**
 *
 * @author devbi
 */
public class DynaimicViews {

    public static void loaderBorderCenter(AnchorPane anchorepane, String newPrescription) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    private  DynaimicViews(){
        
    }
    
    public static void loaderBorderCenter(BorderPane borderPane,String resoource){
        
        try {
            Parent dashboard=FXMLLoader.load(new DynaimicViews().getClass().getResource("/dt/view/"+resoource+".fxml"));
           // borderPane.setCenter(dashboard);
        } catch (IOException ex) {
            Logger.getLogger(DynaimicViews.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}
