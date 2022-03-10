/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import controller.InvoiceGenerateController;
import static helper.config.DB_URL;
import static helper.config.PASS;
import static helper.config.USER;
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

//    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
//    static final String DB_URL = "jdbc:mysql://localhost:3306/";
//
//    //  Database credentials
//    static final String USER = "root";
//    static final String PASS = "root";

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

            String sql = "CREATE DATABASE IF NOT EXISTS Restaurent ";
            stmt.executeUpdate(sql);
            System.out.println("Database created successfully...");
        } catch (SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
        }

    }

    //// database into create table... setup........Start.... 
    public void bankTransaction() throws ClassNotFoundException {

        Connection conn = null;
        Statement stmt = null;
        try {
            //STEP 2: Register JDBC driver
            Class.forName("com.mysql.jdbc.Driver");

            //STEP 3: Open a connection
            System.out.println("Connecting to a selected database...");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Restaurent", "root", "root");
            System.out.println("Connected database successfully...");

            //STEP 4: Execute a query
            System.out.println("Creating bankTransaction table in given database...");
            stmt = conn.createStatement();
            String createBankTransactionSql = "CREATE TABLE IF NOT EXISTS `tbl_bank_transaction` ("
                    + "`id` int(11) NOT NULL ,"
                    + "`payment_id` int(11) NOT NULL,"
                    + " `account_name` text NOT NULL,"
                    + "`account_no` text NOT NULL,"
                    + "`branch_name` text NOT NULL,"
                    + "`bank_name` text NOT NULL,"
                    + "`transaction_id` text NOT NULL"
                    + ") ENGINE=InnoDB DEFAULT CHARSET=utf8";

            stmt.executeUpdate(createBankTransactionSql);
            System.out.println("Created bankTransaction table in given database...");
        } catch (SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
        }
    }

    /////////
    public void bkashTransaction() throws ClassNotFoundException {

        Connection conn = null;
        Statement stmt = null;
        try {
            //STEP 2: Register JDBC driver
            Class.forName("com.mysql.jdbc.Driver");

            //STEP 3: Open a connection
            System.out.println("Connecting to a selected database...");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Restaurent", "root", "root");
            System.out.println("Connected database successfully...");

            //STEP 4: Execute a query
            System.out.println("Creating bkashTransaction table in given database...");
            stmt = conn.createStatement();

            String bkashTransactionsql = "CREATE TABLE IF NOT EXISTS `tbl_bkash_transaction`  ("
                    + "`id` int(11) NOT NULL,"
                    + " `payment_id` int(11) NOT NULL,"
                    + " `transaction_id` text NOT NULL,"
                    + "`sender` text NOT NULL"
                    + ") ENGINE=InnoDB DEFAULT CHARSET=utf8";

            stmt.executeUpdate(bkashTransactionsql);
            System.out.println("Created bkashTransaction table in given database...");
        } catch (SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
        }
    }

    /////////
    public void categoryTable() throws ClassNotFoundException {

        Connection conn = null;
        Statement stmt = null;
        try {
            //STEP 2: Register JDBC driver
            Class.forName("com.mysql.jdbc.Driver");

            //STEP 3: Open a connection
            System.out.println("Connecting to a selected database...");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Restaurent", "root", "root");
            System.out.println("Connected database successfully...");

            //STEP 4: Execute a query
            System.out.println("Creating categoryTable table in given database...");
            stmt = conn.createStatement();

            String categoryTableSql = "CREATE TABLE IF NOT EXISTS `tbl_category` (\n"
                    + "  `id` int(11) NOT NULL,\n"
                    + "  `name_bn` text COLLATE utf8_bin NOT NULL,\n"
                    + "  `name_en` text COLLATE utf8_bin NOT NULL,\n"
                    + "  `parent_id` int(11) NOT NULL DEFAULT '0',\n"
                    + "  `priority` int(11) NOT NULL COMMENT 'max top, min low',\n"
                    + "  `deleted` tinyint(4) NOT NULL DEFAULT '0' COMMENT '0=visible, 1= delete',\n"
                    + "  `insert_by` int(11) NOT NULL,\n"
                    + "  `insert_time` varchar(20) COLLATE utf8_bin NOT NULL DEFAULT '0000-00-00 00:00'\n"
                    + ") ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin";

            stmt.executeUpdate(categoryTableSql);
            System.out.println("Created categoryTable table in given database...");
        } catch (SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
        }
    }

    /////////
    public void clientsTable() throws ClassNotFoundException {

        Connection conn = null;
        Statement stmt = null;
        try {
            //STEP 2: Register JDBC driver
            Class.forName("com.mysql.jdbc.Driver");

            //STEP 3: Open a connection
            System.out.println("Connecting to a selected database...");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Restaurent", "root", "root");
            System.out.println("Connected database successfully...");

            //STEP 4: Execute a query
            System.out.println("Creating clientsTable table in given database...");
            stmt = conn.createStatement();

            String clientsTableSql = "CREATE TABLE IF NOT EXISTS `tbl_clients` (\n"
                    + "  `id` int(11) NOT NULL PRIMARY KEY AUTO_INCREMENT,\n"
                    + "  `name` text COLLATE utf8_bin NOT NULL,\n"
                    + "  `phone` varchar(20) COLLATE utf8_bin NOT NULL,\n"
                    + "  `email` text COLLATE utf8_bin NOT NULL,\n"
                    + "  `is_online` int(11) NOT NULL DEFAULT '0' COMMENT '0 = no, 1 = yes'\n"
                    + ") ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin";

            stmt.executeUpdate(clientsTableSql);
            System.out.println("Created clientsTable table in given database...");
        } catch (SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
        }
    }

    /////////
    public void divissionTable() throws ClassNotFoundException {

        Connection conn = null;
        Statement stmt = null;
        try {
            //STEP 2: Register JDBC driver
            Class.forName("com.mysql.jdbc.Driver");

            //STEP 3: Open a connection
            System.out.println("Connecting to a selected database...");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Restaurent", "root", "root");
            System.out.println("Connected database successfully...");

            //STEP 4: Execute a query
            System.out.println("Creating divissionTable table in given database...");
            stmt = conn.createStatement();

            String divissionTableSql = "CREATE TABLE IF NOT EXISTS `tbl_divission` (\n"
                    + "  `id` int(11) NOT NULL,\n"
                    + "  `name` varchar(255) NOT NULL\n"
                    + ") ENGINE=InnoDB DEFAULT CHARSET=utf8";

            stmt.executeUpdate(divissionTableSql);
            System.out.println("Created divissionTable table in given database...");
        } catch (SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
        }
    }

    /////////
    public void foodBillTable() throws ClassNotFoundException {

        Connection conn = null;
        Statement stmt = null;
        try {
            //STEP 2: Register JDBC driver
            Class.forName("com.mysql.jdbc.Driver");

            //STEP 3: Open a connection
            System.out.println("Connecting to a selected database...");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Restaurent", "root", "root");
            System.out.println("Connected database successfully...");

            //STEP 4: Execute a query
            System.out.println("Creating foodBillTable table in given database...");
            stmt = conn.createStatement();

            String foodBillTableSql = "CREATE TABLE IF NOT EXISTS `tbl_food_bill` (\n"
                    + "  `id` int(11) NOT NULL PRIMARY KEY AUTO_INCREMENT,\n"
                    + "  `food_product_id` int(11) NOT NULL,\n"
                    + "  `quantity` int(11) NOT NULL,\n"
                    + "  `invoice` varchar(250) COLLATE utf8_bin NOT NULL,\n"
                    + "  `client_id` int(11) NOT NULL,\n"
                    + "  `bill_date` varchar(10) COLLATE utf8_bin NOT NULL DEFAULT '0000-00-00',\n"
                    + "  `insert_time` varchar(20) COLLATE utf8_bin NOT NULL DEFAULT '0000-00-00 00:00',\n"
                    + "  `user_id` int(11) NOT NULL,\n"
                    + "  `insert_by` int(11) NOT NULL,\n"
                    + "  `is_online` int(11) NOT NULL DEFAULT '0' COMMENT '0 = not, 1 = yes'\n"
                    + ") ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin";

            stmt.executeUpdate(foodBillTableSql);
            System.out.println("Created foodBillTable table in given database...");
        } catch (SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
        }
    }

    /////////
    public void foodItemOfferTable() throws ClassNotFoundException {

        Connection conn = null;
        Statement stmt = null;
        try {
            //STEP 2: Register JDBC driver
            Class.forName("com.mysql.jdbc.Driver");

            //STEP 3: Open a connection
            System.out.println("Connecting to a selected database...");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Restaurent", "root", "root");
            System.out.println("Connected database successfully...");

            //STEP 4: Execute a query
            System.out.println("Creating foodItemOfferTable table in given database...");
            stmt = conn.createStatement();

            String foodItemOfferTableSql = "CREATE TABLE IF NOT EXISTS `tbl_food_item_offer` (\n"
                    + "  `id` int(11) NOT NULL,\n"
                    + "  `food_product_id` int(11) NOT NULL,\n"
                    + "  `start_date` varchar(10) COLLATE utf8_bin NOT NULL DEFAULT '0000-00-00',\n"
                    + "  `end_date` varchar(10) COLLATE utf8_bin NOT NULL DEFAULT '0000-00-00',\n"
                    + "  `discount_percent` double NOT NULL DEFAULT '0' COMMENT 'out of 100%',\n"
                    + "  `insert_by` int(11) NOT NULL,\n"
                    + "  `insert_time` varchar(20) COLLATE utf8_bin NOT NULL DEFAULT '0000-00-00 00:00'\n"
                    + ") ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin ROW_FORMAT=COMPACT";

            stmt.executeUpdate(foodItemOfferTableSql);
            System.out.println("Created foodItemOfferTable table in given database...");
        } catch (SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
        }
    }

    /////////
    public void foodItemTypeTable() throws ClassNotFoundException {

        Connection conn = null;
        Statement stmt = null;
        try {
            //STEP 2: Register JDBC driver
            Class.forName("com.mysql.jdbc.Driver");

            //STEP 3: Open a connection
            System.out.println("Connecting to a selected database...");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Restaurent", "root", "root");
            System.out.println("Connected database successfully...");

            //STEP 4: Execute a query
            System.out.println("Creating foodItemTypeTable table in given database...");
            stmt = conn.createStatement();

            String foodItemTypeTableeSql = "CREATE TABLE IF NOT EXISTS `tbl_food_item_type` (\n"
                    + "  `id` int(11) NOT NULL,\n"
                    + "  `name_bn` text COLLATE utf8_bin NOT NULL,\n"
                    + "  `name_en` text COLLATE utf8_bin NOT NULL,\n"
                    + "  `insert_by` int(11) NOT NULL,\n"
                    + "  `insert_time` varchar(20) COLLATE utf8_bin NOT NULL DEFAULT '0000-00-00 00:00'\n"
                    + ") ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin ROW_FORMAT=COMPACT";

            stmt.executeUpdate(foodItemTypeTableeSql);
            System.out.println("Created foodItemTypeTable table in given database...");
        } catch (SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
        }
    }

    /////////
    public void foodOrderDeliveryTable() throws ClassNotFoundException {

        Connection conn = null;
        Statement stmt = null;
        try {
            //STEP 2: Register JDBC driver
            Class.forName("com.mysql.jdbc.Driver");

            //STEP 3: Open a connection
            System.out.println("Connecting to a selected database...");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Restaurent", "root", "root");
            System.out.println("Connected database successfully...");

            //STEP 4: Execute a query
            System.out.println("Creating foodOrderDeliveryTable table in given database...");
            stmt = conn.createStatement();

            String foodOrderDeliveryTableSql = "CREATE TABLE IF NOT EXISTS `tbl_food_order_delivery` (\n"
                    + "  `id` int(11) NOT NULL,\n"
                    + "  `food_payment_id` int(11) NOT NULL,\n"
                    + "  `delivery_division_id` int(11) NOT NULL,\n"
                    + "  `delivery_zilla_id` int(11) NOT NULL,\n"
                    + "  `delivery_thana_id` int(11) NOT NULL,\n"
                    + "  `delivery_post` int(11) NOT NULL,\n"
                    + "  `delivery_house_no` text COLLATE utf8_bin\n"
                    + ") ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin";

            stmt.executeUpdate(foodOrderDeliveryTableSql);
            System.out.println("Created foodOrderDeliveryTable table in given database...");
        } catch (SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
        }
    }

    /////////
    public void foodPaymentTable() throws ClassNotFoundException {

        Connection conn = null;
        Statement stmt = null;
        try {
            //STEP 2: Register JDBC driver
            Class.forName("com.mysql.jdbc.Driver");

            //STEP 3: Open a connection
            System.out.println("Connecting to a selected database...");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Restaurent", "root", "root");
            System.out.println("Connected database successfully...");

            //STEP 4: Execute a query
            System.out.println("Creating foodPaymentTable table in given database...");
            stmt = conn.createStatement();

            String foodPaymentTableSql = "CREATE TABLE IF NOT EXISTS `tbl_food_payment` (\n"
                    + "  `id` int(11) NOT NULL PRIMARY KEY AUTO_INCREMENT,\n"
                    + "  `client_id` int(11) NOT NULL,\n"
                    + "  `invoice` varchar(255) COLLATE utf8_bin NOT NULL,\n"
                    + "  `amount` double(10,2) NOT NULL,\n"
                    + "  `discount` double NOT NULL,\n"
                    + "  `vat` double NOT NULL DEFAULT '0',\n"
                    + "  `net_amount` double NOT NULL DEFAULT '0',\n"
                    + "  `paid_amount` double NOT NULL DEFAULT '0',\n"
                    + "  `return_amount` double NOT NULL DEFAULT '0',\n"
                    + "  `payment_date` varchar(10) COLLATE utf8_bin NOT NULL DEFAULT '0000-00-00',\n"
                    + "  `user_id` int(11) NOT NULL,\n"
                    + "  `method_id` int(11) NOT NULL DEFAULT '0',\n"
                    + "  `insert_time` varchar(20) COLLATE utf8_bin NOT NULL DEFAULT '0000-00-00 00:00',\n"
                    + "  `insert_by` int(11) NOT NULL,\n"
                    + "  `order_approve` int(11) NOT NULL DEFAULT '0' COMMENT '1=yes, 0 = no',\n"
                    + "  `approved_time` varchar(20) COLLATE utf8_bin NOT NULL DEFAULT '0000-00-00 00:00',\n"
                    + "  `delivery_time` varchar(20) COLLATE utf8_bin NOT NULL DEFAULT '0000-00-00 00:00',\n"
                    + "  `is_online` int(11) NOT NULL DEFAULT '0' COMMENT '0 = not, 1 = yes'\n"
                    + ") ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin";

            stmt.executeUpdate(foodPaymentTableSql);
            System.out.println("Created foodPaymentTable table in given database...");
        } catch (SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
        }
    }

    /////////
    public void foodProductsTable() throws ClassNotFoundException {

        Connection conn = null;
        Statement stmt = null;
        try {
            //STEP 2: Register JDBC driver
            Class.forName("com.mysql.jdbc.Driver");

            //STEP 3: Open a connection
            System.out.println("Connecting to a selected database...");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Restaurent", "root", "root");
            System.out.println("Connected database successfully...");

            //STEP 4: Execute a query
            System.out.println("Creating foodProductsTable table in given database...");
            stmt = conn.createStatement();

            String foodProductsTableSql = " CREATE TABLE IF NOT EXISTS `tbl_food_products` (\n"
                    + "  `id` int(11) NOT NULL PRIMARY KEY AUTO_INCREMENT,\n"
                    + "  `name` text COLLATE utf8_bin NOT NULL,\n"
                    + "  `url_generate` varchar(255) COLLATE utf8_bin NOT NULL,\n"
                    + "  `description` text COLLATE utf8_bin NOT NULL,\n"
                    + "  `photo` text COLLATE utf8_bin NOT NULL,\n"
                    + "  `price` double NOT NULL,\n"
                    + "  `item_type_id` int(11) NOT NULL COMMENT 'dirnsk, solid',\n"
                    + "  `category_id` int(11) NOT NULL,\n"
                    + "  `delivary_policy_id` int(11) DEFAULT NULL,\n"
                    + "  `term_policy_id` int(11) DEFAULT NULL,\n"
                    + "  `insert_by` int(11) NOT NULL,\n"
                    + "  `insert_time` varchar(20) COLLATE utf8_bin NOT NULL DEFAULT '0000-00-00 00:00',\n"
                    + "  `last_update` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP\n"
                    + ") ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin";

            stmt.executeUpdate(foodProductsTableSql);
            System.out.println("Created foodProductsTable table in given database...");
        } catch (SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
        }
    }

    /////////
    public void methodTable() throws ClassNotFoundException {

        Connection conn = null;
        Statement stmt = null;
        try {
            //STEP 2: Register JDBC driver
            Class.forName("com.mysql.jdbc.Driver");

            //STEP 3: Open a connection
            System.out.println("Connecting to a selected database...");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Restaurent", "root", "root");
            System.out.println("Connected database successfully...");

            //STEP 4: Execute a query
            System.out.println("Creating methodTable table in given database...");
            stmt = conn.createStatement();

            String methodTableSql = " CREATE TABLE IF NOT EXISTS `tbl_method` (\n"
                    + "  `id` int(11) NOT NULL,\n"
                    + "  `name` text NOT NULL\n"
                    + ") ENGINE=InnoDB DEFAULT CHARSET=utf8";

            stmt.executeUpdate(methodTableSql);
            System.out.println("Created methodTable table in given database...");
        } catch (SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
        }
    }

    /////////
    public void upozillaTable() throws ClassNotFoundException {

        Connection conn = null;
        Statement stmt = null;
        try {
            //STEP 2: Register JDBC driver
            Class.forName("com.mysql.jdbc.Driver");

            //STEP 3: Open a connection
            System.out.println("Connecting to a selected database...");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Restaurent", "root", "root");
            System.out.println("Connected database successfully...");

            //STEP 4: Execute a query
            System.out.println("Creating upozillaTable table in given database...");
            stmt = conn.createStatement();

            String upozillaTableSql = " CREATE TABLE IF NOT EXISTS `tbl_upozilla` (\n"
                    + "  `id` int(11) NOT NULL,\n"
                    + "  `division_id` int(11) NOT NULL,\n"
                    + "  `zilla_id` int(11) NOT NULL,\n"
                    + "  `name` varchar(255) NOT NULL\n"
                    + ") ENGINE=InnoDB DEFAULT CHARSET=utf8";

            stmt.executeUpdate(upozillaTableSql);
            System.out.println("Created upozillaTable table in given database...");
        } catch (SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
        }
    }

    /////////
    public void zillaTable() throws ClassNotFoundException {

        Connection conn = null;
        Statement stmt = null;
        try {
            //STEP 2: Register JDBC driver
            Class.forName("com.mysql.jdbc.Driver");

            //STEP 3: Open a connection
            System.out.println("Connecting to a selected database...");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Restaurent", "root", "root");
            System.out.println("Connected database successfully...");

            //STEP 4: Execute a query
            System.out.println("Creating zillaTable table in given database...");
            stmt = conn.createStatement();

            String zillaTableSql = " CREATE TABLE IF NOT EXISTS `tbl_zilla` (\n"
                    + "  `id` int(11) NOT NULL,\n"
                    + "  `divission_id` int(11) NOT NULL,\n"
                    + "  `name` varchar(255) NOT NULL\n"
                    + ") ENGINE=InnoDB DEFAULT CHARSET=utf8";

            stmt.executeUpdate(zillaTableSql);
            System.out.println("Created zillaTable table in given database...");
        } catch (SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
        }
    }

    /////////
    public void userTable() throws ClassNotFoundException {

        Connection conn = null;
        Statement stmt = null;
        try {
            //STEP 2: Register JDBC driver
            Class.forName("com.mysql.jdbc.Driver");

            //STEP 3: Open a connection
            System.out.println("Connecting to a selected database...");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Restaurent", "root", "root");
            System.out.println("Connected database successfully...");

            //STEP 4: Execute a query
            System.out.println("Creating userTable table in given database...");
            stmt = conn.createStatement();

            String userSql = " CREATE TABLE IF NOT EXISTS `user` (\n"
                    + "  `id` int(11) NOT NULL PRIMARY KEY AUTO_INCREMENT,\n"
                    + "  `name` text NOT NULL,\n"
                    + "  `userName` varchar(200) NOT NULL,\n"
                    + "  `email` varchar(200) NOT NULL,\n"
                    + "  `password` text NOT NULL,\n"
                    + "  `address` text NOT NULL,\n"
                    + "  `phone` text NOT NULL,\n"
                    + "  `photo` text CHARACTER SET utf8 COLLATE utf8_bin NOT NULL\n"
                    + ") ENGINE=InnoDB DEFAULT CHARSET=latin1";

            stmt.executeUpdate(userSql);

            System.out.println("Created userTable table in given database...");
        } catch (SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
        }
    }

    /////////
    public void moneyReceiptSettingTable() throws ClassNotFoundException {

        Connection conn = null;
        Statement stmt = null;
        try {
            //STEP 2: Register JDBC driver
            Class.forName("com.mysql.jdbc.Driver");

            //STEP 3: Open a connection
            System.out.println("Connecting to a selected database...");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Restaurent", "root", "root");
            System.out.println("Connected database successfully...");

            //STEP 4: Execute a query
            System.out.println("Creating moneyReceiptSettingTable table in given database...");
            stmt = conn.createStatement();

            String moneyReceiptSettingSql = " CREATE TABLE IF NOT EXISTS `tbl_money_receipt_setting` (\n"
                    + "  `id` int(11) NOT NULL,\n"
                    + "  `restaurent_name` text COLLATE utf8_unicode_ci,\n"
                    + "  `restaurent_address` text COLLATE utf8_unicode_ci,\n"
                    + "  `email` text COLLATE utf8_unicode_ci,\n"
                    + "  `phone` text COLLATE utf8_unicode_ci,\n"
                    + "  `title_tag` text COLLATE utf8_unicode_ci,\n"
                    + "  `bottom_text` text COLLATE utf8_unicode_ci,\n"
                    + "  `insert_by` int(11) NOT NULL,\n"
                    + "  `insert_time` varchar(20) COLLATE utf8_unicode_ci NOT NULL DEFAULT '0000-00-00 00:00'\n"
                    + ") ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci";

            stmt.executeUpdate(moneyReceiptSettingSql);
            System.out.println("Created moneyReceiptSettingTable table in given database...");
        } catch (SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
        }
    }

    ////database into create table... setup........End....
    ////Insert data into database ...........Start....
    /////////
    public void userInsertTable() throws ClassNotFoundException, SQLException {
        
        Connection connection = DBConn.getConnection();
        Statement statement = connection.createStatement();
        String truncateTable = "TRUNCATE TABLE  restaurent.user";
        statement.execute(truncateTable);

        try {
            //STEP 2: Register JDBC driver
            Class.forName("com.mysql.jdbc.Driver");

            //STEP 3: Open a connection
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Restaurent", "root", "root");
            System.out.println("Connected database successfully...");

            //STEP 4: Execute a query
            System.out.println("Inserting methodInsertTable table in given database...");
            Statement stmt = conn.createStatement();

            // String zillaInsertSql = ;
            stmt.execute("INSERT INTO restaurent.user(id, name, userName, email, password, address, phone, photo ) VALUES(1,'Dev biswas','admin','debobratocse06@gmail.com','admin','Jashore','01778451499','photo')");
            System.out.println("Inseted methodInsertTable table in given database...");
        } catch (SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
        }
    }

    /////////
    /////////
    public void methodInsertTable() throws ClassNotFoundException {

        try {
            //STEP 2: Register JDBC driver
            Class.forName("com.mysql.jdbc.Driver");

            //STEP 3: Open a connection
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Restaurent", "root", "root");
            System.out.println("Connected database successfully...");

            //STEP 4: Execute a query
            System.out.println("Inserting methodInsertTable table in given database...");
            Statement stmt = conn.createStatement();

            // String zillaInsertSql = ;
            stmt.execute("INSERT INTO `tbl_method` (`id`, `name`) VALUES\n"
                    + "(1, 'BKASH'),\n"
                    + "(2, 'BANK'),\n"
                    + "(3, 'CASH')");
            System.out.println("Inseted methodInsertTable table in given database...");
        } catch (SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
        }
    }

    /////////
    public void divissionInsertTable() throws ClassNotFoundException {

        try {
            //STEP 2: Register JDBC driver
            Class.forName("com.mysql.jdbc.Driver");

            //STEP 3: Open a connection
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Restaurent", "root", "root");
            System.out.println("Connected database successfully...");

            //STEP 4: Execute a query
            System.out.println("Inserting divissionInsertTable table in given database...");
            Statement stmt = conn.createStatement();

            // String zillaInsertSql = ;
            stmt.execute(" INSERT INTO `tbl_divission` (`id`, `name`) VALUES\n"
                    + "(1, 'ঢাকা'),\n"
                    + "(2, 'রাজশাহী'),\n"
                    + "(3, 'চট্টগ্রাম'),\n"
                    + "(4, 'সিলেট'),\n"
                    + "(5, 'খুলনা'),\n"
                    + "(6, 'বরিশাল'),\n"
                    + "(7, 'রংপুর'),\n"
                    + "(8, 'ময়মনসিংহ')");
            System.out.println("Inseted divissionInsertTable table in given database...");
        } catch (SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
        }
    }

    /////////
    public void zillaInsertTable() throws ClassNotFoundException {

        try {
            //STEP 2: Register JDBC driver
            Class.forName("com.mysql.jdbc.Driver");

            //STEP 3: Open a connection
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Restaurent", "root", "root");
            System.out.println("Connected database successfully...");

            //STEP 4: Execute a query
            System.out.println("Inserting zillaTable table in given database...");
            Statement stmt = conn.createStatement();

            // String zillaInsertSql = ;
            stmt.execute("INSERT INTO `tbl_zilla` (`id`, `divission_id`, `name`) VALUES\n"
                    + "(1, 1, 'ঢাকা'),\n"
                    + "(2, 1, 'গাজীপুর'),\n"
                    + "(3, 1, 'টাঙ্গাইল'),\n"
                    + "(4, 1, 'নারায়ণগঞ্জ'),\n"
                    + "(5, 1, 'কিশোরগঞ্জ'),\n"
                    + "(6, 1, 'নরসিংদী'),\n"
                    + "(7, 1, 'রাজবাড়ী'),\n"
                    + "(8, 1, 'ফরিদপুর'),\n"
                    + "(9, 1, 'মাদারীপুর'),\n"
                    + "(10, 1, 'গোপালগঞ্জ'),\n"
                    + "(11, 1, 'মুন্সিগঞ্জ'),\n"
                    + "(12, 1, 'মানিকগঞ্জ'),\n"
                    + "(13, 1, 'শরীয়তপুর'),\n"
                    + "(14, 2, 'রাজশাহী'),\n"
                    + "(15, 2, 'সিরাজগঞ্জ'),\n"
                    + "(16, 2, 'পাবনা'),\n"
                    + "(17, 2, 'বগুড়া'),\n"
                    + "(18, 2, 'চাঁপাইনবাবগঞ্জ'),\n"
                    + "(19, 2, 'জয়পুরহাট'),\n"
                    + "(20, 2, 'নওগাঁ'),\n"
                    + "(21, 2, 'নাটোর'),\n"
                    + "(22, 3, 'চট্টগ্রাম'),\n"
                    + "(23, 3, 'কুমিল্লা'),\n"
                    + "(24, 3, 'ফেনী'),\n"
                    + "(25, 3, 'ব্রাহ্মণবাড়িয়া'),\n"
                    + "(26, 3, 'রাঙ্গামাটি'),\n"
                    + "(27, 3, 'চাঁদপুর'),\n"
                    + "(28, 3, 'নোয়াখালী'),\n"
                    + "(29, 3, 'লক্ষ্মীপুর'),\n"
                    + "(30, 3, 'কক্সবাজার'),\n"
                    + "(31, 3, 'খাগড়াছড়ি'),\n"
                    + "(32, 3, 'বান্দরবান'),\n"
                    + "(33, 4, 'সিলেট'),\n"
                    + "(34, 4, 'মৌলভীবাজার'),\n"
                    + "(35, 4, 'হবিগঞ্জ'),\n"
                    + "(36, 4, 'সুনামগঞ্জ'),\n"
                    + "(37, 5, 'খুলনা'),\n"
                    + "(38, 5, 'যশোর'),\n"
                    + "(39, 5, 'সাতক্ষীরা'),\n"
                    + "(40, 5, 'মেহেরপুর'),\n"
                    + "(41, 5, 'নড়াইল'),\n"
                    + "(42, 5, 'চুয়াডাঙ্গা'),\n"
                    + "(43, 5, 'মাগুড়া'),\n"
                    + "(44, 5, 'বাগেরহাট'),\n"
                    + "(45, 5, 'ঝিনাইদহ'),\n"
                    + "(46, 5, 'কুষ্টিয়া'),\n"
                    + "(47, 6, 'বরিশাল'),\n"
                    + "(48, 6, 'ঝালকাঠি'),\n"
                    + "(49, 6, 'পটুয়াখালী'),\n"
                    + "(50, 6, 'পিরোজপুর'),\n"
                    + "(51, 6, 'ভোলা'),\n"
                    + "(52, 6, 'বরগুনা'),\n"
                    + "(53, 7, 'রংপুর'),\n"
                    + "(54, 7, 'লালমনিরহাট'),\n"
                    + "(55, 7, 'পঞ্চগড়'),\n"
                    + "(56, 7, 'কুড়িগ্রাম'),\n"
                    + "(57, 7, 'দিনাজপুর'),\n"
                    + "(58, 7, 'ঠাকুরগাঁও'),\n"
                    + "(59, 7, 'গাইবান্ধা'),\n"
                    + "(60, 7, 'নীলফামারী'),\n"
                    + "(61, 8, 'ময়মনসিংহ'),\n"
                    + "(62, 8, 'জামালপুর'),\n"
                    + "(63, 8, 'নেত্রকোনা'),\n"
                    + "(64, 8, 'শেরপুর')");
            System.out.println("Inseted zillaTable table in given database...");
        } catch (SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
        }
    }

    /////////
    public void upozillaInsertTable() throws ClassNotFoundException {

        try {
            //STEP 2: Register JDBC driver
            Class.forName("com.mysql.jdbc.Driver");

            //STEP 3: Open a connection
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Restaurent", "root", "root");
            System.out.println("Connected database successfully...");

            //STEP 4: Execute a query
            System.out.println("Inserting upozillaTable table in given database...");
            Statement stmt = conn.createStatement();

            // String zillaInsertSql = ;
            stmt.execute(" INSERT INTO `tbl_upozilla` (`id`, `division_id`, `zilla_id`, `name`) VALUES\n"
                    + "(1, 1, 1, 'সাভার'),\n"
                    + "(2, 1, 1, 'ধামরাই'),\n"
                    + "(3, 1, 1, 'কেরাণীগঞ্জ'),\n"
                    + "(4, 1, 1, 'নবাবগঞ্জ'),\n"
                    + "(5, 1, 1, 'দোহার'),\n"
                    + "(6, 1, 1, 'তেজগাঁও উন্নয়ন সার্কেল'),\n"
                    + "(7, 1, 2, 'কালীগঞ্জ'),\n"
                    + "(8, 1, 2, 'কালিয়াকৈর'),\n"
                    + "(9, 1, 2, 'কাপাসিয়া'),\n"
                    + "(10, 1, 2, 'গাজীপুর সদর'),\n"
                    + "(11, 1, 2, 'শ্রীপুর'),\n"
                    + "(12, 1, 3, 'বাসাইল'),\n"
                    + "(13, 1, 3, 'ভুয়াপুর'),\n"
                    + "(14, 1, 3, 'ঘাটাইল'),\n"
                    + "(15, 1, 3, 'দেলদুয়ার'),\n"
                    + "(16, 1, 3, 'গোপালপুর'),\n"
                    + "(17, 1, 3, 'মধুপুর'),\n"
                    + "(18, 1, 3, 'মির্জাপুর'),\n"
                    + "(19, 1, 3, 'নাগরপুর'),\n"
                    + "(20, 1, 3, 'সখিপুর'),\n"
                    + "(21, 1, 3, 'টাঙ্গাইল সদর'),\n"
                    + "(22, 1, 3, 'কালিহাতী'),\n"
                    + "(23, 1, 3, 'ধনবাড়ি'),\n"
                    + "(24, 1, 4, 'আড়াইহাজার'),\n"
                    + "(25, 1, 4, 'বন্দর'),\n"
                    + "(26, 1, 4, 'নারায়ণগঞ্জ সদর'),\n"
                    + "(27, 1, 4, 'রূপগঞ্জ'),\n"
                    + "(28, 1, 4, 'সোনারগাঁ'),\n"
                    + "(29, 1, 5, 'ইটনা'),\n"
                    + "(30, 1, 5, 'কটিয়াদি'),\n"
                    + "(31, 1, 5, 'ভৈরব'),\n"
                    + "(32, 1, 5, 'হোসেনপুর'),\n"
                    + "(33, 1, 5, 'তাড়াইল'),\n"
                    + "(34, 1, 5, 'পাকুন্দিয়া'),\n"
                    + "(35, 1, 5, 'কুলিয়ারচর'),\n"
                    + "(36, 1, 5, 'কিশোরগঞ্জ সদর'),\n"
                    + "(37, 1, 5, 'করিমগঞ্জ'),\n"
                    + "(38, 1, 5, 'বাজিতপুর'),\n"
                    + "(39, 1, 5, 'অষ্টগ্রাম'),\n"
                    + "(40, 1, 5, 'মিঠামইন'),\n"
                    + "(41, 1, 5, 'নিকলী'),\n"
                    + "(42, 1, 6, 'বেলাবো'),\n"
                    + "(43, 1, 6, 'মনোহরদী'),\n"
                    + "(44, 1, 6, 'নরসিংদী সদর'),\n"
                    + "(45, 1, 6, 'পলাশ'),\n"
                    + "(46, 1, 6, 'রায়পুরা'),\n"
                    + "(47, 1, 6, 'শিবপুর'),\n"
                    + "(48, 1, 7, 'রাজবাড়ী সদর'),\n"
                    + "(49, 1, 7, 'গোয়ালন্দ'),\n"
                    + "(50, 1, 7, 'পাংশা'),\n"
                    + "(51, 1, 7, 'বালিয়াকান্দি'),\n"
                    + "(52, 1, 7, 'কালুখালী'),\n"
                    + "(53, 1, 8, 'ফরিদপুর সদর'),\n"
                    + "(54, 1, 8, 'আলফাডাঙ্গা'),\n"
                    + "(55, 1, 8, 'বোয়ালমারী'),\n"
                    + "(56, 1, 8, 'সদরপুর'),\n"
                    + "(57, 1, 8, 'নগরকান্দা'),\n"
                    + "(58, 1, 8, 'ভাঙ্গা'),\n"
                    + "(59, 1, 8, 'চরভদ্রাসন'),\n"
                    + "(60, 1, 8, 'মধুখালী'),\n"
                    + "(61, 1, 8, 'সালথা'),\n"
                    + "(62, 1, 9, 'মাদারীপুর সদর'),\n"
                    + "(63, 1, 9, 'শিবচর'),\n"
                    + "(64, 1, 9, 'কালকিনি'),\n"
                    + "(65, 1, 9, 'রাজৈর'),\n"
                    + "(66, 1, 10, 'গোপালগঞ্জ সদর'),\n"
                    + "(67, 1, 10, 'কাশিয়ানী'),\n"
                    + "(68, 1, 10, 'টুংগীপাড়া'),\n"
                    + "(69, 1, 10, 'কোটালীপাড়া'),\n"
                    + "(70, 1, 10, 'মুকসুদপুর'),\n"
                    + "(71, 1, 11, 'মুন্সিগঞ্জ সদর'),\n"
                    + "(72, 1, 11, 'শ্রীনগর'),\n"
                    + "(73, 1, 11, 'সিরাজদিখান'),\n"
                    + "(74, 1, 11, 'লৌহজং '),\n"
                    + "(75, 1, 11, 'গজারিয়া'),\n"
                    + "(76, 1, 11, 'টংগিবাড়ী'),\n"
                    + "(77, 1, 12, 'হরিরামপুর'),\n"
                    + "(78, 1, 12, 'সাটুরিয়া'),\n"
                    + "(79, 1, 12, 'মানিকগঞ্জ সদর'),\n"
                    + "(80, 1, 12, 'ঘিওর'),\n"
                    + "(81, 1, 12, 'শিবালয়'),\n"
                    + "(82, 1, 12, 'দৌলতপুর'),\n"
                    + "(83, 1, 12, 'সিংগাইর'),\n"
                    + "(84, 1, 13, 'শরিয়তপুর সদর'),\n"
                    + "(85, 1, 13, 'নড়িয়া'),\n"
                    + "(86, 1, 13, 'জাজিরা'),\n"
                    + "(87, 1, 13, 'গোসাইরহাট'),\n"
                    + "(88, 1, 13, 'ভেদরগঞ্জ'),\n"
                    + "(89, 1, 13, 'ডামুড্যা'),\n"
                    + "(90, 2, 14, 'পবা'),\n"
                    + "(91, 2, 14, 'দুর্গাপুর'),\n"
                    + "(92, 2, 14, 'মোহনপুর'),\n"
                    + "(93, 2, 14, 'চারঘাট'),\n"
                    + "(94, 2, 14, 'পুঠিয়া'),\n"
                    + "(95, 2, 14, 'বাঘা'),\n"
                    + "(96, 2, 14, 'গোদাগাড়ী'),\n"
                    + "(97, 2, 14, 'তানোর'),\n"
                    + "(98, 2, 14, 'বাঘমারা'),\n"
                    + "(99, 2, 15, 'বেলকুচি'),\n"
                    + "(100, 2, 15, 'চৌহালি'),\n"
                    + "(101, 2, 15, 'কামারখন্দ'),\n"
                    + "(102, 2, 15, 'কাজীপুর'),\n"
                    + "(103, 2, 15, 'রায়গঞ্জ'),\n"
                    + "(104, 2, 15, 'শাহজাদপুর'),\n"
                    + "(105, 2, 15, 'সিরাজগঞ্জ সদর'),\n"
                    + "(106, 2, 15, 'তাড়াশ'),\n"
                    + "(107, 2, 15, 'উল্লাপাড়া'),\n"
                    + "(108, 2, 16, 'সুজানগর'),\n"
                    + "(109, 2, 16, 'ঈশ্বরদী'),\n"
                    + "(110, 2, 16, 'ভাঙ্গুরা'),\n"
                    + "(111, 2, 16, 'পাবনা সদর'),\n"
                    + "(112, 2, 16, 'বেড়া'),\n"
                    + "(113, 2, 16, 'আটঘরিয়া'),\n"
                    + "(114, 2, 16, 'চাটমোহর'),\n"
                    + "(115, 2, 16, 'সাঁথিয়া'),\n"
                    + "(116, 2, 16, 'ফরিদপুর'),\n"
                    + "(117, 2, 17, 'কাহালু'),\n"
                    + "(118, 2, 17, 'বগুড়া সদর'),\n"
                    + "(119, 2, 17, 'সারিয়াকান্দি'),\n"
                    + "(120, 2, 17, 'শাজাহানপুর'),\n"
                    + "(121, 2, 17, 'দুপচাঁচিয়া'),\n"
                    + "(122, 2, 17, 'আদমদিঘি'),\n"
                    + "(123, 2, 17, 'নন্দিগ্রাম'),\n"
                    + "(124, 2, 17, 'সোনাতলা'),\n"
                    + "(125, 2, 17, 'ধুনট'),\n"
                    + "(126, 2, 17, 'গাবতলী'),\n"
                    + "(127, 2, 17, 'শেরপুর'),\n"
                    + "(128, 2, 17, 'শিবগঞ্জ'),\n"
                    + "(129, 2, 18, 'চাঁপাইনবাবগঞ্জ সদর'),\n"
                    + "(130, 2, 18, 'গোমস্তাপুর'),\n"
                    + "(131, 2, 18, 'নাচোল'),\n"
                    + "(132, 2, 18, 'ভোলাহাট'),\n"
                    + "(133, 2, 18, 'শিবগঞ্জ'),\n"
                    + "(134, 2, 19, 'আক্কেলপুর'),\n"
                    + "(135, 2, 19, 'কালাই'),\n"
                    + "(136, 2, 19, 'ক্ষেতলাল'),\n"
                    + "(137, 2, 19, 'পাঁচবিবি'),\n"
                    + "(138, 2, 19, 'জয়পুরহাট সদর'),\n"
                    + "(139, 2, 20, 'মহাদেবপুর'),\n"
                    + "(140, 2, 20, 'বদলগাছী'),\n"
                    + "(141, 2, 20, 'পত্নিতলা'),\n"
                    + "(142, 2, 20, 'ধামইরহাট'),\n"
                    + "(143, 2, 20, 'নিয়ামতপুর'),\n"
                    + "(144, 2, 20, 'মান্দা'),\n"
                    + "(145, 2, 20, 'আত্রাই'),\n"
                    + "(146, 2, 20, 'রাণীনগর'),\n"
                    + "(147, 2, 20, 'নওগাঁ সদর'),\n"
                    + "(148, 2, 20, 'সাপাহার'),\n"
                    + "(149, 2, 20, 'পোরশা'),\n"
                    + "(150, 2, 21, 'নাটোর সদর'),\n"
                    + "(151, 2, 21, 'সিংড়া'),\n"
                    + "(152, 2, 21, 'বড়াইগ্রাম'),\n"
                    + "(153, 2, 21, 'বাগাতিপাড়া'),\n"
                    + "(154, 2, 21, 'গুরুদাসপুর'),\n"
                    + "(155, 2, 21, 'লালপুর'),\n"
                    + "(156, 2, 21, 'নলডাঙ্গা'),\n"
                    + "(157, 3, 22, 'রাঙ্গুনিয়া'),\n"
                    + "(158, 3, 22, 'সীতাকুণ্ড'),\n"
                    + "(159, 3, 22, 'মীরসরাই'),\n"
                    + "(160, 3, 22, 'পটিয়া'),\n"
                    + "(161, 3, 22, 'সন্দ্বীপ'),\n"
                    + "(162, 3, 22, 'বাঁশখালী'),\n"
                    + "(163, 3, 22, 'বোয়ালখালী'),\n"
                    + "(164, 3, 22, 'আনোয়ারা'),\n"
                    + "(165, 3, 22, 'সাতকানিয়া'),\n"
                    + "(166, 3, 22, 'লোহাগাড়া'),\n"
                    + "(167, 3, 22, 'হাটহাজারী'),\n"
                    + "(168, 3, 22, 'ফটিকছড়ি'),\n"
                    + "(169, 3, 22, 'রাঊজান'),\n"
                    + "(170, 3, 22, 'চন্দনাইশ'),\n"
                    + "(171, 3, 23, 'দেবিদ্বার'),\n"
                    + "(172, 3, 23, 'বরুড়া'),\n"
                    + "(173, 3, 23, 'ব্রাহ্মণপাড়া'),\n"
                    + "(174, 3, 23, 'চান্দিনা'),\n"
                    + "(175, 3, 23, 'চৌদ্দগ্রাম'),\n"
                    + "(176, 3, 23, 'দাউদকান্দি'),\n"
                    + "(177, 3, 23, 'হোমনা'),\n"
                    + "(178, 3, 23, 'লাকসাম'),\n"
                    + "(179, 3, 23, 'মুরাদনগর'),\n"
                    + "(180, 3, 23, 'নাঙ্গলকোট'),\n"
                    + "(181, 3, 23, 'কুমিল্লা সদর'),\n"
                    + "(182, 3, 23, 'মেঘনা'),\n"
                    + "(183, 3, 23, 'মনোহরগঞ্জ'),\n"
                    + "(184, 3, 23, 'সদর দক্ষিণ'),\n"
                    + "(185, 3, 23, 'তিতাস'),\n"
                    + "(186, 3, 23, 'বুড়িচং'),\n"
                    + "(187, 3, 24, 'ছাগলনাইয়া'),\n"
                    + "(188, 3, 24, 'ফেনী সদর'),\n"
                    + "(189, 3, 24, 'সোনাগাজী'),\n"
                    + "(190, 3, 24, 'ফুলগাজী'),\n"
                    + "(191, 3, 24, 'পরশুরাম'),\n"
                    + "(192, 3, 24, 'দাগনভুঞা'),\n"
                    + "(193, 3, 25, 'ব্রাহ্মণবাড়িয়া সদর'),\n"
                    + "(194, 3, 25, 'কসবা'),\n"
                    + "(195, 3, 25, 'নাসিরনগর'),\n"
                    + "(196, 3, 25, 'সরাইল'),\n"
                    + "(197, 3, 25, 'আশুগঞ্জ'),\n"
                    + "(198, 3, 25, 'আখাউরা'),\n"
                    + "(199, 3, 25, 'নবীনগর'),\n"
                    + "(200, 3, 25, 'বাঞ্ছারামপুর'),\n"
                    + "(201, 3, 25, 'বিজয়নগর'),\n"
                    + "(202, 3, 26, 'রাঙ্গামাটি সদর'),\n"
                    + "(203, 3, 26, 'কাপ্তাই'),\n"
                    + "(204, 3, 26, 'কাউখালী'),\n"
                    + "(205, 3, 26, 'বাঘাইছড়ি'),\n"
                    + "(206, 3, 26, 'বরকল'),\n"
                    + "(207, 3, 26, 'লংগদু'),\n"
                    + "(208, 3, 26, 'রাজস্থলী'),\n"
                    + "(209, 3, 26, 'বিলাইছড়ি'),\n"
                    + "(210, 3, 26, 'জুরাছড়ি'),\n"
                    + "(211, 3, 26, 'নানিয়ারচর'),\n"
                    + "(212, 3, 27, 'হাইমচর'),\n"
                    + "(213, 3, 27, 'কচুয়া'),\n"
                    + "(214, 3, 27, 'শহরাস্তি'),\n"
                    + "(215, 3, 27, 'চাঁদপুর সদর'),\n"
                    + "(216, 3, 27, 'মতলব উত্তর'),\n"
                    + "(217, 3, 27, 'ফরিদ্গঞ্জ'),\n"
                    + "(218, 3, 27, 'মতলব দক্ষিণ'),\n"
                    + "(219, 3, 27, 'হাজীগঞ্জ'),\n"
                    + "(220, 3, 28, 'নোয়াখালী সদর'),\n"
                    + "(221, 3, 28, 'কোম্পানীগঞ্জ'),\n"
                    + "(222, 3, 28, 'বেগমগঞ্জ'),\n"
                    + "(223, 3, 28, 'হাতিয়া'),\n"
                    + "(224, 3, 28, 'সুবর্ণচর'),\n"
                    + "(225, 3, 28, 'কবিরহাট'),\n"
                    + "(226, 3, 28, 'সেনবাগ'),\n"
                    + "(227, 3, 28, 'চাটখিল'),\n"
                    + "(228, 3, 28, 'সোনাইমুড়ী'),\n"
                    + "(229, 3, 29, 'লক্ষ্মীপুর সদর'),\n"
                    + "(230, 3, 29, 'কমলনগর'),\n"
                    + "(231, 3, 29, 'রায়পুর'),\n"
                    + "(232, 3, 29, 'রামগতি'),\n"
                    + "(233, 3, 29, 'রামগঞ্জ'),\n"
                    + "(234, 3, 30, 'কক্সবাজার সদর'),\n"
                    + "(235, 3, 30, 'চকরিয়া'),\n"
                    + "(236, 3, 30, 'কুতুবদিয়া'),\n"
                    + "(237, 3, 30, 'উখিয়া'),\n"
                    + "(238, 3, 30, 'মহেশখালী'),\n"
                    + "(239, 3, 30, 'পেকুয়া'),\n"
                    + "(240, 3, 30, 'রামু'),\n"
                    + "(241, 3, 30, 'টেকনাফ'),\n"
                    + "(242, 3, 31, 'খাগড়াছড়ি সদর'),\n"
                    + "(243, 3, 31, 'দিঘীনালা'),\n"
                    + "(244, 3, 31, 'পানছড়ি'),\n"
                    + "(245, 3, 31, 'লক্ষীছড়ি'),\n"
                    + "(246, 3, 31, 'মহালছড়ি'),\n"
                    + "(247, 3, 31, 'মানিকছড়ি'),\n"
                    + "(248, 3, 31, 'রামগড়'),\n"
                    + "(249, 3, 31, 'মাটিরাঙ্গা'),\n"
                    + "(250, 3, 31, 'গুইমারা'),\n"
                    + "(251, 3, 32, 'বান্দরবান সদর'),\n"
                    + "(252, 3, 32, 'আলীকদম'),\n"
                    + "(253, 3, 32, 'নাইক্ষ্যংছড়ি'),\n"
                    + "(254, 3, 32, 'রোয়াংছড়ি'),\n"
                    + "(255, 3, 32, 'লামা'),\n"
                    + "(256, 3, 32, 'রুমা'),\n"
                    + "(257, 3, 32, 'থানচি'),\n"
                    + "(258, 4, 33, 'বালাগঞ্জ'),\n"
                    + "(259, 4, 33, 'বিয়ানীবাজার'),\n"
                    + "(260, 4, 33, 'বিশ্বনাথ'),\n"
                    + "(261, 4, 33, 'কোম্পানীগঞ্জ'),\n"
                    + "(262, 4, 33, 'ফেঞ্চুগঞ্জ'),\n"
                    + "(263, 4, 33, 'গোলাপগঞ্জ'),\n"
                    + "(264, 4, 33, 'গোয়াইনঘাট'),\n"
                    + "(265, 4, 33, 'জৈন্তাপুর'),\n"
                    + "(266, 4, 33, 'কানাইঘাট'),\n"
                    + "(267, 4, 33, 'সিলেট সদর'),\n"
                    + "(268, 4, 33, 'জকিগঞ্জ'),\n"
                    + "(269, 4, 33, 'দক্ষিণ সুরমা'),\n"
                    + "(270, 4, 33, 'ওসমানী নগর'),\n"
                    + "(271, 4, 34, 'বড়লেখা'),\n"
                    + "(272, 4, 34, 'কমলগঞ্জ'),\n"
                    + "(273, 4, 34, 'কুলাউরা'),\n"
                    + "(274, 4, 34, 'মৌলভীবাজার সদর '),\n"
                    + "(275, 4, 34, 'রাজনগর'),\n"
                    + "(276, 4, 34, 'শ্রীমঙ্গল'),\n"
                    + "(277, 4, 34, 'জুড়ী'),\n"
                    + "(278, 4, 35, 'নবীগঞ্জ'),\n"
                    + "(279, 4, 35, 'বাহুবল'),\n"
                    + "(280, 4, 35, 'আজমিরীগঞ্জ'),\n"
                    + "(281, 4, 35, 'বানিয়াচং'),\n"
                    + "(282, 4, 35, 'লাখাই'),\n"
                    + "(283, 4, 35, 'চুনারুঘাট'),\n"
                    + "(284, 4, 35, 'হবিগঞ্জ সদর'),\n"
                    + "(285, 4, 35, 'মাধবপুর'),\n"
                    + "(286, 4, 36, 'সুনামগঞ্জ সদর'),\n"
                    + "(287, 4, 36, 'দক্ষিণ সুনামগঞ্জ'),\n"
                    + "(288, 4, 36, 'বিশ্বম্ভরপুর'),\n"
                    + "(289, 4, 36, 'ছাতক'),\n"
                    + "(290, 4, 36, 'জগন্নাথপুর'),\n"
                    + "(291, 4, 36, 'তাহিরপুর'),\n"
                    + "(292, 4, 36, 'ধর্মপাশা'),\n"
                    + "(293, 4, 36, 'জামালগঞ্জ'),\n"
                    + "(294, 4, 36, 'শাল্লা'),\n"
                    + "(295, 4, 36, 'দিরাই'),\n"
                    + "(296, 4, 36, 'দোয়ারাবাজার'),\n"
                    + "(297, 5, 37, 'পাইকগাছা'),\n"
                    + "(298, 5, 37, 'ফুলতলা'),\n"
                    + "(299, 5, 37, 'দিঘলিয়া'),\n"
                    + "(300, 5, 37, 'রূপসা'),\n"
                    + "(301, 5, 37, 'তেরখাদা'),\n"
                    + "(302, 5, 37, 'ডুমুরিয়া'),\n"
                    + "(303, 5, 37, 'বটিয়াঘাটা'),\n"
                    + "(304, 5, 37, 'দাকোপ'),\n"
                    + "(305, 5, 37, 'কয়রা'),\n"
                    + "(306, 5, 38, 'মণিরামপুর'),\n"
                    + "(307, 5, 38, 'অভয়নগর'),\n"
                    + "(308, 5, 38, 'বাঘারপাড়া'),\n"
                    + "(309, 5, 38, 'চৌগাছা'),\n"
                    + "(310, 5, 38, 'ঝিকরগাছা'),\n"
                    + "(311, 5, 38, 'কেশবপুর'),\n"
                    + "(312, 5, 38, 'যশোর সদর'),\n"
                    + "(313, 5, 38, 'শার্শা'),\n"
                    + "(314, 5, 39, 'আশাশুনি'),\n"
                    + "(315, 5, 39, 'দেবহাটা'),\n"
                    + "(316, 5, 39, 'কলারোয়া'),\n"
                    + "(317, 5, 39, 'সাতক্ষীরা সদর'),\n"
                    + "(318, 5, 39, 'শ্যামনগর'),\n"
                    + "(319, 5, 39, 'তালা'),\n"
                    + "(320, 5, 39, 'কালিগঞ্জ'),\n"
                    + "(321, 5, 40, 'মুজিবনগর'),\n"
                    + "(322, 5, 40, 'মেহেরপুর সদর'),\n"
                    + "(323, 5, 40, 'গাংনী'),\n"
                    + "(324, 5, 41, 'নড়াইল সদর'),\n"
                    + "(325, 5, 41, 'লোহাগড়া'),\n"
                    + "(326, 5, 41, 'কালিয়া'),\n"
                    + "(327, 5, 42, 'চুয়াডাঙ্গা সদর'),\n"
                    + "(328, 5, 42, 'আলমডাঙ্গা'),\n"
                    + "(329, 5, 42, 'দামুড়হুদা'),\n"
                    + "(330, 5, 42, 'জীবননগর'),\n"
                    + "(331, 5, 43, 'শালিখা'),\n"
                    + "(332, 5, 43, 'শ্রীপুর'),\n"
                    + "(333, 5, 43, 'মাগুরা সদর'),\n"
                    + "(334, 5, 43, 'মহম্মদপুর'),\n"
                    + "(335, 5, 44, 'ফকিরহাট'),\n"
                    + "(336, 5, 44, 'বাগেরহাট সদর'),\n"
                    + "(337, 5, 44, 'মোল্লাহাট'),\n"
                    + "(338, 5, 44, 'শরণখোলা'),\n"
                    + "(339, 5, 44, 'রামপাল'),\n"
                    + "(340, 5, 44, 'মোড়েলগঞ্জ'),\n"
                    + "(341, 5, 44, 'কচুয়া'),\n"
                    + "(342, 5, 44, 'মোংলা'),\n"
                    + "(343, 5, 44, 'চিতলমারী'),\n"
                    + "(344, 5, 45, 'ঝিনাইদহ সদর'),\n"
                    + "(345, 5, 45, 'শৈলকুপা'),\n"
                    + "(346, 5, 45, 'হরিণাকুণ্ডু '),\n"
                    + "(347, 5, 45, 'কালীগঞ্জ'),\n"
                    + "(348, 5, 45, 'কোটচাঁদপুর'),\n"
                    + "(349, 5, 45, 'মহেশপুর'),\n"
                    + "(350, 5, 46, 'কুষ্টিয়া সদর'),\n"
                    + "(351, 5, 46, 'কুমারখালী'),\n"
                    + "(352, 5, 46, 'খোকসা'),\n"
                    + "(353, 5, 46, 'মিরপুর'),\n"
                    + "(354, 5, 46, 'দৌলতপুর'),\n"
                    + "(355, 5, 46, 'ভেড়ামারা'),\n"
                    + "(356, 6, 47, 'বরিশাল সদর'),\n"
                    + "(357, 6, 47, 'বাকেরগঞ্জ'),\n"
                    + "(358, 6, 47, 'বাবুগঞ্জ'),\n"
                    + "(359, 6, 47, 'উজিরপুর'),\n"
                    + "(360, 6, 47, 'বানারীপাড়া'),\n"
                    + "(361, 6, 47, 'গৌরনদী'),\n"
                    + "(362, 6, 47, 'আগৈলঝাড়া'),\n"
                    + "(363, 6, 47, 'মেহেন্দিগঞ্জ'),\n"
                    + "(364, 6, 47, 'মুলাদী'),\n"
                    + "(365, 6, 47, 'হিজলা'),\n"
                    + "(366, 6, 48, 'ঝালকাঠি সদর'),\n"
                    + "(367, 6, 48, 'কাঠালিয়া'),\n"
                    + "(368, 6, 48, 'নলছিটি'),\n"
                    + "(369, 6, 48, 'রাজাপুর'),\n"
                    + "(370, 6, 49, 'বাউফল'),\n"
                    + "(371, 6, 49, 'পটুয়াখালী সদর'),\n"
                    + "(372, 6, 49, 'দুমকি'),\n"
                    + "(373, 6, 49, 'দশমিনা'),\n"
                    + "(374, 6, 49, 'কলাপাড়া'),\n"
                    + "(375, 6, 49, 'মির্জাগঞ্জ'),\n"
                    + "(376, 6, 49, 'গলাচিপা'),\n"
                    + "(377, 6, 49, 'রাঙ্গাবালী'),\n"
                    + "(378, 6, 50, 'পিরোজপুর সদর'),\n"
                    + "(379, 6, 50, 'নাজিরপুর'),\n"
                    + "(380, 6, 50, 'কাউখালী'),\n"
                    + "(381, 6, 50, 'জিয়ানগর'),\n"
                    + "(382, 6, 50, 'ভান্ডারিয়া'),\n"
                    + "(383, 6, 50, 'মঠবাড়ীয়া'),\n"
                    + "(384, 6, 50, 'নেছারাবাদ'),\n"
                    + "(385, 6, 51, 'ভোলা সদর'),\n"
                    + "(386, 6, 51, 'বোরহানউদ্দিন'),\n"
                    + "(387, 6, 51, 'চরফ্যাশন'),\n"
                    + "(388, 6, 51, 'দৌলতখান'),\n"
                    + "(389, 6, 51, 'মনপুরা'),\n"
                    + "(390, 6, 51, 'তজুমদ্দিন'),\n"
                    + "(391, 6, 51, 'লালমোহন'),\n"
                    + "(392, 6, 52, 'আমতলী'),\n"
                    + "(393, 6, 52, 'বরগুনা সদর'),\n"
                    + "(394, 6, 52, 'বেতাগী'),\n"
                    + "(395, 6, 52, 'বামনা'),\n"
                    + "(396, 6, 52, 'পাথরঘাটা'),\n"
                    + "(397, 6, 52, 'তালতলি'),\n"
                    + "(398, 7, 53, 'রংপুর সদর'),\n"
                    + "(399, 7, 53, 'গঙ্গাচড়া'),\n"
                    + "(400, 7, 53, 'তারাগঞ্জ'),\n"
                    + "(401, 7, 53, 'বদরগঞ্জ'),\n"
                    + "(402, 7, 53, 'মিঠাপুকুর'),\n"
                    + "(403, 7, 53, 'কাউনিয়া'),\n"
                    + "(404, 7, 53, 'পীরগঞ্জ'),\n"
                    + "(405, 7, 53, 'পীরগাছা'),\n"
                    + "(406, 7, 54, 'লালমনিরহাট সদর'),\n"
                    + "(407, 7, 54, 'আদিতমারী'),\n"
                    + "(408, 7, 54, 'কালীগঞ্জ'),\n"
                    + "(409, 7, 54, 'হাতীবান্ধা'),\n"
                    + "(410, 7, 54, 'পাটগ্রাম'),\n"
                    + "(411, 7, 55, 'পঞ্চগড় সদর'),\n"
                    + "(412, 7, 55, 'দেবীগঞ্জ'),\n"
                    + "(413, 7, 55, 'বোদা'),\n"
                    + "(414, 7, 55, 'আটোয়ারী'),\n"
                    + "(415, 7, 55, 'তেতুলিয়া'),\n"
                    + "(416, 7, 56, 'কুড়িগ্রাম সদর'),\n"
                    + "(417, 7, 56, 'নাগেশ্বরী'),\n"
                    + "(418, 7, 56, 'ভুরুঙ্গামারী'),\n"
                    + "(419, 7, 56, 'ফুলবাড়ী'),\n"
                    + "(420, 7, 56, 'রাজারহাট'),\n"
                    + "(421, 7, 56, 'উলিপুর'),\n"
                    + "(422, 7, 56, 'চিলমারী'),\n"
                    + "(423, 7, 56, 'রৌমারী'),\n"
                    + "(424, 7, 56, 'চর রাজিবপুর'),\n"
                    + "(425, 7, 57, 'নবাবগঞ্জ'),\n"
                    + "(426, 7, 57, 'বীরগঞ্জ'),\n"
                    + "(427, 7, 57, 'ঘোড়াঘাট'),\n"
                    + "(428, 7, 57, 'বিরামপুর'),\n"
                    + "(429, 7, 57, 'পার্বতীপুর'),\n"
                    + "(430, 7, 57, 'বোচাগঞ্জ'),\n"
                    + "(431, 7, 57, 'কাহারোল'),\n"
                    + "(432, 7, 57, 'ফুলবাড়ী'),\n"
                    + "(433, 7, 57, 'দিনাজপুর সদর'),\n"
                    + "(434, 7, 57, 'হাকিমপুর'),\n"
                    + "(435, 7, 57, 'খানসামা'),\n"
                    + "(436, 7, 57, 'বিরল'),\n"
                    + "(437, 7, 57, 'চিরিরবন্দর'),\n"
                    + "(438, 7, 58, 'ঠাকুরগাঁও সদর'),\n"
                    + "(439, 7, 58, 'পীরগঞ্জ'),\n"
                    + "(440, 7, 58, 'রাণীশংকৈল'),\n"
                    + "(441, 7, 58, 'হরিপুর'),\n"
                    + "(442, 7, 58, 'বালিয়াডাঙ্গী'),\n"
                    + "(443, 7, 59, 'সাদুল্লাপুর'),\n"
                    + "(444, 7, 59, 'গাইবান্ধা সদর'),\n"
                    + "(445, 7, 59, 'পলাশবাড়ী'),\n"
                    + "(446, 7, 59, 'সাঘাটা'),\n"
                    + "(447, 7, 59, 'গোবিন্দগঞ্জ'),\n"
                    + "(448, 7, 59, 'সুন্দরগঞ্জ'),\n"
                    + "(449, 7, 59, 'ফুলছড়ি'),\n"
                    + "(450, 7, 60, 'সৈয়দপুর'),\n"
                    + "(451, 7, 60, 'ডোমার'),\n"
                    + "(452, 7, 60, 'ডিমলা'),\n"
                    + "(453, 7, 60, 'জলঢাকা'),\n"
                    + "(454, 7, 60, 'কিশোরগঞ্জ'),\n"
                    + "(455, 7, 60, 'নীলফামারী সদর'),\n"
                    + "(456, 8, 61, 'ফুলবাড়ীয়া '),\n"
                    + "(457, 8, 61, 'ত্রিশাল'),\n"
                    + "(458, 8, 61, 'ভালুকা'),\n"
                    + "(459, 8, 61, 'মুক্তাগাছা'),\n"
                    + "(460, 8, 61, 'ময়মনসিংহ সদর'),\n"
                    + "(461, 8, 61, 'ধোবাউরা'),\n"
                    + "(462, 8, 61, 'ফুলপুর'),\n"
                    + "(463, 8, 61, 'হালুয়াঘাট'),\n"
                    + "(464, 8, 61, 'গৌরীপুর'),\n"
                    + "(465, 8, 61, 'গফরগাঁও'),\n"
                    + "(466, 8, 61, 'ঈশ্বরগঞ্জ'),\n"
                    + "(467, 8, 61, 'নান্দাইল'),\n"
                    + "(468, 8, 61, 'তারাকান্দা'),\n"
                    + "(469, 8, 62, 'জামালপুর সদর'),\n"
                    + "(470, 8, 62, 'মেলান্দহ'),\n"
                    + "(471, 8, 62, 'ইসলামপুর'),\n"
                    + "(472, 8, 62, 'দেওয়ানগঞ্জ'),\n"
                    + "(473, 8, 62, 'সরিষাবাড়ী'),\n"
                    + "(474, 8, 62, 'মাদারগঞ্জ'),\n"
                    + "(475, 8, 62, 'বকশীগঞ্জ'),\n"
                    + "(476, 8, 63, 'বারহাট্টা'),\n"
                    + "(477, 8, 63, 'দুর্গাপুর'),\n"
                    + "(478, 8, 63, 'কেন্দুয়া'),\n"
                    + "(479, 8, 63, 'আটপাড়া'),\n"
                    + "(480, 8, 63, 'মদন'),\n"
                    + "(481, 8, 63, 'খালিয়াজুরী'),\n"
                    + "(482, 8, 63, 'কলমাকান্দা'),\n"
                    + "(483, 8, 63, 'মোহনগঞ্জ'),\n"
                    + "(484, 8, 63, 'পূর্বধলা'),\n"
                    + "(485, 8, 63, 'নেত্রকোনা সদর'),\n"
                    + "(486, 8, 64, 'শেরপুর সদর'),\n"
                    + "(487, 8, 64, 'নালিতাবাড়ী'),\n"
                    + "(488, 8, 64, 'শ্রীবরদী'),\n"
                    + "(489, 8, 64, 'নকলা'),\n"
                    + "(490, 8, 64, 'ঝিনাইগাতী'),\n"
                    + "(491, 1, 1, 'ঢাকা মহানগর')");
            System.out.println("Inseted upozillaTable table in given database...");
        } catch (SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
        }
    }

    ////Insert data into database ...........End....
}
