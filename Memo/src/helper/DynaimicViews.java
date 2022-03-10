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

    public static void loaderBorderCenter(BorderPane borderPane, String resoource) {
        /* try {

            // TODO
            Parent memo= FXMLLoader.load(new DynaimicViews().getClass().getResource(""+resoource+""));

            borderpane.setCenter(NewPrescription);

        } catch (IOException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }*/
        try {
            Parent dashboard = FXMLLoader.load(new DynaimicViews().getClass().getResource("/View/" + resoource + ".fxml"));
            borderPane.setCenter(dashboard);
            // borderPane.setRight(dashboard);
            //borderPane.setLeft(dashboard);

        } catch (IOException ex) {
            Logger.getLogger(DynaimicViews.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
