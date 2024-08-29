package com.skillstorm.project1.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.skillstorm.project1.models.Inventory;
import com.skillstorm.project1.models.Order;
import com.skillstorm.project1.repositories.InventoryRepo;
import com.skillstorm.project1.repositories.OrderRepo;

public class OrderServiceTest {

    @Mock
    private OrderRepo orderRepo;

    @InjectMocks
    private OrderService orderService;
    private AutoCloseable closeable;

    @Before
    public void setup() {
        closeable = MockitoAnnotations.openMocks(this);
    }

    @After
    public void teardown() throws Exception {
        closeable.close();
    }

    @Test
    public void findAllTest() {
        List<Order> expectedOrders = Arrays.asList(new Order(), new Order());

        when(orderRepo.findAll()).thenReturn(expectedOrders);

        Iterable<Order> response = orderService.findAll();

        assertEquals(expectedOrders, response);
    }

    @Test
    public void findByIdTest() {
        Order expectedOrder = new Order();
        int orderId = 4;

        when(orderRepo.findById(orderId)).thenReturn(Optional.of(expectedOrder));

        Optional<Order> response = orderService.findById(orderId);

        assertEquals(Optional.of(expectedOrder), response);
    }

    @Test
    public void saveTest() {
        Order inputOrder = new Order();
        Order savedOrder = new Order();

        when(orderRepo.save(inputOrder)).thenReturn(savedOrder);

        Order response = orderService.save(inputOrder);

        assertEquals(savedOrder, response);
    }

    @Test
    public void deleteByIdTest() {
        orderService.deleteById(3);
    }
}
