package com.ticketbooking.model;
import jakarta.persistence.*; import lombok.*; import java.math.BigDecimal; import java.time.LocalDateTime;
@Entity @Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Payment {
 @Id @GeneratedValue(strategy=GenerationType.IDENTITY) Long id;
 @OneToOne(optional=false) Ticket ticket; @Enumerated(EnumType.STRING) PaymentMethod method;
 BigDecimal amount; String status; LocalDateTime paidAt;
}