/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package activity.mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author curt
 */
public class MySQL{
    private static String userName = "root";
    private static String password ="nalle12h";
    private static String dbms  = "mysql";
    private static String serverName ="localhost";
    private static String portNumber = "3306";
    private static String dbName ="cfdb";
    private static Connection connection = null;
    public static String[] getCategories(){
        return new String[]{"enthuware", "bass", "running", "art", "programming", "whatever", "plockeri", "pro tools"};
    }
    public static String sayHello(){
        return "hello";
    }
    public static boolean connect() throws SQLException {
        System.out.println("getConnection()");
        String dbUrl  = "jdbc:" + dbms + "://" + serverName + ":" + portNumber + "/" + dbName;    
        Properties connectionProps = new Properties();
        connectionProps.put("user", userName);
        connectionProps.put("password", password);
        System.out.println("dbUrl: " + dbUrl);
        connection = DriverManager.getConnection(dbUrl, connectionProps);
        System.out.println("Connected to database ???");
        return true;
    }
    public static String dateTimeToString(LocalDateTime localDateTime ){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return localDateTime.format(formatter);
    }
    
    public static void insert (String category, LocalDateTime start, long secs, String comment){
        try {
            System.out.println("MySQL.insert(String, LocalDateTime, long)");
            String str = dateTimeToString(start);
            String query = String.format("insert into activity(activity, start, seconds, comment) values(\"%s\",\"%s\", %d,\"%s\");", category, str, secs, comment);
            System.out.println(query);
            if ( connect()){
                execute(query);
                close();
            }       
        } catch (SQLException ex) {
            System.out.println("exception " + ex.toString());
        } 
    }   

    private static int execute(String query) {
        int stat = 0;
        try {
            Statement statement =  connection.createStatement();
            stat = statement.executeUpdate(query);
            System.out.println("stat: " + stat);
            //tement.
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("goodbye");
        return stat;
    }
    public static List<String> getNotes(){
        String query = "select * from jpanote;";
        List<String> notes = new ArrayList<>();
        try {
            if( connect()){
                Statement statement =  connection.createStatement();
                ResultSet rs = statement.executeQuery(query);
                while(rs.next()){
                    String temp = rs.getString("timestamp");
                    //System.out.println("temp:" + temp);
                    temp = temp.concat(": " + rs.getString("note"));
                   notes.add(temp);
                }    
            }
        } catch (SQLException ex) {
            System.out.println(ex.toString());
        }
        return notes;
    }

    private static void close() {
        try {
            connection.close();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }
}