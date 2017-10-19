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
interface Nocturnal {
    default boolean isBlind(){
        return true;
    }

}
public class Owl implements Nocturnal {
    public boolean isBlind(){ return false;}
    public static void main(String[] args){
        Nocturnal nocturnal = (Nocturnal) new Owl();
        System.out.println(nocturnal.isBlind());
    }
}
