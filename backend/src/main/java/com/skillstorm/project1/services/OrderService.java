package com.skillstorm.project1.services;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.skillstorm.project1.models.Order;
import com.skillstorm.project1.repositories.OrderRepo;

@Service
public class OrderService {

    private OrderRepo repo;

    public OrderService(OrderRepo repo) {
        this.repo = repo;
    }

    public Iterable<Order> findAll(){
        return repo.findAll();
    }

    public Optional<Order> findById(int id){
        return repo.findById(id);
    }

    //Used for PUT/POST requests
    public Order save(Order order){
        return repo.save(order);
    }

    //Used for DELETE requests
    public void deleteById(int id) {
        repo.deleteById(id);
    }
    
}
