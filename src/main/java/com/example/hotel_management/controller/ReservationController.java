package com.example.hotel_management.controller;

import com.example.hotel_management.dto.ReservationRequestDTO;
import com.example.hotel_management.dto.ReservationResponseDTO;
import com.example.hotel_management.service.ReservationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/reservations")
@Tag(name = "Reservation Management", description = "Quản lý đặt phòng khách sạn")
public class ReservationController {

    private final ReservationService reservationService;

    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @PostMapping
    @Operation(summary = "Tạo đơn đặt phòng mới", description = "Kiểm tra phòng trống và tính tiền tự động")
    public ReservationResponseDTO create(@RequestBody ReservationRequestDTO request) {
        return reservationService.createReservation(request);
    }

    @GetMapping
    @Operation(summary = "Lấy danh sách tất cả đơn đặt phòng")
    public List<ReservationResponseDTO> getAll() {
        return reservationService.getAllReservations();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Lấy chi tiết đơn đặt phòng theo ID")
    public ReservationResponseDTO getById(@PathVariable Long id) {
        return reservationService.getReservationById(id);
    }
}