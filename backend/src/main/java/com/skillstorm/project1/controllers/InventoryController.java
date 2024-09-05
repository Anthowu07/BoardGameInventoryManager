package com.skillstorm.project1.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.skillstorm.project1.dtos.InventoryRequest;
import com.skillstorm.project1.models.BoardGame;
import com.skillstorm.project1.models.Inventory;
import com.skillstorm.project1.models.Warehouse;
import com.skillstorm.project1.services.BoardGameService;
import com.skillstorm.project1.services.InventoryService;
import com.skillstorm.project1.services.WarehouseService;

@RestController
@RequestMapping("/api/inventory")
public class InventoryController {
    @Autowired
    private InventoryService service;

    @Autowired
    private BoardGameService boardGameService;

    @Autowired
    private WarehouseService warehouseService;

    // public InventoryController(InventoryService service) {
    // this.service = service;
    // }

    @GetMapping
    public Iterable<Inventory> findAllInventories() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Inventory> findById(@PathVariable int id) {
        Optional<Inventory> inventory = service.findById(id);
        if (inventory.isPresent()) {
            return ResponseEntity.ok(inventory.get());
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // POST Method to add a inventory entry
    @PostMapping()
    @ResponseStatus(code = HttpStatus.CREATED)
    public ResponseEntity<?> Create(@RequestBody InventoryRequest inventoryRequest) {
        Optional<BoardGame> boardGameOptional = boardGameService.findById(inventoryRequest.getBoardgame_id());
        Optional<Warehouse> warehouseOptional = warehouseService.findById(inventoryRequest.getWarehouse_id());

        // If given board game id or warehouse id is not present in their tables, return
        // an error NOT_FOUND
        if (!boardGameOptional.isPresent()) {
            return new ResponseEntity<>("Board game with that ID is not found", HttpStatus.NOT_FOUND);
        } else if (!warehouseOptional.isPresent()) {
            return new ResponseEntity<>("Warehouse with that ID is not found", HttpStatus.NOT_FOUND);
        }

        BoardGame boardgame = boardGameOptional.get();
        Warehouse warehouse = warehouseOptional.get();
        // Update warehouse quantity field
        warehouse.setNum_items(warehouse.getNum_items() + boardgame.getReorder_quantity());
        Inventory inventory = new Inventory();

        inventory.setQuantity_available(inventoryRequest.getQuantity_available());
        inventory.setReorder_point(inventoryRequest.getReorder_point());
        inventory.setBoardgame(boardgame);
        inventory.setWarehouse(warehouse);
        inventory.setMinimum_stock_level(inventoryRequest.getMinimum_stock_level());
        inventory.setMaximum_stock_level(inventoryRequest.getMaximum_stock_level());

        // Create inventory table entry
        inventory = service.save(inventory);

        return new ResponseEntity<>(inventory, HttpStatus.CREATED);
    }

    // PUT Method to update/change inventory entry
    // The method checks if the value exists in the table before attempting the
    // method
    @PutMapping("/{id}")
    public ResponseEntity<Inventory> updateInventory(@PathVariable("id") int id, @RequestBody Inventory inventory) {
        inventory.setInventory_id(id);
        Optional<Inventory> existingInventory = this.service.findById(inventory.getInventory_id());
        Inventory updatedInventory = inventory;
        if (existingInventory.isPresent()) {
            Inventory oldInventory = existingInventory.get();
            inventory.setBoardgame(oldInventory.getBoardgame());
            inventory.setWarehouse(oldInventory.getWarehouse());
            // When adding more inventory, update the num_items property of that warehouse
            inventory.getWarehouse().setNum_items(inventory.getWarehouse().getNum_items()
                    + (updatedInventory.getQuantity_available() - oldInventory.getQuantity_available()));

            // Hard coded values for now
            inventory.setReorder_point(5);
            inventory.setMaximum_stock_level(100);
            inventory.setMinimum_stock_level(0);
            updatedInventory = this.service.save(inventory);
            return new ResponseEntity<>(updatedInventory, HttpStatus.OK);
        }
        return ResponseEntity.notFound().build();
    }

    // DELETE method to erase data from table
    @DeleteMapping("/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable int id) {
        Optional<Inventory> existingInventory = service.findById(id);
        if (existingInventory.isPresent()) {
            Inventory inventory = existingInventory.get();
            inventory.getWarehouse()
                    .setNum_items(inventory.getWarehouse().getNum_items() - inventory.getQuantity_available());
            service.deleteById(id);
        }
    }

}
