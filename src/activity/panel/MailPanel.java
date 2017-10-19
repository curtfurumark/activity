/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package activity.panel;

import activity.ActivityApp;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author User
 */
public class MailPanel extends JPanel {
    private final ActivityApp activityApp;
    private final JButton checkButton = new JButton("check");
    private final JTextField textFieldPopServer = new JTextField(55);
    private final JTextField textFieldUser = new JTextField(55);
    private final JTextField textFieldPassword = new JTextField(55);

    public MailPanel(ActivityApp activityApp) {
        this.activityApp = activityApp;
        init();
    }
    private void init(){
        setLayout(null);
        checkButton.setBounds(20, 20, 300, 30);
        checkButton.addActionListener((ae)->checkMail());
        add(checkButton);
    
    }

    private void checkMail() {
        System.out.println("check the mail....");
    }
    
}
