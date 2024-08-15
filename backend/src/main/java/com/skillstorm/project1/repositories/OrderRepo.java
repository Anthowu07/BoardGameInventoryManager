package com.skillstorm.project1.repositories;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.skillstorm.project1.models.Order;

@Repository
public interface OrderRepo extends JpaRepository<Order, Integer> {
    
}
