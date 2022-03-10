/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Debobrato Biswas
 */
public class MemoController implements Initializable {
    
    @FXML
    private TextField custometName;
    @FXML
    private TextField customerPhoneNumber;
    @FXML
    private TextField customerAddress;
    
    @FXML
    private TextField unitValue;
    @FXML
    private TextField unit1;
    @FXML
    private TextField unit2;
    @FXML
    private TextField unit3;
    @FXML
    private TextField unit4;
    @FXML
    private TextField unit5;
    @FXML
    private TextField unit6;
    @FXML
    private TextField unit7;
    @FXML
    private TextField unit8;
    @FXML
    private TextField unit9;
    
    @FXML
    private TextField totalPrice;
    @FXML
    private TextField totalPrice1;
    @FXML
    private TextField totalPrice2;
    @FXML
    private TextField totalPrice3;
    @FXML
    private TextField totalPrice4;
    @FXML
    private TextField totalPrice5;
    @FXML
    private TextField totalPrice6;
    @FXML
    private TextField totalPrice7;
    @FXML
    private TextField totalPrice8;
    @FXML
    private TextField totalPrice9;
    
    @FXML
    private TextField perUnitPrice;
    @FXML
    private TextField perUnitPrice1;
    @FXML
    private TextField perUnitPrice2;
    @FXML
    private TextField perUnitPrice3;
    @FXML
    private TextField perUnitPrice4;
    @FXML
    private TextField perUnitPrice5;
    @FXML
    private TextField perUnitPrice6;
    @FXML
    private TextField perUnitPrice7;
    @FXML
    private TextField perUnitPrice8;
    @FXML
    private TextField perUnitPrice9;
    
    @FXML
    private TextField allTotalPrice;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

    }
    
    @FXML
    private void Print(ActionEvent event) {
        String Unit = unitValue.getText();
        String Unit1 = unit1.getText();
        String Unit2 = unit2.getText();
        String Unit3 = unit3.getText();
        String Unit4 = unit4.getText();
        String Unit5 = unit5.getText();
        String Unit6 = unit6.getText();
        String Unit7 = unit7.getText();
        String Unit8 = unit8.getText();        
        String Unit9 = unit9.getText();
        
        String perUnit = perUnitPrice.getText();
        String perUnit1 = perUnitPrice1.getText();
        String perUnit2 = perUnitPrice2.getText();
        String perUnit3 = perUnitPrice3.getText();
        String perUnit4 = perUnitPrice4.getText();
        String perUnit5 = perUnitPrice5.getText();
        String perUnit6 = perUnitPrice6.getText();
        String perUnit7 = perUnitPrice7.getText();
        String perUnit8 = perUnitPrice8.getText();
        String perUnit9 = perUnitPrice9.getText();
        
        String result = String.valueOf(Float.valueOf(Unit) * Float.valueOf(perUnit));
        String result1 = String.valueOf(Float.valueOf(Unit1) * Float.valueOf(perUnit1));
        String result2 = String.valueOf(Float.valueOf(Unit2) * Float.valueOf(perUnit2));
        String result3 = String.valueOf(Float.valueOf(Unit3) * Float.valueOf(perUnit3));
        String result4 = String.valueOf(Float.valueOf(Unit4) * Float.valueOf(perUnit4));
        String result5 = String.valueOf(Float.valueOf(Unit5) * Float.valueOf(perUnit5));
        String result6 = String.valueOf(Float.valueOf(Unit6) * Float.valueOf(perUnit6));
        String result7 = String.valueOf(Float.valueOf(Unit7) * Float.valueOf(perUnit7));
        String result8 = String.valueOf(Float.valueOf(Unit8) * Float.valueOf(perUnit8));
        String result9 = String.valueOf(Float.valueOf(Unit9) * Float.valueOf(perUnit9));
        
        System.out.println("Result:" + result);
        
        totalPrice.setText(result);
        totalPrice1.setText(result1);
        totalPrice2.setText(result2);
        totalPrice3.setText(result3);
        totalPrice4.setText(result4);
        totalPrice5.setText(result5);
        totalPrice6.setText(result6);
        totalPrice7.setText(result7);
        totalPrice8.setText(result8);
        totalPrice9.setText(result9);
        
       String allPrice = String.valueOf(Float.valueOf(result) + Float.valueOf(result1) + Float.valueOf(result2) + Float.valueOf(result3) + Float.valueOf(result4) + Float.valueOf(result5) + Float.valueOf(result6) + Float.valueOf(result7) + Float.valueOf(result8) + Float.valueOf(result9));
        // String allPrice1 = String.valueOf(Float.valueOf(result) + Float.valueOf(result1));
//       if(totalPrice.equals(result)){
//           String allPrice = String.valueOf(Float.valueOf(result));
//           
//            allTotalPrice.setText(String.valueOf(result));
//       }
//        
        
      allTotalPrice.setText(allPrice);
       // allTotalPrice.setText(allPrice1);
       
       
       
       

//        try {
//
//            FXMLLoader fXMLLoader = new FXMLLoader(getClass().getResource("/View/Memo_sit.fxml"));
//            Parent root2 = (Parent) fXMLLoader.load();
//            Memo_sitController pc = fXMLLoader.getController();
//            pc.setParams(this);
//
//            Scene scene = new Scene(root2);
//            Stage stage = new Stage();
//            //Stage.setTitel("");
//            stage.setScene(scene);
//            stage.show();
//
//        } catch (Exception e) {
//            System.out.println("Can't load new windows");
//        }
    }
    
}
