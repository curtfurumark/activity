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
public class JobPanelListSelectionListener implements ListSelectionListener{
    private ActivityApp activityApp;
    private JobPanel jobPanel;
    private static boolean DEBUG = true;

    public JobPanelListSelectionListener(ActivityApp activityApp, JobPanel jobPanel) {
        if (DEBUG) System.out.println("JobPanelListSelectionListener()");
        if ( DEBUG) System.out.println("\tJobPanel " + jobPanel);
        if ( DEBUG) System.out.println("\tActivityApp " + activityApp);
        this.activityApp = activityApp;
        this.jobPanel = jobPanel;
    }
    

    @Override
    public void valueChanged(ListSelectionEvent e) {
        System.out.println("JobPanelListSelectionListener.valueChanged()");
        if (jobPanel == null) System.out.println("jobpanel is null");
        jobPanel.selectionChangeCB();
        //ListSelectionModel listSelectionModel  = (ListSelectionModel) e.getSource();
       //foodPanel.selectionChangeCB();
    }
}
