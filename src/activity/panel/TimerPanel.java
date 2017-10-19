/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package activity.panel;

import activity.ActivityApp;
import activity.IPersist;
import activity.UpdateThread;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.xml.transform.Source;

/**
 *
 * @author User
 */
public class TimerPanel extends JPanel {
            
    private final ActivityApp activityApp;
    private final JButton buttonFakeIt = new JButton("start timer");
    private final JTextField textFieldStartFake = new JTextField(55);
    private final JTextField textFieldStopFake = new JTextField(55);
    private final JTextField textFieldComment = new JTextField(55);
    private final LocalDateTime localDateTimeStart = LocalDateTime.parse("2016-09-12T09:00:00");
    private final LocalDateTime localDateTimeEnd = LocalDateTime.parse("2016-12-22T13:10:00");
    private Duration durationTotal  = null;
    private Duration durationPassed = null;
    private Duration durationLeft = null;
    private  LocalDateTime localDateTimeNow = LocalDateTime.now();
    private static final boolean DEBUG = false;
    public TimerPanel(ActivityApp activityApp) {
        super();
        this.activityApp = activityApp;
        init();
    }

    private void print(){
        System.out.println("start. "  + localDateTimeStart);
        System.out.println("end: " + localDateTimeEnd);
        System.out.println("durationPassed: " + durationPassed);
        System.out.println("durationLeft: + durationLeft");
    }
    private String print(long seconds){

        
        long days = seconds / (24 *  3600);
        //System.out.println("days: " + days);
        seconds -= days * 24 * 3600;
        long hours = seconds / 3600;
        
        seconds -= hours * 3600;
        long minutes = seconds / 60;
        seconds -= minutes * 60;
        String tmp = days + ":" + hours + ":" + minutes + ":" + seconds;
        //System.out.println("tmp: " + tmp);
        return tmp;
    }
    private void init() {
        System.out.println("TimerPanel.init()");
        setLayout(null);
        add(buttonFakeIt);
        buttonFakeIt.addActionListener(ae -> update());
        textFieldStartFake.setBounds(20, 100, 300, 40);
        System.out.println(LocalDateTime.now().toString());
        Thread thread = new Thread(new TimerThread(this));
        thread.start();
        textFieldStartFake.setText(localDateTimeStart.toString());
        textFieldStopFake.setBounds(20, 150, 300, 40);
        textFieldStopFake.setText(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        textFieldComment.setBounds(20, 200, 300, 40);
        buttonFakeIt.setBounds(20, 250, 300, 40);
        add(textFieldStartFake);
        add(textFieldStopFake);
        add(textFieldComment);
    }

    protected void update() {
        if ( DEBUG) System.out.println("TimerPanel.update");
        localDateTimeNow = LocalDateTime.now();
        //Duration duration = Duration.ofSeconds(localDateTimeNow.toEpochSecond(ZoneOffset.UTC) - localDateTimeStart.toEpochSecond(ZoneOffset.UTC));
        durationPassed = Duration.ofSeconds(localDateTimeNow.toEpochSecond(ZoneOffset.UTC) - localDateTimeStart.toEpochSecond(ZoneOffset.UTC));
        durationLeft = Duration.ofSeconds(localDateTimeEnd.toEpochSecond(ZoneOffset.UTC) - localDateTimeNow.toEpochSecond(ZoneOffset.UTC));
        durationTotal = Duration.ofSeconds(localDateTimeEnd.toEpochSecond(ZoneOffset.UTC) - localDateTimeStart.toEpochSecond(ZoneOffset.UTC));
        String strPassed = print(durationPassed.getSeconds());
        //long secsDone = durationPassed.getSeconds();
        String strLeft = print(durationLeft.getSeconds());
        String total = print(durationTotal.getSeconds());
        
        double percentRemaining = durationPassed.getSeconds() / durationTotal.getSeconds();
        long durationPassedLong = durationPassed.getSeconds();
        long durationRemainingLong = durationLeft.getSeconds();
        System.out.println("durationPassedLong: " + durationPassedLong);
        System.out.println("durationRemainingLong " + durationRemainingLong );
        System.out.println("total: " + total + " percentLeft: " + percentRemaining);
        //print(secsDone);
        //long hoursDone = secsDone / 3600;
        //long daysDone = hoursDone / 24;
        //System.out.println("daysDone: " + daysDone);
        //secsDone -= hoursDone * 3600;
        //long minsDone = secsDone / 60;
        //secsDone -= minsDone * 60;
        /*System.out.println("\thours: " + hoursDone);
        System.out.println("\tinutes done: " + minsDone);
        System.out.println("\tseconds done: " + secsDone);
        System.out.println("duration" + duration.toString());
        System.out.println("\thours left: " + durationLeft.getSeconds() / 3600);*/
        //String tmp = hoursDone + ":" + minsDone + ":" + secsDone;
        //DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        //formatter.
        //duration.
        /*long hoursToGo = durationLeft.getSeconds() / 3600;
        long secsToGo = durationLeft.getSeconds()  %  3600;
        long minsToGo = secsToGo / 60;
        secsToGo = secsToGo %  60;
        String strToGo = hoursToGo + ":" + minsToGo + ":" + secsToGo;
*/
        this.textFieldStartFake.setText(strPassed);
        this.textFieldStopFake.setText(strLeft);
    }
}

class TimerThread implements Runnable {

    private TimerPanel timerPanel;

    public TimerThread(TimerPanel timerPanel) {
        this.timerPanel = timerPanel;
    }

    public void run() {
        boolean running = true;
        while (running) {
            try {
                timerPanel.update();
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(UpdateThread.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
