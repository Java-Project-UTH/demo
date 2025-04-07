package com.example.demo.service;

import com.example.demo.model.CourtPrice;
import com.example.demo.repository.CourtPriceRepository;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Service
public class CourtPriceService {
    private final CourtPriceRepository courtPriceRepository;

    public CourtPriceService(CourtPriceRepository courtPriceRepository) {
        this.courtPriceRepository = courtPriceRepository;
    }

    public List<CourtPrice> getAllPrices() {
        return courtPriceRepository.findAll();
    }

    public List<CourtPrice> getPricesByCourtId(Long courtId) {
        return courtPriceRepository.findByCourtId(courtId);
    }

    public Optional<CourtPrice> getPriceById(Long id) {
        return courtPriceRepository.findById(id);
    }

    public CourtPrice addPrice(CourtPrice courtPrice) {
        return courtPriceRepository.save(courtPrice);
    }

    public CourtPrice updatePrice(Long id, CourtPrice updatedPrice) {
        return courtPriceRepository.findById(id).map(price -> {
            price.setCourt(updatedPrice.getCourt());
            price.setStartTime(updatedPrice.getStartTime());
            price.setEndTime(updatedPrice.getEndTime());
            price.setPrice(updatedPrice.getPrice());
            price.setDescription(updatedPrice.getDescription());
            return courtPriceRepository.save(price);
        }).orElseThrow(() -> new RuntimeException("Court price not found"));
    }

    public void deletePrice(Long id) {
        courtPriceRepository.deleteById(id);
    }
}