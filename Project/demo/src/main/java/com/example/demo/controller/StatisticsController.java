package com.example.demo.controller;
import com.example.demo.dto.BookingStatsDTO;
import com.example.demo.service.StatisticsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class StatisticsController {
    private final StatisticsService statisticsService;

    public StatisticsController(StatisticsService statisticsService) {
        this.statisticsService = statisticsService;
    }

    @GetMapping("/statistics")
    public String showStatistics(Model model) {
        List<BookingStatsDTO> stats = statisticsService.getBookingStatistics();
        model.addAttribute("stats", stats);
        return "statistics";
    }
}

