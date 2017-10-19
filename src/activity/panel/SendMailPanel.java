/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package activity.panel;

import activity.ActivityApp;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.MessagingException;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import mail.SendMail;

/**
 *
 * @author User
 */
public class SendMailPanel extends JPanel{
    private ActivityApp     activityApp = null;
    private JButton         buttonSendMail = new JButton("send mail");
    private JTextField      textFieldTo = new JTextField("");
    private JTextField      textFieldSubject = new JTextField("");
    private JTextArea       textAreaBody = new JTextArea("");

    public SendMailPanel(ActivityApp activityApp) {
        this.activityApp = activityApp;
        init();
    }
    private void init(){
        setLayout(null);
        textFieldTo.setBounds(      20, 20, 300, 30);
        textFieldSubject.setBounds( 20, 70, 300, 30);
        textAreaBody.setBounds(     20, 120, 300, 150);
        buttonSendMail.setBounds(   20, 290, 300, 30);
        buttonSendMail.addActionListener(ae->sendMail());
        add(textFieldTo);
        add(textFieldSubject);
        add(textAreaBody);
        add(buttonSendMail);
    }

    private void sendMail() {
        String to = textFieldTo.getText();
        String subject = textFieldSubject.getText();
        String body = textAreaBody.getText();
        sendMail(to, subject, body);
    }
    private void sendMail(String to, String subject, String body){
        System.out.println("to: " + to);
        System.out.println("subject: " + subject);
        System.out.println("body: " + body);
        try {
            SendMail.send(to, subject, body);
        } catch (MessagingException ex) {
            System.out.println(ex);
        }
    }
    
}
