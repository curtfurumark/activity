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
public class C01Q02 {
    private String soul;
    { System.out.println(soul.length());}
    {soul += " soul";};
    public static void main(String[] args) {
        C01Q02 c = new C01Q02();
        System.out.println("soul = " + c.soul);
    } 
}
