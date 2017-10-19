/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa;

import java.io.Serializable;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 *
 * @author User
 */
@Entity
@Table(name="todo")
public class TodoItem implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    public String getDescription() {
        return description;
    }

    
    private static final long serialVersionUID = 1L;
    private String description;
    //private LocalDateTime dateAdded;//not supported
    private Timestamp date_added;
    private boolean is_done = false;

    public boolean isIs_done() {
        return is_done;
    }

    public Timestamp getDate_added() {
        return date_added;
    }
    
    //@Transient
    //private long secs;
    //@Transient
    //private String comment;

    
    
    public TodoItem(){
        this.description ="";
    }
    public TodoItem(String description){
        this.description = description;    
        this.date_added = Timestamp.valueOf(LocalDateTime.now());
    }
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
        if (!(object instanceof TodoItem)) {
            return false;
        }
        TodoItem other = (TodoItem) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "activity.TodoItem[ id=" + id + ", description: " + description + ",isdone:" + is_done + "]";
    }

    public void setIs_done(boolean is_done) {
        this.is_done = is_done;
    }
    
}
