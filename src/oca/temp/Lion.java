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

class HasSoreThroatException extends Exception{

}
class TiredException extends RuntimeException{
}
interface Roar{
    void roar() throws HasSoreThroatException;
}
public class Lion implements Roar{
    //public void roar(){}//ok
    @Override
    public void roar() throws HasSoreThroatException{}
}
