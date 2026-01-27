package com.example.hotel_management.controller;

import com.example.hotel_management.entity.RoomType;
import com.example.hotel_management.repository.RoomTypeRepository;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/room-types")
@Tag(name = "Room Type Management", description = "APIs cho quản lý loại phòng")
public class RoomTypeController {

    private final RoomTypeRepository roomTypeRepository;

    public RoomTypeController(RoomTypeRepository roomTypeRepository) {
        this.roomTypeRepository = roomTypeRepository;
    }

    @PostMapping
    public RoomType create(@RequestBody RoomType rt) {
        return roomTypeRepository.save(rt);
    }

    @GetMapping
    public List<RoomType> getAll() {
        return roomTypeRepository.findAll();
    }
}