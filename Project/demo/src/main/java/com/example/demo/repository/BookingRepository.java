package com.example.demo.repository;

import com.example.demo.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface BookingRepository extends JpaRepository<Booking, Long> {
    Booking findByCourtIdAndStartTimeLessThanEqualAndEndTimeGreaterThanEqual(Long courtId, java.time.LocalDateTime endTime, java.time.LocalDateTime startTime);
    @Query("SELECT FUNCTION('MONTH', b.startTime), COUNT(b) FROM Booking b GROUP BY FUNCTION('MONTH', b.startTime)")
    List<Object[]> countBookingsByMonth();
}