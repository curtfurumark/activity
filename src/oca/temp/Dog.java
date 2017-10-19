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
public class Dog {
    public String name;
    public void parseName(){
        System.out.println("1");
        try{
            System.out.println("2");
            //int x = Integer.parseInt(name);
            name.toString();
            System.out.println("3");
        //}catch(NumberFormatException e){
        }catch(NullPointerException e){
            System.out.println("4");
            throw e;
        }
        System.out.println("5");
    }
    public static Exception getException(){
        return new Exception("im a exception not thrown...");
    }
    public static void errorMethod() throws Error{
        throw new Error();
    }
    
    public static void main(String[] args) {
        Dog leroy = new Dog();
        //leroy.name = "leroy";
        //leroy.parseName();
        //System.out.println("6");
        //Exception exception = getException();
        //System.out.println(exception);
        errorMethod();
    }
}
