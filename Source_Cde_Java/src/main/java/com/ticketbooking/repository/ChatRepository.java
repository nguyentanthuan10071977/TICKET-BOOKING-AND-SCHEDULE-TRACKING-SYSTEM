package com.ticketbooking.repository;
import com.ticketbooking.model.ChatMessage; import org.springframework.data.jpa.repository.JpaRepository; import java.util.*;
public interface ChatRepository extends JpaRepository<ChatMessage,Long>{List<ChatMessage> findByUserIdOrderByCreatedAtAsc(Long userId);}