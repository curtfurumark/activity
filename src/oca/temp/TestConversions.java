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
public class TestConversions {
    public static void print(float f){
            System.out.println("float");
    }
    public static void print(int i){
            System.out.println("int");
    }
    public static void print(byte b){
            System.out.println("byte");
    }
    public static void print(Object o){
            System.out.println("object");
    }
    public static void main(String[] args) {
        short s = 123;
        print(s);
        print(3.456);
        print(true);
    }
}
