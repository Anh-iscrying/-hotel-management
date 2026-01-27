/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.example.hotel_management.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "reservations")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "guest_id")
    private Guest guest;

    @ManyToOne
    @JoinColumn(name = "room_id")
    private Room room;

    private LocalDate checkInDate;
    private LocalDate checkOutDate;

    private Double totalAmount;

    @Column(unique = true)
    private String confirmationNumber; // BR-008: Mã xác nhận HTLYYYY...

    private String status; // PENDING, CONFIRMED, CHECKED_IN, CANCELLED
    
    @Builder.Default
    private LocalDateTime createdAt = LocalDateTime.now();
}
