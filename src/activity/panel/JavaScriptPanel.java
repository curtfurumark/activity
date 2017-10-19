/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package activity.panel;

import activity.ActivityApp;
import java.awt.BorderLayout;
import java.util.List;
//import java.util.logging.Level;
//import java.util.logging.Logger;
import javax.script.ScriptContext;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 *
 * @author User
 */
public class JavaScriptPanel extends JPanel {
    ActivityApp activityApp;
    private JTextArea   textAreaJavaScript = new JTextArea(10, 5);
    private JTextField  textFieldResult     = new JTextField(20);
    private JButton     buttonEvaluate = new JButton("evaluate javascript");
    private ScriptEngineManager manager = new ScriptEngineManager();
    private     ScriptEngine engine = manager.getEngineByName("nashorn");
    private HelloJS helloJS = new HelloJS();

    public JavaScriptPanel(ActivityApp activityApp) {
        this.activityApp = activityApp;
        init();
    }
    // s.eval("manager.test(); manager.class.static.test2();");
    private void init(){
        setLayout(new BorderLayout());
        buttonEvaluate.addActionListener(ae->evaluate());
        add(buttonEvaluate, BorderLayout.NORTH);
        add(textAreaJavaScript, BorderLayout.CENTER);
        add(textFieldResult, BorderLayout.SOUTH);
        //engine.put("jazz", helloJS );
        engine.put("persist", activityApp.getPersist());
        engine.put("jazz", activityApp);
    }
    private void print(ScriptContext scriptContext){
        List<Integer> scopes = scriptContext.getScopes();
        for ( Integer i: scopes){
            System.out.println("scope: " + i);
        }
    }

    private void evaluate() {
        try {
            String jsCode = textAreaJavaScript.getText();
            System.out.println("jsCode: " + jsCode);
            ScriptContext  scriptContext = engine.getContext();
            scriptContext.setAttribute("my_var", 42, 100);
            //scriptContext.setAttribute("mymy", new activity.mysql.MySQL(), 100);

            Object o = engine.eval(jsCode);  
            print(scriptContext);
            Object o2 = engine.getContext().getAttribute("my_var");
            if (o2 != null){
                System.out.println("02:" + o2);
            }
            if ( o != null){
                textFieldResult.setText(o.toString());
            }else{
                textFieldResult.setText("returned null");
            }
        } catch (ScriptException ex) {
            textFieldResult.setText(ex.toString());
        }
    }
    
    
}
