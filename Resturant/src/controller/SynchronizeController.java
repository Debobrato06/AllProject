/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.jfoenix.controls.JFXProgressBar;
import static helper.config.BASE_URL;
import database.DBConn;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Array;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLEncoder;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.json.simple.*;
//import org.json.simple.JSONObject;
import org.json.simple.parser.*;
//import org.json.simple.parser.ParseException;
//import org.json.simple.JSONArray;
//import org.json.simple.JSONObject;
//
//import org.json.simple.parser.*;

/**
 * FXML Controller class
 *
 * @author Debobrato Biswas
 */
public class SynchronizeController implements Initializable {

    @FXML
    private JFXProgressBar client;
    @FXML
    private JFXProgressBar foodBill;
    @FXML
    private JFXProgressBar foodPayment;
    @FXML
    private JFXProgressBar food;

    private DBConn database = new DBConn();
    private Connection connection;
    private Statement statement;
    private ResultSet resultSet;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        System.out.println("SynchronizeController Start.");
        System.out.println("::::::::::::::::::::::::::::::");

        try {

            updateClients();

            updateFoodPayment();
            updateFoodBill();

            initialCategories();
            initialFoodProducts();
            initialFoodItemType();
            initialClients();

            initialFoodPayment();
            initialFoodBill();

        } catch (SQLException ex) {
            Logger.getLogger(SynchronizeController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(SynchronizeController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(SynchronizeController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    ////////////
    //////TIME AND DATE Start //////
    private static java.sql.Timestamp getCurrentTimeStampOnlyDate() {

        java.util.Date today = new java.util.Date();
        return new java.sql.Timestamp(today.getTime());

    }

    private static String getCurrentTimeStampAsUpdate() {

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyMMddHHmm");
        LocalDateTime now = LocalDateTime.now();
        return dtf.format(now);

    }

    private static String getCurrentTimeStamp() {

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm");
        LocalDateTime now = LocalDateTime.now();
        return dtf.format(now);

    }

    //////TIME AND DATE End //////
    //////////////
    public static void initialCategories() throws IOException, ParseException, SQLException {
        Connection connection = DBConn.getConnection();
        Statement statement = connection.createStatement();
        String truncateTable = "TRUNCATE TABLE  restaurent.tbl_category";
        statement.execute(truncateTable);

        URL urlForGetRequest = new URL(BASE_URL + "categories");

        String readLine = null;
        HttpURLConnection conection = (HttpURLConnection) urlForGetRequest.openConnection();

        conection.setRequestMethod("GET");
        conection.setRequestProperty("userId", "a1bcdef"); // set userId as get paramiter its a sample here
        int responseCode = conection.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_OK) {
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(conection.getInputStream()));
            StringBuffer response = new StringBuffer();
            System.out.println("DDDDDDDDDD :::: " + response.toString());
            while ((readLine = in.readLine()) != null) {
                response.append(readLine);
            }

            in.close();

            // parsing JSON FROM STRING
            // Object obj = new JSONParser().parse(response.toString());
            Object obj = new JSONParser().parse(response.toString());
            // typecasting obj to JSONObject 
            JSONObject jo = (JSONObject) obj;

            // getting categories 
            JSONArray ja = (JSONArray) jo.get("categories");

            // iterating categories 
            Iterator itr2 = ja.iterator();
            Iterator itr1;

            int id = 0;
            String name_bn = "";
            String name_en = "";

            //WHERE NOT EXISTS ( SELECT * FROM tbl_category WHERE id = ? AND name_bn = ? AND name_en = ? AND priority = ? AND insert_by = ?)
            String categories = "INSERT INTO restaurent.tbl_category  (id, name_bn, name_en, priority, insert_by) VALUES(?,?,?,?,?)";
            PreparedStatement pst = connection.prepareStatement(categories, Statement.RETURN_GENERATED_KEYS);

            while (itr2.hasNext()) {

                itr1 = ((Map) itr2.next()).entrySet().iterator();

                id = 0;
                name_bn = "";
                name_en = "";

                while (itr1.hasNext()) {
                    Map.Entry pair = (Map.Entry) itr1.next();
                    System.out.println(pair.getKey() + " : " + pair.getValue());

                    if (pair.getKey().toString().equals("id")) {
                        id = Integer.valueOf(pair.getValue().toString());
                    }
                    if (pair.getKey().toString().equals("name_en")) {
                        name_en = pair.getValue().toString();
                    }
                    if (pair.getKey().toString().equals("name_bn")) {
                        name_bn = pair.getValue().toString();
                    }

                }

                try {

                    pst.setInt(1, id);
                    pst.setString(2, name_bn);
                    pst.setString(3, name_en);
                    pst.setInt(4, 1);
                    pst.setInt(5, 1);
                    pst.executeUpdate();

                } catch (SQLException e) {
                    Logger.getLogger(InvoiceGenerateController.class.getName()).log(Level.SEVERE, null, e);
                    System.out.println(e);
                }
            }
            pst.close();

        } else {
            System.out.println("GET NOT WORKED");
        }
    }

    ///////////
//    public static void POSTRequest() throws IOException {
////        final String POST_PARAMS = "{\"message\":[{\"id\":\"3\",\"step_name\":\"Lead \\/ Inqueue\"},{\"id\":\"2\",\"step_name\":\"Telemarketing\\/sms\\/email\"},{\"id\":\"4\",\"step_name\":\"Visit\"},{\"id\":\"5\",\"step_name\":\"Final Lead\"},{\"id\":\"6\",\"step_name\":\"Proposal \\/ Prospect\"},{\"id\":\"7\",\"step_name\":\"Negotiate or Pipeline\"},{\"id\":\"8\",\"step_name\":\"Work Order\"},{\"id\":\"9\",\"step_name\":\"Done Case\"}]}";
////        System.out.println(POST_PARAMS);
//        URL obj = new URL("https://jsonplaceholder.typicode.com/posts");
//        HttpURLConnection postConnection = (HttpURLConnection) obj.openConnection();
//        postConnection.setRequestMethod("POST");
//        postConnection.setRequestProperty("userId", "a1bcdefgh");
//        postConnection.setRequestProperty("Content-Type", "application/json");
//        postConnection.setDoOutput(true);
//        OutputStream os = postConnection.getOutputStream();
////        os.write(POST_PARAMS.getBytes());
//        os.flush();
//        os.close();
//        int responseCode = postConnection.getResponseCode();
//        System.out.println("POST Response Code :  " + responseCode);
//        System.out.println("POST Response Message : " + postConnection.getResponseMessage());
//        if (responseCode == HttpURLConnection.HTTP_CREATED) { //success
//            BufferedReader in = new BufferedReader(new InputStreamReader(
//                    postConnection.getInputStream()));
//            String inputLine;
//            StringBuffer response = new StringBuffer();
//            while ((inputLine = in.readLine()) != null) {
//                response.append(inputLine);
//            }
//            in.close();
//            // print result
//            System.out.println(response.toString());
//        } else {
//            System.out.println("POST NOT WORKED");
//        }
//    }
    private void initialFoodProducts() throws IOException, ParseException, SQLException {
        Connection connection = DBConn.getConnection();
        Statement statement = connection.createStatement();
        String truncateTable = "TRUNCATE TABLE  restaurent.tbl_food_products";
        statement.execute(truncateTable);

        URL updateFoodProducts = new URL(BASE_URL + "get_product");
        // URL updateFoodProducts = new URL("http://res.hrsoftbd-software-company.name/api/get_product");

        String readLine = null;
        HttpURLConnection conection = (HttpURLConnection) updateFoodProducts.openConnection();

        conection.setRequestMethod("GET");
        conection.setRequestProperty("userId", "a1bcdef"); // set userId as get paramiter its a sample here
        int responseCode = conection.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_OK) {
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(conection.getInputStream()));
            StringBuffer response = new StringBuffer();
            while ((readLine = in.readLine()) != null) {
                response.append(readLine);
            }

            in.close();
            System.out.println("Product ::" + response);

            // parsing JSON FROM STRING
            Object obje = new JSONParser().parse(response.toString());

            // typecasting obj to JSONObject 
            JSONObject jo = (JSONObject) obje;

            // getting categories 
            JSONArray jar = (JSONArray) jo.get("products");

            System.out.println("ARRAY ::" + jar);
            // iterating categories 
            Iterator itr2 = jar.iterator();
            Iterator itr1;

            int id = 0;
            String name = "";
            String url_generate = "";
            String description = "";
            String photo = "";
            double price = 0.0;
            int item_type_id = 0;
            int category_id = 0;
            int delivary_policy_id = 0;
            int term_policy_id = 0;

//            Connection connection = DBConn.getConnection();
            //WHERE NOT EXISTS ( SELECT * FROM tbl_category WHERE id = ? AND name_bn = ? AND name_en = ? AND priority = ? AND insert_by = ?)
            String product = "INSERT INTO restaurent.tbl_food_products(id, name, url_generate, description, photo, price, item_type_id, category_id, delivary_policy_id, term_policy_id, insert_by, insert_time, last_update) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement pst = connection.prepareStatement(product, Statement.RETURN_GENERATED_KEYS);

            while (itr2.hasNext()) {

                itr1 = ((Map) itr2.next()).entrySet().iterator();

                id = 0;
                name = "";
                url_generate = "";
                description = "";
                photo = "";
                price = 0;
                item_type_id = 0;
                category_id = 0;
                delivary_policy_id = 0;
                term_policy_id = 0;

                while (itr1.hasNext()) {
                    Map.Entry pair = (Map.Entry) itr1.next();
                    System.out.println(pair.getKey() + " : " + pair.getValue());

                    if (pair.getKey().toString().equals("id")) {
                        id = Integer.valueOf(pair.getValue().toString());

                    }
                    if (pair.getKey().toString().equals("name")) {
                        name = pair.getValue().toString();

                    }
                    if (pair.getKey().toString().equals("url_generate")) {
                        url_generate = pair.getValue().toString();

                    }
                    if (pair.getKey().toString().equals("description")) {
                        description = pair.getValue().toString();
                    }
                    if (pair.getKey().toString().equals("photo")) {
                        photo = pair.getValue().toString();
                    }
                    if (pair.getKey().toString().equals("price")) {
                        price = Double.valueOf(pair.getValue().toString());
                    }

                    if (pair.getKey().toString().equals("item_type_id")) {
                        item_type_id = Integer.valueOf(pair.getValue().toString());
                    }
                    if (pair.getKey().toString().equals("category_id")) {
                        category_id = Integer.valueOf(pair.getValue().toString());
                    }
                    if (pair.getKey().toString().equals("delivary_policy_id")) {
                        delivary_policy_id = Integer.valueOf(pair.getValue().toString());
                    }
                    if (pair.getKey().toString().equals("term_policy_id")) {
                        term_policy_id = Integer.valueOf(pair.getValue().toString());
                    }

                }

                try {
//id, name, url_generate, description, photo, price, item_type_id,
//category_id, delivary_policy_id, term_policy_id, insert_by, insert_time, last_update
                    pst.setInt(1, id);
                    pst.setString(2, name);
                    pst.setString(3, url_generate);
                    pst.setString(4, description);
                    pst.setString(5, photo);
                    pst.setDouble(6, price);
                    pst.setInt(7, item_type_id);
                    pst.setInt(8, category_id);
                    pst.setInt(9, delivary_policy_id);
                    pst.setInt(10, term_policy_id);
                    pst.setString(11, "01");
                    pst.setString(12, getCurrentTimeStamp());
                    pst.setString(13, getCurrentTimeStamp());
                    pst.executeUpdate();

                } catch (SQLException e) {
                    Logger.getLogger(InvoiceGenerateController.class.getName()).log(Level.SEVERE, null, e);
                    System.out.println(e);
                }
            }
            pst.close();

        } else {
            System.out.println("GET NOT WORKED");
        }

    }

    private void initialFoodItemType() throws IOException, ParseException, SQLException {

        Connection connection = DBConn.getConnection();
        Statement statement = connection.createStatement();
        String truncateTable = "TRUNCATE TABLE  restaurent.tbl_food_item_type";
        statement.execute(truncateTable);

        URL updateFoodProducts = new URL(BASE_URL + "food_item_type");

        String readLine = null;
        HttpURLConnection conection = (HttpURLConnection) updateFoodProducts.openConnection();

        conection.setRequestMethod("GET");
        conection.setRequestProperty("userId", "a1bcdef"); // set userId as get paramiter its a sample here
        int responseCode = conection.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_OK) {
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(conection.getInputStream()));
            StringBuffer response = new StringBuffer();
            while ((readLine = in.readLine()) != null) {
                response.append(readLine);
            }

            in.close();
            System.out.println("Product ::" + response);

            // parsing JSON FROM STRING
            Object obje = new JSONParser().parse(response.toString());

            // typecasting obj to JSONObject 
            JSONObject jo = (JSONObject) obje;

            // getting categories 
            JSONArray jar = (JSONArray) jo.get("food_item_types");

            System.out.println("ARRAY ::" + jar);
            // iterating categories 
            Iterator itr2 = jar.iterator();
            Iterator itr1;
//id, name_bn, name_en, insert_by, insert_time
            int id = 0;
            String name_bn = "";
            String name_en = "";
            String insert_by = "";

            // Connection connection = DBConn.getConnection();
            //WHERE NOT EXISTS ( SELECT * FROM tbl_category WHERE id = ? AND name_bn = ? AND name_en = ? AND priority = ? AND insert_by = ?)
            String product = "INSERT INTO restaurent.tbl_food_item_type(id, name_bn, name_en, insert_by, insert_time) VALUES(?,?,?,?,?)";
            PreparedStatement pst = connection.prepareStatement(product, Statement.RETURN_GENERATED_KEYS);

            while (itr2.hasNext()) {

                itr1 = ((Map) itr2.next()).entrySet().iterator();

                id = 0;
                name_bn = "";
                name_en = "";
                insert_by = "";

                while (itr1.hasNext()) {
                    Map.Entry pair = (Map.Entry) itr1.next();
                    System.out.println(pair.getKey() + " : " + pair.getValue());

                    if (pair.getKey().toString().equals("id")) {
                        id = Integer.valueOf(pair.getValue().toString());

                    }
                    if (pair.getKey().toString().equals("name_bn")) {
                        name_bn = pair.getValue().toString();

                    }
                    if (pair.getKey().toString().equals("name_en")) {
                        name_en = pair.getValue().toString();

                    }
                    if (pair.getKey().toString().equals("insert_by")) {
                        insert_by = pair.getValue().toString();

                    }

                }

                try {

                    pst.setInt(1, id);
                    pst.setString(2, name_bn);
                    pst.setString(3, name_en);

                    pst.setString(4, "0");
                    pst.setString(5, getCurrentTimeStamp());
                    pst.executeUpdate();

                } catch (SQLException e) {
                    Logger.getLogger(InvoiceGenerateController.class.getName()).log(Level.SEVERE, null, e);
                    System.out.println(e);
                }
            }
            pst.close();

        } else {
            System.out.println("GET NOT WORKED");
        }

    }

    private void initialClients() throws IOException, ParseException, SQLException {

        Connection connection = DBConn.getConnection();
        Statement statement = connection.createStatement();
        String truncateTable = "TRUNCATE TABLE  restaurent.tbl_clients";
        statement.execute(truncateTable);

        URL updateFoodProducts = new URL(BASE_URL + "clients");

        String readLine = null;
        HttpURLConnection conection = (HttpURLConnection) updateFoodProducts.openConnection();

        conection.setRequestMethod("GET");
        conection.setRequestProperty("userId", "a1bcdef"); // set userId as get paramiter its a sample here
        int responseCode = conection.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_OK) {
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(conection.getInputStream()));
            StringBuffer response = new StringBuffer();
            while ((readLine = in.readLine()) != null) {
                response.append(readLine);
            }

            in.close();
            System.out.println("Product ::" + response);

            // parsing JSON FROM STRING
            Object obje = new JSONParser().parse(response.toString());

            // typecasting obj to JSONObject 
            JSONObject jo = (JSONObject) obje;

            // getting categories 
            JSONArray jar = (JSONArray) jo.get("clients");

            System.out.println("ARRAY ::" + jar);
            // iterating categories 
            Iterator itr2 = jar.iterator();
            Iterator itr1;
//id, name_bn, name_en, insert_by, insert_time
            int id = 0;
            String name = "";
            String phone = "";
            String email = "";

            //Connection connection = DBConn.getConnection();
            //WHERE NOT EXISTS ( SELECT * FROM tbl_category WHERE id = ? AND name_bn = ? AND name_en = ? AND priority = ? AND insert_by = ?)
            String product = "INSERT INTO restaurent.tbl_clients( id, name, phone, email, is_online) VALUES(?,?,?,?,?)";
            PreparedStatement pst = connection.prepareStatement(product, Statement.RETURN_GENERATED_KEYS);

            while (itr2.hasNext()) {

                itr1 = ((Map) itr2.next()).entrySet().iterator();

                id = 0;
                name = "";
                phone = "";
                email = "";

                while (itr1.hasNext()) {
                    Map.Entry pair = (Map.Entry) itr1.next();
                    System.out.println(pair.getKey() + " : " + pair.getValue());

                    if (pair.getKey().toString().equals("id")) {
                        id = Integer.valueOf(pair.getValue().toString());

                    }
                    if (pair.getKey().toString().equals("name")) {
                        name = pair.getValue().toString();

                    }
                    if (pair.getKey().toString().equals("phone")) {
                        phone = pair.getValue().toString();

                    }
                    if (pair.getKey().toString().equals("email")) {
                        email = pair.getValue().toString();

                    }

                }

                try {

                    pst.setInt(1, id);
                    pst.setString(2, name);
                    pst.setString(3, phone);
                    pst.setString(4, email);
                    pst.setString(5, "1");
                    pst.executeUpdate();

                } catch (SQLException e) {
                    Logger.getLogger(InvoiceGenerateController.class.getName()).log(Level.SEVERE, null, e);
                    System.out.println(e);
                }
            }
            pst.close();

        } else {
            System.out.println("GET NOT WORKED");
        }

    }

    private void initialFoodPayment() throws IOException, ParseException, SQLException {
        Connection connection = DBConn.getConnection();
        Statement statement = connection.createStatement();
        String truncateTable = "TRUNCATE TABLE  restaurent.tbl_food_payment";
        statement.execute(truncateTable);

        URL updateFoodProducts = new URL(BASE_URL + "get_food_payment");

        String readLine = null;
        HttpURLConnection conection = (HttpURLConnection) updateFoodProducts.openConnection();

        conection.setRequestMethod("GET");
        conection.setRequestProperty("userId", "a1bcdef"); // set userId as get paramiter its a sample here
        int responseCode = conection.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_OK) {
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(conection.getInputStream()));
            StringBuffer response = new StringBuffer();
            while ((readLine = in.readLine()) != null) {
                response.append(readLine);
            }

            in.close();
            System.out.println("Food Payment List ::" + response);

            // parsing JSON FROM STRING
            Object obje = new JSONParser().parse(response.toString());

            // typecasting obj to JSONObject 
            JSONObject jo = (JSONObject) obje;

            // getting categories 
            JSONArray jar = (JSONArray) jo.get("food_payment_list");

            System.out.println("ARRAY ::" + jar);
            // iterating categories 
            Iterator itr2 = jar.iterator();
            Iterator itr1;
//id, name_bn, name_en, insert_by, insert_time
            int id = 0;
            int client_id = 0;
            String invoice = "";
            String amount = "";
            String discount = "";
            String vat = "";
            String net_amount = "";
            String paid_amount = "";
            String return_amount = "";
            String payment_date = "";
            int user_id = 0;
            int method_id = 0;
            String insert_time = "";
            String insert_by = "";
            String order_approve = "";
            String approved_time = "";
            String delivery_time = "";
            String is_online = "";

            //Connection connection = DBConn.getConnection();
            //WHERE NOT EXISTS ( SELECT * FROM tbl_category WHERE id = ? AND name_bn = ? AND name_en = ? AND priority = ? AND insert_by = ?)
            String product = "INSERT INTO restaurent.tbl_food_payment( id, client_id, invoice, amount, discount, vat, net_amount, paid_amount, return_amount, payment_date, user_id, method_id, insert_time, insert_by, order_approve, approved_time, delivery_time, is_online) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement pst = connection.prepareStatement(product, Statement.RETURN_GENERATED_KEYS);

            while (itr2.hasNext()) {

                itr1 = ((Map) itr2.next()).entrySet().iterator();

                id = 0;
                client_id = 0;
                invoice = "";
                amount = "";
                discount = "";
                vat = "";
                net_amount = "";
                paid_amount = "";
                return_amount = "";
                payment_date = "";
                user_id = 0;
                method_id = 0;
                insert_time = "";
                insert_by = "";
                order_approve = "";
                approved_time = "";
                delivery_time = "";
                is_online = "";

                while (itr1.hasNext()) {
                    Map.Entry pair = (Map.Entry) itr1.next();
                    System.out.println(pair.getKey() + " : " + pair.getValue());

                    if (pair.getKey().toString().equals("id")) {
                        id = Integer.valueOf(pair.getValue().toString());
                    }
                    if (pair.getKey().toString().equals("client_id")) {
                        client_id = Integer.valueOf(pair.getValue().toString());
                    }
                    if (pair.getKey().toString().equals("invoice")) {
                        invoice = pair.getValue().toString();
                    }
                    if (pair.getKey().toString().equals("amount")) {
                        amount = pair.getValue().toString();
                    }
                    if (pair.getKey().toString().equals("discount")) {
                        discount = pair.getValue().toString();
                    }
                    if (pair.getKey().toString().equals("vat")) {
                        vat = pair.getValue().toString();
                    }
                    if (pair.getKey().toString().equals("net_amount")) {
                        net_amount = pair.getValue().toString();
                    }
                    if (pair.getKey().toString().equals("paid_amount")) {
                        paid_amount = pair.getValue().toString();
                    }
                    if (pair.getKey().toString().equals("return_amount")) {
                        return_amount = pair.getValue().toString();
                    }
                    if (pair.getKey().toString().equals("payment_date")) {
                        payment_date = pair.getValue().toString();
                    }
                    if (pair.getKey().toString().equals("user_id")) {
                        user_id = Integer.valueOf(pair.getValue().toString());

                    }
                    if (pair.getKey().toString().equals("method_id")) {
                        method_id = Integer.valueOf(pair.getValue().toString());
                    }
//insert_time, insert_by, order_approve, approved_time, delivery_time, is_online                    
                    if (pair.getKey().toString().equals("insert_time")) {
                        insert_time = pair.getValue().toString();
                    }
                    if (pair.getKey().toString().equals("insert_by")) {
                        insert_by = pair.getValue().toString();
                    }
                    if (pair.getKey().toString().equals("order_approve")) {
                        order_approve = pair.getValue().toString();
                    }
                    if (pair.getKey().toString().equals("approved_time")) {
                        approved_time = pair.getValue().toString();
                    }
                    if (pair.getKey().toString().equals("delivery_time")) {
                        delivery_time = pair.getValue().toString();
                    }
                    if (pair.getKey().toString().equals("is_online")) {
                        is_online = pair.getValue().toString();
                    }

                }

                try {

                    pst.setInt(1, id);
                    pst.setInt(2, client_id);
                    pst.setString(3, invoice);
                    pst.setString(4, amount);
                    pst.setString(5, discount);
                    pst.setString(6, vat);
                    pst.setString(7, net_amount);
                    pst.setString(8, paid_amount);
                    pst.setString(9, return_amount);
                    pst.setString(10, payment_date);
                    pst.setInt(11, user_id);
                    pst.setInt(12, method_id);
                    pst.setString(13, insert_time);
                    pst.setString(14, insert_by);
                    pst.setString(15, order_approve);
                    pst.setString(16, approved_time);
                    pst.setString(17, delivery_time);
                    pst.setString(18, is_online);
                    pst.executeUpdate();

                } catch (SQLException e) {
                    Logger.getLogger(InvoiceGenerateController.class.getName()).log(Level.SEVERE, null, e);
                    System.out.println(e);
                }
            }
            pst.close();

        } else {
            System.out.println("GET NOT WORKED");
        }

    }

    private void initialFoodBill() throws SQLException, IOException, ParseException {
        Connection connection = DBConn.getConnection();
        Statement statement = connection.createStatement();
        String truncateTable = "TRUNCATE TABLE  restaurent.tbl_food_bill";
        statement.execute(truncateTable);

        URL updateFoodProducts = new URL(BASE_URL + "get_food_bill");

        String readLine = null;
        HttpURLConnection conection = (HttpURLConnection) updateFoodProducts.openConnection();

        conection.setRequestMethod("GET");
        conection.setRequestProperty("userId", "a1bcdef"); // set userId as get paramiter its a sample here
        int responseCode = conection.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_OK) {
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(conection.getInputStream()));
            StringBuffer response = new StringBuffer();
            while ((readLine = in.readLine()) != null) {
                response.append(readLine);
            }

            in.close();
            System.out.println("Food Bill List ::" + response);

            // parsing JSON FROM STRING
            Object obje = new JSONParser().parse(response.toString());

            // typecasting obj to JSONObject 
            JSONObject jo = (JSONObject) obje;

            // getting categories 
            JSONArray jar = (JSONArray) jo.get("food_bill_list");

            System.out.println("ARRAY ::" + jar);
            // iterating categories 
            Iterator itr2 = jar.iterator();
            Iterator itr1;

            int id = 0;
            int food_product_id = 0;
            String quantity = "";
            String invoice = "";
            int client_id = 0;
            String bill_date = "";
            String insert_time = "";
            int user_id = 0;
            String insert_by = "";
            String is_online = "";

            //Connection connection = DBConn.getConnection();
            //WHERE NOT EXISTS ( SELECT * FROM tbl_category WHERE id = ? AND name_bn = ? AND name_en = ? AND priority = ? AND insert_by = ?)
            String product = "INSERT INTO restaurent.tbl_food_bill( food_product_id, quantity, invoice, client_id, bill_date, insert_time, user_id, insert_by ) VALUES(?,?,?,?,?,?,?,?)";
            PreparedStatement pst = connection.prepareStatement(product, Statement.RETURN_GENERATED_KEYS);

            while (itr2.hasNext()) {

                itr1 = ((Map) itr2.next()).entrySet().iterator();

                id = 0;
                food_product_id = 0;
                quantity = "";
                invoice = "";
                client_id = 0;
                bill_date = "";
                insert_time = "";
                user_id = 0;
                insert_by = "";
                is_online = "";

                while (itr1.hasNext()) {
                    Map.Entry pair = (Map.Entry) itr1.next();
                    System.out.println(pair.getKey() + " : " + pair.getValue());

                    if (pair.getKey().toString().equals("id")) {
                        id = Integer.valueOf(pair.getValue().toString());
                    }
//id, food_product_id, quantity, invoice, client_id, bill_date, 
                    if (pair.getKey().toString().equals("food_product_id")) {
                        food_product_id = Integer.valueOf(pair.getValue().toString());
                    }
                    if (pair.getKey().toString().equals("quantity")) {
                        quantity = pair.getValue().toString();
                    }
                    if (pair.getKey().toString().equals("invoice")) {
                        invoice = pair.getValue().toString();
                    }
                    if (pair.getKey().toString().equals("client_id")) {
                        client_id = Integer.valueOf(pair.getValue().toString());

                    }
                    if (pair.getKey().toString().equals("bill_date")) {
                        bill_date = pair.getValue().toString();
                    }
                    //insert_time, user_id, insert_by, is_online
                    if (pair.getKey().toString().equals("insert_time")) {
                        insert_time = pair.getValue().toString();
                    }
                    if (pair.getKey().toString().equals("user_id")) {
                        user_id = Integer.valueOf(pair.getValue().toString());
                    }
                    if (pair.getKey().toString().equals("insert_by")) {
                        insert_by = pair.getValue().toString();
                    }
                    if (pair.getKey().toString().equals("is_online")) {
                        is_online = pair.getValue().toString();
                    }

                }

                try {
//id, food_product_id, quantity, invoice, client_id, bill_date, //insert_time, user_id, insert_by, is_online
                    // pst.setInt(1, id);
                    pst.setInt(1, food_product_id);
                    pst.setString(2, quantity);
                    pst.setString(3, invoice);
                    pst.setInt(4, client_id);
                    pst.setString(5, bill_date);
                    pst.setString(6, insert_time);
                    pst.setInt(7, user_id);
                    pst.setString(8, insert_by);
                    //  pst.setString(9, is_online);
                    pst.executeUpdate();

                } catch (SQLException e) {
                    Logger.getLogger(InvoiceGenerateController.class.getName()).log(Level.SEVERE, null, e);
                    System.out.println(e);
                }
            }
            pst.close();

        } else {
            System.out.println("GET NOT WORKED");
        }

    }

    private void updateClients() throws SQLException, IOException {

        int isOnlineValue = 0;
        String isOnlineSql = "SELECT * FROM tbl_clients WHERE is_online IN (" + isOnlineValue + ");  ";
        try {
            connection = database.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(isOnlineSql);
            while (resultSet.next()) {
//id, name, phone, email, is_online
                String clientName = resultSet.getString("name");
                String phoneNumber = resultSet.getString("phone");
                String clientEmail = resultSet.getString("email");
                String isOnline = resultSet.getString("is_online");
//                System.out.println("Client name:::" + clientName);
//                System.out.println("Phone Number:::" + phoneNumber);
//                System.out.println("Is Online:::" + isOnline);
                System.out.println("::::::::::::::::::::::::::::::");

                /////////////////////Post Server start ////////////        
                System.out.println(":::::::::::::::::::::POST REQUEST START::::::::");
                HttpClient httpclient = HttpClients.createDefault();
                String url = BASE_URL + "clients_update";
                HttpPost httppost = new HttpPost(url);

// Request parameters and other properties.
                List<NameValuePair> params = new ArrayList<NameValuePair>(3);
                params.add(new BasicNameValuePair("name", clientName));
                params.add(new BasicNameValuePair("phone", phoneNumber));
                params.add(new BasicNameValuePair("email", clientEmail));
                httppost.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));
                System.out.println("MSG ::" + params);

//Execute and get the response.
                HttpResponse response = httpclient.execute(httppost);
                HttpEntity entity = response.getEntity();
                System.out.println("RESPONSE ::" + response);
                System.out.println("HTTP ENTITY ::" + entity);

                if (entity != null) {
                    try (InputStream instream = entity.getContent()) {
                        // do something useful

                    }
                }

                System.out.println(":::::::::::::::::::::POST REQUEST END::::::::");

                /////////////////////Post Server end ////////////        
            }

        } catch (SQLException ex) {
            Logger.getLogger(InvoiceGenerateController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void updateFoodPayment() throws IOException {

        int isOnlineValue = 0;
        String isOnlineSql = "SELECT * FROM tbl_food_payment WHERE is_online IN (" + isOnlineValue + ");  ";
        try {
            connection = database.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(isOnlineSql);
            while (resultSet.next()) {

                int Id = resultSet.getInt("id");
                int client_id = resultSet.getInt("client_id");
                String invoice = resultSet.getString("invoice");
                String amount = resultSet.getString("amount");
                String discount = resultSet.getString("discount");
                String vat = resultSet.getString("vat");
                String net_amount = resultSet.getString("net_amount");
                String paid_amount = resultSet.getString("paid_amount");
                String return_amount = resultSet.getString("return_amount");
                String payment_date = resultSet.getString("payment_date");
                int user_id = resultSet.getInt("user_id");
                int method_id = resultSet.getInt("method_id");
                String insert_time = resultSet.getString("insert_time");
                String insert_by = resultSet.getString("insert_by");
                String order_approve = resultSet.getString("order_approve");
                String approved_time = resultSet.getString("approved_time");
                String delivery_time = resultSet.getString("delivery_time");
                String isOnline = resultSet.getString("is_online");

                System.out.println("Invoice Value:::" + invoice);
                System.out.println("Is Online:::" + isOnline);
                System.out.println("::::::::::::::::::::::::::::::");

                /////////////////////Post Server start ////////////        
                System.out.println(":::::::::::::::::::::POST REQUEST START::::::::");
                HttpClient httpclient = HttpClients.createDefault();
                String url = BASE_URL + "update_food_payment";
                HttpPost httppost = new HttpPost(url);

// Request parameters and other properties.
                List<NameValuePair> params = new ArrayList<NameValuePair>(3);
                // params.add(new BasicNameValuePair("id", String.valueOf(Id)));
                params.add(new BasicNameValuePair("client_id", String.valueOf(client_id)));
                params.add(new BasicNameValuePair("invoice", invoice));
                params.add(new BasicNameValuePair("amount", amount));
                params.add(new BasicNameValuePair("discount", discount));
                params.add(new BasicNameValuePair("vat", vat));
                params.add(new BasicNameValuePair("net_amount", net_amount));
                params.add(new BasicNameValuePair("paid_amount", paid_amount));
                params.add(new BasicNameValuePair("return_amount", return_amount));
                params.add(new BasicNameValuePair("payment_date", payment_date));
                params.add(new BasicNameValuePair("user_id", String.valueOf(user_id)));
                // params.add(new BasicNameValuePair("method_id", String.valueOf(method_id)));
                params.add(new BasicNameValuePair("insert_time", insert_time));
                params.add(new BasicNameValuePair("insert_by", insert_by));
                params.add(new BasicNameValuePair("order_approve", order_approve));
                //params.add(new BasicNameValuePair("approved_time", approved_time));
                // params.add(new BasicNameValuePair("delivery_time", delivery_time));
                // params.add(new BasicNameValuePair("is_online", isOnline));
                httppost.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));
                System.out.println("MSG ::" + params);

//Execute and get the response.
                HttpResponse response = httpclient.execute(httppost);
                HttpEntity entity = response.getEntity();
                System.out.println("RESPONSE ::" + response);
                System.out.println("HTTP ENTITY ::" + entity);

                if (entity != null) {
                    try (InputStream instream = entity.getContent()) {
                        // do something useful

                    }
                }

                System.out.println(":::::::::::::::::::::POST REQUEST END::::::::");
                /////////////////////Post Server end ////////////        
            }

        } catch (SQLException ex) {
            Logger.getLogger(InvoiceGenerateController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void updateFoodBill() throws IOException {

        int isOnlineValue = 0;
        String isOnlineSql = "SELECT * FROM tbl_food_bill WHERE is_online IN (" + isOnlineValue + ");  ";
        try {
            connection = database.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(isOnlineSql);
            while (resultSet.next()) {
//id, food_product_id, quantity, invoice, client_id, bill_date, insert_time, user_id, insert_by, is_online
                int Id = resultSet.getInt("id");
                int foodProductId = resultSet.getInt("food_product_id");
                String quantity = resultSet.getString("quantity");
                String invoice = resultSet.getString("invoice");
                int client_id = resultSet.getInt("client_id");
                String bill_date = resultSet.getString("bill_date");
                String insert_time = resultSet.getString("insert_time");
                int user_id = resultSet.getInt("user_id");
                String insert_by = resultSet.getString("insert_by");
                String isOnline = resultSet.getString("is_online");

                System.out.println("Food Product Id:::" + foodProductId);
                System.out.println("Is Online:::" + isOnline);
                System.out.println("::::::::::::::::::::::::::::::");

                /////////////////////Post Server start ////////////        
                System.out.println(":::::::::::::::::::::POST REQUEST START::::::::");
                HttpClient httpclient = HttpClients.createDefault();
                String url = BASE_URL + "update_food_bill";
                HttpPost httppost = new HttpPost(url);

// Request parameters and other properties.
//id, food_product_id, quantity, invoice, client_id, bill_date, insert_time, user_id, insert_by, is_online
                List<NameValuePair> params = new ArrayList<NameValuePair>(3);
                // params.add(new BasicNameValuePair("id", String.valueOf(Id)));
                params.add(new BasicNameValuePair("food_product_id", String.valueOf(foodProductId)));
                params.add(new BasicNameValuePair("quantity", quantity));
                params.add(new BasicNameValuePair("invoice", invoice));
                params.add(new BasicNameValuePair("client_id", String.valueOf(client_id)));
                params.add(new BasicNameValuePair("bill_date", bill_date));
                params.add(new BasicNameValuePair("insert_time", insert_time));
                params.add(new BasicNameValuePair("user_id", String.valueOf(user_id)));
                params.add(new BasicNameValuePair("insert_by", insert_by));
                //params.add(new BasicNameValuePair("is_online", isOnline));
                httppost.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));
                System.out.println("MSG ::" + params);

//Execute and get the response.
                HttpResponse response = httpclient.execute(httppost);
                HttpEntity entity = response.getEntity();
                System.out.println("RESPONSE ::" + response);
                System.out.println("HTTP ENTITY ::" + entity);

                if (entity != null) {
                    try (InputStream instream = entity.getContent()) {
                        // do something useful

                    }
                }

                System.out.println(":::::::::::::::::::::POST REQUEST END::::::::");
                /////////////////////Post Server end ////////////        
            }

        } catch (SQLException ex) {
            Logger.getLogger(InvoiceGenerateController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public static void postRequest() throws IOException {
//        final String POST_PARAMS = "{\"message\":[{\"id\":\"3\",\"step_name\":\"Lead \\/ Inqueue\"},{\"id\":\"2\",\"step_name\":\"Telemarketing\\/sms\\/email\"},{\"id\":\"4\",\"step_name\":\"Visit\"},{\"id\":\"5\",\"step_name\":\"Final Lead\"},{\"id\":\"6\",\"step_name\":\"Proposal \\/ Prospect\"},{\"id\":\"7\",\"step_name\":\"Negotiate or Pipeline\"},{\"id\":\"8\",\"step_name\":\"Work Order\"},{\"id\":\"9\",\"step_name\":\"Done Case\"}]}";
//        System.out.println(POST_PARAMS);
//        URL obj = new URL("http://res.hrsoftbd-software-company.name/api/clients_update");
//        HttpURLConnection postConnection = (HttpURLConnection) obj.openConnection();
//        System.out.println("POST postConnection :  " + postConnection);
//        postConnection.setRequestMethod("POST");
//        postConnection.setRequestProperty("name", "name");
//        postConnection.setRequestProperty("phone", "01778451499");
//        postConnection.setRequestProperty("email", "email");
//        postConnection.setRequestProperty("Content-Type", "application/json");
//        postConnection.setDoOutput(true);
//        OutputStream os = postConnection.getOutputStream();
//
//        os.write(POST_PARAMS.getBytes());
//        os.flush();
//        os.close();
//        int responseCode = postConnection.getResponseCode();
//        System.out.println("POST Response Code :  " + responseCode);
//        System.out.println("POST Response Message : " + postConnection.getResponseMessage());
//        if (responseCode == HttpURLConnection.HTTP_CREATED) { //success
//            BufferedReader in = new BufferedReader(new InputStreamReader(
//                    postConnection.getInputStream()));
//            String inputLine;
//            StringBuffer response = new StringBuffer();
//            while ((inputLine = in.readLine()) != null) {
//                response.append(inputLine);
//            }
//            in.close();
//            // print result
//            System.out.println("POST SUCCESSFULL::" + response.toString());
//        } else {
//            System.out.println("POST NOT WORKED.");
//        }
//        System.out.println(":::::::::::::::::::::::::::::");
    }

}
