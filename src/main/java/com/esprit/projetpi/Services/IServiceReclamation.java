package com.esprit.projetpi.Services;


import com.esprit.projetpi.Entities.Reclamation;

import java.time.LocalDateTime;
import java.util.List;

public interface IServiceReclamation {

    public Boolean Create(Reclamation reclamation);
    public void Delete(int id);
    public boolean treat(int id, String treatement);
    public Reclamation getOne(int id);
    public List<Reclamation> findAll();
    public List<Reclamation> findNotTreated();
    public List<Reclamation> filter(String filter);
    public List<Reclamation> findBetweenDate(LocalDateTime start, LocalDateTime end);
    public List<Reclamation> findByType(String type);
    public List<Reclamation> searchMultiCriteria(String filter, String type,  LocalDateTime start, LocalDateTime end,boolean treated );
}
