/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tools;

import activity.mysql.MySQL;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author User
 */
public class Tool {
    public static void getNotes(){
        List<String> notes = MySQL.getNotes();
        for(String note: notes){
            System.out.println("a note: " + note);
        }
    }
    public static void main(String[] args) {
        getNotes();
    }
}
