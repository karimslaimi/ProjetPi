package com.esprit.projetpi.Services;

import com.esprit.projetpi.Entities.Job;

import java.util.List;

public interface IServiceJob {

    public Job create(Job job);

    public Job update(Job job);

    public void delete(int id);

    public Job getOne(int id);

    public List<Job> getAll();

    public List<Job> search(String filter);

    public List<Job> activeJobs();

    public Job closeJob(int id);

}
