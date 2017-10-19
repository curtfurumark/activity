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
public class HowMany {
    public static int count(int a ){
        int b = 0;
        if (a != 3){ 
            b = 1;
        }
        else{
            b = 2;
        }
        return a++ + b;
    }
    public static void main(String[] args) {
        System.out.println(count(3));
        System.out.println(count(9));
    }
}
