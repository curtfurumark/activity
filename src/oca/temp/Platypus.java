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
class Mammal2{
    public Mammal2(){}
    public Mammal2(int age){
        System.out.println("Mammal");
    }
}

public class Platypus extends Mammal2 {
    public Platypus(){
        super();
        System.out.println("Platypus");
    }
    public static void main(String[] args) {
        new Mammal2(5);
    }
}
