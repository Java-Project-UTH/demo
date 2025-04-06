package com.example.demo.controller;

import com.example.demo.model.Booking;
import com.example.demo.model.Court;
import com.example.demo.repository.BookingRepository;
import com.example.demo.repository.CourtRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Optional;  // ✅ Thêm để sử dụng Optional
@Controller
@RequestMapping("/bookings")
public class BookingController {

        private final BookingRepository bookingRepository;
        private final CourtRepository courtRepository;

        public BookingController(BookingRepository bookingRepository, CourtRepository courtRepository) {
            this.bookingRepository = bookingRepository;
            this.courtRepository = courtRepository;
        }

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

        // ✅ Xử lý Optional để tránh lỗi nếu không tìm thấy sân
        Optional<Court> optionalCourt = courtRepository.findById(courtId);
        if (optionalCourt.isEmpty()) {
            model.addAttribute("error", "Không tìm thấy sân với ID: " + courtId);
            model.addAttribute("bookings", bookingRepository.findAll());
            model.addAttribute("courts", courtRepository.findAll());
            return "bookings";
        }

        Court court = optionalCourt.get();

        // ✅ Kiểm tra nếu repository chưa có phương thức findByCourtIdAndStartTimeLessThanEqualAndEndTimeGreaterThanEqual
        Booking existingBooking = bookingRepository
                .findByCourtIdAndStartTimeLessThanEqualAndEndTimeGreaterThanEqual(courtId, end, start);

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
