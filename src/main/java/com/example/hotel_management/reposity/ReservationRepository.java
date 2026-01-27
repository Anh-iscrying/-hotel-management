/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.example.hotel_management.repository;

import com.example.hotel_management.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    
    // Tìm mã xác nhận (BR-008)
    Reservation findByConfirmationNumber(String confirmationNumber);

    // Hàm "Sát thủ" để check trùng lịch (BR-003)
    // Tìm tất cả các đơn đặt phòng của 1 phòng cụ thể mà bị trùng khoảng ngày khách chọn
    @Query("SELECT r FROM Reservation r WHERE r.room.id = :roomId " +
           "AND r.status != 'CANCELLED' " +
           "AND (:startDate < r.checkOutDate AND :endDate > r.checkInDate)")
    List<Reservation> findOverlappingReservations(
            @Param("roomId") Long roomId, 
            @Param("startDate") LocalDate startDate, 
            @Param("endDate") LocalDate endDate);
}
