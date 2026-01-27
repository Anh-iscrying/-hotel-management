/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.example.hotel_management.repository;

import com.example.hotel_management.entity.Guest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface GuestRepository extends JpaRepository<Guest, Long> {
    // Để kiểm tra Email duy nhất (Quy tắc BR-102)
    Optional<Guest> findByEmail(String email);
    
    // Tìm kiếm khách theo tên hoặc số điện thoại (Hỗ trợ UC-001)
    Optional<Guest> findByPhone(String phone);
}