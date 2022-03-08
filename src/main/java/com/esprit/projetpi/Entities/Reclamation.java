package com.esprit.projetpi.Entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
public class Reclamation implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;


    private String type;

    private String title;

    private String explication;
    private LocalDateTime dateTime;
    private String treatement;
    private Boolean state;//by default false when it will be treated it will be true



    public Reclamation(int id, String type, String title, String explication, LocalDateTime dateTime, String treatement, Boolean state) {
        this.id = id;
        this.type = type;
        this.title = title;
        this.explication = explication;
        this.dateTime = dateTime;
        this.treatement = treatement;
        this.state = state;
    }

    public Reclamation(String type, String title, String explication, LocalDateTime dateTime) {
        this.type=type;
        this.explication=explication;
        this.title=title;
        this.dateTime=dateTime;
    }
    public Reclamation(String type, String title,String explication) {
        this.type=type;
        this.explication=explication;
        this.title=title;

        this.treatement="";
        this.state=false;
        this.dateTime= LocalDateTime.now();
    }

    public Reclamation() {
    }

    public int getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public String getTitle() {
        return title;
    }

    public String getExplication() {
        return explication;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }


    public void setId(int id) {
        this.id = id;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setExplication(String explication) {
        this.explication = explication;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }


    public String getTreatement() {
        return treatement;
    }

    public void setTreatement(String treatement) {
        this.treatement = treatement;
    }

    public Boolean getState() {
        return state;
    }

    public void setState(Boolean state) {
        this.state = state;
    }

}