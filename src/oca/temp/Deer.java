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
public class Deer {
    static int count;
    static {count = 0;}
    public Deer(){ 
        count++;
        System.out.println("Deer");}
    public Deer(int age){System.out.println("DeerAge");}
    private boolean hasHorns(){return false;}
    public static void main(String[] args) {
        Deer mother = new Deer();
        Deer father = new Deer();
        Deer fawn = new Deer();
        System.out.println(father.count);
        //Deer deer = new ReinDeer(5);
        //will print true
        //System.out.println(deer.hasHorns());
    }
}
class ReinDeer extends Deer{
    public ReinDeer(int age){System.out.println("Reindeer");}
    public boolean hasHorns(){ return true;}

}