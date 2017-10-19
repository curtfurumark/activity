/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package activity.panel;

import activity.ActivityApp;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author curt
 */
public class FoodPanelTableListener implements TableModelListener {
    private ActivityApp activityApp;
    private FoodPanel   foodPanel;

    public FoodPanelTableListener(ActivityApp activityApp, FoodPanel foodPanel) {
        this.activityApp = activityApp;
        this.foodPanel = foodPanel;
    }
    
    
    @Override
    public void tableChanged(TableModelEvent e) {
        System.out.println("FoodPanelTableListener.tableChanged");
        int row = e.getFirstRow();
        int column = e.getColumn();
        if( column >= 0){
            System.out.format("row %d, column: %d\n", row, column);
            DefaultTableModel defaultTableModel = (DefaultTableModel) e.getSource();
            Object data = defaultTableModel.getValueAt(row, column);      
            Object id =  defaultTableModel.getValueAt(row, 0);
            System.out.println("id: = " + id);
            Object date = defaultTableModel.getValueAt(row, 2);
            System.out.println("date: " + date.toString());
           
        }
    }
}
