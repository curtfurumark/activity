/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 *
 * @author curt
 */
@Entity
public class ProToolsProject implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Long proToolsId;
    private String name;
     @OneToMany(cascade= CascadeType.PERSIST)
    private List<ProToolsNote> notes = new ArrayList<>();
    //private String proToolsId;

    public Long getProToolsId() {
        return proToolsId;
    }

    public void setProToolsId(Long proToolsId) {
        this.proToolsId = proToolsId;
    }

    
    public ProToolsProject() {
    }

    public ProToolsProject(String name) {
        this.name = name;
    }

    ProToolsProject(Long proToolsId, String proToolsName, List<ProToolsNote> notes) {
        this.proToolsId = proToolsId;
        this.name = proToolsName;
        this.notes = notes;
    }

    
    public List<ProToolsNote> getNotes() {
        return notes;
    }

    public void setNotes(List<ProToolsNote> notes) {
        this.notes = notes;
    }
    public void addNote(ProToolsNote proToolsNote){
        notes.add(proToolsNote);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
        if (!(object instanceof ProToolsProject)) {
            return false;
        }
        ProToolsProject other = (ProToolsProject) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ProToolsProject{" + "id=" + id + ", name=" + name + ", notes=" + notes + '}';
    }

    public Vector getRow(int i) {
        Vector vector = new Vector();
        vector.add(notes.get(i).getId());
        vector.add(notes.get(i).getNote());
        return vector;
    }


}
