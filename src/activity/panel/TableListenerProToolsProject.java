/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package activity.panel;

import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;

/**
 *
 * @author curt
 */
public class TableListenerProToolsProject implements TableModelListener {

    @Override
    public void tableChanged(TableModelEvent e) {
        System.out.println("TableListenerProToolsProject.tableChanged()");
    }
    
}
