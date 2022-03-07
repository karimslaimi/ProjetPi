package com.esprit.projetpi.Controllers;

import com.esprit.projetpi.Entities.Candidature;
import com.esprit.projetpi.Services.IServiceCandidature;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.UUID;

@RestController
@RequestMapping("candidature")
public class CandidatureController {
    private IServiceCandidature serviceCandidature;

    public CandidatureController(IServiceCandidature service){
        this.serviceCandidature=service;
    }



    @Operation(summary = "create application",description = "apply for a job offer by specifying the id of the job and the the application fields \n upload the cv and the lm (motivation letter)")
    @PostMapping("create/{id}")
    public ResponseEntity<?> createCandidature(@RequestBody Candidature candidate, @PathVariable int id,@RequestParam("cv") MultipartFile cv,@RequestParam("lm") MultipartFile lm){
        if(!cv.isEmpty()){

            try {

                // Get the file and save it somewhere
                byte[] bytes = cv.getBytes();
                Path path = Paths.get(System.getProperty("java.io.tmpdir") + cv.getOriginalFilename()+ UUID.randomUUID());
                Files.write(path, bytes);
                candidate.setCv(path.toString());


            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if(!lm.isEmpty()){

            try {

                // Get the file and save it somewhere
                byte[] bytes = lm.getBytes();
                Path path = Paths.get(System.getProperty("java.io.tmpdir") + lm.getOriginalFilename()+ UUID.randomUUID());
                Files.write(path, bytes);
                candidate.setLm(path.toString());


            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        
        Candidature c=serviceCandidature.postC(candidate, id);
        if(c!=null){
            return new ResponseEntity<>(c, HttpStatus.CREATED);
        }else{
            return new ResponseEntity<>("error occured",HttpStatus.BAD_REQUEST);
        }
    }


    @Operation(summary = "get application for a job",description = "get all the applications for a specific job offer (specify the id of the job offer)")
    @GetMapping("/cList/{id}")
    public ResponseEntity<?> jobCList(@PathVariable int id){
        return new ResponseEntity<>(serviceCandidature.jobCList(id),HttpStatus.OK);
    }

    @Operation(summary = "all applications",description = "get the list of all applications")
    @GetMapping("/all")
    public ResponseEntity<?> allCandidature(){
        return new ResponseEntity<>(serviceCandidature.allCandidature(),HttpStatus.ACCEPTED);
    }


    @Operation(summary = "filter by state",description = "filter the applications by the state of application (accepted/refused/on hold)")
    @GetMapping("/filter")
    public ResponseEntity<?> filterByState(@RequestParam(name="filter")String filter){
        return new ResponseEntity<>(serviceCandidature.filterByState(filter),HttpStatus.OK);
    }


    @Operation(summary = "add interview date",description = "add an interview date for the application by specifying the id of the application and the date of the interview with this format (dd-MM-yyyy hh:mm ex: 25-07-2021 21:50")
    @PutMapping("addInterview/{id}")
    public ResponseEntity<?> addInterview(@RequestParam(name="date")String date,@PathVariable int id){
        Candidature c=serviceCandidature.addInterview(id,date);
        if(c!=null){
            return new ResponseEntity<>(c,HttpStatus.OK);
        }else{
            return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
        }
    }

    @Operation(summary = "interview list for a job",description = "the list of interview for a specified job offer with the id ")
    @GetMapping("/interview/{id}")
    public ResponseEntity<?> listInterview(@PathVariable int id){
        return new ResponseEntity<>(serviceCandidature.listInterview(id),HttpStatus.OK);
    }
    @Operation(summary = "next interview list",description = "the list of the upcoming interview")
    @GetMapping("/nextInterview")
    public ResponseEntity<?> nextInterview(){
        return new ResponseEntity<>(serviceCandidature.nextInterview(),HttpStatus.OK);
    }

    @Operation(summary = "update application's state",description = "update an application state relative to the id make it accepted/refused or on hold ")
    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateCandidate(@RequestParam(name="state")String state,@PathVariable int id){
        Candidature c=serviceCandidature.updateCandidate(id,state);
        if(c==null){
            return new ResponseEntity<>("error occured",HttpStatus.BAD_REQUEST);
        }else{
            return new ResponseEntity<>(c,HttpStatus.OK);
        }
    }
}
