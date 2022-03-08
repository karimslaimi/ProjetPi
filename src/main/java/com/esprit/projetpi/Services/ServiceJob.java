package com.esprit.projetpi.Services;

import com.esprit.projetpi.Entities.Job;
import com.esprit.projetpi.Repositories.JobRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceJob implements IServiceJob {

    private JobRepository jobRepository;
    private ServiceMessages serviceMessages;

    public ServiceJob(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    @Override
    public Job create(Job job) {
        if (job == null) {
            return null;
        }
        job.setActive(true);
        return jobRepository.save(job);
    }

    @Override
    public Job update(Job job) {
        if (job == null || job.getId() == 0) {
            return null;
        }
        return jobRepository.save(job);
    }

    @Override
    public void delete(int id) {
        jobRepository.deleteById(id);
    }

    @Override
    public Job getOne(int id) {
        return jobRepository.findById(id).orElse(null);
    }

    @Override
    public List<Job> getAll() {
        return jobRepository.findAll();
    }

    @Override
    public List<Job> search(String filter) {
        return jobRepository.search(filter);
    }

    @Override
    public List<Job> activeJobs() {
        return jobRepository.activeJobs();
    }

    @Override
    public Job closeJob(int id) {
        Job job=jobRepository.findById(id).orElse(null);
        if(job!=null){
        job.setActive(false);
        jobRepository.save(job);
        return job;
        }
        return null;
    }
}
