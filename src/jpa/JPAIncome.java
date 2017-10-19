/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa;

import java.io.Serializable;
import java.sql.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author curt
 */
@Entity
public class JPAIncome implements Serializable {

    public JPAIncome() {
    }

    private static final long serialVersionUID = 1L;

    public JPAIncome(String description, Double amount, Date date) {
        this.description = description;
        this.amount = amount;
        this.date = date;
    }
    /**
     * 
     * @param description
     * @param amount kronoer
     * @param date, "yyyy-mm-dd" 
     */
    public JPAIncome(String description, Double amount, String stringDate) {
        this.description = description;
        this.amount = amount;
        this.date = Date.valueOf(stringDate);
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String description;
    private Double amount;
    private Date date;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof JPAIncome)) {
            return false;
        }
        JPAIncome other = (JPAIncome) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "jpa.JPAIncome[ id=" + id + " ]";
    }
    
}
