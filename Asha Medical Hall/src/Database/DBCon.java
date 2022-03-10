/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author devbi
 */
public class DBCon {

//    public static Connection getConnection() throws SQLException {
//
//        //Connection connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/barcode","root","root");
//        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/ashamedicalhall", "root", "root");
//        return connection;
//
//    }
    
     public static Connection getConnection() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/ashamedicalhall?zeroDateTimeBehavior=convertToNull";
        String user = "root";
        String pass = "root";

        Connection connection = DriverManager.getConnection(url,user,pass);

        return connection;

    }

   
}
