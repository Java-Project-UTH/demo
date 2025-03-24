package com.example.demo.controller;

import com.example.demo.model.Booking;
import com.example.demo.model.Court;
import com.example.demo.repository.BookingRepository;
import com.example.demo.repository.CourtRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@Controller
@RequestMapping("/bookings")
public class BookingController {

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private CourtRepository courtRepository;

    @GetMapping
    public String getAllBookings(Model model) {
        model.addAttribute("bookings", bookingRepository.findAll());
        model.addAttribute("courts", courtRepository.findAll());
        return "bookings";
    }

    @PostMapping("/add")
    public String addBooking(@RequestParam Long courtId,
                           @RequestParam String startTime,
                           @RequestParam String endTime,
                           @RequestParam String userEmail,
                           Model model) {
        
        LocalDateTime start = LocalDateTime.parse(startTime);
        LocalDateTime end = LocalDateTime.parse(endTime);

        // Validate start time is before end time
        if (!start.isBefore(end)) {
            model.addAttribute("error", "Thời gian bắt đầu phải trước thời gian kết thúc!");
            model.addAttribute("bookings", bookingRepository.findAll());
            model.addAttribute("courts", courtRepository.findAll());
            return "bookings";
        }

        Court court = courtRepository.findById(courtId)
                .orElseThrow(() -> new RuntimeException("Court not found"));

        // Check for booking conflicts
        Booking existingBooking = bookingRepository.findByCourtIdAndStartTimeLessThanEqualAndEndTimeGreaterThanEqual(
                courtId, end, start);
        if (existingBooking != null) {
            model.addAttribute("error", "Sân đã được đặt trong khung giờ này!");
            model.addAttribute("bookings", bookingRepository.findAll());
            model.addAttribute("courts", courtRepository.findAll());
            return "bookings";
        }

        Booking booking = new Booking();
        booking.setCourt(court);
        booking.setStartTime(start);
        booking.setEndTime(end);
        booking.setUserEmail(userEmail);

        // Save the booking
        bookingRepository.save(booking);

        // Update court availability
        court.setAvailable(false);
        courtRepository.save(court);

        return "redirect:/bookings";
    }
}