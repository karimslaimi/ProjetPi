package com.esprit.projetpi.Services;

import com.esprit.projetpi.Entities.Candidature;
import com.esprit.projetpi.Entities.Job;
import com.esprit.projetpi.Repositories.CandidatureRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class ServiceCandidature implements IServiceCandidature {

    private CandidatureRepository candidatureRepository;
    private ServiceJob serviceJob;

    public ServiceCandidature(CandidatureRepository candidatureRepository, ServiceJob serviceJob){
        this.candidatureRepository=candidatureRepository;
        this.serviceJob=serviceJob;
    }


    @Override
    public Candidature postC(Candidature candidature, int jobId) {

        if(jobId==0){
           return null;
        }
        candidature.setDate(LocalDateTime.now());
        Job job=serviceJob.getOne(jobId);
        candidature.setJob(job);
        return candidatureRepository.save(candidature);

    }

    @Override
    public List<Candidature> jobCList(int id) {
        return candidatureRepository.getByJob(id);
    }

    @Override
    public List<Candidature> allCandidature() {
        return candidatureRepository.findAll();
    }

    @Override
    public List<Candidature> filterByState(String filter) {
        return candidatureRepository.filterByState(filter);
    }

    @Override
    public Candidature addInterview(int idc, String date) {
        Candidature candidature=candidatureRepository.findById(idc).orElse(null);
        if(candidature==null){
        return null;
        }else{

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");//25-07-2021 15:22
            LocalDateTime dateTime = LocalDateTime.parse(date, formatter);
            candidature.setEntretien(dateTime);
            return candidatureRepository.save(candidature);
        }
    }

    @Override
    public List<Candidature> listInterview(int idc) {
        return candidatureRepository.listInterview(idc);
    }

    @Override
    public Candidature updateCandidate(int idc, String state) {
        Candidature candidature=candidatureRepository.findById(idc).orElse(null);
        if(candidature==null){
            return null;
        }else{
            candidature.setState(state);
            return candidatureRepository.save(candidature);
        }
    }

    @Override
    public List<Candidature> nextInterview() {
        LocalDateTime date=LocalDateTime.now();
        return candidatureRepository.nextInterviw(date);
    }
}
