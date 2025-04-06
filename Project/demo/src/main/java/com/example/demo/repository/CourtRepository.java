package com.example.demo.repository;

import com.example.demo.model.Court;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface CourtRepository extends JpaRepository<Court, Long> {
    // Đếm số sân theo trạng thái available (true/false)
    long countByAvailable(boolean available);

    // Lấy danh sách các sân theo trạng thái available (true/false)
    List<Court> findByAvailable(boolean available);
}
