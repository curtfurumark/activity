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
class Animal{
    public int getAge(){return 10;}
}
class Mammal extends Animal{
    public int getAge(int i){return 7;}
}
public class Sloth extends Mammal {
    public boolean hasFur(){return true;}
    public static void main(String[] args) {
        Mammal sloth = new Sloth();
        
    }
}
