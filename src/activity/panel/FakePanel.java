/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package activity.panel;

import activity.ActivityApp;
import activity.IPersist;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author User
 */
public class FakePanel extends JPanel {
    private ActivityApp activityApp;
    private JButton     buttonFakeIt = new JButton("fake it");
    private JTextField  textFieldStartFake = new JTextField(55);
    private JTextField  textFieldStopFake = new JTextField(55);
    private JTextField  textFieldComment = new JTextField(55);
    private JButton     buttonMarkTodoItemDone =  new JButton("mark todoitem as done");
    private JComboBox   comboBoxCategories;

    public FakePanel(ActivityApp activityApp) {
        super();
        this.activityApp = activityApp;
        init();
    }
    public void init(){
        setLayout(null);
        add(buttonFakeIt);
        comboBoxCategories = new JComboBox(activityApp.getPersist().getCategories());
        buttonFakeIt.addActionListener(ae->fakeIt());
        comboBoxCategories.setBounds(       20, 50, 300, 40);
        textFieldStartFake.setBounds(       20, 100, 300, 40);
        System.out.println(LocalDateTime.now().toString());
        LocalDateTime localDateTime = LocalDateTime.parse("2016-07-05T10:15:00");
        LocalDateTime localDateTimeEnd= LocalDateTime.parse("2016-07-08T17:15:00");
        LocalDateTime localDateTimeNow = LocalDateTime.now();
        //localDateTimeNow.minusMinutes(localDateTime.);
        /*Duration duration = Duration.ofSeconds(localDateTimeNow.toEpochSecond(ZoneOffset.UTC) - localDateTime.toEpochSecond(ZoneOffset.UTC));
        Duration durationLeft = Duration.ofSeconds(localDateTimeEnd.toEpochSecond(ZoneOffset.UTC) - localDateTimeNow.toEpochSecond(ZoneOffset.UTC));
        System.out.println(" hours: " + duration.getSeconds() / 3600);
        System.out.println(" hours left: " + durationLeft.getSeconds() / 3600);
        */
        //localDateTime.m
        //textFieldStartFake.setText(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        textFieldStartFake.setText(localDateTime.toString());
        textFieldStopFake.setBounds(        20, 150, 300, 40);
        textFieldStopFake.setText(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        textFieldComment.setBounds(         20, 200, 300, 40);
        buttonFakeIt.setBounds(             20,  250, 300, 40);
        add(textFieldStartFake);
        add(textFieldStopFake);
        add(comboBoxCategories);
        add(textFieldComment);
    }

    private void fakeIt() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime start = LocalDateTime.parse(textFieldStartFake.getText(), formatter);
        System.out.println("localdateTime to string " + start.toString());
        //ldtStart = null;
        LocalDateTime end = LocalDateTime.parse(textFieldStopFake.getText(), formatter);
        System.out.println("localdateTime to string " + end.toString());
        Duration duration = Duration.between(start, end);
        activityApp.getPersist().insert(comboBoxCategories.getSelectedItem().toString(), start, duration.getSeconds(), textFieldComment.getText());
    }
}
