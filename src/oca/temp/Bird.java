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
class Sparrow extends Bird{}
public abstract class Bird {
    public static void main(String[] args) {
        Bird b = new Sparrow();
        Sparrow s = new Sparrow();
        if (b == s){
            System.out.println("==");
        }
        if ( b.equals(s)){
            System.out.println("bird");
            Sparrow s1 = new Sparrow();
        }
        //if ( b.equals(s1)){
        //    System.out.println("sparrow");
        //}
    }
}
