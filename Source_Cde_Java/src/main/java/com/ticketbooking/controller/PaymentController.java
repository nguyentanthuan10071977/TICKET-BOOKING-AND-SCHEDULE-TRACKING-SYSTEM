package com.ticketbooking.controller;
import com.ticketbooking.model.*; import com.ticketbooking.repository.*; import org.springframework.http.*; import org.springframework.web.bind.annotation.*; import java.time.LocalDateTime;
@RestController @RequestMapping("/api/payments") public class PaymentController {
 final PaymentRepository payments;final TicketRepository tickets;public PaymentController(PaymentRepository p,TicketRepository t){payments=p;tickets=t;}
 @PostMapping("/online") ResponseEntity<?> online(@RequestParam Long ticketId){return pay(ticketId,PaymentMethod.ONLINE,"PAID");}
 @PostMapping("/counter") ResponseEntity<?> counter(@RequestParam Long ticketId){return pay(ticketId,PaymentMethod.COUNTER,"PENDING");}
 private ResponseEntity<?> pay(Long id,PaymentMethod m,String status){return tickets.findById(id).map(t->{if(m==PaymentMethod.ONLINE)t.setStatus("PAID");tickets.save(t);return ResponseEntity.status(201).body(payments.save(Payment.builder().ticket(t).method(m).amount(t.getAmount()).status(status).paidAt(m==PaymentMethod.ONLINE?LocalDateTime.now():null).build()));}).orElse(ResponseEntity.notFound().build());}
}