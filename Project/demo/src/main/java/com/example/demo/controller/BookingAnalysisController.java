package com.example.demo.controller;

import com.example.demo.dto.BookingStatsDTO;
import com.example.demo.dto.RevenueReportDTO;
import com.example.demo.service.BookingAnalysisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/booking-analysis")
public class BookingAnalysisController {

    private final BookingAnalysisService bookingAnalysisService;

    // Constructor Injection
    @Autowired
    public BookingAnalysisController(BookingAnalysisService bookingAnalysisService) {
        this.bookingAnalysisService = bookingAnalysisService;
    }

    // Phân tích thống kê theo tháng
    @GetMapping("/monthly")
    public ResponseEntity<List<BookingStatsDTO>> getMonthlyBookingStats() {
        List<BookingStatsDTO> stats = bookingAnalysisService.getMonthlyBookingStats();
        return ResponseEntity.ok(stats);
    }

    // Phân tích thống kê theo quý
    @GetMapping("/quarterly")
    public ResponseEntity<List<BookingStatsDTO>> getQuarterlyBookingStats() {
        List<BookingStatsDTO> stats = bookingAnalysisService.getQuarterlyBookingStats();
        return ResponseEntity.ok(stats);
    }

    // Phân tích thống kê theo năm
    @GetMapping("/yearly")
    public ResponseEntity<List<BookingStatsDTO>> getYearlyBookingStats() {
        List<BookingStatsDTO> stats = bookingAnalysisService.getYearlyBookingStats();
        return ResponseEntity.ok(stats);
    }

    // Báo cáo doanh thu
    @GetMapping("/revenue")
    public ResponseEntity<List<RevenueReportDTO>> getRevenueReport() {
        List<RevenueReportDTO> revenueReport = bookingAnalysisService.getRevenueReport();
        return ResponseEntity.ok(revenueReport);
    }
}
