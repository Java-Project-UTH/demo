package com.example.demo.controller;

import com.example.demo.service.BookingRequestService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/booking-requests")
public class BookingRequestController {
    private final BookingRequestService bookingRequestService;

    public BookingRequestController(BookingRequestService bookingRequestService) {
        this.bookingRequestService = bookingRequestService;
    }

    @GetMapping
    public String showRequests(Model model) {
        model.addAttribute("requests", bookingRequestService.getAllRequests());
        return "booking-requests";
    }

    @PostMapping("/update")
    public String updateStatus(@RequestParam Long id, @RequestParam String status) {
        bookingRequestService.updateRequestStatus(id, status);
        return "redirect:/booking-requests";
    }
}
