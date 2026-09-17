package com.ticketbooking.model;
import jakarta.persistence.*; import lombok.*; import java.math.BigDecimal; import java.time.LocalDateTime;
@Entity @Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Ticket {
 @Id @GeneratedValue(strategy=GenerationType.IDENTITY) Long id;
 @ManyToOne(optional=false) UserAccount user; @ManyToOne(optional=false) Trip trip;
 String seatNumber,status; @Enumerated(EnumType.STRING) PaymentMethod paymentMethod;
 BigDecimal amount; LocalDateTime bookedAt;
 @PrePersist void init(){bookedAt=LocalDateTime.now();}
}