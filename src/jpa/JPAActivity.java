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
public class JPAActivity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String category;

    public Long getId() {
        return id;
    }

    @Override
    public String toString() {
        return "JPAActivity{" + "id=" + id + ", category=" + category + ", startTime=" + startTime + ", seconds=" + seconds + ", comment=" + comment + '}';
    }

    public String getCategory() {
        return category;
    }

    public Timestamp getStartTime() {
        return startTime;
    }

    public long getSeconds() {
        return seconds;
    }

    public String getComment() {
        return comment;
    }
    private Timestamp startTime;
    private long seconds;
    private String comment;
    public JPAActivity(){}
    public JPAActivity(String category, LocalDateTime start, long secs, String comment){
        this.category = category;
        this.seconds = secs;
        this.startTime = Timestamp.valueOf(start);
        this.comment = comment;
    }
}
