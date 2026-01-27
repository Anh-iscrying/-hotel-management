package com.example.hotel_management.controller;

import com.example.hotel_management.dto.GuestDTO;
import com.example.hotel_management.service.GuestService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/guests")
@Tag(name = "Guest Management", description = "APIs cho quản lý khách hàng")
public class GuestController {
    private final GuestService guestService;

    public GuestController(GuestService guestService) {
        this.guestService = guestService;
    }

    @GetMapping
    public List<GuestDTO> getAll() {
        return guestService.getAllGuests();
    }

    @PostMapping
    public GuestDTO create(@RequestBody GuestDTO guestDTO) {
        return guestService.createGuest(guestDTO);
    }
}