/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package activity.panel;

import activity.ActivityApp;
import activity.ToDoTableListener;
import java.awt.Color;
import java.awt.Container;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import jpa.ProToolsNote;
import jpa.ProToolsProject;
import org.jdatepicker.impl.JDatePickerImpl;
import util.CFDatePickerFactory;

/**
 *
 * @author User
 */
public class ProToolsPanel extends JPanel {

    private static final boolean DEBUG = true;
    private final JTextField textFieldProToolsId = new JTextField(55);
    private final JTextField textFieldProToolsName = new JTextField(55);
    private final JLabel labelTitle = new JLabel("title:");
    private final JLabel labelProToolsID = new JLabel("pt id:");
    private final JTextField textFieldProToolsNote = new JTextField();

    private final JTable tableProjects = new JTable();
    private final JTable tableNotes = new JTable();
    private final JScrollPane scrollPane4CurrentDayTable = new JScrollPane(tableNotes);
    private final JScrollPane scrollPanelTable = new JScrollPane(tableProjects);
    private final ActivityApp activityApp;
    private final JButton buttonAddProToolsProject = new JButton("add pro tools project");
    private final JButton buttonAddNote = new JButton("add pro tools note");
    private final Container tableContainer = new Container();
    private final Container containerTableProToolsNote = new Container();

    private DefaultTableModel defaultTableModelProToolsProjects; // = new DefaultTableModel(ActivityUtil.getData(), ActivityUtil.getCategories());
    private DefaultTableModel defaultTableModelNotes;
    private  JDatePickerImpl datePicker = null;

    public ProToolsPanel(ActivityApp activityApp) {
        Properties properties = new Properties();
        properties.put("text.today", "today");
        properties.put("text.month", "month");
        properties.put("text.year", "year");
        this.activityApp = activityApp;
        init();
    }

    public void init() {
        setLayout(null);
        datePicker = CFDatePickerFactory.createDatePicker();
        defaultTableModelProToolsProjects = new DefaultTableModel(activityApp.getPersist().getProToolsProjects(), new String[]{"jpa id", "name", "project id"});
        tableProjects.setModel(defaultTableModelProToolsProjects);
        Date date = new java.util.Date(Calendar.getInstance().getTime().getTime());
        defaultTableModelNotes = new DefaultTableModel(activityApp.getPersist().getFoodItems4Date(date), new String[]{"pro tools id", "date", "id ", "note"});
        tableNotes.setModel(defaultTableModelNotes);
        defaultTableModelProToolsProjects.addTableModelListener(new ProToolsPanelTableListener(activityApp, this));
        tableProjects.getSelectionModel().addListSelectionListener(new ProToolsPanelListSelectionListener(activityApp, this));
        labelTitle.setBounds(20, 70, 100, 30);
        textFieldProToolsId.setBounds(60, 20, 60, 30);
        labelProToolsID.setBounds(20, 20, 100, 30);
        textFieldProToolsName.setBounds(60, 70, 300, 30);
        buttonAddProToolsProject.setBounds(160, 20, 200, 30);

        textFieldProToolsNote.setBounds(380, 20, 360, 30);
        buttonAddNote.setBounds(380, 70, 160, 30);
        datePicker.setBounds(600, 70, 160, 30);

        tableContainer.setBounds(20, 120, 340, 500);
        tableContainer.setBackground(Color.red);
        tableContainer.setLayout(new GridLayout(1, 1));
        tableContainer.add(scrollPanelTable);

        containerTableProToolsNote.setBounds(380, 120, 400, 300);
        containerTableProToolsNote.setLayout(new GridLayout(1, 1));

        //tableCurrentDayContainer.setBounds(300, 250, 200,300);
        containerTableProToolsNote.setBackground(Color.red);
        containerTableProToolsNote.add(scrollPane4CurrentDayTable);
        buttonAddProToolsProject.addActionListener((e) -> addProToolsProject());
        buttonAddNote.addActionListener(ae -> addNote());
        //textFieldSum.setText(String.valueOf(activityApp.getPersist().getKcalSum()));
        add(labelTitle);
        add(textFieldProToolsId);
        add(labelProToolsID);
        add(textFieldProToolsName);
        add(buttonAddProToolsProject);
        add(buttonAddNote);
        add(datePicker);
        add(textFieldProToolsNote);
        add(tableContainer);
        add(containerTableProToolsNote);
    }

    public void selectionChangeCB() {
        System.out.println("FoodPanel.selectionChangeCB()");
        int selectedRow = tableProjects.getSelectedRow();
        System.out.println("\tselected row: " + selectedRow);
        if (selectedRow > -1) {
            Long id = (Long) defaultTableModelProToolsProjects.getValueAt(selectedRow, 0);
            System.out.println("\tid = " + id);
            updateTableNotes(id);
            //this.datePicker.getModel().setDate(selectedDate.getYear() + 1900,selectedDate.getMonth(), selectedDate.getDate());
        }
    }

    private Long getSelectedProToolsProjectID() {
        int selectedRow = tableProjects.getSelectedRow();
        Long id = null;
        if (selectedRow > -1) {
            id = (Long) defaultTableModelProToolsProjects.getValueAt(selectedRow, 0);
            System.out.println("\tid = " + id);
            //updateTableNotes(id);
            //this.datePicker.getModel().setDate(selectedDate.getYear() + 1900,selectedDate.getMonth(), selectedDate.getDate());
        }
        return id;
    }

    private void updateTable() {
        if (DEBUG) {
            System.out.println("ProToolsPanel.updateTable()");
        }
        Object[][] data = activityApp.getPersist().getProToolsProjects();
        defaultTableModelProToolsProjects.setRowCount(0);
        if (DEBUG) {
            System.out.println("\tnumber of rows: " + data.length);
        }
        for (int i = 0; i < data.length; i++) {
            //if (DEBUG) System.out.println(data[i]);
            defaultTableModelProToolsProjects.addRow(data[i]);
        }
    }

    private void updateTableNotes(ProToolsProject proToolsProject) {
        if (DEBUG) {
            System.out.println("ProToolsPanel.updateTableNotes()");
        }
        //Object[][] data = activityApp.getPersist().getProToolsNotes(id);
        this.defaultTableModelNotes.setRowCount(0);
        for (int i = 0; i < proToolsProject.getNotes().size(); i++) {
            //defaultTableModelNotes.addRow(data[i]);
            defaultTableModelNotes.addRow(proToolsProject.getRow(i));
        }
    }

    private void updateTableNotes(long id) {
        if (DEBUG) {
            System.out.println("ProToolsPanel.updateTableNotes() " + id);
        }
        Object[][] data = activityApp.getPersist().getProToolsNotes(id);
        if (data != null) {
            this.defaultTableModelNotes.setRowCount(0);
            for (int i = 0; i < data.length; i++) {
                //defaultTableModelNotes.addRow(data[i]);
                defaultTableModelNotes.addRow(data[i]);
            }
        } else {
            System.out.println("data is null");
        }
    }

    private void addProToolsProject() {
        try {
            String proToolsId = textFieldProToolsId.getText();
            String proToolsName = textFieldProToolsName.getText();
            if (proToolsId.equals("")) {
                JOptionPane.showMessageDialog(this, "you need to state some kind of edible stuff");
            } else {
                System.out.println("id: " + proToolsId);
                System.out.println("name: " + proToolsName);
                List<ProToolsNote> notes = new ArrayList<>();
                ProToolsProject proToolsProject = activityApp.getPersist().addProToolsProject(proToolsId, proToolsName, notes);
                updateTable();
                this.updateTableNotes(proToolsProject);
            }
        } catch (NumberFormatException nfe) {
            JOptionPane.showMessageDialog(this, "number please or so");
            System.out.println(nfe.toString());
        }
    }

    private void addNote() {
        System.out.println("ProToolsPanel.addNote");
        //Long id = Long.parseLong(this.textFieldProToolsId.getText());
        String note = this.textFieldProToolsNote.getText();
        Long id = this.getSelectedProToolsProjectID();
        Date date = (Date)datePicker.getModel().getValue();
        if (id != null) {
            activityApp.getPersist().addProToolsNote(id, note, date);
            this.updateTableNotes(id);
        } else {
            JOptionPane.showMessageDialog(this, "select a project please!");
        }
    }
}
