package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
public class RevenueReportDTO {

    private LocalDate timePeriod;  // Thời gian (ngày, tháng, năm)
    private Double totalRevenue;   // Doanh thu tổng
}
