/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa;

import activity.IPersist;
import activity.panel.FactorChoice;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.time.LocalDateTime;
import static java.time.temporal.ChronoUnit.DAYS;
import java.util.Calendar;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
//import javax.persistence.LockModeType;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 *
 * @author User
 */
public class JPAPersist implements IPersist {

    private static final boolean DEBUG = true;
    private EntityManagerFactory emf = javax.persistence.Persistence.createEntityManagerFactory("activityPU");
    private EntityManager em = emf.createEntityManager();
    private EntityTransaction tx = em.getTransaction();

    @Override
    public void insert(String category, LocalDateTime start, long secs, String comment) {
        System.out.println("JPAPersist.insert");
        JPAActivity activity = new JPAActivity(category, start, secs, comment);
        System.out.println("\t" + activity.toString());
        tx.begin();
        em.persist(activity);
        tx.commit();
    }

    @Override
    public void saveUrl(String urlx, String comment) {
        URL url = new URL(urlx, comment);
        tx.begin();
        em.persist(url);
        tx.commit();
    }

    @Override
    public void addTodo(TodoItem todoItem) {
        System.out.println("JPAPersist.addTodo(TodoItem)\n\t" + todoItem.toString());

        tx.begin();
        em.persist(todoItem);
        tx.commit();
        System.out.println("diddilidone");
    }

    @Override
    public void markTodoItemDone(Long id) {
        System.out.println("JPAPersist.markTodoItemDone");
        TodoItem todoItem = em.find(TodoItem.class, id);
        tx.begin();
        todoItem.setIs_done(true);
        tx.commit();
    }

    public Object[][] getTodoItems() {
        final int COLUMN_COUNT = 4;
        List<TodoItem> items = em.createQuery("SELECT t FROM TodoItem t where t.is_done = false order by t.date_added desc", TodoItem.class).getResultList();
        int rows = items.size();
        Object[][] data = new Object[rows][COLUMN_COUNT];
        int row = 0;
        for (TodoItem todoItem : items) {
            em.refresh(todoItem);
            data[row][0] = todoItem.getId();
            data[row][1] = todoItem.getDescription();
            //if (DEBUG)System.err.println(todoItem.getDescription());
            data[row][2] = todoItem.isIs_done();
            data[row][3] = todoItem.getDate_added();
            row++;
        }
        return data;
    }

    @Override
    public void addNote(String heading, String text) {
        System.out.println("JPAPersist.addNote");
        tx.begin();
        em.persist(new JPANote(heading, text));
        tx.commit();
    }

    public JPAPersist() {
    }

    @Override
    public void updateTodo(long id, String description, boolean isdone) {
        System.out.println("JPAPersist.updateTodo()" + description);
        Query query = em.createQuery("update TodoItem ti set ti.description = ?1, ti.is_done = ?2 where ti.id = ?3");
        query.setParameter(1, description);
        query.setParameter(2, isdone);
        query.setParameter(3, id);
        tx.begin();
        int updates = query.executeUpdate();
        //System.out.println("number of updates: " + updates);
        tx.commit();
    }

    @Override
    public void deleteItem(Long id) {
        System.out.format("JPAPersist.deleteItem(%d)\n", id);
        TodoItem todoItem = em.find(TodoItem.class, id);
        em.getTransaction().begin();
        em.remove(todoItem);
        em.getTransaction().commit();
    }

    public void persist(Object object) {
        tx.begin();
        em.persist(object);
        tx.commit();

    }

    @Override
    public void addNewCategory(String name) {
        persist(new JPACategory(name));
    }

    @Override
    public String[] getCategories() {
        List<JPACategory> categories = em.createQuery("SELECT j FROM JPACategory j order by j.name", JPACategory.class).getResultList();
        String[] res = new String[categories.size()];
        for (int i = 0; i < categories.size(); i++) {
            res[i] = categories.get(i).getName();
        }
        return res;
    }

    @Override
    public void deleteCategory(String name) {
        System.out.format("JPAPersist.deleteCategory(%s)", name);
        String query = String.format("SELECT jpac FROM JPACategory jpac where jpac.name = '%s'", name);
        List<JPACategory> items = em.createQuery(query, JPACategory.class).getResultList();
        System.out.println("items: " + items.size());
        if (items.size() == 1) {
            tx.begin();
            em.remove(items.get(0));
            tx.commit();
        } else {
            System.out.println("too many categories");
        }
    }

    @Override
    public void addKcal(String description, int kcal, Date date) {
        if (date != null) {
            System.out.printf("JPAPersist.addKcal(%s, %d, %s)\n", description, kcal, date.toString());
        } else {
            System.out.printf("JPAPersist.addKcal(%s, %d)\n", description, kcal);
        }
        java.sql.Date datesql = new java.sql.Date(date.getTime());
        tx.begin();
        em.persist(new FoodItem(description, kcal, datesql));
        tx.commit();
    }

    @Override
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

    @Override
    public Long getKcalSum(java.util.Date date) {
        System.out.printf("getKcalSum(%s)\n", date.toString());
        String jpql_query = "select sum(f.kcal) from FoodItem f where f.date = :date";
        TypedQuery<Long> query = em.createQuery(jpql_query, Long.class);
        query.setParameter("date", date);
        Long sum = query.getSingleResult();
        System.out.println("sum: " + sum);
        return sum;
    }

    @Override
    public void addRun(int mins, Date date) {
        double kcal = 700.0 / 60.0 * mins * -1;
        addKcal("springrunda", (int) kcal, date);
    }

    @Override
    public Object[][] getSumLastDays(int days) {

        Query query = em.createQuery("select f.id, sum(f.kcal), f.date from FoodItem f group by f.date order by f.date desc");
        List<Object[]> result = query.getResultList();
        Object[][] data = new Object[result.size()][3];
        for (int i = 0; i < result.size(); i++) {
            data[i][0] = result.get(i)[0];
            data[i][1] = result.get(i)[1];
            data[i][2] = result.get(i)[2];
        }
        return data;
    }

    public Object[][] getSumLastDays_old(int days) {

        Query query = em.createQuery("select sum(f.kcal) from FoodItem f group by f.date");
        List res = query.getResultList();
        Object[][] data = new Object[res.size()][1];
        for (int i = 0; i < res.size(); i++) {
            data[i][0] = res.get(i);
        }
        return data;
    }

    /*
    public static void main(String[] args){
        System.out.println("main");
        new JPAPersist().getSumLastDays(0);
    }*/
    @Override
    public Object[][] getFoodItems4Date(Date date) {
        //date = java.sql.Date.valueOf("2016-06-20");
        Object[][] data = null;
        if (DEBUG) {
            System.out.printf("getFoodItemsForDate(%s)\n", date.toString());
        }
        TypedQuery<FoodItem> query = em.createQuery("select fi from FoodItem fi  where fi.date = :date", FoodItem.class);
        query.setParameter("date", date);
        List<FoodItem> foodItemList = query.getResultList();
        data = new Object[foodItemList.size()][2];
        if (DEBUG) {
            System.out.println("\tnumber of rows: " + data.length);
        }
        for (int i = 0; i < foodItemList.size(); i++) {
            data[i][0] = foodItemList.get(i).getDescription();
            data[i][1] = foodItemList.get(i).getKcal();
        }
        return data;
    }

    @Override
    public void addEconomy(String description, double amount, Date date) {
        System.out.println("addEconomy()" + description + " " + amount);
        persist(new JPAEconomy(description, amount, new java.sql.Date(date.getTime())));
    }

    @Override
    public Object[][] getFoodItems4ID(long id) {
        return null;
    }

    @Override
    public void addApplication(String heading, String description, Date lastApplicationDate) {
        persist(new JPAJobApplication(heading, description, new java.sql.Date(lastApplicationDate.getTime())));
    }

    @Override
    public Object[][] getApplications() {
        Object[][] data = null;
        List<JPAJobApplication> applications = em.createQuery("SELECT j FROM JPAJobApplication j", JPAJobApplication.class).getResultList();
        data = new Object[applications.size()][2];
        for (int i = 0; i < applications.size(); i++) {
            data[i][0] = applications.get(i).getId();
            data[i][1] = applications.get(i).getCompany();
        }
        return data;
    }

    @Override
    public Object[][] getApplicationNotes(long id) {
        System.out.println("getApplicationNotes: " + id);
        JPAJobApplication jpaJobApplication = em.find(JPAJobApplication.class, id);
        if (jpaJobApplication == null) {
            return null;
        }
        System.out.println(jpaJobApplication);
        List<JPAJobApplicationNote> notes = jpaJobApplication.getNotes();
        if (notes == null) {
            return null;
        }
        Object[][] data = new Object[notes.size()][2];
        for (int i = 0; i < notes.size(); i++) {
            data[i][0] = notes.get(i).getId();
            data[i][1] = notes.get(i).getNote();
        }
        return data;
    }

    @Override
    public Object[][] getProToolsProjects() {
        TypedQuery<ProToolsProject> query = em.createQuery("select pt from ProToolsProject pt order by pt.proToolsId", ProToolsProject.class);
        List<ProToolsProject> result = query.getResultList();
        Object[][] data = new Object[result.size()][3];
        for (int i = 0; i < result.size(); i++) {
            data[i][0] = result.get(i).getId();
            data[i][1] = result.get(i).getName();
            data[i][2] =  result.get(i).getProToolsId();
                    
        }
        return data;
    }

    @Override
    public Object[][] getProToolsNotes(Long id) {
        System.out.println("JPAPersist.getProToolsNotes()" + id);
        ProToolsProject proToolsProject = em.find(ProToolsProject.class, id);
        if ( proToolsProject == null){
            System.out.println("pro tools project not found" +  id);
            return null;
        }
        Object[][] data = new Object[proToolsProject.getNotes().size()][4];
        for ( int i = 0; i< proToolsProject.getNotes().size(); i++){
            data[i][0] = proToolsProject.getProToolsId();
            data[i][1] = proToolsProject.getNotes().get(i).getDate();
            data[i][2] =  proToolsProject.getNotes().get(i).getId();
            data[i][3] = proToolsProject.getNotes().get(i).getNote();
        }
        return data;        
    }

    @Override
    public ProToolsProject addProToolsProject(String proToolsId, String proToolsName, List<ProToolsNote> notes) {
        long ptId = Long.parseLong(proToolsId);
        ProToolsProject proToolsProject = new ProToolsProject(ptId, proToolsName, notes);
        persist(proToolsProject);
        return proToolsProject;
    }

    @Override
    public ProToolsProject addProToolsNote(Long proToolsId, String note, Date date) {
        System.out.println("JPAPersist.addProToolsNote");
        ProToolsProject proToolsProject = em.find(ProToolsProject.class, proToolsId);
        if ( proToolsProject == null){
            System.out.println("proToolsProject not found ");
            return null;
        }
        proToolsProject.addNote(new ProToolsNote(note, new java.sql.Date(date.getTime())));
        persist(proToolsProject);
        return proToolsProject;
    }

    @Override
    public void addWeight(float weight, Date date) {
        System.out.println("JPAPersist.addWeight"); 
        JPAWeight jpaWeight = new JPAWeight(weight);
        persist( jpaWeight);
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Object[][] getWeight() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Double getWeekAverage() {
        System.out.println("JPAPersist.getWeekAverage()");
        LocalDate weekAgo = LocalDate.now().minusDays(6);
        LocalDate today = LocalDate.now();
        Double average = getAverageFromTo(weekAgo, today, FactorChoice.ALL);
        //System.out.println("average: " + average);
        return average;
    }

    @Override
    public Double getAverageFromTo(LocalDate firstDate, LocalDate lastDate, FactorChoice factorChoice) {
        System.out.println(String.format("JPAPersist.getAverageFromTo(%s, %s)", firstDate.toString(), lastDate.toString()));
        System.out.println("\tFactorChoice: " + factorChoice);
        long nDays = DAYS.between(firstDate, lastDate) + 1; 
        System.out.println("\tnumber of days:" + nDays);
        if (nDays == 0){
            System.out.println("division by 0 not allowed...and you know that");
            return 0.0;
        }
        if ( firstDate.isAfter(lastDate)){
            System.out.println("first date must be before second date");
            return 0.0;
        }
        String query = String.format("select sum (j.amount) / %d  from JPAEconomy j where j.date >= '%s' AND j.date <= '%s'",nDays,  firstDate.toString(), lastDate.toString());
        if ( factorChoice == FactorChoice.VARIABLE){
            query += " and j.factor = 0";
        }
        System.out.println("\tquery: " + query);
        Double average = (Double)em.createQuery(query).getSingleResult();
        System.out.println("...average is: " +  average);
        return average;
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Object[][] getEconomy(int nDays) {
        System.out.println(String.format("JPAPersist.getEconomy(%d)", nDays));
        TypedQuery<JPAEconomy> query = em.createQuery("select ec from JPAEconomy ec where ec.date = '2017-09-20'", JPAEconomy.class);
        //TypedQuery<JPAEconomy> query = em.createQuery("selcect ec from JPAEconomy ec where ec.date >=  and ec.date <= ");
        System.out.println("after");
        List<JPAEconomy> result = query.getResultList();
        Object[][] data = new Object[result.size()][4];
        for (int i = 0; i < result.size(); i++) {
            data[i][0] = result.get(i).getId();
            data[i][1] = result.get(i).getAmount();
            data[i][2] =  result.get(i).getDescription();
            data[i][3] = result.get(i).getDate();
                    
        }
        return data;
    }

    @Override
    public Object[][] getEconomy(LocalDate firstDate, LocalDate lastDate) {
        System.out.println(String.format("JPAPersist.getEconommy(%s, %s)", firstDate.toString(), lastDate.toString()));
        String stringQuery = String.format("select ec from JPAEconomy ec where ec.date >= '%s' and ec.date <= '%s' order by ec.date desc",firstDate.toString(), lastDate.toString());
        //TypedQuery<JPAEconomy> query = em.createQuery("select ec from JPAEconomy ec where ec.date >= '2017-09-20'", JPAEconomy.class);
        TypedQuery<JPAEconomy> query = em.createQuery(stringQuery, JPAEconomy.class);
        //System.out.println("after");
        List<JPAEconomy> result = query.getResultList();
        Object[][] data = new Object[result.size()][4];
        for (int i = 0; i < result.size(); i++) {
            data[i][0] = result.get(i).getId();
            data[i][1] = result.get(i).getAmount();
            data[i][2] =  result.get(i).getDescription();
            data[i][3] = result.get(i).getDate();
                    
        }
        return data;
    }

    @Override
    public Double getMonthAverage() {
        System.out.println("JPAPersist.getMontAverage()");
        LocalDate monthAgo = LocalDate.now().minusDays(29);
        LocalDate today = LocalDate.now();
        Double average = getAverageFromTo(monthAgo, today, FactorChoice.ALL);
        return average;
    }

    @Override
    public Double getBankBalance() {
        String stringQuery = "select  bank from JPABank bank order by bank.id desc";
        TypedQuery<JPABank> query = em.createQuery(stringQuery, JPABank.class);
        query.setMaxResults(1);
        JPABank  bank = query.getSingleResult();
        return bank.getAmount();
    }

    @Override
    public void add2BankAccount(LocalDate date, Double amount) {
        System.out.println(String.format("add2BankAccount(%s, %f ", date.toString(), amount));
        JPABank jpaBank = new JPABank(amount, date.toString());
        persist(jpaBank);
    }
}
