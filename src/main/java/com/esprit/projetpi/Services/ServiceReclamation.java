package com.esprit.projetpi.Services;


import com.esprit.projetpi.Entities.Reclamation;
import com.esprit.projetpi.Repositories.ReclamationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ServiceReclamation implements IServiceReclamation {


    private ReclamationRepository reclamationRepository;

    public ServiceReclamation(ReclamationRepository reclamationRepo){
        this.reclamationRepository=reclamationRepo;
    }

    @Override
    public Boolean Create(Reclamation reclamation) {

        if (reclamation != null) {
            reclamation.setDateTime(LocalDateTime.now());
            reclamation.setState(false);
            reclamationRepository.save(reclamation);
            return true;

        }
        return false;
    }

    @Override
    public void Delete(int id) {
        reclamationRepository.deleteById(id);

    }

    @Override
    public boolean treat(int id, String treatement) {
        Reclamation reclamation = reclamationRepository.findById(id).orElse(null);
        if (reclamation != null && !reclamation.getState()) {
            reclamation.setTreatement(treatement);
            reclamation.setState(true);
            reclamationRepository.save(reclamation);

            return true;
        } else {
            return false;
        }
    }

    @Override
    public Reclamation getOne(int id) {
        return reclamationRepository.findById(id).orElse(null);
    }

    @Override
    public List<Reclamation> findAll() {
        return reclamationRepository.findAll(Sort.by(Sort.Direction.DESC, "dateTime"));
    }

    @Override
    public List<Reclamation> findNotTreated() {
        return reclamationRepository.findNotTreated();
    }


    @Override
    public List<Reclamation> filter(String filter) {
        return reclamationRepository.findByFilter(filter);
    }

    @Override
    public List<Reclamation> findBetweenDate(LocalDateTime start, LocalDateTime end) {
        return reclamationRepository.findBetweenDate(start, end);
    }

    @Override
    public List<Reclamation> findByType(String type) {
        return reclamationRepository.findByType(type);
    }


    public List<Reclamation> searchMultiCriteria(String filter, String type, LocalDateTime start, LocalDateTime end, boolean treated) {

        List<Reclamation> reclamations;

        reclamations = findAll();


        if (filter!=null && !filter.isEmpty()) {
            reclamations = reclamations.stream().filter(x -> x.getTitle().toLowerCase().contains(filter.toLowerCase())).collect(Collectors.toList());
        }
        if (type!=null && !type.isEmpty()) {
            reclamations = reclamations.stream().filter(x -> x.getType().toLowerCase().contains(type.toLowerCase())).collect(Collectors.toList());
        }

        if (start != null && end != null) {
            reclamations = reclamations.stream().filter(x -> x.getDateTime().isAfter(start) && x.getDateTime().isBefore(end)).collect(Collectors.toList());
        }
        if (treated) {
            reclamations = reclamations.stream().filter(Reclamation::getState).collect(Collectors.toList());
        }

        return reclamations;


    }
}