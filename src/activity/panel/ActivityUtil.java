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
public class ActivityUtil {
    public static String[] getCategories(){
        return new String[]{"description", "kcal", "date"};
    }
    public static Object[][] getData(){
        Object[][] data = new Object[3][3];
        for(int i = 0; i < 3; i++){
            for (int y = 0; y < 3; y++){
                data[i][y] = "i = " + i + "  y = " + y;
            }
        }
        return data;
    }
}
