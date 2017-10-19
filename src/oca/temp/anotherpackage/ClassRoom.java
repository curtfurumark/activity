/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oca.temp.anotherpackage;

/**
 *
 * @author User curt furumark , 20160427
 * oca chapter 4, question 7
 */
public class ClassRoom {
    private int roomNumber;
    protected String teacherName;
    static int globalKey = 54321;
    public int floor = 3;
    ClassRoom (int r,String t){
        roomNumber = r;
        teacherName = t;
    }
}
