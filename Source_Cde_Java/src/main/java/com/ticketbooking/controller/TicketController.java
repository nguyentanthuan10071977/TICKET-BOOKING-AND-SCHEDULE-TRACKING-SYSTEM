package com.ticketbooking.controller;
import com.ticketbooking.model.*; import com.ticketbooking.repository.*; import org.springframework.http.*; import org.springframework.web.bind.annotation.*; import java.util.*;
@RestController @RequestMapping("/api/tickets") public class TicketController {
 final TicketRepository tickets; final UserRepository users; final TripRepository trips;
 public TicketController(TicketRepository t,UserRepository u,TripRepository p){tickets=t;users=u;trips=p;}
 @GetMapping List<Ticket> all(){return tickets.findAll();}
 @GetMapping("/{id}") ResponseEntity<Ticket> get(@PathVariable Long id){return tickets.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());}
 @GetMapping("/user/{userId}") List<Ticket> user(@PathVariable Long userId){return tickets.findByUserId(userId);}
 @PostMapping("/reserve") ResponseEntity<?> reserve(@RequestParam Long userId,@RequestParam Long tripId,@RequestParam String seatNumber,@RequestParam PaymentMethod paymentMethod){
  var u=users.findById(userId);var p=trips.findById(tripId);if(u.isEmpty()||p.isEmpty())return ResponseEntity.notFound().build();
  Trip t=p.get();if(t.getAvailableSeats()<1)return ResponseEntity.badRequest().body("No available seats");
  t.setAvailableSeats(t.getAvailableSeats()-1);trips.save(t);
  return ResponseEntity.status(201).body(tickets.save(Ticket.builder().user(u.get()).trip(t).seatNumber(seatNumber).paymentMethod(paymentMethod).amount(t.getPrice()).status("BOOKED").build()));
 }
 @PutMapping("/{id}/cancel") ResponseEntity<?> cancel(@PathVariable Long id){return tickets.findById(id).map(t->{if(!"CANCELLED".equals(t.getStatus())){t.setStatus("CANCELLED");t.getTrip().setAvailableSeats(t.getTrip().getAvailableSeats()+1);trips.save(t.getTrip());tickets.save(t);}return ResponseEntity.ok(t);}).orElse(ResponseEntity.notFound().build());}
}