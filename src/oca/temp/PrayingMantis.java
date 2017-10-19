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
abstract class Insect{
    public Insect(int age){System.out.println("!");}
    public Insect(String color){this(5);System.out.println("2");}
    
}
public class PrayingMantis extends Insect{
    public PrayingMantis(String color){
        super(color);
        System.out.println("3");}
}
