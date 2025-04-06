package com.example.demo.repository;

import com.example.demo.dto.RevenueReportDTO;
import com.example.demo.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RevenueRepository extends JpaRepository<Booking, Long> {

    @Query("SELECT new com.example.demo.dto.RevenueReportDTO(" +
            "FUNCTION('DATE_FORMAT', b.createdAt, '%Y-%m'), SUM(b.totalPrice)) " +
            "FROM Booking b " +
            "GROUP BY FUNCTION('DATE_FORMAT', b.createdAt, '%Y-%m') " +
            "ORDER BY FUNCTION('DATE_FORMAT', b.createdAt, '%Y-%m')")
    List<RevenueReportDTO> findRevenueReport();
}
