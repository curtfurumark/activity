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

public interface CanFly {
    void fly();
}
interface HasWings{
    public abstract Object getWindSpan();
}

abstract class Falcon implements CanFly, HasWings{

}

class Canary extends Falcon{

    @Override
    public void fly() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Object getWindSpan() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    public static void main(String[] args) {
        System.out.println("main");
        Canary canary = new Canary();
    }

}