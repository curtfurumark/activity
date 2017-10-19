/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package activity;

import activity.panel.TodoTablePanel;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author User
 */
public class ToDoTableListener implements TableModelListener{
    private final static boolean DEBUG = true;
    private ActivityApp activityTab;
    private TodoTablePanel todoTablePanel;

    public ToDoTableListener(ActivityApp activityTab, TodoTablePanel todoTablePanel) {
        this.activityTab = activityTab;
        this.todoTablePanel = todoTablePanel;
    }
    
    @Override
    public void tableChanged(TableModelEvent e) {
        if( DEBUG) System.out.println("ToDoTableListener.tableChanged()");
        if (DEBUG)System.out.println("\t" + e.getType());
        int row = e.getFirstRow();
        int column = e.getColumn();
        if( column >= 0){
            if (DEBUG) System.out.format("row %d, column: %d\n", row, column);
            DefaultTableModel defaultTableModel = (DefaultTableModel) e.getSource();
            Object data = defaultTableModel.getValueAt(row, column);
            if ( DEBUG) System.out.println("\tnew value: " + data.toString());
            Object id =  defaultTableModel.getValueAt(row, 0);
            Object description =  defaultTableModel.getValueAt(row, 1);
            Object is_done =  defaultTableModel.getValueAt(row, 2);;
            activityTab.getPersist().updateTodo(Long.parseLong(id.toString()), description.toString(), Boolean.parseBoolean(is_done.toString()));   
            todoTablePanel.updateTable();
        }
    }
    
}
