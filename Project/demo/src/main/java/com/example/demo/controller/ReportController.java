package com.example.demo.controller;

import com.example.demo.service.ReportService;
import com.example.demo.dto.RevenueReportDTO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.List;

@Controller
@RequestMapping("/reports")
public class ReportController {
    private final ReportService reportService;

    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }

    @GetMapping("/booking")
    public String generateBookingReport(Model model) {
        List<RevenueReportDTO> report = reportService.getRevenueReport("month"); // Lấy doanh thu theo tháng
        model.addAttribute("report", report); // Truyền dữ liệu vào Thymeleaf
        return "revenue-reports"; // Render file revenue-reports.html
    }
}
