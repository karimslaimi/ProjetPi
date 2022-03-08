package com.esprit.projetpi.Repositories;

import com.esprit.projetpi.Entities.Appointement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface AppointementRepository extends JpaRepository<Appointement,Integer> {

    @Query("select a from Appointement a where a.date>=:date")
    public List<Appointement> comingAppointement(@Param("date")LocalDateTime date);

    @Query("select a from Appointement a where a.date=:date")
    public List<Appointement> searchByDate(@Param("date") LocalDate date);

    @Query("select a from Appointement a where a.email=:email")
    public List<Appointement> searchByEmail(@Param("email") String email);

    @Query("select a from Appointement a where a.subject=:subject")
    public List<Appointement> searchBySubejct(@Param("subject") String subject);

    @Query("select a from Appointement a where a.name=:name")
    public List<Appointement> searchByName(@Param("name") String name);

}
