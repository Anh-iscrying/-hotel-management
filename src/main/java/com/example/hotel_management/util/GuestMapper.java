/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.example.hotel_management.util;

import com.example.hotel_management.dto.GuestDTO;
import com.example.hotel_management.entity.Guest;

public class GuestMapper {

    // Chuyển từ Entity sang DTO (Trả về cho Client)
    public static GuestDTO toDTO(Guest guest) {
        if (guest == null) return null;
        
        return GuestDTO.builder()
                .id(guest.getId())
                .firstName(guest.getFirstName())
                .lastName(guest.getLastName())
                .email(guest.getEmail())
                .phone(guest.getPhone())
                .birthDate(guest.getBirthDate())
                .loyaltyPoints(guest.getLoyaltyPoints())
                .build();
    }

    // Chuyển từ DTO sang Entity (Lưu vào Database)
    public static Guest toEntity(GuestDTO dto) {
        if (dto == null) return null;
        
        Guest guest = new Guest();
        guest.setFirstName(dto.getFirstName());
        guest.setLastName(dto.getLastName());
        guest.setEmail(dto.getEmail());
        guest.setPhone(dto.getPhone());
        guest.setBirthDate(dto.getBirthDate());
        return guest;
    }
}
