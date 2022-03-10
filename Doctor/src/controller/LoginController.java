/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import JDBC.DBConn;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import static com.sun.glass.ui.Cursor.setVisible;
import static controller.MasterPage.scene;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author Debobrato Biswas
 */
public class LoginController implements Initializable {

    Stage smallstage = null;
    private double xOffset = 0;
    private double yOffset = 0;
    @FXML
    private JFXTextField userName;
    @FXML
    private JFXPasswordField password;
    @FXML
    private JFXButton loginId;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void login(ActionEvent event) throws SQLException {

//        String userNameInput = userName.getText().toString();
//        String passwordInput = password.getText().toString();
//
//        String sql = "select*from user where Username=? and password=? ";
//
//        try {
//            Connection connection = DBConn.getConnection();
//            PreparedStatement preparedStatement = connection.prepareStatement(sql);
//            preparedStatement.setString(1, userNameInput);
//            preparedStatement.setString(2, passwordInput);
//            ResultSet resultSet = preparedStatement.executeQuery();
//            if (!resultSet.next()) {
//             //   infoBox("Enter Correct Email and Password", "Failed", null);
//            } else {
//               // infoBox("Login Successfull", "Success", null);
//                Node source = (Node) event.getSource();
//                smallstage = (Stage) source.getScene().getWindow();
//                smallstage.close();
//                scene = new Scene(FXMLLoader.load(getClass().getResource(resultSet.getIn‌​t(1) == 0 ? "/view/MainView.fxml" : "FXMLMenuManager.fxml")));
//                smallstage.setScene(scene);
//                smallstage.show();
//            }
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        if (smallstage == null) {

            try {

                FXMLLoader fXMLLoader = new FXMLLoader(getClass().getResource("/view/MainView.fxml"));
                Parent root2 = (Parent) fXMLLoader.load();
                MainController pc = fXMLLoader.getController();
                //  pc.setParams(this);
                Scene scene = new Scene(root2);
                smallstage = new Stage();
                Image icon = new Image(getClass().getResourceAsStream("/images/download.jpg"));
                smallstage.getIcons().add(icon);

                smallstage.initStyle(StageStyle.UNDECORATED);
                root2.setOnMousePressed(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        xOffset = event.getSceneX();
                        yOffset = event.getSceneY();
                    }
                });
                root2.setOnMouseDragged(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        smallstage.setX(event.getScreenX() - xOffset);
                        smallstage.setY(event.getScreenY() - yOffset);
                    }
                });

                smallstage.setScene(scene);
                smallstage.show();

                Stage stage = (Stage) loginId.getScene().getWindow();
                stage.close();

            } catch (Exception e) {
                System.out.println("Can't load new windows");
            }
        } else if (smallstage.isShowing()) {
            smallstage.toFront();
        } else {
            smallstage.show();
        }

    }

}
