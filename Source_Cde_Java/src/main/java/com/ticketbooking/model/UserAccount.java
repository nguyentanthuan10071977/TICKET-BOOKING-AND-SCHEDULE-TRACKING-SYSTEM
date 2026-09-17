package com.ticketbooking.model;
import jakarta.persistence.*; import lombok.*;
@Entity @Table(name="users") @Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class UserAccount {
 @Id @GeneratedValue(strategy=GenerationType.IDENTITY) Long id;
 @Column(nullable=false,unique=true) String email;
 @Column(nullable=false) String password;
 @Column(nullable=false) String fullName;
 String mobilePhone,gender,dateOfBirth,confirmationCode;
 @Enumerated(EnumType.STRING) Role role;
 boolean enabled=true,confirmed=false;
}