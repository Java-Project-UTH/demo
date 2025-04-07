package com.example.demo.controller;

import com.example.demo.model.CourtPrice;
import com.example.demo.service.CourtPriceService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/court-prices")
public class CourtPriceController {
    private final CourtPriceService courtPriceService;

    public CourtPriceController(CourtPriceService courtPriceService) {
        this.courtPriceService = courtPriceService;
    }

    @GetMapping
    public String listPrices(Model model) {
        List<CourtPrice> prices = courtPriceService.getAllPrices();
        model.addAttribute("prices", prices);
        model.addAttribute("newPrice", new CourtPrice());
        return "court-prices";
    }

    @GetMapping("/court/{courtId}")
    public String listPricesByCourt(@PathVariable Long courtId, Model model) {
        List<CourtPrice> prices = courtPriceService.getPricesByCourtId(courtId);
        model.addAttribute("prices", prices);
        return "court-prices";
    }

    @PostMapping
    public String addPrice(@ModelAttribute CourtPrice courtPrice) {
        courtPriceService.addPrice(courtPrice);
        return "redirect:/court-prices";
    }

    @PostMapping("/update/{id}")
    public String updatePrice(@PathVariable Long id, @ModelAttribute CourtPrice courtPrice) {
        courtPriceService.updatePrice(id, courtPrice);
        return "redirect:/court-prices";
    }

    @PostMapping("/delete/{id}")
    public String deletePrice(@PathVariable Long id) {
        courtPriceService.deletePrice(id);
        return "redirect:/court-prices";
    }
}