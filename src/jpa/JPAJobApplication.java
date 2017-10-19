/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;

/**
 *
 * @author User
 */
@Entity
public class JPAJobApplication implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String company;
    @OneToMany(cascade= CascadeType.PERSIST)
    private List<JPAJobApplicationNote> notes = new ArrayList<>();
    @Lob
    //private byte[] pdf;
    private String heading;
    private String description;
    private Date lastApplicationDate;

    public JPAJobApplication() {
    }
    public JPAJobApplication(String company){
        this.company = company;
    }
    public void addNote(JPAJobApplicationNote note){
        this.notes.add(note);
    }
  
    JPAJobApplication(String heading, String description, Date lastApplicationDate) {
        this.heading = heading;
        this.description = description;
        this.lastApplicationDate = lastApplicationDate;
    }

    public List<JPAJobApplicationNote> getNotes() {
        return notes;
    }

    public void setNotes(List<JPAJobApplicationNote> notes) {
        this.notes = notes;
    }

    public String getHeading() {
        return heading;
    }

    public void setHeading(String heading) {
        this.heading = heading;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getLastApplicationDate() {
        return lastApplicationDate;
    }

    public void setLastApplicationDate(Date lastApplicationDate) {
        this.lastApplicationDate = lastApplicationDate;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }
/*
    public byte[] getPdf() {
        return pdf;
    }
*/
    /*
    public void setPdf(byte[] pdf) {
        this.pdf = pdf;
    }
*/
    /*
    public void setPdf(String fileLocation){
        pdf = fileToByteArray(fileLocation);
    }
    */
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
        if (!(object instanceof JPAJobApplication)) {
            return false;
        }
        JPAJobApplication other = (JPAJobApplication) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }
    private static byte[] writtingImage(String fileLocation) {
        FileInputStream fileInputStream = null;
        try {
            System.out.println("file lication is"+fileLocation);
            fileInputStream = new FileInputStream(fileLocation);
            //fileInputStream.read(b)
        } catch (FileNotFoundException ex) {
            Logger.getLogger(JPAJobApplication.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                fileInputStream.close();
            } catch (IOException ex) {
                System.out.println(ex);
            }
        }
        return null;
    }
    private static byte[] fileToByteArray(String fileLocation){
        byte[] data = null;
        try {
            Path path = Paths.get(fileLocation);
            data = Files.readAllBytes(path);
        } catch (IOException ex) {
            Logger.getLogger(JPAJobApplication.class.getName()).log(Level.SEVERE, null, ex);
        }
        return data;
    }

    @Override
    public String toString() {
        return "jpa.JPAJobApplication[ id=" + id + " ]";
    }

    
}
