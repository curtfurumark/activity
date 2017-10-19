/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package activity.panel;

import activity.ActivityApp;
import activity.ToDoTableListener;
import java.awt.Container;
import java.awt.GridLayout;
import jpa.TodoItem;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author User
 */
public class TodoTablePanel extends JPanel {
    private ActivityApp activityApp;
    private DefaultTableModel defaultTableModel;
    private JTable      tableTodoItems;
    private JScrollPane tableScrollPane;
    private final Container   tableContainer = new Container();
    private final JTextField  textFieldTodo = new JTextField(255);
    private final JButton     buttonTodo = new JButton("add todo item");
    private final JTextField  textFieldTodoID = new JTextField(255);
    private final JButton     buttonDeleteItem =  new JButton("delete item");

    public TodoTablePanel(ActivityApp activityApp) {
        super();
        this.activityApp = activityApp;
        init();
    }  
    private void init(){
        Object[][] data = activityApp.getPersist().getTodoItems();
        String[] columnNames = {"ID","description", "is_done", "timestamp"};
        defaultTableModel = new DefaultTableModel(data, columnNames);
        defaultTableModel.addTableModelListener(new ToDoTableListener(activityApp, this));
        tableTodoItems = new JTable();
        tableTodoItems.setModel(defaultTableModel);
        tableScrollPane = new JScrollPane(tableTodoItems);
        tableTodoItems.getSelectionModel().addListSelectionListener(new  ToDoTableListSelectionListener(this));
        //tableTodoItems.getColumnModel().getColumn(0).setWidth(30);
        //tableTodoItems.getColumnModel().getColumn(1).setWidth(110);
        setLayout(null);
        textFieldTodo.setBounds(            20, 20,  600, 30);
        buttonTodo.setBounds(               20, 70,  600, 30);
        textFieldTodoID.setBounds(          20, 120, 200, 30);
        buttonDeleteItem.setBounds(         20, 170, 200, 30);
        //tableTodoItems.setBounds(           20, 220, 600, 400);
        tableContainer.setBounds(20, 220, 600, 400);
        tableContainer.setLayout(new GridLayout(1, 1));
        
        buttonTodo.addActionListener(ae->addTodo());
        buttonDeleteItem.addActionListener(ae->deleteItem());
        add(textFieldTodo);
        add(buttonTodo);
        add(textFieldTodoID);
        add(buttonDeleteItem);
        tableContainer.add(tableScrollPane);
        add(tableContainer);
        //add(tableTodoItems, null);
    }
    private void addTodo() {
        activityApp.getPersist().addTodo(new TodoItem(textFieldTodo.getText()));
        updateTable();
    }
    
    public void updateTable(){
        System.out.println("TodoTablePanel.updateTable()");
        Object[][] data = activityApp.getPersist().getTodoItems();
        defaultTableModel.setRowCount(0);
        for ( int i = 0; i < data.length; i++){
            defaultTableModel.addRow(data[i]);
        }
    }
    
    private void markTodoItemDone() {
        System.out.println("TodoTablePanel.markTodoItemDone");
        activityApp.getPersist().markTodoItemDone(Long.parseLong(textFieldTodoID.getText()));
        updateTable();
    }

    private void deleteItem() {
        System.out.println("TodoTablePanel.deleteItem()");;
        activityApp.getPersist().deleteItem(Long.parseLong(textFieldTodoID.getText()));
        updateTable();
                
    }

    void UpdateTextFieldDeleteItem(Long id) {
        System.out.println("UpdateTextFieldDeleteItem(Long id");
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
