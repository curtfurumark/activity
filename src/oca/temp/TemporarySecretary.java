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
public class TemporarySecretary {
    public static void main(String[] args) {
        int[] i = new int[]{1, 3, 2};
        Object o = i;
        String[] two = (String[])o;
        System.out.println(two[two.length]);
    }
}
