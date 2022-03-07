package com.esprit.projetpi.Services;

import com.esprit.projetpi.Entities.Candidature;

import java.time.LocalDateTime;
import java.util.List;

public interface IServiceCandidature {

    public Candidature postC(Candidature candidature,int jobId);
    public List<Candidature>jobCList(int id);
    public List<Candidature>allCandidature();
    public List<Candidature>filterByState(String filter);
    public Candidature addInterview(int idc, String date);
    public List<Candidature> listInterview(int idj);
    public Candidature updateCandidate(int idc,String state);
    public List<Candidature> nextInterview();
}
