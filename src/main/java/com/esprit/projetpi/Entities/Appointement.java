package com.esprit.projetpi.Entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.time.LocalDateTime;


@Entity
public class Appointement implements Serializable {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String name;

    private String email;

    private String subject;

    private LocalDateTime date;

    public Appointement() {
    }

    public Appointement(String name, String with, String type) {
        this.name = name;
        this.email = with;
        this.subject = type;

    }

    public Appointement(int id, String name, String with, String type, LocalDateTime date) {
        this.id = id;
        this.name = name;
        this.email = with;
        this.subject = type;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String with) {
        this.email = with;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String type) {
        this.subject = type;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
