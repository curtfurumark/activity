/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package activity;

import activity.panel.FactorChoice;
import java.time.LocalDate;
import java.util.Date;
import jpa.TodoItem;
import java.time.LocalDateTime;
import java.util.List;
import jpa.ProToolsNote;
import jpa.ProToolsProject;

/**
 *
 * @author User
 */
public interface IPersist {
    public void insert (String category, LocalDateTime start, long secs, String comment);
    public void saveUrl(String url, String comment);
    public void addTodo(TodoItem todoItem);
    public void markTodoItemDone(Long id);
    public void deleteItem(Long id);
    //public String getCategoryiesNew();
    //public static String[] getCategoriesOld(){
    //    return new String[]{"enthuware", "bass", "running", "art", "programming", "whatever", "plockeri", "pro tools", "lectures", "lexicon", "pluralsight"};
    //}
    public String[] getCategories();
    public Object[][] getTodoItems();

    public void addNote(String heading, String text);
    public void deleteCategory(String name);
    public void updateTodo(long id, String description, boolean _done);
    public void addNewCategory(String name);
    public void addKcal(String description, int kcal, Date date);
    public void addRun(int mins, Date date);
    public void addWeight( float weight, Date  date);
    public void addEconomy(String description , double amount, Date date);
    public Long getKcalSum();
    public Long getKcalSum(Date date);
    public Double getWeekAverage();
    /**
     * from JPAEconomy, average spent per day last month counting from today
     * @return 
     */
    public Double getMonthAverage();
    public Double getAverageFromTo(LocalDate  firstDate, LocalDate lastDate, FactorChoice fc);
    public Object[][] getSumLastDays(int days);
    public Object[][] getFoodItems4Date(Date date);
    public Object[][] getFoodItems4ID(long id);
    public void addApplication(String heading, String description, Date lastApplicationDate);
    public Object[][] getApplications();
    public Object[][] getApplicationNotes(long id);
    public Object[][] getProToolsProjects();
    public Object[][] getProToolsNotes(Long id);
    public Object[][] getWeight();
    public ProToolsProject addProToolsProject(String proToolsId, String proToolsName,List<ProToolsNote> notes);
    public ProToolsProject addProToolsNote(Long proToolsId, String note, Date date);
    public Object[][] getEconomy(int i);
    public Object[][] getEconomy(LocalDate firstDate, LocalDate lastDate);
    public Double getBankBalance();
    public void add2BankAccount(LocalDate date, Double amount);
}