package com.skillstorm.project1.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.skillstorm.project1.models.Inventory;

@Repository
public interface InventoryRepo extends JpaRepository<Inventory, Integer>{
    
    //Used to make join table between warehouse and inventory
    @Query("SELECT i FROM Inventory i WHERE i.warehouse.warehouse_id = :warehouseId")
    List<Inventory> findByWarehouseId(int warehouseId);
}
