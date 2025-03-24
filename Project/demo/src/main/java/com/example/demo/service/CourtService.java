package com.example.demo.service;

import com.example.demo.model.Court;
import com.example.demo.repository.CourtRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CourtService {

    private final CourtRepository courtRepository;

    // Constructor Injection
    public CourtService(CourtRepository courtRepository) {
        this.courtRepository = courtRepository;
    }

    public List<Court> getAllCourts() {
        return courtRepository.findAll();
    }

    // Tìm sân theo ID
    public Optional<Court> getCourtById(Long id) {
        return courtRepository.findById(id);
    }

    // Thêm sân mới
    public Court addCourt(Court court) {
        court.setAvailable(true);
        return courtRepository.save(court);
    }

    public Court updateCourt(Long id, Court updatedCourt) {
        return courtRepository.findById(id).map(court -> {
            court.setName(updatedCourt.getName());
            court.setLocation(updatedCourt.getLocation());
            court.setAvailable(updatedCourt.isAvailable());
            return courtRepository.save(court);
        }).orElseThrow(() -> new RuntimeException("Court not found"));
    }

    // Xóa sân
    public void deleteCourt(Long id) {
        courtRepository.deleteById(id);
    }
}
