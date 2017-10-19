/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oca.temp;

import java.io.IOException;

/**
 *
 * @author User
 */
public class OHNO {
    public void ohNo() throws IOException{
        char c  = 0;
        switch (c){
            case 'A' :System.out.println("its okay");
            case 'B': //throw new Exception();   
            case 'C': throw new IllegalArgumentException();
            case 'D': throw new java.io.IOException();
            case 'E': throw new RuntimeException();
        }   
    }
}
