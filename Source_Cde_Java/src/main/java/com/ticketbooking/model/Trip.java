package com.ticketbooking.model;
import jakarta.persistence.*; import lombok.*; import java.math.BigDecimal; import java.time.*;
@Entity @Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Trip {
 @Id @GeneratedValue(strategy=GenerationType.IDENTITY) Long id;
 String pickupPoint,destination,carType,status;
 LocalDate departureDate; LocalTime departureTime;
 BigDecimal price; int totalSeats,availableSeats;
}