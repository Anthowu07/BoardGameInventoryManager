package com.skillstorm.project1.controllers;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.skillstorm.project1.dtos.OrderRequest;
import com.skillstorm.project1.models.BoardGame;
import com.skillstorm.project1.models.Inventory;
import com.skillstorm.project1.models.Order;
import com.skillstorm.project1.models.Warehouse;
import com.skillstorm.project1.services.BoardGameService;
import com.skillstorm.project1.services.InventoryService;
import com.skillstorm.project1.services.OrderService;
import com.skillstorm.project1.services.WarehouseService;

@RestController
@RequestMapping("/api/orders")
public class OrderController {
    @Autowired
    private OrderService service;

    @Autowired
    private BoardGameService boardGameService;
    @Autowired
    private InventoryService inventoryService;
    @Autowired
    private WarehouseService warehouseService;

    // public OrderController(OrderService service) {
    //     this.service = service;
    // }

    @GetMapping
    public Iterable<Order> findAllOrders(){
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Order> findById(@PathVariable int id){
        Optional<Order> order = service.findById(id);
        if(order.isPresent()){
            return ResponseEntity.ok(order.get());
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND)
;        }
    }

    //POST Method to add a order entry
    @PostMapping()
    @ResponseStatus(code = HttpStatus.CREATED)
    public ResponseEntity<?> Create(@RequestBody OrderRequest orderRequest){
        Optional<BoardGame> boardGameOptional = boardGameService.findById(orderRequest.getBoardgame_id());
        Optional<Warehouse> warehouseOptional = warehouseService.findById(orderRequest.getWarehouse_id());

        //If given board game id or warehouse id is not present in their tables, return an error NOT_FOUND
        if (!boardGameOptional.isPresent()) {
            return new ResponseEntity<>("Board game with that ID is not found", HttpStatus.NOT_FOUND);
        }else if (!warehouseOptional.isPresent()){
            return new ResponseEntity<>("Warehouse with that ID is not found", HttpStatus.NOT_FOUND);
        }

        //Based on order, make new inventory entry
        BoardGame boardgame = boardGameOptional.get();
        Warehouse warehouse = warehouseOptional.get();

        //Update warehouse quantity field
        if(warehouse.getCapacity() > warehouse.getNum_items() + boardgame.getReorder_quantity()){
            warehouse.setNum_items(warehouse.getNum_items() + boardgame.getReorder_quantity());
        }else{
            return new ResponseEntity<>("Warehouse over capacity", HttpStatus.BAD_REQUEST);
        }
        

        Inventory inventory = new Inventory();
        inventory.setBoardgame(boardgame);
        inventory.setWarehouse(warehouse);
        inventory.setQuantity_available(boardgame.getReorder_quantity());
        inventory.setReorder_point(5);
        inventoryService.save(inventory);
        
        Order order = new Order();
        order.setBoardgame(boardgame);
        order.setWarehouse(warehouse);
        order.setQuantity(boardgame.getReorder_quantity());
        order.setDate(LocalDate.now());

        
        //Create order table entry
        order = service.save(order);

        return new ResponseEntity<>(order, HttpStatus.CREATED);
    }
    
}
