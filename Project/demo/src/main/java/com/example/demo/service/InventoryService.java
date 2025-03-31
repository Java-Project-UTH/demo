package com.example.demo.service;

import com.example.demo.model.Inventory;
import com.example.demo.repository.InventoryRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class InventoryService {
    private final InventoryRepository inventoryRepository;

    public InventoryService(InventoryRepository inventoryRepository) {
        this.inventoryRepository = inventoryRepository;
    }

    public List<Inventory> getAllItems() {
        return inventoryRepository.findAll();
    }

    public void addItem(Inventory item) {
        inventoryRepository.save(item);
    }
}

