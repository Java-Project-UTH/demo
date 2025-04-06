package com.example.demo.repository;

import com.example.demo.model.Booking;
import com.example.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {
    // Kiểm tra xung đột đặt sân: nếu có booking nào trùng khoảng thời gian không
    Booking findByCourtIdAndStartTimeLessThanEqualAndEndTimeGreaterThanEqual(
            Long courtId, LocalDateTime endTime, LocalDateTime startTime);

    // Đếm số lượt đặt sân theo tháng
    @Query("SELECT FUNCTION('MONTH', b.startTime), COUNT(b) FROM Booking b GROUP BY FUNCTION('MONTH', b.startTime)")
    List<Object[]> countBookingsByMonth();

    // Lấy 5 booking mới nhất (dựa vào trường createdAt trong Booking)
    List<Booking> findTop5ByOrderByCreatedAtDesc();

    // Lấy danh sách booking của một user, sắp xếp theo startTime giảm dần
    List<Booking> findByUserOrderByStartTimeDesc(User user);
}
