package com.example.demo.controller;

import com.example.demo.model.Booking;
import com.example.demo.model.Court;
import com.example.demo.model.User;
import com.example.demo.repository.BookingRepository;
import com.example.demo.repository.CourtRepository;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class DashboardController {

    @Autowired
    private CourtRepository courtRepository;

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        User currentUser = userRepository.findByUsername(username).orElse(null);
        boolean isAdmin = authentication.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ADMIN"));
        
        if (isAdmin) {
            return adminDashboard(model);
        } else {
            return userDashboard(model, currentUser);
        }
    }

    private String adminDashboard(Model model) {
        // Thống kê tổng quan
        long totalCourts = courtRepository.count();
        long availableCourts = courtRepository.countByAvailable(true);
        long totalBookings = bookingRepository.count();

        // Lấy các booking gần đây
        List<Booking> recentBookings = bookingRepository.findTop5ByOrderByCreatedAtDesc();

        model.addAttribute("totalCourts", totalCourts);
        model.addAttribute("availableCourts", availableCourts);
        model.addAttribute("totalBookings", totalBookings);
        model.addAttribute("recentBookings", recentBookings);
        model.addAttribute("isAdmin", true);
        
        return "dashboard";
    }

    private String userDashboard(Model model, User user) {
        if (user == null) {
            return "redirect:/login";
        }

        // Lấy booking của user
        List<Booking> userBookings = bookingRepository.findByUserOrderByStartTimeDesc(user);
        
        // Lấy các sân đang trống
        List<Court> availableCourts = courtRepository.findByAvailable(true);

        model.addAttribute("userBookings", userBookings);
        model.addAttribute("availableCourts", availableCourts);
        model.addAttribute("isAdmin", false);
        
        return "dashboard";
    }
}