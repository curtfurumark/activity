/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.List;
import java.util.regex.Pattern;
import javax.persistence.TypedQuery;
import jpa.JPAEnumTemp.IsDone;

/**
 *
 * @author User
 */
public class JPATester extends JPASuper {
    

    public TodoItem findTodoItemById(Long id) {
        return em.find(TodoItem.class, id);
    }

    public void remove(TodoItem todoItem) {
        System.out.println("JPATester.remove(TodoItem)");
        System.out.println(todoItem.toString());
        //TodoItem deleteMe = em.merge(todoItem);
        //if( deleteMe == null){
        //    System.out.println("deleteMe is null");
        //}
        tx.begin();
        em.remove(em.merge(todoItem));
        tx.commit();
        System.out.println("should be removed...");
    }

    public void removeTodoItemById(Long id) {
        System.out.format("removeTodoItemById(%d)\n", id);
        TodoItem todoItem = em.find(TodoItem.class, id);
        if (todoItem != null) {
            tx.begin();
            em.remove(todoItem);
            tx.commit();
        } else {
            System.out.println("could not find object with id: " + id);
        }
        System.out.println("removed ? ! ? ");
    }

    public void setTodoItemStatus(long id, boolean status) {

    }

    public void setTodoItemStatus(TodoItem todoItem, boolean status) {
        System.out.println("setTodoItemStatus(TodoItem, boolean");
        TodoItem tobupdated = em.merge(todoItem);
        tx.begin();
        tobupdated.setIs_done(true);
        tx.commit();
        System.out.println(todoItem.toString());
    }

    public Long getKcalSum() {
        System.out.println("getKcalSum");
        Date date = new Date(Calendar.getInstance().getTimeInMillis());
        String jpql_query = "select sum(f.kcal) from FoodItem f where f.date = :date";
        TypedQuery<Long> query = em.createQuery(jpql_query, Long.class);
        query.setParameter("date", date);
        Long sum = query.getSingleResult();
        System.out.println("sum: " + sum);
        return sum;
    }

    public void getKcalSumPerDay() {
        System.out.println("getKcalSumPerDay");

        String jpql_query = "select sum(f.kcal), f.date from FoodItem f group by f.date";
        List<Object[]> result = em.createQuery(jpql_query).getResultList();
        for (Object[] objectArray : result) {
            Long kcal = (Long) objectArray[0];
            Date date = (Date) objectArray[1];
            System.out.println("kcal  " + kcal + ", Date: " + date);
        }
    }

    public void jobApplication() {
        JPAJobApplication jobApplication = new JPAJobApplication("dad");
        jobApplication.addNote(new JPAJobApplicationNote("woke kids"));
        jobApplication.addNote(new JPAJobApplicationNote("served breakfast"));
        persist(jobApplication);
        //jobApplication.setLastDate( date);
        //jobApplication.setDescription( descripition);
    }
    public void testJPAIncome(){
        System.out.println("testJPAIncom()");
        JPAIncome jpaIncome = new JPAIncome("lön kommun, augusti", 1871.50, "2017-09-26");
        persist(jpaIncome);
        System.out.println("diddely done ?? or not");
    
    }
    public void testJPABank(){
        System.out.println("testJPABank()");
        JPABank jpaBank = new JPABank(2368.54, "2017-10-06");
        persist(jpaBank);
    }
    public void getFixedCosts(){
        System.out.println("getFixedCosts");
        String strQuery ="select ec from JPAEconomy ec where ec.factor > 0";
        TypedQuery<JPAEconomy> query = em.createQuery(strQuery, JPAEconomy.class);
        List<JPAEconomy> objects = query.getResultList();
        for ( JPAEconomy ec: objects){
            System.out.println(ec.toString());
        }
    
    }

    public void run() {
        //jobApplication();
        //this.testJPAIncome();
        this.testJPABank();
        //this.getFixedCosts();
        //this.testJPAEnum();
        //this.testWeekAverage();
       // proTools();
        //this.printProToolsProjects();
        //getKcalSumPerDay();
        //Date date = new java.sql.Date(Calendar.getInstance().getTime().getTime());
        //getFoodItemsForDate(date);
        //restoreFoodItems("c:\\users\\curt\\documents\\2016\\fooditem backup.txt");
        //JPAPersist persist = new JPAPersist();
        //persist.deleteCategory("admin");
        /*
        TodoItem todoItem = findTodoItemById(32L);
        if( todoItem != null){
            System.out.println("strangeness");
        }
        TodoItem todoItem2 = findTodoItemById(302L);
        System.out.println(todoItem2.toString());
        removeTodoItemById(301L);
         */
        //remove(findTodoItemById(406L));
        //setTodoItemStatus(findTodoItemById(409L), true);
        //testBlob();
    }

    public static void main(String[] args) {
        new JPATester().run();
    }
    /**
     * test of average function, JPAEconomy
     */
    private void testWeekAverage(){
        System.out.println("testWeekAverage()");
                  //String jpql_query = "select sum(f.kcal), f.date from FoodItem f group by f.date";

        //java.util.Date weekAgo = new java.util.Date();
        //weekAgo.setTime( weekAgo.getTime() - 1000 * 60 * 24 * 7);
        LocalDate weekAgo = LocalDate.now().minusDays(7);
        String str = weekAgo.toString();
        System.out.println("str: " + str);
        //String query = String.format("select avg (j.amount) from JPAEconomy j where j.date > '%s'", str.toString());
        String query = String.format("select sum (j.amount) / 7 from JPAEconomy j where j.date > '%s'", str.toString());
        Double avg = (Double)em.createQuery(query).getSingleResult();
        System.out.println("average: " + avg);
    }

    private void testBlob() {
        String fileLocation = "C:/Users/User/Documents/link_mobility.pdf";
        JPAJobApplication jobApplication = new JPAJobApplication();
        jobApplication.setCompany(fileLocation);
        //jobApplication.setPdf(fileLocation);
        tx.begin();
        em.persist(jobApplication);
        tx.commit();
    }

    private void getFoodItemsForDate(Date date) {
        date = Date.valueOf("2016-06-20");
        System.out.printf("getFoodItemsForDate(%s)\n", date.toString());
        TypedQuery<FoodItem> query = em.createQuery("select fi from FoodItem fi  where fi.date = :date", FoodItem.class);
        query.setParameter("date", date);
        List<FoodItem> foodItemList = query.getResultList();
        for (FoodItem foodItem : foodItemList) {
            System.out.println(foodItem.toString());
        }
    }

    private void restoreFoodItems(String fileName) {
        File file = new File(fileName);
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                Pattern pattern = Pattern.compile("\\|");
                String[] items = pattern.split(line);
                if (items.length == 5) {
                    //for (int i = 0; i < items.length; i++) {
                        //Date date = new
                        String date = items[2].trim();
                        //String date ="2016-06-29";
                        String description = items[3];
                        Integer kcal = Integer.valueOf(items[4].trim());
                        Date sqldate = java.sql.Date.valueOf(date);
                        System.out.println("date: " + date);
                        System.out.println("description: " + items[3]);
                        System.out.println("kcal " + items[4]);
                        System.out.println("");
                        FoodItem foodItem = new FoodItem(description, kcal, sqldate);
                        JPAPersist jpaPersist = new JPAPersist();
                        jpaPersist.addKcal(description, kcal, sqldate);
                    //}
                }
            }
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }

    private void printProToolsProjects(){
        TypedQuery<ProToolsProject> query = em.createQuery("select p from ProToolsProject p" ,ProToolsProject.class);
        List<ProToolsProject> projects = query.getResultList();
        for ( ProToolsProject project: projects){
            System.out.println( project);
            for ( ProToolsNote note: project.getNotes()){
                System.out.println(note);
            }
        }
    
    }
    private void proTools() {
        ProToolsProject proToolsProject = new ProToolsProject("075_20130506_cma");
        proToolsProject.addNote(new ProToolsNote("listened 2060711"));
        proToolsProject.addNote(new ProToolsNote("instrumental"));
        persist(proToolsProject);
    }

    private void testJPAEnum() {
        System.out.println("testJPAEnum");
        JPAEnumTemp jpaEnum = new JPAEnumTemp("gå på vinden", IsDone.NOTDONE);
        JPAEnumTemp jpaEnum2 = new JPAEnumTemp("enum", IsDone.WIP);
        JPAEnumTemp jpaEnum3 = new JPAEnumTemp("enum", IsDone.DONE);
        persist(jpaEnum);
        persist(jpaEnum2);
        persist(jpaEnum3);
    }
}
