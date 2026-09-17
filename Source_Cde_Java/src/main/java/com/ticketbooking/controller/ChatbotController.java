package com.ticketbooking.controller;
import com.ticketbooking.model.ChatMessage; import com.ticketbooking.repository.ChatRepository; import org.springframework.http.*; import org.springframework.web.bind.annotation.*; import java.util.*;
@RestController @RequestMapping("/api/chatbot") public class ChatbotController {
 final ChatRepository repo;public ChatbotController(ChatRepository r){repo=r;}
 @GetMapping("/{userId}") List<ChatMessage> history(@PathVariable Long userId){return repo.findByUserIdOrderByCreatedAtAsc(userId);}
 @PostMapping("/{userId}/ask") Map<String,String> ask(@PathVariable Long userId,@RequestParam String question){repo.save(ChatMessage.builder().userId(userId).sender("USER").message(question).build());String a="Your question has been received. An administrator/instructor can provide more information.";repo.save(ChatMessage.builder().userId(userId).sender("CHATBOT").message(a).build());return Map.of("answer",a);}
}