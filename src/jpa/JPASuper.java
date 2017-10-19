/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

/**
 *
 * @author User
 */
public class JPASuper {
    protected EntityManagerFactory emf = javax.persistence.Persistence.createEntityManagerFactory("activityPU");
    protected EntityManager em = emf.createEntityManager();
    protected EntityTransaction  tx = em.getTransaction();
    protected void persist(Object object){
        System.out.println("JPASuper.persist");
        tx.begin();
        em.persist(object);
        tx.commit();
    }
}
