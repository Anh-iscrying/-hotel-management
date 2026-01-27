/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.example.hotel_management.service;

import com.example.hotel_management.dto.GuestDTO;
import com.example.hotel_management.entity.Guest;
import com.example.hotel_management.repository.GuestRepository;
import com.example.hotel_management.util.GuestMapper;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class GuestService {
    private final GuestRepository guestRepository;

    public GuestService(GuestRepository guestRepository) {
        this.guestRepository = guestRepository;
    }

    public List<GuestDTO> getAllGuests() {
        return guestRepository.findAll().stream()
                .map(GuestMapper::toDTO)
                .collect(Collectors.toList());
    }

    public GuestDTO createGuest(GuestDTO guestDTO) {
        // BR-101: Kiểm tra tuổi >= 18
        if (Period.between(guestDTO.getBirthDate(), LocalDate.now()).getYears() < 18) {
            throw new RuntimeException("Khách hàng phải từ 18 tuổi trở lên.");
        }
        
        // BR-102: Kiểm tra trùng Email
        if (guestRepository.findByEmail(guestDTO.getEmail()).isPresent()) {
            throw new RuntimeException("Email đã tồn tại trong hệ thống.");
        }

        Guest guest = GuestMapper.toEntity(guestDTO);
        return GuestMapper.toDTO(guestRepository.save(guest));
    }
}
