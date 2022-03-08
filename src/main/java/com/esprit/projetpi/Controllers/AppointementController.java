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
    @Operation(summary = "delete an appointement",description = "delete an appointement with the id")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteAppointement(@PathVariable int id) {
        serviceAppointement.delete(id);
        return new ResponseEntity<>("", HttpStatus.ACCEPTED);
    }

    @Operation(summary = "get one appointement",description = "get one appointement with the id")
    @GetMapping("/get/{id}")
    public ResponseEntity<?> getOneAppointement(@PathVariable int id) {
        Appointement app = serviceAppointement.getOne(id);
        if (app != null) {
            return new ResponseEntity<>(app, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }
    @Operation(summary = "get all appointements",description = "the list of all appointement")
    @GetMapping("/getall")
    public ResponseEntity<?> allAppointement() {
        return new ResponseEntity<>(serviceAppointement.getAll(), HttpStatus.OK);
    }

    @Operation(summary = "coming appointement",description = "list of the upcoming appointement")
    @GetMapping("/coming")
    public ResponseEntity<?> comingAppointement() {
        return new ResponseEntity<>(serviceAppointement.comingAppointement(), HttpStatus.OK);
    }

    @Operation(summary = "search appointements by subject",description = "search for appointements by specifying the subject")
    @GetMapping("/bysubject")
    public ResponseEntity<?> searchByType(@RequestParam(name = "subject") String subject) {
        return new ResponseEntity<>(serviceAppointement.searchBySubject(subject), HttpStatus.OK);
    }

    @Operation(summary = "search appointements by email",description = "search for appointements by specifying the email")
    @GetMapping("/byEmail")
    public ResponseEntity<?> searchByEmail(@RequestParam(name = "email") String email) {
        return new ResponseEntity<>(serviceAppointement.searchByEmail(email), HttpStatus.OK);
    }

    @Operation(summary = "search appointements by name",description = "search for appointements by specifying the name")
    @GetMapping("/byName")
    public ResponseEntity<?> searchByname(@RequestParam(name = "name") String name) {
        return new ResponseEntity<>(serviceAppointement.searchByname(name), HttpStatus.OK);
    }

    @Operation(summary = "search appointements by date",description = "search for appointements by specifying the date in the format of dd-MM-yyyy ex:07-03-2022")
    @GetMapping("/byDate")
    public ResponseEntity<?> searchBydate(@RequestParam(name = "date") String date) {
        return new ResponseEntity<>(serviceAppointement.searchByDate(date), HttpStatus.OK);
    }

    @Operation(summary = "delete appointements by email",description = "delete an appointement by specifying the email")
    @DeleteMapping("/deleteByEmail")
    public ResponseEntity<?> deleteByEmail(@RequestParam(name = "email")String email){
        serviceAppointement.deleteByEmail(email);
        return new ResponseEntity<>("done",HttpStatus.ACCEPTED);
    }
}
