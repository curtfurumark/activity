/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oca.temp;

/**
 *
 * @author User
 */
public class Cardinal {
    static int number;
    public Cardinal(){number++;}
    public static void main(String[] args) {
        String s = new String("hello");
        String b = new String(s);
        if ( s == b){System.out.println("==");}
        Cardinal c1 = new Cardinal();
        if ( c1 == null){
            Cardinal c2 = new Cardinal();
        }else{
            Cardinal c2 = new Cardinal();
        }
        Cardinal c2 = new Cardinal();
        System.out.println(c1.number);
    }
    
}
