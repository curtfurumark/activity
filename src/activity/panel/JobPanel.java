/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package activity.panel;

import activity.ActivityApp;
import java.awt.Container;
import java.awt.GridLayout;
import java.util.Date;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author User
 */
public class JobPanel extends JPanel {

    private ActivityApp activityApp;
    private JTextField textFieldDescription = new JTextField();
    private JButton buttonOpenFile = new JButton();
    private JButton buttonAddApplication = new JButton("add application");
    private JTable tableApplications = new JTable();
    private JTable tableNotes = new JTable();
    private JScrollPane scrollPaneApplications = new JScrollPane(tableApplications);
    private JScrollPane scrollPaneNotes = new JScrollPane(tableNotes);
    private DefaultTableModel defaultTableModelApplications;
    private DefaultTableModel defaultTableModelNotes;
    private Container containerApplications = new Container();
    private Container containerNotes = new Container();
    private JFileChooser fileChooser = new JFileChooser();
    private FileNameExtensionFilter filter = new FileNameExtensionFilter(
            "pdd files", "pdf");
    private JTextField textFieldHeading = new JTextField(55);
    private static boolean DEBUG = true;

    @Override
    public String toString() {
        return "JobPanel{" + '}';
    }

    private void init() {
        if (DEBUG)System.out.println("JobPanel.init()");
        if( DEBUG) System.out.println(this.toString());
        setLayout(null);
        this.defaultTableModelApplications = new DefaultTableModel(activityApp.getPersist().getApplications(), new String[]{"id", "heading"});
        this.defaultTableModelNotes = new DefaultTableModel(activityApp.getPersist().getApplicationNotes(1L), new String[]{"id", "note"});
        //defaultTableModelApplications.;
        tableApplications.setModel(defaultTableModelApplications);
        tableApplications.getSelectionModel().addListSelectionListener(new JobPanelListSelectionListener(activityApp, this));
        tableNotes.setModel(defaultTableModelNotes);
        textFieldHeading.setBounds(20, 20, 300, 30);
        textFieldDescription.setBounds(20, 70, 300, 30);
        this.buttonAddApplication.setBounds(20, 120, 300, 30);
        this.buttonAddApplication.addActionListener(ae -> addApplication());
        //this.tableApplications.setBounds(20, 170, 200, 30);
        containerApplications.setLayout(new GridLayout(1, 1));
        this.containerApplications.setBounds(20, 170, 200, 200);
        containerNotes.setLayout(new GridLayout(1, 1));
        containerNotes.setBounds(250, 170, 200, 200);
        add(textFieldHeading);
        add(textFieldDescription);
        add(buttonAddApplication);
        containerApplications.add(this.scrollPaneApplications);
        containerNotes.add(this.scrollPaneNotes);
        ;
        add(containerApplications);
        add(containerNotes);

    }

    public JobPanel(ActivityApp activityApp) {
        this.activityApp = activityApp;
        init();
    }

    private void addApplication() {
        System.out.println("addApplication");
        String heading = this.textFieldHeading.getText();
        String description = this.textFieldDescription.getText();
        Date lastApplicationDate = new java.util.Date();
        java.sql.Date sqlDate = new java.sql.Date(lastApplicationDate.getTime());
        this.activityApp.getPersist().addApplication(heading, description, lastApplicationDate);
    }

    public void selectionChangeCB() {
        System.out.println("FoodPanel.selectionChangeCB()");
        int selectedRow = tableApplications.getSelectedRow();
        System.out.println("\tselected row: " + selectedRow);
        if (selectedRow > -1) {
            Long id = (Long) defaultTableModelApplications.getValueAt(selectedRow, 0);
            System.out.println("id: " + id);
            updateTableNotes(id);
            //this.datePicker.getModel().setDate(selectedDate.getYear(), selectedDate.getMonth(), selectedDate.getDate());
        }
    }

    private void updateTableNotes(Long id) {
        System.out.println("JobPanel.updateTableNotes()" + id);
                if (DEBUG) {
            System.out.println("FoodPanel.updateTable()");
        }
        Object[][] data = activityApp.getPersist().getApplicationNotes(id);
        defaultTableModelNotes.setRowCount(0);
        if (DEBUG) {
            System.out.println("\tnumber of rows: " + data.length);
        }
        for (int i = 0; i < data.length; i++) {
            //if (DEBUG) Debug.print(data[i]);
            defaultTableModelNotes.addRow(data[i]);
        }
    }
}
