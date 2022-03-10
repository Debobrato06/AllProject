/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author devbi
 */
public class DBConn {

    public static Connection getConnection() throws SQLException {

        //Connection connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/barcode","root","root");
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/pharmacy_shop", "root", "root");
        return connection;

    }

    public static void dbExecuteQuery(String sql) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
