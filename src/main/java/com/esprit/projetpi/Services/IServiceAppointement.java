package com.esprit.projetpi.Services;

import com.esprit.projetpi.Entities.Appointement;

import java.util.List;

public interface IServiceAppointement {

    public Appointement create(Appointement appointement);

    public Appointement update(Appointement appointement);

    public void delete(int id);

    public void deleteByEmail(String email);

    public Appointement getOne(int id);

    public List<Appointement> getAll();

    public List<Appointement> comingAppointement();

    public List<Appointement> searchByEmail(String email);

    public List<Appointement> searchBySubject(String subject);

    public List<Appointement> searchByname(String name);

    public List<Appointement> searchByDate(String date);



}
