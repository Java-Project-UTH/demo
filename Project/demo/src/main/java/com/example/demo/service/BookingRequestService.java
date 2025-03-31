package com.example.demo.service;

import com.example.demo.model.BookingRequest;
import com.example.demo.repository.BookingRequestRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class BookingRequestService {
    private final BookingRequestRepository bookingRequestRepository;

    public BookingRequestService(BookingRequestRepository bookingRequestRepository) {
        this.bookingRequestRepository = bookingRequestRepository;
    }

    public List<BookingRequest> getAllRequests() {
        return bookingRequestRepository.findAll();
    }

    public void updateRequestStatus(Long id, String status) {
        BookingRequest request = bookingRequestRepository.findById(id).orElseThrow();
        request.setStatus(status);
        bookingRequestRepository.save(request);
    }
}

