package com.esprit.projetpi.Controllers;

import com.esprit.projetpi.Entities.Appointement;
import com.esprit.projetpi.Services.IServiceAppointement;
import com.esprit.projetpi.Services.ServiceAppointement;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("appointement")
public class AppointementController {

    private IServiceAppointement serviceAppointement;

    public AppointementController(IServiceAppointement serviceAppointement) {
        this.serviceAppointement = serviceAppointement;
    }

    @Operation(summary = "add appointement",description = "adding a new appointement")
    @PostMapping("/add")
    public ResponseEntity<?> createAppointement(@RequestBody Appointement appointement) {
        Appointement app = serviceAppointement.create(appointement);
        if (app != null) {
            return new ResponseEntity<>(app, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>("error occured", HttpStatus.BAD_REQUEST);
        }
    }

    @Operation(summary = "update appointement",description = "update appointement")
    @PutMapping("/update")
    public ResponseEntity<?> updateAppointement(@RequestBody Appointement appointement) {
        Appointement app = serviceAppointement.update(appointement);
        if (app != null) {
            return new ResponseEntity<>(app, HttpStatus.ACCEPTED);
        } else {
            return new ResponseEntity<>("error occured", HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteAppointement(@PathVariable int id) {
        serviceAppointement.delete(id);
        return new ResponseEntity<>("", HttpStatus.ACCEPTED);
    }


    @GetMapping("/get/{id}")
    public ResponseEntity<?> getOneAppointement(@PathVariable int id) {
        Appointement app = serviceAppointement.getOne(id);
        if (app != null) {
            return new ResponseEntity<>(app, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/getall")
    public ResponseEntity<?> allAppointement() {
        return new ResponseEntity<>(serviceAppointement.getAll(), HttpStatus.OK);
    }

    @GetMapping("/coming")
    public ResponseEntity<?> comingAppointement() {
        return new ResponseEntity<>(serviceAppointement.comingAppointement(), HttpStatus.OK);
    }
    @GetMapping("/bysubject")
    public ResponseEntity<?> searchByType(@RequestParam(name = "subject") String subject) {
        return new ResponseEntity<>(serviceAppointement.searchBySubject(subject), HttpStatus.OK);
    }

    @GetMapping("/byEmail")
    public ResponseEntity<?> searchByEmail(@RequestParam(name = "email") String email) {
        return new ResponseEntity<>(serviceAppointement.searchByEmail(email), HttpStatus.OK);
    }

    @GetMapping("/byName")
    public ResponseEntity<?> searchByname(@RequestParam(name = "name") String name) {
        return new ResponseEntity<>(serviceAppointement.searchByname(name), HttpStatus.OK);
    }

    @GetMapping("/byDate")
    public ResponseEntity<?> searchBydate(@RequestParam(name = "date") String date) {
        return new ResponseEntity<>(serviceAppointement.searchByDate(date), HttpStatus.OK);
    }

    @DeleteMapping("/deleteByEmail")
    public ResponseEntity<?> deleteByEmail(@RequestParam(name = "email")String email){
        serviceAppointement.deleteByEmail(email);
        return new ResponseEntity<>("done",HttpStatus.ACCEPTED);
    }
}
