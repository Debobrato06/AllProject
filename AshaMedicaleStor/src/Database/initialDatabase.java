/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Database;

import static java.awt.JobAttributes.DestinationType.FILE;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import static javax.print.attribute.standard.ReferenceUriSchemesSupported.FILE;

/**
 *
 * @author Debobrato Biswas
 */
public class initialDatabase {

    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3306/";

    //  Database credentials
    static final String USER = "root";
    static final String PASS = "root";

    //  Database Create''''''''''
    public void creatDatabse() throws ClassNotFoundException {

        Connection conn = null;
        Statement stmt = null;
        try {
            //STEP 2: Register JDBC driver
            Class.forName("com.mysql.jdbc.Driver");

            //STEP 3: Open a connection
            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            //STEP 4: Execute a query
            System.out.println("Creating database...");
            stmt = conn.createStatement();

            String sql = "CREATE DATABASE IF NOT EXISTS ashamedicalestore ";
            stmt.executeUpdate(sql);
            System.out.println("Database created successfully...");
        } catch (SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
        }

    }

    //// database into create table... setup........Start.... 
    public void tbl_drug() throws ClassNotFoundException {

        Connection conn = null;
        Statement stmt = null;
        try {
            //STEP 2: Register JDBC driver
            Class.forName("com.mysql.jdbc.Driver");

            //STEP 3: Open a connection
            System.out.println("Connecting to a selected database...");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/ashamedicalestore", "root", "root");
            System.out.println("Connected database successfully...");

            //STEP 4: Execute a query
            System.out.println("Creating drug table in given database...");
            stmt = conn.createStatement();//drug_id, drug_name, drug_power, drug_price
            String createDrugTableSql = "CREATE TABLE IF NOT EXISTS `tbl_drug` ("
                    + "`drug_id` int(11)  UNSIGNED NOT NULL AUTO_INCREMENT,"
                    + "`drug_name` varchar(45) NOT NULL,"
                    + " `drug_power` varchar(45) NOT NULL,"
                    + "`drug_price` varchar(45) NOT NULL,"
                    + "PRIMARY KEY(`drug_id`)"
                    + ") ENGINE=InnoDB DEFAULT CHARSET=utf8";

            stmt.executeUpdate(createDrugTableSql);
            System.out.println("Created drug table in given database...");
        } catch (SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
        }
    }

    /////////
    public void drugBillTable() throws ClassNotFoundException {

        Connection conn = null;
        Statement stmt = null;
        try {
            //STEP 2: Register JDBC driver
            Class.forName("com.mysql.jdbc.Driver");

            //STEP 3: Open a connection
            System.out.println("Connecting to a selected database...");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/ashamedicalstore", "root", "root");
            System.out.println("Connected database successfully...");

            //STEP 4: Execute a query
            System.out.println("Creating drugBillTable table in given database...");
            stmt = conn.createStatement();
//id, drug_name, drug_perprice, quantity, total_price, invoice_no   ashamedicalstore.tbl_drugbill
            String bkashTransactionsql = "CREATE TABLE IF NOT EXISTS `tbl_drugbill`  ("
                    + "`id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT,"
                    + "`drug_name` varchar(45) NOT NULL,"
                    + " `drug_perprice` varchar(45) NOT NULL,"
                    + "`quantity` varchar(45) NOT NULL,"
                    + " `total_price` varchar(45) NOT NULL,"
                    + "`invoice_no` varchar(45) NOT NULL,"
                    + "PRIMARY KEY(`id`)"
                    + ") ENGINE=InnoDB DEFAULT CHARSET=utf8";

            stmt.executeUpdate(bkashTransactionsql);
            System.out.println("Created drugBillTable table in given database...");
        } catch (SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
        }
    }

    /////////
    public void tblPayment() throws ClassNotFoundException {

        Connection conn = null;
        Statement stmt = null;
        try {
            //STEP 2: Register JDBC driver
            Class.forName("com.mysql.jdbc.Driver");

            //STEP 3: Open a connection
            System.out.println("Connecting to a selected database...");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/ashamedicalstore", "root", "root");
            System.out.println("Connected database successfully...");

            //STEP 4: Execute a query
            System.out.println("Creating tblPayment table in given database...");
            stmt = conn.createStatement();

            String tblPaymentSql = "CREATE TABLE IF NOT EXISTS `ashamedicalstore`.`tbl_payment` ("
                    + "`payment_id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT,"
                    + "`drug_name` VARCHAR(45) NOT NULL DEFAULT '',"
                    + "`quantity` VARCHAR(45) NOT NULL DEFAULT '',"
                    + "`total_price` VARCHAR(45) NOT NULL DEFAULT '',"
                    + "`vat` VARCHAR(45) NOT NULL DEFAULT '',"
                    + "`paid_amount` VARCHAR(45) NOT NULL DEFAULT '',"
                    + "`return_amount` VARCHAR(45) NOT NULL DEFAULT '',"
                    + "PRIMARY KEY(`payment_id`)"
                    + ")ENGINE = InnoDB;";

            stmt.executeUpdate(tblPaymentSql);
            System.out.println("Created tblPayment table in given database...");
        } catch (SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
        }
    }

    ////Insert data into database ...........End....
}
