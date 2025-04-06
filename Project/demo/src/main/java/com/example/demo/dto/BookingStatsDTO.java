package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class BookingStatsDTO {
    private String label;  // Nhãn (Tháng X)
    private Long value;    // Số lượt đặt sân
}


