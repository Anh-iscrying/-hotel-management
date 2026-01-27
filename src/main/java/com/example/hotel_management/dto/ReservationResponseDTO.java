/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.example.hotel_management.dto;

import lombok.*;
import java.time.LocalDate;

@Data
@Builder
public class ReservationResponseDTO {
    private String confirmationNumber;
    private String guestName;
    private String roomNumber;
    private LocalDate checkInDate;
    private LocalDate checkOutDate;
    private Double totalAmount;
    private String status;
}
