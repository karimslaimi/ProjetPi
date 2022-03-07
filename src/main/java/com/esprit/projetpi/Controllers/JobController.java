package com.esprit.projetpi.Controllers;

import com.esprit.projetpi.Entities.Appointement;
import com.esprit.projetpi.Entities.Job;
import com.esprit.projetpi.Services.IServiceJob;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("job")
public class JobController {

    private IServiceJob serviceJob;

    public JobController(IServiceJob serviceJob){
        this.serviceJob=serviceJob;
    }


    @Operation(summary = "add job",description = "adding a new job")
    @PostMapping("/add")
    public ResponseEntity<?> createJob(@RequestBody Job job) {
        Job j = serviceJob.create(job);
        if (j != null) {
            return new ResponseEntity<>(j, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>("error occured", HttpStatus.BAD_REQUEST);
        }
    }


    @Operation(summary = "update a job",description = "update a job the id field must be specified so the job fields will be over written")
    @PutMapping("/update")
    public ResponseEntity<?> updateJob(@RequestBody Job job){
        Job j=serviceJob.update(job);
        if (j != null) {
            return new ResponseEntity<>(j, HttpStatus.ACCEPTED);
        } else {
            return new ResponseEntity<>("error occured", HttpStatus.BAD_REQUEST);
        }
    }


    @Operation(summary = "delete a job",description = "delete a job by id")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteJob(@PathVariable int id) {
        serviceJob.delete(id);
        return new ResponseEntity<>("", HttpStatus.ACCEPTED);
    }


    @Operation(summary = "get a job",description = "get a job by id")
    @GetMapping("/get/{id}")
    public ResponseEntity<?> getOneJob(@PathVariable int id) {
        Job j = serviceJob.getOne(id);
        if (j != null) {
            return new ResponseEntity<>(j, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }


    @Operation(summary = "list of jobs",description = "get the list of job offers active and not active ")
    @GetMapping("/getall")
    public ResponseEntity<?> alljobs() {
        return new ResponseEntity<>(serviceJob.getAll(), HttpStatus.OK);
    }


    @Operation(summary = "search for a job",description = "searcg for job with a key word")
    @GetMapping("/search")
    public ResponseEntity<?> search(@RequestParam(name = "filter") String filter) {
        return new ResponseEntity<>(serviceJob.search(filter), HttpStatus.OK);
    }

    @Operation(summary = "active job offers",description = "get the list of active job offers")
    @GetMapping("/active")
    public ResponseEntity<?> activejobs() {
        return new ResponseEntity<>(serviceJob.activeJobs(), HttpStatus.OK);
    }

    @Operation(summary = "close a job",description = "close a job offer and make it disabled")
    @PutMapping("/closeJob/{id}")
    public ResponseEntity<?> closeJob(@PathVariable int id){
        Job job=serviceJob.closeJob(id);
        return new ResponseEntity<>(job,HttpStatus.OK);

    }

}
