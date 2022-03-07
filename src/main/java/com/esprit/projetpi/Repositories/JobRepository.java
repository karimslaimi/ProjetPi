package com.esprit.projetpi.Repositories;

import com.esprit.projetpi.Entities.Job;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JobRepository extends JpaRepository<Job,Integer> {

    @Query("select j from Job j where j.title like %:filter% or j.description like %:filter% or j.category like %:filter% or j.skills like %:filter% ")
    public List<Job> search(@Param("filter") String filter);

    @Query("select j from Job j where j.active=true ")
    public List<Job> activeJobs();

}
