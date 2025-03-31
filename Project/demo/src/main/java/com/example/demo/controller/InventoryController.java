package com.example.demo.controller;

import com.example.demo.model.Inventory;
import com.example.demo.service.InventoryService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/inventory")
public class InventoryController {
    private final InventoryService inventoryService;

    public InventoryController(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    @GetMapping
    public String showInventory(Model model) {
        model.addAttribute("inventory", inventoryService.getAllItems());
        return "inventory";
    }

    @PostMapping("/add")
    public String addItem(@ModelAttribute Inventory item) {
        inventoryService.addItem(item);
        return "redirect:/inventory";
    }
}
