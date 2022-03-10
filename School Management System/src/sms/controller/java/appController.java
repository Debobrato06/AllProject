/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sms.controller.java;

import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.time;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;
import org.codehaus.plexus.interpolation.Interpolator;
import sms.helper.DynaimicViews;

/**
 *
 * @author devbi
 */
public class appController implements Initializable {

    @FXML
    private BorderPane border_pane;
    
    @FXML
    private AnchorPane anchorroot;
    @FXML
    private StackPane parentcontainer;


   
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void show_dashboard(MouseEvent event) throws IOException {
       // Parent dashboard=FXMLLoader.load(getClass().getResource("/sms/views/dashboard.fxml"));
        //border_pane.setCenter(dashboard);
        DynaimicViews.loaderBorderCenter(border_pane, "dashboard");
    }

    @FXML
    private void show_teacher(MouseEvent event) throws IOException {
       //  Parent teacher=FXMLLoader.load(getClass().getResource("/sms/views/teacher.fxml"));
      //  border_pane.setCenter(teacher);
       DynaimicViews.loaderBorderCenter(border_pane, "teacher");
    }

    @FXML
    private void show_student(MouseEvent event) throws IOException {
       //  Parent student=FXMLLoader.load(getClass().getResource("/sms/views/student.fxml"));
       // border_pane.setCenter(student);
        DynaimicViews.loaderBorderCenter(border_pane, "student");
    }

    @FXML
    private void show_parents(MouseEvent event) throws IOException {
        // Parent parents=FXMLLoader.load(getClass().getResource("/sms/views/parents.fxml"));
       // border_pane.setCenter(parents);
        DynaimicViews.loaderBorderCenter(border_pane, "parents");
    }

    @FXML
    private void show_subject(MouseEvent event) throws IOException {
         //Parent subject=FXMLLoader.load(getClass().getResource("/sms/views/subject.fxml"));
       // border_pane.setCenter(subject);
        DynaimicViews.loaderBorderCenter(border_pane, "subject");
    }

    @FXML
    private void show_result(MouseEvent event) throws IOException {
       //  Parent result=FXMLLoader.load(getClass().getResource("/sms/views/result.fxml"));
      //  border_pane.setCenter(result);
       DynaimicViews.loaderBorderCenter(border_pane, "result");
    }

    @FXML
    private void Dashboard(ActionEvent event) throws IOException {
       // Parent root=FXMLLoader.load(getClass().getResource("/sms/views/dashboard.fxml"));
      //  Scene scene=border_pane.getScene();
      //  root.translateXProperty().set(scene.getHeight());
      //  parentcontainer.getChildren().add(root);
      // Timeline timeline = new Timeline();
       //KeyValue kv=new KeyValue(root.translateXProperty(), 0, javafx.animation.Interpolator.EASE_BOTH);
       
        //KeyValue keyValue = new KeyValue(root.translateXProperty(), 0,Interpolator);
        
      //  KeyFrame keyFrame = new KeyFrame(Duration.seconds(1), kv);
      //  timeline.getKeyFrames().add(keyFrame);
      // timeline.setCycleCount(2);
       // timeline.setAutoReverse(true);
       //timeline.setOnFinished(event-> {
       //    parentcontainer.getChildren().remove(anchorroot);
      // });
        //timeline.play();
        
    }

   
    
    
    
}
