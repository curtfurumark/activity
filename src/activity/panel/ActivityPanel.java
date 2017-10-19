/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package activity.panel;

import activity.ActivityApp;
import activity.IActivity;
import activity.IPersist;
import activity.UpdateThread;
import activity.mysql.MySQL;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.time.Duration;
import java.time.LocalDateTime;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author User
 */
public class ActivityPanel extends JPanel implements IActivity{
    private ActivityApp activityApp;
    private JComboBox comboBoxCategories;
    private IPersist iPersist;
    private JButton buttonStart;
    private JButton buttonStop;
    private JButton buttonCommit;
    private JButton buttonClear;
    private JButton buttonAddNewCategory;
    private JTextField textFieldNewCategory;
    private JTextField textFieldComment; 
    private JTextField textFieldStart;
    private JTextField textFieldStop;
    private JTextField textFieldSeconds;
    private UpdateThread updateThread;
    private LocalDateTime               ldtStart;
    private LocalDateTime               ldtNow;
    
    public ActivityPanel(ActivityApp activityApp) {
        this.activityApp = activityApp;
    }
    private void init(){
        System.out.println("ActivityPanel.init()");
        //setSize(800, 700);
        //setTitle("activity 2016 " + VERSION);
        //setResizable(false);
        comboBoxCategories = new JComboBox(iPersist.getCategories());
        /*
        final JTextField tfListText = (JTextField) comboBoxCategories.getEditor().getEditorComponent();
        tfListText.addCaretListener(new CaretListener() {
            private String lastText;
    @Override
    public void caretUpdate(CaretEvent e) {
        String text = tfListText.getText();
        if (!text.equals(lastText)) {
            lastText = text;
            System.out.println(" HERE YOU CAN WRITE YOUR CODE");
        }
    }
});*/
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
        
        //this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        buttonStop.setEnabled(false);
        buttonCommit.setEnabled(false);
        //adding stuff to the mainpanel
        setLayout(null);
        add(comboBoxCategories);
        add(textFieldComment);
        add(buttonStart);
        add(buttonStop);
        add(buttonCommit);
        add(textFieldStart);
        add(textFieldStop);
        textFieldSeconds.setBackground(Color.LIGHT_GRAY);
        textFieldSeconds.setForeground(Color.BLACK);
        Font myFont = new Font("Courier", Font.BOLD, 24);
        textFieldSeconds.setFont(myFont);
        add(textFieldSeconds);
        add(buttonClear);
        add(textFieldNewCategory);
        add(buttonAddNewCategory);
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
    
    private void addNewCategory() {
        String str = textFieldNewCategory.getText();
        comboBoxCategories.addItem(str);
        iPersist.addNewCategory(str);
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
    
     public void stop(){
        ldtNow = LocalDateTime.now();
        updateThread.stop();
        buttonStop.setEnabled(false);
        buttonCommit.setEnabled(true);
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
        ///setTitle("activity " + str);
        textFieldSeconds.setText(str);
    }
    
    private void addCategory(ActionEvent ae) {
        System.out.println("addCategory....when does this fire");
        System.out.println(ae.getClass());
    }
    
}
