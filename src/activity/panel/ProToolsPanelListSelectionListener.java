/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package activity.panel;

import activity.ActivityApp;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author curt
 */
public class ProToolsPanelListSelectionListener implements ListSelectionListener{
    private ActivityApp activityApp;
    private ProToolsPanel proToolsPanel;

    public ProToolsPanelListSelectionListener(ActivityApp activityApp, ProToolsPanel proToolsPanel) {
        this.activityApp = activityApp;
        this.proToolsPanel = proToolsPanel;
    }
    

    @Override
    public void valueChanged(ListSelectionEvent e) {
        System.out.println("FoodPanelListSelectionListener.valueChanged()");
        //ListSelectionModel listSelectionModel  = (ListSelectionModel) e.getSource();
       proToolsPanel.selectionChangeCB();
    }
}
