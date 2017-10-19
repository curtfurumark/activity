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
public class Rope {
    public static void swing(){
        System.out.println("swing");
    }
    public void climb(){
        System.out.println("climb");
    }
    public static void play(){
        swing();
        //climb();
    }
    public static void main(String[] args) {
        Rope rope = new Rope();
        rope.play();
        Rope rope2 = null;
        rope2.play();
    }
}
