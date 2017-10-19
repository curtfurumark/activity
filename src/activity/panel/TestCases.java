/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package activity.panel;

/**
 *
 * @author curt
 */
public class TestCases {

    public TestCases() {
        System.out.println("test cases");
        System.out.println(FactorChoice.ALL);
        if ( "all".equalsIgnoreCase(FactorChoice.ALL.toString())){
            System.out.println("is all");
        }
    }
    public static void main(String[] args){
        TestCases tc = new TestCases();
        //tc.
    }
    
    
}
