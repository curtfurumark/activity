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
public class Succer {
        Succer s = new Succer();
        public void thrower() throws Exception{
            throw new Exception();
        }
        public void whatever(){}
        public void method(){
            try{
                //thrower();
                whatever();
            }finally{
                System.out.println("finally");
            }
        }
        
    public static void main(String[] args) {
        //new Succer();
        StringBuilder s1 = new StringBuilder("wow");
        StringBuilder s2 = new StringBuilder("wow");
        if ( s1 == s2)System.out.println("==");
        if ( s1.equals(s2))System.out.println("equals");
        //if( s1 == "wow")System.out.println("==");
        if( s1.toString() == "wow")System.out.println("==");
        System.out.println("hello");
                
    }
}
