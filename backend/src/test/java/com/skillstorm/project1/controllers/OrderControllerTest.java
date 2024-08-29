package com.skillstorm.project1.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Optional;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.skillstorm.project1.dtos.InventoryRequest;
import com.skillstorm.project1.dtos.OrderRequest;
import com.skillstorm.project1.models.BoardGame;
import com.skillstorm.project1.models.Inventory;
import com.skillstorm.project1.models.Order;
import com.skillstorm.project1.models.Warehouse;
import com.skillstorm.project1.services.BoardGameService;
import com.skillstorm.project1.services.InventoryService;
import com.skillstorm.project1.services.OrderService;
import com.skillstorm.project1.services.WarehouseService;

public class OrderControllerTest {

    @Mock
    private OrderService orderService;

    @Mock
    private InventoryService inventoryService;

    @Mock
    private BoardGameService boardGameService;

    @Mock
    private WarehouseService warehouseService;

    @InjectMocks
    private OrderController orderController;
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
    public void findAllOrdersTest() {
        Iterable<Order> expectedOrders = Arrays.asList(new Order(), new Order());

        when(orderService.findAll()).thenReturn(expectedOrders);

        Iterable<Order> response = orderController.findAllOrders();

        assertEquals(expectedOrders, response);
    }

    @Test
    public void findByIdSuccessTest() {
        int orderId = 1;
        Order expectedOrder = new Order();

        when(orderService.findById(orderId))
        
        .thenReturn(Optional.of(expectedOrder));

        ResponseEntity<Order> response = orderController.findById(orderId);

        assertEquals(expectedOrder, response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());

    }

    @Test
    public void findByIdFailureTest() {
        int orderId = 1;
        Order expectedOrder = new Order();

        when(orderService.findById(orderId))
        
        .thenReturn(Optional.of(expectedOrder));

        ResponseEntity<Order> response = orderController.findById(30);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());

    }

    @Test
    public void createFailureNoBoardGameTest() {
        Order inputOrder = new Order();
        Order savedOrder = new Order();
        OrderRequest request = new OrderRequest();

        when(orderService.save(inputOrder)).thenReturn(savedOrder);

        ResponseEntity<?> response = orderController.Create(request);

        assertEquals("Board game with that ID is not found", response.getBody());
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    public void createFailureNoWarehouseTest() {
        Order inputOrder = new Order();
        Order savedOrder = new Order();
        OrderRequest request = new OrderRequest();
        BoardGame savedBoardGame = new BoardGame();

        int gameId = 5;
        request.setBoardgame_id(gameId);


        when(orderService.save(inputOrder)).thenReturn(savedOrder);
        when(boardGameService.findById(gameId)).thenReturn(Optional.of(savedBoardGame));

        ResponseEntity<?> response = orderController.Create(request);

        assertEquals("Warehouse with that ID is not found", response.getBody());
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    public void createBadRequestTest() {
        Order inputOrder = new Order();
        Order savedOrder = new Order();
        OrderRequest request = new OrderRequest();
        BoardGame savedBoardGame = new BoardGame();
        Warehouse savedWarehouse = new Warehouse();

        int gameId = 5;
        request.setBoardgame_id(gameId);

        int warehouseId = 10;
        request.setWarehouse_id(warehouseId);


        when(orderService.save(inputOrder)).thenReturn(savedOrder);
        when(boardGameService.findById(gameId)).thenReturn(Optional.of(savedBoardGame));
        when(warehouseService.findById(warehouseId)).thenReturn(Optional.of(savedWarehouse));

        ResponseEntity<?> response = orderController.Create(request);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    public void createSuccessTest() {
        Order inputOrder = new Order();
        Order savedOrder = new Order();
        OrderRequest request = new OrderRequest();
        BoardGame savedBoardGame = new BoardGame();
        Warehouse savedWarehouse = new Warehouse();
        savedWarehouse.setCapacity(10);
        savedWarehouse.setNum_items(7);

        int gameId = 5;
        request.setBoardgame_id(gameId);

        int warehouseId = 10;
        request.setWarehouse_id(warehouseId);


        when(orderService.save(inputOrder)).thenReturn(savedOrder);
        when(boardGameService.findById(gameId)).thenReturn(Optional.of(savedBoardGame));
        when(warehouseService.findById(warehouseId)).thenReturn(Optional.of(savedWarehouse));

        ResponseEntity<?> response = orderController.Create(request);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }


}
