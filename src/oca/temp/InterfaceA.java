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
public interface InterfaceA {
    public abstract void method();
    public static void staticMethod(){
    }
    public default void defaultMethod(){
    
    }
}
interface AAA{

}
interface InterfaceB {

}
interface InterfaceC extends InterfaceB, InterfaceA{

}
abstract class Kandinsky{
    public abstract void paint();
    //public default void draw(){
    //}

}

class Main implements InterfaceC{
    public static void main(String[] args) {
        System.out.println("mian");
    }

    @Override
    public void method() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
