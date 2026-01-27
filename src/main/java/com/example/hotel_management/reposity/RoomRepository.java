/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.example.hotel_management.repository;

import com.example.hotel_management.entity.Room;
import com.example.hotel_management.entity.RoomStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {
    // Tìm danh sách phòng theo trạng thái (AVAILABLE, OCCUPIED...)
    List<Room> findByStatus(RoomStatus status);

    // Tìm phòng theo số phòng (101, 102...)
    Room findByRoomNumber(String roomNumber);
    
    // Tìm tất cả phòng thuộc một loại phòng cụ thể
    List<Room> findByRoomTypeId(Long roomTypeId);
}
