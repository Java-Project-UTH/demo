package com.example.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;

import java.time.LocalTime;

@Entity
@Data
public class CourtPrice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Court court;

    private LocalTime startTime;
    private LocalTime endTime;
    private double price;
    private String description;

    public Long getId() {
        return id;
    }

    public Court getCourt() {
        return court;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public double getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setCourt(Court court) {
        this.court = court;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public CourtPrice() {}

    public CourtPrice(Court court, LocalTime startTime, LocalTime endTime, double price, String description) {
        this.court = court;
        this.startTime = startTime;
        this.endTime = endTime;
        this.price = price;
        this.description = description;
    }
}