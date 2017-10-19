/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package whatever.furumark_se;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author User
 */
//oheQBZYo
public class FurumarkMySQL {
    private static String userName = "curtfurumark_se";
    private static String password ="oheQBZYo";
    private static String dbms  = "mysql";
    private static String serverName ="curtfurumark.se.mysql";
    private static String portNumber = "3306";
    private static String dbName ="curtfurumark_se";
    private static Connection connection = null;
    public static String[] getCategories(){
        return new String[]{"enthuware", "bass", "running", "art", "programming", "whatever", "plockeri", "pro tools"};
    }
    public static boolean connect() throws SQLException {
        System.out.println("FurumarkMySQL.getConnection()");
        String dbUrl  = "jdbc:" + dbms + "://" + serverName + ":" + portNumber + "/" + dbName;
        Properties connectionProps = new Properties();
        connectionProps.put("user", userName);
        connectionProps.put("password", password);
        System.out.println("dbUrl: " + dbUrl);
        connection = DriverManager.getConnection(dbUrl, connectionProps);
        System.out.println("Connected to database ???");
        connection.close();
        return true;
    }
    
    public static void main(String[] args) {
        System.out.println("FurumarkMySQL");
        try {
            connect();
        } catch (SQLException ex) {
            System.out.println(ex.toString());
        }
    }
}
