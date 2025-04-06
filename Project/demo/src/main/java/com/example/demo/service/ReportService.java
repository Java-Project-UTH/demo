package com.example.demo.service;

import com.example.demo.dto.RevenueReportDTO;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class ReportService {

    public List<RevenueReportDTO> getRevenueReport(String period) {
        List<RevenueReportDTO> report = new ArrayList<>();

        switch (period.toLowerCase()) {
            case "day":
                report.add(new RevenueReportDTO(LocalDate.now(), 900.0));
                break;
            case "week":
                for (int i = 6; i >= 0; i--) {
                    report.add(new RevenueReportDTO(LocalDate.now().minusDays(i), 500.0 + (i * 50)));
                }
                break;
            case "month":
                for (int i = 29; i >= 0; i--) {
                    report.add(new RevenueReportDTO(LocalDate.now().minusDays(i), 400.0 + (i * 30)));
                }
                break;
            default:
                throw new IllegalArgumentException("Invalid period: " + period);
        }

        return report;
    }
}