package com.example.hotel_management.util;

import com.example.hotel_management.dto.RoomDTO;
import com.example.hotel_management.entity.Room;

public class RoomMapper {
    public static RoomDTO toDTO(Room room) {
        if (room == null) return null;
        return RoomDTO.builder()
                .id(room.getId())
                .roomNumber(room.getRoomNumber())
                .status(room.getStatus())
                .isSmoking(room.getIsSmoking())
                .roomTypeId(room.getRoomType() != null ? room.getRoomType().getId() : null)
                .roomTypeName(room.getRoomType() != null ? room.getRoomType().getName() : null)
                .build();
    }
}