/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mediaviewexample;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;

/**
 *
 * @author Debobrato Biswas
 */
public class FXMLDocumentController implements Initializable {

    @FXML
    private Label label;
    @FXML
    private Button button;
    @FXML
    private MediaView mediaView;
    private MediaPlayer mp;
    private Media md;

    @FXML
    private void handleButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");
        label.setText("Hello World!");

       
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
//        String path = new File("/media/1.mp4").getAbsolutePath();
 String path = new File("D:\\Tumse Milke Dilka Jo Haal   Ankur Rathee & Sonal Devraj   Bollywood Dance.mp4").getAbsolutePath();
        md = new Media(new File(path).toURI().toString());
        mp = new MediaPlayer(md);
        mediaView.setMediaPlayer(mp);
       // mp.setAutoPlay(true);
        DoubleProperty width = mediaView.fitWidthProperty();
        DoubleProperty height = mediaView.fitHeightProperty();
        width.bind(Bindings.selectDouble(mediaView.sceneProperty(), "width"));
        height.bind(Bindings.selectDouble(mediaView.sceneProperty(), "height"));
        mediaView.setPreserveRatio(true);
        

    }

    @FXML
    private void mV(MouseEvent event) {

    }

}
