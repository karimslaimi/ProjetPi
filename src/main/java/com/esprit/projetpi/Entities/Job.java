package com.esprit.projetpi.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
public class Job implements Serializable {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String title;
    private String description;
    private String category;
    private String skills;
    private Date posted;
    private float salary;
    private boolean active;

    @JsonIgnore
    @OneToMany
    private List<Candidature> candidatures;


    public Job() {
    }

    public Job(int id, String title, String description, String category, String skills, Date posted, float salary) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.category = category;
        this.skills = skills;
        this.posted = posted;
        this.salary = salary;
    }

    public Job(String title, String description, Date posted, float salary) {
        this.title = title;
        this.description = description;
        this.posted = posted;
        this.salary = salary;
    }

    public Job(String title, String description, float salary) {
        this.title = title;
        this.description = description;
        this.salary = salary;
    }

    public Job(int id, String title, String description, Date posted, float salary) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.posted = posted;
        this.salary = salary;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getPosted() {
        return posted;
    }

    public void setPosted(Date posted) {
        this.posted = posted;
    }

    public float getSalary() {
        return salary;
    }

    public void setSalary(float salary) {
        this.salary = salary;
    }

    public List<Candidature> getCandidatures() {
        return candidatures;
    }

    public void setCandidatures(List<Candidature> candidatures) {
        this.candidatures = candidatures;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getSkills() {
        return skills;
    }

    public void setSkills(String skills) {
        this.skills = skills;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
