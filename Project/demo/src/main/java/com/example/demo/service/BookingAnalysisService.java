package com.example.demo.service;

import com.example.demo.dto.BookingStatsDTO;
import com.example.demo.dto.RevenueReportDTO;
import com.example.demo.repository.BookingRepository;
import com.example.demo.repository.RevenueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookingAnalysisService {

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private RevenueRepository revenueRepository;

    // Phân tích thống kê theo tháng
    public List<BookingStatsDTO> getMonthlyBookingStats() {
        return bookingRepository.findMonthlyStats();
    }

    // Phân tích thống kê theo quý
    public List<BookingStatsDTO> getQuarterlyBookingStats() {
        return bookingRepository.findQuarterlyStats();
    }

    // Phân tích thống kê theo năm
    public List<BookingStatsDTO> getYearlyBookingStats() {
        return bookingRepository.findYearlyStats();
    }

    // Báo cáo doanh thu
    public List<RevenueReportDTO> getRevenueReport() {
        return revenueRepository.findRevenueReport();
    }
}
