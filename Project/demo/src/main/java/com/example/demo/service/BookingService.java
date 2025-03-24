package com.example.demo.service;

import com.example.demo.model.Booking;
import com.example.demo.model.Court;
import com.example.demo.repository.BookingRepository;
import com.example.demo.repository.CourtRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class BookingService {
    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private CourtRepository courtRepository;

    public List<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }

    public Optional<Booking> getBookingById(Long id) {
        return bookingRepository.findById(id);
    }

    public Booking addBooking(Long courtId, String startTime, String endTime, String userEmail) {
        LocalDateTime start = LocalDateTime.parse(startTime);
        LocalDateTime end = LocalDateTime.parse(endTime);

        if (!start.isBefore(end)) {
            throw new RuntimeException("Thời gian bắt đầu phải trước thời gian kết thúc!");
        }

        Court court = courtRepository.findById(courtId)
                .orElseThrow(() -> new RuntimeException("Court not found"));

        Booking existingBooking = bookingRepository.findByCourtIdAndStartTimeLessThanEqualAndEndTimeGreaterThanEqual(
                courtId, end, start);
        if (existingBooking != null) {
            throw new RuntimeException("Sân đã được đặt trong khung giờ này!");
        }

        Booking booking = new Booking();
        booking.setCourt(court);
        booking.setStartTime(start);
        booking.setEndTime(end);
        booking.setUserEmail(userEmail);
        bookingRepository.save(booking);

        court.setAvailable(false);
        courtRepository.save(court);

        return booking;
    }
}
