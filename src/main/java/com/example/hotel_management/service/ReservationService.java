package com.example.hotel_management.service;

import com.example.hotel_management.dto.ReservationRequestDTO;
import com.example.hotel_management.dto.ReservationResponseDTO;
import com.example.hotel_management.entity.Guest;
import com.example.hotel_management.entity.Reservation;
import com.example.hotel_management.entity.Room;
import com.example.hotel_management.repository.GuestRepository;
import com.example.hotel_management.repository.ReservationRepository;
import com.example.hotel_management.repository.RoomRepository;
import org.springframework.stereotype.Service;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Random;

@Service
public class ReservationService {

    private final ReservationRepository reservationRepository;
    private final RoomRepository roomRepository;
    private final GuestRepository guestRepository;

    public ReservationService(ReservationRepository reservationRepository, 
                              RoomRepository roomRepository, 
                              GuestRepository guestRepository) {
        this.reservationRepository = reservationRepository;
        this.roomRepository = roomRepository;
        this.guestRepository = guestRepository;
    }

    public ReservationResponseDTO createReservation(ReservationRequestDTO request) {
        // 1. Tìm Khách và Phòng
        Guest guest = guestRepository.findById(request.getGuestId())
                .orElseThrow(() -> new RuntimeException("Không tìm thấy khách hàng ID: " + request.getGuestId()));
        Room room = roomRepository.findById(request.getRoomId())
                .orElseThrow(() -> new RuntimeException("Không tìm thấy phòng ID: " + request.getRoomId()));

        // 2. Kiểm tra logic ngày tháng (BR-001)
        long days = ChronoUnit.DAYS.between(request.getCheckInDate(), request.getCheckOutDate());
        if (days <= 0) {
            throw new RuntimeException("Ngày trả phòng phải sau ngày nhận phòng ít nhất 1 ngày!");
        }

        // 3. Kiểm tra trùng lịch (BR-003)
        var overlaps = reservationRepository.findOverlappingReservations(
                request.getRoomId(), request.getCheckInDate(), request.getCheckOutDate());
        if (!overlaps.isEmpty()) {
            throw new RuntimeException("Phòng này đã có người đặt trong khoảng thời gian từ " 
                    + request.getCheckInDate() + " đến " + request.getCheckOutDate());
        }

        // 4. Tính toán tiền bạc (BR-301, 302)
        double basePrice = room.getRoomType().getBasePrice();
        double roomCharge = basePrice * days;
        double tax = roomCharge * 0.10; // Thuế 10%
        double serviceCharge = roomCharge * 0.05; // Phí dịch vụ 5%
        double total = roomCharge + tax + serviceCharge;

        // 5. Sinh mã xác nhận (BR-008): HTLYYYYMMDD-XXXXX
        String datePart = request.getCheckInDate().format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        String randomPart = String.format("%05d", new Random().nextInt(100000));
        String confNo = "HTL" + datePart + "-" + randomPart;

        // 6. Lưu vào Database
        Reservation res = new Reservation();
        res.setGuest(guest);
        res.setRoom(room);
        res.setCheckInDate(request.getCheckInDate());
        res.setCheckOutDate(request.getCheckOutDate());
        res.setTotalAmount(total);
        res.setConfirmationNumber(confNo);
        res.setStatus("CONFIRMED");

        Reservation saved = reservationRepository.save(res);
        return mapToResponseDTO(saved);
    }

    public List<ReservationResponseDTO> getAllReservations() {
        return reservationRepository.findAll().stream()
                .map(this::mapToResponseDTO)
                .toList();
    }

    public ReservationResponseDTO getReservationById(Long id) {
        Reservation res = reservationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy đơn đặt phòng ID: " + id));
        return mapToResponseDTO(res);
    }

    // Hàm phụ trợ để chuyển Entity sang DTO
    private ReservationResponseDTO mapToResponseDTO(Reservation saved) {
        return ReservationResponseDTO.builder()
                .confirmationNumber(saved.getConfirmationNumber())
                .guestName(saved.getGuest().getFirstName() + " " + saved.getGuest().getLastName())
                .roomNumber(saved.getRoom().getRoomNumber())
                .checkInDate(saved.getCheckInDate())
                .checkOutDate(saved.getCheckOutDate())
                .totalAmount(saved.getTotalAmount())
                .status(saved.getStatus())
                .build();
    }
}