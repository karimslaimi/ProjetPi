package com.esprit.projetpi.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
public class Candidature implements Serializable {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String phone;
    private String email;
    private String name;
    private String lm;//lettre de motivation
    private String cv;//fichier à joindre plus tard
    private LocalDateTime date;

    private LocalDateTime entretien;

    private String state;//accepted/refused/ on hold

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "job_id")
    private Job job;


    public Candidature() {
    }

    public Candidature(int id, String name, String lm, String cv, LocalDateTime date) {
        this.id = id;
        this.name = name;
        this.lm = lm;
        this.cv = cv;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLm() {
        return lm;
    }

    public void setLm(String lm) {
        this.lm = lm;
    }

    public String getCv() {
        return cv;
    }

    public void setCv(String cv) {
        this.cv = cv;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public Job getJob() {
        return job;
    }

    public void setJob(Job job) {
        this.job = job;
    }

    public LocalDateTime getEntretient() {
        return entretien;
    }

    public void setEntretien(LocalDateTime entretien) {
        this.entretien = entretien;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
