package com.example.demo.service;

import com.example.demo.dto.BookingStatsDTO;
import com.example.demo.repository.BookingRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StatisticsService {
    private final BookingRepository bookingRepository;

    public StatisticsService(BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }

    public List<BookingStatsDTO> getBookingStatistics() {
        List<Object[]> results = bookingRepository.countBookingsByMonth();
        return results.stream()
                .map(obj -> new BookingStatsDTO(
                        "Tháng " + obj[0],
                        ((Number) obj[1]).longValue()  // Chuyển đổi an toàn
                ))
                .collect(Collectors.toList());
    }
}
