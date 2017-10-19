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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;
import javax.swing.JButton;
import javax.swing.JFormattedTextField.AbstractFormatter;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;
import util.Debug;

/**
 *
 * @author UserDate
 */
public class FoodPanel extends JPanel {

    private static final boolean DEBUG = true;
    private final JTextField textFieldFood = new JTextField(55);
    private final JTextField textFieldKcal = new JTextField(55);
    private final JTextField textFieldWeight = new JTextField(55);
    private final JLabel labelFood = new JLabel("food:");
    private final JLabel labelKcal = new JLabel("kcal:");
    private final JLabel labelDate = new JLabel("date");
    private final JTextField textFieldRunMins = new JTextField();

    private final JTable table = new JTable();
    private final JTable tableCurrentDay = new JTable();
    private  final JTable tableWeight = new JTable();
    private final JScrollPane scrollPane4CurrentDayTable = new JScrollPane(tableCurrentDay);
    private final JScrollPane scrollPanelTable = new JScrollPane(table);
    private final JScrollPane  scrollPaneTableWeight = new JScrollPane(tableWeight);
    private ActivityApp activityApp;
    private final JButton buttonAdd = new JButton("add food item");
    private final JButton buttonRun = new JButton("add run mins");
    private final JButton buttonAddWeight = new JButton("add weight");
    private final Container tableContainer = new Container();
    private final Container tableCurrentDayContainer = new Container();
    private final Container tableWeightContainer = new Container();

    private DefaultTableModel defaultTableModel; // = new DefaultTableModel(ActivityUtil.getData(), ActivityUtil.getCategories());
    private DefaultTableModel defaultTableModelCurrentDay;
    private DefaultTableModel defaultTableModelWeight;

    private UtilDateModel model = new UtilDateModel();
    private JDatePanelImpl datePanel;
    private JDatePickerImpl datePicker;

    public FoodPanel(ActivityApp activityApp) {
        Properties properties = new Properties();
        properties.put("text.today", "today");
        properties.put("text.month", "month");
        properties.put("text.year", "year");
        this.datePanel = new JDatePanelImpl(model, properties);
        datePicker = new JDatePickerImpl(datePanel, new DateLabelFormatter());
        model.setSelected(true);
        this.activityApp = activityApp;
        init();
    }

    public void init() {
        setLayout(null);
        defaultTableModel = new DefaultTableModel(activityApp.getPersist().getSumLastDays(3), new String[]{"id", "kcal", "date"});
        table.setModel(defaultTableModel);
        Date date = new java.util.Date(Calendar.getInstance().getTime().getTime());
        //Date date =  new java.util.Date.valueOf("2016-06-20");
        defaultTableModelCurrentDay = new DefaultTableModel(activityApp.getPersist().getFoodItems4Date(date), new String[]{"food", "date"});
        tableCurrentDay.setModel(defaultTableModelCurrentDay);
        defaultTableModel.addTableModelListener(new FoodPanelTableListener(activityApp, this));
        table.getSelectionModel().addListSelectionListener(new FoodPanelListSelectionListener(activityApp, this));
        labelFood.setBounds(20, 20, 100, 30);
        textFieldFood.setBounds(60, 20, 300, 30);
        textFieldWeight.setBounds(380, 20, 50, 30);
        buttonAddWeight.setBounds(500, 20, 100, 30);
        labelKcal.setBounds(20, 70, 100, 30);
        textFieldKcal.setBounds(60, 70, 300, 30);
        
        //textFieldSum.setBounds(20, 120, 300, 30);
        labelDate.setBounds(20, 120, 50, 30);
        datePicker.setBounds(60, 120, 140, 30);
        buttonAdd.setBounds(230, 120, 130, 30);

        textFieldRunMins.setBounds(380, 70, 60, 30);
        buttonRun.setBounds(480, 70, 260, 30);

        tableContainer.setBounds(20, 170, 300, 300);
        tableContainer.setLayout(new GridLayout(1, 1));
        tableContainer.add(scrollPanelTable);

        tableCurrentDayContainer.setBounds(380, 250, 250, 300);
        tableCurrentDayContainer.setLayout(new GridLayout(1, 1));
        
        this.tableWeightContainer.setBounds(600, 250, 250,  300);
        tableWeightContainer.setLayout(new  GridLayout(1, 1));

        //tableCurrentDayContainer.setBounds(300, 250, 200,300);
        //tableCurrentDayContainer.setBackground(Color.red);
        //tableCurrentDayContainer.setLayout(new GridLayout(1, 1));
        tableCurrentDayContainer.add(scrollPane4CurrentDayTable);
        datePicker.addActionListener(ae -> changeDate());
        buttonAdd.addActionListener((e) -> addKcal());
        buttonRun.addActionListener(ae -> addRun());
        buttonAddWeight.addActionListener(ae->addWeight());
        //textFieldSum.setText(String.valueOf(activityApp.getPersist().getKcalSum()));
        add(labelFood);
        add(textFieldFood);
        add(textFieldWeight);
        add(labelKcal);
        add(textFieldKcal);
        add(datePicker);
        add(buttonAdd);
        add(buttonRun);
        add(textFieldRunMins);
        add(tableContainer);
        add(tableCurrentDayContainer);
        add(tableWeightContainer);
        add(buttonAddWeight);
        add(labelDate);
        
    }

    public void selectionChangeCB() {
        System.out.println("FoodPanel.selectionChangeCB()");
        int selectedRow = table.getSelectedRow();
        System.out.println("\tselected row: " + selectedRow);
        if (selectedRow > -1) {
            Date selectedDate = (Date) defaultTableModel.getValueAt(selectedRow, 2);
            System.out.println("date = " + selectedDate);
            updateTableCurrentDate(selectedDate);
            this.datePicker.getModel().setDate(selectedDate.getYear() + 1900,selectedDate.getMonth(), selectedDate.getDate());
        }
    }

    private void updateTable() {
        if (DEBUG) {
            System.out.println("FoodPanel.updateTable()");
        }
        Object[][] data = activityApp.getPersist().getSumLastDays(4);
        defaultTableModel.setRowCount(0);
        if (DEBUG) {
            System.out.println("\tnumber of rows: " + data.length);
        }
        for (int i = 0; i < data.length; i++) {
            //if (DEBUG) Debug.print(data[i]);
            defaultTableModel.addRow(data[i]);
        }
    }

    private void updateTableCurrentDate(Date date) {
        if (DEBUG) {
            System.out.println("FoodPanel.updateTable()");
        }
        Object[][] data = activityApp.getPersist().getFoodItems4Date(date);
        this.defaultTableModelCurrentDay.setRowCount(0);
        for (int i = 0; i < data.length; i++) {
            defaultTableModelCurrentDay.addRow(data[i]);
        }
    }

    private void addKcal() {
        try {
            String foodDescription = textFieldFood.getText();
            if (foodDescription.equals("")) {
                JOptionPane.showMessageDialog(this, "you need to state some kind of edible stuff");
            } else {
                this.activityApp.getPersist().addKcal(
                        textFieldFood.getText(),
                        Integer.parseInt(textFieldKcal.getText().trim()),
                        (Date) datePicker.getModel().getValue());
                this.textFieldFood.setText("");
                this.textFieldKcal.setText("");
                updateTable();
                this.updateTableCurrentDate((Date) datePicker.getModel().getValue());
            }
        } catch (NumberFormatException nfe) {
            JOptionPane.showMessageDialog(this, "number please or so");
            System.out.println(nfe.toString());
        }
    }

    private void changeDate() {
        if (DEBUG) {
            System.out.println("FoodPanel.changeDate()");
        }
        Date selectedDate = (Date) datePicker.getModel().getValue();
        if (DEBUG) {
            System.out.println(" new date: " + selectedDate);
        }
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        //System.out.println(dateFormat.format(selectedDate));
        textFieldFood.setText("");
        textFieldKcal.setText("");
        updateTableCurrentDate(selectedDate);
        //textFieldSum.setText(String.valueOf(activityApp.getPersist().getKcalSum(selectedDate)));
    }

    private void addRun() {
        Date selectedDate = (Date) datePicker.getModel().getValue();
        activityApp.getPersist().addRun(Integer.parseInt(textFieldRunMins.getText()), selectedDate);
        updateTable();
        ///this.updateTableCurrentDate(selectedDate);
    }

    private void addWeight() {
        System.out.println("activity.panel.FoodPanel.addWeight()");
        String weight = this.textFieldWeight.getText();
        System.out.println("\tweight: " + weight);
        activityApp.getPersist().addWeight(Float.parseFloat(weight), null);
    }
}

class DateLabelFormatter extends AbstractFormatter {

    private String datePattern = "yyyy-MM-dd";
    private SimpleDateFormat dateFormatter = new SimpleDateFormat(datePattern);

    @Override
    public Object stringToValue(String text) throws ParseException {
        return dateFormatter.parseObject(text);
    }

    @Override
    public String valueToString(Object value) throws ParseException {
        if (value != null) {
            Calendar cal = (Calendar) value;
            return dateFormatter.format(cal.getTime());
        }
        return "";
    }

}
