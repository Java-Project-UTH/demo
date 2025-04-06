package com.example.demo.repository;

import com.example.demo.dto.BookingStatsDTO;
import com.example.demo.model.Booking;
import com.example.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {

    Booking findByCourtIdAndStartTimeLessThanEqualAndEndTimeGreaterThanEqual(
            Long courtId, LocalDateTime endTime, LocalDateTime startTime);

    @Query("SELECT FUNCTION('MONTH', b.startTime), COUNT(b) FROM Booking b GROUP BY FUNCTION('MONTH', b.startTime)")
    List<Object[]> countBookingsByMonth();

    List<Booking> findTop5ByOrderByCreatedAtDesc();

    List<Booking> findByUserOrderByStartTimeDesc(User user);

    // ✅ Thống kê theo tháng (label: YYYY-MM)
    @Query("SELECT new com.example.demo.dto.BookingStatsDTO(" +
            "FUNCTION('DATE_FORMAT', b.createdAt, '%Y-%m'), COUNT(b)) " +
            "FROM Booking b " +
            "GROUP BY FUNCTION('DATE_FORMAT', b.createdAt, '%Y-%m') " +
            "ORDER BY FUNCTION('DATE_FORMAT', b.createdAt, '%Y-%m')")
    List<BookingStatsDTO> findMonthlyStats();

    // ✅ Thống kê theo quý (label: Q1-2025)
    @Query("SELECT new com.example.demo.dto.BookingStatsDTO(" +
            "CONCAT('Q', FUNCTION('QUARTER', b.createdAt), '-', FUNCTION('YEAR', b.createdAt)), COUNT(b)) " +
            "FROM Booking b " +
            "GROUP BY FUNCTION('YEAR', b.createdAt), FUNCTION('QUARTER', b.createdAt) " +
            "ORDER BY FUNCTION('YEAR', b.createdAt), FUNCTION('QUARTER', b.createdAt)")
    List<BookingStatsDTO> findQuarterlyStats();

    // ✅ Thống kê theo năm (label: 2024, 2025...)
    @Query("SELECT new com.example.demo.dto.BookingStatsDTO(" +
            "FUNCTION('YEAR', b.createdAt), COUNT(b)) " +
            "FROM Booking b " +
            "GROUP BY FUNCTION('YEAR', b.createdAt) " +
            "ORDER BY FUNCTION('YEAR', b.createdAt)")
    List<BookingStatsDTO> findYearlyStats();
}
