package com.example.demo.repository;

import com.example.demo.model.CourtPrice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourtPriceRepository extends JpaRepository<CourtPrice, Long> {
    List<CourtPrice> findByCourtId(Long courtId);
}