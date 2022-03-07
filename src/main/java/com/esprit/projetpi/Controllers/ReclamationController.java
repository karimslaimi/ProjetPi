package com.esprit.projetpi.Controllers;

import com.esprit.projetpi.Entities.Reclamation;
import com.esprit.projetpi.Services.IServiceReclamation;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@RestController
@RequestMapping("reclamation")
public class ReclamationController {


    private IServiceReclamation reclamationService;

    public ReclamationController(IServiceReclamation reclamationService){
        this.reclamationService=reclamationService;
    }


    @Operation(summary = "add a claim",description = "add a new claim by specifying all the fields")
    @PostMapping("/add")
    public ResponseEntity<?> AddReclam(@RequestBody Reclamation reclamation){

        if(!reclamationService.Create(reclamation)){
            return new ResponseEntity<>("could not create the claim", HttpStatus.BAD_REQUEST);
        }else{
            return new ResponseEntity<>("claim created",HttpStatus.CREATED);
        }

    }


    @Operation(summary = "delete a claim",description = "delete a claim by specifying the id of the claim in the path variable if the claim exist it will be deleted")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteReclam(@PathVariable int id){
        if(id!=0){
            reclamationService.Delete(id);
            return new ResponseEntity<>("deleted",HttpStatus.ACCEPTED);

        }else{
            return new ResponseEntity<>("not deleted",HttpStatus.BAD_REQUEST);
        }
    }


    @Operation(summary = "treat a claim",description = "treat a claim by specifying the id of the claim to treat and the treatement message")
    @PutMapping("/treat/{id}")
    public ResponseEntity<?> treatReclam(@RequestBody String treatement, @PathVariable int id){
        boolean flag= reclamationService.treat(id,treatement);
        if(flag){
            return new ResponseEntity<>("updated",HttpStatus.ACCEPTED);
        }else{
            return new ResponseEntity<>("not updated",HttpStatus.BAD_REQUEST);
        }
    }

    @Operation(summary = "get one claim",description = "get a claim by id, specify the id")
    @GetMapping("/{id}")
    public ResponseEntity<?> getOne(@PathVariable int id){
        Reclamation reclam=reclamationService.getOne(id);
        if(reclam!=null){
            return new ResponseEntity<>(reclam,HttpStatus.OK);
        }else{
            return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
        }
    }


    @Operation(summary = "find all",description = "get the list of all claims the treated and not treated")
    @GetMapping("/")
    public ResponseEntity<?> getAll(){
        return new ResponseEntity<>(reclamationService.findAll(),HttpStatus.OK);
    }


    @Operation(summary = "get not treated",description = "get the list of not treated claims")
    @GetMapping("/nottreated")
    public ResponseEntity<?> NotTreated(){
        return new ResponseEntity<>(reclamationService.findNotTreated(),HttpStatus.OK);
    }


    @Operation(summary = "search claims",description = "search for claims with a key word ")
    @GetMapping("/search")
    public ResponseEntity<?> search(@RequestParam(name = "query") String query){
        return new ResponseEntity<>(reclamationService.filter(query),HttpStatus.OK);
    }


    @Operation(summary = "between two date",description = "get the claims between two dates, the date format must be dd-MM-yyyy ex: 25-07-2021")
    @GetMapping("/between")
    public ResponseEntity<?> betweenDate(@RequestParam(name = "start") String start,@RequestParam(name = "end") String end){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDateTime s = LocalDateTime.parse(start, formatter);
        LocalDateTime e = LocalDateTime.parse(end, formatter);

        return new ResponseEntity<>(reclamationService.findBetweenDate(s,e),HttpStatus.OK);
    }


    @Operation(summary = "get list of claims by type",description = "every claim have a type this method will search for claims that have the specified type")
    @GetMapping("/type")
    public ResponseEntity<?> byType(@RequestParam(name = "query") String query){
        return new ResponseEntity<>(reclamationService.findByType(query),HttpStatus.OK);
    }



    @Operation(summary = "advanced search",description = "here you can search for claims with diffrent types of filter (key word, type, date, treated or not")
    @GetMapping("/searchmulti")
    public ResponseEntity<?> searchMulti(@RequestParam(name="filter",required = false) String filter, @RequestParam(name="type",required = false) String type,
                                         @RequestParam(name="start",required = false) String start,@RequestParam(name="end",required = false) String end,
                                        @RequestParam(name="treated",required = false) String treat){

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

        LocalDateTime s=LocalDateTime.parse("2020-12-31 00:00",formatter);
        LocalDateTime e=LocalDateTime.parse("2030-12-31 00:00",formatter);

        boolean treated=false;



        if(start!=null){
            s = LocalDateTime.parse(start, formatter);

        }
        if(end!=null){
            e = LocalDateTime.parse(end, formatter);
        }

        if(treat!=null){
            treated=Boolean.parseBoolean(treat);

        }

        return new ResponseEntity<> (reclamationService.searchMultiCriteria(filter,type,s,e,treated),HttpStatus.OK);


    }






}
