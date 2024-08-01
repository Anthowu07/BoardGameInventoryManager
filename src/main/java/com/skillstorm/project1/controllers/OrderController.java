package com.skillstorm.project1.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skillstorm.project1.models.Order;
import com.skillstorm.project1.services.OrderService;

@RestController
@RequestMapping("/orders")
public class OrderController {
    @Autowired
    private OrderService service;

    public OrderController(OrderService service) {
        this.service = service;
    }

    @GetMapping
    public Iterable<Order> findAllMovies(){
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
    
}
