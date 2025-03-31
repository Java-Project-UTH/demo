package com.example.demo.controller;

import com.example.demo.model.Court;
import com.example.demo.repository.CourtRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/courts")
public class CourtController {

    private final CourtRepository courtRepository;

    // ✅ Constructor Injection (Best Practice)
    public CourtController(CourtRepository courtRepository) {
        this.courtRepository = courtRepository;
    }

    @GetMapping
    public String listCourts(Model model) {
        model.addAttribute("courts", courtRepository.findAll());
        model.addAttribute("newCourt", new Court());
        return "courts";
    }

    @PostMapping
    public String addCourt(Court court) {
        court.setAvailable(true);
        courtRepository.save(court);
        return "redirect:/courts";
    }
}