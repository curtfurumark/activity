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
public class UrlPanel extends JPanel {
    private JPanel          tablePanel = new JPanel();
    private JTextField      textFieldUrl = new JTextField(255);
    private JTextField      textFieldUrlComment = new JTextField(255);
    public ActivityApp      activityApp;
    private JButton         buttonSaveUrl = new JButton("save url");
    private JButton         buttonSearch = new JButton("search");
    private JTextField      textFieldSearch = new JTextField(255);

    public UrlPanel(ActivityApp activityApp) {
        this.activityApp = activityApp;
        init();
    }
    
    private void init(){
        setLayout(null);
        textFieldUrl.setBounds(20, 20, 600, 30);
        textFieldUrlComment.setBounds(20, 70, 600, 30);
        buttonSaveUrl.setBounds(20, 120, 600, 30);
        buttonSaveUrl.addActionListener(ae->saveUrl());
        textFieldSearch.setBounds(20, 170, 600, 30);
        buttonSearch.setBounds(20, 220, 200, 30);
        buttonSearch.addActionListener(ae->search());
        add(textFieldUrl);
        add(textFieldUrlComment);
        add(buttonSaveUrl);
        add(textFieldSearch);
        add(buttonSearch);
    
    }

      private void saveUrl() {
        String url = textFieldUrl.getText();
        String comment = textFieldUrlComment.getText();
        activityApp.getPersist().saveUrl(url ,  comment);
        textFieldUrl.setText("");
        textFieldUrlComment.setText("");
    }

    private void search() {
        System.out.println("UrlPanel.search()");
    }
}


