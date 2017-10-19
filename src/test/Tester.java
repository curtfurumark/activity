/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import activity.mysql.MySQL;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import jpa.JPAWeight;

/**
 *
 * @author curt
 */
public class Tester {
    public void testWeight(){
        JPAWeight weight = new JPAWeight();
    
    }
    public static void main(String[] args) {
        System.out.println("Tester main");
        try {
            MySQL.connect();
        } catch (SQLException ex) {
            Logger.getLogger(Tester.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
   
}
