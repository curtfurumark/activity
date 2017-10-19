/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package activity.panel;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author curt
 */
public class ToDoTableListSelectionListener implements ListSelectionListener{
    private final TodoTablePanel todoTablePanel;

    public ToDoTableListSelectionListener(TodoTablePanel todoTablePanel){
        this.todoTablePanel = todoTablePanel; 
    }
    @Override
    public void valueChanged(ListSelectionEvent e) {
        System.out.println("ToDoTableListSelectionListener.valueChanged");
        //todoTablePanel.UpdateTextFieldDeleteTodoItem(Long id);
        todoTablePanel.UpdateTextFieldDeleteItem(12L);
    }
    
}
