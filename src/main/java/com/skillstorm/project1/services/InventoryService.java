package com.skillstorm.project1.services;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.skillstorm.project1.models.Inventory;
import com.skillstorm.project1.repositories.InventoryRepo;

@Service
public class InventoryService {
    private InventoryRepo repo;

    public InventoryService(InventoryRepo repo) {
        this.repo = repo;
    }

    public Iterable<Inventory> findAll(){
        return repo.findAll();
    }

    public Optional<Inventory> findById(int id){
        return repo.findById(id);
    }

    //Used for PUT/POST requests
    public Inventory save(Inventory inventory){
        return repo.save(inventory);
    }

    //Used for DELETE requests
    public void deleteById(int id) {
        repo.deleteById(id);
    }
}
