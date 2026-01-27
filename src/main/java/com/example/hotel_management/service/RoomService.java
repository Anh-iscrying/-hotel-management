package com.example.hotel_management.service;

import com.example.hotel_management.dto.RoomDTO;
import com.example.hotel_management.entity.Room;
import com.example.hotel_management.entity.RoomType;
import com.example.hotel_management.repository.RoomRepository;
import com.example.hotel_management.repository.RoomTypeRepository;
import com.example.hotel_management.util.RoomMapper;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service // Lỗi "class Service" là do thiếu dòng import org.springframework.stereotype.Service
public class RoomService {

    private final RoomRepository roomRepository;
    private final RoomTypeRepository roomTypeRepository;

    public RoomService(RoomRepository roomRepository, RoomTypeRepository roomTypeRepository) {
        this.roomRepository = roomRepository;
        this.roomTypeRepository = roomTypeRepository;
    }

    public RoomDTO createRoom(RoomDTO roomDTO) {
        RoomType roomType = roomTypeRepository.findById(roomDTO.getRoomTypeId())
                .orElseThrow(() -> new RuntimeException("Loại phòng không tồn tại."));

        Room room = new Room();
        room.setRoomNumber(roomDTO.getRoomNumber());
        room.setStatus(roomDTO.getStatus());
        room.setIsSmoking(roomDTO.getIsSmoking());
        room.setRoomType(roomType);

        return RoomMapper.toDTO(roomRepository.save(room));
    }

    public List<RoomDTO> getAllRooms() {
        return roomRepository.findAll().stream()
                .map(RoomMapper::toDTO)
                .collect(Collectors.toList());
    }
}