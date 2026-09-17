package com.ticketbooking.repository;
import com.ticketbooking.model.Trip; import org.springframework.data.jpa.repository.JpaRepository; import java.time.LocalDate; import java.util.*;
public interface TripRepository extends JpaRepository<Trip,Long>{
 List<Trip> findByPickupPointIgnoreCaseAndDestinationIgnoreCase(String pickup,String destination);
 List<Trip> findByPickupPointIgnoreCaseAndDestinationIgnoreCaseAndDepartureDate(String pickup,String destination,LocalDate date);
}