/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.example.hotel_management.entity;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "room_types")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoomType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    private Double basePrice;
    
    private Integer maxOccupancy;

    @OneToMany(mappedBy = "roomType", cascade = CascadeType.ALL)
    @JsonIgnore // Tránh bị vòng lặp vô tận khi xuất JSON
    private List<Room> rooms;
}
