package com.ticketbooking.controller;
import com.ticketbooking.model.*; import com.ticketbooking.repository.UserRepository; import org.springframework.http.*; import org.springframework.security.crypto.password.PasswordEncoder; import org.springframework.web.bind.annotation.*; import java.util.*;
@RestController @RequestMapping("/api/auth") public class AuthController {
 final UserRepository repo; final PasswordEncoder encoder;
 public AuthController(UserRepository r,PasswordEncoder e){repo=r;encoder=e;}
 @PostMapping("/register") ResponseEntity<?> register(@RequestBody UserAccount u){
  if(repo.findByEmail(u.getEmail()).isPresent())return ResponseEntity.status(409).body(Map.of("message","Email already exists"));
  u.setId(null);u.setPassword(encoder.encode(u.getPassword()));u.setRole(Role.USER);u.setConfirmationCode("123456");return ResponseEntity.status(201).body(repo.save(u));
 }
 @PostMapping("/confirm") ResponseEntity<?> confirm(@RequestParam String email,@RequestParam String code){
  return repo.findByEmail(email).map(u->{if(!code.equals(u.getConfirmationCode()))return ResponseEntity.badRequest().body("Invalid code");u.setConfirmed(true);u.setConfirmationCode(null);repo.save(u);return ResponseEntity.ok("Confirmed");}).orElse(ResponseEntity.notFound().build());
 }
 @PostMapping("/login") ResponseEntity<?> login(@RequestBody UserAccount u){
  return repo.findByEmail(u.getEmail()).filter(x->x.isConfirmed()&&encoder.matches(u.getPassword(),x.getPassword()))
   .<ResponseEntity<?>>map(x->ResponseEntity.ok(Map.of("userId",x.getId(),"email",x.getEmail(),"role",x.getRole())))
   .orElse(ResponseEntity.status(401).body("Invalid credentials or not confirmed"));
 }
 @PostMapping("/logout") ResponseEntity<?> logout(){return ResponseEntity.ok(Map.of("message","Logged out"));}
}