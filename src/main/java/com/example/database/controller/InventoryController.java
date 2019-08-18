package com.example.database.controller;

import com.example.database.exception.NotFoundException;
import com.example.database.persistence.entity.Inventory;
import com.example.database.persistence.repo.InventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(path = "/api/inventory")
public class InventoryController {

    @Autowired
    private InventoryRepository inventoryRepository;

    @PostMapping(produces = "application/json")
    public Inventory addInventory(@RequestBody Inventory inventory) {
        return inventoryRepository.save(inventory);
    }

    @DeleteMapping(path = "/{id}", produces = "application/json")
    public Inventory deleteInventory(@PathVariable("id") long id) {
        Optional<Inventory> optionalInventory = inventoryRepository.findById(id);
        if (!optionalInventory.isPresent()) {
            throw new NotFoundException();
        }
        inventoryRepository.deleteById(id);
        return optionalInventory.get();
    }
}
