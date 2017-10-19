/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package activity.panel;

import activity.ActivityApp;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 *
 * @author User
 */
public class NotePanel extends JPanel{
    private JTextField      textFieldHeading = new JTextField(55);
    private JTextArea       textAreaNote = new JTextArea(20, 5);
    private JButton         buttonAddNote = new JButton("add note");
    private ActivityApp     activityTab = null;
    public NotePanel(){
        init();
    }

    public NotePanel(ActivityApp activityTab) {
        this.activityTab = activityTab;
        init();
    }
    public void init(){
        setLayout(null);
        buttonAddNote.setBounds(   20, 20,     500, 30);
        textFieldHeading.setBounds(     20, 70,     500, 30);
        textAreaNote.setBounds(    20, 120,    500, 200);
        textAreaNote.setLineWrap(true);
        buttonAddNote.addActionListener(ae->addNote());
        add(buttonAddNote);
        add(textAreaNote);
        add(textFieldHeading);
    
    }

    private void addNote() {
        System.out.println("NotePanel.addNote");
        String note = textAreaNote.getText();
        String heading =  textFieldHeading.getText();
        System.out.println("note: " + note);
        System.out.println("heading: " + heading);
        activityTab.getPersist().addNote(textFieldHeading.getText(), textAreaNote.getText());
        textFieldHeading.setText("");
        textAreaNote.setText("");
    }
}
