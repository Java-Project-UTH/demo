package com.example.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Court court;

    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String userEmail;

    public Long getId() {
        return id;
    }

    public Court getCourt() {
        return court;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setCourt(Court court) {
        this.court = court;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public Booking(Long id, Court court, LocalDateTime startTime, LocalDateTime endTime) {
        this.id = id;
        this.court = court;
        this.startTime = startTime;
        this.endTime = endTime;
    }
    public Booking() {}
}