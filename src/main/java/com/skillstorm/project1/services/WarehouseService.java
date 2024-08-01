package com.skillstorm.project1.services;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.skillstorm.project1.models.Warehouse;
import com.skillstorm.project1.repositories.WarehouseRepo;

@Service
public class WarehouseService {
    
    private WarehouseRepo repo;

    public WarehouseService(WarehouseRepo repo) {
        this.repo = repo;
    }

    public Iterable<Warehouse> findAll(){
        return repo.findAll();
    }

    public Optional<Warehouse> findById(int id){
        return repo.findById(id);
    }

    //Used for PUT/POST requests
    public Warehouse save(Warehouse warehouse){
        return repo.save(warehouse);
    }

    //Used for DELETE requests
    public void deleteById(int id) {
        repo.deleteById(id);
    }
}
