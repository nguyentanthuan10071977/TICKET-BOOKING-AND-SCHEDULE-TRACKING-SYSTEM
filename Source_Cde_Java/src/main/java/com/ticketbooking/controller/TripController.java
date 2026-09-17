package com.ticketbooking.controller;
import com.ticketbooking.model.Trip; import com.ticketbooking.repository.TripRepository; import org.springframework.http.*; import org.springframework.web.bind.annotation.*; import java.time.LocalDate; import java.util.*;
@RestController @RequestMapping("/api/trips") public class TripController {
 final TripRepository repo; public TripController(TripRepository r){repo=r;}
 @GetMapping List<Trip> all(){return repo.findAll();}
 @GetMapping("/{id}") ResponseEntity<Trip> get(@PathVariable Long id){return repo.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());}
 @GetMapping("/search") List<Trip> search(@RequestParam String pickup,@RequestParam String destination,@RequestParam(required=false) String departureDate){return departureDate==null?repo.findByPickupPointIgnoreCaseAndDestinationIgnoreCase(pickup,destination):repo.findByPickupPointIgnoreCaseAndDestinationIgnoreCaseAndDepartureDate(pickup,destination,LocalDate.parse(departureDate));}
 @PostMapping ResponseEntity<Trip> add(@RequestBody Trip t){if(t.getAvailableSeats()==0)t.setAvailableSeats(t.getTotalSeats());return ResponseEntity.status(201).body(repo.save(t));}
 @PutMapping("/{id}") ResponseEntity<Trip> edit(@PathVariable Long id,@RequestBody Trip t){return repo.findById(id).map(x->{t.setId(id);return ResponseEntity.ok(repo.save(t));}).orElse(ResponseEntity.notFound().build());}
 @DeleteMapping("/{id}") ResponseEntity<Void> del(@PathVariable Long id){if(!repo.existsById(id))return ResponseEntity.notFound().build();repo.deleteById(id);return ResponseEntity.noContent().build();}
}