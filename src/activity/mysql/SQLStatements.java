/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package activity.mysql;

import activity.IPersist;
import jpa.TodoItem;
import static activity.mysql.MySQL.dateTimeToString;
import activity.panel.FactorChoice;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import jpa.ProToolsNote;
import jpa.ProToolsProject;

/**
 *
 * @author User
 */
public class SQLStatements implements IPersist{
    private static final String FILE_PATH  = "C:/Users/User/Downloads/personal/";
    private static final String FILE_NAME  = "activity.sql";
    private static final boolean DEBUG = true;
    @Override
    public void insert(String category, LocalDateTime start, long secs, String comment) {
        if (DEBUG )System.out.println("SQLStatements.insert()");
        try {
            String str = dateTimeToString(start);
            String query = String.format("\ninsert into activity(activity, start, seconds, comment) values(\"%s\",\"%s\", %d,\"%s\");", category, str, secs, comment);
            writeToFile(query);
        } catch (IOException ex) {
            Logger.getLogger(SQLStatements.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    /**
     * trying to get a newline
     * @param str
     * @throws IOException 
     */
    public void writeToFile(String str) throws IOException {       
        FileWriter fileWriter = new FileWriter(FILE_PATH + FILE_NAME, true);
        fileWriter.write(System.getProperty( "line.separator" ));
        fileWriter.write(str);
        fileWriter.close();
    }

    @Override
    public void saveUrl(String url, String comment) {
        try {
            writeToFile("\n" + url + "//" + comment);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void addTodo(TodoItem todoItem) {
        try {
            this.writeToFile(todoItem.toString());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void markTodoItemDone(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Object[][] getTodoItems() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void addNote(String heading, String text) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void updateTodo(long id, String description, boolean _done) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void deleteItem(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void addNewCategory(String name) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String[] getCategories() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void deleteCategory(String name) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void addKcal(String description, int kcal, Date date) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Long getKcalSum() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Long getKcalSum(Date date) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void addRun(int mins, Date date) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Object[][] getSumLastDays(int days) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Object[][] getFoodItems4Date(Date date) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void addEconomy(String description, double amount, Date date) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Object[][] getFoodItems4ID(long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void addApplication(String heading, String description, Date lastApplicationDate) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Object[][] getApplications() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Object[][] getApplicationNotes(long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Object[][] getProToolsProjects() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Object[][] getProToolsNotes(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ProToolsProject addProToolsProject(String proToolsId, String proToolsName, List<ProToolsNote> notes) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ProToolsProject addProToolsNote(Long proToolsId, String note, Date date) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void addWeight(float weight, Date date) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Object[][] getWeight() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Double getWeekAverage() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Double getAverageFromTo(LocalDate firstDate, LocalDate lastDate, FactorChoice fc) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Object[][] getEconomy(int i) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Object[][] getEconomy(LocalDate firstDate, LocalDate lastDate) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Double getMonthAverage() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Double getBankBalance() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void add2BankAccount(LocalDate date, Double amount) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
