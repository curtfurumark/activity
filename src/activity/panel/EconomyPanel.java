/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package activity.panel;

import activity.ActivityApp;
import java.awt.Container;
import java.awt.GridLayout;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import javax.swing.JButton;
//import javax.swing.JCheckBox;
import javax.swing.JComboBox;
//import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import org.jdatepicker.impl.JDatePickerImpl;
import util.CFDatePickerFactory;

/**
 *
 * @author curt
 */
public class EconomyPanel extends JPanel {
    private final ActivityApp activityApp;
    private final JTextField  textfieldDescription = new JTextField(55);
    private final JTextField  textfieldAmount = new JTextField(55);
    private final JTextField  textfieldAverage = new JTextField("average here please");
    private final JButton     buttonAdd = new JButton("add");
    private final JTable      expensesTable = new JTable();
    private final JTable      incomeTable = new JTable();
    private final JLabel      labelAmount = new JLabel("amount:");
    private final String[]    stringChoices = {"fixed", "variable", "all"};
    private final JComboBox     comboBoxChoices= new JComboBox(stringChoices);
    
    //private final JLabel      labelFactor = new JLabel("factor");
    //private final JLabel      labelFactorNDays = new JLabel("number of days");
    //private final JTextField    textFieldFactorNDays = new JTextField();
    private final JLabel        labelInDaBank = new JLabel("in da bank");
    private final JTextField    textFieldInDaBank = new JTextField();
    private final JButton       buttonAdd2BankAccount = new JButton("add 2 account");
    //private final JCheckBox   checkBoxFactor = new JCheckBox("factor");
    //private JTable      tableEconomy = new JTable();
    private DefaultTableModel defaultTableModelExpenses;
    private DefaultTableModel defaulTableModelIncome;
    private JScrollPane economyTableScrollPane;
    private Date        date;

    private final Container tableContainerExpenses = new Container();
    private JScrollPane scrollPane;
    private JDatePickerImpl datePicker;
    private JDatePickerImpl firstDatePicker;
    private JDatePickerImpl lastDatePicker;
    public EconomyPanel(ActivityApp activityApp) {
        this.activityApp = activityApp;
        init();
    }
    
    private void initTable(){
        System.out.println("JPAPanel.initTable");
        String[] columnNames = {"ID","amount", "description", "date"};
        Object[][] data = activityApp.getPersist().getEconomy(7);
        defaultTableModelExpenses = new DefaultTableModel(data, columnNames);
        expensesTable.setModel(defaultTableModelExpenses);
        economyTableScrollPane = new JScrollPane(expensesTable);
        expensesTable.setFillsViewportHeight(true);
        tableContainerExpenses.setLayout(new GridLayout(1, 1));
        tableContainerExpenses.setBounds(370, 20, 350, 250);
        tableContainerExpenses.add(economyTableScrollPane);
        
    }

    private void init() {
        setLayout(null);
        textfieldDescription.setBounds(20, 20, 300, 30);
        labelAmount.setBounds(20, 70, 100, 30);
        textfieldAmount.setBounds(70, 70, 250, 30);
        comboBoxChoices.setBounds(20,120, 160, 30);
        //comboBoxChoices.addActionListener(ae->factorChoices());
        comboBoxChoices.setSelectedIndex(2);
        comboBoxChoices.addActionListener(ae->factorChoice());
        //checkBoxFactor.setBounds(20, 120, 60, 30);
        //checkBoxFactor.addActionListener(ae->checkBoxFactorListener());
        //labelFactorNDays.setBounds(80, 120, 120, 30);
        //textFieldFactorNDays.setBounds(190, 120, 50, 30);
        buttonAdd.setBounds(20, 170, 300, 30);
        buttonAdd.addActionListener(ae->add());
        datePicker = CFDatePickerFactory.createDatePicker();
        datePicker.addActionListener(ae->showDate());
        firstDatePicker = CFDatePickerFactory.createDatePicker();
        //firstDatePicker.getModel().setDate(WIDTH, WIDTH, WIDTH);
        lastDatePicker = CFDatePickerFactory.createDatePicker();
        datePicker.setBounds(20, 220, 300, 30);
        textfieldAverage.setBounds(20, 270, 300, 30);
        firstDatePicker.setBounds(20, 320, 300, 30);
        firstDatePicker.addActionListener(ae->showAverage());
        lastDatePicker.addActionListener(ae->showAverage());
        lastDatePicker.setBounds(20, 370, 300, 30);
        labelInDaBank.setBounds(20, 410, 100, 30);
        textFieldInDaBank.setBounds(120, 410, 80, 30);
        textfieldAverage.setText(activityApp.getPersist().getMonthAverage().toString());
        textFieldInDaBank.setText(activityApp.getPersist().getBankBalance().toString());
        buttonAdd2BankAccount.setBounds(220, 410, 100, 30);
        buttonAdd2BankAccount.addActionListener(ae->add2BankAccount());
        initTable();
        updateTable(LocalDate.now().minusDays(7), LocalDate.now());
        add(textfieldDescription);
        add(labelAmount);
        add(textfieldAmount);
        add(comboBoxChoices);
        //add(labelFactorNDays);
        //add(textFieldFactorNDays);
        add(buttonAdd);
        add(datePicker);
        add(textfieldAverage);
        add(firstDatePicker);
        add(lastDatePicker);
        add(tableContainerExpenses);
        add(labelInDaBank);
        add(textFieldInDaBank);
        add(buttonAdd2BankAccount);
    }

    private void add() {
        System.out.println("EconomyPanel.add()");
        String description =  this.textfieldDescription.getText();
        double amount = Double.parseDouble(this.textfieldAmount.getText());
        Date date = (Date)datePicker.getModel().getValue();
        activityApp.getPersist().addEconomy(description, amount, date);
        updatePanel();
        updateTable(date, date );
    }
    private void updatePanel(){
        System.out.println("updatePanel");
        textfieldAverage.setText(activityApp.getPersist().getWeekAverage().toString());
    }
    private void updateTable(Date firstDate, Date lastDate){
        System.out.println("updateTable()");
        LocalDate  firstLocalDate  = firstDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate lastLocalDate = lastDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        updateTable(firstLocalDate, lastLocalDate);
    }
    private void updateTable(LocalDate firstDate, LocalDate lastDate){
        System.out.println("EconomyPanel.updateTable()");
        defaultTableModelExpenses.setRowCount(0);
        Object data[][] = activityApp.getPersist().getEconomy(firstDate, lastDate);
         for (int i = 0; i < data.length; i++) {
            //if (DEBUG) System.out.println(data[i]);
            defaultTableModelExpenses.addRow(data[i]);
        }
    }
    /**
     * shows economy data for the specified date
     */
    private void showDate(){
        System.out.println("EconomyPanel.showDate()");
        Date date1 = (Date)datePicker.getModel().getValue();
        Object data[][] = activityApp.getPersist().getEconomy(  date1.toInstant().atZone(ZoneId.systemDefault()).toLocalDate(),
                                                                date1.toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
        defaultTableModelExpenses.setRowCount(0);
        for(int i = 0; i < data.length; i++){
            defaultTableModelExpenses.addRow(data[i]);
        }
        textfieldAverage.setText(activityApp.getPersist().getWeekAverage().toString());
    }

    private void showAverage() {
        System.out.println("EconomyPanel.showAverage..................................................");
        Date firstDate = (Date)firstDatePicker.getModel().getValue();
        Date lastDate = (Date)lastDatePicker.getModel().getValue();
        LocalDate  firstLocalDate  = firstDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate lastLocalDate = lastDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        System.out.println("\tfirst date: " + firstDate);
        System.out.println("\tlast date: " + lastDate);
        System.out.println("\tfactor: " + comboBoxChoices.getSelectedItem());
        FactorChoice fc = FactorChoice.fromString((String)comboBoxChoices.getSelectedItem());
        Double average = activityApp.getPersist().getAverageFromTo(firstLocalDate, lastLocalDate, fc);
        //Object[][] data = activityApp.getPersist().getEconomy(firstLocalDate, lastLocalDate);
        textfieldAverage.setText(average.toString());
        updateTable(firstLocalDate, lastLocalDate);
    }


    private void add2BankAccount() {
        System.out.println("add2BankAccount()");
        String strAmount = textFieldInDaBank.getText();
        Double amount = Double.parseDouble(strAmount);
        LocalDate date = LocalDate.now();
        activityApp.getPersist().add2BankAccount(date, amount);
    }

    private void factorChoice() {
        System.out.println("factorChoices()");
        String choice = (String)comboBoxChoices.getSelectedItem();
        System.out.println("\tchoice: " + choice);
        showAverage();
    }
    
}
