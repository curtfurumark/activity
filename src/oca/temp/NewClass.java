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

class OldClass{
    private OldClass(){
    
    }

}
public class NewClass {
    private NewClass(){
        System.out.println("NewClass ctor");
    }
    public static void main(String[] args) {
        NewClass nc = new NewClass();
        //OldClass oc = new OldClass();
    }
}
