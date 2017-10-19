/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package activity;

import activity.mysql.MySQL;
import activity.panel.EconomyPanel;
import jpa.JPAPersist;
import activity.panel.FakePanel;
import activity.panel.FoodPanel;
import activity.panel.JavaScriptPanel;
import activity.panel.JobPanel;
//import activity.panel.MailPanel;
import activity.panel.NotePanel;
import activity.panel.ProToolsPanel;
import activity.panel.SendMailPanel;
import activity.panel.TimerPanel;
import activity.panel.TodoTablePanel;
import activity.panel.UrlPanel;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.time.Duration;
import java.time.LocalDateTime;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import mail.PopperPanel;

/**
 *
 * @author curt
 */


public class ActivityApp extends JFrame implements Runnable, IActivity {
    private final   JTabbedPane         tabbedPane = new JTabbedPane();
    private final   JPanel              mainPanel = new JPanel();
    private final FakePanel             fakePanel;
    private final TodoTablePanel              todoTablePanel;
    private final JavaScriptPanel             javaScriptPanel;
    private final UrlPanel              urlPanel;
    private final SendMailPanel         sendMailPanel;
    private final PopperPanel           popperPanel;
    private final FoodPanel             foodPanel;
    private final JobPanel              jobPanel;
    private final EconomyPanel           economyPanel;
    //private final TimerPanel            timerPanel;
    private final ProToolsPanel         proToolsPanel;
    private static final String         VERSION = "20171009 build 001";
    private final static String         TITLE = "MY LIFE " + VERSION;
    //private final static String         
    private JComboBox                   comboBoxCategories;
    private final JButton               buttonStart = new JButton("start");
    private final JButton               buttonStop = new JButton("stop");
    private final JButton               buttonCommit = new JButton("commit");
    private final JButton               buttonAddNewCategory = new JButton("add new category");
    private final JButton               buttonClear =  new JButton("clear");  
    private final JTextField            textFieldComment = new JTextField(55);
    private final JTextField            textFieldStart = new JTextField(55);
    private final JTextField            textFieldStop = new JTextField(55); 
    private final JTextField            textFieldSeconds = new JTextField(55);
    private final JTextField            textFieldNewCategory = new JTextField(255);
    
    //private final JScrollPane                 scrollPaneTodoTable = new JScrollPane();
    //private  final DefaultTableModel           defaultTableModel = null;
    
    private LocalDateTime               ldtStart;
    private LocalDateTime               ldtNow;
    
    private  final IPersist                   iPersist;
    
    private UpdateThread updateThread;
    public ActivityApp(){
        //iPersist = new SQLStatements();
        iPersist = new JPAPersist();
        urlPanel = new UrlPanel(this);
        sendMailPanel = new SendMailPanel(this);
        popperPanel = new PopperPanel(this);
        foodPanel = new FoodPanel(this);
        jobPanel = new JobPanel(this);
        economyPanel = new EconomyPanel(this);
        fakePanel = new FakePanel(this);
        todoTablePanel = new TodoTablePanel(this);
        javaScriptPanel = new JavaScriptPanel(this);
        //timerPanel = new TimerPanel(this);
        proToolsPanel = new ProToolsPanel(this);
    }

    @Override
    public String toString() {
        return "ActivityApp{" +  VERSION  + " }";
    }
    private void print(Object[] row){
        for(Object o: row){
            System.out.print(o + " ");
        }
        System.out.println("");
    }
    public void updateCategoriesBox(){
        comboBoxCategories.removeAllItems();
        String[] categories = iPersist.getCategories();
        for(String str: categories){
            comboBoxCategories.addItem(str);
        }
    }
    
    public String sayHello(){
        return "hello from ActivityApp";
    }
    public static String staticHello(){
        return "static hello..from ActivityApp";
    }
    private void init(){
        setSize(800, 700);
        setTitle(TITLE);
        setResizable(false);
        comboBoxCategories = new JComboBox(iPersist.getCategories());

        comboBoxCategories.setEditable(true);
        comboBoxCategories.addActionListener(ae->addCategory(ae));
        buttonStart.addActionListener((ae)->start());
        buttonStop.addActionListener(ae->stop());
        buttonCommit.addActionListener(ae->commit());
        buttonClear.addActionListener(ae->clear());
        buttonAddNewCategory.addActionListener(ae->addNewCategory());
        comboBoxCategories.setBounds(       20, 50, 300, 40);
        textFieldNewCategory.setBounds(     340, 50, 300, 40 );
        textFieldComment.setBounds(         20, 100, 300, 40);
        buttonAddNewCategory.setBounds(     340, 100, 300, 40);
        buttonStart.setBounds(              20, 150, 300, 40);
        buttonStop.setBounds(               20, 200, 300, 40);
        buttonCommit.setBounds(             20, 250, 300, 40);
        textFieldStart.setBounds(           20, 300, 300, 40);
        textFieldStop.setBounds(            20, 350, 300, 40);
        textFieldSeconds.setBounds(         20, 400 ,300, 40);
        buttonClear.setBounds(              20, 450, 300, 40);
        
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        buttonStop.setEnabled(false);
        buttonCommit.setEnabled(false);
        //adding stuff to the mainpanel
        mainPanel.setLayout(null);
        mainPanel.add(comboBoxCategories);
        mainPanel.add(textFieldComment);
        mainPanel.add(buttonStart);
        mainPanel.add(buttonStop);
        mainPanel.add(buttonCommit);
        mainPanel.add(textFieldStart);
        mainPanel.add(textFieldStop);
        textFieldSeconds.setBackground(Color.LIGHT_GRAY);
        textFieldSeconds.setForeground(Color.BLACK);
        Font myFont = new Font("Courier", Font.BOLD, 24);
        textFieldSeconds.setFont(myFont);
        mainPanel.add(textFieldSeconds);
        mainPanel.add(buttonClear);
        mainPanel.add(textFieldNewCategory);
        mainPanel.add(buttonAddNewCategory);
        //fakePanel = new FakePanel(this);
         //jobPanel = new JobPanel(this);
        tabbedPane.addTab("main tab", mainPanel);
        tabbedPane.addTab("fake tab", fakePanel);
        tabbedPane.addTab("todo table", todoTablePanel);
        tabbedPane.addTab("urls", urlPanel);
        //tabbedPane.addTab("javascript", javaScriptPanel);
        tabbedPane.addTab("mail", popperPanel);
        tabbedPane.addTab("food", foodPanel);
        tabbedPane.addTab("sendMail", sendMailPanel);
        tabbedPane.addTab("job", jobPanel);
        tabbedPane.addTab("economy", economyPanel);
        //tabbedPane.addTab("timer", timerPanel);
        tabbedPane.addTab("pro tools", proToolsPanel);
        add(tabbedPane);
        init_note_panel();
        setVisible(true);
    }
    public void start(){
        ldtStart = LocalDateTime.now();
        buttonStart.setEnabled(false);
        buttonStop.setEnabled(true);
        buttonCommit.setEnabled(false);
        textFieldStart.setText(MySQL.dateTimeToString(ldtStart));
        updateThread = new UpdateThread(this);
        Thread thread = new Thread(updateThread);
        thread.start();
    }
    public void clear(){
        ldtNow = ldtStart = null;
        buttonStart.setEnabled(true);
        buttonStop.setEnabled(false);
        buttonCommit.setEnabled(false);
    }
    public void stop(){
        ldtNow = LocalDateTime.now();
        updateThread.stop();
        buttonStop.setEnabled(false);
        buttonCommit.setEnabled(true);
    }
    public static void main(String[] args) {
        new ActivityApp().init();
    }
    public void commit(){
        //Period  period;
        String comment = textFieldComment.getText();
        String category = (String) comboBoxCategories.getSelectedItem();
        Duration duration = Duration.between(ldtStart, ldtNow);
        System.out.println(duration.getSeconds() + " secs");
        iPersist.insert(category, ldtStart, duration.getSeconds(), comment);
        buttonStart.setEnabled((true));
        buttonCommit.setEnabled(false);
        textFieldComment.setText("");
        textFieldSeconds.setText("");
        textFieldStop.setText("");
        textFieldStart.setText("");
    }

    public void update() {
        textFieldStop.setText(MySQL.dateTimeToString(LocalDateTime.now()));
        Duration duration = Duration.between(ldtStart, ldtNow = LocalDateTime.now());
        long secs = duration.getSeconds();
        long hours = secs / 3600;
        if ( hours != 0){
            secs = secs % (3600 * hours);
        }
        long mins = secs / 60;
        if ( mins != 0){
            secs= secs % (60 * mins);
        }
        String str = String.format("%02d:%02d:%02d",hours, mins, secs);
        setTitle("activity " + str);
        textFieldSeconds.setText(str);
    }

    @Override
    public void run() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void init_note_panel() {
        tabbedPane.addTab("notes", new NotePanel(this));
    }

    public IPersist getPersist() {
        return iPersist;
    }

    private void addNewCategory() {
        String str = textFieldNewCategory.getText();
        comboBoxCategories.addItem(str);
        iPersist.addNewCategory(str);
    }

    private void addCategory(ActionEvent ae) {
        System.out.println("addCategory....when does this fire");
        System.out.println(ae.getClass());
    }
}
