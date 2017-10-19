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
public enum FactorChoice {
    ALL, VARIABLE, FIXED;

    static FactorChoice fromString(String str) {
        if (str.equalsIgnoreCase(VARIABLE.toString())){
            return VARIABLE;
        }
        else if ( str.equalsIgnoreCase( FIXED.toString())){
            return FIXED;
        }
        return ALL;
    }
}
