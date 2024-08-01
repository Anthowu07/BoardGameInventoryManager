package com.skillstorm.project1.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.skillstorm.project1.models.Inventory;

@Repository
public interface InventoryRepo extends JpaRepository<Inventory, Integer>{
    
}
