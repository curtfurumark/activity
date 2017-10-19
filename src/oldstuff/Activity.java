/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oldstuff;

import activity.IActivity;
import activity.UpdateThread;
import activity.mysql.MySQL;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.Util;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JTextField;

/**
 *
 * @author curt
 */


    public class Activity extends JFrame implements IActivity {
    private static final String VERSION = "20160328";
    private JComboBox comboBoxCategories;
    private final JButton buttonStart = new JButton("start");
    private final   JButton buttonStop = new JButton("stop");
    private final   JButton buttonCommit = new JButton("commit");
    private final   JButton buttonFakeIt = new JButton("fake it");
    private final   JButton buttonClear =  new JButton("clear");
    
    private final JTextField textFieldComment = new JTextField(55);
    private final JTextField textFieldStart = new JTextField(55);
    private final JTextField textFieldStop = new JTextField(55);
    private final JTextField textFieldStartFake = new JTextField(55);
    private final JTextField textFieldStopFake = new JTextField(55); 
    private final JTextField textFieldSeconds = new JTextField(55);
    
    private LocalDateTime ldtStart;
    private LocalDateTime ldtNow;
    
    private UpdateThread updateThread;
    private void init(){
        setLayout(null);
        setSize(500, 700);
        setTitle("activity 2000 " + VERSION);
        comboBoxCategories = new JComboBox(MySQL.getCategories());
        buttonStart.addActionListener((ae)->start());
        buttonStop.addActionListener(ae->stop());
        buttonCommit.addActionListener(ae->commit());
        buttonFakeIt.addActionListener(ae->fakeIt());
        buttonClear.addActionListener(ae->clear());
        comboBoxCategories.setBounds(20,    50, 300, 40);
        textFieldComment.setBounds(20,      100, 300, 40);
        buttonStart.setBounds(20,           150, 300, 40);
        buttonStop.setBounds(20,            200, 300, 40);
        buttonCommit.setBounds(20,          250, 300, 40);
        textFieldStart.setBounds(20,        300, 300, 40);
        textFieldStop.setBounds(20,         350, 300, 40);
        textFieldSeconds.setBounds(20,      400 , 300, 40);
        buttonClear.setBounds(20,           450,  300, 40);
        buttonFakeIt.setBounds(20,          500, 300, 40);
        textFieldStartFake.setBounds(20,    550, 300, 40);
        textFieldStartFake.setText("2016-03-DD HH:mm");
        textFieldStopFake.setBounds(20, 600, 300, 40);
        textFieldStopFake.setText("2016-03-DD HH:mm");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        buttonStop.setEnabled(false);
        buttonCommit.setEnabled(false);
        buttonFakeIt.setEnabled(true);
        add(comboBoxCategories);
        add(textFieldComment);
        add(buttonStart);
        add(buttonStop);
        add(buttonCommit);
        add(textFieldStart);
        add(textFieldStop);
        add(textFieldSeconds);
        add(buttonFakeIt);
        add(textFieldStartFake);
        add(textFieldStopFake);
        add(buttonClear);
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
        new Activity().init();
    }
    public void commit(){
        Period  period;
        String comment = textFieldComment.getText();
        String category = (String) comboBoxCategories.getSelectedItem();
        Duration duration = Duration.between(ldtStart, ldtNow);
        System.out.println(duration.getSeconds() + " secs");
        MySQL.insert(category, ldtStart, duration.getSeconds(), comment);
        buttonStart.setEnabled((true));
        buttonCommit.setEnabled(false);
        textFieldComment.setText("");
        textFieldSeconds.setText("");
        textFieldStop.setText("");
        textFieldStart.setText("");
        //buttonStop.setEna
    }

    public void update() {
        textFieldStop.setText(MySQL.dateTimeToString(LocalDateTime.now()));
        Duration duration = Duration.between(ldtStart, ldtNow = LocalDateTime.now());
        long secs = duration.getSeconds();
        long mins = secs / 60;
        secs= secs % 60;
        String str = String.format("%02d:%02d", mins, secs);
        textFieldSeconds.setText(str);
       
    }
    
    public void fakeIt(){
        
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime start = LocalDateTime.parse(textFieldStartFake.getText(), formatter);
        System.out.println("localdateTime to string " + start.toString());
        ldtStart = null;
        

        LocalDateTime end = LocalDateTime.parse(textFieldStopFake.getText(), formatter);
        System.out.println("localdateTime to string " + end.toString());
        Duration duration = Duration.between(start, end);
        MySQL.insert(comboBoxCategories.getSelectedItem().toString(), start, duration.getSeconds(), textFieldComment.getText());

       
    
    }
    
}
