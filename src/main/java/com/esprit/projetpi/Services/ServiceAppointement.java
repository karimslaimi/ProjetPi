package com.esprit.projetpi.Services;

import com.esprit.projetpi.Entities.Appointement;
import com.esprit.projetpi.Repositories.AppointementRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class ServiceAppointement implements IServiceAppointement {

    private ServiceMessages serviceMessages;
    private AppointementRepository appointementRepository;

    public ServiceAppointement(AppointementRepository appropointementRepository,ServiceMessages serviceMessages){
        this.appointementRepository=appropointementRepository;
        this.serviceMessages=serviceMessages;
    }


    @Override
    public Appointement create(Appointement appointement) {
        if(appointement!=null){
            appointement=appointementRepository.save(appointement);
            this.serviceMessages.sendMail("You have an appointement","You have an appointement relative to the subject: "+appointement.getSubject(),appointement.getEmail());
            return appointement;
        }
        return null;
    }

    @Override
    public Appointement update(Appointement appointement) {
        if(appointement!=null && appointement.getId()!=0){
            return appointementRepository.save(appointement);
        }
        return null;
    }

    @Override
    public void delete(int id) {

        appointementRepository.deleteById(id);
    }

    @Override
    public void deleteByEmail(String email) {
        //search by email return a list then find the first one and if it s present delete it
        appointementRepository.searchByEmail(email).stream().findFirst().ifPresent(app -> appointementRepository.delete(app));
    }

    @Override
    public Appointement getOne(int id) {
        return appointementRepository.findById(id).orElse(null);
    }

    @Override
    public List<Appointement> getAll() {
        return appointementRepository.findAll();
    }

    @Override
    public List<Appointement> comingAppointement() {
        LocalDateTime now = LocalDateTime.now();

        return appointementRepository.comingAppointement(now);
    }

    @Override
    public List<Appointement> searchByEmail(String email) {
        return appointementRepository.searchByEmail(email);
    }

    @Override
    public List<Appointement> searchBySubject(String subject) {

        return appointementRepository.searchBySubejct(subject);

    }

    @Override
    public List<Appointement> searchByname(String name) {
        return appointementRepository.searchByName(name);
    }

    @Override
    public List<Appointement> searchByDate(String date) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate dateTime = LocalDate.parse(date, formatter);

        return appointementRepository.searchByDate(dateTime);
    }
}
