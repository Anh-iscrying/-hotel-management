package com.example.hotel_management.controller;

import com.example.hotel_management.dto.RoomDTO;
import com.example.hotel_management.service.RoomService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/rooms")
@Tag(name = "Room Management", description = "APIs cho quản lý phòng")
public class RoomController {

    private final RoomService roomService;

    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    @PostMapping
    public RoomDTO create(@RequestBody RoomDTO dto) {
        return roomService.createRoom(dto);
    }

    @GetMapping
    public List<RoomDTO> getAll() {
        return roomService.getAllRooms();
    }
}