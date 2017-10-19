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
public class Discount {
    long order;
   
    public long placeOrder(long amount){
        if ( amount > 25000 && amount < 75000){
            amount *= 0.975;
        }
        if ( amount > 75000 && amount < 100000){
            amount *= 0.95;
        }
        if ( amount > 11000){
            amount *= 0.9;
        }
        return amount;
    }
    public void runTests(){
        System.out.println(placeOrder(100000));
        System.out.println(placeOrder(10000));
        System.out.println(placeOrder(25000));
    }
    
    public static void main(String[] args) {
        new Discount().runTests();
    }
}
