package com.ticketbooking.repository;
import com.ticketbooking.model.Ticket; import org.springframework.data.jpa.repository.JpaRepository; import java.util.*;
public interface TicketRepository extends JpaRepository<Ticket,Long>{List<Ticket> findByUserId(Long userId);}