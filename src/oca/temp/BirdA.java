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
public class BirdA {
    
}
class BirdB{
    //public bird(){}
}
class BirdC{
    //public bird (String name){}
}
class BirdD{
    public BirdD(){}
}
class BirdE{
    BirdE(String name){}
}
class BirdF{
    private BirdF(int age){}
}
class BirdG{
    void BirdG(){}
}

class BirdieMain{
    public static void main(String[] args) {
        System.out.println("birdie main");
        BirdA ba = new BirdA();
        BirdB bb = new BirdB();
        BirdC bc = new BirdC();
        BirdD bd = new BirdD();
        //BirdE be = new BirdE();
        //BirdF bf = new BirdF();
        BirdG bg = new BirdG();
    }
}