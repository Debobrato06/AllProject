/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Debobrato Biswas
 */
public class initiatlDB {

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

            String sql = "CREATE DATABASE IF NOT EXISTS dictionary ";
            stmt.executeUpdate(sql);
            System.out.println("Database created successfully...");
        } catch (SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
        }

    }

    ///////// Create Table Start \\\\\\\\\\
    public void translateTable() throws ClassNotFoundException {

        Connection conn = null;
        Statement stmt = null;
        try {
            //STEP 2: Register JDBC driver
            Class.forName("com.mysql.jdbc.Driver");

            //STEP 3: Open a connection
            System.out.println("Connecting to a selected database...");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/dictionary?useUnicode=yes&characterEncoding=UTF-8", "root", "root");
            System.out.println("Connected database successfully...");

            //STEP 4: Execute a query
            System.out.println("Creating translateTable table in given database...");
            stmt = conn.createStatement();
//english, bangla, noun, verb, synonym, antonym
            String translateTable = "CREATE TABLE IF NOT EXISTS `tbl_translate` (\n"
                    + "  `id` int(11) NOT NULL PRIMARY KEY AUTO_INCREMENT,\n"
                    + "  `english` text COLLATE utf8_bin NOT NULL,\n"
                    + "  `bangla` text COLLATE utf8_bin NOT NULL,\n"
                    + "  `noun` text COLLATE utf8_bin NOT NULL,\n"
                    + "  `verb` text COLLATE utf8_bin NOT NULL,\n"
                    + "  `synonym` text COLLATE utf8_bin NOT NULL,\n"
                    + "  `antonym` text COLLATE utf8_bin NOT NULL\n"
                    + ") ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin";

            stmt.executeUpdate(translateTable);
            System.out.println("Created translateTable table in given database...");
        } catch (SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
        }
    }

    ///////// Create Table End \\\\\\\\\\  
    ///////// Create Table Start \\\\\\\\\\
    public void translateEtoBTable() throws ClassNotFoundException {

        Connection conn = null;
        Statement stmt = null;
        try {
            //STEP 2: Register JDBC driver
            Class.forName("com.mysql.jdbc.Driver");

            //STEP 3: Open a connection
            System.out.println("Connecting to a selected database...");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/dictionary?useUnicode=yes&characterEncoding=UTF-8", "root", "root");
            System.out.println("Connected database successfully...");

            //STEP 4: Execute a query
            System.out.println("Creating translateEtoBTable table in given database...");
            stmt = conn.createStatement();
//english, bangla, noun, verb, synonym, antonym
            String translateEtoBTable = "CREATE TABLE IF NOT EXISTS `tbl_translateEtoBTable` (\n"
                    + "  `id` int(11) NOT NULL PRIMARY KEY AUTO_INCREMENT,\n"
                    + "  `english` varchar(40) COLLATE utf8_bin NOT NULL,\n"
                    + "  `bangla` varchar(40) COLLATE utf8_bin NOT NULL,\n"
                    + "  `noun` varchar(40) COLLATE utf8_bin NOT NULL,\n"
                    + "  `verb` varchar(40) COLLATE utf8_bin NOT NULL,\n"
                    + "  `synonym` varchar(40) COLLATE utf8_bin NOT NULL,\n"
                    + "  `antonym` varchar(40) COLLATE utf8_bin NOT NULL\n"
                    + ") ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin";

            stmt.executeUpdate(translateEtoBTable);
            System.out.println("Created translateEtoBTable table in given database...");
        } catch (SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
        }
    }

    ///////// Create Table End \\\\\\\\\\
    
    ///////// Create Table Start \\\\\\\\\\
    public void appropriatePrepositionTable() throws ClassNotFoundException {

        Connection conn = null;
        Statement stmt = null;
        try {
            //STEP 2: Register JDBC driver
            Class.forName("com.mysql.jdbc.Driver");

            //STEP 3: Open a connection
            System.out.println("Connecting to a selected database...");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/dictionary?useUnicode=yes&characterEncoding=UTF-8", "root", "root");
            System.out.println("Connected database successfully...");

            //STEP 4: Execute a query
            System.out.println("Creating appropriatePreposition table in given database...");
            stmt = conn.createStatement();
//english, bangla, noun, verb, synonym, antonym
            String translateEtoBTable = "CREATE TABLE IF NOT EXISTS `tbl_appropriatePreposition` (\n"
                    + "  `id` int(11) NOT NULL PRIMARY KEY AUTO_INCREMENT,\n"
                    + "  `english` varchar(40) COLLATE utf8_bin NOT NULL,\n"
                    + "  `bangla` varchar(40) COLLATE utf8_bin NOT NULL\n"
                    + ") ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin";

            stmt.executeUpdate(translateEtoBTable);
            System.out.println("Created appropriatePreposition table in given database...");
        } catch (SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
        }
    }

    ///////// Create Table End \\\\\\\\\\
}
