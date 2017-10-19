/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author User
 */
@Entity
public class JPANote {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String note;
    private String heading;
    private Timestamp timestamp;
    public JPANote(){
    
    }

    public String getHeading() {
        return heading;
    }

    public void setHeading(String heading) {
        this.heading = heading;
    }

    public Long getId() {
        return id;
    }

    public String getNote() {
        return note;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }
    public JPANote(String heading, String note){
        this.note = note;
        this.heading = heading;
        this.timestamp = Timestamp.valueOf(LocalDateTime.now());
    }
    
    
}
