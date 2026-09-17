package com.ticketbooking.controller;
import com.ticketbooking.model.*; import com.ticketbooking.repository.UserRepository; import org.springframework.http.*; import org.springframework.security.crypto.password.PasswordEncoder; import org.springframework.web.bind.annotation.*; import java.util.*;
@RestController @RequestMapping("/api/users") public class UserController {
 final UserRepository repo; final PasswordEncoder enc; public UserController(UserRepository r,PasswordEncoder e){repo=r;enc=e;}
 @GetMapping List<UserAccount> all(){return repo.findAll();}
 @GetMapping("/{id}") ResponseEntity<UserAccount> get(@PathVariable Long id){return repo.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());}
 @PostMapping ResponseEntity<UserAccount> add(@RequestBody UserAccount u){u.setId(null);u.setPassword(enc.encode(u.getPassword()));return ResponseEntity.status(201).body(repo.save(u));}
 @PutMapping("/{id}") ResponseEntity<UserAccount> edit(@PathVariable Long id,@RequestBody UserAccount u){return repo.findById(id).map(o->{u.setId(id);u.setPassword(u.getPassword()==null?o.getPassword():enc.encode(u.getPassword()));u.setRole(o.getRole());return ResponseEntity.ok(repo.save(u));}).orElse(ResponseEntity.notFound().build());}
 @DeleteMapping("/{id}") ResponseEntity<Void> del(@PathVariable Long id){if(!repo.existsById(id))return ResponseEntity.notFound().build();repo.deleteById(id);return ResponseEntity.noContent().build();}
 @PutMapping("/{id}/password") ResponseEntity<Void> password(@PathVariable Long id,@RequestParam String value){return repo.findById(id).map(u->{u.setPassword(enc.encode(value));repo.save(u);return ResponseEntity.ok().build();}).orElse(ResponseEntity.notFound().build());}
}