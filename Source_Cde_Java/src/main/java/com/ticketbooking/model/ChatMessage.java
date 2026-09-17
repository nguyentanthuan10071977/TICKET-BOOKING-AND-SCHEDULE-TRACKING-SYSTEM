package com.ticketbooking.model;
import jakarta.persistence.*; import lombok.*; import java.time.LocalDateTime;
@Entity @Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class ChatMessage {
 @Id @GeneratedValue(strategy=GenerationType.IDENTITY) Long id;
 Long userId; String sender; @Column(length=5000) String message; LocalDateTime createdAt;
 @PrePersist void init(){createdAt=LocalDateTime.now();}
}