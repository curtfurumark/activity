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
public class ProToolsPanelTableListener implements TableModelListener {
    private ActivityApp activityApp;
    private ProToolsPanel   proToolsPanel;

    public ProToolsPanelTableListener(ActivityApp activityApp, ProToolsPanel foodPanel) {
        this.activityApp = activityApp;
        this.proToolsPanel = foodPanel;
    }
    
    
    @Override
    public void tableChanged(TableModelEvent e) {
        System.out.println("ProToolsPanelTableListener.tableChanged");
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
