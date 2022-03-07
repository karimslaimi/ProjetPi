package com.esprit.projetpi.Repositories;

import com.esprit.projetpi.Entities.Reclamation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface ReclamationRepository extends JpaRepository<Reclamation,Integer> {

    @Query("select  r from Reclamation r where r.state=false")
    public List<Reclamation> findNotTreated();


    @Query("select r from Reclamation r where lower(r.title) like lower(concat('%', :filter,'%'))")
    public List<Reclamation> findByFilter(@Param("filter") String filter);

    @Query("select r from Reclamation r where r.dateTime>=:start and r.dateTime<=:end")
    public List<Reclamation> findBetweenDate(@Param("start") LocalDateTime start, @Param("end") LocalDateTime end);

    @Query("select r from Reclamation r where lower(r.type) like lower(concat('%', :type,'%'))")
    public List<Reclamation> findByType(@Param("type") String type);
}
