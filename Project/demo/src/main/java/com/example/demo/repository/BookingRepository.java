package com.example.demo.repository;

import com.example.demo.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingRepository extends JpaRepository<Booking, Long> {
    Booking findByCourtIdAndStartTimeLessThanEqualAndEndTimeGreaterThanEqual(Long courtId, java.time.LocalDateTime endTime, java.time.LocalDateTime startTime);
}