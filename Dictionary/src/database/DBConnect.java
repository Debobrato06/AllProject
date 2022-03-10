/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;



/**
 *
 * @author devbi
 */
public class DBConnect {
    
    
     public static Connection getConnection() throws SQLException{
        
        Connection connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/dictionary?useUnicode=yes&characterEncoding=UTF-8","root","root");
        
        return connection;
        
    }
    
//    Connection conn = null;
//    public static Connection ConnecrDb()
//    {
//        try{
//             Class.forName("org.sqlite.JDBC");
//            Connection conn=DriverManager.getConnection("jdbc:sqlite:C:\\Users\\Debobrato Biswas\\Documents\\NetBeansProjects\\Dictionary09.01.2020\\Dictionary\\dictionary.db");
//            return conn;
//        }catch(Exception e)
//        {
//            JOptionPane.showMessageDialog(null,e);
//           return null;
//        }
//        
//    }
//    
    
    
   /*
    private static final String JDBC_DRIVER = "org.apache.derby.jdbc.ClientDriver";

    private static Connection conn = null;

    private static final String connStr = "jdbc:derby://localhost:1527/BarCode";

    public static void dbConnect() throws SQLException, ClassNotFoundException {
       
        try {
            Class.forName(JDBC_DRIVER);
        } catch (ClassNotFoundException e) {
            System.out.println("Where is your Oracle JDBC Driver?");
            e.printStackTrace();
            throw e;
        }
 
        System.out.println("Oracle JDBC Driver Registered!");
 
      
        try {
            conn = DriverManager.getConnection(connStr,"root","root");
        } catch (SQLException e) {
            System.out.println("Connection Failed! Check output console" + e);
            e.printStackTrace();
            throw e;
        }
    }  
    
       public static void dbDisconnect() throws SQLException {
        try {
            if (conn != null && !conn.isClosed()) {
                conn.close();
            }
        } catch (Exception e){
           throw e;
        }
    }
 */

   
    
}
