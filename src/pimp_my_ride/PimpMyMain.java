/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pimp_my_ride;

import jpa.JPASuper;
import java.util.List;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 *
 * @author User
 */
public class PimpMyMain extends JPASuper{
    public void createMechanic(String name, int salary){
        System.out.println("PimpMyMain.createMechanic()");
        persist(new Mechanic(name, salary));
    }
    public void createSalesman(String name, int salary){
        System.out.println("createSalesman " + name);
        tx.begin();
        em.persist(new Salesman(name, salary));
        tx.commit();
    }
    /**
     * create staff of pimp my ride unlimited
     */
    private void createStaff(){
        System.out.println("PimpMyMain.createStaff()");
        createSalesman("miles davies", 5678);
        createMechanic("cannonball adderly", 5432);
        createMechanic("john coltrane", 2345);
        createMechanic("bill evans", 2345);
        createMechanic("paul chambers", 8989);
        createMechanic("jimmy cobb", 4567);
    }
    public List<Employee> getStaff(){
        TypedQuery<Employee> query =  em.createQuery("select e from Employee e", Employee.class);
        List<Employee> employees = query.getResultList();
        for ( Employee employee: employees){
            System.out.println(employee.toString());
        }
        return employees;
    }
    public static void main(String[] args) {
        System.out.println("PimpMyMain.main()");
        PimpMyMain pimpMyMain = new PimpMyMain();
        pimpMyMain.getStaff();

        
    }
  

    public static void createPMRUnlimited() {
        System.out.println("createPMRUnlimited");
        //CarProject carProject = new CarProject();
        //carProject.setSalesman(new Salesman("duke ellington"));
        //carProject.addMechanic(new Mechanic("miles"));
    }
            
}
