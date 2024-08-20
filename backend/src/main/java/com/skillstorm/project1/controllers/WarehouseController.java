package com.skillstorm.project1.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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

import com.skillstorm.project1.models.Inventory;
import com.skillstorm.project1.models.Warehouse;
import com.skillstorm.project1.services.WarehouseService;

@RestController
@RequestMapping("/api/warehouses")
public class WarehouseController {
    @Autowired
    private WarehouseService service;

    public WarehouseController(WarehouseService service) {
        this.service = service;
    }

    @GetMapping
    public Iterable<Warehouse> findAllWarehouses(){
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Warehouse> findById(@PathVariable int id){
        Optional<Warehouse> warehouse = service.findById(id);
        if(warehouse.isPresent()){
            return ResponseEntity.ok(warehouse.get());
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    //POST Method to add a warehouse entry
    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public ResponseEntity<Warehouse> Create(@RequestBody Warehouse warehouse){
        Optional<Warehouse> existingWarehouse = this.service.findById(warehouse.getWarehouse_id());

        //Returns code 409 CONFLICT if boardgame id alredy exists in database
        if(existingWarehouse.isPresent()){
            return new ResponseEntity<>(warehouse, HttpStatus.CONFLICT);
        }
        warehouse = service.save(warehouse);
        return new ResponseEntity<>(warehouse, HttpStatus.CREATED);
    }

    //PUT Method to update/change Warehouse entry
    //The method checks if the value exists in the table before attempting the method
    @PutMapping("/{id}")
    public ResponseEntity<Warehouse> updateWarehouse(@PathVariable("id") int id, @RequestBody Warehouse warehouse){
        warehouse.setWarehouse_id(id);
        Optional<Warehouse> existingWarehouse = this.service.findById(warehouse.getWarehouse_id());
        Warehouse updatedWarehouse = warehouse;
        if(existingWarehouse.isPresent()){
            updatedWarehouse = this.service.save(warehouse);
            return new ResponseEntity<>(updatedWarehouse, HttpStatus.OK);
        }
        return ResponseEntity.notFound().build();
    }

    //DELETE method to erase data from table
    @DeleteMapping("/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable int id) {
        service.deleteById(id);
    }

    //GET request to fetch join table (all inventories related to one warehouse ID)
    @GetMapping(value = "/{id}/inventories", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Inventory>> getInventoriesByWarehouseId(@PathVariable int id) {
        List<Inventory> inventories = service.getInventoriesByWarehouseId(id);
        return ResponseEntity.ok(inventories);
    }
    
}
